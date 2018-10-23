package hospital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<Department> departments;
    static List<Doctor> doctors;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        departments = new ArrayList<>();
        doctors = new ArrayList<>();

        String command = br.readLine();

        while (!command.equals("Output")) {
            String[] commandArgs = command.split("\\s+");
            String departmentName = commandArgs[0];
            String firstName = commandArgs[1];
            String lastName = commandArgs[2];
            String patient = commandArgs[3];

            Department department = getDepartment(departmentName);
            Doctor doctor = getDoctor(firstName, lastName);

            boolean containsFreeSpase =
                    department.getRooms().size() < 60;
            if (containsFreeSpase) {
                int targetRoom = 0;
                doctor.getPatients().add(patient);
                for (int room = 0; room < department.getRooms().size(); room++) {
                    if (department.getRooms().size() < 3) {
                        targetRoom = room;
                        break;
                    }

                }
                department.getRooms()
                        .get(targetRoom)
                        .getPatients()
                        .add(patient);

            }

            command = br.readLine();
        }
        command = br.readLine();

        while (!command.equals("End")) {
            String[] arguments = command.split("\\s+");

            if (arguments.length == 1) {
                Department department = getDepartment(arguments[0]);
                List<Room> rooms = department.getRooms().stream()
                        .filter(room -> room.getPatients().size() > 0)
                        .collect(Collectors.toList());
                for (Room room : rooms) {
                    for (String patient : room.getPatients()) {
                        System.out.println(patient);
                    }
                }
            } else if (arguments.length == 2 && arguments[1].matches("[0-9]+")) {
                int room = Integer.parseInt(arguments[1]);
                Department department = getDepartment(arguments[0]);
                Room rooms = department.getRooms().get(room - 1);
                List<String> patients =
                        rooms.getPatients()
                                .stream()
                                .sorted(String::compareTo)
                                .collect(Collectors.toList());
                for (String patient : patients) {
                    System.out.println(patient);
                }
//              
            } else {
                String firstName = arguments[0];
                String lastName = arguments[1];
                Doctor doctor = getDoctor(firstName, lastName);
                List<String> patients = doctor.getPatients()
                        .stream()
                        .sorted(String::compareTo)
                        .collect(Collectors.toList());
                for (String patient : patients) {
                    System.out.println(patient);
                }
            }
            command = br.readLine();
        }
    }

    private static Doctor getDoctor(String firstName, String lastName) {

        Doctor doctor = doctors.stream()
                .filter(name -> name.getFirstName().equals(firstName)
                        && name.getLastName().equals(lastName))
                .findFirst().orElse(null);

        if (doctor == null) {
            doctor = new Doctor(firstName, lastName);

            doctors.add(doctor);
        }
        return doctor;
    }

    private static Department getDepartment(String departmentName) {
        Department department = departments
                .stream()
                .filter(d -> d.getName().equals(departmentName))
                .findFirst().orElse(null);
        if (department == null) {
            department = new Department(departmentName);
            for (int i = 0; i < 20; i++) {
                Room room = new Room();
                department.getRooms().add(room);
            }
            departments.add(department);
        }
        return department;
    }
}
