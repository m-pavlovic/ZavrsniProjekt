import java.util.Date;
import java.util.EventObject;

public class OfferEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private boolean washingIsChecked;
    private boolean paintingIsChecked;
    private boolean weldingIsChecked;
    private boolean engineRepairIsChecked;
    private boolean sandBlastingIsChecked;
    private boolean electricalRepairIsChecked;
    private static String startDate;
    private static String endDate;
    public OfferEvent(Object source, boolean washingIsChecked, boolean paintingIsChecked, boolean weldingIsChecked,
                      boolean engineRepairIsChecked, boolean sandBlastingIsChecked, boolean electricalRepairIsChecked, String startDate, String endDate) {
        super(source);
        this.washingIsChecked = washingIsChecked;
        this.paintingIsChecked = paintingIsChecked;
        this.weldingIsChecked = weldingIsChecked;
        this.engineRepairIsChecked = engineRepairIsChecked;
        this.sandBlastingIsChecked = sandBlastingIsChecked;
        this.electricalRepairIsChecked = electricalRepairIsChecked;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public boolean isWashingChecked() {
        return washingIsChecked;
    }

    public boolean isPaintingChecked() {
        return paintingIsChecked;
    }

    public boolean isWeldingChecked() {
        return weldingIsChecked;
    }

    public boolean isEngineRepairChecked() {
        return engineRepairIsChecked;
    }

    public boolean isSandBlastingChecked() {
        return sandBlastingIsChecked;
    }

    public boolean isElectricalRepairChecked() {
        return electricalRepairIsChecked;
    }

    public static String getStartDate() {
        return startDate;
    }

    public static String getEndDate() {
        return endDate;
    }
}
