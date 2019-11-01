package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class Barrier {
  private int x;
  private int y;

  private List<BarrierComponent> topRow;
  private List<BarrierComponent> middleRow;
  private List<BarrierComponent> bottomRow;

  public Barrier(List<PImage> left, List<PImage> top, List<PImage> right, List<PImage> solid, int x, int y) {
    this.topRow = new ArrayList<BarrierComponent>();
    this.topRow.add(new BarrierComponent(left, x, y));
    this.topRow.add(new BarrierComponent(top, x + 8, y));
    this.topRow.add(new BarrierComponent(right, x + 16, y));
    this.middleRow = new ArrayList<BarrierComponent>();
    this.middleRow.add(new BarrierComponent(solid, x, y + 8));
    this.middleRow.add(new BarrierComponent(solid, x + 16, y + 8));
    this.bottomRow = new ArrayList<BarrierComponent>();
    this.bottomRow.add(new BarrierComponent(solid, x, y + 16));
    this.bottomRow.add(new BarrierComponent(solid, x + 16, y + 16));

    this.x = x;
    this.y = y;
  }

  public void draw(PApplet app) {
    for (BarrierComponent component : topRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

    for (BarrierComponent component : middleRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

    for (BarrierComponent component : bottomRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

  }

  public void reset() {
    for (BarrierComponent component : topRow) {
      component.reset();
    }

    for (BarrierComponent component : middleRow) {
      component.reset();
    }

    for (BarrierComponent component : bottomRow) {
      component.reset();
    }
  }
}
