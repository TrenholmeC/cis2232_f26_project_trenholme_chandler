package ca.hccis.a2.cli;

import ca.hccis.a2.item.ItemPriority;
import ca.hccis.a2.item.ItemType;

import java.util.Scanner;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A2
 */

/**
 * Class for managing CLI prompts
 */

public class PromptManager {

    private static final Scanner input = new Scanner(System.in);
    private static final String promptFormatter = "Enter %s:\n";
    private static final String floatErrorPrompt = "Unable to parse the number, please enter the value again:\n";
    private static final String posIntErrorPrompt = "Unable to parse the number, please enter the value again (positive; no decimals):\n";
    private static final String ynErrorPrompt = "Please enter 'y' or 'n':\n";

    public static void prompt(String prompt) {
        IO.print(String.format(promptFormatter, prompt));
    }

    public static String promptForString(String p) {
        prompt(p);
        return input.nextLine();
    }

    public static String rawPromptForString(String p) {
        IO.print(p);
        return input.nextLine();
    }

    public static float promptForFloat(String p) {
        prompt(p);
        float val = Float.NaN;
        // Continue asking for a number until the value is parsable and not Infinity or NaN
        while(!Float.isFinite(val)) {
            try {
                val = input.nextFloat();
                input.nextLine();
            } catch (Exception e) {
                IO.print(floatErrorPrompt);
            }
        }
        return val;
    }

    public static int promptForPositiveInt(String p) {
        prompt(p);
        int val = -1;
        // Continue asking for a number until the value is parsable and positive
        while(val < 0) {
            try {
                val = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                IO.print(posIntErrorPrompt);
            }
        }
        return val;
    }

    public static boolean promptForYN(String p) {
        String val = promptForString(p).toUpperCase();
        while(!val.equals("Y") && !val.equals("N")) {
            IO.print(ynErrorPrompt);
            val = promptForString(p).toUpperCase();
        }
        return val.equals("Y");
    }

    public static ItemType promptForItemType(String p) {
        String valStr = promptForString(p);
        ItemType valEnum = ItemType.fromString(valStr);
        while(!valEnum.toString().equals(valStr.toUpperCase())) {
            IO.println(String.format("%s is not a valid gradable item type, please enter one of the below values:", valStr));
            for(ItemType type: ItemType.values()) {
                IO.println(type);
            }
            valStr = promptForString(p);
            valEnum = ItemType.fromString(valStr);
        }
        return valEnum;
    }

    public static ItemPriority promptForItemPriority(String p) {
        String valStr = promptForString(p);
        ItemPriority valEnum = ItemPriority.fromString(valStr);
        while(!valEnum.toString().equals(valStr.toUpperCase())) {
            IO.println(String.format("%s is not a valid gradable item priority, please enter one of the below values:", valStr));
            for(ItemPriority priority : ItemPriority.values()) {
                IO.println(priority);
            }
            valStr = promptForString(p);
            valEnum = ItemPriority.fromString(valStr);
        }
        return valEnum;
    }
}
