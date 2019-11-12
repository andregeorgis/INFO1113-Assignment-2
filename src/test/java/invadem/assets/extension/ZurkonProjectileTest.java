package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ZurkonProjectileTest {
  private ZurkonProjectile projectile;

  @Before
  public void setup() {
    this.projectile = new ZurkonProjectile(null, 0, 0);
  }

  @Test
  public void testZurkonProjectileConstruction() {
    assertNotNull(this.projectile);
    assertEquals(this.projectile.getDamage(), 3);
  }
}
