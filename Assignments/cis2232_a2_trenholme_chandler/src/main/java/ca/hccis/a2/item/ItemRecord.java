package ca.hccis.a2.item;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A2
 * Description: Record definition using Java's built-in record class builder
 */

/**
 * The project doc defined a "Weight Achieved", "Current Date" and "Class Mark" field, however the BA agreed that these
 * should be a purely derived or calculated value that is not stored.
 */

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Record of a single gradable item, used for storing the data
 * @param dueDate YYYY-MM-DD string representing the due date
 * @param maxScore float indicating the maximum amount of marks achievable
 * @param achScore float indicating the amount of marks achieved, -1.0 if not graded
 * @param weight float representing the weight
 * @param name gradable item's name (ex. "Assignment 1.1")
 * @param className name of the class the gradable item belongs too
 * @param priority level of priority given to this gradable item
 * @param type type of gradable item
 * @param percent graded mark as a percentage, -1.0 if not graded
 */
public record ItemRecord(
        String name,
        String className,
        ItemType type,
        String dueDate,
        float maxScore,
        float achScore,
        float percent,
        float weight,
        ItemPriority priority
) {

    @Override
    public String toString() {
        String grade = percent < 0 ? "Ungraded; " + priority + " Priority" : String.format("%.2f%%", percent*100);
        return String.format("%s - %s (%s) @ %s", className, name, grade, dueDate);
    }

    /** Returns signed distance between the given date and the item's due date
    * + means the due date is in the future, - means the due date is in the past */
    public long daysAway(LocalDate date) {
        LocalDate due = LocalDate.parse(dueDate);
        return ChronoUnit.DAYS.between(date, due);
    }

    /** Returns weight achieved if marked or negative total weight if unmarked. */
    public float achievedWeight() {
        return percent*weight;
    }
}
