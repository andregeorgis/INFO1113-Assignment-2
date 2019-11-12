/*
  Responsible for distinguishing between Tank and KonamiTank. Only data type
  itself is needed - helps to separate code in App class.
*/

package invadem.assets.extension;

import invadem.assets.Tank;

import processing.core.PImage;

import java.util.List;

public class KonamiTank extends Tank {
  public KonamiTank(PImage tankImg) {
    super(tankImg);
  }
}
