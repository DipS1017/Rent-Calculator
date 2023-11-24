import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RentCalculator{
    private JFrame frame;
    private JTextField rentField, garbageField, waterField;
    private JTextField meter1Unit1Field, meter1Unit2Field;
    private JTextField meter2Unit1Field, meter2Unit2Field;
    private JLabel totalLabel;
    private JLabel meter1DifferenceLabel, meter2DifferenceLabel;
    private JLabel meter1CostLabel, meter2CostLabel;

    public RentCalculator() {
        frame = new JFrame("Rent Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        panel.add(new JLabel("Enter Rent:"));
        rentField = new JTextField(10);
        panel.add(rentField);

        panel.add(new JLabel("Enter Garbage:"));
        garbageField = new JTextField(10);
        panel.add(garbageField);

        panel.add(new JLabel("Enter Water:"));
        waterField = new JTextField(10);
        panel.add(waterField);

        panel.add(new JLabel("Meter 1 (Enter 1st Unit):"));
        meter1Unit1Field = new JTextField(10);
        panel.add(meter1Unit1Field);

        panel.add(new JLabel("Meter 1 (Enter 2nd Unit):"));
        meter1Unit2Field = new JTextField(10);
        panel.add(meter1Unit2Field);

        panel.add(new JLabel("Optional: Meter 2 (Enter 1st Unit):"));
        meter2Unit1Field = new JTextField(10);
        panel.add(meter2Unit1Field);

        panel.add(new JLabel("Optional: Meter 2 (Enter 2nd Unit):"));
        meter2Unit2Field = new JTextField(10);
        panel.add(meter2Unit2Field);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
        panel.add(calculateButton);

        totalLabel = new JLabel("Total:");
        panel.add(totalLabel);

        meter1DifferenceLabel = new JLabel("Meter 1 Difference:");
        panel.add(meter1DifferenceLabel);

        meter2DifferenceLabel = new JLabel("Meter 2 Difference:");
        panel.add(meter2DifferenceLabel);

        meter1CostLabel = new JLabel("Meter 1 Cost:");
        panel.add(meter1CostLabel);

        meter2CostLabel = new JLabel("Meter 2 Cost:");
        panel.add(meter2CostLabel);

        frame.add(panel);

        // Enable Enter key navigation
        enableEnterKeyNavigation(rentField, garbageField);
        enableEnterKeyNavigation(garbageField, waterField);
        enableEnterKeyNavigation(waterField, meter1Unit1Field);
        enableEnterKeyNavigation(meter1Unit1Field, meter1Unit2Field);
        enableEnterKeyNavigation(meter1Unit2Field, meter2Unit1Field);
        enableEnterKeyNavigation(meter2Unit1Field, meter2Unit2Field);
        enableEnterKeyNavigation(meter2Unit2Field, calculateButton);

        // Set the default button for the panel
        frame.getRootPane().setDefaultButton(calculateButton);
    }

    private void calculateTotal() {
        try {
            double rent = Double.parseDouble(rentField.getText());
            double garbage = Double.parseDouble(garbageField.getText());
            double water = Double.parseDouble(waterField.getText());

            double meter1Unit1 = Double.parseDouble(meter1Unit1Field.getText());
            double meter1Unit2 = Double.parseDouble(meter1Unit2Field.getText());
            double meter2Unit1 = Double.parseDouble(meter2Unit1Field.getText());
            double meter2Unit2 = Double.parseDouble(meter2Unit2Field.getText());

            double meter1Difference = Math.abs(meter1Unit2 - meter1Unit1);
            double meter2Difference = Math.abs(meter2Unit2 - meter2Unit1);

            double electricity1Cost = meter1Difference * 15;
            double electricity2Cost = meter2Difference * 15;

            double totalCost = rent + garbage + water + electricity1Cost + electricity2Cost;

            totalLabel.setText("Total: " + totalCost);
            meter1DifferenceLabel.setText("Meter 1 Difference: " + meter1Difference);
            meter2DifferenceLabel.setText("Meter 2 Difference: " + meter2Difference);
            meter1CostLabel.setText("Meter 1 Cost: " + electricity1Cost);
            meter2CostLabel.setText("Meter 2 Cost: " + electricity2Cost);
        } catch (NumberFormatException ex) {
            totalLabel.setText("Please enter valid numbers.");
        }
    }

    private void enableEnterKeyNavigation(final JTextField source, final JComponent nextComponent) {
        source.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nextComponent != null) {
                    nextComponent.requestFocusInWindow();
                }
            }
        });
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RentCalculator calculator = new RentCalculator();
                calculator.display();
            }
        });
    }
}
