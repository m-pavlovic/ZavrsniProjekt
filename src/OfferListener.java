import java.util.EventListener;

public interface OfferListener extends EventListener {

    /**
     * Handles the event when the offer button is clicked
     * @param oe
     */

    void offerEventOccurred(OfferEvent oe);
}
