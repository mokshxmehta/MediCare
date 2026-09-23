import javax.swing.*;
import java.util.List;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

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

        homeButton.addActionListener(e -> {

            refreshHomePage();

        });

        appointmentButton.addActionListener(e ->
                cardLayout.show(contentPanel, "APPOINTMENTS")
        );

        medicineButton.addActionListener(e -> {

            contentPanel.remove(
                    contentPanel.getComponent(2)
            );

            contentPanel.add(
                    createMedicinePage(),
                    "MEDICINE",
                    2
            );

            contentPanel.revalidate();
            contentPanel.repaint();

            cardLayout.show(
                    contentPanel,
                    "MEDICINE"
            );
        });

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

        viewAll.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                cardLayout.show(
                        contentPanel,
                        "HISTORY"
                );
            }
        });


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
                140
        );

        appointmentCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        panel.add(appointmentCard);


// =========================================================
// REAL APPOINTMENT INFORMATION
// =========================================================

        Appointment currentAppointment = null;

        if (patient != null) {

            currentAppointment =
                    AppointmentDAO.getCurrentAppointment(
                            patient.getPatientId()
                    );
        }


        if (currentAppointment == null) {

            JLabel appointmentStatus =
                    new JLabel("No upcoming appointments");

            appointmentStatus.setFont(
                    new Font("Segoe UI", Font.PLAIN, 16)
            );

            appointmentStatus.setForeground(TEXT_GRAY);

            appointmentStatus.setBounds(
                    25,
                    55,
                    500,
                    30
            );

            appointmentCard.add(appointmentStatus);

        } else {

            // =====================================================
            // DATE PANEL
            // =====================================================

            JPanel datePanel = new JPanel();

            datePanel.setLayout(null);

            datePanel.setBackground(
                    new Color(239, 246, 255)
            );

            datePanel.setBounds(
                    15,
                    15,
                    115,
                    110
            );

            datePanel.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(225, 235, 250)
                    )
            );

            appointmentCard.add(datePanel);


            java.time.LocalDate appointmentDate =
                    currentAppointment.getAppointmentDate();


            // DAY

            JLabel dayLabel =
                    new JLabel(
                            String.valueOf(
                                    appointmentDate.getDayOfMonth()
                            ),
                            SwingConstants.CENTER
                    );

            dayLabel.setFont(
                    new Font("Segoe UI", Font.BOLD, 30)
            );

            dayLabel.setForeground(TEXT_DARK);

            dayLabel.setBounds(
                    5,
                    8,
                    105,
                    35
            );

            datePanel.add(dayLabel);


            // MONTH

            String monthName =
                    appointmentDate.getMonth()
                            .toString()
                            .substring(0, 1)
                            +
                            appointmentDate.getMonth()
                                    .toString()
                                    .substring(1)
                                    .toLowerCase();


            JLabel monthLabel =
                    new JLabel(
                            monthName,
                            SwingConstants.CENTER
                    );

            monthLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 14)
            );

            monthLabel.setForeground(TEXT_DARK);

            monthLabel.setBounds(
                    5,
                    45,
                    105,
                    22
            );

            datePanel.add(monthLabel);


            // YEAR

            JLabel yearLabel =
                    new JLabel(
                            String.valueOf(
                                    appointmentDate.getYear()
                            ),
                            SwingConstants.CENTER
                    );

            yearLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13)
            );

            yearLabel.setForeground(TEXT_GRAY);

            yearLabel.setBounds(
                    5,
                    67,
                    105,
                    20
            );

            datePanel.add(yearLabel);


            // DAY OF WEEK

            String dayOfWeek =
                    appointmentDate.getDayOfWeek()
                            .toString()
                            .substring(0, 1)
                            +
                            appointmentDate.getDayOfWeek()
                                    .toString()
                                    .substring(1)
                                    .toLowerCase();


            JLabel weekdayLabel =
                    new JLabel(
                            dayOfWeek,
                            SwingConstants.CENTER
                    );

            weekdayLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 12)
            );

            weekdayLabel.setForeground(TEXT_GRAY);

            weekdayLabel.setBounds(
                    5,
                    88,
                    105,
                    18
            );

            datePanel.add(weekdayLabel);


            // =====================================================
            // VERTICAL SEPARATOR
            // =====================================================

            JSeparator dateSeparator =
                    new JSeparator(
                            SwingConstants.VERTICAL
                    );

            dateSeparator.setForeground(
                    new Color(215, 225, 240)
            );

            dateSeparator.setBounds(
                    145,
                    20,
                    1,
                    100
            );

            appointmentCard.add(dateSeparator);


            // =====================================================
            // DOCTOR NAME
            // =====================================================

            String doctorName =
                    currentAppointment.getDoctorName();


            if (
                    doctorName == null
                            ||
                            doctorName.trim().isEmpty()
            ) {

                doctorName =
                        "Doctor assignment pending";
            }


            JLabel doctorLabel =
                    new JLabel(
                            doctorName
                    );

            doctorLabel.setFont(
                    new Font("Calisto MT", Font.BOLD, 21)
            );

            doctorLabel.setForeground(TEXT_DARK);

            doctorLabel.setBounds(
                    170,
                    15,
                    470,
                    28
            );

            appointmentCard.add(doctorLabel);


            // =====================================================
            // STATUS
            // =====================================================

            String displayStatus;

            if (
                    currentAppointment.getStatus()
                            .equals("APPROVED")
            ) {

                displayStatus = "ACTIVE";

            } else {

                displayStatus =
                        currentAppointment.getStatus();
            }


            JLabel statusLabel =
                    new JLabel(
                            "STATUS : " + displayStatus
                    );

            statusLabel.setFont(
                    new Font("Segoe UI", Font.BOLD, 14)
            );

            statusLabel.setForeground(BLUE);

            statusLabel.setBounds(
                    665,
                    12,
                    180,
                    25
            );

            appointmentCard.add(statusLabel);


            // =====================================================
            // DEPARTMENT
            // =====================================================

            JLabel departmentLabel =
                    new JLabel(
                            "Department : "
                                    + currentAppointment
                                    .getDepartment()
                    );

            departmentLabel.setFont(
                    new Font("Segoe UI", Font.BOLD, 14)
            );

            departmentLabel.setForeground(TEXT_GRAY);

            departmentLabel.setBounds(
                    170,
                    42,
                    300,
                    22
            );

            appointmentCard.add(departmentLabel);


            // =====================================================
            // TIME
            // =====================================================

            String displayTime;

            if (
                    currentAppointment.getStatus()
                            .equals("APPROVED")
                            &&
                            currentAppointment.getConfirmedTime() != null
                            &&
                            !currentAppointment.getConfirmedTime().isEmpty()
            ) {

                displayTime =
                        currentAppointment.getConfirmedTime();

            } else {

                displayTime =
                        currentAppointment.getPreferredTime();
            }


            JLabel timeLabel =
                    new JLabel(
                            "Time : " + displayTime
                    );

            timeLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 14)
            );

            timeLabel.setForeground(TEXT_GRAY);

            timeLabel.setBounds(
                    170,
                    75,
                    300,
                    22
            );

            appointmentCard.add(timeLabel);


            // =====================================================
            // TYPE
            // =====================================================

            JLabel typeLabel =
                    new JLabel(
                            "Type : "
                                    + currentAppointment
                                    .getAppointmentType()
                    );

            typeLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 14)
            );

            typeLabel.setForeground(TEXT_GRAY);

            typeLabel.setBounds(
                    500,
                    75,
                    180,
                    22
            );

            appointmentCard.add(typeLabel);


            // =====================================================
            // PRIORITY
            // =====================================================

            JLabel priorityLabel =
                    new JLabel(
                            "Priority : "
                                    + currentAppointment
                                    .getPriority()
                    );

            priorityLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 14)
            );

            priorityLabel.setForeground(TEXT_GRAY);

            priorityLabel.setBounds(
                    170,
                    100,
                    180,
                    22
            );

            appointmentCard.add(priorityLabel);


            // =====================================================
            // REASON
            // =====================================================

            JLabel reasonLabel =
                    new JLabel(
                            "Reason : "
                                    + currentAppointment.getReason()
                    );

            reasonLabel.setFont(
                    new Font("Segoe UI", Font.PLAIN, 14)
            );

            reasonLabel.setForeground(TEXT_GRAY);

            reasonLabel.setBounds(
                    500,
                    93,
                    490,
                    35
            );

            appointmentCard.add(reasonLabel);


            // =====================================================
            // DOCTOR / ROOM
            // =====================================================

            if (
                    currentAppointment.getRoomNumber() != null
                            &&
                            !currentAppointment.getRoomNumber().isEmpty()
            ) {

                JLabel roomLabel =
                        new JLabel(
                                "Room : "
                                        + currentAppointment
                                        .getRoomNumber()
                        );

                roomLabel.setFont(
                        new Font("Segoe UI", Font.PLAIN, 13)
                );

                roomLabel.setForeground(TEXT_GRAY);

                roomLabel.setBounds(
                        650,
                        90,
                        180,
                        22
                );

                appointmentCard.add(roomLabel);
            }
        }


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
                523,
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
                560,
                270,
                90
        );

        panel.add(totalCard);
        if (patient != null) {

            int totalAppointments =
                    AppointmentDAO.getTotalAppointmentCount(
                            patient.getPatientId()
                    );

            updateOverviewCardNumber(
                    totalCard,
                    String.valueOf(totalAppointments)
            );
        }


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
                560,
                270,
                90
        );

        panel.add(pendingCard);
        if (patient != null) {

            int pendingAppointments =
                    AppointmentDAO.getPendingAppointmentCount(
                            patient.getPatientId()
                    );

            updateOverviewCardNumber(
                    pendingCard,
                    String.valueOf(pendingAppointments)
            );
        }


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
                560,
                290,
                90
        );

        panel.add(medicineCard);


        return panel;
    }

    private void refreshHomePage() {

        contentPanel.remove(
                contentPanel.getComponent(0)
        );

        contentPanel.add(
                createHomePage(),
                "HOME"
        );

        contentPanel.revalidate();
        contentPanel.repaint();

        cardLayout.show(
                contentPanel,
                "HOME"
        );
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

    private void updateOverviewCardNumber(
            JPanel card,
            String number
    ) {

        for (Component component :
                card.getComponents()) {

            if (component instanceof JLabel) {

                JLabel label =
                        (JLabel) component;

                if (
                        label.getFont().getSize() == 26
                                &&
                                label.getFont().isBold()
                ) {

                    label.setText(number);
                    return;
                }
            }
        }
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

        // =========================================================
        // OUTER PAGE
        // =========================================================

        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(BACKGROUND);


        // =========================================================
        // SCROLLABLE CONTENT
        // =========================================================

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND);

        // This is taller than the visible dashboard area,
        // allowing the user to scroll down.
        panel.setPreferredSize(
                new Dimension(950, 850)
        );


        // =========================================================
        // PAGE TITLE
        // =========================================================

        JLabel title = new JLabel(
                "Appointments"
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(BLUE);

        title.setBounds(
                50, 25, 500, 40
        );

        panel.add(title);


        JLabel subtitle = new JLabel(
                "Book and manage your hospital appointments."
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        subtitle.setForeground(TEXT_GRAY);

        subtitle.setBounds(
                50, 68, 650, 30
        );

        panel.add(subtitle);


        // =========================================================
        // PATIENT INFORMATION CARD
        // =========================================================

        JPanel patientCard = new JPanel();

        patientCard.setLayout(null);

        patientCard.setBackground(Color.WHITE);

        patientCard.setBounds(
                50, 115, 850, 155
        );

        patientCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        panel.add(patientCard);


        JLabel patientTitle = new JLabel(
                "Patient Information"
        );

        patientTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 17)
        );

        patientTitle.setForeground(BLUE);

        patientTitle.setBounds(
                20, 12, 300, 25
        );

        patientCard.add(patientTitle);


        Patient patient =
                DataStore.currentPatient;


        // =========================================================
        // PATIENT DATA
        // =========================================================

        String patientName =
                patient != null
                        ? patient.getFullName()
                        : "-";

        String patientId =
                patient != null
                        ? patient.getPatientId()
                        : "-";

        String age =
                patient != null
                        ? String.valueOf(patient.getAge())
                        : "-";

        String gender =
                patient != null
                        ? patient.getGender()
                        : "-";

        String phone =
                patient != null
                        ? patient.getPhone()
                        : "-";

        String bloodGroup =
                patient != null
                        ? patient.getBloodGroup()
                        : "-";


        // =========================================================
        // ROW 1
        // =========================================================

        JLabel nameLabel =
                new JLabel(
                        "Name: " + patientName
                );

        nameLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        nameLabel.setBounds(
                20, 50, 250, 25
        );

        patientCard.add(nameLabel);


        JLabel idLabel =
                new JLabel(
                        "Patient ID: " + patientId
                );

        idLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        idLabel.setBounds(
                300, 50, 250, 25
        );

        patientCard.add(idLabel);


        JLabel ageLabel =
                new JLabel(
                        "Age: " + age
                );

        ageLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        ageLabel.setBounds(
                550, 50, 250, 25
        );

        patientCard.add(ageLabel);


        // =========================================================
        // ROW 2
        // =========================================================

        JLabel genderLabel =
                new JLabel(
                        "Gender: " + gender
                );

        genderLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        genderLabel.setBounds(
                20, 90, 250, 25
        );

        patientCard.add(genderLabel);


        JLabel phoneLabel =
                new JLabel(
                        "Phone: " + phone
                );

        phoneLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        phoneLabel.setBounds(
                300, 90, 250, 25
        );

        patientCard.add(phoneLabel);


        JLabel bloodLabel =
                new JLabel(
                        "Blood Group: " + bloodGroup
                );

        bloodLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        bloodLabel.setBounds(
                550, 90, 250, 25
        );

        patientCard.add(bloodLabel);


        // =========================================================
        // BOOK APPOINTMENT CARD
        // =========================================================

        JPanel bookingCard = new JPanel();

        bookingCard.setLayout(null);

        bookingCard.setBackground(Color.WHITE);

        bookingCard.setBounds(
                50, 290, 850, 490
        );

        bookingCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(215, 225, 240)
                )
        );

        panel.add(bookingCard);


        JLabel bookingTitle =
                new JLabel(
                        "Book New Appointment"
                );

        bookingTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        bookingTitle.setForeground(BLUE);

        bookingTitle.setBounds(
                20, 18, 350, 30
        );

        bookingCard.add(bookingTitle);


        // =========================================================
        // DEPARTMENT
        // =========================================================

        JLabel departmentLabel =
                new JLabel(
                        "Department"
                );

        departmentLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        departmentLabel.setForeground(TEXT_DARK);

        departmentLabel.setBounds(
                20, 70, 200, 25
        );

        bookingCard.add(departmentLabel);


        JComboBox<String> departmentBox =
                new JComboBox<>(
                        new String[]{
                                "Select Department",
                                "General Medicine",
                                "Dermatology",
                                "Cardiology",
                                "Orthopedics",
                                "ENT",
                                "Dentistry",
                                "Pediatrics",
                                "Gynecology"
                        }
                );

        departmentBox.setBounds(
                20, 98, 350, 38
        );

        departmentBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        bookingCard.add(departmentBox);


        // =========================================================
        // APPOINTMENT TYPE
        // =========================================================

        JLabel visitLabel =
                new JLabel(
                        "Appointment Type"
                );

        visitLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        departmentLabel.setForeground(TEXT_DARK);

        visitLabel.setBounds(
                450, 70, 200, 25
        );

        bookingCard.add(visitLabel);


        JRadioButton firstVisit =
                new JRadioButton(
                        "First Visit"
                );

        JRadioButton followUp =
                new JRadioButton(
                        "Follow-up"
                );


        firstVisit.setBackground(Color.WHITE);
        followUp.setBackground(Color.WHITE);


        firstVisit.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        followUp.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );


        firstVisit.setBounds(
                450, 98, 120, 35
        );

        followUp.setBounds(
                575, 98, 120, 35
        );


        ButtonGroup visitGroup =
                new ButtonGroup();

        visitGroup.add(firstVisit);
        visitGroup.add(followUp);


        firstVisit.setSelected(true);


        bookingCard.add(firstVisit);
        bookingCard.add(followUp);

