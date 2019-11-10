package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;

public class Tank extends MovableAsset {
  public static final int WIDTH = 22;
  public static final int HEIGHT = 14;
  public static final int X_INITIAL = 320 - WIDTH/2;
  public static final int Y_INITIAL = 480 - HEIGHT - 10;
  public static final int X_VELOCITY_INITIAL = 0;
  public static final int Y_VELOCITY_INITIAL = 0;
  public static final int X_BOUND_LEFT = 180;
  public static final int X_BOUND_RIGHT = 460 - WIDTH;
  public static final int HEALTH_INITIAL = 3;

  private boolean left;
  private boolean right;

  public Tank(PImage img) {
    super(img, X_INITIAL, Y_INITIAL, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.left = false;
    this.right = false;
  }

  public void draw(PApplet app) {
    checkBounds();
    app.image(this.img, this.x, this.y, this.width, this.height);
    tick();
  }

  public void tick() {
    if (this.left && this.right) {
      setXVelocity(0);
    } else if (this.right) {
      setXVelocity(1);
    } else if (this.left) {
      setXVelocity(-1);
    } else {
      setXVelocity(0);
    }
    changeX();
  }

  public void checkBounds() {
    if (this.x < X_BOUND_LEFT) {
      this.x = X_BOUND_LEFT;
    }

    if (this.x > X_BOUND_RIGHT) {
      this.x = X_BOUND_RIGHT;
    }
  }

  public boolean isMovingLeft() {return this.left;}

  public boolean isMovingRight() {return this.right;}

  public void setLeft(boolean state) {this.left = state;}

  public void setRight(boolean state) {this.right = state;}

  public void reset() {
    this.x = X_INITIAL;
    this.y = Y_INITIAL;
    this.health = HEALTH_INITIAL;
    this.xVelocity = X_VELOCITY_INITIAL;
    this.yVelocity = Y_VELOCITY_INITIAL;
    this.left = false;
    this.right = false;
  }

  public boolean isDead() {return this.health <= 0;}

  // Extension

  public void konamiReset() {changeImage(0);}
}
