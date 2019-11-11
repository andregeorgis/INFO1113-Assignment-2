package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PowerInvaderTest {
  private PowerInvader invaderOne;

  @Before
  public void setup() {
    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);
    imgs.add(null);

    this.invaderOne = new PowerInvader(imgs, 0, 0);
  }

  @Test
  public void testInvaderConstruction() {
    assertNotNull(this.invaderOne);
  }

}
