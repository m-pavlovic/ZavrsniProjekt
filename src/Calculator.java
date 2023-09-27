import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Calculator extends JFrame implements ActionListener {

    static JTextArea invoiceTextArea;
    private JScrollPane scrollPane;
    private JTextArea priceTextArea;
    private JButton confirmButton;
    private JButton cancelButton;
    private static int invoiceCounter = 1;
    static int searchID;
    static String searchName;
    static HashMap<String, String> invoiceMap;
    static TreeMap<Integer, HashMap<String, String>> invoiceTreeMap;


    Calculator() {
        invoiceTreeMap = new TreeMap<>();
        invoiceMap = new HashMap<>();
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
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
    }

    private void layoutFrame() {
        invoiceTextArea.setPreferredSize(new Dimension(400, 700));
        invoiceTextArea.setEditable(false);
        invoiceTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));
        invoiceTextArea.setText(Offer.getInvoiceText());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 400, 650);
        add(scrollPane);
        priceTextArea.setPreferredSize(new Dimension(400, 650));
        priceTextArea.setEditable(false);
        priceTextArea.setBounds(400, 0, 380, 300);
        priceTextArea.setFont(new Font("Calibri", Font.BOLD, 20));
        priceTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        priceTextArea.setText("---------------------------------------------\n" +
                "---------------------------------------------\n" + "\nPRICE: " + Offer.getPrice() + " €\n"
                + "---------------------------------------------\n" +
                "---------------------------------------------\n");
        add(priceTextArea);
        confirmButton.setBounds(500, 400, 200, 80);
        confirmButton.setFont(new Font(null, Font.BOLD, 18));
        add(confirmButton);
        cancelButton.setBounds(500, 500, 200, 80);
        cancelButton.setFont(new Font(null, Font.BOLD, 18));
        add(cancelButton);
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == confirmButton) {
            saveInvoice();
            JOptionPane.showMessageDialog(null, "Invoice saved!");
            invoiceCounter++;
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }

    }

    private void saveInvoice() {
        invoiceMap.put(ViewPanel.customerInfo(), Offer.getInvoiceText());
        invoiceTreeMap.put(invoiceCounter, invoiceMap);
        saveToFile(invoiceTreeMap);

    }

    static void searchInvoices() {
        String[] options = {"Search by ID", "Search by Name"};
        int searchChoice = JOptionPane.showOptionDialog(null, "Search by ID or Name?", "Search",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (searchChoice == 0) {
            searchID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID: "));
            searchByID(searchID);
        } else if (searchChoice == 1) {
            searchName = JOptionPane.showInputDialog(null, "Enter Name: ");
            searchByName(searchName);
        }
    }

    private static void searchByID(int searchID) {
        StringBuilder result = new StringBuilder();
        for (HashMap<String, String> invoiceMap : invoiceTreeMap.values()) {
            for (HashMap.Entry<String, String> entry : invoiceMap.entrySet()) {
                if (entry.getKey().startsWith("Invoice") && entry.getKey().contains(String.valueOf(searchID))) {
                    result.append(entry.getValue()).append("\n");
                }
            }
        }
        if (result.length() > 0) {
            invoiceTextArea.setText(result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Invoice not found!");
        }
    }

    private static void searchByName(String searchName) {
        StringBuilder result = new StringBuilder();
        for (HashMap<String, String> invoiceMap : invoiceTreeMap.values()) {
            for (HashMap.Entry<String, String> entry : invoiceMap.entrySet()) {
                if (entry.getValue().toLowerCase().contains(searchName.toLowerCase())) {
                    result.append(entry.getValue()).append("\n");
                }
            }
        }
        if (result.length() > 0) {
            invoiceTextArea.setText(result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Invoice not found!");
        }
    }


    private void saveToFile(TreeMap<Integer, HashMap<String, String>> invoiceTreeMap) {
        String filePath = "invoices/invoices.txt";
        File invoicesDirectory = new File("invoices");

        if (!invoicesDirectory.exists()) {
            if (invoicesDirectory.mkdirs()) {
                JOptionPane.showMessageDialog(null, "Created 'invoices' directory.");
            } else {
                JOptionPane.showMessageDialog(null, "Could not create 'invoices' directory.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (HashMap.Entry<Integer, HashMap<String, String>> entry : invoiceTreeMap.entrySet()) {
                writer.write("Invoice " + entry.getKey() + ":\n");
                for (HashMap.Entry<String, String> invoiceEntry : entry.getValue().entrySet()) {
                    writer.write(invoiceEntry.getKey() + ":\n");
                    writer.write(invoiceEntry.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}