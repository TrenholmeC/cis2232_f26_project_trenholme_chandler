package ca.hccis.a2.bo;

import ca.hccis.a2.item.ItemPriority;
import ca.hccis.a2.item.ItemRecord;
import ca.hccis.a2.item.ItemType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

public class CheckMath {

    private static ItemRecord item1 = new ItemRecord(
            "Assignment 2",
            "CIS-2232",
            ItemType.ASSIGNMENT,
            "2026-10-02",
            100.0f,
            -1.0f,
            -1.0f,
            7.0f,
            ItemPriority.HIGH
    );

    private static ItemRecord item2 = new ItemRecord(
            "Assignment 1",
            "CIS-2232",
            ItemType.ASSIGNMENT,
            "2026-09-25",
            100.0f,
            95.0f,
            0.95f,
            10.0f,
            ItemPriority.HIGH
    );

    /**
     * Author: Chandler Trenholme
     * Date: 2026-10-02
     */
    @Test
    void dayCheck() {
        LocalDate date = LocalDate.parse("2026-09-29");

        //Test future date
        long dist1 = item1.daysAway(date);
        assertEquals(3, dist1);

        //Test date in the past
        long dist2 = item2.daysAway(date);
        assertEquals(-4, dist2);
    }

    /**
     * Author: Chandler Trenholme
     * Date: 2026-10-02
     */
    @Test
    void achievedWeightCheck() {
        // When unmarked returns a negative value of total weight
        float weight1 = item1.achievedWeight();
        assertTrue(weight1 < 0.0f);

        // When marked should return a positive value representing achieved weight
        float weight2 = item2.achievedWeight();
        assertFalse(weight2 < 0.0f);
    }

    // ============================================
    // New tests added by Claude
    // ============================================

    /**
     * Author: Claude (automated test generation)
     * Date: 2026-10-02
     * Description: Tests daysAway with same day comparison
     */
    @Test
    void sameDayCheck() {
        LocalDate date = LocalDate.parse("2026-09-25");

        // Item2 is due on the same date, should return 0
        long dist = item2.daysAway(date);
        assertEquals(0, dist);
    }

    /**
     * Author: Claude (automated test generation)
     * Date: 2026-10-02
     * Description: Tests achievedWeight for zero score scenario
     */
    @Test
    void zeroScoreWeightCheck() {
        ItemRecord zeroItem = new ItemRecord(
                "Quiz 1",
                "CIS-2232",
                ItemType.ASSIGNMENT,
                "2026-10-10",
                100.0f,
                0.0f,
                0.0f,
                10.0f,
                ItemPriority.LOW
        );

        // Zero score should return 0 weight
        float weight = zeroItem.achievedWeight();
        assertEquals(0.0f, weight, 0.01f);
    }

    /**
     * Author: Claude (automated test generation)
     * Date: 2026-10-02
     * Description: Tests achievedWeight for perfect score scenario
     */
    @Test
    void perfectScoreWeightCheck() {
        ItemRecord perfectItem = new ItemRecord(
                "Final Project",
                "CIS-2232",
                ItemType.ASSIGNMENT,
                "2026-12-01",
                100.0f,
                100.0f,
                1.0f,
                20.0f,
                ItemPriority.HIGH
        );

        // Perfect score should return full weight
        float weight = perfectItem.achievedWeight();
        assertEquals(20.0f, weight, 0.01f);
    }

    /**
     * Author: Claude (automated test generation)
     * Date: 2026-10-02
     * Description: Tests daysAway with large date difference
     */
    @Test
    void largeDateDifferenceCheck() {
        LocalDate date = LocalDate.parse("2026-01-15");

        // item1 due 2026-10-02, difference should be ~260 days
        long dist = item1.daysAway(date);
        assertTrue(dist > 200, "Expected large positive difference");
    }

    /**
     * Author: Claude (automated test generation)
     * Date: 2026-10-02
     * Description: Tests achievedWeight with partial score
     */
    @Test
    void partialScoreWeightCheck() {
        ItemRecord partialItem = new ItemRecord(
                "Midterm",
                "CIS-2232",
                ItemType.ASSIGNMENT,
                "2026-11-15",
                100.0f,
                75.0f,
                0.75f,
                25.0f,
                ItemPriority.HIGH
        );

        // 75% of 25 weight = 18.75
        float weight = partialItem.achievedWeight();
        assertEquals(18.75f, weight, 0.01f);
    }
}
