package invadem.assets;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.MovableAsset;

public class Invader extends MovableAsset {
  public static final int WIDTH = 16;
  public static final int HEIGHT = 16;
  public static final int HEALTH_INITIAL = 1;
  public static final int X_VELOCITY_INITIAL = 1;
  public static final int Y_VELOCITY_INITIAL = 0;

  private int stepCounter;
  private boolean movingDown;
  private boolean movingRight;
  private boolean movingLeft;
  private boolean makeStep;
  private boolean alive;

  public Invader(PImage img, int x, int y) {
    super(img, x, y, WIDTH, HEIGHT, HEALTH_INITIAL, X_VELOCITY_INITIAL, Y_VELOCITY_INITIAL);
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

  public void checkState() {
    if (this.stepCounter == 30) {
      this.movingDown = true;
      setYVelocity(1);
      setXVelocity(0);
      this.stepCounter = 0;
    } else if (this.movingDown && this.stepCounter == 8) {
      if (this.movingRight) {
        setXVelocity(-1);
        setYVelocity(0);
        this.movingRight = false;
        this.movingDown = false;
        this.movingLeft = true;
      } else if (this.movingLeft) {
        setXVelocity(1);
        setYVelocity(0);
        this.movingRight = true;
        this.movingDown = false;
        this.movingLeft = false;
      }
      this.stepCounter = 0;
    }
  }

  public void takeStep() {stepCounter++;}

  public boolean isAlive() {return this.alive;}

  public void kill() {
    changeImage(null);
    this.alive = false;
  }
}
