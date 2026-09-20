public class Appointment {

    private int appointmentId;

    private String patientId;
    private String patientName;
    private String department;
    private String appointmentType;

    private java.time.LocalDate appointmentDate;

    private String preferredTime;
    private String reason;
    private String priority;
    private String status;

    private String doctorName;
    private String confirmedTime;
    private String roomNumber;


    // =========================================================
    // CONSTRUCTOR FOR NEW APPOINTMENT
    // =========================================================

    public Appointment(
            String patientId,
            String patientName,
            String department,
            String appointmentType,
            java.time.LocalDate appointmentDate,
            String preferredTime,
            String reason,
            String priority
    ) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.department = department;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.preferredTime = preferredTime;
        this.reason = reason;
        this.priority = priority;

        // Every new patient request starts as PENDING
        this.status = "PENDING";
    }


    // =========================================================
    // GETTERS
    // =========================================================

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDepartment() {
        return department;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public java.time.LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public String getReason() {
        return reason;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getConfirmedTime() {
        return confirmedTime;
    }

    public String getRoomNumber() {
        return roomNumber;
    }


    // =========================================================
    // SETTERS
    // =========================================================

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setAppointmentDate(
            java.time.LocalDate appointmentDate
    ) {
        this.appointmentDate = appointmentDate;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setConfirmedTime(String confirmedTime) {
        this.confirmedTime = confirmedTime;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}