import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math; 

public class Ball {
    private double x = 0.0, y = 0.0, xVel = 0.0, yVel = 0.0;
    
    public Ball() {
        x = 350;  // ball starts from the centre of the applet
        y = 250;  // same reason 
        xVel = getRandomSpeed() * getRandomDirection(); // multiplying results in starting off the ball in random direction at random speed 
        yVel = getRandomSpeed() * getRandomDirection(); // same reason ^^
    }
    
    public double getRandomSpeed() { // this is for random speed
        return (Math.random() *3 + 1); // the value will return an integer from 2 - 5 
    }
    
    public int getRandomDirection() {
        int rand = (int)(Math.random() * 2); // if int is casted the maximum limit should be made (+1)
        if (rand == 1) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public void checkPaddleCollision(Paddle p1, Paddle p2) {
        /* paddle starts from 20 units far from the edge of left side of applet, and the width of the paddle is 20,
         and the radius of the ball is 10. so (20+20+10)=50. So we have to check collision within 50 units from the left edge of applet*/         
        if (x <= 50) { 
            if (y >= p1.getY() && y <= p1.getY()+80) { // this is to check if the ball hits the paddle accoding to the y-axis of the paddle
                xVel = -xVel; // if hits, the ball bounces back in the x-axis
            } 
        }
        if (x >= 650) { // this is for right edge, (700-20-20-10)=650. 
            if (y >= p2.getY() && y <= p2.getY()+80) { // this is for the other paddle
                xVel = -xVel;
            }
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white); // choosing the ball color 
        g.fillOval((int)x-10, (int)y-10, 20, 20); // subtracted (20/2)=10 so that the drawing starts from the centre of the circle, 
    }                                             // not from the top-left of the x and y position
    
    public void move() {
        x += xVel; // changing the position of the ball according to its velocity
        y += yVel; // same reason ^^
        if (y > 490) { // this is for bottom of applet. Ball radius is 10. so if (500-10)=490 is crossed by the ball,
            yVel = -yVel; //  the ball bounces back completely
        }
        if (y < 10){  // this is for bottom of applet. Ball radius is 10. so if (0+10)=10 is crossed by the ball,
            yVel = -yVel; //  the ball bounces back completely
        }
    }
    
    public int getY() {
        return (int)y;
    }
    
    public int getX() {
        return (int)x;
    }
}