import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBarPanel extends JPanel {

    private MenuBarListener menuBarListener;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu searchMenu;
    private JMenu clearMenu;
    private JMenuItem load;
    private JMenuItem save;
    private ImageIcon loadIcon;
    private ImageIcon saveIcon;


    public MenuBarPanel() {
        initPanelComps();
        layoutComps();
        activateComps();

    }


    private void initPanelComps() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        searchMenu = new JMenu("Search");
        clearMenu = new JMenu("Clear");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        loadIcon = new ImageIcon("icons/load.png");
        saveIcon = new ImageIcon("icons/save.png");
    }


    private void layoutComps() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(file);
        menuBar.add(searchMenu);
        menuBar.add(clearMenu);
        file.add(load);
        file.add(save);
        loadIcon.setImage(loadIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        load.setIcon(loadIcon);
        save.setIcon(saveIcon);
        load.setMnemonic(KeyEvent.VK_L);
        save.setMnemonic(KeyEvent.VK_S);
        file.setMnemonic(KeyEvent.VK_F);
        searchMenu.setMnemonic(KeyEvent.VK_S);
        clearMenu.setMnemonic(KeyEvent.VK_C);
        add(menuBar);
    }

    public void setMenuBarListener(MenuBarListener menuBarListener) {
        this.menuBarListener = menuBarListener;
    }

    public void activateComps() {
        if (menuBarListener != null) {
            load.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuBarListener.loadEventOccurred(new MenuBarEvent(this));
                }
            });
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuBarListener.saveEventOccurred(new MenuBarEvent(this));
                }
            });
            searchMenu.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    menuBarListener.searchEventOccurred(new MenuBarEvent(this));
                }
            });
            clearMenu.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    menuBarListener.clearButtonEventOccurred(new MenuBarEvent(this));
                }
            });
        }
    }
}
