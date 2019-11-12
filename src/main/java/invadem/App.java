/*
  Responsible for creating the window for the game, and controlling the display.

  EXTENSION:
    - Secret level with new invaders and tank
    - Important bits:
      - Accessed by entering the Konami Code (UP, UP, DOWN, DOWN, LEFT, RIGHT,
        LEFT, RIGHT, B, A, BACKSPACE)
      - Tank now shoots projectiles in a sine wave
      - New enemy projectile are three "electrical spikes" that generate a small
        interval of electricity that can damage tanks and projectiles (but not
        barriers)
        - The electricity turns on and off at a set rate - when it is off it is
          possible for the tank to be left unharmed
        - The spikes themselves can damage barriers as well
      - Can be exited by entering the Konami Code again
*/


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
  // Constants
  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;
  public static final int BARRIER_TOP = 420;
  public static final int HIGHSCORE_INITIAL = 10000;

  // Game Objects
  protected Tank tank;
  protected InvaderSwarm swarm;
  protected List<Barrier> barriers;
  protected CurrentProjectiles projectiles;
  protected boolean nextLevel;
  protected boolean gameOver;
  protected int frameCounter;
  protected int highScore;
  protected int currentScore;

  // Extension - Objects
  protected boolean konami;
  protected Konami konamiChecker;
  protected KonamiTank konamiTank;
  protected KonamiInvaderSwarm konamiInvaderSwarm;
  protected KonamiCurrentProjectiles konamiProjectiles;

  // Game Images and Font
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

  // Extension - Images
  public static PImage konamiTankImg;
  public static List<PImage> konamiInvaderAllImgs;
  public static List<PImage> konamiProjectilesAllImgs;

  public App() {
    // Initialising everything
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
    // Setting up everything
    frameRate(60);
    // Tank
    this.tankImg = loadImage("tank1.png");
    this.tank = new Tank(this.tankImg);

    // Projectiles
    this.projectileImg = loadImage("projectile.png");
    this.powerProjectileImg = loadImage("projectile_lg.png");

    this.projectiles = new CurrentProjectiles(this.projectileImg, this.powerProjectileImg);

    // Invaders
    this.invaderAllImgs.add(loadImage("invader1.png"));
    this.invaderAllImgs.add(loadImage("invader2.png"));
    this.invaderAllImgs.add(loadImage("invader1_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader2_armoured.png"));
    this.invaderAllImgs.add(loadImage("invader1_power.png"));
    this.invaderAllImgs.add(loadImage("invader2_power.png"));
    this.swarm = new InvaderSwarm(this.invaderAllImgs);

    // Barriers
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

    // Transition Displays
    this.nextLevelImg = loadImage("nextlevel.png");
    this.gameOverImg = loadImage("gameover.png");

    // Score Displays
    this.scoreFont = createFont("PressStart2P-Regular.ttf", 10, false);

    // Extension Projectiles
    this.konamiProjectilesAllImgs.add(loadImage("blade_projectile1.png"));
    this.konamiProjectilesAllImgs.add(loadImage("blade_projectile2.png"));
    this.konamiProjectilesAllImgs.add(loadImage("slime_projectile.png"));
    this.konamiProjectilesAllImgs.add(loadImage("zurkon_projectile.png"));
    this.konamiProjectilesAllImgs.add(loadImage("tesla_spike.png"));
    this.konamiProjectilesAllImgs.add(loadImage("tesla_electricity.png"));
    this.konamiProjectiles = new KonamiCurrentProjectiles(this.konamiProjectilesAllImgs);

    // Extension Tank
    this.konamiTankImg = loadImage("konami_tank.png");
    this.konamiTank = new KonamiTank(this.konamiTankImg);

    // Extension Invaders
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
    // Set up background and default font and fill (we want white text)
    background(0);
    textFont(this.scoreFont);
    fill(255);

    // If we are transitioning to next level, only display nextLevelImg
    if (this.nextLevel) {
      this.frameCounter++;
      this.image(this.nextLevelImg, 259, 232, 122, 16);
      if (this.frameCounter == 120) {
        this.nextLevel = false;
        this.frameCounter = 0;
      }
    }

    // If we are transitioning to end of game, only display gameOverImg
    else if (this.gameOver) {
      this.frameCounter++;
      this.image(this.gameOverImg, 264, 232, 122, 16);
      if (this.frameCounter == 120) {
        this.gameOver = false;
        this.frameCounter = 0;
      }
    }

    // If Konami Code has been activated, display extension - "secret level"
    else if (this.konami) {
      // Draw Tank, Invaders, Barriers and Projectiles
      this.konamiTank.draw(this);

      this.konamiInvaderSwarm.draw(this);
      this.konamiInvaderSwarm.checkIfKonamiShoot(this.konamiProjectiles);

      for(Barrier barrier : this.barriers) {
        if (!barrier.isBroken()) {
          barrier.draw(this);
        }
      }

      this.konamiProjectiles.draw(this);

      // Check Collisions and Projectiles Outside Screen
      this.konamiProjectiles.checkCollisions(this.konamiInvaderSwarm, this.konamiTank, this.barriers);
      this.konamiProjectiles.checkIfProjectilesOutside();

      // Check End of Game conditions
      if (this.konamiTank.isDead() || this.konamiInvaderSwarm.getYBottom() >= BARRIER_TOP - 10) {
        endGame();
      }

      if (this.konamiInvaderSwarm.isDead()) {
        endGame();
      }

    }

    // Displaying regular game
    else {
      // Draw Tank, Invaders, Barriers, Projectiles and Score
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

      // Check Collisions and Projectiles Outside Screen
      this.currentScore += this.projectiles.checkCollisions(this.swarm, this.tank, this.barriers);
      this.projectiles.checkIfProjectilesOutside();

      // Check End of Game and Next Level conditions
      if (this.tank.isDead() || this.swarm.getYBottom() >= BARRIER_TOP - 10) {
        endGame();
      }

      if (this.swarm.isDead()) {
        nextLevel();
      }

      // Update high score if necessary
      if (this.currentScore > this.highScore) {
        this.highScore = this.currentScore;
      }
    }
  }

  // Check for key presses
  public void keyPressed(KeyEvent e) {
    // Only check keys if we are in a game
    if (!this.nextLevel && !this.gameOver) {
      // Moving tank left
      if (e.getKeyCode() == 37) {
        this.tank.setLeft(true);
        this.konamiTank.setLeft(true);
      }
      // Moving tank right
      if (e.getKeyCode() == 39) {
        this.tank.setRight(true);
        this.konamiTank.setRight(true);
      }
    }
  }

  // Check for key releases
  public void keyReleased(KeyEvent e) {
    // Only check keys if we are in a game
    if (!this.nextLevel && !this.gameOver) {
      // Stop moving tank left
      if (e.getKeyCode() == 37) {
        this.tank.setLeft(false);
        this.konamiTank.setLeft(false);
      }
      // Stop moving tank right
      if (e.getKeyCode() == 39) {
        this.tank.setRight(false);
        this.konamiTank.setRight(false);
      }
      // Shoot tank projectile
      if (e.getKeyCode() == 32) {
        // Extension Projectile
        if (this.konami) {
          this.konamiProjectiles.addProjectile(this.konamiTank.getX() + this.tank.getWidth()/2, this.tank.getY(), true, 'B');
        }
        // Regular Projectile
        else {
          this.projectiles.addProjectile(this.tank.getX() + this.tank.getWidth()/2, this.tank.getY(), true, false);
        }
      }

      // Extension - to check if the konami code has been entered
      if (konamiChecker.checkKonami(e.getKeyCode())) {
        endGame();
        this.konami = !this.konami;
      }
    }
  }

  // To transition to the next level
  public void nextLevel() {
    reset();
    this.swarm.nextLevel();
    this.nextLevel = true;
  }

  // To restart the game
  public void endGame() {
    reset();
    this.swarm.endGame();
    this.currentScore = 0;
    this.gameOver = true;

    // Extension - restart the extension too
    this.konamiChecker.reset();
    konamiReset();
  }

  // Resets the normal game to initial conditions (except objects that depend on
  // whether it is a new level or new game)
  public void reset() {
    this.tank.reset();
    for (Barrier barrier : this.barriers) {
      barrier.reset();
    }
    this.projectiles.reset();
  }

  //Extension - reset (no next level implemented)
  public void konamiReset() {
    this.konamiTank.reset();
    this.konamiInvaderSwarm.endGame();
    this.konamiProjectiles.reset();
  }

  // Create App
  public static void main(String[] args) {
    PApplet.main("invadem.App");
  }

}
