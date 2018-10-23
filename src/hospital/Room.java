package hospital;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<String>patients;

    public Room() {
        this.patients = new ArrayList<>();
    }

    public List<String> getPatients() {
        return this.patients;
    }
}
