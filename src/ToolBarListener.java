import java.util.EventListener;

public interface ToolBarListener extends EventListener {

    void clearButtonEventOccurred(ToolBarEvent tbe);
    void saveEventOccurred(ToolBarEvent tbe);
}