// =========================================================
// DATE
// =========================================================

        JLabel dateLabel =
                new JLabel(
                        "Preferred Date"
                );

        dateLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        dateLabel.setForeground(TEXT_DARK);

        dateLabel.setBounds(
                20, 155, 200, 25
        );

        bookingCard.add(dateLabel);


// Current date
        java.time.LocalDate today =
                java.time.LocalDate.now();

// Maximum booking date = one month from today
        java.time.LocalDate maxDate =
                today.plusMonths(1);


// =========================================================
// DAY COMBOBOX
// =========================================================

        JComboBox<Integer> dayBox =
                new JComboBox<>();


        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }


        dayBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        dayBox.setBounds(
                20, 183, 100, 38
        );

        bookingCard.add(dayBox);


// =========================================================
// MONTH COMBOBOX
// =========================================================

        JComboBox<String> monthBox =
                new JComboBox<>(
                        new String[]{
                                "January",
                                "February",
                                "March",
                                "April",
                                "May",
                                "June",
                                "July",
                                "August",
                                "September",
                                "October",
                                "November",
                                "December"
                        }
                );


        monthBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        monthBox.setBounds(
                130, 183, 140, 38
        );

        bookingCard.add(monthBox);


        // =========================================================
        // YEAR BOX
        // =========================================================

        JComboBox<Integer> yearBox =
                new JComboBox<>();

        yearBox.addItem(2026);
        yearBox.setSelectedItem(2026);


        yearBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        yearBox.setBounds(
                280, 183, 90, 38
        );

        bookingCard.add(yearBox);


        // =========================================================
        // TIME
        // =========================================================

        JLabel timeLabel =
                new JLabel(
                        "Preferred Time"
                );

        timeLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        timeLabel.setForeground(TEXT_DARK);

        timeLabel.setBounds(
                450, 155, 200, 25
        );

        bookingCard.add(timeLabel);


        JComboBox<String> timeBox =
                new JComboBox<>(
                        new String[]{
                                "Select Time",
                                "09:00 AM - 10:00 AM",
                                "10:00 AM - 11:00 AM",
                                "11:00 AM - 12:00 PM",
                                "12:00 PM - 01:00 PM",
                                "02:00 PM - 03:00 PM",
                                "03:00 PM - 04:00 PM",
                                "04:00 PM - 05:00 PM",
                                "05:00 PM - 06:00 PM"
                        }
                );


        timeBox.setBounds(
                450, 183, 350, 38
        );

        timeBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        bookingCard.add(timeBox);


        // =========================================================
        // REASON
        // =========================================================

        JLabel reasonLabel =
                new JLabel(
                        "Reason for Appointment"
                );

        reasonLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        reasonLabel.setForeground(TEXT_DARK);

        reasonLabel.setBounds(
                20, 240, 250, 25
        );

        bookingCard.add(reasonLabel);


        JTextArea reasonArea =
                new JTextArea();


        reasonArea.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        reasonArea.setLineWrap(true);

        reasonArea.setWrapStyleWord(true);


        reasonArea.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 210, 225)
                )
        );


        JScrollPane reasonScroll =
                new JScrollPane(
                        reasonArea
                );


        reasonScroll.setBounds(
                20, 270, 780, 70
        );

        bookingCard.add(reasonScroll);


        // =========================================================
        // PRIORITY
        // =========================================================

        JLabel priorityLabel =
                new JLabel(
                        "Priority"
                );

        priorityLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        priorityLabel.setForeground(TEXT_DARK);

        priorityLabel.setBounds(
                20, 355, 100, 25
        );

        bookingCard.add(priorityLabel);


        JRadioButton normal =
                new JRadioButton(
                        "Normal"
                );

        JRadioButton urgent =
                new JRadioButton(
                        "Urgent"
                );

        JRadioButton emergency =
                new JRadioButton(
                        "Emergency"
                );


        normal.setBackground(Color.WHITE);
        urgent.setBackground(Color.WHITE);
        emergency.setBackground(Color.WHITE);


        normal.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        urgent.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        emergency.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );


        normal.setBounds(
                20, 380, 100, 30
        );

        urgent.setBounds(
                130, 380, 100, 30
        );

        emergency.setBounds(
                240, 380, 120, 30
        );


        ButtonGroup priorityGroup =
                new ButtonGroup();

        priorityGroup.add(normal);
        priorityGroup.add(urgent);
        priorityGroup.add(emergency);


        normal.setSelected(true);


        bookingCard.add(normal);
        bookingCard.add(urgent);
        bookingCard.add(emergency);


        // =========================================================
        // REQUEST BUTTON
        // =========================================================

        JButton bookButton =
                new JButton(
                        "REQUEST APPOINTMENT"
                );


        bookButton.setBounds(
                570, 370, 230, 45
        );

        bookButton.setBackground(BLUE);

        bookButton.setForeground(Color.WHITE);

        bookButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        bookButton.setFocusPainted(false);

        bookButton.setBorderPainted(false);


        bookingCard.add(bookButton);


