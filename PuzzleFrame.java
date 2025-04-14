import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
//main is here! waow


public class PuzzleFrame {
    private JPanel bigBody;
    private JPanel buttons;


    String[] options1 = {"Option A qui", "Option B qui", "Option C qui", "Option D qui"};
    String[] options2 = {"Option A quoi", "Option B quoi", "Option C quoi", "Option D quoi"};
    String[] options3 = {"Option A ou", "Option B ou", "Option C ou", "Option D ou"};
    String[] options4 = {"Option A pourquoi", "Option B pourquoi", "Option C pourquoi", "Option D pourquoi"};

    JComboBox<String> quiButton = new JComboBox<>(options1);
    JComboBox<String> quoiButton = new JComboBox<>(options2);
    JComboBox<String> ouButton = new JComboBox<>(options3);
    JComboBox<String> pourquoiButton = new JComboBox<>(options4);
    JButton soumetrreButton = new JButton("Soumettre");

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
        JFrame frame = new JFrame("Murdle Français");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);

        JPanel bigBody = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel();
        BoxLayout bl = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(bl);
        BoxLayout bl2 = new BoxLayout(buttons, BoxLayout.X_AXIS);

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
        button1.setLayout(new BoxLayout(button1, BoxLayout.X_AXIS));
        button1.add(quiButton);
        button1.add(Box.createHorizontalGlue());
        //rectangle here
        button1.add(panel1);
        panel1.setColor(Color.RED);
        

        button2.setLayout(new BoxLayout(button2, BoxLayout.X_AXIS));
        button2.add(quoiButton);
        button2.add(Box.createHorizontalGlue());
        //rectangle here
        button2.add(panel2);
        panel2.setColor(Color.RED);
        

        button3.setLayout(new BoxLayout(button3, BoxLayout.X_AXIS));
        button3.add(ouButton);
        button3.add(Box.createHorizontalGlue());
        //rectangle here
        button3.add(panel3);
        panel3.setColor(Color.RED);
        

        button4.setLayout(new BoxLayout(button4, BoxLayout.X_AXIS));
        button4.add(pourquoiButton);
        button4.add(Box.createHorizontalGlue());
        //rectangle here
        button4.add(panel4);
        panel4.setColor(Color.RED);
        


        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));


        buttons.add(button1, Component.LEFT_ALIGNMENT);
        buttons.add(button2, Component.LEFT_ALIGNMENT);
        buttons.add(button3, Component.LEFT_ALIGNMENT);
        buttons.add(button4, Component.LEFT_ALIGNMENT);
        buttons.add(soumetrreButton, Component.LEFT_ALIGNMENT);

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
        String[] correctAnswers = {"Option B qui", "Option C quoi", "Option A ou", "Option D pourquoi"};

        if (quiButton.getSelectedItem().equals(correctAnswers[0])) {
            panel1.setColor(Color.GREEN);
        } else {
            panel1.setColor(Color.RED);
        }

        if (quoiButton.getSelectedItem().equals(correctAnswers[1])) {
            panel2.setColor(Color.GREEN);
        } else {
            panel2.setColor(Color.RED);
        }

        if (ouButton.getSelectedItem().equals(correctAnswers[2])) {
            panel3.setColor(Color.GREEN);
        } else {
            panel3.setColor(Color.RED);
        }

        if (pourquoiButton.getSelectedItem().equals(correctAnswers[3])) {
            panel4.setColor(Color.GREEN);
        } else {
            panel4.setColor(Color.RED);
        }
    }

    //Creates 3 JPanels: GridBagLayout on left (puzzle number + JComboBoxes (dropdowns) + submit button), DrawingPanel on right w/ colored correct/incorrect boxes, GridBagLayout (probably) for right half (w/ text + frown image)
    //Contains DrawingPanel class 
    //Above components (within JPanels) created + action listeners for buttons/dropdowns

    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = new PuzzleFrame();
    //     String[] userAnswers = {
    //         "le maire Honey",
    //     "une araignée venimeuse",
    //     "les anciennes ruines",
    //     "pour une arnaque immobilière"
    // };
    //     Game game = new Game();
    //     boolean[] arr = game.checkPuzzle(55, userAnswers);
    //     for (int i = 0; i < arr.length; i++) {
    //         System.out.println(arr[i]);
    //     }
    }
}

