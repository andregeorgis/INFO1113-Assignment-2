/*
  Responsible for dealing with individual barrier components.
*/

package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.DrawableAsset;

import java.util.List;

public class BarrierComponent extends DrawableAsset {
  public static final int WIDTH = 8;
  public static final int HEIGHT = 8;
  public static final int HEALTH_INITIAL = 3;

  private int imgIndex;
  private boolean alive;

  public BarrierComponent(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y, WIDTH, HEIGHT, HEALTH_INITIAL);
    this.imgIndex = 0;
    this.alive = true;
  }

  public void draw(PApplet app) {
    if (isAlive()) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
  }

  // Check what image needs to be displayed - and if component is alive (if
  // compoenent is dead we temporarily disable it by setting the image to null
  // rather than removing it from memory entirely)
  public void checkHealth() {
    if (this.health <= 0) {
      this.img = null;
      this.alive = false;
    } else {
      this.imgIndex = 3 - this.health;
      changeImage(this.imgIndex);
    }
  }

  public boolean isAlive() {return this.alive;}

  public void reset() {
    changeImage(0);
    this.alive = true;
    this.health = 3;
  }
}
