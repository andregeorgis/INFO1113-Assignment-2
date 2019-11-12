package invadem.assets.extension;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class KonamiCurrentProjectilesTest {
  private KonamiCurrentProjectiles projectiles;
  private List<PImage> teslaImgs;
  private List<PImage> bladeImgs;
  private PImage slimeImg;
  private PImage zurkonImg;

  @Before
  public void setup() {
    this.slimeImg = new PImage();
    this.zurkonImg = new PImage();
    this.teslaImgs = new ArrayList<PImage>();
    this.teslaImgs.add(new PImage());
    this.teslaImgs.add(new PImage());
    this.bladeImgs = new ArrayList<PImage>();
    this.bladeImgs.add(new PImage());
    this.bladeImgs.add(new PImage());

    List<PImage> img = new ArrayList<PImage>();
    img.addAll(this.bladeImgs);
    img.add(this.slimeImg);
    img.add(this.zurkonImg);
    img.addAll(this.teslaImgs);

    this.projectiles = new KonamiCurrentProjectiles(img);
  }

  @Test
  public void testKonamiCurrentProjectilesConstruction() {
    assertNotNull(this.projectiles);
  }

  @Test
  public void testKonamiCurrentProjectilesAddProjectile() {
    int numOfFriendlyProjectiles = this.projectiles.getFriendlyProjectiles().size();
    int numOfEnemyProjectiles = this.projectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 0);
    assertEquals(numOfEnemyProjectiles, 0);

    this.projectiles.addProjectile(0, 0, true, 'B');
    numOfFriendlyProjectiles = this.projectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.projectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 0);

    this.projectiles.addProjectile(0, 0, false, 'S');
    numOfFriendlyProjectiles = this.projectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.projectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 1);

    this.projectiles.addProjectile(0, 0, false, 'Z');
    numOfFriendlyProjectiles = this.projectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.projectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 2);

    this.projectiles.addProjectile(0, 0, false, 'T');
    numOfFriendlyProjectiles = this.projectiles.getFriendlyProjectiles().size();
    numOfEnemyProjectiles = this.projectiles.getEnemyProjectiles().size();

    assertEquals(numOfFriendlyProjectiles, 1);
    assertEquals(numOfEnemyProjectiles, 3);

    assertTrue(this.projectiles.getFriendlyProjectiles().get(0) instanceof BladeProjectile);
    assertTrue(this.projectiles.getEnemyProjectiles().get(0) instanceof SlimeProjectile);
    assertTrue(this.projectiles.getEnemyProjectiles().get(1) instanceof ZurkonProjectile);
    assertTrue(this.projectiles.getEnemyProjectiles().get(2) instanceof TeslaCoil);
  }
}
