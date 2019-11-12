/*
  Responsible for managing all invaders - asset group of 40 invaders.
*/

package invadem.assets;

import invadem.AssetGroup;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class InvaderSwarm extends AssetGroup {
  // The invaders
  protected List<Invader> invaders;
  // Responsible for invader projectiles
  protected int projectileTimer;
  protected int projectileRate;

  // Constants
  public static final int X_INITIAL = 171;
  public static final int Y_INITIAL = 20;
  public static final int WIDTH_INITIAL = 268;
  public static final int HEIGHT_INITIAL = 100;
  public static final int GAP = 12;
  public static final int PROJECTILE_RATE_INITIAL = 300;

  public InvaderSwarm(List<PImage> imgs) {
    super(X_INITIAL, Y_INITIAL, WIDTH_INITIAL, HEIGHT_INITIAL, 4, 10, Invader.WIDTH, Invader.HEIGHT);
    this.invaders = new ArrayList<Invader>();
    // Grab Images
    List<PImage> regularImgs = imgs.subList(0, 2);
    List<PImage> armouredImgs = imgs.subList(2, 4);
    List<PImage> powerImgs = imgs.subList(4, 6);

    // Set up grid of invaders
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == 0) {
          this.invaders.add(new ArmouredInvader(armouredImgs, X_INITIAL + j * (GAP + Invader.WIDTH), Y_INITIAL + i * (GAP + Invader.HEIGHT)));
        } else if (i == 1) {
          this.invaders.add(new PowerInvader(powerImgs, X_INITIAL + j * (GAP + Invader.WIDTH), Y_INITIAL + i * (GAP + Invader.HEIGHT)));
        } else {
          this.invaders.add(new Invader(regularImgs, X_INITIAL + j * (GAP + Invader.WIDTH), Y_INITIAL + i * (GAP + Invader.HEIGHT)));
        }
      }
    }

    this.projectileTimer = 0;
    this.projectileRate = PROJECTILE_RATE_INITIAL;
  }

  // Draw each invader (if they are alive) and check which direction they are
  // moving to appropriately change the bounds of the swarm
  public void draw(PApplet app) {
    int changeX = 0;
    int changeY = 0;
    boolean changeChecked = false;
    this.projectileTimer++;


    for (Invader invader : this.invaders) {
      if (invader.isAlive()) {

        if (!changeChecked) {
          changeX = -invader.getX();
          changeY = -invader.getY();
          invader.draw(app);
          changeX += invader.getX();
          changeY += invader.getY();
          changeChecked = true;
        } else {
          invader.draw(app);
        }

      }
    }

    this.xLeft += changeX;
    this.xRight += changeX;
    this.yTop += changeY;
    this.yBottom += changeY;
  }

  // Getter Methods
  public List<Invader> getInvaders() {return this.invaders;}

  public int getProjectileRate() {return this.projectileRate;}

  // Function that determines when to shoot
  public void checkIfShoot(CurrentProjectiles projectiles) {
    if (this.projectileTimer == this.projectileRate) {
      this.projectileTimer = 0;
      shootProjectile(projectiles);
    }
  }

  // Returns number of invaders that are alive
  public int numOfInvaders() {
    int sum = 0;
    for (Invader invader : this.invaders) {
      if (invader.isAlive()) {
        sum++;
      }
    }
    return sum;
  }

  // Function that checks if Minimum and Maximum x, y, row and col have changed
  public void checkBoundaries() {
    int i;

    // Check if left-most column of swarm is destroyed
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.leftCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.leftCol++;
      this.xLeft += this.assetWidth + GAP;
    }


    // Check if right-most column of swarm is destroyed
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.rightCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.rightCol--;
      this.xRight -= this.assetWidth + GAP;
    }


    // Check if right-most column of swarm is destroyed
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.topRow * 10 + i).isAlive()) {
        break;
      }
    }

    if (i == 10) {
      this.topRow++;
      this.yTop += this.assetHeight + GAP;
    }


    // Check if bottom-most column of swarm is destroyed
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.bottomRow * 10 + i).isAlive()) {
        break;
      }
    }

    if (i == 10) {
      this.bottomRow--;
      this.yBottom -= this.assetHeight + GAP;
    }
  }

  // Check if projectile collides into swarm
  public int checkCollisionWithProjectile(Projectile projectile) {
    boolean checkCollision = false;
    boolean invaderKilled = false;
    // For the score obtained by killing an Invader
    int scoreChange = 0;

    // First check if it collides with entire swarm (using the Minimum and
    // Maximum x and y values) - make the program slightly more efficient
    if (this.xLeft < (projectile.getX() + projectile.getWidth()) &&
        this.xRight > projectile.getX() &&
        this.yTop < (projectile.getY() + projectile.getHeight()) &&
        this.yBottom > projectile.getY()) {
      checkCollision = true;
    }

    // If it collides with entire swarm - check each invader
    if (checkCollision) {
      for (Invader invader : this.invaders) {
        if (invader.isAlive() && projectile.checkCollisionWithAsset(invader)) {
          invader.checkHealth();

          // If invader is killed, update score
          if (!invader.isAlive()) {
            invaderKilled = true;

            if (invader instanceof ArmouredInvader || invader instanceof PowerInvader) {
              scoreChange = 250;
            } else {
              scoreChange = 100;
            }
          }
        }
      }
    }

    // If invader is killed, check if bounds need to be updated
    if (invaderKilled) {
      checkBoundaries();
    }

    return scoreChange;
  }

  // Reset each invader and entire swarm
  public void reset() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        this.invaders.get(i * 10 + j).reset(X_INITIAL + j * 28, Y_INITIAL + i * 28);
      }
    }

    this.leftCol = 0;
    this.rightCol = 9;
    this.topRow = 0;
    this.bottomRow = 3;

    this.xLeft = X_INITIAL;
    this.xRight = X_INITIAL + WIDTH_INITIAL;
    this.yTop = Y_INITIAL;
    this.yBottom = Y_INITIAL + HEIGHT_INITIAL;
  }

  // If next level, change the rate of invader projectiles
  public void nextLevel() {
    reset();
    if (this.projectileRate != 60) {
      this.projectileRate -= 60;
    }
    this.projectileTimer = 0;
  }

  // If end game, reset the rate of invader projectiles
  public void endGame() {
    reset();
    this.projectileRate = 300;
  }

  // Check if entire swarm had been killed
  public boolean isDead() {return numOfInvaders() == 0;}

  // Function that randomly shoots a projectile
  public void shootProjectile(CurrentProjectiles projectiles) {
    List<Integer> temp = new ArrayList<Integer>();

    // Check which invaders are still alive
    for (int i = 0; i < 40; i++) {
      if (this.invaders.get(i).isAlive()) {
        temp.add(i);
      }
    }

    // Randomly choose from invaders that are alive
    Random rand = new Random();
    int randInt = temp.get(rand.nextInt(temp.size()));
    int projectileX = this.invaders.get(randInt).getX() + this.assetWidth / 2;
    int projectileY = this.invaders.get(randInt).getY() + this.assetHeight;

    // Check what type of invader has been chosen - and shoot appropriate projectile
    if (this.invaders.get(randInt) instanceof PowerInvader) {
      projectiles.addProjectile(projectileX, projectileY, false, true);
    } else {
      projectiles.addProjectile(projectileX, projectileY, false, false);
    }
  }
}
