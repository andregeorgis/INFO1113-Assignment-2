package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;

import java.util.List;

public class Barrier {
  private int x;
  private int y;

  private List<PImage> topRow;
  private List<PImage> middleRow;
  private List<PImage> bottomRow;

  public final int WIDTH_INDIVIDUAL = 8;
  public final int HEIGHT_INDIVIDUAL = 8;

  public Barrier(List<PImage> topRow, List<PImage> middleRow, List<PImage> bottomRow, int x, int y) {
    this.topRow = topRow;
    this.middleRow = middleRow;
    this.bottomRow = bottomRow;
    this.x = x;
    this.y = y;
  }

  public void draw(PApplet app) {
    for (int i = 0; i < 3; i++) {
      app.image(this.topRow.get(i), this.x + i * 8, this.y, this.WIDTH_INDIVIDUAL, this.HEIGHT_INDIVIDUAL);
    }

    for (int i = 0; i < 2; i++) {
      app.image(this.middleRow.get(i), this.x + i * 16, this.y + 8, this.WIDTH_INDIVIDUAL, this.HEIGHT_INDIVIDUAL);
    }

    for (int i = 0; i < 2; i++) {
      app.image(this.bottomRow.get(i), this.x + i * 16, this.y + 16, this.WIDTH_INDIVIDUAL, this.HEIGHT_INDIVIDUAL);
    }

  }
}
