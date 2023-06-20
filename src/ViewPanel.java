import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private JTextArea textArea;
    public ViewPanel() {
        initPanelComps();
        layoutComps();

    }

    private void initPanelComps() {
        textArea = new JTextArea();
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




}