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

  public int getX() {return this.x;}

  public int getY() {return this.y;}

  public int getWidth() {return this.width;}

  public int getHeight() {return this.height;}

  public int getHealth() {return this.health;}

  public PImage getImage() {return this.img;}

  public void changeImage(PImage img) {this.img = img;}

  public void changeImage(int index) {this.img = this.allImgs.get(index);}

  public boolean isAlive() {return this.health > 0;}

  public void loseHealth(int damage) {this.health -= damage;}

}
