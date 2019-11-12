package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class KonamiTankTest {
  private KonamiTank tank;

  @Before
  public void setup() {
    this.tank = new KonamiTank(null);
  }

  // Test construction
  @Test
  public void testKonamiTankConstruction() {
    assertNotNull(this.tank);
  }
}
