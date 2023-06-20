import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {

    private JButton confirmButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JRadioButton radioPython;
    private JRadioButton radioJava;
    private JRadioButton radioSwing;
    private ButtonGroup programmingGroup;
    private BottomPanelListener bottomPanelListener;

    public BottomPanel() {
        initPanelComps();
        layoutComps();
        activateComps();
    }

    private void initPanelComps() {
        Dimension dims = getPreferredSize();
        dims.height = 150;
        setPreferredSize(dims);
        confirmButton = new JButton("Click me!");
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        radioPython = new JRadioButton("Python");
        radioPython.setActionCommand("Python");
        radioJava = new JRadioButton("Java");
        radioJava.setActionCommand("Java");
        radioSwing = new JRadioButton("Swing");
        radioSwing.setActionCommand("Swing");
        programmingGroup = new ButtonGroup();
        programmingGroup.add(radioPython);
        programmingGroup.add(radioJava);
        programmingGroup.add(radioSwing);
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(new JLabel("Name: "), gbc);
        gbc.gridx++;
        add(nameField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        add(new JLabel("Surname: "), gbc);
        gbc.gridx++;
        add(surnameField, gbc);
        gbc.gridy--;
        gbc.gridx++;
        add(Box.createHorizontalStrut(15), gbc);
        gbc.gridx++;
        add(new JLabel("Programming in: "), gbc);
        gbc.gridx++;
        gbc.gridx++;
        gbc.gridy++;
        add(radioPython, gbc);
        gbc.gridy++;
        add(radioJava, gbc);
        gbc.gridy++;
        add(radioSwing, gbc);
        gbc.gridy++;
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridx--;
        add(confirmButton, gbc);

    }

    public void activateComps() {
        if (bottomPanelListener != null) {
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String programming = programmingGroup.getSelection().getActionCommand();
                    BottomPanelEvent bpe = new BottomPanelEvent(this, name, surname, programming);
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
        programmingGroup.clearSelection();
    }
}
