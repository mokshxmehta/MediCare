import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {


    // =========================================================
    // CHECK ACTIVE/PENDING APPOINTMENT
    // =========================================================

    public static boolean hasActiveAppointment(
            String patientId
    ) {

        String sql =
                "SELECT appointment_id " +
                        "FROM appointments " +
                        "WHERE patient_id = ? " +
                        "AND status IN ('PENDING', 'APPROVED') " +
                        "LIMIT 1";


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


            ResultSet result =
                    statement.executeQuery();


            return result.next();


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CREATE APPOINTMENT
    // =========================================================

    public static boolean createAppointment(
            Appointment appointment
    ) {

        String sql =
                "INSERT INTO appointments " +
                        "(patient_id, patient_name, department, appointment_type, " +
                        "appointment_date, preferred_time, reason, " +
                        "priority, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    appointment.getPatientId()
            );

            statement.setString(
                    2,
                    appointment.getPatientName()
            );

            statement.setString(
                    3,
                    appointment.getDepartment()
            );

            statement.setString(
                    4,
                    appointment.getAppointmentType()
            );

            statement.setDate(
                    5,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            statement.setString(
                    6,
                    appointment.getPreferredTime()
            );

            statement.setString(
                    7,
                    appointment.getReason()
            );

            statement.setString(
                    8,
                    appointment.getPriority()
            );

            statement.setString(
                    9,
                    appointment.getStatus()
            );


            int rows =
                    statement.executeUpdate();


            return rows > 0;


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE PENDING APPOINTMENT
    // =========================================================

    public static boolean deletePendingAppointment(
            int appointmentId,
            String patientId
    ) {

        String sql =
                "DELETE FROM appointments " +
                        "WHERE appointment_id = ? " +
                        "AND patient_id = ? " +
                        "AND status = 'PENDING'";


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

            statement.setString(
                    2,
                    patientId
            );


            int rows =
                    statement.executeUpdate();


            return rows > 0;


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}