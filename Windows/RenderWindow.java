package Windows;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import Structures.Window;

public class RenderWindow extends Window {

    private RenderPanel renderPanel;
    
    public RenderWindow(int quality) throws InterruptedException {
        super("Render Window", new FlowLayout(FlowLayout.LEFT, 0, 0), 528, 551);

        setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(quality);
        add(this.renderPanel);

        while (true) {
            this.renderPanel.render(null);
        }
    }
}

class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;

    private int[][] colorMatrix;

    public RenderPanel(int quality) {
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;

        setPreferredSize(new Dimension(pixelSize * dimensions + 20, pixelSize * dimensions + 10));
    }

    public void render(int[][] cMatrix) {
        this.colorMatrix = cMatrix;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}