package sorting;

import display.MainWindow;
import general.Utility;

import javax.swing.*;
import java.awt.*;

public final class SortingAlgorithms {
    public static void BubbleSort(JPanel numbersPanel, boolean ascending) {
        int size = numbersPanel.getComponentCount();
        int compareAgainst = ascending ? 1 : -1;

        new Thread(() -> {
            // locking every control on the main window (buttons, fields and sliders) until the algorithm is done
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(true));
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    final long sleepDuration = MainWindow.GetInverseSimulationSpeed();
                    final int j_f = j;

                    boolean compResult = SortingUtilities.CompareTwo(numbersPanel, j_f, j_f + 1) == compareAgainst;

                    if (compResult) {
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelTwo(numbersPanel, j_f, j_f + 1, false));

                        try {
                            Thread.sleep(sleepDuration);
                        } catch (InterruptedException e) { System.out.println("Interrupted [BubbleSort] - 1"); }

                        // Swap the elements
                        SwingUtilities.invokeLater(() -> SortingUtilities.SwapTwo(numbersPanel, j_f, j_f + 1));

                        try {
                            Thread.sleep(sleepDuration);
                        } catch (InterruptedException e) { System.out.println("Interrupted [BubbleSort] - 2"); }
                    }
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelTwo(numbersPanel, j_f, j_f + 1, true));
                }
            }
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(false));
        }).start();
    }

    public static void SelectionSort(JPanel numbersPanel, boolean ascending){
        int size = numbersPanel.getComponentCount();
        int compareAgainst = ascending ? 1 : -1;

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(true));

            // outer loop, makes sure the inner loops runs within correct bounds
            for (int i = 0; i < size - 1; i++){
                final int i_f = i;
                int max_i = i;

                // labelling (in blue) the element (anchor) against which the min/max value is being looked up
                SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f, Color.blue));

                // inner loop, searches for the smallest/greatest element on the [i + 1, size - 1] segment
                for (int j = i + 1; j < size; j++){
                    final int j_f = j;
                    final long sleepDuration = MainWindow.GetInverseSimulationSpeed();

                    // labelling (in yellow) the current element currently being compared with the anchor
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.yellow));

                    Utility.Sleep(sleepDuration);
                    final int compResult = SortingUtilities.CompareTwo(numbersPanel, max_i, j_f);

                    // if the currently compared element is smaller/greater than the anchor, unlabel the previous smallest/greatest (if it exists)
                    if (compResult == compareAgainst){
                        final int prev_max_i = max_i;
                        max_i = j;

                        // if the previous greatest element isn't the initial one, unlabel it
                        if (prev_max_i != i)
                            SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, prev_max_i, Color.black));

                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.red));
                    }
                    else
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.black));
                }
                if (max_i != i) {
                    // at the end of each iteration swap the smallest/greatest found element with the anchor accordingly
                    final int max_i_f = max_i;
                    SwingUtilities.invokeLater(() -> SortingUtilities.SwapTwo(numbersPanel, i_f, max_i_f));
                }
                // also label the newly moved element green to signify it now being in the sorted portion of the collection
                // (this is outside of the loop and condition above because a swap doesn't strictly need to happen)
                SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f, Color.green));
            }
            SwingUtilities.invokeLater(() ->
                    SortingUtilities.LabelOne(numbersPanel, numbersPanel.getComponentCount() - 1, Color.green));

            // revert colors of all labels to black
            Utility.Sleep(MainWindow.GetInverseSimulationSpeed());
            for (int i = 0; i < numbersPanel.getComponentCount(); i++) {
                final int i_f = i;
                SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f, Color.black));
            }

            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(false));
        }).start();
    }
}
