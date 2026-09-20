public class Main {

    public static void main(String[] args) {

        String savedPatientId =
                SessionManager.getSavedPatientId();

        if (savedPatientId != null) {

            Patient patient =
                    PatientDAO.findById(savedPatientId);

            if (patient != null) {

                DataStore.currentPatient = patient;

                new PatientDashboard();
                return;
            }

            // Saved session is no longer valid
            SessionManager.clearSession();
        }

        new WelcomePage();
    }
}