package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import invadem.assets.Tank;

public class TankTest {

   @Test
   public void testTankConstruction() {
       Tank tank = new Tank(null);
       assertNotNull(tank);
   }

//    @Test
//    public void testTankProjectile() {
//        Tank tank = new Tank(null, 0, 0);
//        assertNotNull(tank.fire());
//    }

//    @Test
//    public void testTankIsNotDead() {
//        Tank tank = new Tank(null, 0, 0);
//        assertEquals(true, tank.isDead());
//    }

}