// =========================================================
// APPOINTMENT BOOKING ACTION
// =========================================================

        bookButton.addActionListener(e -> {

            // =====================================================
            // CHECK LOGGED-IN PATIENT
            // =====================================================


            if (patient == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No patient is currently logged in.",
                        "Session Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // =====================================================
            // CHECK EXISTING APPOINTMENT
            // =====================================================

            boolean alreadyHasAppointment =
                    AppointmentDAO.hasActiveAppointment(
                            patient.getPatientId()
                    );

            if (alreadyHasAppointment) {

                JOptionPane.showMessageDialog(
                        this,
                        "You already have a pending or active appointment.\n\n"
                                + "Please wait for the current appointment to be completed "
                                + "or cancel the pending appointment before booking another.",
                        "Appointment Already Exists",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // GET DEPARTMENT
            // =====================================================

            String department =
                    (String) departmentBox.getSelectedItem();

            if (department.equals("Select Department")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a department.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // GET APPOINTMENT TYPE
            // =====================================================

            String appointmentType;

            if (firstVisit.isSelected()) {

                appointmentType = "First Visit";

            } else {

                appointmentType = "Follow-up";
            }


            // =====================================================
            // GET DATE
            // =====================================================

            int selectedDay =
                    (Integer) dayBox.getSelectedItem();

            int selectedMonth =
                    monthBox.getSelectedIndex() + 1;

            int selectedYear =
                    (Integer) yearBox.getSelectedItem();


            java.time.LocalDate selectedDate;

            try {

                selectedDate =
                        java.time.LocalDate.of(
                                selectedYear,
                                selectedMonth,
                                selectedDay
                        );

            } catch (java.time.DateTimeException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a valid date.",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // DATE RANGE VALIDATION
            // =====================================================

            // Cannot book in the past
            if (selectedDate.isBefore(today)) {

                JOptionPane.showMessageDialog(
                        this,
                        "You cannot book an appointment for a past date.",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Cannot book more than one month ahead
            if (selectedDate.isAfter(maxDate)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointments can only be booked up to one month in advance.",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // GET TIME
            // =====================================================

            String preferredTime =
                    (String) timeBox.getSelectedItem();

            if (preferredTime.equals("Select Time")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a preferred time.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // GET REASON
            // =====================================================

            String reason =
                    reasonArea.getText().trim();

            if (reason.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the reason for your appointment.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // =====================================================
            // GET PRIORITY
            // =====================================================

            String priority;

            if (urgent.isSelected()) {

                priority = "Urgent";

            } else if (emergency.isSelected()) {

                priority = "Emergency";

            } else {

                priority = "Normal";
            }


            // =====================================================
            // CREATE APPOINTMENT OBJECT
            // =====================================================

            Appointment appointment =
                    new Appointment(
                            patient.getPatientId(),
                            patient.getFullName(),
                            department,
                            appointmentType,
                            selectedDate,
                            preferredTime,
                            reason,
                            priority
                    );


            // =====================================================
            // SAVE TO DATABASE
            // =====================================================

            boolean created =
                    AppointmentDAO.createAppointment(
                            appointment
                    );


            // =====================================================
            // SUCCESS
            // =====================================================

            if (created) {

                JOptionPane.showMessageDialog(
                        this,
                        "Your appointment request has been submitted successfully.\n\n"
                                + "Status: PENDING\n"
                                + "The hospital manager will review your request.",
                        "Appointment Requested",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // =================================================
                // CLEAR FORM
                // =================================================

                departmentBox.setSelectedIndex(0);

                firstVisit.setSelected(true);

                monthBox.setSelectedIndex(
                        today.getMonthValue() - 1
                );

                dayBox.setSelectedItem(
                        today.getDayOfMonth()
                );

                yearBox.setSelectedItem(2026);

                timeBox.setSelectedIndex(0);

                reasonArea.setText("");

                normal.setSelected(true);


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to create the appointment.\n"
                                + "Please try again.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // =========================================================
        // SCROLL PANE
        // =========================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        panel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                );


        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);


        // =========================================================
        // RETURN PAGE
        // =========================================================

        page.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
// MEDICINE REMINDER PAGE
// =========================================================

    private JPanel createMedicinePage() {

        JPanel page =
                new JPanel(new BorderLayout());

        page.setBackground(BACKGROUND);


        // =====================================================
        // MAIN CONTENT PANEL
        // =====================================================

        JPanel panel =
                new JPanel();

        panel.setLayout(null);
        panel.setBackground(BACKGROUND);

        // Large enough for scrolling
        panel.setPreferredSize(
                new Dimension(
                        950,
                        900
                )
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Medicine Reminder"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(TEXT_DARK);

        title.setBounds(
                50,
                30,
                500,
                40
        );

        panel.add(title);


        // =====================================================
        // PAGE SUBTITLE
        // =====================================================

        JLabel subtitle =
                new JLabel(
                        "View prescriptions from your completed appointments."
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(TEXT_GRAY);

        subtitle.setBounds(
                50,
                72,
                700,
                30
        );

        panel.add(subtitle);


        // =====================================================
        // SECTION TITLE
        // =====================================================

        JLabel sectionTitle =
                new JLabel(
                        "Completed Appointments"
                );

        sectionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        sectionTitle.setForeground(TEXT_DARK);

        sectionTitle.setBounds(
                50,
                125,
                400,
                30
        );

        panel.add(sectionTitle);


        // =====================================================
        // APPOINTMENT CONTAINER
        // =====================================================

        JPanel appointmentsPanel =
                new JPanel();

        appointmentsPanel.setLayout(
                new BoxLayout(
                        appointmentsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        appointmentsPanel.setBackground(
                BACKGROUND
        );

        appointmentsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        0,
                        20,
                        0
                )
        );


        // =====================================================
        // EMPTY STATE
        // =====================================================

        JLabel emptyLabel =
                new JLabel(
                        "<html><div style='text-align:center;'>" +
                                "<b>No completed appointments yet</b><br>" +
                                "Your completed appointments and prescriptions " +
                                "will appear here." +
                                "</div></html>",
                        SwingConstants.CENTER
                );

        emptyLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        emptyLabel.setForeground(
                TEXT_GRAY
        );

        emptyLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        emptyLabel.setPreferredSize(
                new Dimension(
                        850,
                        100
                )
        );

        appointmentsPanel.add(
                emptyLabel
        );


        // =====================================================
        // APPOINTMENT SCROLL
        // =====================================================

        JScrollPane appointmentScroll =
                new JScrollPane(
                        appointmentsPanel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                );

        appointmentScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                215,
                                225,
                                240
                        )
                )
        );

        appointmentScroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        appointmentScroll.setBounds(
                50,
                165,
                865,
                650
        );

        panel.add(
                appointmentScroll
        );


        // =====================================================
// LOAD COMPLETED APPOINTMENTS FROM DATABASE
// =====================================================

        if (DataStore.currentPatient != null) {

            String patientId =
                    DataStore.currentPatient.getPatientId();

            List<Appointment> completedAppointments =
                    AppointmentDAO.getAppointments(
                            patientId,
                            "COMPLETED"
                    );

            if (!completedAppointments.isEmpty()) {

                appointmentsPanel.removeAll();

                for (Appointment appointment :
                        completedAppointments) {

                    appointmentsPanel.add(
                            createCompletedAppointmentCard(
                                    appointment
                            )
                    );

                    appointmentsPanel.add(
                            Box.createVerticalStrut(12)
                    );
                }

                appointmentsPanel.revalidate();
                appointmentsPanel.repaint();
            }
        }


        // =====================================================
        // FINAL PAGE SCROLL
        // =====================================================

        JScrollPane pageScroll =
                new JScrollPane(
                        panel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                );

        pageScroll.setBorder(null);

        pageScroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        page.add(
                pageScroll,
                BorderLayout.CENTER
        );


        return page;
    }

    // =========================================================
// COMPLETED APPOINTMENT CARD
// =========================================================

    private JPanel createCompletedAppointmentCard(
            Appointment appointment
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(null);

        card.setBackground(
                Color.WHITE
        );

        card.setPreferredSize(
                new Dimension(
                        820,
                        180
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        180
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        215,
                                        225,
                                        240
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                5,
                                5,
                                5
                        )
                )
        );


        // =====================================================
        // DEPARTMENT
        // =====================================================

        JLabel department =
                new JLabel(
                        appointment.getDepartment()
                );

        department.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        department.setForeground(
                TEXT_DARK
        );

        department.setBounds(
                20,
                15,
                350,
                30
        );

        card.add(
                department
        );


        // =====================================================
        // COMPLETED STATUS
        // =====================================================

        JLabel status =
                new JLabel(
                        "COMPLETED",
                        SwingConstants.CENTER
                );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        status.setForeground(
                new Color(
                        22,
                        101,
                        52
                )
        );

        status.setBackground(
                new Color(
                        220,
                        252,
                        231
                )
        );

        status.setOpaque(true);

        status.setBounds(
                690,
                15,
                110,
                28
        );

        card.add(
                status
        );


        // =====================================================
        // DOCTOR
        // =====================================================

        JLabel doctor =
                new JLabel(
                        "Doctor: " +
                                appointment.getDoctorName()
                );

        doctor.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        doctor.setForeground(
                TEXT_GRAY
        );

        doctor.setBounds(
                20,
                55,
                350,
                25
        );

        card.add(
                doctor
        );


        // =====================================================
        // DATE
        // =====================================================

        JLabel date =
                new JLabel(
                        "Date: " +
                                appointment.getAppointmentDate()
                );

        date.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        date.setForeground(
                TEXT_GRAY
        );

        date.setBounds(
                20,
                82,
                300,
                25
        );

        card.add(
                date
        );


        // =====================================================
        // APPOINTMENT TYPE
        // =====================================================

        JLabel type =
                new JLabel(
                        "Type: " +
                                appointment.getAppointmentType()
                );

        type.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        type.setForeground(
                TEXT_GRAY
        );

        type.setBounds(
                320,
                82,
                250,
                25
        );

        card.add(
                type
        );


        // =====================================================
        // REASON
        // =====================================================

        JLabel reason =
                new JLabel(
                        "<html>Reason: " +
                                appointment.getReason() +
                                "</html>"
                );

        reason.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        reason.setForeground(
                TEXT_GRAY
        );

        reason.setBounds(
                20,
                112,
                500,
                40
        );

        card.add(
                reason
        );


        // =====================================================
        // VIEW PRESCRIPTION BUTTON
        // =====================================================

        JButton viewButton =
                new JButton(
                        "View Prescription"
                );

        viewButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        viewButton.setForeground(
                Color.WHITE
        );

        viewButton.setBackground(
                BLUE
        );

        viewButton.setFocusPainted(false);

        viewButton.setBorderPainted(false);

        viewButton.setBounds(
                590,
                105,
                210,
                38
        );

        card.add(
                viewButton
        );


        // =====================================================
        // BUTTON ACTION
        // =====================================================

        viewButton.addActionListener(e -> {

            List<Prescription> prescriptions =
                    PrescriptionDAO
                            .getPrescriptionsByAppointment(
                                    appointment.getAppointmentId()
                            );

            if (prescriptions.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No prescription was added for this appointment.",
                        "Prescription",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            int patientAge =
                    DataStore.currentPatient.getAge();

            openPrescriptionDetails(
                    appointment,
                    prescriptions,
                    patientAge
            );
        });
        return card;
    }


    // =========================================================
    // PRESCRIPTION DETAILS WINDOW
    // =========================================================

    private void openPrescriptionDetails(
            Appointment appointment,
            List<Prescription> prescriptions,
            int patientAge
    ) {

        JDialog dialog =
                new JDialog(
                        this,
                        "Prescription Details",
                        true
                );

        dialog.setSize(
                820,
                700
        );

        dialog.setLocationRelativeTo(
                this
        );

        dialog.setLayout(
                new BorderLayout()
        );

        // =====================================================
        // MAIN CONTAINER
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        dialog.add(
                mainPanel
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(
                Color.WHITE
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        20,
                        30
                )
        );


        JLabel title =
                new JLabel(
                        "Prescription Details"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        header.add(
                title
        );


        JLabel subtitle =
                new JLabel(
                        "Complete details of medicines prescribed by your doctor."
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                TEXT_GRAY
        );

        header.add(
                Box.createVerticalStrut(5)
        );

        header.add(
                subtitle
        );


        header.add(
                Box.createVerticalStrut(18)
        );


        // =====================================================
        // APPOINTMENT / PATIENT INFORMATION
        // =====================================================

        JPanel infoPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                1,
                                0
                        )
                );

        infoPanel.setBackground(
                new Color(
                        248,
                        250,
                        255
                )
        );

        infoPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        215,
                                        225,
                                        240
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );


        // Patient

        JPanel patientInfo =
                createPrescriptionInfoBlock(
                        "Patient Name",
                        appointment.getPatientName()
                );

        JPanel dateInfo =
                createPrescriptionInfoBlock(
                        "Appointment Date",
                        appointment.getAppointmentDate()
                                .format(
                                        java.time.format.DateTimeFormatter.ofPattern("dd MM yyyy")
                                )
                );

        infoPanel.add(
                dateInfo
        );

        infoPanel.add(
                patientInfo
        );


        // Age

        JPanel ageInfo =
                createPrescriptionInfoBlock(
                        "Age",
                        String.valueOf(
                                patientAge
                        )
                );

        infoPanel.add(
                ageInfo
        );


        // Doctor

        JPanel doctorInfo =
                createPrescriptionInfoBlock(
                        "Doctor",
                        appointment.getDoctorName()
                );

        infoPanel.add(
                doctorInfo
        );


        // Department

        JPanel departmentInfo =
                createPrescriptionInfoBlock(
                        "Department",
                        appointment.getDepartment()
                );

        infoPanel.add(
                departmentInfo
        );


        header.add(
                infoPanel
        );


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // MEDICINES SECTION
        // =====================================================

        JPanel medicinesContainer =
                new JPanel();

        medicinesContainer.setLayout(
                new BoxLayout(
                        medicinesContainer,
                        BoxLayout.Y_AXIS
                )
        );

        medicinesContainer.setBackground(
                BACKGROUND
        );

        medicinesContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        JLabel medicinesTitle =
                new JLabel(
                        "Prescribed Medicines"
                );

        medicinesTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        medicinesTitle.setForeground(
                TEXT_DARK
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        medicinesContainer.add(
                medicinesTitle
        );


        JLabel medicinesSubtitle =
                new JLabel(
                        "Take your medicines as advised and complete the full course."
                );

        medicinesSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        medicinesSubtitle.setForeground(
                TEXT_GRAY
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        medicinesContainer.add(
                Box.createVerticalStrut(4)
        );

        medicinesContainer.add(
                medicinesSubtitle
        );


        medicinesContainer.add(
                Box.createVerticalStrut(15)
        );


        // =====================================================
        // MEDICINE CARDS
        // =====================================================

        for (
                int i = 0;
                i < prescriptions.size();
                i++
        ) {

            Prescription prescription =
                    prescriptions.get(i);

            JPanel medicineCard =
                    createPrescriptionCard(
                            prescription,
                            i + 1
                    );

            medicinesContainer.add(
                    medicineCard
            );

            if (
                    i <
                            prescriptions.size() - 1
            ) {

                medicinesContainer.add(
                        Box.createVerticalStrut(12)
                );
            }
        }


        JScrollPane medicineScroll =
                new JScrollPane(
                        medicinesContainer,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                );

        medicineScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        medicineScroll.getVerticalScrollBar()
                .setUnitIncrement(16);


        mainPanel.add(
                medicineScroll,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footer =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        footer.setBackground(
                Color.WHITE
        );

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        30,
                        15,
                        30
                )
        );


        JLabel note =
                new JLabel(
                        "<html>Please follow the dosage and timings as advised by your doctor.</html>"
                );

        note.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        note.setForeground(
                new Color(
                        55,
                        75,
                        115
                )
        );

        note.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        230,
                                        245
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        note.setOpaque(
                true
        );

        note.setBackground(
                new Color(
                        240,
                        246,
                        255
                )
        );


        footer.add(
                note,
                BorderLayout.CENTER
        );


        JButton closeButton =
                new JButton(
                        "Close"
                );

        closeButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        closeButton.setForeground(
                Color.WHITE
        );

        closeButton.setBackground(
                BLUE
        );

        closeButton.setFocusPainted(
                false
        );

        closeButton.setBorderPainted(
                false
        );

        closeButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        closeButton.setPreferredSize(
                new Dimension(
                        120,
                        42
                )
        );

        closeButton.addActionListener(
                e -> dialog.dispose()
        );

        footer.add(
                closeButton,
                BorderLayout.EAST
        );


        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );


        dialog.setVisible(
                true
        );
    }

    private JPanel createPrescriptionInfoBlock(
            String label,
            String value
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                new Color(
                        248,
                        250,
                        255
                )
        );


        JLabel labelText =
                new JLabel(
                        label
                );

        labelText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        labelText.setForeground(
                new Color(
                        75,
                        95,
                        135
                ));


        JLabel valueText =
                new JLabel(
                        value
                );

        valueText.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        valueText.setForeground(
                TEXT_DARK
        );


        panel.add(
                labelText
        );

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(
                valueText
        );


        return panel;
    }

    // =========================================================
    // PRESCRIPTION MEDICINE CARD
    // =========================================================

    private JPanel createPrescriptionCard(
            Prescription prescription,
            int medicineNumber
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        215,
                                        225,
                                        240
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        card.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        245
                )
        );

        card.setPreferredSize(
                new Dimension(
                        700,
                        245
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        245
                )
        );


        // =====================================================
        // MEDICINE NUMBER
        // =====================================================

        JLabel numberLabel =
                new JLabel(
                        "Medicine " + medicineNumber
                );

        numberLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        numberLabel.setForeground(
                BLUE
        );

        numberLabel.setPreferredSize(
                new Dimension(
                        90,
                        30
                )
        );

        numberLabel.setVerticalAlignment(
                SwingConstants.TOP
        );


        card.add(
                numberLabel,
                BorderLayout.WEST
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBackground(
                Color.WHITE
        );

        content.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // MEDICINE NAME
        // =====================================================

        JLabel medicineName =
                new JLabel(
                        prescription.getMedicineName()
                );

        medicineName.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        medicineName.setForeground(
                TEXT_DARK
        );

        medicineName.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        content.add(
                medicineName
        );


        // =====================================================
        // DESCRIPTION / SHORT INFORMATION
        // =====================================================

        JLabel shortDescription =
                new JLabel(
                        "<html>" +
                                prescription.getDescription() +
                                "</html>"
                );

        shortDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        shortDescription.setForeground(
                TEXT_GRAY
        );

        shortDescription.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        content.add(
                Box.createVerticalStrut(4)
        );

        content.add(
                shortDescription
        );


        content.add(
                Box.createVerticalStrut(12)
        );


        // =====================================================
        // DOSAGE + WHEN TO TAKE
        // =====================================================

        JPanel detailsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                0,
                                0
                        )
                );

        detailsPanel.setBackground(
                new Color(
                        245,
                        248,
                        253
                )
        );

        detailsPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                225,
                                232,
                                242
                        )
                )
        );

        detailsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        // =====================================================
        // DOSAGE
        // =====================================================

        JPanel dosagePanel =
                new JPanel();

        dosagePanel.setLayout(
                new BoxLayout(
                        dosagePanel,
                        BoxLayout.Y_AXIS
                )
        );

        dosagePanel.setBackground(
                new Color(
                        245,
                        248,
                        253
                )
        );

        dosagePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        dosagePanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel dosageTitle =
                new JLabel(
                        "Dosage"
                );

        dosageTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        dosageTitle.setForeground(
                new Color(
                        75,
                        95,
                        135
                ));


        JLabel dosageValue =
                new JLabel(
                        prescription.getDosage()
                );

        dosageValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        dosageValue.setForeground(
                TEXT_DARK
        );


        dosagePanel.add(
                dosageTitle
        );

        dosagePanel.add(
                Box.createVerticalStrut(3)
        );

        dosagePanel.add(
                dosageValue
        );


        detailsPanel.add(
                dosagePanel
        );


        // =====================================================
        // WHEN TO TAKE
        // =====================================================

        JPanel whenPanel =
                new JPanel();

        whenPanel.setLayout(
                new BoxLayout(
                        whenPanel,
                        BoxLayout.Y_AXIS
                )
        );

        whenPanel.setBackground(
                new Color(
                        245,
                        248,
                        253
                )
        );

        whenPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );


        JLabel whenTitle =
                new JLabel(
                        "When to Take"
                );

        whenTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        whenTitle.setForeground(
                new Color(
                        75,
                        95,
                        135
                ));


        JLabel whenValue =
                new JLabel(
                        "<html>" +
                                prescription.getWhenToTake() +
                                "</html>"
                );

        whenValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        whenValue.setForeground(
                TEXT_DARK
        );


        whenPanel.add(
                whenTitle
        );

        whenPanel.add(
                Box.createVerticalStrut(3)
        );

        whenPanel.add(
                whenValue
        );


        detailsPanel.add(
                whenPanel
        );


        content.add(
                detailsPanel
        );


        content.add(
                Box.createVerticalStrut(10)
        );


        // =====================================================
        // DESCRIPTION BOX
        // =====================================================

        JPanel descriptionPanel =
                new JPanel(
                        new BorderLayout()
                );

        descriptionPanel.setBackground(
                new Color(
                        240,
                        247,
                        255
                )
        );

        descriptionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        9,
                        12,
                        9,
                        12
                )
        );

        descriptionPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel descriptionTitle =
                new JLabel(
                        "Description"
                );

        descriptionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        descriptionTitle.setForeground(
                BLUE
        );


        JLabel description =
                new JLabel(
                        "<html>" +
                                prescription.getDescription() +
                                "</html>"
                );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        description.setForeground(
                new Color(
                        55,
                        75,
                        115
                ));


        JPanel descriptionText =
                new JPanel(
                        new BorderLayout()
                );

        descriptionText.setBackground(
                new Color(
                        240,
                        247,
                        255
                ));

        descriptionText.add(
                descriptionTitle,
                BorderLayout.NORTH
        );

        descriptionText.add(
                description,
                BorderLayout.CENTER
        );


        descriptionPanel.add(
                descriptionText,
                BorderLayout.CENTER
        );


        content.add(
                descriptionPanel
        );


        card.add(
                content,
                BorderLayout.CENTER
        );


        return card;
    }

    private JPanel createHistoryPage() {

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245, 247, 250));

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel("Appointment History");

        title.setFont(
                new Font("Calisto MT", Font.BOLD, 28)
        );

        title.setBounds(
                50,
                35,
                500,
                40
        );

        panel.add(title);


        JLabel subtitle =
                new JLabel(
                        "View and manage your hospital appointments."
                );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        subtitle.setForeground(
                new Color(100, 100, 100)
        );

        subtitle.setBounds(
                50,
                75,
                500,
                25
        );

        panel.add(subtitle);


        // =========================
        // FILTER CARD
        // =========================

        JPanel filterCard =
                new JPanel(null);

        filterCard.setBackground(Color.WHITE);

        filterCard.setBounds(
                50,
                120,
                865,
                75
        );

        filterCard.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(filterCard);


        JLabel filterTitle =
                new JLabel("Filter by Status");

        filterTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        filterTitle.setBounds(
                20,
                25,
                120,
                25
        );

        filterCard.add(filterTitle);


        // =========================
        // FILTER BUTTONS
        // =========================

        JButton allButton =
                new JButton("All");

        JButton pendingButton =
                new JButton("Pending");

        JButton activeButton =
                new JButton("Active");

        JButton completedButton =
                new JButton("Completed");

        JButton cancelledButton =
                new JButton("Cancelled");


        JButton[] filterButtons = {
                allButton,
                pendingButton,
                activeButton,
                completedButton,
                cancelledButton
        };


        int buttonX = 155;

        for (JButton button : filterButtons) {

            button.setBounds(
                    buttonX,
                    18,
                    105,
                    38
            );

            button.setFont(
                    new Font("Segoe UI", Font.BOLD, 13)
            );

            button.setFocusPainted(false);

            button.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(210, 215, 225)
                    )
            );

            button.setBackground(Color.WHITE);

            filterCard.add(button);

            buttonX += 115;
        }


        // =========================
        // APPOINTMENTS TITLE
        // =========================

        JLabel appointmentsTitle =
                new JLabel("Your Appointments");

        appointmentsTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        appointmentsTitle.setBounds(
                50,
                220,
                300,
                30
        );

        panel.add(appointmentsTitle);


        // =========================
        // APPOINTMENTS CONTAINER
        // =========================

        JPanel appointmentsList =
                new JPanel();

        appointmentsList.setLayout(
                new BoxLayout(
                        appointmentsList,
                        BoxLayout.Y_AXIS
                )
        );

        appointmentsList.setBackground(
                Color.WHITE
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentsList
                );

        scrollPane.setBounds(
                50,
                265,
                865,
                350
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        panel.add(scrollPane);


        // =========================
        // FILTER LOGIC
        // =========================

        allButton.addActionListener(e -> {

            loadHistoryAppointments(
                    appointmentsList,
                    "ALL"
            );

            setHistoryFilterButton(
                    filterButtons,
                    allButton
            );
        });


        pendingButton.addActionListener(e -> {

            loadHistoryAppointments(
                    appointmentsList,
                    "PENDING"
            );

            setHistoryFilterButton(
                    filterButtons,
                    pendingButton
            );
        });


        activeButton.addActionListener(e -> {

            loadHistoryAppointments(
                    appointmentsList,
                    "ACTIVE"
            );

            setHistoryFilterButton(
                    filterButtons,
                    activeButton
            );
        });


        completedButton.addActionListener(e -> {

            loadHistoryAppointments(
                    appointmentsList,
                    "COMPLETED"
            );

            setHistoryFilterButton(
                    filterButtons,
                    completedButton
            );
        });


        cancelledButton.addActionListener(e -> {

            loadHistoryAppointments(
                    appointmentsList,
                    "CANCELLED"
            );

            setHistoryFilterButton(
                    filterButtons,
                    cancelledButton
            );
        });


        // =========================
        // DEFAULT FILTER
        // =========================

        setHistoryFilterButton(
                filterButtons,
                allButton
        );

        loadHistoryAppointments(
                appointmentsList,
                "ALL"
        );


        return panel;
    }

    private void loadHistoryAppointments(
            JPanel appointmentsList,
            String filter
    ) {

        appointmentsList.removeAll();

        Patient patient =
                DataStore.currentPatient;

        if (patient == null) {
            return;
        }


        List<Appointment> appointments =
                AppointmentDAO.getAppointments(
                        patient.getPatientId(),
                        filter
                );


        // =========================
        // EMPTY STATE
        // =========================

        if (appointments.isEmpty()) {

            JPanel emptyPanel =
                    new JPanel();

            emptyPanel.setLayout(
                    new BoxLayout(
                            emptyPanel,
                            BoxLayout.Y_AXIS
                    )
            );

            emptyPanel.setBackground(
                    Color.WHITE
            );

            emptyPanel.setBorder(
                    new EmptyBorder(
                            100,
                            20,
                            20,
                            20
                    )
            );


            JLabel emptyTitle =
                    new JLabel(
                            "No appointment history available"
                    );

            emptyTitle.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            emptyTitle.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );


            JLabel emptySubtitle =
                    new JLabel(
                            "Your appointments will appear here."
                    );

            emptySubtitle.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            emptySubtitle.setForeground(
                    new Color(120, 120, 120)
            );

            emptySubtitle.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );


            emptyPanel.add(emptyTitle);

            emptyPanel.add(
                    Box.createVerticalStrut(8)
            );

            emptyPanel.add(emptySubtitle);


            appointmentsList.add(emptyPanel);

        } else {

            // =========================
            // APPOINTMENT CARDS
            // =========================

            for (Appointment appointment :
                    appointments) {

                JPanel card =
                        createHistoryAppointmentCard(
                                appointment,
                                appointmentsList,
                                filter
                        );

                appointmentsList.add(card);

                appointmentsList.add(
                        Box.createVerticalStrut(12)
                );
            }
        }


        appointmentsList.revalidate();

        appointmentsList.repaint();
    }

    private JPanel createHistoryAppointmentCard(
            Appointment appointment,
            JPanel appointmentsList,
            String filter
    ) {

        JPanel card =
                new JPanel(null);

        card.setBackground(
                new Color(250, 251, 253)
        );

        card.setPreferredSize(
                new Dimension(820, 155)
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        155
                )
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );


        // =========================
        // DATE
        // =========================

        JPanel datePanel =
                new JPanel(null);

        datePanel.setBackground(
                new Color(240, 243, 248)
        );

        datePanel.setBounds(
                15,
                15,
                90,
                120
        );

        card.add(datePanel);


        LocalDate date =
                appointment.getAppointmentDate();


        JLabel day =
                new JLabel(
                        String.valueOf(
                                date.getDayOfMonth()
                        ),
                        SwingConstants.CENTER
                );

        day.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        day.setBounds(
                0,
                15,
                90,
                35
        );

        datePanel.add(day);


        JLabel month =
                new JLabel(
                        date.getMonth()
                                .toString()
                                .substring(0, 3),
                        SwingConstants.CENTER
                );

        month.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        month.setBounds(
                0,
                52,
                90,
                20
        );

        datePanel.add(month);


        JLabel year =
                new JLabel(
                        String.valueOf(
                                date.getYear()
                        ),
                        SwingConstants.CENTER
                );

        year.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        year.setBounds(
                0,
                72,
                90,
                20
        );

        datePanel.add(year);


        JLabel weekday =
                new JLabel(
                        date.getDayOfWeek()
                                .toString()
                                .substring(0, 3),
                        SwingConstants.CENTER
                );

        weekday.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        weekday.setForeground(
                new Color(100, 100, 100)
        );

        weekday.setBounds(
                0,
                92,
                90,
                20
        );

        datePanel.add(weekday);


        // =========================
        // DOCTOR
        // =========================

        String doctorName =
                appointment.getDoctorName();


        if (
                doctorName == null
                        ||
                        doctorName.trim().isEmpty()
        ) {

            doctorName =
                    "Doctor assignment pending";
        }


        JLabel doctor =
                new JLabel(
                        doctorName
                );

        doctor.setFont(
                new Font(
                        "Calisto MT",
                        Font.BOLD,
                        19
                )
        );

        doctor.setBounds(
                125,
                15,
                300,
                30
        );

        card.add(doctor);


        // =========================
        // STATUS
        // =========================

        String displayStatus =
                appointment.getStatus();


        if (
                "APPROVED".equals(
                        appointment.getStatus()
                )
        ) {

            displayStatus = "ACTIVE";
        }


        JLabel status =
                new JLabel(
                        "STATUS : "
                                + displayStatus
                );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        status.setBounds(
                430,
                18,
                160,
                25
        );

        card.add(status);


        // =========================
        // DEPARTMENT
        // =========================

        JLabel department =
                new JLabel(
                        "Department: "
                                + appointment.getDepartment()
                );

        department.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        department.setBounds(
                125,
                52,
                300,
                25
        );

        card.add(department);


        // =========================
        // TIME
        // =========================

        String time =
                appointment.getPreferredTime();


        if (
                appointment.getConfirmedTime()
                        != null
                        &&
                        !appointment.getConfirmedTime()
                                .trim()
                                .isEmpty()
        ) {

            time =
                    appointment.getConfirmedTime();
        }


        JLabel timeLabel =
                new JLabel(
                        "Time: " + time
                );

        timeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        timeLabel.setBounds(
                125,
                82,
                220,
                25
        );

        card.add(timeLabel);


        // =========================
        // TYPE
        // =========================

        JLabel type =
                new JLabel(
                        "Type: "
                                + appointment.getAppointmentType()
                );

        type.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        type.setBounds(
                350,
                82,
                180,
                25
        );

        card.add(type);


        // =========================
        // PRIORITY
        // =========================

        JLabel priority =
                new JLabel(
                        "Priority: "
                                + appointment.getPriority()
                );

        priority.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        priority.setBounds(
                530,
                82,
                150,
                25
        );

        card.add(priority);


        // =========================
        // ROOM
        // =========================

        if (
                appointment.getRoomNumber()
                        != null
                        &&
                        !appointment.getRoomNumber()
                                .trim()
                                .isEmpty()
        ) {

            JLabel room =
                    new JLabel(
                            "Room: "
                                    + appointment.getRoomNumber()
                    );

            room.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            room.setBounds(
                    530,
                    52,
                    150,
                    25
            );

            card.add(room);
        }


        // =========================
        // REASON
        // =========================

        JLabel reason =
                new JLabel(
                        "Reason: "
                                + appointment.getReason()
                );

        reason.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        card.add(reason);


        // =========================
        // CANCEL BUTTON
        // =========================

        if (
                "PENDING".equals(
                        appointment.getStatus()
                )
        ) {

            JButton cancelButton =
                    new JButton(
                            "CANCEL APPOINTMENT"
                    );

            cancelButton.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            11
                    )
            );

            cancelButton.setFocusPainted(false);

            cancelButton.setBounds(
                    575,
                    110,
                    190,
                    30
            );

            card.add(cancelButton);


            cancelButton.addActionListener(e -> {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to cancel this appointment?",
                                "Cancel Appointment",
                                JOptionPane.YES_NO_OPTION
                        );


                if (
                        choice
                                == JOptionPane.YES_OPTION
                ) {

                    boolean deleted =
                            AppointmentDAO
                                    .cancelPendingAppointment(
                                            appointment.getAppointmentId(),
                                            DataStore.currentPatient
                                                    .getPatientId()
                                    );


                    if (deleted) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Appointment cancelled successfully."
                        );


                        loadHistoryAppointments(
                                appointmentsList,
                                filter
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Unable to cancel appointment.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });
        }


        return card;
    }

    private void setHistoryFilterButton(
            JButton[] buttons,
            JButton selectedButton
    ) {

        for (JButton button : buttons) {

            button.setBackground(
                    Color.WHITE
            );

            button.setForeground(
                    new Color(50, 50, 50)
            );
        }


        selectedButton.setBackground(
                new Color(45, 115, 210)
        );

        selectedButton.setForeground(
                Color.WHITE
        );
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