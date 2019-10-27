package invadem;

import invadem.assets.Tank;

import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;

public class App extends PApplet {
    final int WIDTH = 640;
    final int HEIGHT = 480;

    private Tank tank;

    public App() {
        ;
    }

    public void setup() {
        frameRate(60);

        this.tank = new Tank(loadImage("tank1.png"));
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() {
        background(0);
        this.tank.draw(this);
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
