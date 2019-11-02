package invadem.assets;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class CurrentProjectiles {

  private List<Projectile> friendlyProjectiles;
  private List<Projectile> enemyProjectiles;
  private PImage projectileImg;

  public CurrentProjectiles(PImage projectileImg) {
    this.friendlyProjectiles = new ArrayList<Projectile>();
    this.enemyProjectiles = new ArrayList<Projectile>();
    this.projectileImg = projectileImg;
  }

  public void draw(PApplet app) {
    for (Projectile projectile : this.friendlyProjectiles) {
      projectile.draw(app);
    }

    for (Projectile projectile : this.enemyProjectiles) {
      projectile.draw(app);
    }
  }

  public void addProjectile(int x, int y, boolean friendly) {
    if (friendly) {
      this.friendlyProjectiles.add(new Projectile(projectileImg, x, y));
    } else {
      Projectile projectile = new Projectile(projectileImg, x, y);
      projectile.setYVelocity(1);
      this.enemyProjectiles.add(projectile);
    }
  }

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

  public void checkCollisions(InvaderSwarm swarm, Tank tank, List<Barrier> barriers) {
    for(Projectile projectile : this.friendlyProjectiles) {
      swarm.checkCollisionWithProjectile(projectile);
      for (Barrier barrier : barriers) {
        if (!barrier.isBroken()) {
          barrier.checkCollisionWithProjectile(projectile);
        }
      }
    }

    for(Projectile projectile : this.enemyProjectiles) {
      projectile.checkCollisionWithAsset(tank);
      for (Barrier barrier : barriers) {
        if (!barrier.isBroken()) {
          barrier.checkCollisionWithProjectile(projectile);
        }
      }
      for (Projectile otherProjectile : this.friendlyProjectiles) {
        projectile.checkCollisionWithAsset(otherProjectile);
      }
    }
  }

  public void reset() {
    this.friendlyProjectiles.clear();
    this.enemyProjectiles.clear();
  }
}
