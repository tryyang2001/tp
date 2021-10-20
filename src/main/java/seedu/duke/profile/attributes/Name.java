package seedu.duke.profile.attributes;

/**
 * Name attribute of profile.
 */
public class Name implements Verifiable {

    protected String name;

    public Name() {

    }

    /**
     * Constructs a name object.
     *
     * @param name name input of user
     */
    public Name(String name) {
        setName(name);
    }

    /**
     * Retrieves the name from Name object.
     *
     * @return name of Name object
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the Name Object.
     *
     * @param name name input by the user
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '|' || name.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }


}
