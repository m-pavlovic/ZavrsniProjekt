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
    private String shipName;
    private String shipType;
    private int shipLenght;
    public BottomPanelEvent(Object source, String name, String surname, String paymentIn, String shipName, String shipType, int shipLenght) {
        super(source);
        this.name = name;
        this.surname = surname;
        this.paymentIn = paymentIn;
        this.shipName = shipName;
        this.shipType = shipType;
        this.shipLenght = shipLenght;
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

    public String getShipName() {
        return shipName;
    }

    public String getShipType() {
        return shipType;
    }

    public int getShipLenght() {
        return shipLenght;
    }
}
