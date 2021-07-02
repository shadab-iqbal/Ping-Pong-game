import java.awt.Color;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.*;

public class Tennis extends Applet implements Runnable, KeyListener {
    Scanner sc = new Scanner(System.in);
    //protected int playerNo = sc.nextInt();
    final int WIDTH = 700, HEIGHT = 500;
    boolean gameStarted = false; // wait for user command to start the game
    boolean rematch = false; // for re-matching
    
    Thread thread;
    HumanPaddle p1;
    AnotherHumanPaddle p2;
    Ball b1;
    Graphics gfx; // creating an off-screen buffer
    Image img;
    
    public void init() {
        this.resize(WIDTH, HEIGHT); // sizing the applet window 
        this.addKeyListener(this);
        gameStarted = false;
        
        p1 = new HumanPaddle(); 
        p2 = new AnotherHumanPaddle(); 
        b1 = new Ball();
        img = createImage(WIDTH, HEIGHT); // creating an image with the given height and width
        gfx = img.getGraphics(); // getting the graphics from the image
        thread = new Thread(this);
        
        thread.start(); // starts the run method
    }
    
    public void paint(Graphics g) {
        gfx.setColor(Color.green); // setting background color of the applet
        gfx.fillRect(0, 0, WIDTH, HEIGHT); // filling the background with Black
        
        if (b1.getX() < -10 || b1.getX() > 710) { // this is to check if the ball goes completely out of the screen at left or right side
            gfx.setColor(Color.red); // choosing the color of the string
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            gfx.drawString("GAME OVER", 230, 240); // displaying game over at the centre of the screen
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            gfx.drawString("Press SPACE to play again", 235, 300); // displaying play again 
            if (rematch) {
                b1 = new Ball(); // initializing the ball position again for rematch
            }
        } else {
            p1.draw(gfx);
            p2.draw(gfx);
            b1.draw(gfx);
            gfx.setColor(Color.white); // setting color for the middle line
            gfx.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT); // drawing the middle line
            gfx.drawOval((WIDTH/2)-100, (HEIGHT/2)-100, 200, 200); // drawing the oval line
            rematch = false;
        }
        
        if (!gameStarted) {
            gfx.setColor(Color.black);
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 40)); // setting font type, size etc
            gfx.drawString("Tennis", 290, 100);
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
            gfx.drawString("Press ENTER to begin..", 255, 145);
        }
        
        g.drawImage(img, 0, 0, this); // passing the created image and an image observer
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    public void run() {
        for (;;) { // an infinite loop
            
            if (gameStarted) { // checking if the user has commanded for the game to start
                p1.move(); // move function for the paddle
                p2.move();
                b1.move(); // move function for the ball
                b1.checkPaddleCollision(p1, p2);
            }
            
            repaint(); // calls the update method 
            try {
                Thread.sleep(10); // waits for 10 millseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) { // VK for virtual keyboard
            p1.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) { // VK for virtual keyboard
            p2.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            p2.setDownAccel(true);
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            rematch = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            p2.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            p2.setDownAccel(false);
        } 
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
}