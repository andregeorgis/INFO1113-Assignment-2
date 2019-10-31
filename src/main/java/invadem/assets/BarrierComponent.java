package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.DrawableAsset;

import java.util.List;

public class BarrierComponent extends DrawableAsset {
  public static final int WIDTH = 8;
  public static final int HEIGHT = 8;
  public static final int HEALTH_INITIAL = 3;

  public BarrierComponent(List<PImage> img, int x, int y) {
    super(img.get(0), x, y, WIDTH, HEIGHT, HEALTH_INITIAL);
  }

  public void draw(PApplet app) {
    if (img != null) {
      app.image(this.img, this.x, this.y, this.width, this.height);
    }
  }
}
