import java.util.EventObject;

public class MenuBarEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private boolean clearButtonClicked;
    private boolean saveFileButtonClicked;
    private boolean loadFileButtonClicked;
    private boolean searchButtonClicked;
    public MenuBarEvent(Object source) {
        super(source);
        this.clearButtonClicked = false;
        this.saveFileButtonClicked = false;
        this.loadFileButtonClicked = false;
        this.searchButtonClicked = false;
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

    public boolean isLoadFileButtonClicked() {
        return loadFileButtonClicked;
    }

    public void setLoadFileButtonClicked(boolean loadFileButtonClicked) {
        this.loadFileButtonClicked = loadFileButtonClicked;
    }

    public boolean isSearchButtonClicked() {
        return searchButtonClicked;
    }

    public void setSearchButtonClicked(boolean searchButtonClicked) {
        this.searchButtonClicked = searchButtonClicked;
    }
}
