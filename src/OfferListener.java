import java.util.EventListener;

public interface OfferListener extends EventListener {

    void offerEventOccurred(OfferEvent oe);
}
