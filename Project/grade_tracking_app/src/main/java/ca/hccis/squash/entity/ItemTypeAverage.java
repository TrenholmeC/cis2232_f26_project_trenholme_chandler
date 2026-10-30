package ca.hccis.squash.entity;

/**
 * Entity class to hold the average mark calculated for a single item type.
 * averageMark is null when the type has no marked (graded) items.
 *
 * @author TrenholmeC
 * @since 20261030
 */
public class ItemTypeAverage {

    private String type;
    private Double averageMark;

    public ItemTypeAverage() {
    }

    public ItemTypeAverage(String type, Double averageMark) {
        this.type = type;
        this.averageMark = averageMark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "ItemTypeAverage\n" +
                "    type        = '" + type + "',\n" +
                "    averageMark = " + averageMark + "\n";
    }
}
