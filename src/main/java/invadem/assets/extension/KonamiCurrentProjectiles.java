/*
  Responsible for distinguishing between CurrentProjectiles and
  KonamiCurrentProjectiles - projectiles in the Extension "secret level".
*/

package invadem.assets.extension;

import invadem.assets.CurrentProjectiles;
import invadem.assets.Projectile;
import invadem.assets.PowerProjectile;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class KonamiCurrentProjectiles extends CurrentProjectiles {
  // Hold all images needed
  private List<PImage> bladeProjectilesImg;
  private List<PImage> teslaProjectilesImg;
  private PImage slimeProjectileImg;
  private PImage zurkonProjectileImg;

  public KonamiCurrentProjectiles(List<PImage> imgs) {
    super(null, null);
    this.bladeProjectilesImg = imgs.subList(0, 2);
    this.slimeProjectileImg = imgs.get(2);
    this.zurkonProjectileImg = imgs.get(3);
    this.teslaProjectilesImg = imgs.subList(4, 6);
  }

  // Change the way a projectile is added
  // char type - responsible for determining what type of Projectile is added
  public void addProjectile(int x, int y, boolean friendly, char type) {
    Projectile projectile;

    // If friendly a BladeProjectile is added
    if (friendly) {
      projectile = new BladeProjectile(this.bladeProjectilesImg, x, y);
      this.friendlyProjectiles.add(projectile);
    } else {
      // Shot by a SlimeInvader so a SlimeProjectile is added
      if (type == 'S') {
        projectile = new SlimeProjectile(this.slimeProjectileImg, x, y);
        projectile.setYVelocity(1);
        this.enemyProjectiles.add(projectile);
      }
      // Shot by a ZurkonInvader so a ZurkonProjectile is added
      else if (type == 'Z') {
        projectile = new ZurkonProjectile(this.zurkonProjectileImg, x, y);
        projectile.setYVelocity(1);
        this.enemyProjectiles.add(projectile);
      }
      // Shot by a TeslaInvader so a TeslaCoil is added
      else if (type == 'T') {
        projectile = new TeslaCoil(this.teslaProjectilesImg, x, y);
        projectile.setYVelocity(1);
        this.enemyProjectiles.add(projectile);
      }
    }
  }
}
