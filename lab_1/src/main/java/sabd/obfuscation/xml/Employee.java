package sabd.obfuscation.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "employee")
public class Employee {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty
    private String firstName;
    @JacksonXmlProperty
    private String lastName;
    @JacksonXmlProperty()
    private String location;

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LOCATION = "location";
    private static final String[] values = new String[] {ID, FIRST_NAME, LAST_NAME, LOCATION};

    public static String[] getValues() {
        return values;
    }

    public String getValue(String key) throws Exception {
        switch (key) {
            case ID:
                return getId();
            case FIRST_NAME:
                return getFirstName();
            case LAST_NAME:
                return getLastName();
            case LOCATION:
                return getLocation();
            default:
                throw new Exception("Illegal key value " + key + ". Available keys: " + String.join(", ", values));
        }
    }

    public void setValue(String key, String value) throws Exception {
        switch (key) {
            case ID:
                setId(value);
                break;
            case FIRST_NAME:
                setFirstName(value);
                break;
            case LAST_NAME:
                setLastName(value);
                break;
            case LOCATION:
                setLocation(value);
                break;
            default:
                throw new Exception("Illegal key value " + key + ". Available keys: " + String.join(", ", values));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
