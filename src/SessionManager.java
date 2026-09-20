import java.util.prefs.Preferences;

public class SessionManager {

    private static final Preferences preferences =
            Preferences.userNodeForPackage(SessionManager.class);

    private static final String PATIENT_ID =
            "logged_in_patient_id";

    public static void saveSession(String patientId) {
        preferences.put(PATIENT_ID, patientId);
    }

    public static String getSavedPatientId() {
        return preferences.get(PATIENT_ID, null);
    }

    public static void clearSession() {
        preferences.remove(PATIENT_ID);
    }
}