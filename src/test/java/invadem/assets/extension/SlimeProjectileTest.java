package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class SlimeProjectileTest {
  private SlimeProjectile projectile;

  @Before
  public void setup() {
    this.projectile = new SlimeProjectile(null, 0, 0);
  }

  // Test construction and damage
  @Test
  public void testSlimeProjectileConstruction() {
    assertNotNull(this.projectile);
    assertEquals(this.projectile.getDamage(), 3);
  }
}
