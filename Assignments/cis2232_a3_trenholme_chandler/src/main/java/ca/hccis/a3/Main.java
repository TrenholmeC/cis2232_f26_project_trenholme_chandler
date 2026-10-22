package ca.hccis.a3;

import ca.hccis.a3.cli.Clazz;
import ca.hccis.a3.cli.PromptManager;
import ca.hccis.a3.item.ItemManager;
import ca.hccis.a3.jop.MainPane;

import java.nio.file.Path;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A3
 * Description: Demonstrating use of Unit Tests in Java
 */

public class Main {
    private static final Path folderPath = Path.of("c:/cis2232");
    private static final String fileName = "data_trenholme_chandler.json";
    private static final String cliMenu = "\nGrade Tracker App\n\nA) Add\nV) View\nX) eXit\n--> ";
    static void main() {
        ItemManager.readFromFile(folderPath, fileName);

        Clazz clazz = new Clazz();

        Thread t1 = new Thread(Main::runCliMode); // function reference
        Thread t2 = new Thread(() -> MainPane.create(folderPath, fileName)); // anonymous function so we can pass parameters

        t1.start();
        t2.start();

        try {
            // Main thread pauses here until thread1 dies
            t1.join();
            // Main thread pauses here until thread2 dies
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IO.println(String.format("Items added this process: %d", ItemManager.getItemsAddedThisProcess()));

        ItemManager.writeToFile(folderPath, fileName);
    }

    public static void runCliMode() {
        String option;
        do {
            option = PromptManager.rawPromptForString(cliMenu);
            switch (option.toUpperCase()) {
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
        } while (!option.equalsIgnoreCase("X"));
    }
}
