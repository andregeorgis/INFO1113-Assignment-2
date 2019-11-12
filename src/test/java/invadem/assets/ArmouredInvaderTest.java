package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ArmouredInvaderTest {
  private ArmouredInvader invaderOne;
  private ArmouredInvader invaderTwo;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);

    this.invaderOne = new ArmouredInvader(imgs, 0, 0);
    this.invaderTwo = new ArmouredInvader(imgs, 0, 0);
  }

  // Test construction
  @Test
  public void testArmouredInvaderConstruction() {
    assertNotNull(this.invaderOne);
    assertNotNull(this.invaderTwo);
  }

  // Test that it actually starts with 3 health
  @Test
  public void testKillingArmouredInvader() {
    PImage img = new PImage();
    this.invaderOne.changeImage(img);
    img = this.invaderOne.getImage();
    int health = this.invaderOne.getHealth();
    assertEquals(health, 3);
    assertNotNull(img);

    this.invaderOne.checkHealth();
    img = this.invaderOne.getImage();
    assertTrue(this.invaderOne.isAlive());
    assertNotNull(img);

    this.invaderOne.setHealth(1);
    health = this.invaderOne.getHealth();
    assertEquals(health, 1);

    this.invaderOne.checkHealth();
    img = this.invaderOne.getImage();
    assertTrue(this.invaderOne.isAlive());
    assertNotNull(img);

    this.invaderOne.setHealth(0);
    health = this.invaderOne.getHealth();
    assertEquals(health, 0);

    this.invaderOne.checkHealth();
    img = this.invaderOne.getImage();
    assertFalse(this.invaderOne.isAlive());
    assertNull(img);
  }

  // Test that it resets back to 3 health
  @Test
  public void testArmouredInvaderReset() {
    int health = this.invaderTwo.getHealth();
    assertEquals(health, 3);

    this.invaderTwo.setHealth(-1);
    health = this.invaderTwo.getHealth();
    assertEquals(health, -1);

    this.invaderTwo.reset(0, 0);
    health = this.invaderTwo.getHealth();
    assertEquals(health, 3);
  }
}
