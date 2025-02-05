package display;

import sorting.SortingManager;
import sorting.SortingPanel;
import general.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class MainWindow extends JFrame {
    // panels
    public final DockedPanel topPanel = new DockedPanel();
    public final DockedPanel bottomPanel = new DockedPanel();

    // top panel
    public static final JButton sortButton = new JButton("Sort");
    public static final JButton setButton = new JButton("Set");
    public static final JButton randomizeButton = new JButton("Randomize");
    public static final JTextField[] numberInputFields = new JTextField[Constants.NUM_ITEMS];
    public static final JTextField numberInputField = new JTextField(Constants.NUM_ITEMS * 3);
    private final JPanel numbersPanel = new JPanel();

    // bottom panel
    public static final JButton orderButton = new JButton(Constants.UP_ARROW);
    public static final JComboBox<String> algorithmSelector = new JComboBox<>(Constants.ALGORITHM_NAMES);
    public static final JLabel speedSliderLabel = new JLabel("Simulation speed");
    public static final JSlider speedSlider = new JSlider();

    public static void SetControlsLock(boolean state){
        // speedSlider.setEnabled(!state);
        sortButton.setEnabled(!state);
        setButton.setEnabled(!state);
        randomizeButton.setEnabled(!state);
        orderButton.setEnabled(!state);
        algorithmSelector.setEnabled(!state);
        numberInputField.setEnabled(!state);
    }

    public static long GetInverseSimulationSpeed(){
        return Constants.BASE_SIMULATION_SPEED - 10 * (long)speedSlider.getValue();
    }

    private void setNumbers(){
        numbersPanel.removeAll();
        SortingPanel newPanel;

        try {
            newPanel = SortingPanel.setGenerate(numberInputField);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        numbersPanel.setLayout(newPanel.getLayout());
        numbersPanel.setBackground(newPanel.getBackground());

        for (var component : newPanel.getComponents()) {
            var label = new JLabel();
            label.setFont(component.getFont());
            numbersPanel.add(component);
        }

        repaint();
        revalidate();
    }

    private void randomizeNumbers(){
        String[] randomNumbers = new String[Constants.MAX_NUM_ITEMS];
        for (int i = 0; i < Constants.MAX_NUM_ITEMS; i++)
            randomNumbers[i] = String.valueOf(ThreadLocalRandom.current().nextInt(0, Constants.MAX_NUM_ITEMS * 5 - 1));
        numbersPanel.removeAll();

        var newPanel = SortingPanel.randomizeGenerate(randomNumbers);
        numbersPanel.setLayout(newPanel.getLayout());
        numbersPanel.setBackground(newPanel.getBackground());
        for (var component : newPanel.getComponents()) {
            var label = new JLabel();
            label.setFont(component.getFont());
            numbersPanel.add(component);
        }

        numberInputField.setText("");

        repaint();
        revalidate();
    }

    public MainWindow(String title){
        // initialization
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_DIMENSION);

        // setting the general layout
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(numbersPanel, BorderLayout.CENTER);

        // bottom panel management
        bottomPanel.add(algorithmSelector);
        bottomPanel.add(orderButton);
        speedSlider.setBackground(Color.lightGray);
        bottomPanel.add(speedSliderLabel);
        bottomPanel.add(speedSlider);

        // top panel management
        topPanel.add(numberInputField);
        topPanel.setPreferredSize(new Dimension(0, 40));
        topPanel.add(setButton);
        topPanel.add(randomizeButton);
        topPanel.add(sortButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == orderButton){
                    if (orderButton.getText().equals(Constants.UP_ARROW))
                        orderButton.setText(Constants.DOWN_ARROW);
                    else
                        orderButton.setText(Constants.UP_ARROW);
                }
                else if (e.getSource() == setButton)
                    setNumbers();
                else if (e.getSource() == randomizeButton)
                    randomizeNumbers();
                else if (e.getSource() == sortButton)
                    SortingManager.SortingDelegator(String.valueOf(algorithmSelector.getSelectedItem()),
                            numbersPanel,
                            orderButton.getText().equals(Constants.UP_ARROW));
            }
        };

        orderButton.addActionListener(listener);
        setButton.addActionListener(listener);
        sortButton.addActionListener(listener);
        randomizeButton.addActionListener(listener);
    }
}
