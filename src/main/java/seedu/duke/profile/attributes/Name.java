package seedu.duke.profile.attributes;

/**
 * Name attribute of profile.
 */
public class Name implements Verifiable {

    protected String name;
    private static final String EMPTY_STRING = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        if (name == null || EMPTY_STRING.equals(name) ) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '|' || name.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }


}
