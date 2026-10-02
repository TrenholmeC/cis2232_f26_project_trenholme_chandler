package ca.hccis.a2.item;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A2
 */

/**
 * Enumeration of gradable item types
 */
public enum ItemType {
    ASSIGNMENT("ASSIGNMENT"),
    PROJECT("PROJECT"),
    GROUP_PROJECT("GROUP_PROJECT"),
    QUIZ("QUIZ"),
    TEST("TEST"),
    EXAM("EXAM");

    private final String val;

    ItemType(String val) {
        this.val = val;
    }

    /**
     * Converts a string value to the corresponding enum value
     * @param val String name of the item type
     * @return Corresponding enum value
     */
    public static ItemType fromString(String val) {
        return switch (val.toUpperCase()) {
            case "ASSIGNMENT" -> ItemType.ASSIGNMENT;
            case "PROJECT" -> ItemType.PROJECT;
            case "GROUP_PROJECT" -> ItemType.GROUP_PROJECT;
            case "QUIZ" -> ItemType.QUIZ;
            case "TEST" -> ItemType.TEST;
            case "EXAM" -> ItemType.EXAM;
            default -> ItemType.ASSIGNMENT;
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
