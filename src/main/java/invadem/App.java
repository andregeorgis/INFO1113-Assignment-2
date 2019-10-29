package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.assets.*;

import java.util.List;
import java.util.ArrayList;

public class App extends PApplet {

  private Tank tank;
  private List<Invader> invaders;
  private List<Barrier> barriers;

  public boolean left = false;
  public boolean right = false;

  public App() {
    this.tank = null;
    this.invaders = new ArrayList<Invader>();
    this.barriers = new ArrayList<Barrier>();

  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));
    this.invaders.add(new Invader(loadImage("invader1.png"), 180, 20));

    List<PImage> topRow = new ArrayList<PImage>();
    topRow.add(loadImage("barrier_left1.png"));
    topRow.add(loadImage("barrier_top1.png"));
    topRow.add(loadImage("barrier_right1.png"));
    List<PImage> middleRow = new ArrayList<PImage>();
    middleRow.add(loadImage("barrier_solid1.png"));
    middleRow.add(loadImage("barrier_solid1.png"));
    List<PImage> bottomRow = new ArrayList<PImage>();
    bottomRow.add(loadImage("barrier_solid1.png"));
    bottomRow.add(loadImage("barrier_solid1.png"));

    this.barriers.add(new Barrier(topRow, middleRow, bottomRow, 308, 400));
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

    for(Barrier barrier : this.barriers) {
      barrier.draw(this);
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
