package ca.hccis.a2;

import ca.hccis.a2.cli.PromptManager;
import ca.hccis.a2.item.ItemManager;

import java.nio.file.Path;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A2
 * Description: Demonstrating use of Unit Tests in Java
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
                    ItemManager.writeToFile(folderPath, fileName);
                    break;
                case "V":
                    IO.println(ItemManager.itemDump());
                    break;
                case "X": continue;
                default: IO.println("invalid option");
            }
        } while (!option.equals("X"));

        ItemManager.writeToFile(folderPath, fileName);
    }
}
