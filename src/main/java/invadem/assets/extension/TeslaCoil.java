package invadem.assets.extension;

import invadem.assets.Projectile;
import invadem.assets.BarrierComponent;
import invadem.DrawableAsset;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class TeslaCoil extends Projectile {
  public static final int RATE = 90;
  public static final int WIDTH = 142;
  public static final int HEIGHT = 20;

  private boolean makeStep;
  private boolean zap;
  private boolean canZapLeft;
  private boolean canZapRight;
  private int rateCounter;
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

  public void draw(PApplet app) {
    this.spikeLeft.draw(app);
    this.spikeMiddle.draw(app);
    this.spikeRight.draw(app);

    if (this.canZapLeft && this.zap) {
      app.image(allImgs.get(1), x + 14, y, 50, 9);
    }
    if (this.canZapRight && this.zap) {
      app.image(allImgs.get(1), x + 78, y, 50, 9);
    }

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

    if (this.rateCounter >= RATE) {
      this.zap = !this.zap;
      this.rateCounter = 0;
    }
  }

  public boolean canZapLeft() {return this.canZapLeft;}

  public boolean canZapRight() {return this.canZapRight;}

  public boolean isZapping() {return this.zap;}

  public boolean checkCollisionWithAsset(DrawableAsset asset) {
    boolean returnBool = false;
    boolean checkCollision = false;

    if (this.x < (asset.getX() + asset.getWidth()) &&
        (this.x + WIDTH) > asset.getX() &&
        this.y < (asset.getY() + asset.getHeight()) &&
        (this.y + HEIGHT) > asset.getY()) {
      checkCollision = true;
    }

    if (checkCollision) {
      if (!this.spikeLeft.isDud()) {
        if (this.spikeLeft.checkCollisionWithAsset(asset)) {
          this.spikeLeft.hit();
          this.canZapLeft = false;
          asset.loseHealth(this.damage);
          returnBool = true;
        }
      }

      if (!this.spikeMiddle.isDud()) {
        if (this.spikeMiddle.checkCollisionWithAsset(asset)) {
          this.spikeMiddle.hit();
          this.canZapLeft = false;
          this.canZapRight = false;
          asset.loseHealth(this.damage);
          returnBool = true;
        }
      }

      if (!this.spikeRight.isDud()) {
        if (this.spikeRight.checkCollisionWithAsset(asset)) {
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
