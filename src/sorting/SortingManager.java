package sorting;

import javax.swing.JPanel;

public final class SortingManager {

    public static void SortingDelegator(String algorithm, JPanel numbersPanel, boolean ascending) throws IllegalArgumentException {
        switch (algorithm) {
            case "Bubble-sort":
                SortingAlgorithms.BubbleSort(numbersPanel, ascending);
                break;
            case "Selection-sort":
                SortingAlgorithms.SelectionSort(numbersPanel, ascending);
                break;
            default:
                throw new IllegalArgumentException("Nevalidan odabrani algoritam: " + algorithm);
        }
    }
}