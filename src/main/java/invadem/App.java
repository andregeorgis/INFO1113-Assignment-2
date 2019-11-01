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
  private List<Projectile> projectiles;
  private boolean shootProjectile;
  private boolean nextLevel;
  private boolean gameOver;
  private int frameCounter;
  //private int invaderShootCounter;

  public static List<PImage> leftBarrierAllImgs;
  public static List<PImage> topBarrierAllImgs;
  public static List<PImage> rightBarrierAllImgs;
  public static List<PImage> solidBarrierAllImgs;
  public static PImage nextLevelImg;
  public static PImage gameOverImg;
  public PImage projectileImg;

  public App() {
    this.tank = null;
    this.swarm = null;
    this.barriers = new ArrayList<Barrier>();
    this.projectiles = new ArrayList<Projectile>();
    this.shootProjectile = true;
    this.nextLevel = false;
    this.gameOver = false;
    this.frameCounter = 0;

    this.leftBarrierAllImgs = new ArrayList<PImage>();
    this.topBarrierAllImgs = new ArrayList<PImage>();
    this.rightBarrierAllImgs = new ArrayList<PImage>();
    this.solidBarrierAllImgs = new ArrayList<PImage>();
    this.nextLevelImg = null;
    this.gameOverImg = null;
  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));
    this.swarm = new InvaderSwarm(loadImage("invader1.png"));

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

    this.projectileImg = loadImage("projectile.png");
    this.nextLevelImg = loadImage("nextlevel.png");
    this.gameOverImg = loadImage("gameover.png");

  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    //invaderShootCounter++;

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

      for(Barrier barrier : this.barriers) {
        if (!barrier.isBroken()) {
          barrier.draw(this);
        }
      }

      for(Projectile projectile : this.projectiles) {
        projectile.draw(this);
        this.swarm.checkCollisionwithProjectile(projectile);
        for(Barrier barrier : this.barriers) {
          if (!barrier.isBroken()) {
            barrier.checkCollisionwithProjectile(projectile);
          }
        }
      }

      for (int i = 0; i < projectiles.size(); i++) {
        if (projectiles.get(i).isProjectileOutside()) {
          projectiles.remove(i);
          i--;
        }
      }

      if (this.swarm.isDead()) {
        nextLevel();
      }

      if (this.swarm.getBottom() >= BARRIER_TOP - 10) {
        endGame();
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
      this.projectiles.add(new Projectile(projectileImg, this.tank.getX() + this.tank.getWidth()/2, this.tank.getY()));
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
    this.nextLevel = true;
  }

  public void endGame() {
    reset();
    this.gameOver = true;
  }

  public void reset() {
    this.tank.reset();
    this.swarm.reset();
    for (Barrier barrier : this.barriers) {
      barrier.reset();
    }
    this.projectiles.clear();
  }

  public static void main(String[] args) {
    PApplet.main("invadem.App");
  }

}
