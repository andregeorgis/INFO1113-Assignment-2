package invadem.assets.extension;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TeslaSpikeTest {
  private TeslaSpike spike;

  @Before
  public void setup() {
    this.spike = new TeslaSpike(null, 0, 0);
  }

  @Test
  public void testTeslaSpikeConstruction() {
    assertNotNull(this.spike);
  }
}
