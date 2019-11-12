/*
  Responsible for managing a whole barrier - asset group of 7 barrier components.
*/

package invadem.assets;

import invadem.AssetGroup;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class Barrier extends AssetGroup {
  // Rows
  private List<BarrierComponent> topComponentRow;
  private List<BarrierComponent> middleComponentRow;
  private List<BarrierComponent> bottomComponentRow;
  // If barrier is broken
  private boolean broken;
  // Initial values stored for resetting
  private int xInitial;
  private int yInitial;

  // Constants
  public static final int WIDTH_INITIAL = 24;
  public static final int HEIGHT_INITIAL = 24;

  public Barrier(List<PImage> left, List<PImage> top, List<PImage> right, List<PImage> solid, int x, int y) {
    super(x, y, WIDTH_INITIAL, HEIGHT_INITIAL, 3, 3, BarrierComponent.WIDTH, BarrierComponent.HEIGHT);

    // Creating the rows
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
    this.xInitial = x;
    this.yInitial = y;
  }

  public void draw(PApplet app) {
    // Draw each row - and each component
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
    // Reset each row - and each component
    for (BarrierComponent component : this.topComponentRow) {
      component.reset();
    }

    for (BarrierComponent component : this.middleComponentRow) {
      component.reset();
    }

    for (BarrierComponent component : this.bottomComponentRow) {
      component.reset();
    }


    this.xLeft = this.xInitial;
    this.xRight = this.xInitial + WIDTH_INITIAL;
    this.yTop = this.yInitial;
    this.yBottom = this.yInitial + HEIGHT_INITIAL;
    this.leftCol = 0;
    this.rightCol = 2;
    this.topRow = 0;
    this.bottomRow = 2;
    this.broken = false;
  }

  // Getter Methods
  public boolean isBroken() {return this.broken;}

  public List<BarrierComponent> getTopComponentRow() {return this.topComponentRow;}

  public List<BarrierComponent> getMiddleComponentRow() {return this.middleComponentRow;}

  public List<BarrierComponent> getBottomComponentRow() {return this.bottomComponentRow;}

  // Function that checks if Minimum and Maximum x, y, row and col have changed
  public void checkBoundaries() {
    int i;

    // Check if left-most column of barrier is destroyed
    if (this.leftCol == 0) {
      if (!(this.topComponentRow.get(0).isAlive() ||
            this.middleComponentRow.get(0).isAlive() ||
            this.bottomComponentRow.get(0).isAlive())) {
        leftCol++;
        this.xLeft += this.assetWidth;
      }
    } else if (this.leftCol == 1) {
      if (!this.topComponentRow.get(1).isAlive()) {
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


    // Check if right-most column of barrier is destroyed
    if (this.rightCol == 0) {
      if (!(this.topComponentRow.get(0).isAlive() ||
            this.middleComponentRow.get(0).isAlive() ||
            this.bottomComponentRow.get(0).isAlive())) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    } else if (this.rightCol == 1) {
      if (!this.topComponentRow.get(1).isAlive()) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    } else if (this.rightCol == 2) {
      if (!(this.topComponentRow.get(2).isAlive() ||
            this.middleComponentRow.get(1).isAlive() ||
            this.bottomComponentRow.get(1).isAlive())) {
        rightCol--;
        this.xRight -= this.assetWidth;
      }
    }


    // Check top-most row of barrier is destroyed
    for (i = 0; i < 3; i++) {
      if (this.topRow == 0) {
        if (this.topComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.topRow == 1) {
        if (i <= 1) {
          if (this.middleComponentRow.get(i).isAlive()) {
            break;
          }
        }
      } else if (this.topRow == 2) {
        if (i <= 1) {
          if (this.bottomComponentRow.get(i).isAlive()) {
            break;
          }
        }
      } else {
        break;
      }
    }

    if (i == 3) {
      this.topRow++;
      this.yTop += this.assetHeight;
    }


    // Check bottom-most row of barrier is destroyed
    for (i = 0; i < 3; i++) {
      if (this.bottomRow == 0) {
        if (this.topComponentRow.get(i).isAlive()) {
          break;
        }
      } else if (this.bottomRow == 1) {
        if (i <= 1) {
          if (this.middleComponentRow.get(i).isAlive()) {
            break;
          }
        }
      } else if (this.bottomRow == 2) {
        if (i <= 1) {
          if (this.bottomComponentRow.get(i).isAlive()) {
            break;
          }
        }
      } else {
        break;
      }
    }

    if (i == 3) {
      this.bottomRow--;
      this.yBottom -= this.assetHeight;
    }

    // Check if broken
    if (this.xLeft >= this.xRight || this.yTop >= this.yBottom || this.leftCol == this.rightCol || this.topRow == this.bottomRow) {
      this.broken = true;
    }
  }

  // Check if projectile collides into barrier
  public int checkCollisionWithProjectile(Projectile projectile) {
    boolean checkCollision = false;
    boolean componentKilled = false;

    // First check if it collides with entire barrier (using the Minimum and
    // Maximum x and y values) - make the program slightly more efficient
    if (this.xLeft < (projectile.getX() + projectile.getWidth()) &&
        this.xRight > projectile.getX() &&
        this.yTop < (projectile.getY() + projectile.getHeight()) &&
        this.yBottom > projectile.getY()) {
      checkCollision = true;
    }

    // If it collides with entire barrier - check each component
    if (checkCollision) {
      for (BarrierComponent component : this.topComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();

          if (!component.isAlive()) {
            componentKilled = true;
          }
        }
      }

      for (BarrierComponent component : this.middleComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();

          if (!component.isAlive()) {
            componentKilled = true;
          }
        }
      }

      for (BarrierComponent component : this.bottomComponentRow) {
        if (component.isAlive() && projectile.checkCollisionWithAsset(component)) {
          component.checkHealth();

          if (!component.isAlive()) {
            componentKilled = true;
          }
        }
      }
    }

    // If a component has been destroyed, check if Minimum and Maximum bounds
    // need to be changed
    if (componentKilled) {
      checkBoundaries();
    }

    return 0;
  }

}
