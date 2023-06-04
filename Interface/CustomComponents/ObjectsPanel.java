///////////////////////
//
// Filename: ObjectsPanel.java
// Author: Daniel Long
// Course: ICS4U1.
// Description: A class that handles the creation and user interactions of the panel where the environment's objects are displayed/added/removed
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;

import Core.Utility.Enum.EntityType;
import Interface.Utility.FontLoader;
import Interface.Utility.ComboBox.DropDownMenuRenderer;
import Interface.Utility.ComboBox.DropDownMenuUI;
import Interface.Windows.MainWindow;

public class ObjectsPanel extends JPanel implements MouseListener, ItemListener {

    public static final String[] OBJECT_TYPES = {"Sphere", "Rectangular Prism", "Triangular Prism"};

    private JPanel addObjectsArea;
    private JLabel objectsTitle;
    private JPanel objectsArea;
    private JButton addObjectButton;
    
    public ObjectsPanel(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        this.addObjectsArea = new JPanel();
        this.addObjectsArea.setPreferredSize(new Dimension(width, 95));
        this.addObjectsArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.addObjectsArea.setBorder(new MatteBorder(0, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.addObjectsArea.setLayout(new GridBagLayout());

        this.objectsTitle = new JLabel("Objects");
        this.objectsTitle.setFont(MainWindow.TITLE_FONT);
        this.objectsTitle.setBorder(new EmptyBorder(3, 10, 0, 100));
        this.objectsTitle.setForeground(Color.WHITE);

        this.addObjectButton = new RoundedButton(15, "+ Add Object", new Color(200, 200, 200), new Color(255, 255, 255), true);
        this.addObjectButton.setFont(FontLoader.loadFont("montserrat_medium", 17));
        this.addObjectButton.setBorder(new EmptyBorder(8, 10, 8, 10));
        this.addObjectButton.addMouseListener(this);

        this.objectsArea = new JPanel();
        this.objectsArea.setPreferredSize(new Dimension(width, height - addObjectsArea.getHeight()));
        this.objectsArea.setLayout(new FlowLayout(0, 0, 0));
        this.objectsArea.setBorder(new MatteBorder(1, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.objectsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.addObjectsArea.add(this.objectsTitle);
        this.addObjectsArea.add(this.addObjectButton);
        this.add(this.addObjectsArea);
        this.add(this.objectsArea);
    }

    public void addObject() {

        JPanel objectContainer = new JPanel();
        IconPanel objectIcon = new IconPanel(EntityType.SPHERE);
        JComboBox<String> objectTypeSelector = new JComboBox<String>(ObjectsPanel.OBJECT_TYPES);

        Component objectTypeSelectorComponent;


        objectTypeSelector.setUI(new DropDownMenuUI());

        for (int i = 0; i < objectTypeSelector.getComponentCount(); i++) {

            objectTypeSelectorComponent = objectTypeSelector.getComponent(i);

            // Removing combobox borders
            if (objectTypeSelectorComponent instanceof JComponent) {
                ((JComponent) objectTypeSelectorComponent).setBorder(new EmptyBorder(0, 0, 0, 10)); 
            }
        }

        objectTypeSelector.setMaximumSize( objectTypeSelector.getPreferredSize() );
        objectTypeSelector.setFont(FontLoader.loadFont("montserrat_medium", 15));
        objectTypeSelector.setBackground(MainWindow.BACKGROUND_COLOR);
        objectTypeSelector.setForeground(Color.WHITE);
        objectTypeSelector.setBorder(new MatteBorder(1, 1, 1, 1, MainWindow.BACKGROUND_COLOR));
        objectTypeSelector.setRenderer(new DropDownMenuRenderer());
        objectTypeSelector.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        objectTypeSelector.addItemListener(this);

        objectContainer.setBorder(new CompoundBorder(new MatteBorder(new Insets(0, 0, 1,1), MainWindow.BORDER_COLOR), new EmptyBorder(0, 0, 0, 0)));
        objectContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        objectContainer.setBackground(MainWindow.BACKGROUND_COLOR);
        objectContainer.setPreferredSize(new Dimension(this.getWidth(), 35));

        objectContainer.add(objectIcon);
        objectContainer.add(objectTypeSelector);
        this.objectsArea.add(objectContainer);

        this.revalidate();
        this.repaint();
    }

    // Manditory override methods
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    // Adding a new object when the addObject button is clicked
    @Override
    public void mouseClicked(MouseEvent event) {
        this.addObject();
    }

    // When a JComboBox registers an item state change, change its corresponding icon
    @Override
    public void itemStateChanged(ItemEvent event) {

        IconPanel iconPanel;

        // This is necessary to prevent a double-register of the event (one event is fired for deselected and for selected)
        if (event.getStateChange() == ItemEvent.SELECTED) {

            iconPanel = (IconPanel) ((JComponent) event.getSource()).getParent().getComponent(0);
            
            if (event.getItem().equals(EntityType.RECTANGULAR_PRISM.getName())) {

                iconPanel.changeEntityType(EntityType.RECTANGULAR_PRISM);

            } else if (event.getItem().equals(EntityType.TRIANGULAR_PRISM.getName())) {

                iconPanel.changeEntityType(EntityType.TRIANGULAR_PRISM);

            } else if (event.getItem().equals(EntityType.SPHERE.getName())) {
                
                iconPanel.changeEntityType(EntityType.SPHERE);
            }
        }
    }
}
