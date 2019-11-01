package invadem;

import processing.core.PImage;

import invadem.DrawableAsset;

import java.util.List;

public abstract class MovableAsset extends DrawableAsset {
  protected int xVelocity;
  protected int yVelocity;

  public MovableAsset(PImage img, int x, int y, int width, int height, int health, int xVelocity, int yVelocity) {
    super(img, x, y, width, height, health);
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
  }

  public MovableAsset(List<PImage> allImgs, int x, int y, int width, int height, int health, int xVelocity, int yVelocity) {
    super(allImgs, x, y, width, height, health);
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
  }

  public abstract void tick();

  public int getXVelocity() {return this.xVelocity;}

  public int getYVelocity() {return this.yVelocity;}

  public void setXVelocity(int velocity) {this.xVelocity = velocity;}

  public void setYVelocity(int velocity) {this.yVelocity = velocity;}

  public void changeX() {this.x += this.xVelocity;}

  public void changeY() {this.y += this.yVelocity;}

  public boolean isMoving() {return this.xVelocity > 0 || this.yVelocity > 0;}
}
