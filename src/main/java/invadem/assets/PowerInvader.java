/*
  Responsible for distinguishing between PowerInvader and Invader - all that is
  really needed is the extra data type to check "instanceof".
*/

package invadem.assets;

import processing.core.PImage;

import java.util.List;

public class PowerInvader extends Invader {

  public PowerInvader(List<PImage> imgs, int x, int y) {
    super(imgs, x, y);
  }
}
