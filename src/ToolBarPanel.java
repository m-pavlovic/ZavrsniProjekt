import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {

    private JButton clearButton;
    private JButton register;
    private ToolBarListener toolBarListener;


    public ToolBarPanel() {
        initPanelComps();
        layoutComps();
        activateComps();

    }


    private void initPanelComps() {
        clearButton = new JButton("Clear All!");
        register = new JButton("Register");
    }


    private void layoutComps() {
        add(clearButton);
        add(register);
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
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ToolBarEvent tbe = new ToolBarEvent(this);
                    toolBarListener.registerEventOccurred(tbe);
                }
            });
        }
    }
}
