package ca.hccis.squash.dao;

import ca.hccis.squash.entity.ItemTypeAverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DAO class to access the item table in the db.
 *
 * @author TrenholmeC
 * @since 20261030
 */
public class ItemDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(ItemDAO.class);

    public ItemDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }

    }

    /**
     * Select the average mark (percent) per item type for class names matching (partially
     * or fully, case-insensitive, via a MySQL LIKE) the given className.  Items with an
     * actScore of -1 are unmarked and are excluded from the average, but every item type
     * found for the matching class(es) is still returned, even if all of its items are
     * unmarked (in which case the average is null).
     *
     * @since 20261030
     * @author TrenholmeC
     */
    public ArrayList<ItemTypeAverage> selectAverageMarkByTypeForClassName(String className) {
        ArrayList<ItemTypeAverage> itemTypeAverages = null;

        String sqlStatement = "select type, avg(case when actScore <> -1 then percent end) as averageMark " +
                "from item where className like ? group by type";

        try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {

            stmt.setString(1, "%" + className + "%");
            rs = stmt.executeQuery();

            itemTypeAverages = new ArrayList<>();

            while (rs.next()) {
                ItemTypeAverage itemTypeAverage = new ItemTypeAverage();
                itemTypeAverage.setType(rs.getString("type"));
                double averageMark = rs.getDouble("averageMark");
                itemTypeAverage.setAverageMark(rs.wasNull() ? null : averageMark);
                itemTypeAverages.add(itemTypeAverage);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return itemTypeAverages;
    }
}
