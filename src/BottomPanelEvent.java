import java.util.EventObject;

public class BottomPanelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private String name;
    private String surname;
    private String paymentIn;
    public BottomPanelEvent(Object source, String name, String surname, String paymentIn) {
        super(source);
        this.name = name;
        this.surname = surname;
        this.paymentIn = paymentIn;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPaymentIn() {
        return paymentIn;
    }
}
