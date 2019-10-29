package invadem;

import processing.core.PApplet;
import invadem.assets.*;

import java.util.List;
import java.util.ArrayList;

public class App extends PApplet {

  private Tank tank;
  private List<Invader> invaders;

  public boolean left = false;
  public boolean right = false;

  public App() {
    this.tank = null;
    this.invaders = new ArrayList<Invader>();
  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));
    this.invaders.add(new Invader(loadImage("invader1.png"), 180, 20));
  }

  public void settings() {
    size(640, 480);
  }

  public void draw() {
    background(0);
    this.tank.draw(this, left, right);
    for(Invader invader : this.invaders) {
      invader.draw(this);
    }
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
