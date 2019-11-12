package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class SlimeInvaderTest {
  private SlimeInvader invader;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);

    this.invader = new SlimeInvader(imgs, 0, 0);
  }

  // Test construction
  @Test
  public void testSlimeInvaderConstruction() {
    assertNotNull(this.invader);
  }

  // Test reset - correct health
  @Test
  public void testSlimeInvaderReset() {
    int health = this.invader.getHealth();
    assertEquals(health, 2);

    this.invader.setHealth(0);
    health = this.invader.getHealth();
    assertEquals(health, 0);

    this.invader.reset(0, 0);

    health = this.invader.getHealth();
    assertEquals(health, 2);
  }
}
