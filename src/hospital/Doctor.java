package hospital;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String firstName;

    private String lastName;

    private List<String> patients;

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patients = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List<String> getPatients() {
        return this.patients;
    }
}
