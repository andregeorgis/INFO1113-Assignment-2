package invadem.assets.extension;

import invadem.assets.Invader;

import processing.core.PImage;

import java.util.List;

public class ZurkonInvader extends Invader {

  public ZurkonInvader(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y);
    this.health = 3;
  }

  public void reset(int x, int y) {
    super.reset(x, y);
    this.health = 3;
  }
}
