
package brick_breaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int map[][];
    public int BrickWidth;
    public int BrickHeight;
    
    public MapGenerator(int col , int row){
        map = new int[row][col];
        for(int i = 0;i < map.length; i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = 1;
            }
        }
        BrickWidth = 540/col;
        BrickHeight = 150/row;
    }
    
    public void draw(Graphics2D g){
        for(int i = 0;i < map.length; i++){
            for(int j=0;j<map[0].length;j++){
                if (map[i][j] > 0) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j*BrickWidth+80, i*BrickHeight+50, BrickWidth, BrickHeight);
                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.white);
                    g.drawRect(j*BrickWidth+80, i*BrickHeight+50, BrickWidth, BrickHeight);
                    
                }
            }
         }
    }
    public void setBrickValue(int value,int row, int col){
        map[row][col] = value;
    }
}
