package invadem.assets;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class InvaderSwarm {

  private List<Invader> invaders;
  private int xLeft;
  private int xRight;
  private int yTop;
  private int yBottom;
  private int leftCol;
  private int rightCol;
  private int topRow;
  private int bottomRow;

  public static final int X_INITIAL = 171;
  public static final int Y_INITIAL = 20;
  public static final int WIDTH = 268;
  public static final int HEIGHT = 100;

  public InvaderSwarm(PImage img) {
    this.invaders = new ArrayList<Invader>();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        this.invaders.add(new Invader(img, X_INITIAL + j * 28, Y_INITIAL + i * 28));
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

  public void killInvader(Invader invader) {
    invader.kill();
    checkBoundaries();
  }

  public void checkBoundaries() {
    int i;

    // Check leftRow
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.leftCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.leftCol++;
      this.xLeft += 28;
    }


    // Check rightRow
    for (i = 0; i < 4; i++) {
      if (this.invaders.get(this.rightCol + i * 10).isAlive()) {
        break;
      }
    }

    if (i == 4) {
      this.rightCol--;
      this.xRight -= 28;
    }


    // Check topRow
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.topRow * 10 + i).isAlive()) {
        break;
      }
    }

    if (i == 10) {
      this.topRow++;
      this.yTop += 28;
    }


    // Check bottomRow
    for (i = 0; i < 10; i++) {
      if (this.invaders.get(this.bottomRow * 10 + i).isAlive()) {
        break;
      }
    }

    if (i == 10) {
      this.bottomRow--;
      this.yBottom -= 28;
    }
  }

  public void checkCollisionwithProjectile(Projectile projectile) {
    if (!(projectile.getY() > this.yBottom || (projectile.getY() + projectile.getHeight() < this.yTop))) {
      for (Invader invader : this.invaders) {
        if (invader.isAlive() && projectile.checkCollisionWithInvader(invader)) {
          killInvader(invader);
        }
      }
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
}
