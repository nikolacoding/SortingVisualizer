package sorting;

import general.Constants;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Color;

public final class SortingPanel extends JPanel {
    SortingPanel(int numItems){
        this.setLayout(new GridLayout(1, numItems));
        this.setBackground(Color.white);
    }

    public static SortingPanel setGenerate(JTextField field) throws IllegalArgumentException {
        String content = field.getText();
        String regex = ",";

        // removing all whitespaces so instead of, say, "a, b, c" we get "a,b,c"
        content = content.replaceAll(" ", "");

        // splitting a string of format "a,b,c" into an array of strings ["a","b","c"]
        String[] items = content.split(regex);

        if (items.length == 1 && items[0].isEmpty())
            throw new IllegalArgumentException("'Set' pozvan nad praznim poljem za unos.");

        // iterating through them all to check how many empty strings we have;
        // this is *apparently* more efficient than making a new collection on the spot;
        // empty strings are caused by misplaced commas; e.g. "a,b,,c" gets interpreted as ["a","b","","c"]
        int numEmpty = 0;
        for (var item : items)
            if (item.isEmpty())
                numEmpty++;

        var panel = new SortingPanel(items.length - numEmpty);

        final float finalFontSize = (items.length >= Constants.REDUCED_FONT_SIZE_THRESHOLD) ?
                Constants.COLLECTION_FONT_SIZE_REDUCED : Constants.COLLECTION_FONT_SIZE;

        // skipping empty strings caused by misplaced commas
        for (var item : items){
            if (item.isEmpty())
                continue;

            // generating a label out of every valid string
            var label = new JLabel(item, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(finalFontSize));
            panel.add(label);
        }
        return panel;
    }

    public static SortingPanel randomizeGenerate(String[] items){
        var panel = new SortingPanel(items.length);

        final float finalFontSize = (items.length >= Constants.REDUCED_FONT_SIZE_THRESHOLD) ?
                Constants.COLLECTION_FONT_SIZE_REDUCED : Constants.COLLECTION_FONT_SIZE;

        for (var string : items){
            var label = new JLabel(string, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(finalFontSize));
            panel.add(label);
        }
        return panel;
    }
}
