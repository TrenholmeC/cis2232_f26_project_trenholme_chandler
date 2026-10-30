package ca.hccis.squash.entity;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the item reports.
 *
 * @author TrenholmeC
 * @since 20261030
 */
public class ReportItem {

    private String className;
    private ArrayList<ItemTypeAverage> itemTypeAverages;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<ItemTypeAverage> getItemTypeAverages() {
        return itemTypeAverages;
    }

    public void setItemTypeAverages(ArrayList<ItemTypeAverage> itemTypeAverages) {
        this.itemTypeAverages = itemTypeAverages;
    }
}
