package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.Movable;

public class Tank implements Movable {
  private int x;
  private int y;
  private int width;
  private int height;
  private int x_velocity;
  private int y_velocity;
  private int health;

  private PImage img;

  public Tank(PImage img) {
    this.img = img;
    this.x = 309;
    this.y = 456;
    this.width = 22;
    this.height = 14;
    this.x_velocity = 1;
    this.y_velocity = 0;
    this.health = 3;
  }

  public void draw(PApplet app) {
    app.image(img, x, y, width, height);
  }

  public void tick() {;}

  public int getX() {return this.x;}

  public int getY() {return this.y;}
}
