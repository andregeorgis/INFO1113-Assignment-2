package invadem.assets;

import invadem.AssetGroup;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class InvaderSwarm extends AssetGroup {

  private List<Invader> invaders;

  public static final int X_INITIAL = 171;
  public static final int Y_INITIAL = 20;
  public static final int WIDTH = 268;
  public static final int HEIGHT = 100;
  public static final int GAP = 12;

  public InvaderSwarm(List<PImage> imgs) {
    super(X_INITIAL, Y_INITIAL, WIDTH, HEIGHT, 4, 10, Invader.WIDTH, Invader.HEIGHT);
    this.invaders = new ArrayList<Invader>();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        this.invaders.add(new Invader(imgs, X_INITIAL + j * (GAP + Invader.WIDTH), Y_INITIAL + i * (GAP + Invader.HEIGHT)));
      }
    }
  }

  public void draw(PApplet app) {
    int changeX = 0;
    int changeY = 0;
    boolean changeChecked = false;

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

  public int numOfInvaders() {
    int sum = 0;
    for (Invader invader : this.invaders) {
      if (invader.isAlive()) {
        sum++;
      }
    }
    return sum;
  }

  public void checkBoundaries() {
    int i;

    // Check leftCol
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.leftCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.leftCol++;
      this.xLeft += this.assetWidth + GAP;
    }


    // Check rightCol
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.rightCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.rightCol--;
      this.xRight -= this.assetWidth + GAP;
    }


    // Check topRow
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.topRow * 10 + i).isAlive()) {
        break;
      }
    }

    if (i == 10) {
      this.topRow++;
      this.yTop += this.assetHeight + GAP;
    }


    // Check bottomRow
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.bottomRow * 10 + i).isAlive()) {
        break;
      }
    }

    System.out.println(i);

    if (i == 10) {
      this.bottomRow--;
      this.yBottom -= this.assetHeight + GAP;
    }
  }

  public void checkCollisionwithProjectile(Projectile projectile) {
    boolean invaderKilled = false;

    if (!(projectile.getY() + projectile.getHeight() > this.yBottom || (projectile.getY() < this.yTop)) &&
        !(projectile.getX() + projectile.getWidth() > this.xRight || (projectile.getX() < this.xLeft))) {
      for (Invader invader : this.invaders) {
        if (invader.isAlive() && projectile.checkCollisionWithAsset(invader)) {
          invader.checkHealth();
          invaderKilled = true;
        }
      }
    }

    if (invaderKilled) {
      checkBoundaries();
    }
  }

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
    this.xRight = X_INITIAL + WIDTH;
    this.yTop = Y_INITIAL;
    this.yBottom = Y_INITIAL + HEIGHT;
  }

  public boolean isDead() {return numOfInvaders() == 0;}

  public int getBottom() {return this.yBottom;}
}
