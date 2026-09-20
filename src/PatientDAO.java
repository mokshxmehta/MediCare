import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO {

    // =====================================================
    // REGISTER PATIENT
    // =====================================================

    public static boolean registerPatient(Patient patient) {

        String sql = """
                INSERT INTO patients
                (
                    patient_id,
                    full_name,
                    age,
                    gender,
                    phone,
                    password,
                    email,
                    address,
                    blood_group,
                    emergency_contact,
                    medical_conditions,
                    allergies
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, patient.getPatientId());
            statement.setString(2, patient.getFullName());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getPassword());
            statement.setString(7, patient.getEmail());
            statement.setString(8, patient.getAddress());
            statement.setString(9, patient.getBloodGroup());
            statement.setString(10, patient.getEmergencyContact());
            statement.setString(11, patient.getMedicalConditions());
            statement.setString(12, patient.getAllergies());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // CHECK IF PHONE ALREADY EXISTS
    // =====================================================

    public static boolean phoneExists(String phone) {

        String sql =
                "SELECT patient_id FROM patients WHERE phone = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, phone);

            ResultSet result =
                    statement.executeQuery();

            return result.next();

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // LOGIN
    // =====================================================

    public static Patient login(
            String phone,
            String password
    ) {

        String sql =
                "SELECT * FROM patients " +
                        "WHERE phone = ? AND password = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, phone);
            statement.setString(2, password);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return new Patient(

                        result.getString("patient_id"),
                        result.getString("full_name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getString("phone"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("blood_group"),
                        result.getString("emergency_contact"),
                        result.getString("medical_conditions"),
                        result.getString("allergies")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // FIND PATIENT BY ID
    // =====================================================

    public static Patient findById(String patientId) {

        String sql =
                "SELECT * FROM patients WHERE patient_id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, patientId);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return new Patient(

                        result.getString("patient_id"),
                        result.getString("full_name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getString("phone"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("blood_group"),
                        result.getString("emergency_contact"),
                        result.getString("medical_conditions"),
                        result.getString("allergies")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}