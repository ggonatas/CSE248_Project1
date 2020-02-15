package Model;

public class Name {
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String prefix;

    public Name(String firstName, String lastName, String middleName, String suffix, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.prefix = prefix;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() {
        return lastName;
    }
    public String getMiddleName() { return middleName; }
    public String getSuffix() { return suffix; }
    public String getPrefix() { return prefix; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
}
