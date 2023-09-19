import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    JTextArea invoiceTextArea;
    JScrollPane scrollPane;
    JTextArea priceTextArea;
    JButton calculateButton;


    Calculator() {

        setTitle("Invoice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        initFrame();
        layoutFrame();
        setVisible(true);

    }

    private void initFrame() {
        invoiceTextArea = new JTextArea();
        scrollPane = new JScrollPane(invoiceTextArea);
        priceTextArea = new JTextArea();
        calculateButton = new JButton("Calculate");
    }

    private void layoutFrame() {
        invoiceTextArea.setPreferredSize(new Dimension(400, 700));
        invoiceTextArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 400, 700);
        add(scrollPane);
        priceTextArea.setPreferredSize(new Dimension(400, 700));
        priceTextArea.setEditable(false);
        priceTextArea.setBounds(400, 0, 400, 300);
        add(priceTextArea);
        calculateButton.setBounds(500, 400, 200, 200);
        add(calculateButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {
            priceTextArea.setText("Hello");
        }

    }
}
