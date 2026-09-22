import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    public static boolean login(
            String username,
            String password
    ) {

        String sql =
                "SELECT admin_id FROM admins " +
                        "WHERE username = ? AND password = ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs =
                    stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // GET ALL APPOINTMENTS
    // =====================================================

    public static List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments " +
                        "ORDER BY appointment_date ASC, appointment_id DESC";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Appointment appointment =
                        new Appointment(
                                rs.getString("patient_id"),
                                rs.getString("patient_name"),
                                rs.getString("department"),
                                rs.getString("appointment_type"),
                                rs.getDate("appointment_date")
                                        .toLocalDate(),
                                rs.getString("preferred_time"),
                                rs.getString("reason"),
                                rs.getString("priority")
                        );

                appointment.setAppointmentId(
                        rs.getInt("appointment_id")
                );

                appointment.setStatus(
                        rs.getString("status")
                );

                appointment.setDoctorName(
                        rs.getString("doctor_name")
                );

                appointment.setConfirmedTime(
                        rs.getString("confirmed_time")
                );

                appointment.setRoomNumber(
                        rs.getString("room_number")
                );

                appointments.add(appointment);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }


    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public static boolean updateAppointmentStatus(
            int appointmentId,
            String status
    ) {

        String sql =
                "UPDATE appointments " +
                        "SET status = ? " +
                        "WHERE appointment_id = ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, status);
            stmt.setInt(2, appointmentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // ASSIGN DOCTOR / CONFIRMED TIME
    // =====================================================

    public static boolean updateAppointmentAssignment(
            int appointmentId,
            String doctorName,
            String confirmedTime
    ) {

        String sql =
                "UPDATE appointments " +
                        "SET doctor_name = ?, " +
                        "confirmed_time = ? " +
                        "WHERE appointment_id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, doctorName);
            stmt.setString(2, confirmedTime);
            stmt.setInt(3, appointmentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // GET SINGLE APPOINTMENT
    // =====================================================

    public static Appointment getAppointmentById(int appointmentId) {

        String sql =
                "SELECT * FROM appointments " +
                        "WHERE appointment_id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, appointmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createAppointmentFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // DASHBOARD - TOTAL PATIENTS
    // =====================================================

    public static int getTotalPatients() {

        String sql =
                "SELECT COUNT(*) FROM patients";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // DASHBOARD - TODAY'S APPOINTMENTS
    // =====================================================

    public static int getTodayAppointments() {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM appointments " +
                        "WHERE appointment_date = CURDATE() " +
                        "AND status IN " +
                        "('PENDING', 'APPROVED', 'COMPLETED')";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // DASHBOARD - PENDING APPOINTMENTS
    // =====================================================

    public static int getPendingAppointments() {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM appointments " +
                        "WHERE status = 'PENDING'";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // DASHBOARD - RECENT APPOINTMENTS
    // =====================================================

    public static List<Appointment>
    getRecentAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments " +
                        "WHERE status <> 'CANCELLED' " +
                        "ORDER BY created_at DESC " +
                        "LIMIT 5";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(appointment);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }


    // =====================================================
    // DASHBOARD - NEXT APPOINTMENT
    // =====================================================

    public static Appointment getNextAppointment() {

        String sql =
                "SELECT * FROM appointments " +
                        "WHERE status = 'APPROVED' " +
                        "AND appointment_date >= CURDATE() " +
                        "ORDER BY appointment_date ASC, " +
                        "appointment_id ASC " +
                        "LIMIT 1";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {

                return createAppointmentFromResultSet(rs);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // CONVERT DATABASE ROW → APPOINTMENT OBJECT
    // =====================================================

    private static Appointment createAppointmentFromResultSet(
            ResultSet rs
    ) throws SQLException {

        Appointment appointment =
                new Appointment(
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("department"),
                        rs.getString("appointment_type"),
                        rs.getDate("appointment_date")
                                .toLocalDate(),
                        rs.getString("preferred_time"),
                        rs.getString("reason"),
                        rs.getString("priority")
                );

        appointment.setAppointmentId(
                rs.getInt("appointment_id")
        );

        appointment.setStatus(
                rs.getString("status")
        );

        appointment.setDoctorName(
                rs.getString("doctor_name")
        );

        appointment.setConfirmedTime(
                rs.getString("confirmed_time")
        );

        appointment.setRoomNumber(
                rs.getString("room_number")
        );

        return appointment;
    }
}