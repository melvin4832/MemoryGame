
package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GUI and logic for game
 * @author Cody Melvin | melvin4832@gmail.com   
 * @version %I%
 * @since 1.0
 * @see MemoryGame.java, MemoryModel.java
 */
public class MemoryView {
    
    private JFrame frame;
    
    private JPanel panel;
    private JPanel cardPanel;
    private JPanel botPanel;
    
    private JLabel moves;
    
    private JButton newGame;
    private JButton first;
    private JButton second;
    private JButton[][] cards;
    
    private Dimension dimension;
    
    private int moveCount;
    private int comparing;
    private int firstRow;
    private int seconRow;
    private int firstCol;
    private int seconCol;
    private int matches;
    
    private MemoryController controller = new MemoryController();
    private MemoryModel model = new MemoryModel();
    
    /**
     * "constructs" this GUI
     */
    public MemoryView(){
        
        //components and such
        frame = new JFrame();
        
        panel = new JPanel();
        cardPanel = new JPanel();
        botPanel = new JPanel();
        
        moves = new JLabel();
        
        newGame = new JButton();
        cards = new JButton[4][4];
        
        dimension = new Dimension(150,30);
        
        moveCount = 0;
                
        //botPanel stuff
        botPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        moves.setText("MOVES: 0");
        moves.setBackground(Color.white);
        moves.setOpaque(true);
        moves.setPreferredSize(dimension);
        
        newGame.setText("NEW GAME");
        newGame.setOpaque(true);
        newGame.addActionListener(controller);
        newGame.setPreferredSize(dimension);
        
        botPanel.setLayout(new FlowLayout());
        botPanel.add(moves);
        botPanel.add(newGame);

        //cardPanelstuff      
        cardPanel.setLayout(new GridLayout(4,4));
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                cards[i][j] = new JButton();
                
                cards[i][j].setText("Card");
                cards[i][j].setHorizontalAlignment(JLabel.CENTER);
                cards[i][j].setPreferredSize(new Dimension(160, 160));
                cards[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                cards[i][j].addActionListener(controller);
                
                cardPanel.add(cards[i][j]);
            }
        }
        
        //panel stuff
        panel.setLayout(new BorderLayout());
        panel.add(cardPanel, BorderLayout.NORTH);
        panel.add(botPanel, BorderLayout.SOUTH);
        
        //frame stuff
        frame.add(panel);
        frame.setTitle("Match Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);  //centers frame on screen
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
    
    /**
     * starts a new game by re-initializing the values of the "cards"
     */
    public void newGame(){
        model.initialize();
        
        comparing = 0;
        moveCount = 0;
        matches = 0;
        
        moves.setText("MOVES: " + moveCount);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                cards[i][j].setBackground(Color.gray);
                cards[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                cards[i][j].setEnabled(true);
            }
        }
    }
    
    /**
     * compares selected "cards"
     * @param card represents a "card"
     * @param row represents the 'x' coordinate of the given JButton
     * @param col represents the 'y' coordinate of the given JButton
     */
    public void checkCard(JButton card, int row, int col){
        Color cardColour = Color.GRAY;
        
        switch(model.getCard(row, col)){
            case 0:
                cardColour = Color.BLUE;
                break;
            case 1:
                cardColour = Color.CYAN;
                break;
            case 2:
                cardColour = Color.GREEN;
                break;
            case 3:
                cardColour = Color.MAGENTA;
                break;
            case 4:
                cardColour = Color.ORANGE;
                break;
            case 5:
                cardColour = Color.PINK;
                break;
            case 6:
                cardColour = Color.RED;
                break;
            case 7:
                cardColour = Color.YELLOW;
                break;
        }
        
        if(comparing == 0){
            first = card;
            firstRow = row;
            firstCol = col;
            
            first.setBackground(cardColour);
        }
        else if(comparing == 1){
            second = card;
            seconRow = row;
            seconCol = col;
            
            second.setBackground(cardColour);
        }
        
        comparing++;
        
        //if two "cards" are "selected"
        if(comparing == 2){
            moveCount++;
            
            if(model.getCard(firstRow, firstCol) == model.getCard(seconRow, seconCol) && first != second){
                matches++;
                first.setEnabled(false);
                second.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(frame, "No match there.");
                
                first.setBackground(Color.gray);
                second.setBackground(Color.gray);
                first.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                second.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            }
         
            comparing = 0;
        }
        
        if(matches == 8){
            JOptionPane.showMessageDialog(frame, "You did it!");
            newGame();
        }
    }
    
    private class MemoryController implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == newGame){
                newGame();
            }
            else{
                JButton thisButton = (JButton) e.getSource();
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 4; j++){
                        if(thisButton == cards[i][j]){
                            checkCard(thisButton, i, j);
                            moves.setText("MOVES: " + moveCount);
                        }
                    }
                }
            }
        }
        
    }
    
}
