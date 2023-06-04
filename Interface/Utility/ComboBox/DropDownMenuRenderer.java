///////////////////////
//
// Filename: DropDownMenuRenderer.java
// Author: Daniel Long
// Course: ICS4U1.
// Description: A class that handles custom rendering for the drop down menu list popup.
//
///////////////////////

package Interface.Utility.ComboBox;

import java.awt.*;
import javax.swing.*;

import Interface.Windows.MainWindow;

public class DropDownMenuRenderer extends JLabel implements ListCellRenderer<String> {

    public static final Color HIGHLIGHT_COLOR = new Color(100, 100, 100);

    // Creates a new DropDownMenuREnderer
    public DropDownMenuRenderer() {
        this.setOpaque(true);
        this.setHorizontalAlignment(LEFT);
        this.setVerticalAlignment(CENTER);
    }

    // Custom rendering for the list and its cells
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus)
    {
 
        list.setSelectionBackground(MainWindow.BACKGROUND_COLOR);
        list.setSelectionForeground(Color.WHITE);

        if (isSelected) {
            this.setBackground(DropDownMenuRenderer.HIGHLIGHT_COLOR);
        } else {
            this.setBackground(MainWindow.BACKGROUND_COLOR);
        }
        
        this.setText(value);
        this.setFont(list.getFont());

        return this;
    }
}