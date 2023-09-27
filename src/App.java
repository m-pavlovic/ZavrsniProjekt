import javax.swing.*;

public class App {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
//toolbar u menu bar - search za invoices i customers
// invoices se spremaju u invoices folder - search moze po id i po datumu i po customeru
// confirm botun - da se spremi invoice u invoices folder
// dodati error prozore za los input
// dodati load button za customers
