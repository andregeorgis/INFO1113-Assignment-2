package invadem;

public class Konami {
  public static final int[] SEQUENCE = new int[] {38, 38, 40, 40, 37, 39, 37, 39, 66, 65, 8};
  private int currentButton;

  public Konami() {
    this.currentButton = 0;
  }

  public boolean checkKonami(int keyCode) {
    if (keyCode == SEQUENCE[this.currentButton]) {
      this.currentButton++;
    } else {
      this.currentButton = 0;
    }

    System.out.println(this.currentButton);

    if (this.currentButton == SEQUENCE.length) {
      this.currentButton = 0;
      return true;
    }

    return false;
  }

  public void reset() {this.currentButton = 0;}
}
