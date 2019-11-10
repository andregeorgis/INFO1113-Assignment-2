package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;
import invadem.DrawableAsset;

public class Projectile extends MovableAsset {
  public static final int WIDTH = 1;
  public static final int HEIGHT = 3;
  public static final int X_VELOCITY_INITIAL = 0;
  public static final int Y_VELOCITY_INITIAL = -1;
  public static final int HEALTH_INITIAL = 1;

  protected boolean hit;
  protected int damage;

  public Projectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.hit = false;
    this.damage = 1;
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

  public boolean isProjectileOutside() {
    return getY() < -10 || getY() > 490;
  }

  public boolean isDud() {return this.hit;}

  public int getDamage() {return this.damage;}

  public boolean checkCollisionWithAsset(DrawableAsset asset) {
    if (this.x < (asset.getX() + asset.getWidth()) &&
        (this.x + this.width) > asset.getX() &&
        this.y < (asset.getY() + asset.getHeight()) &&
        (this.y + this.height) > asset.getY() &&
        !this.hit) {
      this.hit = true;
      changeImage(null);
      asset.loseHealth(this.damage);
      return true;
    }

    return false;
  }
}
