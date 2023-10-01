import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Calculator extends JFrame implements ActionListener {

    static JTextArea invoiceTextArea;
    private JScrollPane scrollPane;
    private JTextArea priceTextArea;
    private JButton confirmButton;
    private JButton cancelButton;
    private static int invoiceCounter = 1;
    static int searchID;
    static String searchName;
    static String searchDate;
    static HashMap<String, String> invoiceMap = new HashMap<>();


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

    /**
     * Initializes the frame components
     */
    private void initFrame() {
        invoiceTextArea = new JTextArea();
        scrollPane = new JScrollPane(invoiceTextArea);
        priceTextArea = new JTextArea();
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
    }

    /**
     * Lays out the frame components
     */

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

    /**
     * Saves the invoice to a file using a HashMap
     */

    private void saveInvoice() {
        invoiceMap.put("Invoice ID", String.valueOf(invoiceCounter));
        invoiceMap.put("------------------------------------------------------------\nCustomer", ViewPanel.customerInfo());
        invoiceMap.put("------------------------------------------------------------\nOFFER", Offer.getInvoiceText());
        saveToFile();

    }

    /**
     * Checks if the folder exists, if not, creates it
     */

    private void checkIfFolderExists() {
        File folder = new File("invoices");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * calls the checkIfFolderExists method and saves the invoice to a file
     */

    private void saveToFile() {
        checkIfFolderExists();
        try {
            File file = new File("invoices/invoice" + invoiceCounter + ".txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens a pane with options to search by ID or Name
     */

    static void searchInvoices() {
        String[] options = {"Search by ID", "Search by Name", "Search by Date"};
        int searchChoice = JOptionPane.showOptionDialog(null, "Search by ID, Name or Date?", "Search",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (searchChoice == 0) {
            searchID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID: "));
            if (JOptionPane.CANCEL_OPTION == 2) {
                JOptionPane.showMessageDialog(null, "Search cancelled!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                searchByID(searchID);
            }
        } else if (searchChoice == 1) {
            searchName = JOptionPane.showInputDialog(null, "Enter Name: ");
            if (JOptionPane.CANCEL_OPTION == 2) {
                JOptionPane.showMessageDialog(null, "Search cancelled!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                searchByName(searchName);
            }
        } else if (searchChoice == 2) {
            searchDate = JOptionPane.showInputDialog(null, "Enter Date: ");
            if (JOptionPane.CANCEL_OPTION == 2) {
                JOptionPane.showMessageDialog(null, "Search cancelled!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                searchByDate();
            }
        }
    }

    /**
     * Searches for an invoice by Date
     */

    private static void searchByDate() {
        File folder = new File("invoices");
        File[] listOfFiles = folder.listFiles();

        boolean found = false;

        for (File file : listOfFiles != null ? listOfFiles : new File[0]) {
            if (!file.isFile()) continue;

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(searchDate)) {
                        Desktop.getDesktop().open(file);
                        found = true;
                        break;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * Searches for an invoice by ID
     * @param searchID
     */

    private static void searchByID(int searchID) {
        File file = new File("invoices/invoice" + searchID + ".txt");
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Searches for an invoice by Name
     * @param searchName
     */

    private static void searchByName(String searchName) {
        File folder = new File("invoices");
        File[] listOfFiles = folder.listFiles();

        boolean found = false;

        for (File file : listOfFiles != null ? listOfFiles : new File[0]) {
            if (!file.isFile()) continue;

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().toLowerCase();
                    if (line.contains(searchName.toLowerCase())) {
                        Desktop.getDesktop().open(file);
                        found = true;
                        break;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}