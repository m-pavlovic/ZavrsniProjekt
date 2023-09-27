import java.util.EventListener;

public interface MenuBarListener extends EventListener {

    void clearButtonEventOccurred(MenuBarEvent tbe);
    void saveEventOccurred(MenuBarEvent tbe);

    void loadEventOccurred(MenuBarEvent tbe);

    void searchEventOccurred(MenuBarEvent tbe);
}
