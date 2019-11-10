package invadem;

import invadem.MovableAsset;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovableAssetTest {
  private MovableAsset assetOne;
  private MovableAsset assetTwo;
  private MovableAsset assetThree;
  private MovableAsset assetFour;

  @Before
  public void setup() {
    this.assetOne = new MovableAsset((PImage)null, 0, 0, 0, 0, 0, 1, 2) {
      public void draw(PApplet app) {;}
      public void tick() {;}
    };

    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);

    this.assetTwo = new MovableAsset(imgs, 0, 0, 0, 0, 0, 2, 3) {
      public void draw(PApplet app) {;}
      public void tick() {;}
    };

    this.assetThree = new MovableAsset((PImage)null, 1, -3, 0, 0, 0, -2, 8) {
      public void draw(PApplet app) {;}
      public void tick() {;}
    };

    this.assetFour = new MovableAsset((PImage)null, 0, 0, 0, 0, 0, 0, 0) {
      public void draw(PApplet app) {;}
      public void tick() {;}
    };
  }

  @Test
  public void testMovableAssetConstructionOne() {
    assertNotNull(this.assetOne);
  }

  @Test
  public void testMovableAssetConstructionTwo() {
    assertNotNull(this.assetTwo);
  }

  @Test
  public void testMovableAssetGetterMethods() {
    int xVelocity = this.assetOne.getXVelocity();
    assertEquals(xVelocity, 1);

    int yVelocity = this.assetOne.getYVelocity();
    assertEquals(yVelocity, 2);
  }

  @Test
  public void testMovableAssetSetterMethods() {
    int xVelocity = this.assetTwo.getXVelocity();
    assertEquals(xVelocity, 2);
    this.assetTwo.setXVelocity(4);
    xVelocity = this.assetTwo.getXVelocity();
    assertEquals(xVelocity, 4);

    int yVelocity = this.assetTwo.getYVelocity();
    assertEquals(yVelocity, 3);
    this.assetTwo.setYVelocity(5);
    yVelocity = this.assetTwo.getYVelocity();
    assertEquals(yVelocity, 5);
  }

  @Test
  public void testMovableAssetChangeMethods() {
    int x = this.assetThree.getX();
    assertEquals(x, 1);
    this.assetThree.changeX();
    x = this.assetThree.getX();
    assertEquals(x, -1);

    int y = this.assetThree.getY();
    assertEquals(y, -3);
    this.assetThree.changeY();
    y = this.assetThree.getY();
    assertEquals(y, 5);
  }

  @Test
  public void testMovableAssetIsMoving() {
    assertFalse(this.assetFour.isMoving());
    this.assetFour.setXVelocity(1);
    assertTrue(this.assetFour.isMoving());
    this.assetFour.setYVelocity(1);
    assertTrue(this.assetFour.isMoving());
    this.assetFour.setXVelocity(0);
    assertTrue(this.assetFour.isMoving());
  }
}
