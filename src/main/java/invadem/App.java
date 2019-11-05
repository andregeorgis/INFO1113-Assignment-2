package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import invadem.assets.*;

import java.util.List;
import java.util.ArrayList;

public class App extends PApplet {

  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;
  public static final int BARRIER_TOP = 420;

  private Tank tank;
  private InvaderSwarm swarm;
  private List<Barrier> barriers;
  private CurrentProjectiles projectiles;
  private boolean shootProjectile;
  private boolean nextLevel;
  private boolean gameOver;
  private int frameCounter;

  public static List<PImage> leftBarrierAllImgs;
  public static List<PImage> topBarrierAllImgs;
  public static List<PImage> rightBarrierAllImgs;
  public static List<PImage> solidBarrierAllImgs;
  public static List<PImage> invaderAllImgs;
  public static PImage nextLevelImg;
  public static PImage gameOverImg;
  public PImage projectileImg;

  public App() {
    this.tank = null;
    this.swarm = null;
    this.barriers = new ArrayList<Barrier>();
    this.projectiles = null;
    this.shootProjectile = true;
    this.nextLevel = false;
    this.gameOver = false;
    this.frameCounter = 0;

    this.leftBarrierAllImgs = new ArrayList<PImage>();
    this.topBarrierAllImgs = new ArrayList<PImage>();
    this.rightBarrierAllImgs = new ArrayList<PImage>();
    this.solidBarrierAllImgs = new ArrayList<PImage>();
    this.invaderAllImgs = new ArrayList<PImage>();
    this.nextLevelImg = null;
    this.gameOverImg = null;
  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));

    this.projectileImg = loadImage("projectile.png");

    this.projectiles = new CurrentProjectiles(this.projectileImg);

    this.invaderAllImgs.add(loadImage("invader1.png"));
    this.invaderAllImgs.add(loadImage("invader2.png"));
    this.invaderAllImgs.add(loadImage("invader1_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader2_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader1_power.png"));
    this.invaderAllImgs.add(loadImage("invader2_power.png"));
    this.swarm = new InvaderSwarm(invaderAllImgs, this.projectileImg);

    this.leftBarrierAllImgs.add(loadImage("barrier_left1.png"));
    this.leftBarrierAllImgs.add(loadImage("barrier_left2.png"));
    this.leftBarrierAllImgs.add(loadImage("barrier_left3.png"));

    this.topBarrierAllImgs.add(loadImage("barrier_top1.png"));
    this.topBarrierAllImgs.add(loadImage("barrier_top2.png"));
    this.topBarrierAllImgs.add(loadImage("barrier_top3.png"));

    this.rightBarrierAllImgs.add(loadImage("barrier_right1.png"));
    this.rightBarrierAllImgs.add(loadImage("barrier_right2.png"));
    this.rightBarrierAllImgs.add(loadImage("barrier_right3.png"));

    this.solidBarrierAllImgs.add(loadImage("barrier_solid1.png"));
    this.solidBarrierAllImgs.add(loadImage("barrier_solid2.png"));
    this.solidBarrierAllImgs.add(loadImage("barrier_solid3.png"));

    this.barriers.add(new Barrier(leftBarrierAllImgs, topBarrierAllImgs, rightBarrierAllImgs, solidBarrierAllImgs, 308, BARRIER_TOP));
    this.barriers.add(new Barrier(leftBarrierAllImgs, topBarrierAllImgs, rightBarrierAllImgs, solidBarrierAllImgs, 200, BARRIER_TOP));
    this.barriers.add(new Barrier(leftBarrierAllImgs, topBarrierAllImgs, rightBarrierAllImgs, solidBarrierAllImgs, 416, BARRIER_TOP));

    this.nextLevelImg = loadImage("nextlevel.png");
    this.gameOverImg = loadImage("gameover.png");
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    background(0);

    if (this.nextLevel) {
      this.frameCounter++;
      this.image(this.nextLevelImg, 259, 232, 122, 16);
      if (this.frameCounter == 120) {
        this.nextLevel = false;
        this.frameCounter = 0;
      }
    } else if (this.gameOver) {
      this.frameCounter++;
      this.image(this.gameOverImg, 264, 232, 122, 16);
      if (this.frameCounter == 120) {
        this.gameOver = false;
        this.frameCounter = 0;
      }
    } else {
      this.tank.draw(this);

      this.swarm.draw(this);
      this.swarm.checkIfShoot(this.projectiles);

      for(Barrier barrier : this.barriers) {
        if (!barrier.isBroken()) {
          barrier.draw(this);
        }
      }

      this.projectiles.draw(this);
      this.projectiles.checkCollisions(this.swarm, this.tank, this.barriers);
      this.projectiles.checkIfProjectilesOutside();

      if (this.tank.isDead() || this.swarm.getBottom() >= BARRIER_TOP - 10) {
        endGame();
      }

      if (this.swarm.isDead()) {
        nextLevel();
      }
    }
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 37) {
      this.tank.setLeft(true);
    }

    if (e.getKeyCode() == 39) {
      this.tank.setRight(true);
    }

    if (e.getKeyCode() == 32 && this.shootProjectile == true) {
      this.projectiles.addProjectile(this.tank.getX() + this.tank.getWidth()/2, this.tank.getY(), true);
      this.shootProjectile = false;
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == 37) {
      this.tank.setLeft(false);
    }

    if (e.getKeyCode() == 39) {
      this.tank.setRight(false);
    }

    if (e.getKeyCode() == 32) {
      this.shootProjectile = true;
    }
  }

  public void nextLevel() {
    reset();
    this.swarm.nextLevel();
    this.nextLevel = true;
  }

  public void endGame() {
    reset();
    this.swarm.endGame();
    this.gameOver = true;
  }

  public void reset() {
    this.tank.reset();
    for (Barrier barrier : this.barriers) {
      barrier.reset();
    }
    this.projectiles.reset();
  }

  public static void main(String[] args) {
    PApplet.main("invadem.App");
  }

}
