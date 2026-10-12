package ca.hccis.a3.jop;

import ca.hccis.a3.item.ItemManager;
import ca.hccis.a3.item.ItemPriority;
import ca.hccis.a3.item.ItemRecord;
import ca.hccis.a3.item.ItemType;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Chandler Trenholme
 * Instructor: BJ Maclean
 * Assignment: CIS-2232 A3
 */

/**
 * GUI entry point for the Grade Tracker App. Lets the user add and view gradable item
 * records, stored and retrieved through ItemManager, via a JOptionPane menu.
 */
public class MainPane {
    private static final String[] menuOptions = {"Add", "View", "Exit"};

    public static void create(Path folderPath, String fileName) {
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(
                    null,
                    "What would you like to do?",
                    "Grade Tracker App",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    menuOptions,
                    menuOptions[0]
            );
            if (choice == 0) {
                addItem();
                ItemManager.writeToFile(folderPath, fileName);
            } else if (choice == 1) {
                viewItems();
            } else {
                choice = 2; // closing the dialog or picking Cancel also exits
            }
        } while (choice != 2);

        ItemManager.writeToFile(folderPath, fileName);
    }

    private static void addItem() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField classNameField = new JTextField();
        JComboBox<ItemType> typeCombo = new JComboBox<>(ItemType.values());
        JTextField dueDateField = new JTextField();
        JTextField maxScoreField = new JTextField();
        JTextField achScoreField = new JTextField("-1");
        JTextField weightField = new JTextField();
        JComboBox<ItemPriority> priorityCombo = new JComboBox<>(ItemPriority.values());

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Item ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Class name:"));
        panel.add(classNameField);
        panel.add(new JLabel("Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Due date (YYYY-MM-DD):"));
        panel.add(dueDateField);
        panel.add(new JLabel("Max score:"));
        panel.add(maxScoreField);
        panel.add(new JLabel("Achieved score (-1 if ungraded):"));
        panel.add(achScoreField);
        panel.add(new JLabel("Weight (%):"));
        panel.add(weightField);
        panel.add(new JLabel("Priority:"));
        panel.add(priorityCombo);

        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Add Gradable Item",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            if (result != JOptionPane.OK_OPTION) return;

            List<String> errors = new ArrayList<>();
            Integer id = parsePositiveInt(idField.getText(), "Item ID", errors);
            String name = requireNonBlank(nameField.getText(), "Name", errors);
            String className = requireNonBlank(classNameField.getText(), "Class name", errors);
            String dueDate = parseDate(dueDateField.getText(), "Due date", errors);
            Float maxScore = parseFloat(maxScoreField.getText(), "Max score", errors);
            Float achScore = parseFloat(achScoreField.getText(), "Achieved score", errors);
            Float weightPct = parseFloat(weightField.getText(), "Weight", errors);

            if (id != null && ItemManager.getItem(id) != null) {
                int overwrite = JOptionPane.showConfirmDialog(
                        null,
                        "This ID is already in use, do you want to overwrite it?",
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION
                );
                if (overwrite != JOptionPane.YES_OPTION) {
                    errors.add("Choose a different Item ID.");
                }
            }

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        String.join("\n", errors),
                        "Please fix the following",
                        JOptionPane.ERROR_MESSAGE
                );
                continue;
            }

            float percent = achScore < 0 ? -1.0f : achScore / maxScore;
            ItemManager.setItem(id, new ItemRecord(
                    name,
                    className,
                    (ItemType) typeCombo.getSelectedItem(),
                    dueDate,
                    maxScore,
                    achScore,
                    percent,
                    weightPct / 100.0f,
                    (ItemPriority) priorityCombo.getSelectedItem()
            ));
            return;
        }
    }

    private static void viewItems() {
        String dump = ItemManager.itemDump();
        if (dump.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "No gradable items have been added yet.",
                    "Gradable Items",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JTextArea textArea = new JTextArea(dump);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Gradable Items", JOptionPane.PLAIN_MESSAGE);
    }

    private static String requireNonBlank(String val, String label, List<String> errors) {
        if (val == null || val.isBlank()) {
            errors.add(label + " cannot be blank.");
            return null;
        }
        return val.trim();
    }

    private static String parseDate(String val, String label, List<String> errors) {
        try {
            LocalDate.parse(val.trim());
            return val.trim();
        } catch (Exception e) {
            errors.add(label + " must be a valid date (YYYY-MM-DD).");
            return null;
        }
    }

    private static Integer parsePositiveInt(String val, String label, List<String> errors) {
        try {
            int parsed = Integer.parseInt(val.trim());
            if (parsed >= 0) return parsed;
        } catch (NumberFormatException ignored) {
            // falls through to the shared error message below
        }
        errors.add(label + " must be a positive whole number.");
        return null;
    }

    private static Float parseFloat(String val, String label, List<String> errors) {
        try {
            float parsed = Float.parseFloat(val.trim());
            if (Float.isFinite(parsed)) return parsed;
        } catch (NumberFormatException ignored) {
            // falls through to the shared error message below
        }
        errors.add(label + " must be a valid number.");
        return null;
    }
}
