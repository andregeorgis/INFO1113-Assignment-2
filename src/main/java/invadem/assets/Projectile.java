/*
  Responsible for defining the behaviour of a Projectile.
*/

package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;
import invadem.DrawableAsset;

public class Projectile extends MovableAsset {
  // Constants
  public static final int WIDTH = 1;
  public static final int HEIGHT = 3;
  public static final int X_VELOCITY_INITIAL = 0;
  public static final int Y_VELOCITY_INITIAL = -1;
  public static final int HEALTH_INITIAL = 1;

  // Check if projectile has collided
  protected boolean hit;
  // Store damage projectile should deal
  protected int damage;

  public Projectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.hit = false;
    this.damage = 1;
  }

  // Extra construction created for sub-classes of Projectile that have a different
  // height and width.
  public Projectile(PImage img, int x, int y, int width, int height) {
    super(img, x, y, width, height, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.hit = false;
    this.damage = 1;
  }

  // Only draw if the projectile has not collided into anything yet
  public void draw(PApplet app) {
    if (!this.hit) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
    tick();
  }

  // Tick always - even if projectile has collided
  public void tick() {
    changeX();
    changeY();
  }

  // Check if projectile is outside the screen
  public boolean isProjectileOutside() {
    return getY() < -10 || getY() > 490;
  }

  // Check if projectile has collided
  public boolean isDud() {return this.hit;}

  // Confirm that projectile has collided
  public void hit() {this.hit = true;}

  // Get damage
  public int getDamage() {return this.damage;}

  // Check collision with another object in the game
  public boolean checkCollisionWithAsset(DrawableAsset asset) {
    // If projectile collides, the projectile is always instantly destroyed and
    // the appropriate damage is dealt to the asset. We set the image of the
    // asset to null (temporarily disable it until it is removed when off the
    // screen)
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
