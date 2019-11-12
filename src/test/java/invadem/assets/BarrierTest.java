package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BarrierTest {
  Barrier barrierOne;
  Barrier barrierTwo;
  Barrier barrierThree;
  Barrier barrierFour;
  Barrier barrierFive;
  Barrier barrierSix;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);

    this.barrierOne = new Barrier(imgs, imgs, imgs, imgs, 10, 10);
    this.barrierTwo = new Barrier(imgs, imgs, imgs, imgs, 0, 0);
    this.barrierThree = new Barrier(imgs, imgs, imgs, imgs, 0, 0);
    this.barrierFour = new Barrier(imgs, imgs, imgs, imgs, 0, 0);
    this.barrierFive = new Barrier(imgs, imgs, imgs, imgs, 0, 0);
    this.barrierSix = new Barrier(imgs, imgs, imgs, imgs, 0, 0);
  }

  // Test construction
  @Test
  public void testBarrierConstruction() {
    assertNotNull(this.barrierOne);
    assertNotNull(this.barrierTwo);
    assertNotNull(this.barrierThree);
    assertNotNull(this.barrierFour);
    assertNotNull(this.barrierFive);
    assertNotNull(this.barrierSix);
  }

  // Test if correct number of components are generated
  @Test
  public void testNumberOfComponents() {
    int componentsInTopRow = this.barrierOne.getTopComponentRow().size();
    int componentsInMiddleRow = this.barrierOne.getMiddleComponentRow().size();
    int componentsInBottomRow = this.barrierOne.getBottomComponentRow().size();

    assertEquals(componentsInTopRow, 3);
    assertEquals(componentsInMiddleRow, 2);
    assertEquals(componentsInBottomRow, 2);
  }

  // Test colliding with projectiles
  @Test
  public void testBarrierCollisionWithProjectileAndIsBroken() {
    int healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    int healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    int healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    int healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    int healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    int healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    int healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);

    // Checking no collision first
    Projectile projectile = new Projectile(null, 20, 0);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile.setX(0);
    projectile.setY(20);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile.setX(0);
    projectile.setY(40);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile.setX(40);
    projectile.setY(0);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile.setX(10);
    projectile.setY(8);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 2);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    // Checking that the top row is always checked first
    projectile = new Projectile(null, 10, 16);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 1);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile = new Projectile(null, 10, 16);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    // Checking that if component is dead, it is ignored
    projectile = new Projectile(null, 10, 16);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    // Checking that all components can actually be collided with
    projectile = new Projectile(null, 10, 32);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile = new Projectile(null, 33, 25);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile = new Projectile(null, 25, 10);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 2);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile = new Projectile(null, 30, 10);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 2);
    assertEquals(healthThree, 2);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 3);
    assertFalse(barrierOne.isBroken());

    projectile = new Projectile(null, 30, 30);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 2);
    assertEquals(healthThree, 2);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 2);
    assertFalse(barrierOne.isBroken());

    // Checking that inside the gap no collision occurs
    projectile = new Projectile(null, 20, 20);

    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 2);
    assertEquals(healthThree, 2);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 2);
    assertFalse(barrierOne.isBroken());

    projectile.setY(28);
    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 2);
    assertEquals(healthThree, 2);
    assertEquals(healthFour, 2);
    assertEquals(healthFive, 2);
    assertEquals(healthSix, 2);
    assertEquals(healthSeven, 2);
    assertFalse(barrierOne.isBroken());

    // Destroying the rest of the barrier
    projectile = new Projectile(null, 30, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 20, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 20, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 10);
    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 0);
    assertEquals(healthThree, 0);
    assertEquals(healthFour, 0);
    assertEquals(healthFive, 0);
    assertEquals(healthSix, 0);
    assertEquals(healthSeven, 0);
    assertTrue(barrierOne.isBroken());

    // If another projectile is thrown after the barrier is broken, nothing is hit
    projectile = new Projectile(null, 30, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 30);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 12, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 20);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 20, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 20, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 10);
    barrierOne.checkCollisionWithProjectile(projectile);
    projectile = new Projectile(null, 30, 10);
    barrierOne.checkCollisionWithProjectile(projectile);

    healthOne = this.barrierOne.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierOne.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierOne.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierOne.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierOne.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierOne.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierOne.getBottomComponentRow().get(1).getHealth();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 0);
    assertEquals(healthThree, 0);
    assertEquals(healthFour, 0);
    assertEquals(healthFive, 0);
    assertEquals(healthSix, 0);
    assertEquals(healthSeven, 0);
    assertTrue(barrierOne.isBroken());
  }

  // Check if barrier changes boundaries correctly
  @Test
  public void testBarrierCheckBoundariesAndIsBroken() {
    int xLeft = this.barrierTwo.getXLeft();
    int xRight = this.barrierTwo.getXRight();
    int yTop = this.barrierTwo.getYTop();
    int yBottom = this.barrierTwo.getYBottom();
    int leftCol = this.barrierTwo.getLeftCol();
    int rightCol = this.barrierTwo.getRightCol();
    int topRow = this.barrierTwo.getTopRow();
    int bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    // Destroying nothing doesn't change boundaries
    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    // Almost destroying everything doesn't change boundaries
    this.barrierTwo.getTopComponentRow().get(0).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    // Destroying the middle of a row or column doesn't change the boundary
    this.barrierTwo.getTopComponentRow().get(0).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    // Destroying the barrier from the top down
    this.barrierTwo.getTopComponentRow().get(0).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierTwo.getTopComponentRow().get(0).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(0);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 2);

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 2);

    this.barrierTwo.getTopComponentRow().get(0).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(0);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(1);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(1);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 16);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 2);
    assertEquals(bottomRow, 2);

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 16);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 2);
    assertEquals(bottomRow, 2);

    this.barrierTwo.getTopComponentRow().get(0).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(1).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(2).setHealth(0);
    this.barrierTwo.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierTwo.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierTwo.getBottomComponentRow().get(0).setHealth(0);
    this.barrierTwo.getBottomComponentRow().get(1).setHealth(0);
    this.barrierTwo.getTopComponentRow().get(0).checkHealth();
    this.barrierTwo.getTopComponentRow().get(1).checkHealth();
    this.barrierTwo.getTopComponentRow().get(2).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(0).checkHealth();
    this.barrierTwo.getMiddleComponentRow().get(1).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(0).checkHealth();
    this.barrierTwo.getBottomComponentRow().get(1).checkHealth();

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 8);
    assertEquals(xRight, 16);
    assertEquals(yTop, 24);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 3);
    assertEquals(bottomRow, 1);

    this.barrierTwo.checkBoundaries();

    xLeft = this.barrierTwo.getXLeft();
    xRight = this.barrierTwo.getXRight();
    yTop = this.barrierTwo.getYTop();
    yBottom = this.barrierTwo.getYBottom();
    leftCol = this.barrierTwo.getLeftCol();
    rightCol = this.barrierTwo.getRightCol();
    topRow = this.barrierTwo.getTopRow();
    bottomRow = this.barrierTwo.getBottomRow();

    assertEquals(xLeft, 16);
    assertEquals(xRight, 8);
    assertEquals(yTop, 24);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 2);
    assertEquals(rightCol, 0);
    assertEquals(topRow, 3);
    assertEquals(bottomRow, 0);

    // Destroying the barrier from the bottom up
    this.barrierThree.getTopComponentRow().get(0).setHealth(1);
    this.barrierThree.getTopComponentRow().get(1).setHealth(1);
    this.barrierThree.getTopComponentRow().get(2).setHealth(1);
    this.barrierThree.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierThree.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierThree.getBottomComponentRow().get(0).setHealth(1);
    this.barrierThree.getBottomComponentRow().get(1).setHealth(1);
    this.barrierThree.getTopComponentRow().get(0).checkHealth();
    this.barrierThree.getTopComponentRow().get(1).checkHealth();
    this.barrierThree.getTopComponentRow().get(2).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(0).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(1).checkHealth();
    this.barrierThree.getBottomComponentRow().get(0).checkHealth();
    this.barrierThree.getBottomComponentRow().get(1).checkHealth();

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierThree.getTopComponentRow().get(0).setHealth(1);
    this.barrierThree.getTopComponentRow().get(1).setHealth(1);
    this.barrierThree.getTopComponentRow().get(2).setHealth(1);
    this.barrierThree.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierThree.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierThree.getBottomComponentRow().get(0).setHealth(0);
    this.barrierThree.getBottomComponentRow().get(1).setHealth(0);
    this.barrierThree.getTopComponentRow().get(0).checkHealth();
    this.barrierThree.getTopComponentRow().get(1).checkHealth();
    this.barrierThree.getTopComponentRow().get(2).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(0).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(1).checkHealth();
    this.barrierThree.getBottomComponentRow().get(0).checkHealth();
    this.barrierThree.getBottomComponentRow().get(1).checkHealth();

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 1);

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 1);

    this.barrierThree.getTopComponentRow().get(0).setHealth(1);
    this.barrierThree.getTopComponentRow().get(1).setHealth(1);
    this.barrierThree.getTopComponentRow().get(2).setHealth(1);
    this.barrierThree.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierThree.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierThree.getBottomComponentRow().get(0).setHealth(0);
    this.barrierThree.getBottomComponentRow().get(1).setHealth(0);
    this.barrierThree.getTopComponentRow().get(0).checkHealth();
    this.barrierThree.getTopComponentRow().get(1).checkHealth();
    this.barrierThree.getTopComponentRow().get(2).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(0).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(1).checkHealth();
    this.barrierThree.getBottomComponentRow().get(0).checkHealth();
    this.barrierThree.getBottomComponentRow().get(1).checkHealth();

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 0);

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 0);

    this.barrierThree.getTopComponentRow().get(0).setHealth(0);
    this.barrierThree.getTopComponentRow().get(1).setHealth(0);
    this.barrierThree.getTopComponentRow().get(2).setHealth(0);
    this.barrierThree.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierThree.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierThree.getBottomComponentRow().get(0).setHealth(0);
    this.barrierThree.getBottomComponentRow().get(1).setHealth(0);
    this.barrierThree.getTopComponentRow().get(0).checkHealth();
    this.barrierThree.getTopComponentRow().get(1).checkHealth();
    this.barrierThree.getTopComponentRow().get(2).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(0).checkHealth();
    this.barrierThree.getMiddleComponentRow().get(1).checkHealth();
    this.barrierThree.getBottomComponentRow().get(0).checkHealth();
    this.barrierThree.getBottomComponentRow().get(1).checkHealth();

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 8);
    assertEquals(xRight, 16);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 0);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, -1);

    this.barrierThree.checkBoundaries();

    xLeft = this.barrierThree.getXLeft();
    xRight = this.barrierThree.getXRight();
    yTop = this.barrierThree.getYTop();
    yBottom = this.barrierThree.getYBottom();
    leftCol = this.barrierThree.getLeftCol();
    rightCol = this.barrierThree.getRightCol();
    topRow = this.barrierThree.getTopRow();
    bottomRow = this.barrierThree.getBottomRow();

    assertEquals(xLeft, 16);
    assertEquals(xRight, 8);
    assertEquals(yTop, 16);
    assertEquals(yBottom, 0);
    assertEquals(leftCol, 2);
    assertEquals(rightCol, 0);
    assertEquals(topRow, 2);
    assertEquals(bottomRow, -1);

    // Destroying the barrier from the left to the right
    this.barrierFour.getTopComponentRow().get(0).setHealth(1);
    this.barrierFour.getTopComponentRow().get(1).setHealth(1);
    this.barrierFour.getTopComponentRow().get(2).setHealth(1);
    this.barrierFour.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierFour.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierFour.getBottomComponentRow().get(0).setHealth(1);
    this.barrierFour.getBottomComponentRow().get(1).setHealth(1);
    this.barrierFour.getTopComponentRow().get(0).checkHealth();
    this.barrierFour.getTopComponentRow().get(1).checkHealth();
    this.barrierFour.getTopComponentRow().get(2).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFour.getBottomComponentRow().get(0).checkHealth();
    this.barrierFour.getBottomComponentRow().get(1).checkHealth();

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.getTopComponentRow().get(0).setHealth(0);
    this.barrierFour.getTopComponentRow().get(1).setHealth(1);
    this.barrierFour.getTopComponentRow().get(2).setHealth(1);
    this.barrierFour.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierFour.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierFour.getBottomComponentRow().get(0).setHealth(0);
    this.barrierFour.getBottomComponentRow().get(1).setHealth(1);
    this.barrierFour.getTopComponentRow().get(0).checkHealth();
    this.barrierFour.getTopComponentRow().get(1).checkHealth();
    this.barrierFour.getTopComponentRow().get(2).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFour.getBottomComponentRow().get(0).checkHealth();
    this.barrierFour.getBottomComponentRow().get(1).checkHealth();

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 8);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 8);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.getTopComponentRow().get(0).setHealth(0);
    this.barrierFour.getTopComponentRow().get(1).setHealth(0);
    this.barrierFour.getTopComponentRow().get(2).setHealth(1);
    this.barrierFour.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierFour.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierFour.getBottomComponentRow().get(0).setHealth(0);
    this.barrierFour.getBottomComponentRow().get(1).setHealth(1);
    this.barrierFour.getTopComponentRow().get(0).checkHealth();
    this.barrierFour.getTopComponentRow().get(1).checkHealth();
    this.barrierFour.getTopComponentRow().get(2).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFour.getBottomComponentRow().get(0).checkHealth();
    this.barrierFour.getBottomComponentRow().get(1).checkHealth();

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 16);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 2);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 16);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 2);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFour.getTopComponentRow().get(0).setHealth(0);
    this.barrierFour.getTopComponentRow().get(1).setHealth(0);
    this.barrierFour.getTopComponentRow().get(2).setHealth(0);
    this.barrierFour.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierFour.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierFour.getBottomComponentRow().get(0).setHealth(0);
    this.barrierFour.getBottomComponentRow().get(1).setHealth(0);
    this.barrierFour.getTopComponentRow().get(0).checkHealth();
    this.barrierFour.getTopComponentRow().get(1).checkHealth();
    this.barrierFour.getTopComponentRow().get(2).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFour.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFour.getBottomComponentRow().get(0).checkHealth();
    this.barrierFour.getBottomComponentRow().get(1).checkHealth();

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 24);
    assertEquals(xRight, 16);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 3);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 1);

    this.barrierFour.checkBoundaries();

    xLeft = this.barrierFour.getXLeft();
    xRight = this.barrierFour.getXRight();
    yTop = this.barrierFour.getYTop();
    yBottom = this.barrierFour.getYBottom();
    leftCol = this.barrierFour.getLeftCol();
    rightCol = this.barrierFour.getRightCol();
    topRow = this.barrierFour.getTopRow();
    bottomRow = this.barrierFour.getBottomRow();

    assertEquals(xLeft, 24);
    assertEquals(xRight, 8);
    assertEquals(yTop, 16);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 3);
    assertEquals(rightCol, 0);
    assertEquals(topRow, 2);
    assertEquals(bottomRow, 0);

    // Destroying the barrier from the right to the left
    this.barrierFive.getTopComponentRow().get(0).setHealth(1);
    this.barrierFive.getTopComponentRow().get(1).setHealth(1);
    this.barrierFive.getTopComponentRow().get(2).setHealth(1);
    this.barrierFive.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierFive.getMiddleComponentRow().get(1).setHealth(1);
    this.barrierFive.getBottomComponentRow().get(0).setHealth(1);
    this.barrierFive.getBottomComponentRow().get(1).setHealth(1);
    this.barrierFive.getTopComponentRow().get(0).checkHealth();
    this.barrierFive.getTopComponentRow().get(1).checkHealth();
    this.barrierFive.getTopComponentRow().get(2).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFive.getBottomComponentRow().get(0).checkHealth();
    this.barrierFive.getBottomComponentRow().get(1).checkHealth();

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.getTopComponentRow().get(0).setHealth(1);
    this.barrierFive.getTopComponentRow().get(1).setHealth(1);
    this.barrierFive.getTopComponentRow().get(2).setHealth(0);
    this.barrierFive.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierFive.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierFive.getBottomComponentRow().get(0).setHealth(1);
    this.barrierFive.getBottomComponentRow().get(1).setHealth(0);
    this.barrierFive.getTopComponentRow().get(0).checkHealth();
    this.barrierFive.getTopComponentRow().get(1).checkHealth();
    this.barrierFive.getTopComponentRow().get(2).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFive.getBottomComponentRow().get(0).checkHealth();
    this.barrierFive.getBottomComponentRow().get(1).checkHealth();

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 16);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 16);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.getTopComponentRow().get(0).setHealth(1);
    this.barrierFive.getTopComponentRow().get(1).setHealth(0);
    this.barrierFive.getTopComponentRow().get(2).setHealth(0);
    this.barrierFive.getMiddleComponentRow().get(0).setHealth(1);
    this.barrierFive.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierFive.getBottomComponentRow().get(0).setHealth(1);
    this.barrierFive.getBottomComponentRow().get(1).setHealth(0);
    this.barrierFive.getTopComponentRow().get(0).checkHealth();
    this.barrierFive.getTopComponentRow().get(1).checkHealth();
    this.barrierFive.getTopComponentRow().get(2).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFive.getBottomComponentRow().get(0).checkHealth();
    this.barrierFive.getBottomComponentRow().get(1).checkHealth();

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 8);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 0);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 0);
    assertEquals(xRight, 8);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 0);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);

    this.barrierFive.getTopComponentRow().get(0).setHealth(0);
    this.barrierFive.getTopComponentRow().get(1).setHealth(0);
    this.barrierFive.getTopComponentRow().get(2).setHealth(0);
    this.barrierFive.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierFive.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierFive.getBottomComponentRow().get(0).setHealth(0);
    this.barrierFive.getBottomComponentRow().get(1).setHealth(0);
    this.barrierFive.getTopComponentRow().get(0).checkHealth();
    this.barrierFive.getTopComponentRow().get(1).checkHealth();
    this.barrierFive.getTopComponentRow().get(2).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(0).checkHealth();
    this.barrierFive.getMiddleComponentRow().get(1).checkHealth();
    this.barrierFive.getBottomComponentRow().get(0).checkHealth();
    this.barrierFive.getBottomComponentRow().get(1).checkHealth();

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 8);
    assertEquals(xRight, 0);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, -1);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 1);

    this.barrierFive.checkBoundaries();

    xLeft = this.barrierFive.getXLeft();
    xRight = this.barrierFive.getXRight();
    yTop = this.barrierFive.getYTop();
    yBottom = this.barrierFive.getYBottom();
    leftCol = this.barrierFive.getLeftCol();
    rightCol = this.barrierFive.getRightCol();
    topRow = this.barrierFive.getTopRow();
    bottomRow = this.barrierFive.getBottomRow();

    assertEquals(xLeft, 16);
    assertEquals(xRight, 0);
    assertEquals(yTop, 16);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 2);
    assertEquals(rightCol, -1);
    assertEquals(topRow, 2);
    assertEquals(bottomRow, 0);
  }

  // Testing that the barrier resets appropriately
  @Test
  public void testBarrierReset() {
    int healthOne = this.barrierSix.getTopComponentRow().get(0).getHealth();
    int healthTwo = this.barrierSix.getTopComponentRow().get(1).getHealth();
    int healthThree = this.barrierSix.getTopComponentRow().get(2).getHealth();
    int healthFour = this.barrierSix.getMiddleComponentRow().get(0).getHealth();
    int healthFive = this.barrierSix.getMiddleComponentRow().get(1).getHealth();
    int healthSix = this.barrierSix.getBottomComponentRow().get(0).getHealth();
    int healthSeven = this.barrierSix.getBottomComponentRow().get(1).getHealth();
    int xLeft = this.barrierSix.getXLeft();
    int xRight = this.barrierSix.getXRight();
    int yTop = this.barrierSix.getYTop();
    int yBottom = this.barrierSix.getYBottom();
    int leftCol = this.barrierSix.getLeftCol();
    int rightCol = this.barrierSix.getRightCol();
    int topRow = this.barrierSix.getTopRow();
    int bottomRow = this.barrierSix.getBottomRow();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);
    assertFalse(this.barrierSix.isBroken());

    // Destroying Barrier
    this.barrierSix.getTopComponentRow().get(0).setHealth(0);
    this.barrierSix.getTopComponentRow().get(1).setHealth(0);
    this.barrierSix.getTopComponentRow().get(2).setHealth(0);
    this.barrierSix.getMiddleComponentRow().get(0).setHealth(0);
    this.barrierSix.getMiddleComponentRow().get(1).setHealth(0);
    this.barrierSix.getBottomComponentRow().get(0).setHealth(0);
    this.barrierSix.getBottomComponentRow().get(1).setHealth(0);
    this.barrierSix.getTopComponentRow().get(0).checkHealth();
    this.barrierSix.getTopComponentRow().get(1).checkHealth();
    this.barrierSix.getTopComponentRow().get(2).checkHealth();
    this.barrierSix.getMiddleComponentRow().get(0).checkHealth();
    this.barrierSix.getMiddleComponentRow().get(1).checkHealth();
    this.barrierSix.getBottomComponentRow().get(0).checkHealth();
    this.barrierSix.getBottomComponentRow().get(1).checkHealth();
    healthOne = this.barrierSix.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierSix.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierSix.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierSix.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierSix.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierSix.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierSix.getBottomComponentRow().get(1).getHealth();

    this.barrierSix.checkBoundaries();

    xLeft = this.barrierSix.getXLeft();
    xRight = this.barrierSix.getXRight();
    yTop = this.barrierSix.getYTop();
    yBottom = this.barrierSix.getYBottom();
    leftCol = this.barrierSix.getLeftCol();
    rightCol = this.barrierSix.getRightCol();
    topRow = this.barrierSix.getTopRow();
    bottomRow = this.barrierSix.getBottomRow();

    assertEquals(healthOne, 0);
    assertEquals(healthTwo, 0);
    assertEquals(healthThree, 0);
    assertEquals(healthFour, 0);
    assertEquals(healthFive, 0);
    assertEquals(healthSix, 0);
    assertEquals(healthSeven, 0);
    assertEquals(xLeft, 8);
    assertEquals(xRight, 16);
    assertEquals(yTop, 8);
    assertEquals(yBottom, 16);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 1);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 1);
    assertTrue(this.barrierSix.isBroken());

    // Reset and Check
    this.barrierSix.reset();

    healthOne = this.barrierSix.getTopComponentRow().get(0).getHealth();
    healthTwo = this.barrierSix.getTopComponentRow().get(1).getHealth();
    healthThree = this.barrierSix.getTopComponentRow().get(2).getHealth();
    healthFour = this.barrierSix.getMiddleComponentRow().get(0).getHealth();
    healthFive = this.barrierSix.getMiddleComponentRow().get(1).getHealth();
    healthSix = this.barrierSix.getBottomComponentRow().get(0).getHealth();
    healthSeven = this.barrierSix.getBottomComponentRow().get(1).getHealth();
    xLeft = this.barrierSix.getXLeft();
    xRight = this.barrierSix.getXRight();
    yTop = this.barrierSix.getYTop();
    yBottom = this.barrierSix.getYBottom();
    leftCol = this.barrierSix.getLeftCol();
    rightCol = this.barrierSix.getRightCol();
    topRow = this.barrierSix.getTopRow();
    bottomRow = this.barrierSix.getBottomRow();

    assertEquals(healthOne, 3);
    assertEquals(healthTwo, 3);
    assertEquals(healthThree, 3);
    assertEquals(healthFour, 3);
    assertEquals(healthFive, 3);
    assertEquals(healthSix, 3);
    assertEquals(healthSeven, 3);
    assertEquals(xLeft, 0);
    assertEquals(xRight, 24);
    assertEquals(yTop, 0);
    assertEquals(yBottom, 24);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 2);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 2);
    assertFalse(this.barrierSix.isBroken());
  }
}
