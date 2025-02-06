package sorting;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

public final class SortingUtilities {
    public static JLabel GetLabelByIndex(JPanel numbersPanel, int index) throws IllegalArgumentException {
        if (index < 0 || index >= numbersPanel.getComponentCount())
            throw new IllegalArgumentException("GetLabelByIndex pozvan nad nevalidnim indeksom.");

        return (JLabel)numbersPanel.getComponent(index);
    }

    public static int GetLabelValueByIndex(JPanel numbersPanel, int index) throws IllegalArgumentException {
        if (index < 0 || index >= numbersPanel.getComponentCount())
            throw new IllegalArgumentException("GetLabelValueByIndex pozvan nad nevalidnim indeksom.");

        var label = GetLabelByIndex(numbersPanel, index);
        return Integer.parseInt(label.getText());
    }

    // an abstraction to help handle JPanel elements as comparable values;
    // caller covers all the expected assumptions required for a behavior free of exceptions
    public static int CompareTwo(JPanel numbersPanel, int first, int second){
        JLabel label1 = GetLabelByIndex(numbersPanel, first);
        JLabel label2 = GetLabelByIndex(numbersPanel, second);
        int firstValue, secondValue;

        try {
            firstValue = Integer.parseInt(label1.getText());
            secondValue = Integer.parseInt(label2.getText());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CompareTwo called on an invalid panel.");
            return 0;
        }

        // actual comparison
        if (firstValue == secondValue)
            return 0;
        else
            return firstValue > secondValue ? 1 : -1;
    }

    // swap them accordingly by using this method with the same exact signature as CompareTwo;
    // caller makes sure that the encapsulating panel contains nothing but JLabels with String <-> int values
    public static void SwapTwo(JPanel numbersPanel, int first, int second){
        try {
            Component[] components = numbersPanel.getComponents();
            Component temp = components[first];
            components[first] = components[second];
            components[second] = temp;

            numbersPanel.removeAll();
            for (var component : components)
                numbersPanel.add(component);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("SwapTwo called on an invalid panel.");
        }

        numbersPanel.revalidate();
        numbersPanel.repaint();
    }

    public static void LabelOne(JPanel numbersPanel, int index, Color color){
        JLabel label = GetLabelByIndex(numbersPanel, index);

        label.setForeground(color);

        numbersPanel.repaint();
        numbersPanel.revalidate();
    }

    public static void LabelTwo(JPanel numbersPanel, int first, int second, boolean reverse){
        JLabel label1 = GetLabelByIndex(numbersPanel, first);
        JLabel label2 = GetLabelByIndex(numbersPanel, second);

        if (!reverse){
            label1.setForeground(Color.red);
            label2.setForeground(Color.blue);
        } else {
            label1.setForeground(Color.black);
            label2.setForeground(Color.black);
        }

        numbersPanel.repaint();
        numbersPanel.revalidate();
    }

    public static void LabelAll(JPanel numbersPanel, Color color){
        for (var component : numbersPanel.getComponents()){
            JLabel label = (JLabel)component;
            label.setForeground(color);
        }
    }

    public static boolean IsAlreadySorted(JPanel numbersPanel, boolean ascending){
        Component[] components = numbersPanel.getComponents();
        int[] componentValues = new int[components.length];

        for (int i = 0; i < components.length; i++)
         componentValues[i] = SortingUtilities.GetLabelValueByIndex(numbersPanel, i);

        boolean areAscending = true, areDescending = true;

        for (int i = 0; i < components.length - 1; i++){
            if (componentValues[i] < componentValues[i + 1]) {
                areDescending = false;
                break;
            }
        }

        for (int i = 0; i < components.length - 1; i++){
            if (componentValues[i] > componentValues[i + 1]) {
                areAscending = false;
                break;
            }
        }

        return ((areAscending && ascending) || (areDescending && !ascending));
    }
}
