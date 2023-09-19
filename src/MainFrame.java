import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private ViewPanel viewPanel;
    private BottomPanel bottomPanel;
    private ToolBarPanel toolBarPanel;
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
                viewPanel.setTextOnTextArea(bpe.getName());
                viewPanel.setTextOnTextArea(bpe.getSurname());
                viewPanel.setTextOnTextArea(bpe.getPaymentIn());
                viewPanel.setTextOnTextArea(bpe.getShipName());
                viewPanel.setTextOnTextArea(bpe.getShipType());
                viewPanel.setTextOnTextArea(String.valueOf(bpe.getShipLenght()));
            }
        });

        bottomPanel.activateComps();

        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void clearButtonEventOccurred(ToolBarEvent tbe) {
                viewPanel.clearAll();
                bottomPanel.resetForm();
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
