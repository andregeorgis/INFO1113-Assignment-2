/*
  Responsible for distinguishing between Invader and SlimeInvader.
*/

package invadem.assets.extension;

import invadem.assets.Invader;

import processing.core.PImage;

import java.util.List;

public class SlimeInvader extends Invader {

  public SlimeInvader(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y);
    // Only difference is health
    this.health = 2;
  }

  public void reset(int x, int y) {
    super.reset(x, y);
    this.health = 2;
  }
}
