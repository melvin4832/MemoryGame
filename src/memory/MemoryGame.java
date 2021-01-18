
package memory;

/**
 * a simple card matching game
 * @author Cody Melvin | melvin4832@gmail.com   
 * @version %I%
 * @since 1.0
 * @see MemoryView.java, MemoryModel.java
 */
public class MemoryGame {
      
    /**
     * creates new match object, thus creating and displaying GUI
     * @param args command line arguments
     */
    public static void main(String[] args){
        MemoryView game = new MemoryView();
        game.newGame();
    }
    
}
