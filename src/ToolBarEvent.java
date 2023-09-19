import java.util.EventObject;

public class ToolBarEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private boolean clearButtonClicked;
    private boolean saveFileButtonClicked;
    public ToolBarEvent(Object source) {
        super(source);
        this.clearButtonClicked = false;
        this.saveFileButtonClicked = false;
    }

    public boolean isClearButtonClicked() {
        return clearButtonClicked;
    }

    public void setClearButtonClicked(boolean clearButtonClicked) {
        this.clearButtonClicked = clearButtonClicked;
    }

    public boolean isSaveFileButtonClicked() {
        return saveFileButtonClicked;
    }

    public void setSaveFileButtonClicked(boolean saveFileButtonClicked) {
        this.saveFileButtonClicked = saveFileButtonClicked;
    }
}
