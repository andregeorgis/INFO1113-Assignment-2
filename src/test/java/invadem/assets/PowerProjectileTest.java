package invadem.assets;

import invadem.DrawableAsset;

import processing.core.PApplet;
import processing.core.PImage;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PowerProjectileTest {
  private PowerProjectile projectileOne;

  @Before
  public void setup() {
    this.projectileOne = new PowerProjectile(null, 0, 0);
  }

  @Test
  public void testPowerProjectileConstruction() {
    assertNotNull(this.projectileOne);
  }

  @Test
  public void testDamageDone() {
    DrawableAsset asset = new DrawableAsset((PImage)null, 1, 2, 3, 4, 5) {
      public void draw(PApplet app) {;}
    };

    int health = asset.getHealth();
    assertEquals(health, 5);

    this.projectileOne.checkCollisionWithAsset(asset);

    health = asset.getHealth();
    assertEquals(health, 2);
  }
}
