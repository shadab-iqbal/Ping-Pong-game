import java.awt.Graphics;
import java.awt.Color;

public class HumanPaddle implements Paddle {
    protected double y = 0, yVel = 0;
    protected int player = 0, x = 0;
    private boolean upAccel = false, downAccel = false;
    final static double FRICTION = .88; // this is because when the keyboard key is realeased, the paddle can slowly come to a stop
    
    public HumanPaddle() {
        upAccel = false;
        downAccel = false;
        y = 210; // from where the paddle shows on the applet
        yVel = 0;
        x = 20; // this shows the paddle at the left side of the applet
        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.blue); // choosing the color for the paddle
        g.fillRect(x, (int)y, 20, 80);  // coloring the paddle with the given parameters
    }
    
    public void move() {
        if (upAccel) {
            yVel -= 2; // velocity handling of the paddle
        } else if (downAccel) {
            yVel += 2;
        } else if (!upAccel && !downAccel) { 
            yVel *= FRICTION; // so that the paddle slows down normally
        }
        if (yVel > 5) {
            yVel = 5; // so that the paddle doesnt move too fast
        } else if (yVel < -5) {
            yVel = -5; // same reason ^^
        }
        y += yVel;
        if (y < 0) {
            y = 0; // so that the paddle doesnt go out of the screen of the applet
        } else if (y > 500-80) {
            y = 500-80; // same reason ^^
        }
    }
    
    public int getY() {
        return (int)y;
    }
    
    public void setUpAccel(boolean input) {
        upAccel = input; // if VK pressed, so that the program gets the command for accelerating upwards
    }
    
    public void setDownAccel(boolean input) {
        downAccel = input; // same reason^^
    }
}