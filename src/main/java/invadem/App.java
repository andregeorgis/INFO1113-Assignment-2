package invadem;

import processing.core.PApplet;
import invadem.assets.Tank;

public class App extends PApplet {

  private Tank tank;

  public boolean left = false;
  public boolean right = false;

  public App() {
    this.tank = null;
  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));
  }

  public void settings() {
    size(640, 480);
  }

  public void draw() {
    background(0);
    this.tank.draw(this, left, right);
  }

  public void keyPressed() {
    if (key == CODED) {
      if (keyCode == LEFT) {
        left = true;
      }

      if (keyCode == RIGHT) {
        right = true;
      }
    }
  }

  public void keyReleased() {
    if (key == CODED) {
      if (keyCode == LEFT) {
        left = false;
      }

      if (keyCode == RIGHT) {
        right = false;
      }
    }
  }

  public static void main(String[] args) {
    PApplet.main("invadem.App");
  }

}
