import javax.swing.event.ChangeListener;
import java.util.EventListener;

public interface BottomPanelListener extends EventListener {

    void bottomPanelEventOccurred(BottomPanelEvent bpe);
}
