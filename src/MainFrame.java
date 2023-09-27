import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class MainFrame extends JFrame {

    private ViewPanel viewPanel;
    private BottomPanel bottomPanel;
    private MenuBarPanel menuBarPanel;
    private JFileChooser fileChooser;


    public MainFrame() {
        super("Price Calculator");
        setSize(800, 700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initFrameComps();
        layoutComps();
        activateApp();

    }

    private void initFrameComps() {
        viewPanel = new ViewPanel();
        bottomPanel = new BottomPanel();
        menuBarPanel = new MenuBarPanel();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(menuBarPanel, BorderLayout.NORTH);
    }

    /**
     * Activates the app
     * 1. Sets the text on the text area
     * 2. Clears the text area
     * 3. Saves the text from the text area to a file
     * 4. Enables the clear and save buttons
     */
    private void activateApp() {
        bottomPanel.setBottomPanelListener(new BottomPanelListener() {
            @Override
            public void bottomPanelEventOccurred(BottomPanelEvent bpe) {
                viewPanel.setTextOnTextArea(bpe.getName());
                viewPanel.setTextOnTextArea(bpe.getSurname());
                viewPanel.setTextOnTextArea(bpe.getPaymentIn());
                viewPanel.setTextOnTextArea(bpe.getShipName());
                viewPanel.setTextOnTextArea(bpe.getShipType());
                viewPanel.setTextOnTextArea(String.valueOf(bpe.getShipLenght()));
            }
        });

        bottomPanel.activateComps();

        menuBarPanel.setMenuBarListener(new MenuBarListener() {
            @Override
            public void clearButtonEventOccurred(MenuBarEvent tbe) {
                viewPanel.clearAll();
                bottomPanel.resetForm();
            }

            @Override
            public void saveEventOccurred(MenuBarEvent tbe) {
                int value = fileChooser.showSaveDialog(MainFrame.this);
                if (JFileChooser.APPROVE_OPTION == value) {
                    File file = fileChooser.getSelectedFile();
                    viewPanel.saveToFile(file);
                }
            }

            @Override
            public void loadEventOccurred(MenuBarEvent tbe) {
                int value = fileChooser.showOpenDialog(MainFrame.this);
                if (JFileChooser.APPROVE_OPTION == value) {
                    File file = fileChooser.getSelectedFile();
                    viewPanel.clearAll();
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = fis.read(buffer)) != -1) {
                            String text = new String(buffer, 0, read);
                            viewPanel.setTextOnTextArea(text);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            fis.close();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Could not close file!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

            }

            @Override
            public void searchEventOccurred(MenuBarEvent tbe) {
                String search = JOptionPane.showInputDialog(null, "Enter search term:", "Search", JOptionPane.QUESTION_MESSAGE);
                if (search != null) {
                    File file = new File("invoices/invoices.txt");
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = fis.read(buffer)) != -1) {
                            String text = new String(buffer, 0, read);
                            if (text.toLowerCase().contains(search.toLowerCase())) {
                                viewPanel.setTextOnTextArea(text);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            fis.close();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Could not close file!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }


            }
        });

        menuBarPanel.activateComps();
    }






}
