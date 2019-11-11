package invadem;

import invadem.assets.Projectile;

import processing.core.PApplet;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class AssetGroupTest {
  private AssetGroup assetGroupOne;
  private AssetGroup assetGroupTwo;
  private AssetGroup assetGroupThree;

  @Before
  public void setup() {
    this.assetGroupOne = new AssetGroup(1, 1, 10, 3, 1, 2, 5, 3) {
      public void draw(PApplet app) {;}
      public void checkBoundaries() {;}
      public void reset() {;}
      public int checkCollisionWithProjectile(Projectile projectile, boolean friendly) {return 0;}
    };

    this.assetGroupTwo = new AssetGroup(40, 40, 268, 100, 4, 10, 16, 16) {
      public void draw(PApplet app) {;}
      public void checkBoundaries() {;}
      public void reset() {;}
      public int checkCollisionWithProjectile(Projectile projectile, boolean friendly) {return 0;}
    };
  }

  @Test
  public void testAssetGroupConstruction() {
    assertNotNull(this.assetGroupOne);
    assertNotNull(this.assetGroupTwo);
  }

  @Test
  public void testGetterMethods() {
    int xLeft = this.assetGroupOne.getXLeft();
    assertEquals(xLeft, 1);

    int xRight = this.assetGroupOne.getXRight();
    assertEquals(xRight, 11);

    int yTop = this.assetGroupOne.getYTop();
    assertEquals(yTop, 1);

    int yBottom = this.assetGroupOne.getYBottom();
    assertEquals(yBottom, 4);

    int leftCol = this.assetGroupOne.getLeftCol();
    assertEquals(leftCol, 0);

    int rightCol = this.assetGroupOne.getRightCol();
    assertEquals(rightCol, 1);

    int topRow = this.assetGroupOne.getTopRow();
    assertEquals(topRow, 0);

    int bottomRow = this.assetGroupOne.getBottomRow();
    assertEquals(bottomRow, 0);

    int assetWidth = this.assetGroupOne.getAssetWidth();
    assertEquals(assetWidth, 5);

    int assetHeight = this.assetGroupOne.getAssetHeight();
    assertEquals(assetHeight, 3);
  }

  @Test
  public void testSetterMethods() {
    int xLeft = this.assetGroupTwo.getXLeft();
    assertEquals(xLeft, 40);
    this.assetGroupTwo.setXLeft(50);
    xLeft = this.assetGroupTwo.getXLeft();
    assertEquals(xLeft, 50);

    int xRight = this.assetGroupTwo.getXRight();
    assertEquals(xRight, 308);
    this.assetGroupTwo.setXRight(319);
    xRight = this.assetGroupTwo.getXRight();
    assertEquals(xRight, 319);

    int yTop = this.assetGroupTwo.getYTop();
    assertEquals(yTop, 40);
    this.assetGroupTwo.setYTop(50);
    yTop = this.assetGroupTwo.getYTop();
    assertEquals(yTop, 50);

    int yBottom = this.assetGroupTwo.getYBottom();
    assertEquals(yBottom, 140);
    this.assetGroupTwo.setYBottom(162);
    yBottom = this.assetGroupTwo.getYBottom();
    assertEquals(yBottom, 162);

    int leftCol = this.assetGroupTwo.getLeftCol();
    assertEquals(leftCol, 0);
    this.assetGroupTwo.setLeftCol(3);
    leftCol = this.assetGroupTwo.getLeftCol();
    assertEquals(leftCol, 3);

    int rightCol = this.assetGroupTwo.getRightCol();
    assertEquals(rightCol, 9);
    this.assetGroupTwo.setRightCol(6);
    rightCol = this.assetGroupTwo.getRightCol();
    assertEquals(rightCol, 6);

    int topRow = this.assetGroupTwo.getTopRow();
    assertEquals(topRow, 0);
    this.assetGroupTwo.setTopRow(1);
    topRow = this.assetGroupTwo.getTopRow();
    assertEquals(topRow, 1);

    int bottomRow = this.assetGroupTwo.getBottomRow();
    assertEquals(bottomRow, 3);
    this.assetGroupTwo.setBottomRow(2);
    bottomRow = this.assetGroupTwo.getBottomRow();
    assertEquals(bottomRow, 2);
  }
}
