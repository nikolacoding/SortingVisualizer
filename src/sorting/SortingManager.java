package sorting;

import javax.swing.JPanel;

// calls the appropriate algorithm using SortingDelegator through SortingAlgorithms' otherwise inaccessible (protected) interface
public final class SortingManager extends SortingAlgorithms {

    public static void SortingDelegator(String algorithm, JPanel numbersPanel, boolean ascending) throws IllegalArgumentException {
        switch (algorithm) {
            case "Bubble-sort":
                BubbleSort(numbersPanel, ascending);
                break;
            case "Selection-sort":
                SelectionSort(numbersPanel, ascending);
                break;
            case "Insertion-sort":
                InsertionSort(numbersPanel, ascending);
                break;
            case "Shell-sort":
                ShellSort(numbersPanel, ascending);
                break;
            default:
                throw new IllegalArgumentException("Nevalidan odabrani algoritam: " + algorithm);
        }
    }
}