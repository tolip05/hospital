package hospital;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;

    private List<Room> rooms;

    public Department(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }
}
