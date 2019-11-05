package invadem;

import invadem.assets.Projectile;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public abstract class AssetGroup {
  protected int xLeft;
  protected int xRight;
  protected int yTop;
  protected int yBottom;
  protected int leftCol;
  protected int rightCol;
  protected int topRow;
  protected int bottomRow;
  protected int assetWidth;
  protected int assetHeight;


  public AssetGroup(int x, int y, int width, int height, int numOfRows, int numOfCols, int assetWidth, int assetHeight) {
    this.leftCol = 0;
    this.rightCol = numOfCols - 1;
    this.topRow = 0;
    this.bottomRow = numOfRows - 1;
    this.xLeft = x;
    this.xRight = x + width;
    this.yTop = y;
    this.yBottom = y + height;
    this.assetWidth = assetWidth;
    this.assetHeight = assetHeight;
  }

  public abstract void draw(PApplet app);

  public abstract void checkBoundaries();

  public abstract void reset();

  public abstract int checkCollisionWithProjectile(Projectile projectile);
}
