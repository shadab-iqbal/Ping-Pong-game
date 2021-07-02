import java.awt.Graphics;

public interface Paddle {
    public void draw(Graphics g); // for  DRAWING the ball and the paddle
    public void move();           // for  MOVING  ||   ||   ||  ||   ||
    public int getY();
}