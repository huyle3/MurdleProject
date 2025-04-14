

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private Color color = Color.GRAY;

    public DrawingPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void setColor(Color c) {
        this.color = c;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(25, 25, 25, 25);
    }
}