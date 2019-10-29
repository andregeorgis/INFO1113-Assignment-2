package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.Movable;

public class Tank {
  private int x;
  private int y;
  private int width;
  private int height;
  private int xVelocity;
  private int yVelocity;
  private int health;

  private PImage img;

  public final int X_BOUND_LEFT = 180;
  public final int X_BOUND_RIGHT = 460;

  public Tank(PImage img) {
    this.img = img;
    this.x = 309;
    this.y = 456;
    this.width = 22;
    this.height = 14;
    this.xVelocity = 1;
    this.yVelocity = 0;
    this.health = 3;
  }

  public void draw(PApplet app, boolean left, boolean right) {
    tick(left, right);
    checkBounds();
    app.image(img, x, y, width, height);
  }

  public void tick(boolean left, boolean right) {
    if (left && right) {
      this.xVelocity = 0;
    } else if (right) {
      this.xVelocity = 1;
    } else if (left) {
      this.xVelocity = -1;
    } else {
      this.xVelocity = 0;
    }
    this.x += this.xVelocity;
  }

  public void checkBounds() {
    if (this.x < X_BOUND_LEFT) {
      this.x = X_BOUND_LEFT;
    }

    if (this.x > X_BOUND_RIGHT) {
      this.x = X_BOUND_RIGHT;
    }
  }
}
