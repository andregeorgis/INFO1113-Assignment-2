package invadem.assets;

import invadem.DrawableAsset;

import processing.core.PApplet;
import processing.core.PImage;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ProjectileTest {
  private Projectile projectileOne;
  private Projectile projectileTwo;
  private Projectile projectileThree;
  private Projectile projectileFour;

  @Before
  public void setup() {
    this.projectileOne = new Projectile(null, 0, 0, 1, 3);
    this.projectileTwo = new Projectile(null, 0, 10);
    this.projectileThree = new Projectile(null, 100, 100);
    this.projectileFour = new Projectile(null, 100, 100);
  }

  // Test construction
  @Test
  public void testProjectileConstruction() {
    assertNotNull(this.projectileOne);
    assertNotNull(this.projectileTwo);
    assertNotNull(this.projectileThree);
    assertNotNull(this.projectileFour);
  }

  // Test forcfefully "hitting" a projectile
  @Test
  public void testProjectileHit() {
    assertFalse(this.projectileOne.isDud());

    this.projectileOne.hit();

    assertTrue(this.projectileOne.isDud());
  }

  // Test the movement of a projectile is as expected
  @Test
  public void testProjectileMoving() {
    int x = this.projectileTwo.getX();
    int y = this.projectileTwo.getY();
    assertEquals(x, 0);
    assertEquals(y, 10);

    this.projectileTwo.tick();

    x = this.projectileTwo.getX();
    y = this.projectileTwo.getY();
    assertEquals(x, 0);
    assertEquals(y, 9);

    for (int i = 0; i < 9; i++) {
      this.projectileTwo.tick();
    }

    x = this.projectileTwo.getX();
    y = this.projectileTwo.getY();
    assertEquals(x, 0);
    assertEquals(y, 0);
  }

  // Test getting the damage
  @Test
  public void testProjectileGetterMethod() {
    int damage = this.projectileOne.getDamage();
    assertEquals(damage, 1);
  }

  // Test that projectiles are correctly classified as outside - if far enough
  @Test
  public void testProjectileIsOutside() {
    int y = this.projectileThree.getY();
    assertEquals(y, 100);
    assertFalse(this.projectileThree.isProjectileOutside());

    this.projectileThree.setY(-5);
    y = this.projectileThree.getY();
    assertEquals(y, -5);
    assertFalse(this.projectileThree.isProjectileOutside());

    this.projectileThree.setY(-11);
    y = this.projectileThree.getY();
    assertEquals(y, -11);
    assertTrue(this.projectileThree.isProjectileOutside());

    this.projectileThree.setY(480);
    y = this.projectileThree.getY();
    assertEquals(y, 480);
    assertFalse(this.projectileThree.isProjectileOutside());

    this.projectileThree.setY(491);
    y = this.projectileThree.getY();
    assertEquals(y, 491);
    assertTrue(this.projectileThree.isProjectileOutside());
  }

  // Test colliding projectiles with other assets - damage done and if projectile
  // is hit
  @Test
  public void testProjectileCollisions() {
    PImage img = new PImage();
    this.projectileFour.changeImage(img);

    PImage currentImage = this.projectileFour.getImage();
    int projectileX = this.projectileFour.getX();
    int projectileY = this.projectileFour.getY();
    assertNotNull(currentImage);
    assertEquals(projectileX, 100);
    assertEquals(projectileY, 100);

    DrawableAsset asset = new DrawableAsset((PImage)null, 1, 2, 3, 4, 5) {
      public void draw(PApplet app) {;}
    };

    int health = asset.getHealth();
    assertEquals(health, 5);

    this.projectileFour.setX(1);
    this.projectileFour.setY(7);
    projectileX = this.projectileFour.getX();
    projectileY = this.projectileFour.getY();
    assertEquals(projectileX, 1);
    assertEquals(projectileY, 7);

    this.projectileFour.checkCollisionWithAsset(asset);
    currentImage = this.projectileFour.getImage();
    health = asset.getHealth();
    assertNotNull(currentImage);
    assertEquals(health, 5);

    this.projectileFour.setX(1);
    this.projectileFour.setY(-2);
    projectileX = this.projectileFour.getX();
    projectileY = this.projectileFour.getY();
    assertEquals(projectileX, 1);
    assertEquals(projectileY, -2);

    this.projectileFour.checkCollisionWithAsset(asset);
    currentImage = this.projectileFour.getImage();
    health = asset.getHealth();
    assertNotNull(currentImage);
    assertEquals(health, 5);

    this.projectileFour.setX(0);
    this.projectileFour.setY(2);
    projectileX = this.projectileFour.getX();
    projectileY = this.projectileFour.getY();
    assertEquals(projectileX, 0);
    assertEquals(projectileY, 2);

    this.projectileFour.checkCollisionWithAsset(asset);
    currentImage = this.projectileFour.getImage();
    health = asset.getHealth();
    assertNotNull(currentImage);
    assertEquals(health, 5);

    this.projectileFour.setX(5);
    this.projectileFour.setY(2);
    projectileX = this.projectileFour.getX();
    projectileY = this.projectileFour.getY();
    assertEquals(projectileX, 5);
    assertEquals(projectileY, 2);

    this.projectileFour.checkCollisionWithAsset(asset);
    currentImage = this.projectileFour.getImage();
    health = asset.getHealth();
    assertNotNull(currentImage);
    assertEquals(health, 5);

    this.projectileFour.setX(1);
    this.projectileFour.setY(2);
    projectileX = this.projectileFour.getX();
    projectileY = this.projectileFour.getY();
    assertEquals(projectileX, 1);
    assertEquals(projectileY, 2);

    this.projectileFour.checkCollisionWithAsset(asset);
    currentImage = this.projectileFour.getImage();
    health = asset.getHealth();
    assertNull(currentImage);
    assertEquals(health, 4);
  }
}
