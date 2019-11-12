package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class InvaderSwarmTest {
  private InvaderSwarm swarmOne;
  private PImage regularInvaderImgOne;
  private PImage regularInvaderImgTwo;
  private PImage armouredInvaderImgOne;
  private PImage armouredInvaderImgTwo;
  private PImage powerInvaderImgOne;
  private PImage powerInvaderImgTwo;

  @Before
  public void setup() {
    this.regularInvaderImgOne = new PImage();
    this.regularInvaderImgTwo = new PImage();
    this.armouredInvaderImgOne = new PImage();
    this.armouredInvaderImgTwo = new PImage();
    this.powerInvaderImgOne = new PImage();
    this.powerInvaderImgTwo = new PImage();
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(this.regularInvaderImgOne);
    imgs.add(this.regularInvaderImgTwo);
    imgs.add(this.armouredInvaderImgOne);
    imgs.add(this.armouredInvaderImgTwo);
    imgs.add(this.powerInvaderImgOne);
    imgs.add(this.powerInvaderImgTwo);
    this.swarmOne = new InvaderSwarm(imgs);
  }

  // Test construction
  @Test
  public void testInvaderSwarmConstruction() {
    assertNotNull(this.swarmOne);
  }

  // Test that invaders are generated correctly (right spots and right types)
  @Test
  public void testInvaderGenerating() {
    List<Invader> invaders = this.swarmOne.getInvaders();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        assertEquals(invaders.get(i * 10 + j).getX(), 171 + j * 28);
        assertEquals(invaders.get(i * 10 + j).getY(), 20 + i * 28);

        if (i == 0) {
          assertTrue(invaders.get(j) instanceof ArmouredInvader);
        } else if (i == 1) {
          assertTrue(invaders.get(10 + j) instanceof PowerInvader);
        } else {
          assertTrue(invaders.get(i * 10 + j) instanceof Invader);
        }
      }
    }
  }

  // Test function that provides number of alive invaders
  @Test
  public void testInvaderSwarmNumOfInvaders() {
    int numOfInvaders = this.swarmOne.numOfInvaders();
    assertEquals(numOfInvaders, 40);

    this.swarmOne.getInvaders().get(2).setHealth(0);
    this.swarmOne.getInvaders().get(21).setHealth(0);
    this.swarmOne.getInvaders().get(39).setHealth(0);
    this.swarmOne.getInvaders().get(31).setHealth(0);
    this.swarmOne.getInvaders().get(0).setHealth(0);
    this.swarmOne.getInvaders().get(2).checkHealth();
    this.swarmOne.getInvaders().get(21).checkHealth();
    this.swarmOne.getInvaders().get(39).checkHealth();
    this.swarmOne.getInvaders().get(31).checkHealth();
    this.swarmOne.getInvaders().get(0).checkHealth();

    numOfInvaders = this.swarmOne.numOfInvaders();
    assertEquals(numOfInvaders, 35);
    assertFalse(this.swarmOne.isDead());

    this.swarmOne.reset();

    numOfInvaders = this.swarmOne.numOfInvaders();
    assertEquals(numOfInvaders, 40);
    assertFalse(this.swarmOne.isDead());

    for (int i = 0; i < 40; i++) {
      this.swarmOne.getInvaders().get(i).setHealth(0);
      this.swarmOne.getInvaders().get(i).checkHealth();
    }

    numOfInvaders = this.swarmOne.numOfInvaders();
    assertEquals(numOfInvaders, 0);
    assertTrue(this.swarmOne.isDead());

    this.swarmOne.reset();
  }

  // Test resetting the swarm
  @Test
  public void testInvaderSwarmReset() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getX(), 171 + j * 28);
        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getY(), 20 + i * 28);

        this.swarmOne.getInvaders().get(i * 10 + j).setX(20);
        this.swarmOne.getInvaders().get(i * 10 + j).setY(20);

        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getX(), 20);
        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getY(), 20);
      }
    }

    this.swarmOne.reset();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getX(), 171 + j * 28);
        assertEquals(this.swarmOne.getInvaders().get(i * 10 + j).getY(), 20 + i * 28);
      }
    }

  }

  // Test the change in random projectile rate when changing level or ending game
  @Test
  public void testInvaderSwarmNextLevelAndEndGame() {
    int projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 300);

    this.swarmOne.nextLevel();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 240);

    this.swarmOne.nextLevel();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 180);

    this.swarmOne.nextLevel();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 120);

    this.swarmOne.nextLevel();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 60);

    this.swarmOne.nextLevel();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 60);

    this.swarmOne.endGame();
    projectileRate = this.swarmOne.getProjectileRate();
    assertEquals(projectileRate, 300);
  }

  // Test that collisions are handled appropriately
  @Test
  public void testInvaderSwarmCollisionWithProjectile() {
    Projectile projectile;
    projectile = new Projectile(null, 171, 20);

    int score = 0;
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 0);

    projectile = new Projectile(null, 171, 20);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 0);

    projectile = new Projectile(null, 171, 20);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 250);


    projectile = new Projectile(null, 171, 50);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 500);


    projectile = new Projectile(null, 171, 76);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 600);


    projectile = new Projectile(null, 0, 200);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 600);


    projectile = new Projectile(null, 500, 200);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 600);


    projectile = new Projectile(null, 300, 0);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 600);


    projectile = new Projectile(null, 300, 500);
    score += this.swarmOne.checkCollisionWithProjectile(projectile);
    assertEquals(score, 600);


  }

  // Test that swarm boundaries are updated appropriately when invaders are killed
  @Test
  public void testInvaderSwarmCheckBoundaries() {
    this.swarmOne.checkBoundaries();
    int xLeft = this.swarmOne.getXLeft();
    int xRight = this.swarmOne.getXRight();
    int yTop = this.swarmOne.getYTop();
    int yBottom = this.swarmOne.getYBottom();
    int leftCol = this.swarmOne.getLeftCol();
    int rightCol = this.swarmOne.getRightCol();
    int topRow = this.swarmOne.getTopRow();
    int bottomRow = this.swarmOne.getBottomRow();
    assertEquals(xLeft, 171);
    assertEquals(xRight, 439);
    assertEquals(yTop, 20);
    assertEquals(yBottom, 120);
    assertEquals(leftCol, 0);
    assertEquals(rightCol, 9);
    assertEquals(topRow, 0);
    assertEquals(bottomRow, 3);

    // Destroying from top down
    for (int row = 0; row < 3; row++) {
      for (int i = 0; i < 10; i++) {
        this.swarmOne.getInvaders().get(row * 10 + i).setHealth(0);
        this.swarmOne.getInvaders().get(row * 10 + i).checkHealth();
      }

      this.swarmOne.checkBoundaries();

      xLeft = this.swarmOne.getXLeft();
      xRight = this.swarmOne.getXRight();
      yTop = this.swarmOne.getYTop();
      yBottom = this.swarmOne.getYBottom();
      leftCol = this.swarmOne.getLeftCol();
      rightCol = this.swarmOne.getRightCol();
      topRow = this.swarmOne.getTopRow();
      bottomRow = this.swarmOne.getBottomRow();
      assertEquals(xLeft, 171);
      assertEquals(xRight, 439);
      assertEquals(yTop, 20 + (row + 1) * 28);
      assertEquals(yBottom, 120);
      assertEquals(leftCol, 0);
      assertEquals(rightCol, 9);
      assertEquals(topRow, row + 1);
      assertEquals(bottomRow, 3);

    }

    for (int i = 0; i < 10; i++) {
      this.swarmOne.getInvaders().get(30 + i).setHealth(0);
      this.swarmOne.getInvaders().get(30 + i).checkHealth();
    }

    this.swarmOne.checkBoundaries();
    xLeft = this.swarmOne.getXLeft();
    xRight = this.swarmOne.getXRight();
    yTop = this.swarmOne.getYTop();
    yBottom = this.swarmOne.getYBottom();
    leftCol = this.swarmOne.getLeftCol();
    rightCol = this.swarmOne.getRightCol();
    topRow = this.swarmOne.getTopRow();
    bottomRow = this.swarmOne.getBottomRow();
    assertEquals(xLeft, 199);
    assertEquals(xRight, 411);
    assertEquals(yTop, 132);
    assertEquals(yBottom, 92);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 8);
    assertEquals(topRow, 4);
    assertEquals(bottomRow, 2);

    this.swarmOne.reset();



    // Destroying from bottom up
    for (int row = 0; row < 3; row++) {
      for (int i = 0; i < 10; i++) {
        this.swarmOne.getInvaders().get((3 - row) * 10 + i).setHealth(0);
        this.swarmOne.getInvaders().get((3 - row) * 10 + i).checkHealth();
      }

      this.swarmOne.checkBoundaries();

      xLeft = this.swarmOne.getXLeft();
      xRight = this.swarmOne.getXRight();
      yTop = this.swarmOne.getYTop();
      yBottom = this.swarmOne.getYBottom();
      leftCol = this.swarmOne.getLeftCol();
      rightCol = this.swarmOne.getRightCol();
      topRow = this.swarmOne.getTopRow();
      bottomRow = this.swarmOne.getBottomRow();
      assertEquals(xLeft, 171);
      assertEquals(xRight, 439);
      assertEquals(yTop, 20);
      assertEquals(yBottom, 120 - (row + 1) * 28);
      assertEquals(leftCol, 0);
      assertEquals(rightCol, 9);
      assertEquals(topRow, 0);
      assertEquals(bottomRow, 3 - row - 1);

    }

    for (int i = 0; i < 10; i++) {
      this.swarmOne.getInvaders().get(i).setHealth(0);
      this.swarmOne.getInvaders().get(i).checkHealth();
    }

    this.swarmOne.checkBoundaries();
    xLeft = this.swarmOne.getXLeft();
    xRight = this.swarmOne.getXRight();
    yTop = this.swarmOne.getYTop();
    yBottom = this.swarmOne.getYBottom();
    leftCol = this.swarmOne.getLeftCol();
    rightCol = this.swarmOne.getRightCol();
    topRow = this.swarmOne.getTopRow();
    bottomRow = this.swarmOne.getBottomRow();
    assertEquals(xLeft, 199);
    assertEquals(xRight, 411);
    assertEquals(yTop, 48);
    assertEquals(yBottom, 8);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, 8);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, -1);

    this.swarmOne.reset();



    // Destroying from left to right
    for (int col = 0; col < 9; col++) {
      for (int i = 0; i < 4; i++) {
        this.swarmOne.getInvaders().get(i * 10 + col).setHealth(0);
        this.swarmOne.getInvaders().get(i * 10 + col).checkHealth();
      }

      this.swarmOne.checkBoundaries();

      xLeft = this.swarmOne.getXLeft();
      xRight = this.swarmOne.getXRight();
      yTop = this.swarmOne.getYTop();
      yBottom = this.swarmOne.getYBottom();
      leftCol = this.swarmOne.getLeftCol();
      rightCol = this.swarmOne.getRightCol();
      topRow = this.swarmOne.getTopRow();
      bottomRow = this.swarmOne.getBottomRow();
      assertEquals(xLeft, 171 + (col + 1) * 28);
      assertEquals(xRight, 439);
      assertEquals(yTop, 20);
      assertEquals(yBottom, 120);
      assertEquals(leftCol, 0 + (col + 1));
      assertEquals(rightCol, 9);
      assertEquals(topRow, 0);
      assertEquals(bottomRow, 3);

    }

    for (int i = 0; i < 4; i++) {
      this.swarmOne.getInvaders().get(i * 10 + 9).setHealth(0);
      this.swarmOne.getInvaders().get(i * 10 + 9).checkHealth();
    }

    this.swarmOne.checkBoundaries();
    xLeft = this.swarmOne.getXLeft();
    xRight = this.swarmOne.getXRight();
    yTop = this.swarmOne.getYTop();
    yBottom = this.swarmOne.getYBottom();
    leftCol = this.swarmOne.getLeftCol();
    rightCol = this.swarmOne.getRightCol();
    topRow = this.swarmOne.getTopRow();
    bottomRow = this.swarmOne.getBottomRow();
    assertEquals(xLeft, 451);
    assertEquals(xRight, 411);
    assertEquals(yTop, 48);
    assertEquals(yBottom, 92);
    assertEquals(leftCol, 10);
    assertEquals(rightCol, 8);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 2);

    this.swarmOne.reset();



    // Destroying from right to left
    for (int col = 0; col < 9; col++) {
      for (int i = 0; i < 4; i++) {
        this.swarmOne.getInvaders().get(i * 10 + (9 - col)).setHealth(0);
        this.swarmOne.getInvaders().get(i * 10 + (9 - col)).checkHealth();
      }

      this.swarmOne.checkBoundaries();

      xLeft = this.swarmOne.getXLeft();
      xRight = this.swarmOne.getXRight();
      yTop = this.swarmOne.getYTop();
      yBottom = this.swarmOne.getYBottom();
      leftCol = this.swarmOne.getLeftCol();
      rightCol = this.swarmOne.getRightCol();
      topRow = this.swarmOne.getTopRow();
      bottomRow = this.swarmOne.getBottomRow();
      assertEquals(xLeft, 171);
      assertEquals(xRight, 439 - (col + 1) * 28);
      assertEquals(yTop, 20);
      assertEquals(yBottom, 120);
      assertEquals(leftCol, 0);
      assertEquals(rightCol, 9 - (col + 1));
      assertEquals(topRow, 0);
      assertEquals(bottomRow, 3);

    }

    for (int i = 0; i < 4; i++) {
      this.swarmOne.getInvaders().get(i * 10).setHealth(0);
      this.swarmOne.getInvaders().get(i * 10).checkHealth();
    }

    this.swarmOne.checkBoundaries();
    xLeft = this.swarmOne.getXLeft();
    xRight = this.swarmOne.getXRight();
    yTop = this.swarmOne.getYTop();
    yBottom = this.swarmOne.getYBottom();
    leftCol = this.swarmOne.getLeftCol();
    rightCol = this.swarmOne.getRightCol();
    topRow = this.swarmOne.getTopRow();
    bottomRow = this.swarmOne.getBottomRow();
    assertEquals(xLeft, 199);
    assertEquals(xRight, 159);
    assertEquals(yTop, 48);
    assertEquals(yBottom, 92);
    assertEquals(leftCol, 1);
    assertEquals(rightCol, -1);
    assertEquals(topRow, 1);
    assertEquals(bottomRow, 2);

    this.swarmOne.reset();
  }
}
