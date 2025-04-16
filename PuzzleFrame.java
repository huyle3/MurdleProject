import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
//main is here! waow
//here be dragons


public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;
    private Game game;
    private int currentPuzzle;
//creates stuffs

//dropdown stuff
    String[] options1;
    String[] options2; 
    String[] options3;  
    String[] options4; 
    String[] nums;


    JComboBox<String> quiButton;
    JComboBox<String> quoiButton; 
    JComboBox<String> ouButton; 
    JComboBox<String> pourquoiButton; 
    JButton soumetrreButton = new JButton("Soumettre");

    JComboBox<Integer> numButton;

    JPanel button1 = new JPanel();
    JPanel button2 = new JPanel();
    JPanel button3 = new JPanel();
    JPanel button4 = new JPanel();

    DrawingPanel panel1 = new DrawingPanel(50, 50);
    DrawingPanel panel2 = new DrawingPanel(50, 50);
    DrawingPanel panel3 = new DrawingPanel(50, 50);
    DrawingPanel panel4 = new DrawingPanel(50, 50);

    Graphics g1 = panel1.getGraphics();
    Graphics g2 = panel2.getGraphics();
    Graphics g3 = panel3.getGraphics();
    Graphics g4 = panel4.getGraphics();


    public PuzzleFrame(){

        game = new Game();
        currentPuzzle = 1;
        options1 = game.getPuzzle(currentPuzzle).getWho();
        options2 = game.getPuzzle(currentPuzzle).getWhat();
        options3 = game.getPuzzle(currentPuzzle).getWhere();
        options4 = game.getPuzzle(currentPuzzle).getWhy();
        Integer[] nums = new Integer[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }


        quiButton = new JComboBox<>(options1);
        quoiButton = new JComboBox<>(options2);
        ouButton = new JComboBox<>(options3);
        pourquoiButton = new JComboBox<>(options4);

        numButton = new JComboBox<>(nums);
        
        JFrame frame = new JFrame("Murdle Français");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);

        JPanel bigBody = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel();
        BoxLayout bl = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(bl);
        BoxLayout bl2 = new BoxLayout(buttons, BoxLayout.X_AXIS);

        //created the panels & whatnot

        button1.setLayout(bl2);
        button2.setLayout(bl2);
        button3.setLayout(bl2);
        button4.setLayout(bl2);

        
        addButtonFrame(buttons);
        

        frame.add(bigBody, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);

    }


    public void addButtonFrame(JPanel buttons){  
        //created button panel, w/ the drop down menues
        button1.setLayout(new BoxLayout(button1, BoxLayout.X_AXIS));
        button1.add(quiButton);
        button1.add(Box.createHorizontalGlue());
        //rectangle here
        button1.add(panel1);
        panel1.setColor(Color.GRAY);
        

        button2.setLayout(new BoxLayout(button2, BoxLayout.X_AXIS));
        button2.add(quoiButton);
        button2.add(Box.createHorizontalGlue());
        //rectangle here
        button2.add(panel2);
        panel2.setColor(Color.GRAY);
        

        button3.setLayout(new BoxLayout(button3, BoxLayout.X_AXIS));
        button3.add(ouButton);
        button3.add(Box.createHorizontalGlue());
        //rectangle here
        button3.add(panel3);
        panel3.setColor(Color.GRAY);
        

        button4.setLayout(new BoxLayout(button4, BoxLayout.X_AXIS));
        button4.add(pourquoiButton);
        button4.add(Box.createHorizontalGlue());
        //rectangle here
        button4.add(panel4);
        panel4.setColor(Color.GRAY);
        


        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        buttons.add(numButton, Component.LEFT_ALIGNMENT);
        // thing to numbutton
        ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // //quiButton = new JComboBox<>(game.getPuzzle(Integer.parseInt(numButton.getSelectedItem().toString())).getWho());
                // quoiButton = new JComboBox<> (game.getPuzzle(Integer.parseInt(numButton.getSelectedItem().toString())).getWhat());
                // ouButton = new JComboBox<>(game.getPuzzle(Integer.parseInt(numButton.getSelectedItem().toString())).getWhere());
                // pourquoiButton = new JComboBox<>(game.getPuzzle(Integer.parseInt(numButton.getSelectedItem().toString())).getWhy());

                quiButton.removeAllItems();
                quoiButton.removeAllItems();
                ouButton.removeAllItems();
                pourquoiButton.removeAllItems();

                for () {
                    quiButton.addItem();

                }
            }
        };

        buttons.add(button1, Component.LEFT_ALIGNMENT);
        buttons.add(button2, Component.LEFT_ALIGNMENT);
        buttons.add(button3, Component.LEFT_ALIGNMENT);
        buttons.add(button4, Component.LEFT_ALIGNMENT);
        buttons.add(soumetrreButton, Component.LEFT_ALIGNMENT);

        //add check box

        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                checkAnswers();
            }
        };
        soumetrreButton.addActionListener(submitListener);
        
    }

    private void checkAnswers() {
        // define correct answers
        String[] userAnswer;
        DrawingPanel[] panelsList = {panel1, panel2, panel3, panel4};
        if (Integer.parseInt(numButton.getSelectedItem().toString()) == 4){ 
            userAnswer = new String[]{
                quiButton.getSelectedItem().toString(),
                quoiButton.getSelectedItem().toString(),
                ouButton.getSelectedItem().toString(),
                pourquoiButton.getSelectedItem().toString()
            };
        }else{
            userAnswer = new String[]{
                quiButton.getSelectedItem().toString(),
                quoiButton.getSelectedItem().toString(),
                ouButton.getSelectedItem().toString()
            };
        }
        boolean[] checkedAnswerArr = game.checkPuzzle(currentPuzzle, userAnswer);  
        for (int i = 0; i < checkedAnswerArr.length; i++) {// it would loop through {true, false, false, true} for example
            if (checkedAnswerArr[i]) {
                panelsList[i].setColor(Color.GREEN);
            } else {
                panelsList[i].setColor(Color.RED);
            }
            //panelsList[i].repaint();
        }
        
    }


    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns

    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = new PuzzleFrame();
    }
}

