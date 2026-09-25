package ca.hccis.a1.item;

import ca.hccis.a1.cli.PromptManager;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A1
 */

/**
 * Class for handling records of assignments and grades
 */
public class ItemManager {
    private static HashMap<Integer, ItemRecord> itemMap = new HashMap<>();
    private static final Gson gson = new Gson();

    //GSON requires a TypeToken for non-String keyed Hash Maps
    private static final Type ITEM_RECORD_MAP_TYPE = new TypeToken<HashMap<Integer, ItemRecord>>(){}.getType();

    public static void promptToCreateItem() {
        int id = PromptManager.promptForPositiveInt("the gradable item id");
        if(itemMap.containsKey(id)) {
            if(!PromptManager.promptForYN("This ID is already in use, do you want to overwrite it? (y/n): ")) {
                return;
            }
        }
        String name = PromptManager.promptForString("the gradable item name");
        String className = PromptManager.promptForString("the class name");
        ItemType type = PromptManager.promptForItemType("the gradable item type");
        String dueDate = PromptManager.promptForString("the due date");
        float maxScore = PromptManager.promptForFloat("the max score");
        float actScore = PromptManager.promptForFloat("the achieved score (-1 for ungraded)");
        float percent = actScore < 0 ? -1.0f : actScore/maxScore;
        float weight = PromptManager.promptForFloat("the weight percentage") / 100.0f;
        ItemPriority priority = PromptManager.promptForItemPriority("the priority for the gradable item");
        setItem(id, new ItemRecord(
                name,
                className,
                type,
                dueDate,
                maxScore,
                actScore,
                percent,
                weight,
                priority
        ));
    }

    public static String itemDump() {
        String result = "";
        for(int key: itemMap.keySet()) {
            result += String.format("%5d | %s\n", key, itemMap.get(key));
        }
        return result;
    }

    public static ItemRecord setItem(int id, ItemRecord item) {
        return itemMap.put(id, item);
    }

    public static ItemRecord getItem(int id) {
        return itemMap.get(id);
    }

    private static Path prepareFile(Path folderPath, String fileName) {
        try {
            Files.createDirectories(folderPath); //ensure the full folder path exists by creating any missing folders
            Path filePath = folderPath.resolve(fileName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            if(!Files.isRegularFile(filePath)) {
                IO.println("File path isn't a file");
                System.exit(0x110);
            }
            return filePath;
        } catch (Exception e) {
            IO.println("Couldn't prepare file");
            IO.println(e.getMessage());
            System.exit(0x100);
        }
        return null; //unreachable, required for compiler
    }

    public static void readFromFile(Path folderPath, String fileName) {
        try {
            Path filePath = prepareFile(folderPath, fileName);
            String content = Files.readString(filePath);
            itemMap = gson.fromJson(content, ITEM_RECORD_MAP_TYPE);
            if(itemMap == null) {
                itemMap = new HashMap<>();
            }
        } catch (Exception e) {
            IO.println("Couldn't read from file");
            IO.println(e.getMessage());
            System.exit(0x120);
        }
    }

    public static void writeToFile(Path folderPath, String fileName) {
        Path filePath = prepareFile(folderPath, fileName);
        try {
            Files.writeString(filePath, gson.toJson(itemMap));
        } catch (Exception e) {
            IO.println("Couldn't write to file");
            IO.println(e.getMessage());
            System.exit(0x130);
        }
    }
}
