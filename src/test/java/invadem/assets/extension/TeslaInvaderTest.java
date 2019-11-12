package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TeslaInvaderTest {
  private TeslaInvader invaderOne;
  private TeslaInvader invaderTwo;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);

    this.invaderOne = new TeslaInvader(imgs, 0, 0);
    this.invaderTwo = new TeslaInvader(imgs, 0, 0);
  }

  @Test
  public void testTeslaInvaderConstruction() {
    assertNotNull(this.invaderOne);
  }

  @Test
  public void testElectrify() {
    assertFalse(this.invaderOne.isElectrified());
    this.invaderOne.electrify();
    assertTrue(this.invaderOne.isElectrified());
  }

  @Test
  public void testTeslaInvaderReset() {
    int health = this.invaderTwo.getHealth();
    assertEquals(health, 5);
    assertFalse(this.invaderTwo.isElectrified());

    this.invaderTwo.setHealth(0);
    this.invaderTwo.electrify();
    health = this.invaderTwo.getHealth();
    assertEquals(health, 0);
    assertTrue(this.invaderTwo.isElectrified());

    this.invaderTwo.reset(0, 0);

    health = this.invaderTwo.getHealth();
    assertEquals(health, 5);
    assertFalse(this.invaderTwo.isElectrified());
  }

  @Test
  public void testTelsaInvaderCheckState() {
    TeslaInvader invader;
    PImage imageOne = new PImage();
    PImage imageTwo = new PImage();
    PImage imageThree = new PImage();
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(imageOne);
    imgs.add(imageTwo);
    imgs.add(imageThree);
    invader = new TeslaInvader(imgs, 0, 0);

    PImage currentImage = invader.getImage();
    assertEquals(currentImage, imageOne);

    invader.checkState();
    currentImage = invader.getImage();
    assertEquals(currentImage, imageOne);

    invader.electrify();
    invader.checkState();
    currentImage = invader.getImage();
    assertEquals(currentImage, imageThree);
    assertTrue(invader.isElectrified());

    for (int i = 0; i < 20; i++) {
      invader.checkState();
    }

    assertFalse(invader.isElectrified());
  }
}
