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

  public void checkHealth() {
    this.imgIndex = 3 - this.health;
    if (this.imgIndex == 3) {
      this.img = null;
      this.alive = false;
    } else {
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
