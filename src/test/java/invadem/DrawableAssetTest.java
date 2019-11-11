package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class DrawableAssetTest {
  private DrawableAsset assetOne;
  private DrawableAsset assetTwo;
  private DrawableAsset assetThree;
  private DrawableAsset assetFour;

  @Before
  public void setupDrawableAsset() {
    this.assetOne = new DrawableAsset((PImage)null, 1, 2, 3, 4, 5) {
      public void draw(PApplet app) {;}
    };

    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(null);

    this.assetTwo = new DrawableAsset(imgs, 0, 0, 0, 0, 0) {
      public void draw(PApplet app) {;}
    };

    this.assetThree = new DrawableAsset((PImage)null, 5, 4, 3, 2, 1) {
      public void draw(PApplet app) {;}
    };
  }

  @Test
  public void testDrawableAssetConstructionOne() {
    assertNotNull(this.assetOne);
  }

  @Test
  public void testDrawableAssetConstructionTwo() {
    assertNotNull(this.assetTwo);
  }

  @Test
  public void testDrawableAssetGetterMethods() {
    int x = this.assetOne.getX();
    assertEquals(x, 1);

    int y = this.assetOne.getY();
    assertEquals(y, 2);

    int width = this.assetOne.getWidth();
    assertEquals(width, 3);

    int height = this.assetOne.getHeight();
    assertEquals(height, 4);

    int health = this.assetOne.getHealth();
    assertEquals(health, 5);

    PImage img = this.assetOne.getImage();
    assertNull(img);
  }

  @Test
  public void testSetterMethods() {
    int x = this.assetTwo.getX();
    assertEquals(x, 0);
    this.assetTwo.setX(1);
    x = this.assetTwo.getX();
    assertEquals(x, 1);

    int y = this.assetTwo.getY();
    assertEquals(y, 0);
    this.assetTwo.setY(1);
    y = this.assetTwo.getY();
    assertEquals(y, 1);

    int width = this.assetTwo.getWidth();
    assertEquals(width, 0);
    this.assetTwo.setWidth(1);
    width = this.assetTwo.getWidth();
    assertEquals(width, 1);

    int height = this.assetTwo.getHeight();
    assertEquals(height, 0);
    this.assetTwo.setHeight(1);
    height = this.assetTwo.getHeight();
    assertEquals(height, 1);

    int health = this.assetTwo.getHealth();
    assertEquals(health, 0);
    this.assetTwo.setHealth(1);
    health = this.assetTwo.getHealth();
    assertEquals(health, 1);
  }

  @Test
  public void testDrawableAssetIsAliveAndLoseHealth() {
    assertTrue(this.assetThree.isAlive());
    this.assetThree.setHealth(0);
    assertFalse(this.assetThree.isAlive());
    this.assetThree.setHealth(-1);
    assertFalse(this.assetThree.isAlive());

    this.assetThree.loseHealth(1);
    int health = this.assetThree.getHealth();
    assertEquals(health, -2);
    this.assetThree.loseHealth(-10);
    health = this.assetThree.getHealth();
    assertEquals(health, 8);
  }

  @Test
  public void drawableAssetChangingImages() {
    PImage tankImg = new PImage();
    PImage emptyImg = new PImage();
    PImage projectileImg = new PImage();

    List<PImage> imgs = new ArrayList<PImage>();
    imgs.add(tankImg);
    imgs.add(emptyImg);

    this.assetFour = new DrawableAsset(imgs, 0, 0, 0, 0, 0) {
      public void draw(PApplet app) {;}
    };

    PImage currentImage = this.assetFour.getImage();
    assertEquals(currentImage, tankImg);
    this.assetFour.changeImage(projectileImg);
    currentImage = this.assetFour.getImage();
    assertEquals(currentImage, projectileImg);
    this.assetFour.changeImage(1);
    currentImage = this.assetFour.getImage();
    assertEquals(currentImage, emptyImg);
  }

}
