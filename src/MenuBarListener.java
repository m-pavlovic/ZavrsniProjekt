import java.util.EventListener;

public interface MenuBarListener extends EventListener {

    /**
     * Handles the event when the clear button is clicked
     * @param tbe
     */

    void clearButtonEventOccurred(MenuBarEvent tbe);

    /**
     * Handles the event when the save button is clicked
     * @param tbe
     */
    void saveEventOccurred(MenuBarEvent tbe);

    /**
     * Handles the event when the load button is clicked
     * @param tbe
     */

    void loadEventOccurred(MenuBarEvent tbe);

    /**
     * Handles the event when the search button is clicked
     * @param tbe
     */

    void searchEventOccurred(MenuBarEvent tbe);
}
