import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Offer extends JFrame {

    JLabel offerLabel;
    static JCheckBoxMenuItem washing;
    static JCheckBoxMenuItem welding;
    static JCheckBoxMenuItem painting;
    static JCheckBoxMenuItem sandBlasting;
    static JCheckBoxMenuItem overhaulingAndRepairOfStableEngines;
    static JCheckBoxMenuItem repairOfElectricalEquipment;
    JLabel checkBoxLabel;
    JLabel startingDateLabel;
    JLabel endingDateLabel;
    JTextField startingDateTextField;
    JTextField endingDateTextField;
    JButton calculateButton;
    private OfferListener offerListener;
    private static String invoiceText;
    Date date = new Date();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    Offer() {
        setTitle("Offer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        initFrame();
        layoutFrame();
        activateComps();
        setVisible(true);
    }

    public static String getPrice() {
        return calculatePrice();
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
        offerLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 40));
        washing.setBounds(70, 250, 200, 50);
        welding.setBounds(70, 300, 200, 50);
        painting.setBounds(70, 350, 200, 50);
        sandBlasting.setBounds(70, 400, 200, 50);
        overhaulingAndRepairOfStableEngines.setBounds(70, 450, 400, 50);
        repairOfElectricalEquipment.setBounds(70, 500, 400, 50);
        checkBoxLabel.setText("Choose the services you want to use:");
        checkBoxLabel.setBounds(70, 150, 320, 50);
        checkBoxLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        startingDateLabel.setBounds(500, 150, 200, 50);
        startingDateLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        startingDateTextField.setBounds(500, 200, 200, 50);
        endingDateLabel.setBounds(500, 250, 200, 50);
        endingDateLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        endingDateTextField.setBounds(500, 300, 200, 50);
        startingDateTextField.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
        endingDateTextField.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
        calculateButton.setBounds(500, 500, 200, 100);
        calculateButton.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
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

    /**
     * Activates the components
     * 1. Sets the offer listener
     * 2. Adds action listener to the calculate button
     * 3. Creates a new OfferEvent object
     * 4. Calls the offerEventOccurred method from the OfferListener interface
     * 5. Calls the calculate method
     * 6. Creates a new Calculator object
     * 7. Disposes the frame
     */


    public void activateComps() {
        setOfferListener(new OfferListener() {
            @Override
            public void offerEventOccurred(OfferEvent oe) {
                washing.setState(oe.isWashingChecked());
                welding.setState(oe.isWeldingChecked());
                painting.setState(oe.isPaintingChecked());
                sandBlasting.setState(oe.isSandBlastingChecked());
                overhaulingAndRepairOfStableEngines.setState(oe.isEngineRepairChecked());
                repairOfElectricalEquipment.setState(oe.isElectricalRepairChecked());
                startingDateTextField.setText(oe.getStartDate());
                endingDateTextField.setText(oe.getEndDate());
            }
        });
        if (offerListener != null) {
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isWashingChecked = washing.getState();
                    boolean isWeldingChecked = welding.getState();
                    boolean isPaintingChecked = painting.getState();
                    boolean isSandBlastingChecked = sandBlasting.getState();
                    boolean isOverhaulingAndRepairOfStableEnginesChecked = overhaulingAndRepairOfStableEngines.getState();
                    boolean isRepairOfElectricalEquipmentChecked = repairOfElectricalEquipment.getState();
                    String startingDate = startingDateTextField.getText();
                    String endingDate = endingDateTextField.getText();
                    OfferEvent oe = new OfferEvent(this, isWashingChecked, isWeldingChecked,
                            isPaintingChecked, isSandBlastingChecked, isOverhaulingAndRepairOfStableEnginesChecked,
                            isRepairOfElectricalEquipmentChecked, startingDate, endingDate);
                    offerListener.offerEventOccurred(oe);
                    if (checkDate(startingDate) && checkDate(endingDate)) {
                        calculate();
                        new Calculator();
                        dispose();
                    }
                }
            });
        }

    }

    public void setOfferListener(OfferListener offerListener) {
        this.offerListener = offerListener;
    }
/**
calculate() method is used to calculate the price of the services that the user has chosen.
 */
    public void calculate() {
        String invoice = "INVOICE\n\n" + "Date: " + formatter.format(date) + "\n\n" + "Services:\n\n";
        if (washing.getState()) {
            invoice += "WASHING\n";
        }
        if (welding.getState()) {
            invoice += "WELDING\n";
        }
        if (painting.getState()) {
            invoice += "PAINTING\n";
        }
        if (sandBlasting.getState()) {
            invoice += "SAND BLASTING\n";
        }
        if (overhaulingAndRepairOfStableEngines.getState()) {
            invoice += "OVERHAULING AND REPAIR OF STABLE ENGINES\n";
        }
        if (repairOfElectricalEquipment.getState()) {
            invoice += "REPAIR OF ELECTRICAL EQUIPMENT\n";
        }
        invoice += "\n";
        invoice += "Starting date: " + startingDateTextField.getText() + "\n";
        invoice += "Ending date: " + endingDateTextField.getText() + "\n\n";
        invoice += "Price: " + calculatePrice() + " EUR";
        invoiceText = invoice;
    }

    /**
     * getInvoiceText() method is used to get the invoice text.
     * @return the invoice text.
     */
    public static String getInvoiceText() {
        return invoiceText;
    }

    /**
     * calculatePrice() method is used to calculate the price of the services that the user has chosen.
     * @return the price of the services.
     */
    private static String calculatePrice() {
        int price = 0;
        if (washing.getState()) {
            price += 1000;
        }
        if (welding.getState()) {
            price += 1400;
        }
        if (painting.getState()) {
            price += 1350;
        }
        if (sandBlasting.getState()) {
            price += 1500;
        }
        if (overhaulingAndRepairOfStableEngines.getState()) {
            price += 2000;
        }
        if (repairOfElectricalEquipment.getState()) {
            price += 1600;
        }
        return String.valueOf(price);
    }

    public static boolean checkDate(String searchDate) {
        try {
            formatter.setLenient(false);
            formatter.parse(searchDate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date!");
            return false;
        }
        return true;
    }
}

