import java.util.Random;
public class Patient {
    // ---- Fields ----
    private String patientId;
    private String fullName;
    private int    age;
    private String gender;
    private String phone;
    private String password;
    private String email;
    private String address;
    private String bloodGroup;
    private String emergencyContact;
    private String medicalConditions; // optional
    private String allergies;         // optional
    // ---- Constructor ----
    public Patient(String patientId, String fullName, int age, String gender,
                   String phone, String password, String email, String address,
                   String bloodGroup, String emergencyContact,
                   String medicalConditions, String allergies) {
        this.patientId         = patientId;
        this.fullName          = fullName;
        this.age               = age;
        this.gender            = gender;
        this.phone             = phone;
        this.password          = password;
        this.email             = email;
        this.address           = address;
        this.bloodGroup        = bloodGroup;
        this.emergencyContact  = emergencyContact;
        this.medicalConditions = medicalConditions;
        this.allergies         = allergies;
    }
    // ---- Generate unique Patient ID ----
    public static String generateId() {
        Random rand = new Random();
        int num = 100000 + rand.nextInt(900000);
        return "PAT-" + num;
    }
    // ---- Getters ----
    public String getPatientId()         { return patientId; }
    public String getFullName()          { return fullName; }
    public int    getAge()               { return age; }
    public String getGender()            { return gender; }
    public String getPhone()             { return phone; }
    public String getPassword()          { return password; }
    public String getEmail()             { return email; }
    public String getAddress()           { return address; }
    public String getBloodGroup()        { return bloodGroup; }
    public String getEmergencyContact()  { return emergencyContact; }
    public String getMedicalConditions() { return medicalConditions; }
    public String getAllergies()          { return allergies; }
    // ---- Setters (for Profile editing) ----
    public void setPassword(String password)                  { this.password = password; }
    public void setEmail(String email)                        { this.email = email; }
    public void setAddress(String address)                    { this.address = address; }
    public void setEmergencyContact(String emergencyContact)  { this.emergencyContact = emergencyContact; }
    public void setMedicalConditions(String medicalConditions){ this.medicalConditions = medicalConditions; }
    public void setAllergies(String allergies)                { this.allergies = allergies; }
    @Override
    public String toString() {
        return "Patient{id='" + patientId + "', name='" + fullName + "', phone='" + phone + "'}";
    }
}