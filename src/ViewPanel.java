import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ViewPanel extends JPanel {

    private JTextArea textArea;
    public ViewPanel() {
        initPanelComps();
        layoutComps();

    }

    private void initPanelComps() {
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(800, 200));
        textArea.setEditable(false);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(textArea, BorderLayout.CENTER);
    }

    public void setTextOnTextArea(String someText) {
        textArea.append(someText + "\n");
    }

    public void clearAll() {
        textArea.setText("");
    }


    public void saveToFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(textArea.getText().getBytes());
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not save file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadFromFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}