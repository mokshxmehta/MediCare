import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PatientDashboard extends JFrame {

    // Main content area
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Colors
    private final Color NAVY = new Color(20, 32, 87);
    private final Color BLUE = new Color(37, 99, 235);
    private final Color LIGHT_BLUE = new Color(239, 246, 255);
    private final Color BACKGROUND = new Color(248, 250, 252);
    private final Color TEXT_DARK = new Color(25, 40, 70);
    private final Color TEXT_GRAY = new Color(100, 110, 125);

    public PatientDashboard() {

        setTitle("MediCare+ - Patient Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Main layout
        setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = createSidebar();

        // Create content area
        JPanel mainArea = createMainArea();

        add(sidebar, BorderLayout.WEST);
        add(mainArea, BorderLayout.CENTER);

        setVisible(true);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setBackground(NAVY);
        sidebar.setPreferredSize(new Dimension(235, 700));
        sidebar.setLayout(null);

        // =====================================================
        // LOGO
        // =====================================================

        JLabel logo = new JLabel("MediCare+");

        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setBounds(30, 30, 180, 40);

        sidebar.add(logo);


        JLabel tagline = new JLabel("Your Health. Our Priority.");

        tagline.setForeground(new Color(180, 220, 255));
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tagline.setBounds(32, 68, 180, 25);

        sidebar.add(tagline);


        // =====================================================
        // SEPARATOR
        // =====================================================

        JSeparator separator = new JSeparator();

        separator.setForeground(new Color(70, 90, 140));
        separator.setBounds(25, 105, 185, 1);

        sidebar.add(separator);


        // =====================================================
        // NAVIGATION
        // =====================================================

        JButton homeButton = createNavButton(
                "Home",
                "assets/home.png",
                125
        );

        JButton appointmentButton = createNavButton(
                "Appointments",
                "assets/appointment.png",
                185
        );

        JButton medicineButton = createNavButton(
                "Medicine Reminder",
                "assets/medicine.png",
                245
        );

        JButton historyButton = createNavButton(
                "Appointment History",
                "assets/history.png",
                305
        );

        JButton profileButton = createNavButton(
                "Profile",
                "assets/profile.png",
                365
        );


        sidebar.add(homeButton);
        sidebar.add(appointmentButton);
        sidebar.add(medicineButton);
        sidebar.add(historyButton);
        sidebar.add(profileButton);


        // =====================================================
        // SECOND SEPARATOR
        // =====================================================

        JSeparator separator2 = new JSeparator();

        separator2.setForeground(new Color(70, 90, 140));
        separator2.setBounds(25, 435, 185, 1);

        sidebar.add(separator2);


        // =====================================================
        // SIDEBAR VECTOR IMAGE
        // =====================================================

        ImageIcon illustrationIcon =
                new ImageIcon("assets/sidebar.png");

        Image illustrationImage =
                illustrationIcon.getImage().getScaledInstance(
                        170,
                        130,
                        Image.SCALE_SMOOTH
                );

        JLabel illustration =
                new JLabel(new ImageIcon(illustrationImage));

        illustration.setBounds(
                32,
                440,
                170,
                130
        );

        sidebar.add(illustration);


        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutButton = createNavButton(
                "Logout",
                "assets/logout.png",
                575
        );

        sidebar.add(logoutButton);


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        homeButton.addActionListener(e ->
                cardLayout.show(contentPanel, "HOME")
        );

        appointmentButton.addActionListener(e ->
                cardLayout.show(contentPanel, "APPOINTMENTS")
        );

        medicineButton.addActionListener(e ->
                cardLayout.show(contentPanel, "MEDICINE")
        );

        historyButton.addActionListener(e ->
                cardLayout.show(contentPanel, "HISTORY")
        );

        profileButton.addActionListener(e ->
                cardLayout.show(contentPanel, "PROFILE")
        );


        logoutButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                DataStore.currentPatient = null;

                SessionManager.clearSession();

                DataStore.currentPatient = null;

                new WelcomePage();
                dispose();
            }
        });


        return sidebar;
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private JButton createNavButton(
            String text,
            String iconPath,
            int y
    ) {

        JButton button = new JButton(text);

        button.setBounds(
                20,
                y,
                195,
                48
        );

        // ---------------- ICON ----------------

        ImageIcon icon = new ImageIcon(iconPath);

        Image image = icon.getImage().getScaledInstance(
                20,
                20,
                Image.SCALE_SMOOTH
        );

        button.setIcon(new ImageIcon(image));


        // ---------------- STYLE ----------------

        button.setBackground(NAVY);
        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setIconTextGap(15);

        button.setBorder(
                new EmptyBorder(
                        5,
                        15,
                        5,
                        5
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =========================================================
    // MAIN AREA
    // =========================================================

    private JPanel createMainArea() {

        JPanel mainArea = new JPanel(new BorderLayout());

        mainArea.setBackground(BACKGROUND);

        // CardLayout for changing pages
        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BACKGROUND);

        // Add pages
        contentPanel.add(createHomePage(), "HOME");
        contentPanel.add(createAppointmentPage(), "APPOINTMENTS");
        contentPanel.add(createMedicinePage(), "MEDICINE");
        contentPanel.add(createHistoryPage(), "HISTORY");
        contentPanel.add(createProfilePage(), "PROFILE");

        mainArea.add(contentPanel, BorderLayout.CENTER);

        return mainArea;
    }

    // =========================================================
    // HOME PAGE
    // =========================================================

    private JPanel createHomePage() {

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(null);

        Patient patient = DataStore.currentPatient;

        String name = "Patient";
        String patientId = "Not Available";

        if (patient != null) {
            name = patient.getFullName();
            patientId = patient.getPatientId();
        }

        // =========================================================
        // GREETING
        // =========================================================

        JLabel greeting = new JLabel("Good Afternoon,");

        greeting.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        greeting.setForeground(TEXT_GRAY);

        greeting.setBounds(45, 25, 250, 30);

        panel.add(greeting);


        JLabel nameLabel = new JLabel(name);

        nameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 32)
        );

        nameLabel.setForeground(TEXT_DARK);

        nameLabel.setBounds(45, 52, 450, 45);

        panel.add(nameLabel);


        JLabel welcome = new JLabel(
                "Take care of your health, one step at a time."
        );

        welcome.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        welcome.setForeground(TEXT_GRAY);

        welcome.setBounds(45, 95, 450, 25);

        panel.add(welcome);


        // =========================================================
        // PATIENT ID
        // =========================================================

        JLabel patientIdLabel = new JLabel(
                "Patient ID: " + patientId
        );

        patientIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        patientIdLabel.setForeground(BLUE);

        patientIdLabel.setBounds(700, 40, 200, 30);

        panel.add(patientIdLabel);


        // =========================================================
        // HOME BANNER IMAGE
        // =========================================================

        ImageIcon bannerIcon =
                new ImageIcon("assets/homeBanner.jpg");

        Image bannerImage =
                bannerIcon.getImage().getScaledInstance(
                        870,
                        180,
                        Image.SCALE_SMOOTH
                );

        JLabel bannerLabel =
                new JLabel(new ImageIcon(bannerImage));

        bannerLabel.setBounds(
                45,
                135,
                870,
                180
        );

        panel.add(bannerLabel);


        // =========================================================
        // UPCOMING APPOINTMENT TITLE
        // =========================================================

        JLabel upcomingTitle =
                new JLabel("Upcoming Appointment");

        upcomingTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        upcomingTitle.setForeground(TEXT_DARK);

        upcomingTitle.setBounds(
                45,
                335,
                300,
                30
        );

        panel.add(upcomingTitle);


        JLabel viewAll =
                new JLabel("<html><u>View All →</u></html>");

        viewAll.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        viewAll.setForeground(BLUE);

        viewAll.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        viewAll.setBounds(
                815,
                337,
                100,
                25
        );

        panel.add(viewAll);


        // =========================================================
        // UPCOMING APPOINTMENT CARD
        // =========================================================

        JPanel appointmentCard = new JPanel();

        appointmentCard.setLayout(null);

        appointmentCard.setBackground(Color.WHITE);

        appointmentCard.setBounds(
                45,
                375,
                870,
                95
        );

        appointmentCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        panel.add(appointmentCard);


        /*
         * TEMPORARY STATE
         *
         * Currently we don't have the Appointment class.
         * Therefore we display an empty state.
         *
         * Later this section will be replaced with
         * real appointment information.
         */


        JLabel appointmentStatus =
                new JLabel("No upcoming appointments");

        appointmentStatus.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        appointmentStatus.setForeground(TEXT_GRAY);

        appointmentStatus.setBounds(
                25,
                30,
                300,
                30
        );

        appointmentCard.add(appointmentStatus);


        // =========================================================
        // HEALTH OVERVIEW
        // =========================================================

        JLabel overviewTitle =
                new JLabel("Health Overview");

        overviewTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        overviewTitle.setForeground(TEXT_DARK);

        overviewTitle.setBounds(
                45,
                495,
                300,
                30
        );

        panel.add(overviewTitle);


        // =========================================================
        // TOTAL APPOINTMENTS CARD
        // =========================================================

        JPanel totalCard = createOverviewCard(
                        "Total Appointments",
                        "0",
                        BLUE
                );

        totalCard.setBounds(
                45,
                535,
                270,
                90
        );

        panel.add(totalCard);


        // =========================================================
        // PENDING APPOINTMENTS CARD
        // =========================================================

        JPanel pendingCard =
                createOverviewCard(
                        "Pending",
                        "0",
                        new Color(232, 113, 53)
                );

        pendingCard.setBounds(
                335,
                535,
                270,
                90
        );

        panel.add(pendingCard);


        // =========================================================
        // ACTIVE MEDICINES CARD
        // =========================================================

        JPanel medicineCard =
                createOverviewCard(
                        "Active Medicines",
                        "0",
                        new Color(16, 150, 110)
                );

        medicineCard.setBounds(
                625,
                535,
                290,
                90
        );

        panel.add(medicineCard);


        return panel;
    }

    private JPanel createOverviewCard(
            String title,
            String number,
            Color accent
    ) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        titleLabel.setForeground(TEXT_GRAY);

        titleLabel.setBounds(
                20,
                15,
                220,
                25
        );

        card.add(titleLabel);


        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 26)
        );

        numberLabel.setForeground(accent);

        numberLabel.setBounds(
                20,
                38,
                100,
                35
        );

        card.add(numberLabel);


        return card;
    }

    // =========================================================
    // ACTION CARD
    // =========================================================

    private JPanel createActionCard(
            String title,
            String description,
            Color accent
    ) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        JLabel icon = new JLabel("●");

        icon.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        icon.setForeground(accent);

        icon.setBounds(20, 20, 40, 40);

        card.add(icon);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        titleLabel.setForeground(TEXT_DARK);

        titleLabel.setBounds(65, 18, 190, 25);

        card.add(titleLabel);

        JLabel descriptionLabel = new JLabel(
                "<html>" + description + "</html>"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        descriptionLabel.setForeground(TEXT_GRAY);

        descriptionLabel.setBounds(65, 45, 190, 40);

        card.add(descriptionLabel);

        return card;
    }

    // =========================================================
    // APPOINTMENT PAGE
    // =========================================================

    private JPanel createAppointmentPage() {

        JPanel panel = createBasicPage(
                "Appointments",
                "Book and manage your hospital appointments."
        );

        JLabel comingSoon = new JLabel(
                "Appointment Booking"
        );

        comingSoon.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        comingSoon.setForeground(BLUE);

        comingSoon.setBounds(50, 140, 400, 40);

        panel.add(comingSoon);

        JLabel info = new JLabel(
                "<html>Select your preferred date, time, reason and priority.<br>"
                        + "Your request will be sent to the hospital manager for approval.</html>"
        );

        info.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        info.setForeground(TEXT_GRAY);

        info.setBounds(50, 190, 650, 60);

        panel.add(info);

        JButton bookButton = new JButton("BOOK APPOINTMENT");

        bookButton.setBounds(50, 280, 230, 50);

        bookButton.setBackground(BLUE);

        bookButton.setForeground(Color.WHITE);

        bookButton.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        bookButton.setFocusPainted(false);

        panel.add(bookButton);

        return panel;
    }

    // =========================================================
    // MEDICINE PAGE
    // =========================================================

    private JPanel createMedicinePage() {

        JPanel panel = createBasicPage(
                "Medicine Reminder",
                "Keep track of your prescribed medicines."
        );

        JLabel title = new JLabel(
                "Your Medicines"
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(BLUE);

        title.setBounds(50, 140, 400, 40);

        panel.add(title);

        JLabel info = new JLabel(
                "<html>No medicines have been added yet.<br>"
                        + "Your prescribed medicines will appear here.</html>"
        );

        info.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        info.setForeground(TEXT_GRAY);

        info.setBounds(50, 195, 600, 60);

        panel.add(info);

        return panel;
    }

    // =========================================================
    // HISTORY PAGE
    // =========================================================

    private JPanel createHistoryPage() {

        JPanel panel = createBasicPage(
                "Appointment History",
                "View your pending, active and completed appointments."
        );

        JLabel title = new JLabel(
                "Appointment History"
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(BLUE);

        title.setBounds(50, 140, 400, 40);

        panel.add(title);

        // Status buttons

        JButton pending = new JButton("Pending");
        pending.setBounds(50, 205, 130, 40);

        JButton active = new JButton("Active");
        active.setBounds(190, 205, 130, 40);

        JButton completed = new JButton("Completed");
        completed.setBounds(330, 205, 140, 40);

        JButton cancelled = new JButton("Cancelled");
        cancelled.setBounds(480, 205, 140, 40);

        panel.add(pending);
        panel.add(active);
        panel.add(completed);
        panel.add(cancelled);

        JLabel empty = new JLabel(
                "No appointment history available."
        );

        empty.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        empty.setForeground(TEXT_GRAY);

        empty.setBounds(50, 280, 400, 30);

        panel.add(empty);

        return panel;
    }

    // =========================================================
    // PROFILE PAGE
    // =========================================================

    private JPanel createProfilePage() {

        JPanel panel = createBasicPage(
                "My Profile",
                "View your personal and medical information."
        );

        Patient patient = DataStore.currentPatient;

        // Profile card

        JPanel profileCard = new JPanel();

        profileCard.setLayout(null);

        profileCard.setBackground(Color.WHITE);

        profileCard.setBounds(50, 140, 820, 450);

        profileCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        panel.add(profileCard);

        JLabel profileTitle = new JLabel(
                "Personal Information"
        );

        profileTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        profileTitle.setForeground(BLUE);

        profileTitle.setBounds(30, 25, 300, 35);

        profileCard.add(profileTitle);

        if (patient != null) {

            addProfileLabel(
                    profileCard,
                    "Patient ID",
                    patient.getPatientId(),
                    30,
                    85
            );

            addProfileLabel(
                    profileCard,
                    "Full Name",
                    patient.getFullName(),
                    30,
                    145
            );

            addProfileLabel(
                    profileCard,
                    "Age",
                    String.valueOf(patient.getAge()),
                    30,
                    205
            );

            addProfileLabel(
                    profileCard,
                    "Gender",
                    patient.getGender(),
                    30,
                    265
            );

            addProfileLabel(
                    profileCard,
                    "Phone",
                    patient.getPhone(),
                    430,
                    85
            );

            addProfileLabel(
                    profileCard,
                    "Email",
                    patient.getEmail(),
                    430,
                    145
            );

            addProfileLabel(
                    profileCard,
                    "Blood Group",
                    patient.getBloodGroup(),
                    430,
                    205
            );

            addProfileLabel(
                    profileCard,
                    "Emergency Contact",
                    patient.getEmergencyContact(),
                    430,
                    265
            );
        }

        return panel;
    }

    // =========================================================
    // PROFILE LABEL
    // =========================================================

    private void addProfileLabel(
            JPanel panel,
            String title,
            String value,
            int x,
            int y
    ) {

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        titleLabel.setForeground(TEXT_GRAY);

        titleLabel.setBounds(x, y, 180, 20);

        panel.add(titleLabel);

        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        valueLabel.setForeground(TEXT_DARK);

        valueLabel.setBounds(x, y + 20, 330, 25);

        panel.add(valueLabel);
    }

    // =========================================================
    // BASIC PAGE HEADER
    // =========================================================

    private JPanel createBasicPage(
            String title,
            String subtitle
    ) {

        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND);

        panel.setLayout(null);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 32)
        );

        titleLabel.setForeground(TEXT_DARK);

        titleLabel.setBounds(50, 35, 600, 45);

        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel(subtitle);

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        subtitleLabel.setForeground(TEXT_GRAY);

        subtitleLabel.setBounds(50, 82, 700, 30);

        panel.add(subtitleLabel);

        return panel;
    }
}