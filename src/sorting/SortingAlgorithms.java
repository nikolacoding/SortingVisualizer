package sorting;

import display.MainWindow;
import general.Utility;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class SortingAlgorithms {

    protected static void BubbleSort(JPanel numbersPanel, boolean ascending) {
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

    protected static void SelectionSort(JPanel numbersPanel, boolean ascending){
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

                    // un-label the swapped element
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, max_i_f, Color.black));
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

    protected static void InsertionSort(JPanel numbersPanel, boolean ascending) {
        int size = numbersPanel.getComponentCount();
        int compareAgainst = ascending ? -1 : 1;

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(true));

            // outer loop, makes sure the inner loop, if invoked, runs from i backwards
            for (int i = 1; i < size; i++) {
                final int i_f = i;
                final long sleepDuration = MainWindow.GetInverseSimulationSpeed();
                int j = i;

                SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f, Color.blue));
                Utility.Sleep(sleepDuration);

                // inner loop, makes sure to keep swapping elements until the logic of INSERTING the out-of-place i-th element
                // into its appropriate place in the sorted (left) portion of the collection is correctly simulated
                while (j >= 1 && SortingUtilities.CompareTwo(numbersPanel, j, j - 1) == compareAgainst) {
                    final int j_f = j;

                    // label the about-to-be-swapped 'critical' element
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.red));

                    // pause
                    Utility.Sleep(sleepDuration);

                    // label the element about to be swapped with the critical one
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f - 1, Color.blue));

                    // swap them
                    SortingUtilities.SwapTwo(numbersPanel, j, j - 1);

                    // pause
                    Utility.Sleep(sleepDuration);

                    // re-label the critical element to signify that it's being checked again
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f - 1, Color.blue));

                    // un-label the swapped element
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.black));

                    j--;
                }
                final int j_f = j;
                SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.black));
            }
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(false));
        }).start();
    }

    protected static void ShellSort(JPanel numbersPanel, boolean ascending) {
        int size = numbersPanel.getComponentCount();
        int compareAgainst = ascending ? -1 : 1;

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(true));

            // outer outer loop, tracks the increment
            for (int h = size / 2; h > 0; h /= 2) {
                final int h_f = h;
                // outer loop, makes sure the inner loop, if invoked, runs from i backwards
                for (int i = 1; i < size; i++) {
                    final int i_f = i;
                    final long sleepDuration = MainWindow.GetInverseSimulationSpeed();
                    int j = i;

                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f, Color.blue));
                    if (i_f - h_f >= 0)
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f - h_f, Color.blue));
                    Utility.Sleep(sleepDuration);

                    // inner loop, makes sure to keep swapping elements until the logic of INSERTING the out-of-place i-th element
                    // into its appropriate place in the sorted (left) portion of the collection is correctly simulated
                    while (j >= h && SortingUtilities.CompareTwo(numbersPanel, j, j - h) == compareAgainst) {
                        final int j_f = j;

                        // label the about-to-be-swapped 'critical' element
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.red));

                        // pause
                        Utility.Sleep(sleepDuration);

                        // label the element about to be swapped with the critical one
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f - h_f, Color.blue));

                        // swap them
                        SortingUtilities.SwapTwo(numbersPanel, j, j - h);

                        // pause
                        Utility.Sleep(sleepDuration);

                        // re-label the critical element to signify that it's being checked again
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f - h_f, Color.blue));

                        // un-label the swapped element
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.black));

                        j -= h;
                    }
                    final int j_f = j;
                    SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, j_f, Color.black));
                    if (i_f - h_f >= 0)
                        SwingUtilities.invokeLater(() -> SortingUtilities.LabelOne(numbersPanel, i_f - h_f, Color.black));
                }
            }
            SwingUtilities.invokeLater(() -> MainWindow.SetControlsLock(false));

        }).start();
    }
}
