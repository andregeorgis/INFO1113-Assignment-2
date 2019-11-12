package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ZurkonInvaderTest {
  private ZurkonInvader invader;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);

    this.invader = new ZurkonInvader(imgs, 0, 0);
  }

  @Test
  public void testZurkonInvaderConstruction() {
    assertNotNull(this.invader);
  }

  @Test
  public void testZurkonInvaderReset() {
    int health = this.invader.getHealth();
    assertEquals(health, 3);

    this.invader.setHealth(0);
    health = this.invader.getHealth();
    assertEquals(health, 0);

    this.invader.reset(0, 0);

    health = this.invader.getHealth();
    assertEquals(health, 3);
  }
}
