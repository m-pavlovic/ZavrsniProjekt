import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {

    private ViewPanel viewPanel;
    private BottomPanel bottomPanel;
    private ToolBarPanel toolBarPanel;
    private JFileChooser fileChooser;


    public MainFrame() {
        super("Price Calculator");
        setSize(800, 500);
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
        toolBarPanel = new ToolBarPanel();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(toolBarPanel, BorderLayout.NORTH);
    }

    private void activateApp() {
        bottomPanel.setBottomPanelListener(new BottomPanelListener() {
            @Override
            public void bottomPanelEventOccurred(BottomPanelEvent bpe) {
                String name = bpe.getName();
                String surname = bpe.getSurname();
                String paymentIn = bpe.getPaymentIn();
                viewPanel.setTextOnTextArea(name);
                viewPanel.setTextOnTextArea(surname);
                viewPanel.setTextOnTextArea(paymentIn);
            }
        });

        bottomPanel.activateComps();

        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void clearButtonEventOccurred(ToolBarEvent tbe) {
                viewPanel.clearAll();
            }

            @Override
            public void saveEventOccurred(ToolBarEvent tbe) {
                int value = fileChooser.showSaveDialog(MainFrame.this);
                if (JFileChooser.APPROVE_OPTION == value) {
                    File file = fileChooser.getSelectedFile();
                    viewPanel.saveToFile(file);
                }
            }
        });

        toolBarPanel.activateComps();
    }






}
