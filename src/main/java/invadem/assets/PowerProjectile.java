package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

public class PowerProjectile extends Projectile {

  public final static int WIDTH = 2;
  public final static int HEIGHT = 5;

  public PowerProjectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT);
    this.damage = 3;
  }
}
