import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class ViewPanel extends JPanel {

    private static JTextArea textArea;
    private JScrollPane scrollPane;
    public ViewPanel() {
        initPanelComps();
        layoutComps();

    }

    private void initPanelComps() {
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(800, 280));
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets the text on the text area
     * @param someText
     */
    public void setTextOnTextArea(String someText) {
        textArea.append(someText + "\n");
    }

    /**
     * Clears the text area
     */
    public void clearAll() {
        textArea.setText("");
    }

    /**
     * Saves the text from the text area to a file
     * @param file
     */
    public void saveToFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(textArea.getText().getBytes());
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not save file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String customerInfo() {
        return textArea.getText();
    }
}