import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {

    private JButton calculateButton;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField surnameField;
    private JLabel surnameLabel;
    private JLabel nameOfShip;
    private JTextField nameOfShipField;
    private JLabel typeOfShip;
    private String[] shipTypes = {"Gulet", "Jahta", "Koća", "Plivarica", "Jedrilica", "Katamaran", "Ponton"};
    private JComboBox<String> typeOfShipBox;
    private JLabel shipLength;
    private JSlider shipLengthSlider;
    private JLabel typeOfPayment;
    private JRadioButton radioCash;
    private JRadioButton radioCard;
    private ButtonGroup paymentGroup;
    private BottomPanelListener bottomPanelListener;

    public BottomPanel() {
        initPanelComps();
        layoutComps();
        activateComps();
    }

    private void initPanelComps() {
        Dimension dims = getPreferredSize();
        dims.height = 300;
        setPreferredSize(dims);
        calculateButton = new JButton("Calculate");
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(15);
        nameOfShip = new JLabel("Name of ship:");
        nameOfShipField = new JTextField(15);
        typeOfShip = new JLabel("Type of ship:");
        typeOfShipBox = new JComboBox<>(shipTypes);
        shipLength = new JLabel("Length of ship:");
        typeOfPayment = new JLabel("Type of payment:");
        radioCash = new JRadioButton("Cash");
        radioCash.setActionCommand("Cash");
        radioCard = new JRadioButton("Card");
        radioCard.setActionCommand("Card");
        paymentGroup = new ButtonGroup();
        paymentGroup.add(radioCash);
        paymentGroup.add(radioCard);
    }

    private void layoutComps() {
        setLayout(null);
        calculateButton.setBounds(620, 90, 120, 40);
        nameLabel.setBounds(20, 30, 120, 20);
        nameField.setBounds(150, 30, 120, 20);
        surnameLabel.setBounds(20, 80, 120, 20);
        surnameField.setBounds(150, 80, 120, 20);
        typeOfPayment.setBounds(350, 30, 150, 20);
        radioCash.setBounds(410, 80, 120, 20);
        radioCard.setBounds(410, 130, 120, 20);
        nameOfShip.setBounds(20, 130, 120, 20);
        nameOfShipField.setBounds(150, 130, 120, 20);
        typeOfShip.setBounds(20, 180, 120, 20);
        shipLength.setBounds(20, 230, 120, 20);
        typeOfShipBox.setBounds(150,180, 120, 20);
        add(nameOfShip);
        add(nameOfShipField);
        add(typeOfShip);
        add(shipLength);
        add(calculateButton);
        add(nameLabel);
        add(nameField);
        add(surnameLabel);
        add(surnameField);
        add(typeOfPayment);
        add(radioCash);
        add(radioCard);
        add(typeOfShipBox);

    }

    public void activateComps() {
        if (bottomPanelListener != null) {
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String payment = paymentGroup.getSelection().getActionCommand();
                    BottomPanelEvent bpe = new BottomPanelEvent(this, name, surname, payment);
                    bottomPanelListener.bottomPanelEventOccurred(bpe);
                    resetForm();
                }
            });
        }
    }

    public void setBottomPanelListener(BottomPanelListener bottomPanelListener) {
        this.bottomPanelListener = bottomPanelListener;
    }

    private void resetForm() {
        nameField.setText(null);
        surnameField.setText(null);
        paymentGroup.clearSelection();
    }
}
