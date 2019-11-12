package invadem.assets.extension;

import invadem.assets.InvaderSwarm;
import invadem.assets.Invader;
import invadem.assets.CurrentProjectiles;
import invadem.assets.Projectile;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class KonamiInvaderSwarm extends InvaderSwarm {

  public final static int PROJECTILE_RATE = 45;

  public KonamiInvaderSwarm(List<PImage> invaderImgs) {
    super(invaderImgs);
    this.invaders = new ArrayList<Invader>();
    List<PImage> slimeImgs = invaderImgs.subList(0, 2);
    List<PImage> teslaImgs = invaderImgs.subList(2, 5);
    List<PImage> zurkonImgs = invaderImgs.subList(5, 7);

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == 0) {
          this.invaders.add(new TeslaInvader(teslaImgs, InvaderSwarm.X_INITIAL + j * (InvaderSwarm.GAP + Invader.WIDTH), InvaderSwarm.Y_INITIAL + i * (InvaderSwarm.GAP + Invader.HEIGHT)));
        } else if (i == 1) {
          this.invaders.add(new ZurkonInvader(zurkonImgs, InvaderSwarm.X_INITIAL + j * (InvaderSwarm.GAP + Invader.WIDTH), InvaderSwarm.Y_INITIAL + i * (InvaderSwarm.GAP + Invader.HEIGHT)));
        } else {
          this.invaders.add(new SlimeInvader(slimeImgs, InvaderSwarm.X_INITIAL + j * (GAP + Invader.WIDTH), InvaderSwarm.Y_INITIAL + i * (InvaderSwarm.GAP + Invader.HEIGHT)));
        }
      }
    }

    this.projectileTimer = 0;
  }

  public int checkCollisionWithProjectile(Projectile projectile) {
    boolean checkCollision = false;
    boolean invaderKilled = false;

    if (this.xLeft < (projectile.getX() + projectile.getWidth()) &&
        this.xRight > projectile.getX() &&
        this.yTop < (projectile.getY() + projectile.getHeight()) &&
        this.yBottom > projectile.getY()) {
      checkCollision = true;
    }

    if (checkCollision) {
      for (Invader invader : this.invaders) {
        if (invader.isAlive() && projectile.checkCollisionWithAsset(invader)) {
          invader.checkHealth();

          if (!invader.isAlive()) {
            invaderKilled = true;
          }
        }
      }
    }

    if (invaderKilled) {
      checkBoundaries();
    }

    return 0;
  }

  public void checkIfKonamiShoot(KonamiCurrentProjectiles projectiles) {
    if (this.projectileTimer == PROJECTILE_RATE) {
      this.projectileTimer = 0;
      shootKonamiProjectile(projectiles);
    }
  }

  public void shootKonamiProjectile(KonamiCurrentProjectiles projectiles) {
    List<Integer> temp = new ArrayList<Integer>();

    for (int i = 0; i < 40; i++) {
      if (this.invaders.get(i).isAlive()) {
        temp.add(i);
      }
    }

    Random rand = new Random();
    int randInt = temp.get(rand.nextInt(temp.size()));
    int projectileX = this.invaders.get(randInt).getX() + this.assetWidth / 2;
    int projectileY = this.invaders.get(randInt).getY() + this.assetHeight;

    if (this.invaders.get(randInt) instanceof ZurkonInvader) {
      projectiles.addProjectile(projectileX - 1, projectileY, false, 'Z');
    } else if (this.invaders.get(randInt) instanceof SlimeInvader) {
      projectiles.addProjectile(projectileX - 2, projectileY, false, 'S');
    } else {
      projectiles.addProjectile(projectileX - 71, projectileY, false, 'T');
      TeslaInvader tempInvader = (TeslaInvader)this.invaders.get(randInt);
      tempInvader.electrify();
    }
  }
}
