package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class CurrentProjectilesTest {
  private CurrentProjectiles currentProjectiles;
  private PImage projectileImg;
  private PImage powerProjectileImg;

  @Before
  public void setup() {
    this.projectileImg = new PImage();
    this.powerProjectileImg = new PImage();
    this.currentProjectiles = new CurrentProjectiles(this.projectileImg, this.powerProjectileImg);
  }

  @Test
  public void testCurrentProjectilesConstruction() {
    assertNotNull(this.currentProjectiles);
  }

  @Test
  public void testAddProjectileAndReset() {
    int numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    int numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 0);
    assertEquals(numOfEnemyProjectiles, 0);

    this.currentProjectiles.addProjectile(0, 0, true, false);
    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 0);

    this.currentProjectiles.addProjectile(0, 0, false, false);
    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 1);

    this.currentProjectiles.addProjectile(0, 0, false, true);
    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 2);

    PImage imageOne = this.currentProjectiles.getFriendlyProjectiles().get(0).getImage();
    PImage imageTwo = this.currentProjectiles.getEnemyProjectiles().get(0).getImage();
    PImage imageThree = this.currentProjectiles.getEnemyProjectiles().get(1).getImage();

    assertEquals(imageOne, this.projectileImg);
    assertEquals(imageTwo, this.projectileImg);
    assertEquals(imageThree, this.powerProjectileImg);

    this.currentProjectiles.reset();
    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 0);
    assertEquals(numOfEnemyProjectiles, 0);
  }

  @Test
  public void testCheckIfCurrentProjectilesOutside() {
    int numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    int numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 0);
    assertEquals(numOfEnemyProjectiles, 0);

    this.currentProjectiles.addProjectile(0, 100, true, false);
    this.currentProjectiles.addProjectile(0, -20, true, false);
    this.currentProjectiles.addProjectile(0, 200, true, false);
    this.currentProjectiles.addProjectile(0, 500, true, false);
    this.currentProjectiles.addProjectile(0, 30, false, false);
    this.currentProjectiles.addProjectile(0, -40, false, false);
    this.currentProjectiles.addProjectile(0, 510, false, true);
    this.currentProjectiles.addProjectile(0, 300, false, true);
    this.currentProjectiles.addProjectile(0, -10, false, false);
    this.currentProjectiles.addProjectile(0, -11, false, false);
    this.currentProjectiles.addProjectile(0, 409, false, true);
    this.currentProjectiles.addProjectile(0, 2, false, true);

    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();
    assertEquals(numOfFriendlyProjectiles, 4);
    assertEquals(numOfEnemyProjectiles, 8);

    this.currentProjectiles.checkIfProjectilesOutside();

    numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();
    assertEquals(numOfFriendlyProjectiles, 2);
    assertEquals(numOfEnemyProjectiles, 5);

    this.currentProjectiles.reset();
  }

  @Test
  public void testCurrentProjectilesCheckCollision() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);
    imgs.add(null);
    InvaderSwarm swarm = new InvaderSwarm(imgs);
    Tank tank = new Tank(null);
    List<Barrier> barriers = new ArrayList<Barrier>();
    barriers.add(new Barrier(imgs, imgs, imgs, imgs, 20, 400));
    barriers.add(new Barrier(imgs, imgs, imgs, imgs, 600, 400));

    //Hitting a broken barrier
    Barrier barrier = new Barrier(imgs, imgs, imgs, imgs, 500, 500);
    barrier.getTopComponentRow().get(0).setHealth(0);
    barrier.getTopComponentRow().get(1).setHealth(0);
    barrier.getTopComponentRow().get(2).setHealth(0);
    barrier.getMiddleComponentRow().get(0).setHealth(0);
    barrier.getMiddleComponentRow().get(1).setHealth(0);
    barrier.getBottomComponentRow().get(0).setHealth(0);
    barrier.getBottomComponentRow().get(1).setHealth(0);
    barrier.getTopComponentRow().get(0).checkHealth();
    barrier.getTopComponentRow().get(1).checkHealth();
    barrier.getTopComponentRow().get(2).checkHealth();
    barrier.getMiddleComponentRow().get(0).checkHealth();
    barrier.getMiddleComponentRow().get(1).checkHealth();
    barrier.getBottomComponentRow().get(0).checkHealth();
    barrier.getBottomComponentRow().get(1).checkHealth();
    barrier.checkBoundaries();

    assertTrue(barrier.isBroken());
    barriers.add(barrier);

    this.currentProjectiles.addProjectile(320, 460, false, false); // Hit tank
    this.currentProjectiles.addProjectile(320, 470, true, false); // Hit tank but friendly
    this.currentProjectiles.addProjectile(180, 78, true, false); // Hit swarm (3rd row from top)
    this.currentProjectiles.addProjectile(260, 78, true, false); // Hit swarm (3rd row from top)
    this.currentProjectiles.addProjectile(180, 25, false, false); // Hit swarm but enemy (top row)
    this.currentProjectiles.addProjectile(20, 400, true, false); // Hit barrier and friendly
    this.currentProjectiles.addProjectile(600, 400, false, true); // Hit barrier and enemy
    this.currentProjectiles.addProjectile(500, 500, false, true); // Hit broken barrier
    this.currentProjectiles.addProjectile(0, 0, true, false); // Hit nothing
    this.currentProjectiles.addProjectile(400, 400, true, false); // Hit enemy projectile
    this.currentProjectiles.addProjectile(400, 400, false, false); // Hit friendly projectile

    int score = this.currentProjectiles.checkCollisions(swarm, tank, barriers);
    int healthInvaderOne = swarm.getInvaders().get(20).getHealth();
    int healthInvaderTwo = swarm.getInvaders().get(23).getHealth();
    int healthTank = tank.getHealth();
    int healthBarrierOne = barriers.get(0).getTopComponentRow().get(0).getHealth();
    int healthBarrierTwo = barriers.get(0).getTopComponentRow().get(0).getHealth();
    int numOfFriendlyProjectiles = this.currentProjectiles.getFriendlyProjectiles().size();
    int numOfEnemyProjectiles = this.currentProjectiles.getEnemyProjectiles().size();

    assertEquals(score, 200);
    assertEquals(healthInvaderOne, 0);
    assertEquals(healthInvaderTwo, 0);
    assertEquals(healthTank, 2);
    assertEquals(healthBarrierOne, 2);
    assertEquals(healthBarrierTwo, 2);
    // When colliding, projectiles are not removed they are just "duds"
    assertEquals(numOfFriendlyProjectiles, 6);
    assertEquals(numOfEnemyProjectiles, 5);
    // Projectile colliding projectile
    assertTrue(this.currentProjectiles.getFriendlyProjectiles().get(5).isDud());
    assertTrue(this.currentProjectiles.getEnemyProjectiles().get(4).isDud());

  }
}
