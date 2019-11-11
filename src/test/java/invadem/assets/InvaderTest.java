package invadem.assets;

import invadem.assets.Tank;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class InvaderTest {
  private Invader invaderOne;
  private Invader invaderTwo;
  private Invader invaderThree;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);

    this.invaderOne = new Invader(imgs, 0, 0);
    this.invaderTwo = new Invader(imgs, 30, 30);
    this.invaderThree = new Invader(imgs, 0, 0);
  }

  @Test
  public void testInvaderConstruction() {
    assertNotNull(this.invaderOne);
    assertNotNull(this.invaderTwo);
    assertNotNull(this.invaderThree);
  }

  @Test
  public void testKillingInvader() {
    PImage img = new PImage();
    this.invaderOne.changeImage(img);
    img = this.invaderOne.getImage();
    int health = this.invaderOne.getHealth();
    assertEquals(health, 1);
    assertNotNull(img);

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

  @Test
  public void checkInvaderTickAndMovingStates() {
    int x = this.invaderTwo.getX();
    int y = this.invaderTwo.getY();
    int xVelocity = this.invaderTwo.getXVelocity();
    int yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 30);
    assertEquals(y, 30);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 31);
    assertEquals(y, 30);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 31);
    assertEquals(y, 30);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    for (int i = 0; i < 58; i++) {
      this.invaderTwo.tick();
    }

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 30);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 31);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 31);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    for (int i = 0; i < 14; i++) {
      this.invaderTwo.tick();
    }

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 38);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 59);
    assertEquals(y, 38);
    assertEquals(xVelocity, -1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 59);
    assertEquals(y, 38);
    assertEquals(xVelocity, -1);
    assertEquals(yVelocity, 0);

    for (int i = 0; i < 58; i++) {
      this.invaderTwo.tick();
    }

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 30);
    assertEquals(y, 38);
    assertEquals(xVelocity, -1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 30);
    assertEquals(y, 39);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 30);
    assertEquals(y, 39);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    for (int i = 0; i < 14; i++) {
      this.invaderTwo.tick();
    }

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertFalse(this.invaderTwo.isMovingRight());
    assertTrue(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 30);
    assertEquals(y, 46);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 31);
    assertEquals(y, 46);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 31);
    assertEquals(y, 46);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    for (int i = 0; i < 58; i++) {
      this.invaderTwo.tick();
    }

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertTrue(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertFalse(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 46);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.invaderTwo.tick();

    x = this.invaderTwo.getX();
    y = this.invaderTwo.getY();
    xVelocity = this.invaderTwo.getXVelocity();
    yVelocity = this.invaderTwo.getYVelocity();
    assertFalse(this.invaderTwo.isMoving());
    assertTrue(this.invaderTwo.isMovingRight());
    assertFalse(this.invaderTwo.isMovingLeft());
    assertTrue(this.invaderTwo.isMovingDown());
    assertEquals(x, 60);
    assertEquals(y, 47);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 1);
  }
  
}
