package ca.hccis.a1;

import ca.hccis.a1.cli.PromptManager;
import ca.hccis.a1.item.ItemManager;
import ca.hccis.a1.item.ItemPriority;
import ca.hccis.a1.item.ItemRecord;
import ca.hccis.a1.item.ItemType;

import java.nio.file.Path;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A1
 * Description: Demonstrating use of File IO in Java
 */

public class Main {
    private static final Path folderPath = Path.of("c:/cis2232");
    private static final String fileName = "data_trenholme_chandler.json";
    private static final String cliMenu = "\nGrade Tracker App\n\nA) Add\nV) View\nX) eXit\n--> ";
    static void main() {
        ItemManager.readFromFile(folderPath, fileName);

        String option;
        do {
            option = PromptManager.rawPromptForString(cliMenu);
            switch (option) {
                case "A":
                    ItemManager.promptToCreateItem();
                    break;
                case "V":
                    IO.println(ItemManager.itemDump());
                    ItemManager.writeToFile(folderPath, fileName);
                    break;
                case "X": continue;
                default: IO.println("invalid option");
            }
        } while (!option.equals("X"));

        ItemManager.writeToFile(folderPath, fileName);
    }
}
