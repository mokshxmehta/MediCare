import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {


    // =====================================================
    // SAVE PRESCRIPTION
    // =====================================================

    public static boolean addPrescription(
            Prescription prescription
    ) {

        String sql =
                "INSERT INTO prescriptions " +
                        "(appointment_id, patient_id, doctor_name, " +
                        "medicine_name, description, dosage, when_to_take) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    prescription.getAppointmentId()
            );

            statement.setString(
                    2,
                    prescription.getPatientId()
            );

            statement.setString(
                    3,
                    prescription.getDoctorName()
            );

            statement.setString(
                    4,
                    prescription.getMedicineName()
            );

            statement.setString(
                    5,
                    prescription.getDescription()
            );

            statement.setString(
                    6,
                    prescription.getDosage()
            );

            statement.setString(
                    7,
                    prescription.getWhenToTake()
            );

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // GET PRESCRIPTIONS FOR A PATIENT
    // =====================================================

    public static List<Prescription> getPrescriptionsByPatient(
            String patientId
    ) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM prescriptions " +
                        "WHERE patient_id = ? " +
                        "ORDER BY created_at DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    patientId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Prescription prescription =
                        new Prescription(
                                resultSet.getInt("appointment_id"),
                                resultSet.getString("patient_id"),
                                resultSet.getString("doctor_name"),
                                resultSet.getString("medicine_name"),
                                resultSet.getString("description"),
                                resultSet.getString("dosage"),
                                resultSet.getString("when_to_take")
                        );

                prescription.setPrescriptionId(
                        resultSet.getInt("prescription_id")
                );

                prescriptions.add(
                        prescription
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return prescriptions;
    }
    public static List<Prescription> getPrescriptionsByAppointment(
            int appointmentId
    ) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM prescriptions " +
                        "WHERE appointment_id = ? " +
                        "ORDER BY prescription_id ASC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    appointmentId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Prescription prescription =
                        new Prescription(
                                resultSet.getInt("appointment_id"),
                                resultSet.getString("patient_id"),
                                resultSet.getString("doctor_name"),
                                resultSet.getString("medicine_name"),
                                resultSet.getString("description"),
                                resultSet.getString("dosage"),
                                resultSet.getString("when_to_take")
                        );

                prescriptions.add(
                        prescription
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prescriptions;
    }
}