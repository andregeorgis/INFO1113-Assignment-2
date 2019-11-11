package invadem.assets.extension;

import invadem.assets.Invader;

import processing.core.PImage;

import java.util.List;

public class TeslaInvader extends Invader {

  public TeslaInvader(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y);
    this.health = 5;
  }

  public void reset(int x, int y) {
    super.reset(x, y);
    this.health = 5;
  }
}
