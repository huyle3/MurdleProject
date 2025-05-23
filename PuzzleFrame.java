import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;
    private Game game;
    private int currentPuzzle;
    private String[] nums;

    //qui, quoi, ou, pourquoi dropdowns
    JComboBox<String> quiButton;
    JComboBox<String> quoiButton; 
    JComboBox<String> ouButton;
    JComboBox<String> pourquoiButton;
    JComboBox<String>[] comboBoxArr;

    JButton soumetrreButton = new JButton("Soumettre");
    JButton effacerButton = new JButton("Effacer");

    //puzzle number dropdown 
    JComboBox<Integer> numButton;

    JPanel[] listOfButtons;
    DrawingPanel[] listOfPanels = new DrawingPanel[]{new DrawingPanel(50, 50), new DrawingPanel(50, 50), new DrawingPanel(50, 50), new DrawingPanel(50, 50)};

    JLabel titleLabel;
    JLabel statusImageLabel;
    JTextArea statusTextArea;
    JPanel statusPanel;
    int top = 10;
    int bottom = 10;
    int left = 20;
    int right = 20;
    Border padding = BorderFactory.createEmptyBorder(top, left, bottom, right);

    private BufferedImage imageSad;
    private BufferedImage imageHappy;


    public PuzzleFrame(){
        Font font = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Load font from resources (inside jar) using getResourceAsStream
            InputStream is = getClass().getResourceAsStream("/src/fonts/jmh_typewriter/JMH Typewriter-Black.ttf");
            if (is == null) {
                throw new IOException("Font file not found! Check path and packaging.");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            // Derive size and style from loaded font
            font = customFont.deriveFont(Font.PLAIN, 14f);
        } catch (Exception e) {
            e.printStackTrace();
        } //ui manager puts font to all components used
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("JComboBox.font", font);
        UIManager.put("JList.font", font);
        UIManager.put("JTable.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("JMenuItem.font", font);

        //adjusts imagesad & imagehappy
        try {
            imageSad = resize(new URL("https://i.imgur.com/rT9OpJZ.png"), new Dimension(350, 250));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            imageHappy = resize(new URL("https://i.imgur.com/6piy1kK.png"), new Dimension(250, 250));
        } catch (IOException e) {
            e.printStackTrace();
        }

        titleLabel = new JLabel("Murdle");
        titleLabel.setBackground(new Color(224, 211, 175));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(padding);
   
        listOfButtons = new JPanel[4]; 
        for (int i = 0; i < listOfButtons.length; i++) {
            listOfButtons[i] = new JPanel(); //initializes a list of buttons
        }

        game = new Game();
        currentPuzzle = 1; //start at puzzle 1
        
        Integer[] nums = new Integer[100]; //list of 100 nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }

        quiButton = new JComboBox<>(game.getPuzzle(currentPuzzle).getWho()); //each button has the option choices
        quoiButton = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhat());
        ouButton = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhere());
        pourquoiButton = new JComboBox<>(game.getPuzzle(currentPuzzle).getWhy());

        comboBoxArr = new JComboBox[]{quiButton, quoiButton, ouButton, pourquoiButton}; //creates an array of these buttons

        numButton = new JComboBox<>(nums);
        applyFont(font, quiButton, quoiButton, ouButton, pourquoiButton, numButton, soumetrreButton, effacerButton); //applys the font to all buttons
        
        JFrame frame = new JFrame("Murdle Français"); //creates j frame
        frame.setFont(font);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);

        //JPanel bigBody = new JPanel(new FlowLayout()); //creates body panel on the right
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
        } catch (Exception e) {
            statusImageLabel = new JLabel("bad url"); // fallback text/icon if URL fails
        }

        statusTextArea = new JTextArea("Faites votre choix."); //text box stuff
        statusTextArea.setEditable(false);
        statusTextArea.setOpaque(false);
        statusTextArea.setFont(font);
        //rightmost panel, contains 'congrats'/'try again' text, images
        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // <-- ADD THIS
        statusTextArea.setAlignmentX(Component.CENTER_ALIGNMENT); 
        statusPanel.add(statusImageLabel);
        statusPanel.add(Box.createVerticalStrut(10)); //verticle gap
        statusPanel.add(statusTextArea, Component.CENTER_ALIGNMENT);

        statusPanel.setBackground(new Color(224, 211, 175));
        bigBody.add(statusPanel);

        addButtonFrame(buttons); //add buttons to buttom frame
        buttons.setBackground(new Color(224, 211, 175));
        bigBody.setBackground(new Color(224, 211, 175)); 
        frame.add(bigBody, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.WEST);
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }

    public void addButtonFrame(JPanel buttons){   //logic for creating the buttons and adding them to button frame
        for (int i = 0; i < comboBoxArr.length; i++) {
            listOfButtons[i].setLayout(new BoxLayout(listOfButtons[i], BoxLayout.X_AXIS)); // formats each button
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
        ActionListener numListener = new ActionListener() { //action listener for the puzzle number
            @Override
            public void actionPerformed(ActionEvent event) {
                currentPuzzle = (Integer) numButton.getSelectedItem(); // gets current puzzle selected
                Puzzle p = game.getPuzzle(currentPuzzle);
                updateComboBoxItems(quiButton, p.getWho()); //updates all the buttons so their choices match current puzzle
                updateComboBoxItems(quoiButton, p.getWhat());
                updateComboBoxItems(ouButton, p.getWhere());
                if (currentPuzzle >= 50){ // pourquoi button if greater than 50
                        updateComboBoxItems(pourquoiButton, p.getWhy());
                    }                    
                
                setGray(); //sets all buttons back to gray
                updateStatus("start"); //resets image on right
            }
        };
        numButton.addActionListener(numListener); 

        ActionListener clearListener = new ActionListener() { //action listener for clear button
            @Override
            public void actionPerformed(ActionEvent event){
                setGray();
                updateStatus("start"); //resets image on right
                resetComboBoxSelections();
            }
        };
        effacerButton.addActionListener(clearListener);

        String[] labelNames = {"Qui", "Quoi", "Ou", "Pourquoi"}; 
        JLabel[] listOfLabels = new JLabel[labelNames.length];
        for (int i = 0; i < listOfLabels.length; i++) { //loops through labels and initialize each one and formats
            listOfLabels[i] = new JLabel(labelNames[i]);
            listOfLabels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttons.add(listOfLabels[i]);
            listOfButtons[i].setBackground(new Color(224, 211, 175));
            buttons.add(listOfButtons[i], Component.LEFT_ALIGNMENT);
        }

        buttons.add(soumetrreButton, Component.LEFT_ALIGNMENT); 


        ActionListener submitListener = new ActionListener() { //button listener for submit

            @Override
            public void actionPerformed(ActionEvent event) {
                checkAnswers(); 
            }
        };
        soumetrreButton.addActionListener(submitListener);


        //clear button
        buttons.add(effacerButton, Component.LEFT_ALIGNMENT);
        buttons.setBorder(padding);

    }

    private void resetComboBoxSelections() { 
        for (JComboBox<String> box : comboBoxArr) { //loops through each box in combo box arr
            if (box.getItemCount() > 0) { //if the box contains something so not pourquoi
                box.setSelectedIndex(0); //reset
            }
        }
    }

    private void setGray() { //sets all buton labels back to gray
        for (int i = 0; i < listOfPanels.length; i++) {
            listOfPanels[i].setColor(Color.GRAY);
        }
    }

    private void checkAnswers() { //checks answers
        // define correct answers
        String[] userAnswer;
        if (game.getPuzzle(currentPuzzle).getWhat().length == 5){ //if puzzle num > 50
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
        boolean[] checkedAnswerArr = game.checkPuzzle(currentPuzzle, userAnswer);  //boolean array of checked answers
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
    
    //resize
    public BufferedImage resize(final URL url, final Dimension size) throws IOException{
        final BufferedImage image = ImageIO.read(url);
        final BufferedImage resized = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = resized.createGraphics();
        g.drawImage(image, 0, 0, size.width, size.height, null);
        g.dispose();
        return resized;
    }
    
    private void updateStatus(String statusType) {
        try {
            switch (statusType) { //quicker if-else
                case "start":
                    statusImageLabel.setIcon(new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/1800/1800204.png"))); //the base image
                    statusTextArea.setText("Faites votre choix.");
                    break;
                case "correct":
                    statusImageLabel.setIcon(new ImageIcon(imageHappy)); //happy image :)
                    statusTextArea.setText("Félicitations ! Vous êtes un super détective !");
                    break;
                case "wrong":
                    statusImageLabel.setIcon(new ImageIcon(imageSad)); //sad image:(
                    statusTextArea.setText("Malheureusement, ce n’est pas la bonne solution. Essayez encore.");
                    break;
            }
        } catch (Exception e) {
            statusTextArea.setText("error in changing the image");
        }
    }   

    private void updateComboBoxItems(JComboBox<String> comboBox, String[] items) { //when changing puzzle
        comboBox.removeAllItems(); //removes all current items
        for (String item: items) {
            comboBox.addItem(item); //add new items into it
        }
    }
    private void applyFont(Font font, JComponent... components) { //able to take multiple components
        for (JComponent comp : components) { //loops through all the components
            comp.setFont(font); // set the correct font
        }
    }
    public static void main(String[] args) {
        try {
            new PuzzleFrame();
        } catch (Exception e) {
            e.printStackTrace();
            try (PrintWriter out = new PrintWriter("error.log")) {
                e.printStackTrace(out);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}