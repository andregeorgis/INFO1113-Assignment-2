package invadem.assets;

import invadem.AssetGroup;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class Barrier extends AssetGroup {

  private List<BarrierComponent> topComponentRow;
  private List<BarrierComponent> middleComponentRow;
  private List<BarrierComponent> bottomComponentRow;
  private boolean broken;

  public static final int WIDTH = 24;
  public static final int HEIGHT = 24;

  public Barrier(List<PImage> left, List<PImage> top, List<PImage> right, List<PImage> solid, int x, int y) {
    super(x, y, WIDTH, HEIGHT, 3, 3, BarrierComponent.WIDTH, BarrierComponent.HEIGHT);

    this.topComponentRow = new ArrayList<BarrierComponent>();
    this.topComponentRow.add(new BarrierComponent(left, x, y));
    this.topComponentRow.add(new BarrierComponent(top, x + BarrierComponent.WIDTH, y));
    this.topComponentRow.add(new BarrierComponent(right, x + 2 * BarrierComponent.WIDTH, y));
    this.middleComponentRow = new ArrayList<BarrierComponent>();
    this.middleComponentRow.add(new BarrierComponent(solid, x, y + BarrierComponent.HEIGHT));
    this.middleComponentRow.add(new BarrierComponent(solid, x + 2 * BarrierComponent.WIDTH, y + BarrierComponent.HEIGHT));
    this.bottomComponentRow = new ArrayList<BarrierComponent>();
    this.bottomComponentRow.add(new BarrierComponent(solid, x, y + 2 * BarrierComponent.HEIGHT));
    this.bottomComponentRow.add(new BarrierComponent(solid, x + 2 * BarrierComponent.WIDTH, y + 2 * BarrierComponent.HEIGHT));

    this.broken = false;
  }

  public void draw(PApplet app) {
    for (BarrierComponent component : this.topComponentRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

    for (BarrierComponent component : this.middleComponentRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

    for (BarrierComponent component : this.bottomComponentRow) {
      if (component.isAlive()) {
        component.draw(app);
      }
    }

  }

  public void reset() {
    for (BarrierComponent component : this.topComponentRow) {
      component.reset();
    }

    for (BarrierComponent component : this.middleComponentRow) {
      component.reset();
    }

    for (BarrierComponent component : this.bottomComponentRow) {
      component.reset();
    }


    this.xLeft -= this.leftCol * this.assetWidth;
    this.xRight += (2 - this.rightCol) * this.assetWidth;
    this.yTop -= this.topRow * this.assetHeight;
    this.yBottom += (2 - this.bottomRow) * this.assetHeight;
    this.leftCol = 0;
    this.rightCol = 2;
    this.topRow = 0;
    this.bottomRow = 2;
  }

  public boolean isBroken() {return this.broken;}

  public void checkBoundaries() {
    int i;

    // Check leftCol
    if (this.leftCol == 0) {
      if (!(this.topComponentRow.get(0).isAlive() ||
            this.middleComponentRow.get(0).isAlive() ||
            this.bottomComponentRow.get(0).isAlive())) {
        leftCol++;
        this.xLeft += this.assetWidth;
      }
    } else if (this.leftCol == 1) {
      if (!this.middleComponentRow.get(1).isAlive()) {
        leftCol++;
        this.xLeft += this.assetWidth;
      }
    } else if (this.leftCol == 2) {
      if (!(this.topComponentRow.get(2).isAlive() ||
            this.middleComponentRow.get(1).isAlive() ||
            this.bottomComponentRow.get(1).isAlive())) {
        leftCol++;
        this.xLeft += this.assetWidth;
      }
    }


    // Check rightCol
    if (this.rightCol == 0) {
      if (!(this.topComponentRow.get(2).isAlive() ||
            this.middleComponentRow.get(1).isAlive() ||
            this.bottomComponentRow.get(1).isAlive())) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    } else if (this.rightCol == 1) {
      if (!this.middleComponentRow.get(1).isAlive()) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    } else if (this.rightCol == 2) {
      if (!(this.topComponentRow.get(0).isAlive() ||
            this.middleComponentRow.get(0).isAlive() ||
            this.bottomComponentRow.get(0).isAlive())) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    }


    // Check topRow
    for (i = 0; i < 3; i++) {
      if (this.topRow == 0) {
        if (this.topComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.topRow == 1) {
        if (this.middleComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.topRow == 2) {
        if (this.bottomComponentRow.get(i).isAlive()) {
          break;
        }
      }
    }

    if (i == 3) {
      this.topRow++;
      this.yTop += this.assetHeight;
    }


    // Check bottomRow
    for (i = 0; i < 3; i++) {
      if (this.bottomRow == 0) {
        if (this.topComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.bottomRow == 1) {
        if (this.middleComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.bottomRow == 2) {
        if (this.bottomComponentRow.get(i).isAlive()) {
          break;
        }
      }
    }

    if (i == 3) {
      this.bottomRow--;
      this.yBottom -= this.assetHeight;
    }

    // Check if broken
    if (leftCol > rightCol || topRow > bottomRow) {
      this.broken = true;
    }
  }


  public void checkCollisionwithProjectile(Projectile projectile) {
    if (!(projectile.getY() + projectile.getHeight() > this.yBottom || (projectile.getY() < this.yTop)) &&
        !(projectile.getX() + projectile.getWidth() > this.xRight || (projectile.getX() < this.xLeft))) {
      for (BarrierComponent component : this.topComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();
        }
      }

      for (BarrierComponent component : this.middleComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();
        }
      }

      for (BarrierComponent component : this.bottomComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();
        }
      }
    }
  }


}
