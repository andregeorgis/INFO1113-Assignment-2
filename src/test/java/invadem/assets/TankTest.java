package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TankTest {
  private Tank tank;

  @Before
  public void setup() {
    this.tank = new Tank(null);
  }

  @Test
  public void testTankConstruction() {
    Tank tank = new Tank(null);
    assertNotNull(tank);
  }

  @Test
  public void testTankMovingStates() {
    assertFalse(this.tank.isMovingLeft());
    assertFalse(this.tank.isMovingRight());
    this.tank.setLeft(true);
    assertTrue(this.tank.isMovingLeft());
    assertFalse(this.tank.isMovingRight());
    this.tank.setRight(true);
    assertTrue(this.tank.isMovingLeft());
    assertTrue(this.tank.isMovingRight());
    this.tank.setLeft(false);
    assertFalse(this.tank.isMovingLeft());
    assertTrue(this.tank.isMovingRight());
    this.tank.setRight(false);
    assertFalse(this.tank.isMovingLeft());
    assertFalse(this.tank.isMovingRight());
  }

  @Test
  public void testTankHealthStates() {
    assertFalse(this.tank.isDead());
    this.tank.setHealth(0);
    assertTrue(this.tank.isDead());
    this.tank.setHealth(-2);
    assertTrue(this.tank.isDead());
    this.tank.setHealth(3);
    assertFalse(this.tank.isDead());
  }

  @Test
  public void testTankReset() {
    int x = this.tank.getX();
    int y = this.tank.getY();
    int xVelocity = this.tank.getXVelocity();
    int yVelocity = this.tank.getYVelocity();
    int health = this.tank.getHealth();
    boolean left = this.tank.isMovingLeft();
    boolean right = this.tank.isMovingRight();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);
    assertEquals(health, 3);
    assertFalse(left);
    assertFalse(right);

    this.tank.setX(20);
    this.tank.setY(240);
    this.tank.setXVelocity(14);
    this.tank.setYVelocity(-9);
    this.tank.setHealth(1);
    this.tank.setLeft(true);
    this.tank.setRight(false);

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();
    health = this.tank.getHealth();
    left = this.tank.isMovingLeft();
    right = this.tank.isMovingRight();

    assertEquals(x, 20);
    assertEquals(y, 240);
    assertEquals(xVelocity, 14);
    assertEquals(yVelocity, -9);
    assertEquals(health, 1);
    assertTrue(left);
    assertFalse(right);

    this.tank.reset();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();
    health = this.tank.getHealth();
    left = this.tank.isMovingLeft();
    right = this.tank.isMovingRight();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);
    assertEquals(health, 3);
    assertFalse(left);
    assertFalse(right);
  }

  @Test
  public void testTankTick() {
    int x = this.tank.getX();
    int y = this.tank.getY();
    int xVelocity = this.tank.getXVelocity();
    int yVelocity = this.tank.getYVelocity();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);

    this.tank.tick();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);

    this.tank.setLeft(true);
    this.tank.tick();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 308);
    assertEquals(y, 456);
    assertEquals(xVelocity, -1);
    assertEquals(yVelocity, 0);

    this.tank.setRight(true);
    this.tank.tick();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 308);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);

    this.tank.setLeft(false);
    this.tank.tick();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.tank.setRight(false);
    this.tank.tick();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);

    for (int i = 0; i < 21; i++) {
      if (i % 5 == 0) {
        this.tank.setRight(true);
        this.tank.setLeft(false);
        this.tank.tick();
      } else {
        this.tank.setRight(false);
        this.tank.setLeft(true);
        this.tank.tick();
      }
    }

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 298);
    assertEquals(y, 456);
    assertEquals(xVelocity, 1);
    assertEquals(yVelocity, 0);

    this.tank.reset();

    x = this.tank.getX();
    y = this.tank.getY();
    xVelocity = this.tank.getXVelocity();
    yVelocity = this.tank.getYVelocity();

    assertEquals(x, 309);
    assertEquals(y, 456);
    assertEquals(xVelocity, 0);
    assertEquals(yVelocity, 0);
  }

  @Test
  public void testTankCheckBounds() {
    int x = this.tank.getX();
    assertEquals(x, 309);

    this.tank.setX(20);
    x = this.tank.getX();
    assertEquals(x, 20);
    this.tank.checkBounds();
    x = this.tank.getX();
    assertEquals(x, 180);

    this.tank.setX(490);
    x = this.tank.getX();
    assertEquals(x, 490);
    this.tank.checkBounds();
    x = this.tank.getX();
    assertEquals(x, 438);

    this.tank.setX(309);
    x = this.tank.getX();
    assertEquals(x, 309);
    this.tank.checkBounds();
    x = this.tank.getX();
    assertEquals(x, 309);
  }

}
