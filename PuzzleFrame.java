import javax.swing.*;

public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;

    public static void main(String[] args) {
        return 0;
    }

    public PuzzleFrame(Game game){
        bigBody = new JPanel();
        buttons = new ButtonPanel();

        //cannibalized from filterwindow
        setTitle("Murdle Français");
		setSize(750, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setPreferredSize(getSize()); 
		pack();
		setVisible(true);

        ////////
        public void paint(Graphics g){

        }
        //Creates red/green correct/incorrect boxes based on checkAll() boolean array

    }

    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns

}
