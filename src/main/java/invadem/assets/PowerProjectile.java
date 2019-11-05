package invadem.assets;

import processing.core.PImage;

public class PowerProjectile extends Projectile {

  public PowerProjectile(PImage img, int x, int y) {
    super(img, x, y);
    this.damage = 3;
  }
}
