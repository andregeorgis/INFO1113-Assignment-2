/*
  Responsible for defining behaviour of individual Invaders.
*/

package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;

import java.util.List;

public class Invader extends MovableAsset {
  // Constants
  public static final int WIDTH = 16;
  public static final int HEIGHT = 16;
  public static final int HEALTH_INITIAL = 1;
  public static final int X_VELOCITY_INITIAL = 1;
  public static final int Y_VELOCITY_INITIAL = 0;

  // Variables to track animation
  protected int stepCounter;
  protected boolean movingDown;
  protected boolean movingRight;
  protected boolean movingLeft;
  protected boolean makeStep;
  // Check if invader is alive
  protected boolean alive;

  public Invader(List<PImage> allImgs, int x, int y) {
    super(allImgs, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
    this.stepCounter = 0;
    this.movingDown = false;
    this.movingRight = true;
    this.movingLeft = false;
    this.makeStep = true;
    this.alive = true;
  }

  public void draw(PApplet app) {
    if (getImage() != null) {
      app.image(this.img, this.x, this.y, this.width, this.height);
      tick();
    }
  }

  // Tick every 2nd frame
  public void tick() {
    if (this.makeStep) {
      checkState();
      changeX();
      changeY();
      takeStep();
      this.makeStep = false;
    } else {
      this.makeStep = true;
    }
  }

  // Little decision tree for how the invader should move (also changes Images
  // for animation)
  public void checkState() {
    if (this.stepCounter == 30) {
      this.movingDown = true;
      setYVelocity(1);
      setXVelocity(0);
      changeImage(1);
      this.stepCounter = 0;
    } else if (this.movingDown && this.stepCounter == 8) {
      this.movingDown = false;
      changeImage(0);
      if (this.movingRight) {
        setXVelocity(-1);
        setYVelocity(0);
        this.movingRight = false;
        this.movingLeft = true;
      } else if (this.movingLeft) {
        setXVelocity(1);
        setYVelocity(0);
        this.movingRight = true;
        this.movingLeft = false;
      }
      this.stepCounter = 0;
    }
  }

  // Taking step != Every frame (every 2nd frame)
  public void takeStep() {stepCounter++;}

  // Checking health and status methods
  public boolean isAlive() {return this.alive;}

  public void checkHealth() {
    if (this.health <= 0) {
      kill();
    }
  }

  // If invader is killed we change the image to null - temporarily disable it
  // rather than remove from memory
  public void kill() {
    changeImage(null);
    this.alive = false;
  }

  // Reset all attributes
  public void reset(int x, int y) {
    changeImage(0);
    this.alive = true;
    this.x = x;
    this.y = y;
    this.xVelocity = X_VELOCITY_INITIAL;
    this.yVelocity = Y_VELOCITY_INITIAL;
    this.stepCounter = 0;
    this.movingDown = false;
    this.movingRight = true;
    this.movingLeft = false;
    this.makeStep = true;
    this.alive = true;
    this.health = 1;
  }

  // Getter Methods for testing purposes
  public boolean isMovingRight() {return this.movingRight;}

  public boolean isMovingDown() {return this.movingDown;}

  public boolean isMovingLeft() {return this.movingLeft;}

  public boolean isMoving() {return this.makeStep;}

}
