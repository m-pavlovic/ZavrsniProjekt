import java.util.EventObject;

public class ToolBarEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private boolean clearButtonClicked;
    private boolean readFileButtonClicked;
    public ToolBarEvent(Object source) {
        super(source);
        this.clearButtonClicked = false;
        this.readFileButtonClicked = false;
    }

    public boolean isClearButtonClicked() {
        return clearButtonClicked;
    }

    public void setClearButtonClicked(boolean clearButtonClicked) {
        this.clearButtonClicked = clearButtonClicked;
    }

    public boolean isReadFileButtonClicked() {
        return readFileButtonClicked;
    }

    public void setReadFileButtonClicked(boolean readFileButtonClicked) {
        this.readFileButtonClicked = readFileButtonClicked;
    }
}
