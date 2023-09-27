import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {

    private JButton confirmButton;
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
    private JLabel sliderLabel;
    private JLabel typeOfPayment;
    private JRadioButton radioCash;
    private JRadioButton radioCard;
    private ButtonGroup paymentGroup;
    private BottomPanelListener bottomPanelListener;
    private ImageIcon icon;
    private JLabel iconLabel;
    private JButton nextButton;

    public BottomPanel() {
        initPanelComps();
        layoutComps();
        activateComps();
    }

    private void initPanelComps() {
        Dimension dims = getPreferredSize();
        dims.height = 300;
        setPreferredSize(dims);
        confirmButton = new JButton("Confirm");
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(15);
        nameOfShip = new JLabel("Name of ship:");
        nameOfShipField = new JTextField(15);
        typeOfShip = new JLabel("Type of ship:");
        typeOfShipBox = new JComboBox<>(shipTypes);
        shipLength = new JLabel("Length of ship:");
        shipLengthSlider = new JSlider(5, 65, 5);
        sliderLabel = new JLabel();
        typeOfPayment = new JLabel("Type of payment:");
        radioCash = new JRadioButton("Cash");
        radioCash.setActionCommand("Cash");
        radioCard = new JRadioButton("Card");
        radioCard.setActionCommand("Card");
        paymentGroup = new ButtonGroup();
        paymentGroup.add(radioCash);
        paymentGroup.add(radioCard);
        icon = new ImageIcon("icons/anchor.jpg");
        iconLabel = new JLabel(icon);
        nextButton = new JButton("Next");
    }

    private void layoutComps() {
        setLayout(null);
        confirmButton.setBounds(620, 150, 120, 40);
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
        shipLengthSlider.setPreferredSize(new Dimension(200, 50));
        shipLengthSlider.setBounds(150, 230, 200, 50);
        shipLengthSlider.setMajorTickSpacing(10);
        shipLengthSlider.setMinorTickSpacing(5);
        shipLengthSlider.setPaintTicks(true);
        shipLengthSlider.setPaintTrack(true);
        shipLengthSlider.setPaintLabels(true);
        sliderLabel.setBounds(410, 230, 120, 20);
        sliderLabel.setFont(new Font("Serif", Font.BOLD, 15));
        shipLengthSlider.addChangeListener(e -> sliderLabel.setText("Length: " + shipLengthSlider.getValue()));
        icon.setImage(icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        iconLabel.setIcon(icon);
        iconLabel.setBounds(650, 50, 70, 70);
        nextButton.setBounds(620, 200, 120, 40);
        add(iconLabel);
        add(nameOfShip);
        add(nameOfShipField);
        add(typeOfShip);
        add(shipLength);
        add(confirmButton);
        add(nameLabel);
        add(nameField);
        add(surnameLabel);
        add(surnameField);
        add(typeOfPayment);
        add(radioCash);
        add(radioCard);
        add(typeOfShipBox);
        add(shipLengthSlider);
        add(sliderLabel);
        add(nextButton);


    }

    public void activateComps() {
        if (bottomPanelListener != null) {
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String name = nameField.getText();
                        String surname = surnameField.getText();
                        String payment = paymentGroup.getSelection().getActionCommand();
                        String shipName = nameOfShipField.getText();
                        String shipType = (String) typeOfShipBox.getSelectedItem();
                        int shipLength = shipLengthSlider.getValue();
                        BottomPanelEvent bpe = new BottomPanelEvent(this, name, surname, payment, shipName, shipType, shipLength);
                        bottomPanelListener.bottomPanelEventOccurred(bpe);
                        resetForm();
                        nextButton.setEnabled(true);
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Offer();
                }
            });
        }
    }

    public void setBottomPanelListener(BottomPanelListener bottomPanelListener) {
        this.bottomPanelListener = bottomPanelListener;
    }

    public void resetForm() {
        nameField.setText(null);
        surnameField.setText(null);
        paymentGroup.clearSelection();
        nameOfShipField.setText(null);
        typeOfShipBox.setSelectedIndex(0);
        shipLengthSlider.setValue(5);
    }
}
