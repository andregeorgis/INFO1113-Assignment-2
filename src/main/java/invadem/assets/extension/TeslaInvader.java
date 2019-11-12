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

  public void checkState() {
    super.checkState();

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

  public void electrify() {
    this.electrify = true;
    this.electrifyCounter = 0;
  }

  public boolean isElectrified() {return this.electrify;}
}
