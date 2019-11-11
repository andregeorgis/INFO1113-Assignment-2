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


  public AssetGroup(int x, int y, int widthInitial, int heightInitial, int numOfRows, int numOfCols, int assetWidth, int assetHeight) {
    this.leftCol = 0;
    this.rightCol = numOfCols - 1;
    this.topRow = 0;
    this.bottomRow = numOfRows - 1;
    this.xLeft = x;
    this.xRight = x + widthInitial;
    this.yTop = y;
    this.yBottom = y + heightInitial;
    this.assetWidth = assetWidth;
    this.assetHeight = assetHeight;
  }

  public abstract void draw(PApplet app);

  public abstract void checkBoundaries();

  public abstract void reset();

  public abstract int checkCollisionWithProjectile(Projectile projectile, boolean friendly);

  public int getXLeft() {return this.xLeft;}

  public int getXRight() {return this.xRight;}

  public int getYTop() {return this.yTop;}

  public int getYBottom() {return this.yBottom;}

  public int getLeftCol() {return this.leftCol;}

  public int getRightCol() {return this.rightCol;}

  public int getTopRow() {return this.topRow;}

  public int getBottomRow() {return this.bottomRow;}

  public int getAssetWidth() {return this.assetWidth;}

  public int getAssetHeight() {return this.assetHeight;}

  public void setXLeft(int xLeft) {this.xLeft = xLeft;}

  public void setXRight(int xRight) {this.xRight = xRight;}

  public void setYTop(int yTop) {this.yTop = yTop;}

  public void setYBottom(int yBottom) {this.yBottom = yBottom;}

  public void setLeftCol(int leftCol) {this.leftCol = leftCol;}

  public void setRightCol(int rightCol) {this.rightCol = rightCol;}

  public void setTopRow(int topRow) {this.topRow = topRow;}

  public void setBottomRow(int bottomRow) {this.bottomRow = bottomRow;}
}
