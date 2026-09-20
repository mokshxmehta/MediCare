import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static Appointment getCurrentAppointment(
            String patientId
    ) {

        String sql =
                "SELECT * FROM appointments " +
                        "WHERE patient_id = ? " +
                        "AND status IN ('PENDING', 'APPROVED') " +
                        "ORDER BY appointment_date ASC, appointment_id DESC " +
                        "LIMIT 1";

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

                Appointment appointment =
                        new Appointment(
                                result.getString("patient_id"),
                                result.getString("patient_name"),
                                result.getString("department"),
                                result.getString("appointment_type"),
                                result.getDate("appointment_date")
                                        .toLocalDate(),
                                result.getString("preferred_time"),
                                result.getString("reason"),
                                result.getString("priority")
                        );

                appointment.setAppointmentId(
                        result.getInt("appointment_id")
                );

                appointment.setStatus(
                        result.getString("status")
                );

                appointment.setDoctorName(
                        result.getString("doctor_name")
                );

                appointment.setConfirmedTime(
                        result.getString("confirmed_time")
                );

                appointment.setRoomNumber(
                        result.getString("room_number")
                );

                return appointment;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static int getTotalAppointmentCount(
            String patientId
    ) {

        String sql =
                "SELECT COUNT(*) FROM appointments " +
                        "WHERE patient_id = ? " +
                        "AND status IN ('PENDING', 'APPROVED', 'COMPLETED')";


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

                return result.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public static int getPendingAppointmentCount(
            String patientId
    ) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM appointments " +
                        "WHERE patient_id = ? " +
                        "AND status = 'PENDING'";

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

                return result.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }
    public static List<Appointment> getAppointments(
            String patientId,
            String status
    ) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql;

        if (status.equals("ALL")) {

            sql =
                    "SELECT * FROM appointments " +
                            "WHERE patient_id = ? " +
                            "ORDER BY appointment_date DESC, appointment_id DESC";

        } else {

            String databaseStatus =
                    status.equals("ACTIVE")
                            ? "APPROVED"
                            : status.toUpperCase();

            sql =
                    "SELECT * FROM appointments " +
                            "WHERE patient_id = ? " +
                            "AND status = ? " +
                            "ORDER BY appointment_date DESC, appointment_id DESC";
        }


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, patientId);

            if (!status.equals("ALL")) {

                String databaseStatus =
                        status.equals("ACTIVE")
                                ? "APPROVED"
                                : status.toUpperCase();

                statement.setString(
                        2,
                        databaseStatus
                );
            }

            ResultSet result =
                    statement.executeQuery();


            while (result.next()) {

                Appointment appointment =
                        new Appointment(
                                result.getString("patient_id"),
                                result.getString("patient_name"),
                                result.getString("department"),
                                result.getString("appointment_type"),
                                result.getDate("appointment_date")
                                        .toLocalDate(),
                                result.getString("preferred_time"),
                                result.getString("reason"),
                                result.getString("priority")
                        );

                appointment.setAppointmentId(
                        result.getInt("appointment_id")
                );

                appointment.setStatus(
                        result.getString("status")
                );

                appointment.setDoctorName(
                        result.getString("doctor_name")
                );

                appointment.setConfirmedTime(
                        result.getString("confirmed_time")
                );

                appointment.setRoomNumber(
                        result.getString("room_number")
                );

                appointments.add(appointment);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public static boolean cancelPendingAppointment(
            int appointmentId,
            String patientId
    ) {

        String sql =
                "UPDATE appointments " +
                        "SET status = 'CANCELLED' " +
                        "WHERE appointment_id = ? " +
                        "AND patient_id = ? " +
                        "AND status = 'PENDING'";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointmentId);
            statement.setString(2, patientId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}