/*
  Responsible for distinguishing between Invader and TeslaInvader.
*/

package invadem.assets.extension;

import invadem.assets.Invader;

import processing.core.PImage;

import java.util.List;

public class TeslaInvader extends Invader {
  private boolean electrify;
  private int electrifyCounter;

  public TeslaInvader(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y);
    this.health = 5;
    this.electrify = false;
    this.electrifyCounter = 0;
  }

  // Only difference is that when TeslaInvader shoots it has a third image that
  // is displayed.
  public void checkState() {
    super.checkState();

    // Display third image if shooting
    if (this.electrify) {
      this.electrifyCounter++;
      changeImage(2);

      if (this.electrifyCounter == 20) {
        this.electrify = false;
      }
    }
  }

  public void reset(int x, int y) {
    super.reset(x, y);
    this.health = 5;
    this.electrify = false;
  }

  // Used to proc special image when shooting
  public void electrify() {
    this.electrify = true;
    this.electrifyCounter = 0;
  }

  // Used to check if special image is displayed
  public boolean isElectrified() {return this.electrify;}
}
