package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

public class TeslaSpike extends Projectile {
  public static final int WIDTH = 14;
  public static final int HEIGHT = 20;
  public static final int X_VELOCITY = 0;
  public static final int Y_VELOCITY = 1;
  public static final int HEALTH = 3;

  public TeslaSpike(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT);
    this.yVelocity = 1;
  }

  public void draw(PApplet app) {
    if (!this.hit) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
  }
}
