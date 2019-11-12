package invadem;

import invadem.assets.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class AppTest extends App {
  // Set up the app
  @Before
  public void setupTest() {
    runSketch(new String[]{"--location=0,0", ""}, this);
    delay(3000);
  }

  // Test Shooting a Projectile
  @Test
  public void testTankProjectileShot() {
    delay(3000);
    // Assert no projectiles
    assertEquals(this.projectiles.getFriendlyProjectiles().size(), 0);

    // Press Space
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 32));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 32));

    // Assert one projectile
    assertEquals(this.projectiles.getFriendlyProjectiles().size(), 1);
    assertEquals(this.projectiles.getFriendlyProjectiles().get(0).getImage(), this.projectileImg);
  }

  // Test Random Invader shot
  @Test
  public void testRandomProjectileShot() {
    // Assert no projectile
    assertEquals(this.projectiles.getEnemyProjectiles().size(), 0);

    delay(6000);

    // Assert one projectile
    assertEquals(this.projectiles.getEnemyProjectiles().size(), 1);
    assertTrue(this.projectiles.getEnemyProjectiles().get(0).getImage() == this.projectileImg ||
              this.projectiles.getEnemyProjectiles().get(0).getImage() == this.powerProjectileImg);
  }

  // Test if entering konami code actually transitions to extension
  @Test
  public void testKonamiCode() {
    // Enter Code
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 38));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 38));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 38));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 38));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 40));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 40));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 40));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 40));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 37));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 37));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 39));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 39));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 37));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 37));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 39));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 39));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 66));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 66));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 65));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 65));
    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 8));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 8));

    delay(500);

    // Assert a transition
    assertTrue(this.gameOver);

    delay(3000);

    // Assert extension is now playing
    assertTrue(this.konami);
  }

  // Test transitioning to next level - forcefully
  @Test
  public void testNextLevelTransition() {
    assertFalse(this.nextLevel);

    delay(500);

    this.nextLevel();

    delay(500);

    assertTrue(this.nextLevel);

    delay(3000);

    assertFalse(this.nextLevel);
  }

  // Test transitioning to end of game - forcefully
  @Test
  public void testEndGameTransition() {
    assertFalse(this.gameOver);

    delay(500);

    this.endGame();

    delay(500);

    assertTrue(this.gameOver);

    delay(3000);

    assertFalse(this.gameOver);
  }

  // Test transitioning to end of game - by invaders reaching bottom
  @Test
  public void testInvaderReachBarrier() {
    this.swarm.setYBottom(460);

    delay(500);

    assertTrue(this.gameOver);

    delay(3000);

    assertFalse(this.gameOver);
  }

  // Test transitioning to end of game - by tank dying - forcefully :P
  @Test
  public void testTankDies() {
    this.tank.setHealth(0);

    delay(500);

    assertTrue(this.gameOver);

    delay(3000);

    assertFalse(this.gameOver);
  }

  // Test transitioning to end of game - by kill all invaders - forcefully :P
  @Test
  public void testInvaderSwarmDies() {
    for (Invader invader : this.swarm.getInvaders()) {
      invader.setHealth(0);
      invader.checkHealth();
    }

    delay(500);

    assertTrue(this.nextLevel);

    delay(3000);

    assertFalse(this.nextLevel);
  }
}
