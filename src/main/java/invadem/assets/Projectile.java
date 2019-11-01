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

  private boolean hit;

  public Projectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.hit = false;
  }

  public void draw(PApplet app) {
    if (!this.hit) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
    tick();
  }

  public void tick() {
    changeX();
    changeY();
  }

  public boolean checkCollisionWithInvader(Invader invader) {
    if (this.x < (invader.getX() + invader.getWidth()) &&
        (this.x + this.width) > invader.getX() &&
        this.y < (invader.getY() + invader.getHeight()) &&
        (this.y + this.height) > invader.getY() &&
        !this.hit) {
      this.hit = true;
      changeImage(null);
      return true;
    }

    return false;
  }
}
