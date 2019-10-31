package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;

public class Projectile extends MovableAsset {
  public static final int WIDTH = 1;
  public static final int HEIGHT = 3;
  public static final int X_VELOCITY_INITIAL = 0;
  public static final int Y_VELOCITY_INITIAL = -1;
  public static final int HEALTH_INITIAL = 1;

  public Projectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
  }

  public void draw(PApplet app) {
    tick();
    app.image(this.img, this.x, this.y, this.width, this.height);
  }

  public void tick() {
    changeX();
    changeY();
  }
}
