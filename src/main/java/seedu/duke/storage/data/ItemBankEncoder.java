package seedu.duke.storage.data;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.Encoder;

import java.util.ArrayList;

public class ItemBankEncoder {
    /**
     * Encodes the list of items in the item bank in preparation for storage.
     *
     * @param itemBank The list of items to be encoded
     * @return An ArrayList of the items to be stored
     */
    public static ArrayList<String> encode(ItemBank itemBank) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < itemBank.getSize(); i++) {
            items.add(itemBank.getItem(i).toFileTextString());
        }
        return items;
    }
}
