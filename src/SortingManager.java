import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.*;

public final class SortingManager {
    // an abstraction to help handle JPanel elements as comparable values;
    // caller covers all the expected assumptions required for a behavior free of exceptions
    public static int CompareTwo(JPanel numbersPanel, int first, int second){
        JLabel label1 = (JLabel) numbersPanel.getComponent(first);
        JLabel label2 = (JLabel) numbersPanel.getComponent(second);
        int firstValue, secondValue;
        // visual exaggeration
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

    public static void LabelTwo(JPanel numbersPanel, int first, int second, boolean reverse){
        JLabel label1 = (JLabel) numbersPanel.getComponent(first);
        JLabel label2 = (JLabel) numbersPanel.getComponent(second);

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

    public static void BubbleSort(JPanel numbersPanel, boolean ascending, long speed) {
        int size = numbersPanel.getComponentCount();
        int compareAgainst = ascending ? 1 : -1;
        long sleepDuration = 1000 - speed * 10;

        new Thread(() -> {
            // locking every control on the main window (buttons, fields and slider) until the algorithm is done
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(true));
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    final int j_f = j;

                    boolean compResult = CompareTwo(numbersPanel, j_f, j_f + 1) == compareAgainst;

                    if (compResult) {
                        SwingUtilities.invokeLater(() -> LabelTwo(numbersPanel, j_f, j_f + 1, false));

                        try {
                            Thread.sleep(sleepDuration);
                        } catch (InterruptedException e) { System.out.println("Interrupted [BubbleSort] - 1"); }

                        // Swap the elements
                        SwingUtilities.invokeLater(() -> SwapTwo(numbersPanel, j_f, j_f + 1));

                        try {
                            Thread.sleep(sleepDuration);
                        } catch (InterruptedException e) { System.out.println("Interrupted [BubbleSort] - 2"); }
                    }
                    SwingUtilities.invokeLater(() -> LabelTwo(numbersPanel, j_f, j_f + 1, true));
                }
            }
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(false));
        }).start();
    }
}
