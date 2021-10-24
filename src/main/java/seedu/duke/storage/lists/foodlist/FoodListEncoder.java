package seedu.duke.storage.lists.foodlist;

import seedu.duke.data.item.food.FoodList;
import seedu.duke.storage.Encoder;

import java.util.ArrayList;

public class FoodListEncoder extends Encoder {

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param foodList The list of food items to be encoded
     * @return An ArrayList of the food items to be stored
     */
    public ArrayList<String> encodeFoodList(FoodList foodList) {
        for (int i = 0; i < foodList.getSize(); i++) {
            detailsToSave.add(foodList.getItem(i).toFileTextString());
        }
        return detailsToSave;
    }
}
