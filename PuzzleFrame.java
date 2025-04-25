
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.*;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

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
     // Construct path to the file

    // Load the font 


    JComboBox<String> quiButton;
    JComboBox<String> quoiButton; 
    JComboBox<String> ouButton; 
    JComboBox<String> pourquoiButton; 
    JButton soumetrreButton = new JButton("Soumettre");
    JButton effacerButton = new JButton("Effacer");

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

    JLabel statusImageLabel;
    JTextArea statusTextArea;
    JPanel statusPanel;


    public PuzzleFrame(){
        Font font = null;
        try {
            File fontFile = new File("jmh_typewriter/JMH Typewriter-Black.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // Register the font with the GraphicsEnvironment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            // Use the font
            font = new Font(customFont.getName(), Font.PLAIN, 12);
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
        }

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
        frame.setFont(font);
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


        try {
            URL startImageUrl = new URL("https://cdn-icons-png.flaticon.com/512/1800/1800204.png");
            ImageIcon startIcon = new ImageIcon(startImageUrl);
            statusImageLabel = new JLabel(startIcon);
        } catch (MalformedURLException e) {
            statusImageLabel = new JLabel("bad url"); // fallback text/icon if URL fails
        }

        statusTextArea = new JTextArea("Faites votre choix."); //text box stuff
        statusTextArea.setEditable(false);
        statusTextArea.setOpaque(false);
        statusTextArea.setFont(font);
        statusTextArea.setLineWrap(true);

        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.add(statusImageLabel);
        statusPanel.add(Box.createVerticalStrut(10)); //verticle gap
        statusPanel.add(statusTextArea);

        bigBody.add(statusPanel);
        
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

        //adds number label
        JLabel numLabel = new JLabel("Numéro");
        numLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(numLabel);

        buttons.add(numButton, Component.LEFT_ALIGNMENT);
        // thing to numbutton
        ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                quiButton.removeAllItems();
                quoiButton.removeAllItems();
                ouButton.removeAllItems();
                pourquoiButton.removeAllItems();
                
                currentPuzzle = Integer.parseInt(numButton.getSelectedItem().toString());
                for (int i = 0; i < game.getPuzzle(currentPuzzle).getWhat().length; i++) {
                    quoiButton.addItem(game.getPuzzle(currentPuzzle).getWhat()[i]);
                    quiButton.addItem(game.getPuzzle(currentPuzzle).getWho()[i]);
                    ouButton.addItem(game.getPuzzle(currentPuzzle).getWhere()[i]);
                    if (currentPuzzle >= 50){
                        pourquoiButton.addItem(game.getPuzzle(currentPuzzle).getWhy()[i]);
                    }                    
                }
                panel1.setColor(Color.GRAY);
                panel2.setColor(Color.GRAY);
                panel3.setColor(Color.GRAY);
                panel4.setColor(Color.GRAY);
                updateStatus("start"); //resets image on right
            }
        };
        numButton.addActionListener(numListener);

        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                panel1.setColor(Color.GRAY); //set all buttons back to gray
                panel2.setColor(Color.GRAY);
                panel3.setColor(Color.GRAY);
                panel4.setColor(Color.GRAY);
                updateStatus("start"); //resets image on right
                quoiButton.setSelectedIndex(0);
                quiButton.setSelectedIndex(0);
                ouButton.setSelectedIndex(0);                
                if (currentPuzzle >= 50){ 
                    pourquoiButton.setSelectedIndex(0);
                }
            }
        };
        effacerButton.addActionListener(clearListener);

        //adds qui label
        JLabel quiLabel = new JLabel("Qui");
        quiLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(quiLabel);
        buttons.add(button1, Component.LEFT_ALIGNMENT);

        //adds quoi label
        JLabel quoiLabel = new JLabel("Quoi");
        quoiLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(quoiLabel);
        buttons.add(button2, Component.LEFT_ALIGNMENT);

        //adds ou label
        JLabel ouLabel = new JLabel("Ou");
        ouLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(ouLabel);
        buttons.add(button3, Component.LEFT_ALIGNMENT);

        //adds pourquoi label
        JLabel pqLabel = new JLabel("Pourquoi");
        pqLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(pqLabel);
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

        //add clear box: NOTE (Need help to do this, plan is to make a 101st option in the answeChoices list and have that be the default w/ the "make your choice")
        buttons.add(effacerButton, Component.LEFT_ALIGNMENT);

        
    }

    private void checkAnswers() {
        // define correct answers
        String[] userAnswer;
        DrawingPanel[] panelsList = {panel1, panel2, panel3, panel4};
        if (game.getPuzzle(currentPuzzle).getWhat().length == 5){ 
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

        //stuff below is for changing the right image
        boolean allCorrect = true;
        for (int i = 0; i < checkedAnswerArr.length; i++) {
            if (checkedAnswerArr[i]) {
                panelsList[i].setColor(Color.GREEN);
            } else {
                panelsList[i].setColor(Color.RED);
                allCorrect = false;
            }
        }

        if (allCorrect) {
            updateStatus("correct");
        } else {
            updateStatus("wrong");
        }
        
    }
    
    private void updateStatus(String statusType) {
        try {
            switch (statusType) { //quicker if-else
                case "start":
                    statusImageLabel.setIcon(new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/1800/1800204.png"))); //the base image
                    statusTextArea.setText("Faites votre choix.");
                    break;
                case "correct":
                    statusImageLabel.setIcon(new ImageIcon(new URL("https://media1.tenor.com/m/viIU4ICp1N8AAAAd/dance.gif"))); //happy image :)
                    statusTextArea.setText("Félicitations ! Vous êtes un super détective !");
                    break;
                case "wrong":
                    statusImageLabel.setIcon(new ImageIcon(new URL("https://media.istockphoto.com/id/543347592/vector/why-god-why-emoticon.jpg?s=612x612&w=0&k=20&c=gukkiZ3mBsm4qWZaZu_KLbwFhWdteeME0cLoEbo4yMw="))); //sad image:(
                    statusTextArea.setText("Malheureusement, ce n’est pas la bonne solution. Essayez encore.");
                    break;
            }
        } catch (Exception e) {
            statusTextArea.setText("error in changing the image");
        }
    }   

    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns

    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = new PuzzleFrame();
    }
    
}


