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
  @Before
  public void setupTest() {
    runSketch(new String[]{"--location=0,0", ""}, this);
    delay(3000);
  }

  @Test
  public void testTankProjectileShot() {
    delay(3000);
    assertEquals(this.projectiles.getFriendlyProjectiles().size(), 0);

    keyPressed(new KeyEvent(null, 0, 0, 0, ' ', 32));
    keyReleased(new KeyEvent(null, 0, 0, 0, ' ', 32));

    assertEquals(this.projectiles.getFriendlyProjectiles().size(), 1);
    assertEquals(this.projectiles.getFriendlyProjectiles().get(0).getImage(), this.projectileImg);
  }

  @Test
  public void testRandomProjectileShot() {
    assertEquals(this.projectiles.getEnemyProjectiles().size(), 0);

    delay(6000);

    assertEquals(this.projectiles.getEnemyProjectiles().size(), 1);
    assertTrue(this.projectiles.getEnemyProjectiles().get(0).getImage() == this.projectileImg ||
              this.projectiles.getEnemyProjectiles().get(0).getImage() == this.powerProjectileImg);
  }

  @Test
  public void testKonamiCode() {
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

    assertTrue(this.gameOver);

    delay(3000);

    assertTrue(this.konami);
  }

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

  @Test
  public void testInvaderReachBarrier() {
    this.swarm.setYBottom(460);

    delay(500);

    assertTrue(this.gameOver);

    delay(3000);

    assertFalse(this.gameOver);
  }

  @Test
  public void testTankDies() {
    this.tank.setHealth(0);

    delay(500);

    assertTrue(this.gameOver);

    delay(3000);

    assertFalse(this.gameOver);
  }

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
