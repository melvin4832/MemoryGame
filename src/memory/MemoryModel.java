
package memory;

import java.util.ArrayList;
import java.util.Random;

/**
 * model for "card" placement
 * @author Cody Melvin | melvin4832@gmail.com   
 * @version %I%
 * @since 1.0
 * @see MemoryGame.java, MemoryView.java
 */
public class MemoryModel {
    
    ArrayList<Integer> possibleCards;
    int[][] cardValues = new int[4][4];
    
    Random rng = new Random();
    
    /**
     *  assigns random values to "cards" from a list of possible values
     */
    public void initialize(){
        possibleCards = new ArrayList<Integer>();
        int chosenIndex;
        int size;
        
        possibleCards.add(0);
        possibleCards.add(0);
        possibleCards.add(1);
        possibleCards.add(1);
        possibleCards.add(2);
        possibleCards.add(2);
        possibleCards.add(3);
        possibleCards.add(3);
        possibleCards.add(4);
        possibleCards.add(4);
        possibleCards.add(5);
        possibleCards.add(5);
        possibleCards.add(6);
        possibleCards.add(6);
        possibleCards.add(7);
        possibleCards.add(7);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                size = possibleCards.size();
                
                if(size > 0){
                    chosenIndex = rng.nextInt(size);
                    
                    cardValues[i][j] = possibleCards.get(chosenIndex);
                   
                    possibleCards.remove(chosenIndex);
                }
            }
        }
    }
    
    /**
     * provides the value of a given "card"
     * @param x the row that the given "card" is in
     * @param y the column that the given "card" is in
     * @return an integer representing the value of the given "card"
     */
    public int getCard(int x, int y){
        return cardValues[x][y];
    }
    
    
}
