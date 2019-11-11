package invadem.assets;

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
  private Invader invaderFour;

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
  public void testInvaderTickAndMovingStates() {
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

  @Test
  public void testInvaderAnimating() {
    List<PImage> imgs = new ArrayList<PImage>();
    PImage imgOne = new PImage();
    PImage imgTwo = new PImage();
    imgs.add(imgOne);
    imgs.add(imgTwo);

    this.invaderFour = new Invader(imgs, 0, 0);

    PImage currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgOne);

    for (int i = 0; i < 60; i ++) {
      this.invaderFour.tick();
    }

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgOne);

    this.invaderFour.tick();

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgTwo);

    for (int i = 0; i < 15; i ++) {
      this.invaderFour.tick();
    }

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgTwo);

    this.invaderFour.tick();

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgOne);

    for (int i = 0; i < 59; i ++) {
      this.invaderFour.tick();
    }

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgOne);

    this.invaderFour.tick();

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgTwo);

    for (int i = 0; i < 15; i ++) {
      this.invaderFour.tick();
    }

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgTwo);

    this.invaderFour.tick();

    currentImage = this.invaderFour.getImage();
    assertEquals(currentImage, imgOne);
  }

  @Test
  public void testInvaderReset() {
    int x = this.invaderThree.getX();
    int y = this.invaderThree.getY();
    int xVelocity = this.invaderThree.getXVelocity();
    int yVelocity = this.invaderThree.getYVelocity();
    assertEquals(x, 0);
    assertEquals(y, 0);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);
    assertTrue(this.invaderThree.isMoving());
    assertTrue(this.invaderThree.isMovingRight());
    assertFalse(this.invaderThree.isMovingDown());
    assertFalse(this.invaderThree.isMovingLeft());

    for (int i = 0; i < 100; i++) {
      this.invaderThree.tick();
    }

    x = this.invaderThree.getX();
    y = this.invaderThree.getY();
    xVelocity = this.invaderThree.getXVelocity();
    yVelocity = this.invaderThree.getYVelocity();
    assertEquals(x, 18);
    assertEquals(y, 8);
    assertEquals(xVelocity, -1);
    assertEquals(yVelocity, 0);
    assertTrue(this.invaderThree.isMoving());
    assertFalse(this.invaderThree.isMovingRight());
    assertFalse(this.invaderThree.isMovingDown());
    assertTrue(this.invaderThree.isMovingLeft());

    this.invaderThree.reset(0, 0);

    x = this.invaderThree.getX();
    y = this.invaderThree.getY();
    xVelocity = this.invaderThree.getXVelocity();
    yVelocity = this.invaderThree.getYVelocity();
    assertEquals(x, 0);
    assertEquals(y, 0);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);
    assertTrue(this.invaderThree.isMoving());
    assertTrue(this.invaderThree.isMovingRight());
    assertFalse(this.invaderThree.isMovingDown());
    assertFalse(this.invaderThree.isMovingLeft());




    int health = this.invaderThree.getHealth();
    assertEquals(health, 1);
    assertTrue(this.invaderThree.isAlive());

    this.invaderThree.setHealth(0);
    this.invaderThree.checkHealth();
    health = this.invaderThree.getHealth();
    assertEquals(health, 0);
    assertFalse(this.invaderThree.isAlive());

    this.invaderThree.reset(0, 0);
    
    health = this.invaderThree.getHealth();
    assertEquals(health, 1);
    assertTrue(this.invaderThree.isAlive());
  }
}
