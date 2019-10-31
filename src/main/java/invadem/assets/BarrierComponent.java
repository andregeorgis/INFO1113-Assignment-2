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
  private List<PImage> allImg;

  public BarrierComponent(List<PImage> allImg, int x, int y) {
    super(allImg.get(0), x, y, WIDTH, HEIGHT, HEALTH_INITIAL);
    this.imgIndex = 0;
    this.allImg = allImg;
  }

  public void draw(PApplet app) {
    if (img != null) {
      app.image(this.img, this.x, this.y, this.width, this.height);
      checkHealth();
    }
  }

  public void checkHealth() {
    this.imgIndex = 3 - this.health;
    if (this.imgIndex == 3) {
      this.img = null;
    } else {
      this.img = this.allImg.get(this.imgIndex);
    }
  }
}
