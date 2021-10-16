package seedu.duke.item;

public abstract class ItemList {
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";

    public abstract String convertToString();

    public abstract int getSize();

    public abstract int getTotalCalories();
}
