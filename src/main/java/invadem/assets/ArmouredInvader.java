/*
  Responsible for distinguishing ArmouredInvaders from Invaders.
*/

package invadem.assets;

import processing.core.PImage;

import java.util.List;

public class ArmouredInvader extends Invader {

  public ArmouredInvader(List<PImage> imgs, int x, int y) {
    super(imgs, x, y);
    // Only difference is amount of health
    this.health = 3;
  }

  public void reset(int x, int y) {
    super.reset(x, y);
    this.health = 3;
  }
}
