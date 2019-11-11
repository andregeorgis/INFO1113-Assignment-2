package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

public class ZurkonProjectile extends Projectile {

  public final static int WIDTH = 4;
  public final static int HEIGHT = 6;

  public ZurkonProjectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT);
    this.damage = 3;
  }
}
