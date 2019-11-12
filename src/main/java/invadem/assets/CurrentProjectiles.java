/*
  Responsible for dealing with all projectiles shot by both the tank and the
  invaders.
*/

package invadem.assets;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class CurrentProjectiles {
  // Split friendly (Tank) and enemy (Invader) projectiles
  protected List<Projectile> friendlyProjectiles;
  protected List<Projectile> enemyProjectiles;
  // Store all images
  private PImage projectileImg;
  private PImage powerProjectileImg;

  public CurrentProjectiles(PImage projectileImg, PImage powerProjectileImg) {
    this.friendlyProjectiles = new ArrayList<Projectile>();
    this.enemyProjectiles = new ArrayList<Projectile>();
    this.projectileImg = projectileImg;
    this.powerProjectileImg = powerProjectileImg;
  }

  public void draw(PApplet app) {
    // Draw each projectile separately
    for (Projectile projectile : this.friendlyProjectiles) {
      projectile.draw(app);
    }

    for (Projectile projectile : this.enemyProjectiles) {
      projectile.draw(app);
    }
  }

  // Getter Methods
  public List<Projectile> getFriendlyProjectiles() {return this.friendlyProjectiles;}

  public List<Projectile> getEnemyProjectiles() {return this.enemyProjectiles;}

  // Add Projectile
  // boolean friendly - determines whether it is shot by tank or Invader
  // boolean power - determines whether projectile is a PowerProjectile
  public void addProjectile(int x, int y, boolean friendly, boolean power) {
    if (friendly) {
      this.friendlyProjectiles.add(new Projectile(projectileImg, x, y));
    } else {
      Projectile projectile;
      if (power) {
        projectile = new PowerProjectile(powerProjectileImg, x, y);
      } else {
        projectile = new Projectile(projectileImg, x, y);
      }
      projectile.setYVelocity(1);
      this.enemyProjectiles.add(projectile);
    }
  }

  // Remove projectile if it is outside screen
  public void checkIfProjectilesOutside() {
    for (int i = 0; i < this.friendlyProjectiles.size(); i++) {
      if (this.friendlyProjectiles.get(i).isProjectileOutside()) {
        this.friendlyProjectiles.remove(i);
        i--;
      }
    }

    for (int i = 0; i < this.enemyProjectiles.size(); i++) {
      if (this.enemyProjectiles.get(i).isProjectileOutside()) {
        this.enemyProjectiles.remove(i);
        i--;
      }
    }
  }

  // Check if the projectiles collide with every other game object
  public int checkCollisions(InvaderSwarm swarm, Tank tank, List<Barrier> barriers) {
    // Keep track of score from dead invaders
    int scoreChange = 0;

    // For friendly projectiles
    for(Projectile projectile : this.friendlyProjectiles) {
      // Check collision with invaders
      scoreChange += swarm.checkCollisionWithProjectile(projectile);

      // Check collision with barriers
      for (Barrier barrier : barriers) {
        if (!barrier.isBroken()) {
          barrier.checkCollisionWithProjectile(projectile);
        }
      }
    }

    // For enemy projectiles
    for(Projectile projectile : this.enemyProjectiles) {
      // Check collision with tank
      projectile.checkCollisionWithAsset(tank);

      // Check collision with barriers
      for (Barrier barrier : barriers) {
        if (!barrier.isBroken()) {
          barrier.checkCollisionWithProjectile(projectile);
        }
      }

      // Check collision with friendly projectiles
      for (Projectile otherProjectile : this.friendlyProjectiles) {
        if (!otherProjectile.isDud()) {
          projectile.checkCollisionWithAsset(otherProjectile);
        }

        if (!otherProjectile.isAlive()) {
          otherProjectile.hit();
        }
      }
    }

    // Return score obtained
    return scoreChange;
  }

  // Reset projectiles by removing all
  public void reset() {
    this.friendlyProjectiles.clear();
    this.enemyProjectiles.clear();
  }

}
