import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterPage extends JFrame {

    // ---- Form fields ----
    private JTextField    nameField, ageField, phoneField, emailField,
            emergencyField, conditionsField, allergiesField;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextArea     addressArea;
    private JComboBox<String> genderCombo, bloodGroupCombo;

    public RegisterPage() {

        setTitle("MediCare+");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setResizable(false);

        //---------------- LEFT PANEL ----------------//

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(20, 32, 87));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Join MediCare+");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setBounds(70, 80, 380, 50);

        JLabel subtitle = new JLabel("<html>Create your account and get access<br>"
                + "to appointments, prescriptions,<br>"
                + "health records and much more.</html>");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        subtitle.setBounds(70, 145, 400, 90);

        ImageIcon icon = new ImageIcon("assets/hospital.png");
        Image img = icon.getImage().getScaledInstance(370, 340, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(80, 255, 370, 340);

        JLabel bottom = new JLabel("Simple  •  Safe  •  Secure");
        bottom.setForeground(new Color(180, 220, 255));
        bottom.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        bottom.setBounds(140, 620, 260, 25);

        leftPanel.add(title);
        leftPanel.add(subtitle);
        leftPanel.add(imageLabel);
        leftPanel.add(bottom);

        //---------------- RIGHT PANEL ----------------//

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        // ---- Card ----
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(245, 248, 255));
        card.setBounds(45, 15, 450, 660);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // ---- Back button ----
        JButton backBtn = new JButton("← Back");
        backBtn.setBounds(15, 12, 80, 28);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(new Color(37, 99, 235));
        backBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ---- Heading ----
        JLabel heading = new JLabel("PATIENT REGISTRATION");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 26));
        heading.setForeground(new Color(37, 99, 235));
        heading.setBounds(58, 48, 350, 35);

        JLabel subHeading = new JLabel("Fill in your details to create an account");
        subHeading.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subHeading.setForeground(Color.GRAY);
        subHeading.setBounds(70, 83, 320, 20);

        // ---- Separator line ----
        JSeparator sep = new JSeparator();
        sep.setBounds(20, 112, 410, 2);
        sep.setForeground(new Color(210, 215, 235));

        //
        // ---- Scrollable form inside the card ----
        //
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(245, 248, 255));
        formPanel.setPreferredSize(new Dimension(420, 840));

        int y = 12; // running Y position inside formPanel

        // -- Full Name --
        addLabel(formPanel, "Full Name *", 20, y);
        nameField = addField(formPanel, 20, y + 24, 390, 38);
        y += 80;

        // -- Age + Gender (two columns) --
        addLabel(formPanel, "Age *", 20, y);
        ageField = addField(formPanel, 20, y + 24, 175, 38);

        addLabel(formPanel, "Gender *", 215, y);
        String[] genders = {"Select Gender", "Male", "Female", "Other"};
        genderCombo = new JComboBox<>(genders);
        genderCombo.setBounds(215, y + 24, 195, 38);
        genderCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        genderCombo.setBackground(Color.WHITE);
        formPanel.add(genderCombo);
        y += 80;

        // -- Phone Number --
        addLabel(formPanel, "Phone Number *", 20, y);
        JLabel cc = new JLabel("+91");
        cc.setOpaque(true);
        cc.setBackground(Color.WHITE);
        cc.setHorizontalAlignment(SwingConstants.CENTER);
        cc.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cc.setBounds(20, y + 24, 55, 38);
        formPanel.add(cc);
        phoneField = addField(formPanel, 75, y + 24, 335, 38);
        y += 80;

        // -- Password + Confirm (two columns) --
        addLabel(formPanel, "Password *", 20, y);
        passwordField = new JPasswordField();
        passwordField.setBounds(20, y + 24, 180, 38);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(passwordField);

        addLabel(formPanel, "Confirm Password *", 210, y);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(210, y + 24, 200, 38);
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(confirmPasswordField);
        y += 80;

        // -- Email --
        addLabel(formPanel, "Email Address *", 20, y);
        emailField = addField(formPanel, 20, y + 24, 390, 38);
        y += 80;

        // -- Address --
        addLabel(formPanel, "Address *", 20, y);
        addressArea = new JTextArea();
        addressArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addrScroll = new JScrollPane(addressArea);
        addrScroll.setBounds(20, y + 24, 390, 55);
        addrScroll.setBorder(BorderFactory.createLineBorder(new Color(180,180,180)));
        formPanel.add(addrScroll);
        y += 96;

        // -- Blood Group + Emergency Contact (two columns) --
        addLabel(formPanel, "Blood Group *", 20, y);
        String[] bloodGroups = {"Select", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroupCombo = new JComboBox<>(bloodGroups);
        bloodGroupCombo.setBounds(20, y + 24, 170, 38);
        bloodGroupCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bloodGroupCombo.setBackground(Color.WHITE);
        formPanel.add(bloodGroupCombo);

        addLabel(formPanel, "Emergency Contact *", 210, y);
        emergencyField = addField(formPanel, 210, y + 24, 200, 38);
        y += 80;

        // -- Optional section divider --
        JLabel optLabel = new JLabel("======Optional Medical Information======");
        optLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        optLabel.setForeground(new Color(130, 140, 170));
        optLabel.setBounds(110, y, 320, 18);
        formPanel.add(optLabel);
        y += 30;

        // -- Medical Conditions --
        addLabel(formPanel, "Existing Medical Conditions", 20, y);
        conditionsField = addField(formPanel, 20, y + 24, 390, 38);
        y += 80;

        // -- Allergies --
        addLabel(formPanel, "Known Allergies", 20, y);
        allergiesField = addField(formPanel, 20, y + 24, 390, 38);
        y += 80;

        // -- Register button --
        JButton registerBtn = new JButton("CREATE ACCOUNT");
        registerBtn.setBounds(20, y, 390, 50);
        registerBtn.setBackground(new Color(232, 113, 53));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        registerBtn.setBorder(new EmptyBorder(10, 20, 10, 20));
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(registerBtn);

        // -- Scroll pane wrapping the form --
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBounds(0, 118, 450, 542);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);

        card.add(backBtn);
        card.add(heading);
        card.add(subHeading);
        card.add(sep);
        card.add(scrollPane);

        rightPanel.add(card);

        add(leftPanel);
        add(rightPanel);

        //---------------- BUTTON EVENTS ----------------//

        backBtn.addActionListener(e -> {
            new PatientLogin();
            dispose();
        });

        registerBtn.addActionListener(e -> handleRegister());

        setVisible(true);
    }

    //---------------- REGISTRATION LOGIC ----------------//

    private void handleRegister() {

        String name      = nameField.getText().trim();
        String ageStr    = ageField.getText().trim();
        String phone     = phoneField.getText().trim();
        String password  = new String(passwordField.getPassword());
        String confirm   = new String(confirmPasswordField.getPassword());
        String email     = emailField.getText().trim();
        String address   = addressArea.getText().trim();
        String emergency = emergencyField.getText().trim();
        String gender    = (String) genderCombo.getSelectedItem();
        String blood     = (String) bloodGroupCombo.getSelectedItem();
        String conditions = conditionsField.getText().trim();
        String allergies  = allergiesField.getText().trim();

        // --- Required field checks (individual, so exact field is identified) ---
        if (name.isEmpty()) {
            showWarn("Full Name is required.");
            return;
        }
        if (ageStr.isEmpty()) {
            showWarn("Age is required.");
            return;
        }
        if (phone.isEmpty()) {
            showWarn("Phone number is required.");
            return;
        }
        if (password.trim().isEmpty()) {
            showWarn("Password is required.");
            return;
        }
        if (confirm.trim().isEmpty()) {
            showWarn("Please confirm your password.");
            return;
        }
        if (email.isEmpty()) {
            showWarn("Email address is required.");
            return;
        }
        if (address.isEmpty()) {
            showWarn("Address is required.");
            return;
        }
        if (emergency.isEmpty()) {
            showWarn("Emergency contact is required.");
            return;
        }
        if (gender.equals("Select Gender")) {
            showWarn("Please select your gender.");
            return;
        }
        if (blood.equals("Select")) {
            showWarn("Please select your blood group.");
            return;
        }

        // --- Phone format ---
        if (!phone.matches("\\d{10}")) {
            showWarn("Phone number must be exactly 10 digits.");
            return;
        }

        // --- Email format ---
        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            showWarn("Please enter a valid email address.\nExample: name@example.com");
            return;
        }

        // --- Address minimum length ---
        if (address.length() < 10) {
            showWarn("Please enter a complete address (at least 10 characters).");
            return;
        }

        // --- Emergency contact must be 10 digits ---
        if (!emergency.matches("\\d{10}")) {
            showWarn("Emergency contact must be a valid 10-digit phone number.");
            return;
        }

        // --- Age must be a number ---
        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 1 || age > 120) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showWarn("Please enter a valid age (1–120).");
            return;
        }

        // --- Password rules ---
        if (password.length() < 6) {
            showWarn("Password must be at least 6 characters.");
            return;
        }
        if (!password.equals(confirm)) {
            showWarn("Passwords do not match. Please re-enter.");
            return;
        }

        // --- Duplicate phone check ---
        if (PatientDAO.phoneExists(phone)) {
            showWarn("This phone number is already registered.\nPlease login instead.");
            return;
        }

        // --- Create patient and store ---
        String patientId = Patient.generateId();
        Patient patient  = new Patient(patientId, name, age, gender,
                phone, password, email, address, blood,
                emergency, conditions, allergies);

        boolean registered =
                PatientDAO.registerPatient(patient);

        if (!registered) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed.\nPlease try again.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        DataStore.currentPatient = patient;

        SessionManager.saveSession(
                patient.getPatientId()
        );

        new PatientDashboard();
        dispose();

        // --- Success ---
        JOptionPane.showMessageDialog(this,
                "✓  Registration Successful!\n\n"
                        + "  Your Patient ID :  " + patientId + "\n"
                        + "  Please note this down for future reference.\n\n"
                        + "  Welcome to MediCare+, " + name.split(" ")[0] + "!",
                "Account Created",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //---------------- HELPER METHODS ----------------//

    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(new Color(50, 60, 100));
        lbl.setBounds(x, y, 320, 20);
        panel.add(lbl);
    }

    private JTextField addField(JPanel panel, int x, int y, int w, int h) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBounds(x, y, w, h);
        panel.add(field);
        return field;
    }

    private void showWarn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Missing / Invalid Info", JOptionPane.WARNING_MESSAGE);
    }
}
