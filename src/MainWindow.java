import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class MainWindow extends JFrame {
    public final JButton sortButton = new JButton("Sort");
    public final JButton setButton = new JButton("Set");
    public final JButton randomizeButton = new JButton("Randomize");
    public final JTextField[] numberFields = new JTextField[Constants.NUM_ITEMS];
    private final JPanel numbersPanel = new JPanel();

    private boolean anyTextFieldIsEmpty(){
        for (var field : numberFields)
            if (field.getText().isEmpty())
                return true;
        return false;
    }

    private void setNumbers(){
        if (anyTextFieldIsEmpty())
            return;

        numbersPanel.removeAll();
        var newPanel = SortingPanel.generate(numberFields);
        numbersPanel.setLayout(newPanel.getLayout());
        numbersPanel.setBackground(newPanel.getBackground());
        for (var component : newPanel.getComponents())
            numbersPanel.add(component);

        repaint();
        revalidate();
    }

    private void randomizeNumbers(){
        String[] randomNumbers = new String[Constants.NUM_ITEMS];
        for (int i = 0; i < Constants.NUM_ITEMS; i++)
            randomNumbers[i] = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
        numbersPanel.removeAll();

        var newPanel = SortingPanel.generate(randomNumbers);
        numbersPanel.setLayout(newPanel.getLayout());
        numbersPanel.setBackground(newPanel.getBackground());
        for (var component : newPanel.getComponents())
            numbersPanel.add(component);

        for (var field : numberFields)
            field.setText("");

        repaint();
        revalidate();
    }

    MainWindow(String title){
        // initialization
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // setting the general layout
        this.setLayout(new BorderLayout());

        var topPanel = new DockedPanel();
        var bottomPanel = new DockedPanel();
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(numbersPanel, BorderLayout.CENTER);

        // bottom panel management
        var slider = new JSlider();
        var sliderLabel = new JLabel("Brzina simulacije");
        slider.setBackground(Color.lightGray);
        bottomPanel.add(sliderLabel);
        bottomPanel.add(slider);

        // top panel management
        topPanel.setPreferredSize(new Dimension(0, 50));
        for (int i = 0; i < numberFields.length; i++){
            var newTextField = new JTextField();
            newTextField.setPreferredSize(new Dimension(20, 20));
            numberFields[i] = newTextField;
            topPanel.add(newTextField);
        }
        topPanel.add(setButton);
        topPanel.add(randomizeButton);
        topPanel.add(sortButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == setButton)
                    setNumbers();
                else if (e.getSource() == randomizeButton)
                    randomizeNumbers();
            }
        };

        setButton.addActionListener(listener);
        sortButton.addActionListener(listener);
        randomizeButton.addActionListener(listener);
    }
}
