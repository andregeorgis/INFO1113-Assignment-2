/*
  Responsible for distinguishing between projectile and TeslaCoil.
*/

package invadem.assets.extension;

import invadem.assets.Projectile;
import invadem.assets.BarrierComponent;
import invadem.DrawableAsset;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class TeslaCoil extends Projectile {
  // Constants
  public static final int RATE = 90;
  public static final int WIDTH = 142;
  public static final int HEIGHT = 20;

  // Variables to track animation and collisions
  private boolean makeStep; // Every 2nd Frame
  private boolean zap; // Can collide with electric arc
  private boolean canZapLeft; // Left arc can be formed
  private boolean canZapRight; // Right arc can be formed
  private int rateCounter;
  // Each of the spikes
  private TeslaSpike spikeLeft;
  private TeslaSpike spikeMiddle;
  private TeslaSpike spikeRight;

  public TeslaCoil(List<PImage> allImgs, int x, int y) {
    super(allImgs.get(0), x, y, WIDTH, HEIGHT);
    this.damage = 2;
    this.allImgs = allImgs;
    this.makeStep = true;
    this.zap = false;
    this.canZapLeft = true;
    this.canZapRight = true;
    this.rateCounter = 0;
    this.spikeLeft = new TeslaSpike(allImgs.get(0), x, y);
    this.spikeMiddle = new TeslaSpike(allImgs.get(0), x + 64, y);
    this.spikeRight = new TeslaSpike(allImgs.get(0), x + 128, y);
  }

  // Draw each spike and electric arc separately
  public void draw(PApplet app) {
    this.spikeLeft.draw(app);
    this.spikeMiddle.draw(app);
    this.spikeRight.draw(app);

    // Only draw arcs if they can be formed
    if (this.canZapLeft && this.zap) {
      app.image(allImgs.get(1), x + 14, y, 50, 9);
    }
    if (this.canZapRight && this.zap) {
      app.image(allImgs.get(1), x + 78, y, 50, 9);
    }

    // Move every 2nd frame
    if (this.makeStep) {
      tick();
    }

    this.makeStep = !this.makeStep;
  }

  public void tick() {
    this.rateCounter++;
    changeY();
    this.spikeLeft.tick();
    this.spikeMiddle.tick();
    this.spikeRight.tick();

    // Equal time intervals for turning electricity on and off
    if (this.rateCounter >= RATE) {
      this.zap = !this.zap;
      this.rateCounter = 0;
    }
  }

  // Tracking if electricity is on/off and if arcs can be formed
  public boolean canZapLeft() {return this.canZapLeft;}

  public boolean canZapRight() {return this.canZapRight;}

  public boolean isZapping() {return this.zap;}

  public boolean checkCollisionWithAsset(DrawableAsset asset) {
    boolean returnBool = false;
    boolean checkCollision = false;

    // First check if it collides with entire coil  - make the program slightly
    // more efficient
    if (this.x < (asset.getX() + asset.getWidth()) &&
        (this.x + WIDTH) > asset.getX() &&
        this.y < (asset.getY() + asset.getHeight()) &&
        (this.y + HEIGHT) > asset.getY()) {
      checkCollision = true;
    }

    // If it collides with entire barrier - check each spike and arc
    // Only check spikes if they are alive
    // Only check arcs if they are on AND can be formed
    if (checkCollision) {
      if (!this.spikeLeft.isDud()) {
        if (this.spikeLeft.checkCollisionWithAsset(asset)) {
          // When leftSpike is destroyed, left arc cannot be formed
          this.spikeLeft.hit();
          this.canZapLeft = false;
          asset.loseHealth(this.damage);
          returnBool = true;
        }
      }

      if (!this.spikeMiddle.isDud()) {
        if (this.spikeMiddle.checkCollisionWithAsset(asset)) {
          // When middleSpike is destroyed, both arcs cannot be formed
          this.spikeMiddle.hit();
          this.canZapLeft = false;
          this.canZapRight = false;
          asset.loseHealth(this.damage);
          returnBool = true;
        }
      }

      if (!this.spikeRight.isDud()) {
        if (this.spikeRight.checkCollisionWithAsset(asset)) {
          // When rightSpike is destroyed, right arc cannot be formed
          this.spikeRight.hit();
          this.canZapRight = false;
          asset.loseHealth(this.damage);
          returnBool = true;
        }
      }

      if (!(asset instanceof BarrierComponent)) {
        if (this.canZapLeft && this.zap) {
          if (this.x + 14 < (asset.getX() + asset.getWidth()) &&
              (this.x + 54) > asset.getX() &&
              this.y < (asset.getY() + asset.getHeight()) &&
              (this.y + 9) > asset.getY()) {
            asset.loseHealth(this.damage);
            returnBool = true;
          }
        }

        if (this.canZapRight && this.zap) {
          if (this.x + 78 < (asset.getX() + asset.getWidth()) &&
              (this.x + 128) > asset.getX() &&
              this.y < (asset.getY() + asset.getHeight()) &&
              (this.y + 9) > asset.getY()) {
            asset.loseHealth(this.damage);
            returnBool = true;
          }
        }
      }
    }

    return returnBool;
  }
}
