package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

public class SlimeProjectile extends Projectile {

  public final static int WIDTH = 5;
  public final static int HEIGHT = 5;

  public SlimeProjectile(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT);
    this.damage = 3;
  }
}
