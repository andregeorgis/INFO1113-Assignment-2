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

  private Tank tank;
  private InvaderSwarm swarm;
  private List<Barrier> barriers;
  private List<Projectile> projectiles;
  private boolean shootProjectile;
  //private int invaderShootCounter;

  public List<PImage> LEFT_BARRIER_ALL;
  public List<PImage> TOP_BARRIER_ALL;
  public List<PImage> RIGHT_BARRIER_ALL;
  public List<PImage> SOLID_BARRIER_ALL;

  public PImage projectileImage;

  public App() {
    this.tank = null;
    this.swarm = null;
    this.barriers = new ArrayList<Barrier>();
    this.projectiles = new ArrayList<Projectile>();

    this.shootProjectile = true;

    this.LEFT_BARRIER_ALL = new ArrayList<PImage>();
    this.TOP_BARRIER_ALL = new ArrayList<PImage>();
    this.RIGHT_BARRIER_ALL = new ArrayList<PImage>();
    this.SOLID_BARRIER_ALL = new ArrayList<PImage>();
  }

  public void setup() {
    frameRate(60);
    this.tank = new Tank(loadImage("tank1.png"));
    this.swarm = new InvaderSwarm(loadImage("invader1.png"));

    this.LEFT_BARRIER_ALL.add(loadImage("barrier_left1.png"));
    this.LEFT_BARRIER_ALL.add(loadImage("barrier_left2.png"));
    this.LEFT_BARRIER_ALL.add(loadImage("barrier_left3.png"));

    this.TOP_BARRIER_ALL.add(loadImage("barrier_top1.png"));
    this.TOP_BARRIER_ALL.add(loadImage("barrier_top2.png"));
    this.TOP_BARRIER_ALL.add(loadImage("barrier_top3.png"));

    this.RIGHT_BARRIER_ALL.add(loadImage("barrier_right1.png"));
    this.RIGHT_BARRIER_ALL.add(loadImage("barrier_right2.png"));
    this.RIGHT_BARRIER_ALL.add(loadImage("barrier_right3.png"));

    this.SOLID_BARRIER_ALL.add(loadImage("barrier_solid1.png"));
    this.SOLID_BARRIER_ALL.add(loadImage("barrier_solid2.png"));
    this.SOLID_BARRIER_ALL.add(loadImage("barrier_solid3.png"));

    this.barriers.add(new Barrier(LEFT_BARRIER_ALL, TOP_BARRIER_ALL, RIGHT_BARRIER_ALL, SOLID_BARRIER_ALL, 308, 420));
    this.barriers.add(new Barrier(LEFT_BARRIER_ALL, TOP_BARRIER_ALL, RIGHT_BARRIER_ALL, SOLID_BARRIER_ALL, 200, 420));
    this.barriers.add(new Barrier(LEFT_BARRIER_ALL, TOP_BARRIER_ALL, RIGHT_BARRIER_ALL, SOLID_BARRIER_ALL, 416, 420));

    projectileImage = loadImage("projectile.png");
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    //invaderShootCounter++;

    background(0);

    this.tank.draw(this);

    this.swarm.draw(this);

    for(Barrier barrier : this.barriers) {
      barrier.draw(this);
    }

    for(Projectile projectile : this.projectiles) {
      projectile.draw(this);
      this.swarm.checkCollisionwithProjectile(projectile);
    }

    for (int i = 0; i < projectiles.size(); i++) {
      if (projectiles.get(i).isProjectileOutside()) {
        projectiles.remove(i);
        i--;
      }
    }

    if (this.swarm.numOfInvaders() == 0) {
      nextLevel();
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
      this.projectiles.add(new Projectile(projectileImage, this.tank.getX() + this.tank.getWidth()/2, this.tank.getY()));
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
