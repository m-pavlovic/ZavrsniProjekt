import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {

    private JButton clearButton;
    private JButton save;
    private ToolBarListener toolBarListener;


    public ToolBarPanel() {
        initPanelComps();
        layoutComps();
        activateComps();

    }


    private void initPanelComps() {
        clearButton = new JButton("Clear All!");
        save = new JButton("Save");
    }


    private void layoutComps() {
        save.setPreferredSize(new Dimension(100, 40));
        clearButton.setPreferredSize(new Dimension(100, 40));
        add(clearButton);
        add(save);
    }

    public void setToolBarListener(ToolBarListener toolBarListener) {
        this.toolBarListener = toolBarListener;
    }

    public void activateComps() {
        if (toolBarListener != null) {
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ToolBarEvent tbe = new ToolBarEvent(this);
                    toolBarListener.clearButtonEventOccurred(tbe);
                }
            });
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ToolBarEvent tbe = new ToolBarEvent(this);
                    toolBarListener.saveEventOccurred(tbe);
                }
            });
        }
    }
}
