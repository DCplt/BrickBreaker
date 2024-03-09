
package brick_breaker;

import javax.swing.JFrame;


public class main {
    public static void main(String[] args) {
        JFrame obj = new JFrame("BrickBreaker");
        GamePlay game = new GamePlay();
        obj.setSize(700,600);
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.addKeyListener(game);
        obj.add(game);
       
        
        
    }
}
