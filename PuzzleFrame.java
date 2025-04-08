import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
//main is here! waow

public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;
    JButton quiButton = new JButton("Press to Qui");
    JButton quoiButton = new JButton("Press to Quoi");
    JButton ouButton = new JButton("Press to Ou");
    JButton pourquoiButton = new JButton("Press to Porquoi");
    JButton soumetrreButton = new JButton("Press to Soumettre");

    public PuzzleFrame(){
        JFrame frame = new JFrame("Murdle Français");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);

        JPanel bigBody = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel(new BoxLayout());

        ////////
        // public void paint(Graphics g){
        //     return 0;
        // }
        //Creates red/green correct/incorrect boxes based on checkAll() boolean array

        
        addButtonFrame(buttons);
        

        frame.add(bigBody, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);

    }

    public void addButtonFrame(JPanel buttons){  
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(quiButton);
        buttons.add(quoiButton);
        buttons.add(ouButton);
        buttons.add(pourquoiButton);
        buttons.add(soumetrreButton);
        
    }

    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns


    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = new PuzzleFrame();
        //Game game = new Game();
        //game.getPuzzleNum();
    }

}
