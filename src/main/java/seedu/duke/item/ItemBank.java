package seedu.duke.item;

import java.util.ArrayList;

/**
 * Represents a list of Items, meant to store a repository of user-defined Exercise items or Food items.
 * Contains all the methods for actions that can be done on the list.
 */
public class ItemBank {
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";
    protected final ArrayList<Item> internalItems = new ArrayList<>();

    public void addItem(Item item) {
        internalItems.add(item);
    }

    public Item deleteItem(int index) {
        return internalItems.remove(index);
    }

    public void clearList() {
        internalItems.clear();
    }

    public int getSize() {
        return internalItems.size();
    }

    public String convertToString() {
        StringBuilder listToString = new StringBuilder();
        for (int i = 0; i < internalItems.size(); i++) {
            listToString.append(TAB).append(i + 1).append(". ").append(internalItems.get(i)).append(LS);
        }
        return listToString.toString().stripTrailing();
    }

    public Item getItemWithMatchingName(String inputName) {
        Item matchingItem = internalItems
                .stream()
                .filter(item -> item.name.equalsIgnoreCase(inputName))
                .findAny()
                .orElse(null);
        return matchingItem;
    }

}
