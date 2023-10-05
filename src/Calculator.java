import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
    static LinkedHashMap<String, String> invoiceMap = new LinkedHashMap<>();


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
        invoiceTextArea.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
        invoiceTextArea.setText(Offer.getInvoiceText());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 400, 650);
        add(scrollPane);
        priceTextArea.setPreferredSize(new Dimension(400, 650));
        priceTextArea.setEditable(false);
        priceTextArea.setBounds(400, 0, 380, 300);
        priceTextArea.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
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
        invoiceMap.put("******************************************************", "");
        //saveToFile();
        saveToOneFile();

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

//    /**
//     * calls the checkIfFolderExists method and saves the invoice to a file
//     */
//
//    private void saveToFile() {
//        checkIfFolderExists();
//        try {
//            File file = new File("invoices/invoice" + invoiceCounter + ".txt");
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
//                bw.write(entry.getKey() + ": " + entry.getValue() + "\n");
//            }
//            bw.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * Saves all invoices to one file
     */
    private void saveToOneFile() {
        checkIfFolderExists();
        try (FileWriter fw = new FileWriter("invoices/invoice.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
                out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not save file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    /**
     * Opens a pane with options to search by ID or Name
     */

    static void searchInvoices() {
        String[] options = {"Search by ID", "Search by Name"};
        int searchChoice = JOptionPane.showOptionDialog(null, "Search by ID or Name?", "Search",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (searchChoice == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Search cancelled!");
            return;
        }

        try {
            if (searchChoice == 0) {
                String input = JOptionPane.showInputDialog(null, "Enter ID:");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Search cancelled!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                searchID = Integer.parseInt(input);
                searchByID(searchID);
            } else if (searchChoice == 1) {
                searchName = JOptionPane.showInputDialog(null, "Enter Name:");
                if (searchName == null) {
                    JOptionPane.showMessageDialog(null, "Search cancelled!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                searchByName(searchName);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Searches for an invoice by ID
     *
     * @param searchID The ID to search for
     */
    private static void searchByID(int searchID) {
        File file = new File("invoices/invoice.txt");

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                StringBuilder invoiceText = new StringBuilder();
                boolean found = false;

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains("Invoice ID: " + searchID)) {
                        found = true;
                    }

                    if (found) {
                        invoiceText.append(line).append("\n");
                        if (line.contains("******************************************************")) {
                            break;
                        }
                    }
                }

                if (found) {
                    ViewPanel.setTextOnTextArea(invoiceText.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


//    private static void searchByID(int searchID) {
//        File file = new File("invoices/invoice" + searchID + ".txt");
//        if (file.exists()) {
//            try {
//                Desktop.getDesktop().open(file);
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//    }

    /**
     * Searches for an invoice by Name
     *
     * @param searchName
     */

    private static void searchByName(String searchName) {
        File file = new File("invoices/invoice.txt");

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                StringBuilder invoiceText = new StringBuilder();
                boolean found = false;

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().toUpperCase();
                    if (line.contains(searchName.toUpperCase())) {
                        found = true;
                        invoiceText.append(line).append("\n");

                        while (scanner.hasNextLine()) {
                            String nextLine = scanner.nextLine();
                            invoiceText.append(nextLine).append("\n");
                            if (nextLine.contains("******************************************************")) {
                                break;
                            }
                        }
                        ViewPanel.setTextOnTextArea(invoiceText.toString());
                        invoiceText.setLength(0);
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }


//    private static void searchByName(String searchName) {
//        File folder = new File("invoices");
//        File[] listOfFiles = folder.listFiles();
//
//        boolean found = false;
//
//        for (File file : listOfFiles != null ? listOfFiles : new File[0]) {
//            if (!file.isFile()) continue;
//
//            try (Scanner scanner = new Scanner(file)) {
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine().toLowerCase();
//                    if (line.contains(searchName.toLowerCase())) {
//                        Desktop.getDesktop().open(file);
//                        found = true;
//                        break;
//                    }
//                }
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//
//        if (!found) {
//            JOptionPane.showMessageDialog(null, "Invoice does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    }
}