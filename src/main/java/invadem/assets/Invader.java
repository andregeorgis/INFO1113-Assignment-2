package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.Movable;

public class Invader {
  private int x;
  private int y;
  private int xVelocity;
  private int yVelocity;
  private int health;

  private PImage img;

  public final int WIDTH = 16;
  public final int HEIGHT = 16;

  public Invader(PImage img, int x, int y) {
    this.img = img;
    this.x = x;
    this.y = y;
    this.xVelocity = 0;
    this.yVelocity = 0;
    this.health = 1;
  }

  public void draw(PApplet app) {
    app.image(this.img, this.x, this.y, this.WIDTH, this.HEIGHT);
  }
}
