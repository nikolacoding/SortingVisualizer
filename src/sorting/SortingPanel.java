package sorting;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Color;

public final class SortingPanel extends JPanel {
    SortingPanel(int numItems){
        this.setLayout(new GridLayout(1, numItems));
        this.setBackground(Color.white);
    }

    public static SortingPanel generate(JTextField[] fields){
        var panel = new SortingPanel(fields.length);
        for (var field : fields){
            var label = new JLabel(field.getText());
            label.setBackground(Color.red);
            panel.add(label);
        }

        return panel;
    }

    public static SortingPanel generate(String[] strings){
        var panel = new SortingPanel(strings.length);
        for (var s : strings){
            var label = new JLabel(s);
            label.setBackground(Color.red);
            panel.add(label);
        }

        return panel;
    }
}
