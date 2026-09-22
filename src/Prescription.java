public class Prescription {

    private int prescriptionId;
    private int appointmentId;

    private String patientId;
    private String doctorName;

    private String medicineName;
    private String description;
    private String dosage;
    private String whenToTake;


    // Constructor for creating a new prescription
    public Prescription(
            int appointmentId,
            String patientId,
            String doctorName,
            String medicineName,
            String description,
            String dosage,
            String whenToTake
    ) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.description = description;
        this.dosage = dosage;
        this.whenToTake = whenToTake;
    }


    // Getters

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDescription() {
        return description;
    }

    public String getDosage() {
        return dosage;
    }

    public String getWhenToTake() {
        return whenToTake;
    }


    // Setters

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setWhenToTake(String whenToTake) {
        this.whenToTake = whenToTake;
    }
}