package invadem.assets.extension;

import invadem.assets.Projectile;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class KonamiInvaderSwarmTest {
  private KonamiInvaderSwarm swarm;
  private List<PImage> slimeImgs;
  private List<PImage> teslaImgs;
  private List<PImage> zurkonImgs;

  @Before
  public void setup() {
    this.slimeImgs = new ArrayList<PImage>();
    this.slimeImgs.add(new PImage());
    this.slimeImgs.add(new PImage());
    this.teslaImgs = new ArrayList<PImage>();
    this.teslaImgs.add(new PImage());
    this.teslaImgs.add(new PImage());
    this.teslaImgs.add(new PImage());
    this.zurkonImgs = new ArrayList<PImage>();
    this.zurkonImgs.add(new PImage());
    this.zurkonImgs.add(new PImage());

    List<PImage> imgs = new ArrayList<PImage>();
    imgs.addAll(this.slimeImgs);
    imgs.addAll(this.teslaImgs);
    imgs.addAll(this.zurkonImgs);

    this.swarm = new KonamiInvaderSwarm(imgs);
  }

  // Test construction
  @Test
  public void testKonamiInvaderSwarmConstruction() {
    assertNotNull(this.swarm);
  }

  // Test that invaders are generated properly - instances
  @Test
  public void testKonamiInvaderGenerating() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == 0) {
          assertTrue(this.swarm.getInvaders().get(j) instanceof TeslaInvader);
        } else if (i == 1) {
          assertTrue(this.swarm.getInvaders().get(10 + j) instanceof ZurkonInvader);
        } else {
          assertTrue(this.swarm.getInvaders().get(i * 10 + j) instanceof SlimeInvader);
        }
      }
    }
  }

  // Test that collisions are handled appropriately - checking health and
  // missing entire swarm
  @Test
  public void testKonamiCheckCollisionWithProjectile() {
    Projectile projectile;
    projectile = new Projectile(null, 171, 20);

    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(0).getHealth(), 4);

    projectile = new Projectile(null, 171, 20);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(0).getHealth(), 3);


    projectile = new Projectile(null, 171, 20);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(0).getHealth(), 2);


    projectile = new Projectile(null, 171, 50);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(10).getHealth(), 2);


    projectile = new Projectile(null, 171, 76);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(20).getHealth(), 1);

    projectile = new Projectile(null, 171, 76);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(20).getHealth(), 0);

    projectile = new Projectile(null, 171, 76);
    this.swarm.checkCollisionWithProjectile(projectile);
    assertEquals(this.swarm.getInvaders().get(20).getHealth(), 0);


    projectile = new Projectile(null, 0, 200);
    this.swarm.checkCollisionWithProjectile(projectile);


    projectile = new Projectile(null, 500, 200);
    this.swarm.checkCollisionWithProjectile(projectile);


    projectile = new Projectile(null, 300, 0);
    this.swarm.checkCollisionWithProjectile(projectile);


    projectile = new Projectile(null, 300, 500);
    this.swarm.checkCollisionWithProjectile(projectile);
  }
}
