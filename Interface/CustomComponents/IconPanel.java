///////////////////////
//
// Filename: IconPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the icon behavior for each element in the Object Explorer panel (leftmost panel).
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Core.Utility.Enum.EntityType;

public class IconPanel extends JPanel {

    private static final String ICON_DIR = System.getProperty("user.dir") + "/Assets/Icons";
    private EntityType entityType;
    private BufferedImage icon;

    // Creates a new IconPanel object
    public IconPanel(EntityType entityType) {

        this.entityType = entityType;

        this.setOpaque(false);
        this.setPreferredSize(new Dimension(21, 18));

        // Setting the initial image
        this.loadIcon();
    }

    // Changes the current icon given a new EntityType
    public void changeEntityType(EntityType entityType) {
        this.entityType = entityType;
        this.loadIcon();
    }

    // Drawing the relevant icon
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon, 5, 2, this);
    }

    // Loads the current icon based on entityType
    private void loadIcon() {
        
        try {                
            this.icon = ImageIO.read(new File(IconPanel.ICON_DIR + "/" + this.entityType.getName() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.revalidate();
        this.repaint();
    }
}
