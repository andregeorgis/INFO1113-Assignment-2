package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TeslaCoilTest {
  private TeslaCoil coilOne;
  private TeslaCoil coilTwo;
  private TeslaCoil coilThree;
  private TeslaCoil coilFour;
  private PImage spikeImage;
  private PImage arcImage;

  @Before
  public void setup() {
    this.spikeImage = new PImage();
    this.arcImage = new PImage();

    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(this.spikeImage);
    imgs.add(this.arcImage);

    this.coilOne = new TeslaCoil(imgs, 0, 0);
    this.coilTwo = new TeslaCoil(imgs, 0, 0);
    this.coilThree = new TeslaCoil(imgs, 0, 0);
    this.coilFour = new TeslaCoil(imgs, 0, 600);
  }

  @Test
  public void testTeslaCoilConstruction() {
    assertNotNull(this.coilOne);
    assertNotNull(this.coilTwo);
    assertNotNull(this.coilThree);
    assertNotNull(this.coilFour);
  }

  @Test
  public void testCoilZappingRate() {
    assertFalse(this.coilOne.isZapping());

    for (int i = 0; i < 90; i++) {
      this.coilOne.tick();
    }

    assertTrue(this.coilOne.isZapping());

    for (int i = 0; i < 90; i++) {
      this.coilOne.tick();
    }

    assertFalse(this.coilOne.isZapping());
  }

  @Test
  public void testCanZapLeftAndRightAndCollisionsWithSpike() {
    Projectile projectile;

    assertTrue(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());
    this.coilTwo.setY(0);

    // Miss from all sides
    projectile = new Projectile(null, -20, 0);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertTrue(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());

    projectile = new Projectile(null, 300, 0);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertTrue(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());

    projectile = new Projectile(null, 0, -110);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertTrue(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());

    projectile = new Projectile(null, 0, 110);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertTrue(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());


    // Collide with left spike
    projectile = new Projectile(null, 0, 5);
    assertTrue(this.coilTwo.checkCollisionWithAsset(projectile));
    assertFalse(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());

    projectile = new Projectile(null, 0, 5);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertFalse(this.coilTwo.canZapLeft());
    assertTrue(this.coilTwo.canZapRight());

    // Collide with right spike
    projectile = new Projectile(null, 130, 5);
    assertTrue(this.coilTwo.checkCollisionWithAsset(projectile));
    assertFalse(this.coilTwo.canZapLeft());
    assertFalse(this.coilTwo.canZapRight());

    projectile = new Projectile(null, 130, 5);
    assertFalse(this.coilTwo.checkCollisionWithAsset(projectile));
    assertFalse(this.coilTwo.canZapLeft());
    assertFalse(this.coilTwo.canZapRight());

    assertTrue(this.coilThree.canZapLeft());
    assertTrue(this.coilThree.canZapRight());

    // Collide with middle spike
    projectile = new Projectile(null, 65, 5);
    assertTrue(this.coilThree.checkCollisionWithAsset(projectile));
    assertFalse(this.coilThree.canZapLeft());
    assertFalse(this.coilThree.canZapRight());

    projectile = new Projectile(null, 65, 5);
    assertFalse(this.coilThree.checkCollisionWithAsset(projectile));
    assertFalse(this.coilThree.canZapLeft());
    assertFalse(this.coilThree.canZapRight());
  }

  @Test
  public void testCollisionsWithArc() {
    Projectile projectile;

    // Hit no zap left
    projectile = new Projectile(null, 30, 605);
    assertFalse(this.coilFour.isZapping());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));

    for (int i = 0; i < 90; i++) {
      this.coilFour.tick();
    }

    // Hit while zap left
    projectile = new Projectile(null, 30, 515);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapLeft());
    assertTrue(this.coilFour.checkCollisionWithAsset(projectile));

    // Miss while zap left
    projectile = new Projectile(null, -20, 515);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapLeft());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 300, 515);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapLeft());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 30, 400);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapLeft());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 30, 600);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapLeft());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));

    for (int i = 0; i < 90; i++) {
      this.coilFour.tick();
    }

    // Hit no zap right
    projectile = new Projectile(null, 90, 425);
    assertFalse(this.coilFour.isZapping());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));

    for (int i = 0; i < 90; i++) {
      this.coilFour.tick();
    }

    // Hit while zap right
    projectile = new Projectile(null, 90, 335);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapRight());
    assertTrue(this.coilFour.checkCollisionWithAsset(projectile));

    // Miss while zap right
    projectile = new Projectile(null, -20, 335);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapRight());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 300, 335);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapRight());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 90, 515);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapRight());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
    projectile = new Projectile(null, 90, 200);
    assertTrue(this.coilFour.isZapping() && this.coilFour.canZapRight());
    assertFalse(this.coilFour.checkCollisionWithAsset(projectile));
  }
}
