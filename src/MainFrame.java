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
        super("Simple GUI App");
        setSize(670, 400);
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
                String programmingIn = bpe.getProgrammingIn();
                viewPanel.setTextOnTextArea(name);
                viewPanel.setTextOnTextArea(surname);
                viewPanel.setTextOnTextArea(programmingIn);
            }
        });

        bottomPanel.activateComps();

        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void clearButtonEventOccurred(ToolBarEvent tbe) {
                viewPanel.clearAll();
            }

            @Override
            public void registerEventOccurred(ToolBarEvent tbe) {
                int value = fileChooser.showOpenDialog(MainFrame.this);
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try(BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            viewPanel.setTextOnTextArea(line + "\n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        toolBarPanel.activateComps();
    }






}
