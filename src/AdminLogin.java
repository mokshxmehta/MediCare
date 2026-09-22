import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin() {

        setTitle("MediCare+  Admin Login");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // ================= LEFT PANEL =================

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(20, 32, 87));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("MediCare+");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Inter", Font.BOLD, 42));
        title.setBounds(70, 100, 350, 60);

        JLabel subtitle = new JLabel(
                "<html>Hospital Administration<br>" +
                        "Management Portal</html>"
        );

        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        subtitle.setBounds(70, 170, 350, 80);

        ImageIcon icon = new ImageIcon("assets/admin-panel.png");

        Image img = icon.getImage().getScaledInstance(
                330,
                300,
                Image.SCALE_SMOOTH
        );

        JLabel imageLabel =
                new JLabel(new ImageIcon(img));

        imageLabel.setBounds(70, 270, 330, 250);

        JLabel bottom =
                new JLabel("Secure • Manage • Monitor");

        bottom.setForeground(new Color(180, 220, 255));
        bottom.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );
        bottom.setBounds(95, 535, 300, 30);

        leftPanel.add(title);
        leftPanel.add(subtitle);
        leftPanel.add(imageLabel);
        leftPanel.add(bottom);


        // ================= RIGHT PANEL =================

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JPanel loginCard = new JPanel();
        loginCard.setLayout(null);
        loginCard.setBackground(new Color(243, 247, 255));

        loginCard.setBounds(
                55,
                50,
                400,
                480
        );

        loginCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220),
                        2
                )
        );

        rightPanel.add(loginCard);


        // ================= TITLE =================

        JLabel loginTitle =
                new JLabel("ADMIN LOGIN");

        loginTitle.setFont(
                new Font(
                        "Times New Roman",
                        Font.BOLD,
                        34
                )
        );

        loginTitle.setForeground(
                new Color(35, 58, 130)
        );

        loginTitle.setBounds(
                105,
                35,
                250,
                50
        );

        loginCard.add(loginTitle);


        JLabel subtitleLabel =
                new JLabel("Login to administration portal");

        subtitleLabel.setForeground(Color.GRAY);

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        subtitleLabel.setBounds(
                90,
                82,
                250,
                30
        );

        loginCard.add(subtitleLabel);


        // ================= USERNAME =================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        usernameLabel.setBounds(
                50,
                140,
                300,
                25
        );

        loginCard.add(usernameLabel);


        usernameField = new JTextField();

        usernameField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        usernameField.setBounds(
                50,
                170,
                300,
                42
        );

        loginCard.add(usernameField);


        // ================= PASSWORD =================

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        passwordLabel.setBounds(
                50,
                235,
                300,
                25
        );

        loginCard.add(passwordLabel);


        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        passwordField.setBounds(
                50,
                265,
                300,
                42
        );

        loginCard.add(passwordField);


        // ================= LOGIN BUTTON =================

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setBounds(
                50,
                335,
                300,
                55
        );

        loginButton.setBackground(
                new Color(37, 99, 235)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setFocusPainted(false);

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        loginButton.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        loginCard.add(loginButton);


        // ================= BACK BUTTON =================

        JButton backButton =
                new JButton("← Back to Welcome");

        backButton.setBounds(
                105,
                415,
                190,
                30
        );

        backButton.setForeground(
                new Color(35, 58, 130)
        );

        backButton.setBackground(
                new Color(243, 247, 255)
        );

        backButton.setBorderPainted(false);

        backButton.setFocusPainted(false);

        loginCard.add(backButton);


        // ================= BUTTON ACTIONS =================

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (username.isEmpty() ||
                    password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password.",
                        "Login Required",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            boolean valid =
                    AdminDAO.login(
                            username,
                            password
                    );

                if (valid) {

                    new AdminDashboard();
                    dispose();


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });


        backButton.addActionListener(e -> {

            new WelcomePage();
            dispose();

        });

        add(leftPanel);
        add(rightPanel);


        setResizable(false);
        setVisible(true);
    }
}