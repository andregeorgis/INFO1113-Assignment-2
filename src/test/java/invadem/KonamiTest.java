package invadem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class KonamiTest {
  private Konami konami;

  @Before
  public void setup() {
    this.konami = new Konami();
  }

  // Test construction
  @Test
  public void testKonamiConstruction() {
    assertNotNull(this.konami);
  }

  // Test getting current position in sequence
  @Test
  public void testKonamiGetButton() {
    int button = this.konami.getButton();
    assertEquals(button, 0);
  }

  // Test entering the code validly
  @Test
  public void testValidKonamiCode() {
    int button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(38));
    button = this.konami.getButton();
    assertEquals(button, 1);

    assertFalse(this.konami.checkKonami(38));
    button = this.konami.getButton();
    assertEquals(button, 2);

    assertFalse(this.konami.checkKonami(40));
    button = this.konami.getButton();
    assertEquals(button, 3);

    assertFalse(this.konami.checkKonami(40));
    button = this.konami.getButton();
    assertEquals(button, 4);

    assertFalse(this.konami.checkKonami(37));
    button = this.konami.getButton();
    assertEquals(button, 5);

    assertFalse(this.konami.checkKonami(39));
    button = this.konami.getButton();
    assertEquals(button, 6);

    assertFalse(this.konami.checkKonami(37));
    button = this.konami.getButton();
    assertEquals(button, 7);

    assertFalse(this.konami.checkKonami(39));
    button = this.konami.getButton();
    assertEquals(button, 8);

    assertFalse(this.konami.checkKonami(66));
    button = this.konami.getButton();
    assertEquals(button, 9);

    assertFalse(this.konami.checkKonami(65));
    button = this.konami.getButton();
    assertEquals(button, 10);

    assertTrue(this.konami.checkKonami(8));
    button = this.konami.getButton();
    assertEquals(button, 0);
  }

  // Test entering invalid keys from the start
  @Test
  public void checkKonamiInvalidKeys() {
    int button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(73));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(32));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(9));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(192));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(27));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(90));
    button = this.konami.getButton();
    assertEquals(button, 0);
  }

  // Test entering invalid keys partway through the sequence
  @Test
  public void checkKonamiWrongOrder() {
    int button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(192));
    button = this.konami.getButton();
    assertEquals(button, 0);

    this.konami.checkKonami(38);
    this.konami.checkKonami(38);
    button = this.konami.getButton();
    assertEquals(button, 2);

    assertFalse(this.konami.checkKonami(38));
    button = this.konami.getButton();
    assertEquals(button, 0);

    assertFalse(this.konami.checkKonami(8));
    button = this.konami.getButton();
    assertEquals(button, 0);

    this.konami.checkKonami(38);
    this.konami.checkKonami(38);
    this.konami.checkKonami(40);
    this.konami.checkKonami(40);
    this.konami.checkKonami(37);
    this.konami.checkKonami(39);
    this.konami.checkKonami(37);
    button = this.konami.getButton();
    assertEquals(button, 7);

    assertFalse(this.konami.checkKonami(8));
    button = this.konami.getButton();
    assertEquals(button, 0);
  }

  // Test resetting the sequence;
  @Test
  public void testKonamiReset() {
    int button = this.konami.getButton();
    assertEquals(button, 0);

    this.konami.checkKonami(38);
    this.konami.checkKonami(38);
    this.konami.checkKonami(40);

    button = this.konami.getButton();
    assertEquals(button, 3);

    this.konami.reset();
    button = this.konami.getButton();
    assertEquals(button, 0);
  }
}
