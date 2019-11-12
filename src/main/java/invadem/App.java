package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import invadem.assets.*;
import invadem.assets.extension.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class App extends PApplet {

  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;
  public static final int BARRIER_TOP = 420;
  public static final int HIGHSCORE_INITIAL = 10000;

  private Tank tank;
  private InvaderSwarm swarm;
  private List<Barrier> barriers;
  private CurrentProjectiles projectiles;
  private boolean nextLevel;
  private boolean gameOver;
  private int frameCounter;
  private int highScore;
  private int currentScore;

  // Extension
  private boolean konami;
  private Konami konamiChecker;
  private KonamiTank konamiTank;
  private KonamiInvaderSwarm konamiInvaderSwarm;
  private KonamiCurrentProjectiles konamiProjectiles;

  public static PImage tankImg;
  public static List<PImage> leftBarrierAllImgs;
  public static List<PImage> topBarrierAllImgs;
  public static List<PImage> rightBarrierAllImgs;
  public static List<PImage> solidBarrierAllImgs;
  public static List<PImage> invaderAllImgs;
  public static PImage nextLevelImg;
  public static PImage gameOverImg;
  public PImage projectileImg;
  public PImage powerProjectileImg;
  public PFont scoreFont;

  // Extension
  public static PImage konamiTankImg;
  public static List<PImage> konamiInvaderAllImgs;
  public static List<PImage> konamiProjectilesAllImgs;

  public App() {
    this.tank = null;
    this.swarm = null;
    this.barriers = new ArrayList<Barrier>();
    this.projectiles = null;
    this.nextLevel = false;
    this.gameOver = false;
    this.frameCounter = 0;
    this.highScore = HIGHSCORE_INITIAL;
    this.currentScore = 0;

    this.leftBarrierAllImgs = new ArrayList<PImage>();
    this.topBarrierAllImgs = new ArrayList<PImage>();
    this.rightBarrierAllImgs = new ArrayList<PImage>();
    this.solidBarrierAllImgs = new ArrayList<PImage>();
    this.invaderAllImgs = new ArrayList<PImage>();
    this.nextLevelImg = null;
    this.gameOverImg = null;
    this.scoreFont = null;

    // Extension
    this.konami = false;
    this.konamiChecker = new Konami();
    this.konamiTank = null;
    this.konamiInvaderSwarm = null;
    this.konamiProjectiles = null;

    this.konamiTankImg = null;
    this.konamiInvaderAllImgs = new ArrayList<PImage>();
    this.konamiProjectilesAllImgs = new ArrayList<PImage>();
  }

  public void setup() {
    frameRate(60);
    this.tankImg = loadImage("tank1.png");
    this.tank = new Tank(this.tankImg);

    this.projectileImg = loadImage("projectile.png");
    this.powerProjectileImg = loadImage("projectile_lg.png");

    this.projectiles = new CurrentProjectiles(this.projectileImg, this.powerProjectileImg);

    this.invaderAllImgs.add(loadImage("invader1.png"));
    this.invaderAllImgs.add(loadImage("invader2.png"));
    this.invaderAllImgs.add(loadImage("invader1_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader2_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader1_power.png"));
    this.invaderAllImgs.add(loadImage("invader2_power.png"));
    this.swarm = new InvaderSwarm(this.invaderAllImgs);

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

    this.barriers.add(new Barrier(this.leftBarrierAllImgs, this.topBarrierAllImgs, this.rightBarrierAllImgs, this.solidBarrierAllImgs, 308, BARRIER_TOP));
    this.barriers.add(new Barrier(this.leftBarrierAllImgs, this.topBarrierAllImgs, this.rightBarrierAllImgs, this.solidBarrierAllImgs, 200, BARRIER_TOP));
    this.barriers.add(new Barrier(this.leftBarrierAllImgs, this.topBarrierAllImgs, this.rightBarrierAllImgs, this.solidBarrierAllImgs, 416, BARRIER_TOP));

    this.nextLevelImg = loadImage("nextlevel.png");
    this.gameOverImg = loadImage("gameover.png");

    this.scoreFont = createFont("PressStart2P-Regular.ttf", 10, false);

    // Extension
    this.konamiProjectilesAllImgs.add(loadImage("blade_projectile1.png"));
    this.konamiProjectilesAllImgs.add(loadImage("blade_projectile2.png"));
    this.konamiProjectilesAllImgs.add(loadImage("slime_projectile.png"));
    this.konamiProjectilesAllImgs.add(loadImage("zurkon_projectile.png"));
    this.konamiProjectilesAllImgs.add(loadImage("tesla_spike.png"));
    this.konamiProjectilesAllImgs.add(loadImage("tesla_electricity.png"));
    this.konamiProjectiles = new KonamiCurrentProjectiles(this.konamiProjectilesAllImgs);

    this.konamiTankImg = loadImage("konami_tank.png");
    this.konamiTank = new KonamiTank(this.konamiTankImg, this.konamiProjectilesAllImgs.subList(0, 2));

    this.konamiInvaderAllImgs.add(loadImage("slime_invader1.png"));
    this.konamiInvaderAllImgs.add(loadImage("slime_invader2.png"));
    this.konamiInvaderAllImgs.add(loadImage("tesla_invader1.png"));
    this.konamiInvaderAllImgs.add(loadImage("tesla_invader2.png"));
    this.konamiInvaderAllImgs.add(loadImage("tesla_invader3.png"));
    this.konamiInvaderAllImgs.add(loadImage("zurkon_invader1.png"));
    this.konamiInvaderAllImgs.add(loadImage("zurkon_invader2.png"));

    this.konamiInvaderSwarm = new KonamiInvaderSwarm(this.konamiInvaderAllImgs);
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    background(0);
    textFont(this.scoreFont);
    fill(255);

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
    } else if (this.konami) {
      this.konamiTank.draw(this);

      this.konamiInvaderSwarm.draw(this);
      this.konamiInvaderSwarm.checkIfKonamiShoot(this.konamiProjectiles);

      for(Barrier barrier : this.barriers) {
        if (!barrier.isBroken()) {
          barrier.draw(this);
        }
      }

      this.konamiProjectiles.draw(this);

      this.konamiProjectiles.checkCollisions(this.konamiInvaderSwarm, this.konamiTank, this.barriers);
      this.konamiProjectiles.checkIfProjectilesOutside();

      if (this.konamiTank.isDead() || this.konamiInvaderSwarm.getYBottom() >= BARRIER_TOP - 10) {
        endGame();
      }

      if (this.konamiInvaderSwarm.isDead()) {
        endGame();
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

      text("SCORE: " + Integer.toString(this.currentScore), 40, 20);
      text("HIGH SCORE: " + Integer.toString(this.highScore), 430, 20);

      this.currentScore += this.projectiles.checkCollisions(this.swarm, this.tank, this.barriers);
      this.projectiles.checkIfProjectilesOutside();

      if (this.tank.isDead() || this.swarm.getYBottom() >= BARRIER_TOP - 10) {
        endGame();
      }

      if (this.swarm.isDead()) {
        nextLevel();
      }

      if (this.currentScore > this.highScore) {
        this.highScore = this.currentScore;
      }
    }
  }

  public void keyPressed(KeyEvent e) {
    if (!this.nextLevel && !this.gameOver) {
      if (e.getKeyCode() == 37) {
        this.tank.setLeft(true);
        this.konamiTank.setLeft(true);
      }

      if (e.getKeyCode() == 39) {
        this.tank.setRight(true);
        this.konamiTank.setRight(true);
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    if (!this.nextLevel && !this.gameOver) {
      if (e.getKeyCode() == 37) {
        this.tank.setLeft(false);
        this.konamiTank.setLeft(false);
      }

      if (e.getKeyCode() == 39) {
        this.tank.setRight(false);
        this.konamiTank.setRight(false);
      }

      if (e.getKeyCode() == 32) {
        if (this.konami) {
          this.konamiProjectiles.addProjectile(this.konamiTank.getX() + this.tank.getWidth()/2, this.tank.getY(), true, 'B');
        } else {
          this.projectiles.addProjectile(this.tank.getX() + this.tank.getWidth()/2, this.tank.getY(), true, false);
        }
      }

      // Extension
      if (konamiChecker.checkKonami(e.getKeyCode())) {
        endGame();
        this.konami = !this.konami;
      }
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
    this.currentScore = 0;
    this.gameOver = true;

    // Extension
    this.konamiChecker.reset();
    konamiReset();
  }

  public void reset() {
    this.tank.reset();
    for (Barrier barrier : this.barriers) {
      barrier.reset();
    }
    this.projectiles.reset();
  }

  //Extension

  public void konamiReset() {
    this.konamiTank.reset();
    this.konamiInvaderSwarm.endGame();
    this.konamiProjectiles.reset();
  }

  public static void main(String[] args) {
    PApplet.main("invadem.App");
  }

}
