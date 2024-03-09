
package brick_breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener{
    private boolean play = false;
    private int score = 0;
    
    private int totalBrick = 21;
    
    private Timer timer;
    private int delay = 8;
    
    private int playerX = 310;
    
    private int ballposX = 120;
    private int ballposY = 350;
    private int balldirX = -2;
    private int balldirY = -3;
    
    private MapGenerator map;
    
    public GamePlay(){
        map = new MapGenerator(7,3);
        requestFocus();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(delay, this);
        timer.start();
        
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);
        
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 562);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(680, 0, 3, 592);
        
        map.draw((Graphics2D)g);
        
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);
        
        g.setColor(Color.GREEN);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString(" " + score, 590, 30);
        if (totalBrick <= 0) {
            play = false;
            balldirX = 0;
            balldirY = 0;
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Win , Score: "+ score, 190,300 );
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to restart", 230, 350);
        }
        
        
        if (ballposY > 570) {
            play = false;
            balldirX = 0;
            balldirY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over, Score: "+ score, 190,300 );
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to restart", 230, 350);
        }
        
        
        
        g.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
       

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (playerX >= 575) {
                playerX = 575;
            }else{
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                balldirX = -2;
                balldirY = -3;
                score = 0;
                totalBrick = 21;
                map = new MapGenerator(7, 3);
                repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (playerX <= 10) {
                playerX = 10;
            }else{
                moveLeft();
            }
        }
            
    }
        
    public void moveRight(){
        play = true;
        playerX += 30;
    }
    public void moveLeft(){
        play = true;
        playerX -= 30;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
        

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
                    balldirY = -balldirY;
            }
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int BrickX = j*map.BrickWidth + 80;
                        int BrickY = i*map.BrickHeight + 50;
                        int BrickHeight = map.BrickWidth ;
                        int BrickWidth = map.BrickHeight;
                        
                        Rectangle rect = new Rectangle(BrickX,BrickY,BrickWidth,BrickHeight);
                        Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                    
                        if (ballRect.intersects(rect)) {
                            totalBrick--;
                            map.setBrickValue(0, i, j);
                            score += 5;
                            if (ballposX <= rect.x || ballposX +1 >= rect.x + rect.width) {
                                balldirX = -balldirX;
                            }else{
                                balldirY = -balldirY;
                            }
                        }
                    }   
                }
            }
            
            
            ballposX += balldirX;
            ballposY += balldirY;
            
            if (ballposX < 0) {
                balldirX = -balldirX;
            }
            if (ballposY < 0) {
                balldirY = -balldirY;
            }
            if (ballposX > 670) {
                balldirX = -balldirX;
            }
            
        }
        repaint();
    }
        
    
}
