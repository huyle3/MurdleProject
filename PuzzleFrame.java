import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;

    public PuzzleFrame(){
        JFrame frame = new JFrame("Murdle Français");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);

        JPanel bigBody = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel(new FlowLayout());

        ////////
        // public void paint(Graphics g){
        //     return 0;
        // }
        //Creates red/green correct/incorrect boxes based on checkAll() boolean array

        JButton testButton = new JButton("Press to test");

        buttons.add(testButton);
        frame.add(bigBody);
        frame.add(buttons);
        frame.setVisible(true);

    }

    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns


    public static void main(String[] args) {
        new PuzzleFrame();
    }

}
