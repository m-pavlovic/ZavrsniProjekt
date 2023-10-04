import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ViewPanel extends JPanel {

    private static JTextArea textArea;
    private JScrollPane scrollPane;
    public ViewPanel() {
        initPanelComps();
        layoutComps();

    }

    private void initPanelComps() {
        textArea = new JTextArea(12, 38);
        textArea.setEditable(false);
        textArea.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
        scrollPane = new JScrollPane(textArea);
    }

    private void layoutComps() {

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(textArea);
        add(scrollPane);
    }

    /**
     * Sets the text on the text area
     * @param someText
     */
    public static void setTextOnTextArea(String someText) {
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

    /**
     * Returns the text from the text area with all the customer info
     * @return
     */

    public static String customerInfo() {
        return textArea.getText();
    }
}