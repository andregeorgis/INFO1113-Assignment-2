/*
  Responsible for distinguishing between BladeProjectiles and Projectiles.
*/

package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

import java.lang.Math;
import java.util.List;

public class BladeProjectile extends Projectile {
  // Constants
  public final static int WIDTH = 5;
  public final static int HEIGHT = 5;

  // For animating - even tho its pretty much impossible to notice :P
  private List<PImage> allImgs;
  // For moving in a smooth sine wave
  private double xCount;
  private int xInitial;

  public BladeProjectile(List<PImage> imgs, int x, int y) {
    super(imgs.get(0), x, y, WIDTH, HEIGHT);
    this.x -= 7; // For sine wave motion
    this.xInitial = x;
    this.allImgs = imgs;
    this.width = WIDTH;
    this.height = HEIGHT;
    this.xCount = 0;
  }

  public void draw(PApplet app) {
    if (!this.hit) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
    tick();
  }

  // Moves up like regular projectile, but can now move horizontally too
  public void tick() {
    changeY();

    // The motion in x maps out a sine wave to get the "smooth wavy" projectile
    // path
    this.xCount = (this.xCount + 1) % 63;
    setX((int)(this.xInitial - 7 * Math.cos(this.xCount / 10)));

    // For animation :P 
    if (this.img == this.allImgs.get(0)) {
      changeImage(this.allImgs.get(1));
    } else if (this.img == this.allImgs.get(1)) {
      changeImage(this.allImgs.get(0));
    }
  }
}
