import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Offer extends JFrame implements ActionListener {

    JLabel offerLabel;
    JCheckBoxMenuItem washing;
    JCheckBoxMenuItem welding;
    JCheckBoxMenuItem painting;
    JCheckBoxMenuItem sandBlasting;
    JCheckBoxMenuItem overhaulingAndRepairOfStableEngines;
    JCheckBoxMenuItem repairOfElectricalEquipment;
    JLabel checkBoxLabel;
    JLabel startingDateLabel;
    JLabel endingDateLabel;
    JTextField startingDateTextField;
    JTextField endingDateTextField;
    JButton calculateButton;


    Offer() {
        setTitle("Offer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        initFrame();
        layoutFrame();
        setVisible(true);
    }

    private void initFrame() {
        offerLabel = new JLabel("Offer");
        checkBoxLabel = new JLabel();
        washing = new JCheckBoxMenuItem("WASHING");
        welding = new JCheckBoxMenuItem("WELDING");
        painting = new JCheckBoxMenuItem("PAINTING");
        sandBlasting = new JCheckBoxMenuItem("SAND BLASTING");
        overhaulingAndRepairOfStableEngines = new JCheckBoxMenuItem("OVERHAULING AND REPAIR OF STABLE ENGINES");
        repairOfElectricalEquipment = new JCheckBoxMenuItem("REPAIR OF ELECTRICAL EQUIPMENT");
        startingDateLabel = new JLabel("Starting date:");
        endingDateLabel = new JLabel("Ending date:");
        startingDateTextField = new JTextField();
        endingDateTextField = new JTextField();
        calculateButton = new JButton("Calculate");

    }

    private void layoutFrame() {
        offerLabel.setBounds(340, 20, 200, 50);
        offerLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        washing.setBounds(70, 250, 200, 50);
        welding.setBounds(70, 300, 200, 50);
        painting.setBounds(70, 350, 200, 50);
        sandBlasting.setBounds(70, 400, 200, 50);
        overhaulingAndRepairOfStableEngines.setBounds(70, 450, 400, 50);
        repairOfElectricalEquipment.setBounds(70, 500, 400, 50);
        checkBoxLabel.setText("Choose the services you want to use:");
        checkBoxLabel.setBounds(70, 150, 320, 50);
        checkBoxLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        startingDateLabel.setBounds(500, 150, 200, 50);
        startingDateLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        startingDateTextField.setBounds(500, 200, 200, 50);
        endingDateLabel.setBounds(500, 250, 200, 50);
        endingDateLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        endingDateTextField.setBounds(500, 300, 200, 50);
        startingDateTextField.setFont(new Font("MV Boli", Font.BOLD, 18));
        endingDateTextField.setFont(new Font("MV Boli", Font.BOLD, 18));
        calculateButton.setBounds(500, 500, 200, 100);
        calculateButton.setFont(new Font("MV Boli", Font.BOLD, 18));
        startingDateTextField.setText("DD/MM/YYYY");
        startingDateTextField.setDisabledTextColor(Color.GRAY);
        startingDateTextField.setEnabled(false);
        startingDateTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startingDateTextField.setText("");
                startingDateTextField.setEnabled(true);
            }
        });
        endingDateTextField.setEnabled(false);
        endingDateTextField.setText("DD/MM/YYYY");
        endingDateTextField.setDisabledTextColor(Color.GRAY);
        endingDateTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endingDateTextField.setText("");
                endingDateTextField.setEnabled(true);
            }
        });
        add(checkBoxLabel);
        add(repairOfElectricalEquipment);
        add(overhaulingAndRepairOfStableEngines);
        add(sandBlasting);
        add(painting);
        add(welding);
        add(offerLabel);
        add(washing);
        add(startingDateLabel);
        add(startingDateTextField);
        add(endingDateLabel);
        add(endingDateTextField);
        add(calculateButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {
            new Calculator();
        }

    }
}
