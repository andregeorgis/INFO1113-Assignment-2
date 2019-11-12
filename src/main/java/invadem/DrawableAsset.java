/*
  Responsible for defining how every game asset should be defined in order to be
  displayed correctly.
*/

package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public abstract class DrawableAsset {
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected int health;
  protected PImage img;
  protected List<PImage> allImgs;

  // Construction depending on whether the object requires animation (1 image or
  // multiple images)
  public DrawableAsset(List<PImage> allImgs, int x, int y, int width, int height, int health) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.img = allImgs.get(0);
    this.allImgs = allImgs;
    this.health = health;
  }

  public DrawableAsset(PImage img, int x, int y, int width, int height, int health) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.img = img;
    this.allImgs = new ArrayList<PImage>();
    this.allImgs.add(img);
    this.health = health;
  }

  public abstract void draw(PApplet app);

  // Getter methods
  public int getX() {return this.x;}

  public int getY() {return this.y;}

  public int getWidth() {return this.width;}

  public int getHeight() {return this.height;}

  public int getHealth() {return this.health;}

  public PImage getImage() {return this.img;}

  // Setter methods
  public void setX(int x) {this.x = x;}

  public void setY(int y) {this.y = y;}

  public void setWidth(int width) {this.width = width;}

  public void setHeight(int height) {this.height = height;}

  public void setHealth(int health) {this.health = health;}

  // Change Image directly
  public void changeImage(PImage img) {this.img = img;}

  // Change Image using other stored images - animation purposes
  public void changeImage(int index) {this.img = this.allImgs.get(index);}

  // Asset health methods
  public boolean isAlive() {return this.health > 0;}

  public void loseHealth(int damage) {this.health -= damage;}

}
