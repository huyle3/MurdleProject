
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
    String[] nums;

    //qui, quoi, ou, pourquoi dropdowns
    JComboBox<String> quiButton, quoiButton, ouButton, pourquoiButton;
    JComboBox<String>[] comboBoxArr;

    JButton soumetrreButton = new JButton("Soumettre");
    JButton effacerButton = new JButton("Effacer");

    //puzzle number dropdown 
    JComboBox<Integer> numButton;

    JPanel button1 = new JPanel();
    JPanel button2 = new JPanel();
    JPanel button3 = new JPanel();
    JPanel button4 = new JPanel();
    JPanel[] listOfButtons;

    DrawingPanel[] listOfPanels = new DrawingPanel[]{new DrawingPanel(50, 50), new DrawingPanel(50, 50), new DrawingPanel(50, 50), new DrawingPanel(50, 50)};

    JLabel statusImageLabel;
    JTextArea statusTextArea;
    JPanel statusPanel;

    public PuzzleFrame(){
        Font font = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            File fontFile = new File("jmh_typewriter/JMH Typewriter-Black.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // Register the font with the GraphicsEnvironment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            // Use the font
            font = new Font(customFont.getName(), Font.PLAIN, 12);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | FontFormatException | IOException exception) {
            exception.printStackTrace();
        }
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("JComboBox.font", font);
        UIManager.put("JList.font", font);
        UIManager.put("JTable.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("JMenuItem.font", font);
   
        
        comboBoxArr = new JComboBox[]{quiButton, quoiButton, ouButton, pourquoiButton};
        listOfButtons = new JPanel[]{button1, button2, button3, button4};

        game = new Game();
        currentPuzzle = 1;
        
        Integer[] nums = new Integer[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }

        
        comboBoxArr[0] = new JComboBox<>(game.getPuzzle(currentPuzzle).getWho());
        comboBoxArr[1] = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhat());
        comboBoxArr[2] = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhere());
        comboBoxArr[3] = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhy());

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
        for (int i = 0; i < listOfButtons.length; i++) {
            listOfButtons[i].setLayout(bl2);
        }

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
        //statusTextArea.setLineWrap(true);

        //rightmost panel, contains 'congrats'/'try again' text, images
        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.add(statusImageLabel);
        statusPanel.add(Box.createVerticalStrut(10)); //verticle gap
        statusPanel.add(statusTextArea, Component.CENTER_ALIGNMENT);

        statusPanel.setBackground(new Color(224, 211, 175));
        bigBody.add(statusPanel);

        addButtonFrame(buttons);
        buttons.setBackground(new Color(224, 211, 175));
        bigBody.setBackground(new Color(224, 211, 175)); 
        frame.add(bigBody, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);

    }


    public void addButtonFrame(JPanel buttons){  
        for (int i = 0; i < comboBoxArr.length; i++) {
            listOfButtons[i].setLayout(new BoxLayout(listOfButtons[i], BoxLayout.X_AXIS));
            listOfButtons[i].add(comboBoxArr[i]);
            listOfButtons[i].add(Box.createHorizontalGlue());
            listOfPanels[i].setBackground(new Color(224, 211, 175));
            listOfButtons[i].add(listOfPanels[i]);
            listOfPanels[i].setColor(Color.GRAY);
        }

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
                for (int i = 0; i < comboBoxArr.length; i++) {
                    comboBoxArr[i].removeAllItems();
                }
         
                currentPuzzle = Integer.parseInt(numButton.getSelectedItem().toString());
                for (int i = 0; i < game.getPuzzle(currentPuzzle).getWhat().length; i++) {
                    quoiButton.addItem(game.getPuzzle(currentPuzzle).getWhat()[i]);
                    quiButton.addItem(game.getPuzzle(currentPuzzle).getWho()[i]);
                    ouButton.addItem(game.getPuzzle(currentPuzzle).getWhere()[i]);
                    if (currentPuzzle >= 50){
                        pourquoiButton.addItem(game.getPuzzle(currentPuzzle).getWhy()[i]);
                    }                    
                }
                setGray(); //sets all buttons back to gray
                updateStatus("start"); //resets image on right
            }
        };
        numButton.addActionListener(numListener);

        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                setGray();
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

        JLabel quiLabel = new JLabel("Qui");
        JLabel quoiLabel = new JLabel("Quoi");
        JLabel ouLabel = new JLabel("Ou");
        JLabel pourquoiLabel = new JLabel("Pourquoi");
        JLabel[] listOfLabels = new JLabel[] {quiLabel, quoiLabel, ouLabel, pourquoiLabel};

        for (int i = 0; i < listOfLabels.length; i++) {
            listOfLabels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttons.add(listOfLabels[i]);
            listOfButtons[i].setBackground(new Color(224, 211, 175));
            buttons.add(listOfButtons[i], Component.LEFT_ALIGNMENT);
        }

        buttons.add(soumetrreButton, Component.LEFT_ALIGNMENT);

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
    private void setGray() {
        for (int i = 0; i < listOfPanels.length; i++) {
            listOfPanels[i].setColor(Color.GRAY);
        }
    }

    private void checkAnswers() {
        // define correct answers
        String[] userAnswer;
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
        boolean allCorrect = true; 
        for (int i = 0; i < checkedAnswerArr.length; i++) {// it would loop through {true, false, false, true} for example
            if (checkedAnswerArr[i]) {
                listOfPanels[i].setColor(Color.GREEN);
            } else {
                listOfPanels[i].setColor(Color.RED);
                allCorrect = false;
            }
            //panelsList[i].repaint();
        }
        //check if all answers are correct
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


