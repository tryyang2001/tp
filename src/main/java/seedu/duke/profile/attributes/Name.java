package seedu.duke.profile.attributes;

public class Name implements Verifiable {

    protected String name;

    public Name(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '|' || name.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }


}
