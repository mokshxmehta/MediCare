import java.util.ArrayList;
/**
 * DataStore — in-memory storage for all app data.
 * Acts as our "database" until JDBC/MySQL is connected in Phase 4.
 * All fields are static so any page can access them directly.
 */
public class DataStore {
    // Registered patients
    public static ArrayList<Patient> patients = new ArrayList<>();
    // Currently logged-in patient (set on login/register, cleared on logout)
    public static Patient currentPatient = null;
    public static void createDemoPatient() {

        if (!patients.isEmpty()) {
            return;
        }

        Patient demoPatient = new Patient(
                "PAT-100001",
                "Moksh Mehta",
                18,
                "Male",
                "8591919468",
                "123456",
                "moksh@example.com",
                "Mumbai, Maharashtra",
                "B+",
                "9876543211",
                "",
                ""
        );

        patients.add(demoPatient);
    }
    // ---- Helper: find patient by phone ----
    public static Patient findByPhone(String phone) {
        for (Patient p : patients) {
            if (p.getPhone().equals(phone)) {
                return p;
            }
        }
        return null;
    }
    // ---- Helper: check if phone already registered ----
    public static boolean phoneExists(String phone) {
        return findByPhone(phone) != null;
    }
    // ---- Helper: validate login ----
    public static Patient login(String phone, String password) {
        Patient p = findByPhone(phone);
        if (p != null && p.getPassword().equals(password)) {
            return p;
        }
        return null;
    }
}