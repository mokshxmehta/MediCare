import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PatientLogin extends JFrame {

    JTextField     phoneField;
    JPasswordField passwordField;

    public PatientLogin() {

        setTitle("MediCare+");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setResizable(false);

        //---------------- LEFT PANEL ----------------//

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(20, 32, 87));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Welcome Back!");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 38));
        title.setBounds(70, 80, 350, 50);

        JLabel subtitle = new JLabel("<html>Login to access your appointments,<br>"
                + "medicine reminders and health records.</html>");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setBounds(70, 145, 400, 60);

        ImageIcon icon = new ImageIcon("assets/loginpage.png");
        Image img = icon.getImage().getScaledInstance(410, 420, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(60, 220, 360, 300);

        JLabel bottom = new JLabel("Safe  •  Secure  •  Fast");
        bottom.setForeground(new Color(180, 220, 255));
        bottom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        bottom.setBounds(150, 560, 250, 25);

        leftPanel.add(title);
        leftPanel.add(subtitle);
        leftPanel.add(image);
        leftPanel.add(bottom);

        //---------------- RIGHT PANEL ----------------//

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JPanel loginCard = new JPanel();
        loginCard.setLayout(null);
        loginCard.setBounds(55, 40, 430, 550);
        loginCard.setBackground(new Color(245, 248, 255));
        loginCard.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        rightPanel.add(loginCard);

        // ---- Back ----
        JButton backBtn = new JButton("← Back");
        backBtn.setBounds(20, 10, 80, 28);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(new Color(37, 99, 235));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ---- Heading ----
        JLabel heading = new JLabel("PATIENT LOGIN");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 34));
        heading.setForeground(new Color(37, 99, 235));
        heading.setBounds(70, 45, 300, 40);

        JLabel subHeading = new JLabel("Enter your credentials");
        subHeading.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subHeading.setForeground(Color.GRAY);
        subHeading.setBounds(120, 80, 220, 25);

        //---------------- PHONE ----------------//

        JLabel countryCode = new JLabel("+91");
        countryCode.setOpaque(true);
        countryCode.setBackground(Color.WHITE);
        countryCode.setHorizontalAlignment(SwingConstants.CENTER);
        countryCode.setFont(new Font("Segoe UI", Font.BOLD, 18));
        countryCode.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        countryCode.setBounds(40, 170, 60, 42);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        phoneField.setBounds(100, 170, 280, 42);

        //---------------- PASSWORD ----------------//

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        passwordLabel.setBounds(40, 235, 150, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 265, 340, 42);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---------------- CHECKBOX ----------------//

        JCheckBox remember = new JCheckBox("Remember Me");
        remember.setBackground(new Color(245, 248, 255));
        remember.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        remember.setBounds(40, 320, 150, 25);

        //---------------- LOGIN BUTTON ----------------//

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(40, 365, 340, 50);
        loginBtn.setBackground(new Color(232, 113, 53));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //---------------- REGISTER ----------------//

        JLabel registerText = new JLabel("Don't have an account?");
        registerText.setBounds(95, 440, 170, 25);
        registerText.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel registerLink = new JLabel("<html><u>Register Now</u></html>");
        registerLink.setBounds(256, 438, 90, 28);
        registerLink.setForeground(new Color(37, 99, 235));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //---------------- ADD TO CARD ----------------//

        loginCard.add(backBtn);
        loginCard.add(heading);
        loginCard.add(subHeading);
        loginCard.add(countryCode);
        loginCard.add(phoneField);
        loginCard.add(passwordLabel);
        loginCard.add(passwordField);
        loginCard.add(remember);
        loginCard.add(loginBtn);
        loginCard.add(registerText);
        loginCard.add(registerLink);

        add(leftPanel);
        add(rightPanel);

        //---------------- BUTTON EVENTS ----------------//

        backBtn.addActionListener(e -> {
            new WelcomePage();
            dispose();
        });

        loginBtn.addActionListener(e -> handleLogin());

        // Allow pressing Enter in password field to trigger login
        passwordField.addActionListener(e -> handleLogin());

        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new RegisterPage();
                dispose();
            }
        });

        setVisible(true);
    }

    //---------------- LOGIN LOGIC ----------------//

    private void handleLogin() {

        String phone    = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your phone number and password.",
                    "Missing Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Patient patient = DataStore.login(phone, password);

        if (patient != null) {
            DataStore.currentPatient = patient;
            JOptionPane.showMessageDialog(this,
                    "Welcome back, " + patient.getFullName().split(" ")[0] + "!",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);

            new PatientDashboard();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid phone number or password.\nPlease try again.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
