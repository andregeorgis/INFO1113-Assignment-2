package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BladeProjectileTest {
  private BladeProjectile projectile;
  private PImage imgOne;
  private PImage imgTwo;

  @Before
  public void setup() {
    this.imgOne = new PImage();
    this.imgTwo = new PImage();
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(imgOne);
    imgs.add(imgTwo);

    this.projectile = new BladeProjectile(imgs, 10, 10);
  }

  // Test construction
  @Test
  public void testBladeProjectileConstruction() {
    assertNotNull(this.projectile);
  }

  // Test motion is actually in a sine wave
  @Test
  public void testBladeProjectileTick() {
    PImage currentImage = this.projectile.getImage();
    int x = this.projectile.getX();
    assertEquals(currentImage, this.imgOne);
    assertEquals(x, 3);

    this.projectile.tick();
    x = this.projectile.getX();
    currentImage = this.projectile.getImage();
    assertEquals(currentImage, this.imgTwo);
    assertEquals(x, 3);

    this.projectile.tick();
    this.projectile.tick();
    this.projectile.tick();
    this.projectile.tick();
    this.projectile.tick();
    x = this.projectile.getX();
    currentImage = this.projectile.getImage();
    assertEquals(currentImage, this.imgOne);
    assertEquals(x, 4);

    this.projectile.tick();
    this.projectile.tick();
    x = this.projectile.getX();
    currentImage = this.projectile.getImage();
    assertEquals(currentImage, this.imgOne);
    assertEquals(x, 5);

    this.projectile.tick();
    this.projectile.tick();
    x = this.projectile.getX();
    currentImage = this.projectile.getImage();
    assertEquals(currentImage, this.imgOne);
    assertEquals(x, 6);

    for (int i = 0; i < 53; i++) {
      this.projectile.tick();
    }

    x = this.projectile.getX();
    currentImage = this.projectile.getImage();
    assertEquals(currentImage, this.imgTwo);
    assertEquals(x, 3);

  }
}
