import javax.swing.event.ChangeListener;
import java.util.EventListener;

public interface BottomPanelListener extends EventListener {


    /**
     * Handles the event when the confirm button is clicked
     * @param bpe
     */
    void bottomPanelEventOccurred(BottomPanelEvent bpe);
}
