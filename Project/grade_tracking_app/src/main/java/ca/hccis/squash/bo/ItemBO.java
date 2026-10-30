package ca.hccis.squash.bo;

import ca.hccis.squash.dao.ItemDAO;
import ca.hccis.squash.entity.ItemTypeAverage;
import ca.hccis.squash.util.CisUtilityFile;

import java.util.ArrayList;

public class ItemBO {

    /**
     * Process the average mark per item type for a given class name.
     *
     * @since 20261030
     * @author TrenholmeC
     */
    public ArrayList<ItemTypeAverage> processAverageMarkByTypeForClassName(String className) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        ItemDAO itemDAO = new ItemDAO();
        ArrayList<ItemTypeAverage> itemTypeAverages = itemDAO.selectAverageMarkByTypeForClassName(className);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("itemTypeAverageReport", itemTypeAverages);

        return itemTypeAverages;
    }
}
