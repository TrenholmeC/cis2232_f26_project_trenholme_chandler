package ca.hccis.a2.item;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A2
 */

/**
 * Enumeration of gradable item priorities
 */
public enum ItemPriority {
    LOW("LOW"),
    MODERATE("MODERATE"),
    HIGH("HIGH");

    private final String val;

    ItemPriority(String val) {
        this.val = val;
    }

    /**
     * Converts a string value to the corresponding enum value
     * @param val String name of the item type
     * @return Corresponding enum value
     */
    public static ItemPriority fromString(String val) {
        return switch (val.toUpperCase()) {
            case "LOW" -> ItemPriority.LOW;
            case "MODERATE" -> ItemPriority.MODERATE;
            case "HIGH" -> ItemPriority.HIGH;
            default -> ItemPriority.LOW;
        };
    }

    /**
     * Returns the string name of the enum value
     * @return Corresponding string name
     */
    @Override
    public String toString() {
        return this.val;
    }

}
