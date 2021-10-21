package seedu.duke.item.bank;

import seedu.duke.item.Item;
import seedu.duke.item.bank.exceptions.DuplicateItemInBankException;
import seedu.duke.item.bank.exceptions.ItemNotFoundInBankException;

import java.util.ArrayList;

/**
 * Represents a list of Items, meant to store a repository of user-defined Exercise items or Food items.
 * Contains all the methods for actions that can be done on the list.
 */
public class ItemBank {
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";
    protected final ArrayList<Item> internalItems = new ArrayList<>();

    public void addItem(Item item) throws DuplicateItemInBankException {
        try {
            getCaloriesOfItemWithMatchingName(item.getName());
            throw new DuplicateItemInBankException();
        } catch (ItemNotFoundInBankException e) {
            internalItems.add(item);
        }
    }

    public Item getItem(int index) {
        return internalItems.get(index);
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

    public int getCaloriesOfItemWithMatchingName(String inputName) throws ItemNotFoundInBankException {
        Item matchingItem = internalItems
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(inputName))
                .findAny()
                .orElse(null);
        if (matchingItem == null) {
            throw new ItemNotFoundInBankException();
        }
        return matchingItem.getCalories();
    }

}
