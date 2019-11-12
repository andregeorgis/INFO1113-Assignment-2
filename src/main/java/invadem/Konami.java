/*
  Responsible for checking if konami code has been entered.
*/

package invadem;

public class Konami {
  // Konami Code itself
  public static final int[] SEQUENCE = new int[] {38, 38, 40, 40, 37, 39, 37, 39, 66, 65, 8};
  // Position in the sequence
  private int currentButton;

  public Konami() {
    this.currentButton = 0;
  }

  // Get our current position
  public int getButton() {return this.currentButton;}

  // Check if the next button pressed is correct - only returns true if end of
  // sequence is reached
  public boolean checkKonami(int keyCode) {
    if (keyCode == SEQUENCE[this.currentButton]) {
      this.currentButton++;
    } else {
      this.currentButton = 0;
    }

    if (this.currentButton == SEQUENCE.length) {
      this.currentButton = 0;
      return true;
    }

    return false;
  }

  // If wrong button is pressed we start from the beginning of the sequence
  public void reset() {this.currentButton = 0;}
}
