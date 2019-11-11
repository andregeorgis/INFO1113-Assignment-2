package invadem.assets.extension;

import invadem.assets.Tank;

import processing.core.PImage;

import java.util.List;

public class KonamiTank extends Tank {

  private List<PImage> bladeImgs;

  public KonamiTank(PImage tankImg, List<PImage> bladeImgs) {
    super(tankImg);
    this.bladeImgs = bladeImgs;
  }
}
