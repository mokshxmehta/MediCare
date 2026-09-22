import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    private JButton dashboardButton;
    private JButton appointmentsButton;
    private JButton doctorsButton;
    private JButton logoutButton;

    public AdminDashboard() {

        setTitle("MediCare+ - Admin Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(20, 32, 87));
        sidebar.setPreferredSize(new Dimension(210, 700));
        sidebar.setLayout(null);


        // ---------- LOGO ----------

        JLabel logo = new JLabel("MediCare+");

        logo.setForeground(Color.WHITE);
        logo.setFont(
                new Font(
                        "Inter",
                        Font.BOLD,
                        28
                )
        );

        logo.setBounds(
                35,
                35,
                160,
                45
        );

        sidebar.add(logo);


        JLabel adminLabel =
                new JLabel("ADMINISTRATION");

        adminLabel.setForeground(
                new Color(180, 200, 230)
        );

        adminLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        adminLabel.setBounds(
                35,
                80,
                150,
                25
        );

        sidebar.add(adminLabel);


        // ================= SIDEBAR BUTTONS =================

        dashboardButton =
                createSidebarButton(
                        "Dashboard",
                        "assets/dashboard.png",
                        130
                );

        appointmentsButton =
                createSidebarButton(
                        "Appointments",
                        "assets/appointment-admin.png",
                        190
                );

        doctorsButton =
                createSidebarButton(
                        "Doctors",
                        "assets/doctor.png",
                        250
                );


        sidebar.add(dashboardButton);
        sidebar.add(appointmentsButton);
        sidebar.add(doctorsButton);


        // ================= LOGOUT =================

        logoutButton =
                createSidebarButton(
                        "Logout",
                        "assets/logout.png",
                        570
                );

        sidebar.add(logoutButton);


        // ================= CONTENT AREA =================

        cardLayout = new CardLayout();

        contentPanel =
                new JPanel(cardLayout);

        contentPanel.setBackground(
                new Color(248, 249, 253)
        );


        contentPanel.add(
                createDashboardPage(),
                "DASHBOARD"
        );

        contentPanel.add(
                createAppointmentsPage(),
                "APPOINTMENTS"
        );


        contentPanel.add(
                createDoctorsPage(),
                "DOCTORS"
        );


        // ================= ADD TO FRAME =================

        add(sidebar, BorderLayout.WEST);

        add(
                contentPanel,
                BorderLayout.CENTER
        );


        // ================= BUTTON ACTIONS =================

        dashboardButton.addActionListener(e -> {

            contentPanel.remove(
                    contentPanel.getComponent(0)
            );

            contentPanel.add(
                    createDashboardPage(),
                    "DASHBOARD"
            );

            contentPanel.revalidate();
            contentPanel.repaint();

            cardLayout.show(
                    contentPanel,
                    "DASHBOARD"
            );
        });

        appointmentsButton.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "APPOINTMENTS"
                )
        );


        doctorsButton.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "DOCTORS"
                )
        );


        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                new WelcomePage();
                dispose();
            }

        });


        cardLayout.show(
                contentPanel,
                "DASHBOARD"
        );


        setResizable(false);
        setVisible(true);
    }


    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private JButton createSidebarButton(
            String text,
            String iconPath,
            int y
    ) {

        JButton button =
                new JButton(text);

        ImageIcon icon =
                new ImageIcon(iconPath);

        Image image =
                icon.getImage().getScaledInstance(
                        20,
                        20,
                        Image.SCALE_SMOOTH
                );

        button.setIcon(
                new ImageIcon(image)
        );

        button.setBounds(
                15,
                y,
                180,
                45
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setIconTextGap(12);

        button.setBorder(
                new EmptyBorder(
                        5,
                        15,
                        5,
                        5
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(20, 32, 87)
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }


    // =====================================================
    // DASHBOARD HOME
    // =====================================================

    private JPanel createDashboardPage() {

        JPanel panel = new JPanel();

        panel.setBackground(
                new Color(248, 249, 253)
        );

        panel.setLayout(null);


        // ================= HEADER =================

        JLabel title =
                new JLabel("Dashboard");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                new Color(30, 50, 90)
        );

        title.setBounds(
                35,
                20,
                300,
                45
        );

        panel.add(title);


        JLabel subtitle =
                new JLabel(
                        "Hospital Administration Overview"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(Color.GRAY);

        subtitle.setBounds(
                37,
                58,
                350,
                25
        );

        panel.add(subtitle);


        // ================= ADMIN IMAGE =================

        JPanel imagePanel =
                new JPanel();

        imagePanel.setLayout(null);

        imagePanel.setBackground(Color.WHITE);

        imagePanel.setBounds(
                30,
                95,
                930,
                140
        );

        imagePanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(imagePanel);


        ImageIcon adminIcon =
                new ImageIcon(
                        "assets/admin-dashboard.png"
                );

        Image adminImage =
                adminIcon.getImage().getScaledInstance(
                        930,
                        140,
                        Image.SCALE_SMOOTH
                );

        JLabel adminImageLabel =
                new JLabel(
                        new ImageIcon(adminImage)
                );

        adminImageLabel.setBounds(
                0,
                0,
                930,
                140
        );

        imagePanel.add(adminImageLabel);


        // ================= RECENT APPOINTMENTS =================

        JPanel recentPanel =
                new JPanel();

        recentPanel.setLayout(null);

        recentPanel.setBackground(Color.WHITE);

        recentPanel.setBounds(
                35,
                255,
                455,
                220
        );

        recentPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(recentPanel);


        JLabel recentTitle =
                new JLabel(
                        "Recent Appointments"
                );

        recentTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        recentTitle.setForeground(
                new Color(30, 70, 150)
        );

        recentTitle.setBounds(
                20,
                15,
                250,
                30
        );

        recentPanel.add(recentTitle);


        JLabel viewAll =
                new JLabel(
                        "<html><u>View All →</u></html>"
                );

        viewAll.setForeground(
                new Color(37, 99, 235)
        );

        viewAll.setBounds(
                355,
                17,
                80,
                25
        );

        recentPanel.add(viewAll);


        List<Appointment> recentAppointments =
                AdminDAO.getRecentAppointments();

        int y = 55;
        int displayedCount = 0;

        for (Appointment appointment : recentAppointments) {

            // Do not show cancelled appointments
            if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
                continue;
            }

            // ================= PATIENT NAME =================

            JLabel patientLabel =
                    new JLabel(
                            appointment.getPatientName()
                    );

            patientLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            12
                    )
            );

            patientLabel.setBounds(
                    20,
                    y,
                    125,
                    25
            );

            recentPanel.add(patientLabel);


            // ================= DEPARTMENT =================

            JLabel departmentLabel =
                    new JLabel(
                            appointment.getDepartment()
                    );

            departmentLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            departmentLabel.setBounds(
                    145,
                    y,
                    105,
                    25
            );

            recentPanel.add(departmentLabel);


            // ================= STATUS =================

            String status =
                    appointment.getStatus();

            if (status.equalsIgnoreCase("APPROVED")) {
                status = "ACTIVE";
            }

            JLabel statusLabel =
                    new JLabel(status);

            statusLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            statusLabel.setBounds(
                    250,
                    y,
                    80,
                    25
            );

            recentPanel.add(statusLabel);


            // ================= TIME =================

            JLabel timeLabel =
                    new JLabel(
                            appointment.getPreferredTime()
                    );

            timeLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            timeLabel.setBounds(
                    330,
                    y,
                    100,
                    25
            );

            recentPanel.add(timeLabel);


            y += 30;
            displayedCount++;

            // Maximum 5 visible appointments
            if (displayedCount >= 5) {
                break;
            }
        }


// If there are no non-cancelled appointments
        if (displayedCount == 0) {

            JLabel recentInfo =
                    new JLabel(
                            "No active appointments found."
                    );

            recentInfo.setForeground(Color.GRAY);

            recentInfo.setBounds(
                    20,
                    85,
                    400,
                    30
            );

            recentPanel.add(recentInfo);
        }


        viewAll.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        cardLayout.show(
                                contentPanel,
                                "APPOINTMENTS"
                        );
                    }
                }
        );


        // ================= NEXT APPOINTMENT =================

        JPanel nextPanel =
                new JPanel();

        nextPanel.setLayout(null);

        nextPanel.setBackground(
                new Color(243, 247, 255)
        );

        nextPanel.setBounds(
                505,
                255,
                455,
                220
        );
        nextPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(nextPanel);


        JLabel nextTitle =
                new JLabel(
                        "Next Appointment"
                );

        nextTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        nextTitle.setForeground(
                new Color(30, 70, 150)
        );

        nextTitle.setBounds(
                20,
                15,
                250,
                30
        );

        nextPanel.add(nextTitle);


        Appointment nextAppointment =
                AdminDAO.getNextAppointment();

        JLabel nextInfo;

        if (nextAppointment == null) {

            nextInfo =
                    new JLabel(
                            "<html>" +
                                    "No upcoming appointment information." +
                                    "</html>"
                    );

        } else {

            nextInfo =
                    new JLabel(
                            "<html>" +
                                    "<b>Patient:</b> " +
                                    nextAppointment.getPatientName() +
                                    "<br><br>" +

                                    "<b>Department:</b> " +
                                    nextAppointment.getDepartment() +
                                    "<br>" +

                                    "<b>Date:</b> " +
                                    nextAppointment.getAppointmentDate() +
                                    "<br>" +

                                    "<b>Preferred Time:</b> " +
                                    nextAppointment.getPreferredTime() +
                                    "<br>" +

                                    "<b>Doctor:</b> " +
                                    (nextAppointment.getDoctorName() == null
                                            ? "Not assigned"
                                            : nextAppointment.getDoctorName()) +
                                    "</html>"
                    );
        }

        nextInfo.setForeground(Color.GRAY);

        nextInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        nextInfo.setBounds(
                20,
                60,
                410,
                130
        );

        nextPanel.add(nextInfo);


        // ================= STAT CARDS =================

        int totalPatientsCount =
                AdminDAO.getTotalPatients();

        int todayAppointmentsCount =
                AdminDAO.getTodayAppointments();

        int pendingAppointmentsCount =
                AdminDAO.getPendingAppointments();


        JPanel totalPatients =
                createStatCard(
                        "TOTAL PATIENTS",
                        String.valueOf(totalPatientsCount),
                        "Registered Patients",
                        30,
                        490
                );

        JPanel todayAppointments =
                createStatCard(
                        "TODAY'S APPOINTMENTS",
                        String.valueOf(todayAppointmentsCount),
                        "Today's appointments",
                        345,
                        490
                );

        JPanel pendingAppointments =
                createStatCard(
                        "PENDING APPOINTMENTS",
                        String.valueOf(pendingAppointmentsCount),
                        "Awaiting approval",
                        660,
                        490
                );

        panel.add(totalPatients);
        panel.add(todayAppointments);
        panel.add(pendingAppointments);


        return panel;
    }


    // =====================================================
    // STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String title,
            String value,
            String subtitle,
            int x,
            int y
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(null);

        card.setBackground(
                new Color(235, 240, 255)
        );

        card.setBounds(
                x,
                y,
                300,
                85
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 225, 240)
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        titleLabel.setForeground(
                new Color(80, 80, 80)
        );

        titleLabel.setBounds(
                18,
                10,
                300,
                20
        );

        card.add(titleLabel);


        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        valueLabel.setForeground(
                new Color(30, 70, 150)
        );

        valueLabel.setBounds(
                18,
                32,
                80,
                35
        );

        card.add(valueLabel);


        JLabel subtitleLabel =
                new JLabel(subtitle);

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        subtitleLabel.setForeground(Color.GRAY);

        subtitleLabel.setBounds(
                100,
                39,
                180,
                25
        );

        card.add(subtitleLabel);


        return card;
    }


// =====================================================
// APPOINTMENTS PAGE
// =====================================================

    private JPanel createAppointmentsPage() {

        JPanel panel = new JPanel();

        panel.setBackground(
                new Color(248, 249, 253)
        );

        panel.setLayout(null);


        // =================================================
        // HEADER
        // =================================================

        JLabel title =
                new JLabel("Appointments");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                new Color(30, 50, 90)
        );

        title.setBounds(
                35,
                15,
                350,
                40
        );

        panel.add(title);


        JLabel subtitle =
                new JLabel(
                        "Manage and review hospital appointments"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(Color.GRAY);

        subtitle.setBounds(
                37,
                52,
                400,
                25
        );

        panel.add(subtitle);


        // =================================================
        // FILTER PANEL
        // =================================================

        JPanel filterPanel =
                new JPanel();

        filterPanel.setLayout(null);

        filterPanel.setBackground(Color.WHITE);

        filterPanel.setBounds(
                35,
                85,
                930,
                70
        );

        filterPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(filterPanel);


        // SEARCH

        JLabel searchLabel =
                new JLabel("Search Patient");

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        searchLabel.setBounds(
                20,
                7,
                120,
                18
        );

        filterPanel.add(searchLabel);


        JTextField searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 215, 225)
                        ),
                        new EmptyBorder(
                                4,
                                8,
                                4,
                                8
                        )
                )
        );

        searchField.setBounds(
                20,
                27,
                230,
                30
        );

        filterPanel.add(searchField);


        // STATUS

        JLabel statusLabel =
                new JLabel("Status");

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        statusLabel.setBounds(
                275,
                7,
                100,
                18
        );

        filterPanel.add(statusLabel);


        JComboBox<String> statusCombo =
                new JComboBox<>(
                        new String[]{
                                "All",
                                "Pending",
                                "Active",
                                "Completed",
                                "Rejected",
                                "Cancelled"
                        }
                );

        statusCombo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        statusCombo.setBounds(
                275,
                27,
                130,
                30
        );

        filterPanel.add(statusCombo);


        // DEPARTMENT

        JLabel departmentLabel =
                new JLabel("Department");

        departmentLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        departmentLabel.setBounds(
                430,
                7,
                100,
                18
        );

        filterPanel.add(departmentLabel);


        JComboBox<String> departmentCombo =
                new JComboBox<>(
                        new String[]{
                                "All",
                                "Cardiology",
                                "Dermatology",
                                "Orthopedics",
                                "General Medicine",
                                "Pediatrics"
                        }
                );

        departmentCombo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        departmentCombo.setBounds(
                430,
                27,
                160,
                30
        );

        filterPanel.add(departmentCombo);


        // REFRESH

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        refreshButton.setForeground(Color.WHITE);

        refreshButton.setBackground(
                new Color(30, 70, 150)
        );

        refreshButton.setFocusPainted(false);

        refreshButton.setBorderPainted(false);

        refreshButton.setBounds(
                620,
                27,
                110,
                30
        );

        filterPanel.add(refreshButton);


        // =================================================
        // APPOINTMENT TABLE
        // =================================================

        String[] columns = {
                "ID",
                "Patient",
                "Department",
                "Type",
                "Date",
                "Preferred Time",
                "Priority",
                "Status"
        };

        javax.swing.table.DefaultTableModel tableModel =
                new javax.swing.table.DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };


        JTable appointmentTable =
                new JTable(tableModel);

        // Hide Appointment ID column
        appointmentTable.getColumnModel()
                .getColumn(0)
                .setMinWidth(0);

        appointmentTable.getColumnModel()
                .getColumn(0)
                .setMaxWidth(0);

        appointmentTable.getColumnModel()
                .getColumn(0)
                .setWidth(0);

        // =====================================================
        // LOAD APPOINTMENTS FROM DATABASE
        // =====================================================

        List<Appointment> databaseAppointments =
                AdminDAO.getAllAppointments();

        for (Appointment appointment :
                databaseAppointments) {

            tableModel.addRow(
                    new Object[]{
                            appointment.getAppointmentId(),
                            appointment.getPatientName(),
                            appointment.getDepartment(),
                            appointment.getAppointmentType(),
                            appointment.getAppointmentDate(),
                            appointment.getPreferredTime(),
                            appointment.getPriority(),
                            appointment.getStatus()
                    }
            );
        }

        appointmentTable.setRowHeight(34);

        appointmentTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        appointmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        appointmentTable.setGridColor(
                new Color(225, 228, 235)
        );

        appointmentTable.setShowVerticalLines(false);

        appointmentTable.setSelectionBackground(
                new Color(205, 220, 240)
        );

        appointmentTable.setSelectionForeground(
                new Color(30, 50, 90)
        );


        appointmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        appointmentTable.getTableHeader()
                .setBackground(
                        new Color(235, 240, 255)
                );

        appointmentTable.getTableHeader()
                .setForeground(
                        new Color(30, 50, 90)
                );

        appointmentTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        scrollPane.setBounds(
                35,
                165,
                930,
                235
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(scrollPane);


        // =================================================
        // APPOINTMENT DETAILS PANEL
        // =================================================

        JPanel detailsPanel =
                new JPanel();

        detailsPanel.setLayout(null);

        detailsPanel.setBackground(Color.WHITE);

        detailsPanel.setBounds(
                35,
                415,
                930,
                220
        );

        detailsPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
                )
        );

        panel.add(detailsPanel);


        JLabel detailsTitle =
                new JLabel(
                        "Appointment Details"
                );

        detailsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        detailsTitle.setForeground(
                new Color(30, 70, 150)
        );

        detailsTitle.setBounds(
                18,
                10,
                250,
                25
        );

        detailsPanel.add(detailsTitle);


        JLabel selectMessage =
                new JLabel(
                        "Select an appointment from the table to view details."
                );

        selectMessage.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        selectMessage.setForeground(Color.GRAY);

        selectMessage.setBounds(
                18,
                42,
                400,
                25
        );

        detailsPanel.add(selectMessage);


        // =================================================
        // PATIENT DETAILS
        // =================================================

        JLabel patientLabel =
                new JLabel("Patient:");

        patientLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        patientLabel.setBounds(
                20,
                65,
                60,
                20
        );

        detailsPanel.add(patientLabel);


        JLabel patientValue =
                new JLabel("-");

        patientValue.setBounds(
                80,
                65,
                180,
                20
        );

        detailsPanel.add(patientValue);


        JLabel departmentDetailLabel =
                new JLabel("Department:");

        departmentDetailLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        departmentDetailLabel.setBounds(
                280,
                65,
                80,
                20
        );

        detailsPanel.add(departmentDetailLabel);


        JLabel departmentValue =
                new JLabel("-");

        departmentValue.setBounds(
                365,
                65,
                130,
                20
        );

        detailsPanel.add(departmentValue);


        JLabel typeLabel =
                new JLabel("Type:");

        typeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        typeLabel.setBounds(
                515,
                65,
                45,
                20
        );

        detailsPanel.add(typeLabel);


        JLabel typeValue =
                new JLabel("-");

        typeValue.setBounds(
                565,
                65,
                120,
                20
        );

        detailsPanel.add(typeValue);


        JLabel priorityLabel =
                new JLabel("Priority:");

        priorityLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        priorityLabel.setBounds(
                700,
                65,
                55,
                20
        );

        detailsPanel.add(priorityLabel);


        JLabel priorityValue =
                new JLabel("-");

        priorityValue.setBounds(
                760,
                65,
                120,
                20
        );

        detailsPanel.add(priorityValue);


        // =================================================
        // APPOINTMENT INFORMATION
        // =================================================

        JLabel dateLabel =
                new JLabel("Date:");

        dateLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        dateLabel.setBounds(
                20,
                90,
                45,
                20
        );

        detailsPanel.add(dateLabel);


        JLabel dateValue =
                new JLabel("-");

        dateValue.setBounds(
                70,
                90,
                120,
                20
        );

        detailsPanel.add(dateValue);


        JLabel timeLabel =
                new JLabel("Time:");

        timeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        timeLabel.setBounds(
                210,
                90,
                45,
                20
        );

        detailsPanel.add(timeLabel);


        JLabel timeValue =
                new JLabel("-");

        timeValue.setBounds(
                260,
                90,
                120,
                20
        );

        detailsPanel.add(timeValue);


        JLabel statusDetailLabel =
                new JLabel("Status:");

        statusDetailLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        statusDetailLabel.setBounds(
                400,
                90,
                50,
                20
        );

        detailsPanel.add(statusDetailLabel);


        JLabel statusValue =
                new JLabel("-");

        statusValue.setBounds(
                455,
                90,
                120,
                20
        );

        detailsPanel.add(statusValue);


        // =================================================
        // DOCTOR ASSIGNMENT
        // =================================================

        JLabel doctorLabel =
                new JLabel("Doctor:");

        doctorLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        doctorLabel.setBounds(
                20,
                120,
                55,
                20
        );

        detailsPanel.add(doctorLabel);


        JComboBox<String> doctorCombo =
                new JComboBox<>(
                        new String[]{
                                "Select Doctor",
                                "Dr. Amit Shah",
                                "Dr. Priya Mehta",
                                "Dr. Rahul Patel",
                                "Dr. Neha Sharma"
                        }
                );

        doctorCombo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        doctorCombo.setBounds(
                80,
                118,
                180,
                28
        );

        detailsPanel.add(doctorCombo);


        // =================================================
        // CONFIRMED TIME
        // =================================================

        JLabel confirmedTimeLabel =
                new JLabel("Confirmed Time:");

        confirmedTimeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        confirmedTimeLabel.setBounds(
                395,
                120,
                100,
                20
        );

        detailsPanel.add(confirmedTimeLabel);


        JTextField confirmedTimeField =
                new JTextField();

        confirmedTimeField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        confirmedTimeField.setToolTipText(
                "Example: 10:30 AM"
        );

        confirmedTimeField.setBounds(
                500,
                118,
                120,
                28
        );

        detailsPanel.add(
                confirmedTimeField
        );


        // =================================================
        // ADMIN ACTION BUTTONS
        // =================================================

        JButton approveButton =
                new JButton("✅ Approve");

        approveButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        approveButton.setForeground(Color.WHITE);

        approveButton.setBackground(
                new Color(35, 130, 80)
        );

        approveButton.setFocusPainted(false);

        approveButton.setBorderPainted(false);

        approveButton.setBounds(
                20,
                165,
                115,
                32
        );

        detailsPanel.add(approveButton);


        JButton rejectButton =
                new JButton("❌ Reject");

        rejectButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        rejectButton.setForeground(Color.WHITE);

        rejectButton.setBackground(
                new Color(190, 65, 65)
        );

        rejectButton.setFocusPainted(false);

        rejectButton.setBorderPainted(false);

        rejectButton.setBounds(
                145,
                165,
                105,
                32
        );

        detailsPanel.add(rejectButton);

        JButton clearButton =
                new JButton("Clear");

        clearButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        clearButton.setFocusPainted(false);

        clearButton.setBounds(
                550,
                165,
                80,
                32
        );

        detailsPanel.add(clearButton);


        // =================================================
        // SELECT APPOINTMENT
        // =================================================

        appointmentTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    int selectedRow =
                            appointmentTable.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    int modelRow =
                            appointmentTable.convertRowIndexToModel(
                                    selectedRow
                            );

                    patientValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 1
                            ).toString()
                    );

                    departmentValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 2
                            ).toString()
                    );

                    typeValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 3
                            ).toString()
                    );

                    dateValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 4
                            ).toString()
                    );

                    timeValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 5
                            ).toString()
                    );

                    priorityValue.setText(
                            tableModel.getValueAt(
                                    modelRow, 6
                            ).toString()
                    );

                    String selectedStatus =
                            tableModel.getValueAt(
                                    modelRow, 7
                            ).toString();

                    if (selectedStatus.equals("APPROVED")) {
                        statusValue.setText("ACTIVE");
                    } else {
                        statusValue.setText(selectedStatus);
                    }

                    int appointmentId =
                            Integer.parseInt(
                                    tableModel.getValueAt(modelRow, 0).toString()
                            );

                    Appointment selectedAppointment =
                            AdminDAO.getAppointmentById(appointmentId);

                    doctorCombo.setSelectedIndex(0);
                    confirmedTimeField.setText("");

                    if (selectedAppointment != null) {
                        String savedDoctor = selectedAppointment.getDoctorName();
                        String savedTime = selectedAppointment.getConfirmedTime();

                        if (savedDoctor != null && !savedDoctor.trim().isEmpty()) {
                            doctorCombo.setSelectedItem(savedDoctor);
                        }

                        if (savedTime != null) {
                            confirmedTimeField.setText(savedTime);
                        }
                    }

                    selectMessage.setText(
                            "Selected appointment"
                    );
                });


        // =================================================
        // APPROVE
        // =================================================

        approveButton.addActionListener(e -> {

            int selectedRow =
                    appointmentTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Please select an appointment first."
                );

                return;
            }


            int modelRow =
                    appointmentTable.convertRowIndexToModel(
                            selectedRow
                    );


            String currentStatus =
                    tableModel
                            .getValueAt(
                                    modelRow,
                                    7
                            )
                            .toString();


            if (!currentStatus.equals("PENDING")) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Only pending appointments can be approved."
                );

                return;
            }


            // ===============================
            // DOCTOR
            // ===============================

            String doctor =
                    doctorCombo
                            .getSelectedItem()
                            .toString();

            if (doctor.equals("Select Doctor")) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Please select a doctor."
                );

                return;
            }


            // ===============================
            // CONFIRMED TIME
            // ===============================

            String confirmedTime =
                    confirmedTimeField
                            .getText()
                            .trim();

            if (confirmedTime.isEmpty()) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Please enter the confirmed time."
                );

                return;
            }


            // ===============================
            // APPOINTMENT ID
            // ===============================

            int appointmentId =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            0
                                    )
                                    .toString()
                    );


            // ===============================
            // SAVE DOCTOR + TIME
            // ===============================

            boolean assignmentUpdated =
                    AdminDAO.updateAppointmentAssignment(
                            appointmentId,
                            doctor,
                            confirmedTime
                    );


            if (!assignmentUpdated) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Could not save doctor and confirmed time."
                );

                return;
            }


            boolean statusUpdated =
                    AdminDAO.updateAppointmentStatus(
                            appointmentId,
                            "APPROVED"
                    );


            if (!statusUpdated) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Doctor and time were saved, but status could not be updated."
                );

                return;
            }


            // ===============================
            // UPDATE UI
            // ===============================

            tableModel.setValueAt(
                    "APPROVED",
                    modelRow,
                    7
            );

            statusValue.setText("ACTIVE");


            JOptionPane.showMessageDialog(
                    panel,
                    "Appointment approved successfully.\n\n" +
                            "Doctor: " + doctor +
                            "\nConfirmed Time: " +
                            confirmedTime
            );
        });


        // =================================================
        // REJECT
        // =================================================

        rejectButton.addActionListener(e -> {

            int selectedRow =
                    appointmentTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Please select an appointment first."
                );

                return;
            }

            int modelRow =
                    appointmentTable.convertRowIndexToModel(
                            selectedRow
                    );

            String currentStatus =
                    tableModel
                            .getValueAt(
                                    modelRow,
                                    7
                            )
                            .toString();


            if (!currentStatus.equals("PENDING")) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Only pending appointments can be rejected."
                );

                return;
            }


            int appointmentId =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            0
                                    )
                                    .toString()
                    );


            boolean updated =
                    AdminDAO.updateAppointmentStatus(
                            appointmentId,
                            "REJECTED"
                    );


            if (updated) {

                tableModel.setValueAt(
                        "REJECTED",
                        modelRow,
                        7
                );

                statusValue.setText("REJECTED");

                JOptionPane.showMessageDialog(
                        panel,
                        "Appointment rejected successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        panel,
                        "Could not reject appointment."
                );
            }
        });


        // =================================================
        // CLEAR SELECTION
        // =================================================

        clearButton.addActionListener(e -> {

            appointmentTable.clearSelection();

            patientValue.setText("-");

            departmentValue.setText("-");

            typeValue.setText("-");

            dateValue.setText("-");

            timeValue.setText("-");

            priorityValue.setText("-");

            statusValue.setText("-");

            doctorCombo.setSelectedIndex(0);
            confirmedTimeField.setText("");

            selectMessage.setText(
                    "Select an appointment from the table to view details."
            );
        });


        // =================================================
        // SEARCH / FILTER
        // =================================================

        searchField.getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            private void filter() {

                                applyTableFilter(
                                        appointmentTable,
                                        searchField.getText(),
                                        statusCombo
                                                .getSelectedItem()
                                                .toString(),
                                        departmentCombo
                                                .getSelectedItem()
                                                .toString()
                                );
                            }

                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }

                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }

                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }
                        }
                );


        statusCombo.addActionListener(e ->

                applyTableFilter(
                        appointmentTable,
                        searchField.getText(),
                        statusCombo
                                .getSelectedItem()
                                .toString(),
                        departmentCombo
                                .getSelectedItem()
                                .toString()
                )
        );


        departmentCombo.addActionListener(e ->

                applyTableFilter(
                        appointmentTable,
                        searchField.getText(),
                        statusCombo
                                .getSelectedItem()
                                .toString(),
                        departmentCombo
                                .getSelectedItem()
                                .toString()
                )
        );


        refreshButton.addActionListener(e -> {

            searchField.setText("");
            statusCombo.setSelectedIndex(0);
            departmentCombo.setSelectedIndex(0);

            tableModel.setRowCount(0);

            List<Appointment> refreshedAppointments =
                    AdminDAO.getAllAppointments();

            for (Appointment appointment : refreshedAppointments) {
                tableModel.addRow(new Object[]{
                        appointment.getAppointmentId(),
                        appointment.getPatientName(),
                        appointment.getDepartment(),
                        appointment.getAppointmentType(),
                        appointment.getAppointmentDate(),
                        appointment.getPreferredTime(),
                        appointment.getPriority(),
                        appointment.getStatus()
                });
            }

            appointmentTable.clearSelection();

            patientValue.setText("-");
            departmentValue.setText("-");
            typeValue.setText("-");
            dateValue.setText("-");
            timeValue.setText("-");
            priorityValue.setText("-");
            statusValue.setText("-");
            doctorCombo.setSelectedIndex(0);
            confirmedTimeField.setText("");

            selectMessage.setText(
                    "Select an appointment from the table to view details."
            );
        });


        return panel;
    }


    // =====================================================
    // TABLE FILTER
    // =====================================================

    private void applyTableFilter(
            JTable table,
            String search,
            String status,
            String department
    ) {

        javax.swing.table.TableRowSorter<
                javax.swing.table.TableModel
                > sorter =
                new javax.swing.table.TableRowSorter<>(
                        table.getModel()
                );

        table.setRowSorter(sorter);

        java.util.List<RowFilter<Object, Object>> filters =
                new java.util.ArrayList<>();


        // SEARCH PATIENT
        if (!search.trim().isEmpty()) {

            filters.add(
                    RowFilter.regexFilter(
                            "(?i)" +
                                    java.util.regex.Pattern.quote(
                                            search.trim()
                                    ),
                            1
                    )
            );
        }


        // STATUS
        if (!status.equals("All")) {

            String databaseStatus =
                    status.equals("Active")
                            ? "APPROVED"
                            : status.toUpperCase();

            filters.add(
                    RowFilter.regexFilter(
                            "^" +
                                    databaseStatus +
                                    "$",
                            7
                    )
            );
        }


        // DEPARTMENT
        if (!department.equals("All")) {

            filters.add(
                    RowFilter.regexFilter(
                            "^" +
                                    java.util.regex.Pattern.quote(
                                            department
                                    ) +
                                    "$",
                            2
                    )
            );
        }


        if (filters.isEmpty()) {

            sorter.setRowFilter(null);

        } else {

            sorter.setRowFilter(
                    RowFilter.andFilter(filters)
            );
        }
    }


    // =====================================================
    // PLACEHOLDER PAGES
    // ==========================================================================================================

    private JPanel createPlaceholderPage(
            String pageName
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                new Color(248, 249, 253)
        );

        JLabel label =
                new JLabel(
                        pageName +
                                " Page - Coming Next",
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        label.setForeground(
                new Color(40, 70, 130)
        );

        panel.add(
                label,
                BorderLayout.CENTER
        );

        return panel;
    }
    // =====================================================
// DOCTORS PAGE
// =====================================================

    private JPanel createDoctorsPage() {

        // =================================================
        // OUTER PAGE + SCROLL
        // =================================================

        JPanel page =
                new JPanel(new BorderLayout());

        page.setBackground(
                new Color(248, 249, 253)
        );


        JPanel panel =
                new JPanel();

        panel.setBackground(
                new Color(248, 249, 253)
        );

        panel.setLayout(null);

        // Content height
        panel.setPreferredSize(
                new Dimension(
                        950,
                        760
                )
        );


        // =================================================
        // PAGE TITLE
        // =================================================

        JLabel title =
                new JLabel("Doctors");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(25, 40, 70)
        );

        title.setBounds(
                35,
                25,
                300,
                40
        );

        panel.add(title);


        JLabel subtitle =
                new JLabel(
                        "Manage consultations, prescriptions and patient appointments"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                new Color(100, 110, 125)
        );

        subtitle.setBounds(
                35,
                62,
                600,
                25
        );

        panel.add(subtitle);


        // =================================================
        // SEARCH
        // =================================================

        JPanel searchPanel =
                new JPanel();

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setLayout(null);

        searchPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 238)
                )
        );

        searchPanel.setBounds(
                35,
                105,
                890,
                55
        );

        panel.add(searchPanel);


        JLabel searchLabel =
                new JLabel("Search");

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        searchLabel.setForeground(
                new Color(80, 90, 105)
        );

        searchLabel.setBounds(
                15,
                10,
                55,
                30
        );

        searchPanel.add(searchLabel);


        JTextField searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        searchField.setBounds(
                75,
                10,
                400,
                32
        );

        searchField.setToolTipText(
                "Search patient name or patient ID"
        );

        searchPanel.add(searchField);


        JLabel statusText =
                new JLabel("Showing: Approved appointments");

        statusText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        statusText.setForeground(
                new Color(80, 90, 105)
        );

        statusText.setBounds(
                500,
                10,
                300,
                30
        );

        searchPanel.add(statusText);


        // =================================================
        // APPOINTMENTS TITLE
        // =================================================

        JLabel appointmentTitle =
                new JLabel("Active Appointments");

        appointmentTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        appointmentTitle.setForeground(
                new Color(25, 40, 70)
        );

        appointmentTitle.setBounds(
                35,
                175,
                300,
                30
        );

        panel.add(appointmentTitle);


        // =================================================
        // APPOINTMENT TABLE
        // =================================================

        String[] columns = {
                "ID",
                "Patient",
                "Department",
                "Date",
                "Doctor",
                "Status"
        };


        javax.swing.table.DefaultTableModel
                doctorTableModel =
                new javax.swing.table.DefaultTableModel(
                        null,
                        columns
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };


        JTable doctorTable =
                new JTable(
                        doctorTableModel
                );

        doctorTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        doctorTable.setRowHeight(35);

        doctorTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        doctorTable.setGridColor(
                new Color(230, 233, 240)
        );

        doctorTable.setShowVerticalLines(
                false
        );

        doctorTable.setBackground(
                Color.WHITE
        );

        doctorTable.setForeground(
                new Color(40, 50, 70)
        );

        doctorTable.setSelectionBackground(
                new Color(205, 220, 240)
        );

        doctorTable.setSelectionForeground(
                new Color(30, 50, 90)
        );


        doctorTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        doctorTable.getTableHeader()
                .setBackground(
                        new Color(240, 244, 250)
                );

        doctorTable.getTableHeader()
                .setForeground(
                        new Color(50, 60, 80)
                );

        doctorTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );


        // =================================================
        // LOAD ONLY APPROVED APPOINTMENTS
        // =================================================

        List<Appointment> appointments =
                AdminDAO.getAllAppointments();


        for (Appointment appointment :
                appointments) {

            // IMPORTANT:
            // Doctors page shows ONLY APPROVED

            if (!"APPROVED".equalsIgnoreCase(
                    appointment.getStatus()
            )) {
                continue;
            }


            doctorTableModel.addRow(
                    new Object[]{
                            appointment.getAppointmentId(),
                            appointment.getPatientName(),
                            appointment.getDepartment(),
                            appointment.getAppointmentDate(),
                            appointment.getDoctorName(),
                            "ACTIVE"
                    }
            );
        }


        JScrollPane tableScroll =
                new JScrollPane(
                        doctorTable
                );

        tableScroll.setBounds(
                35,
                210,
                890,
                180
        );

        tableScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 238)
                )
        );

        panel.add(tableScroll);


        // =================================================
        // PATIENT DETAILS CARD
        // =================================================

        JPanel patientPanel =
                new JPanel();

        patientPanel.setLayout(null);

        patientPanel.setBackground(
                Color.WHITE
        );

        patientPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 238)
                )
        );

        patientPanel.setBounds(
                35,
                420,
                430,
                230
        );

        panel.add(patientPanel);


        JLabel patientTitle =
                new JLabel(
                        "Patient Details"
                );

        patientTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        patientTitle.setForeground(
                new Color(25, 40, 70)
        );

        patientTitle.setBounds(
                20,
                15,
                250,
                30
        );

        patientPanel.add(patientTitle);


        // Patient

        JLabel patientLabel =
                new JLabel("Patient:");

        patientLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        patientLabel.setBounds(
                20,
                55,
                100,
                25
        );

        patientPanel.add(patientLabel);


        JLabel patientValue =
                new JLabel(
                        "Select an appointment"
                );

        patientValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        patientValue.setForeground(
                new Color(80, 90, 105)
        );

        patientValue.setBounds(
                125,
                55,
                280,
                25
        );

        patientPanel.add(patientValue);


        // Patient ID

        JLabel patientIdLabel =
                new JLabel("Patient ID:");

        patientIdLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        patientIdLabel.setBounds(
                20,
                90,
                100,
                25
        );

        patientPanel.add(patientIdLabel);


        JLabel patientIdValue =
                new JLabel("-");

        patientIdValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        patientIdValue.setForeground(
                new Color(80, 90, 105)
        );

        patientIdValue.setBounds(
                125,
                90,
                280,
                25
        );

        patientPanel.add(patientIdValue);


        // Department

        JLabel departmentLabel =
                new JLabel("Department:");

        departmentLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        departmentLabel.setBounds(
                20,
                125,
                100,
                25
        );

        patientPanel.add(departmentLabel);


        JLabel departmentValue =
                new JLabel("-");

        departmentValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        departmentValue.setForeground(
                new Color(80, 90, 105)
        );

        departmentValue.setBounds(
                125,
                125,
                280,
                25
        );

        patientPanel.add(departmentValue);


        // Reason

        JLabel reasonLabel =
                new JLabel("Reason:");

        reasonLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        reasonLabel.setBounds(
                20,
                160,
                100,
                25
        );

        patientPanel.add(reasonLabel);


        JLabel reasonValue =
                new JLabel("-");

        reasonValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        reasonValue.setForeground(
                new Color(80, 90, 105)
        );

        reasonValue.setBounds(
                125,
                160,
                280,
                25
        );

        patientPanel.add(reasonValue);


        // Priority

        JLabel priorityLabel =
                new JLabel("Priority:");

        priorityLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        priorityLabel.setBounds(
                20,
                195,
                100,
                25
        );

        patientPanel.add(priorityLabel);


        JLabel priorityValue =
                new JLabel("-");

        priorityValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        priorityValue.setForeground(
                new Color(80, 90, 105)
        );

        priorityValue.setBounds(
                125,
                195,
                280,
                25
        );

        patientPanel.add(priorityValue);


        // =================================================
        // PRESCRIPTION CARD
        // =================================================

        JPanel prescriptionPanel =
                new JPanel();

        prescriptionPanel.setLayout(null);

        prescriptionPanel.setBackground(
                Color.WHITE
        );

        prescriptionPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 238)
                )
        );

        prescriptionPanel.setBounds(
                485,
                420,
                440,
                230
        );

        panel.add(prescriptionPanel);


        JLabel prescriptionTitle =
                new JLabel(
                        "Prescription"
                );

        prescriptionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        prescriptionTitle.setForeground(
                new Color(25, 40, 70)
        );

        prescriptionTitle.setBounds(
                20,
                15,
                250,
                30
        );

        prescriptionPanel.add(
                prescriptionTitle
        );


        JLabel prescriptionInfo =
                new JLabel(
                        "<html>No prescription has been added.<br>" +
                                "Click the button below to add medicines.</html>"
                );

        prescriptionInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        prescriptionInfo.setForeground(
                new Color(100, 110, 125)
        );

        prescriptionInfo.setBounds(
                20,
                65,
                380,
                55
        );

        prescriptionPanel.add(
                prescriptionInfo
        );


        // =================================================
        // ADD PRESCRIPTION BUTTON
        // =================================================

        JButton addPrescriptionButton =
                new JButton(
                        "Add Prescription"
                );

        addPrescriptionButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        addPrescriptionButton.setForeground(
                Color.WHITE
        );

        addPrescriptionButton.setBackground(
                new Color(37, 99, 235)
        );

        addPrescriptionButton.setFocusPainted(
                false
        );

        addPrescriptionButton.setBorder(
                BorderFactory.createEmptyBorder()
        );

        addPrescriptionButton.setBounds(
                20,
                135,
                180,
                35
        );

        prescriptionPanel.add(
                addPrescriptionButton
        );


        // =================================================
        // COMPLETE BUTTON
        // =================================================

        JButton completeAppointmentButton =
                new JButton(
                        "Complete Appointment"
                );

        completeAppointmentButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        completeAppointmentButton.setForeground(
                Color.WHITE
        );

        completeAppointmentButton.setBackground(
                new Color(22, 163, 74)
        );

        completeAppointmentButton.setFocusPainted(
                false
        );

        completeAppointmentButton.setBorder(
                BorderFactory.createEmptyBorder()
        );

        completeAppointmentButton.setBounds(
                485,
                670,
                220,
                35
        );

        panel.add(
                completeAppointmentButton
        );


        // =================================================
        // CLEAR BUTTON
        // =================================================

        JButton clearButton =
                new JButton("Clear");

        clearButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        clearButton.setFocusPainted(
                false
        );

        clearButton.setBounds(
                720,
                670,
                100,
                35
        );

        panel.add(clearButton);


        // =================================================
        // TABLE SELECTION
        // =================================================

        doctorTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    int row =
                            doctorTable.getSelectedRow();

                    if (row == -1) {
                        return;
                    }


                    int appointmentId =
                            Integer.parseInt(
                                    doctorTable
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );


                    // Get complete appointment
                    // information from MySQL

                    Appointment appointment =
                            AdminDAO.getAppointmentById(
                                    appointmentId
                            );


                    if (appointment == null) {
                        return;
                    }


                    patientValue.setText(
                            appointment.getPatientName()
                    );

                    patientIdValue.setText(
                            appointment.getPatientId()
                    );

                    departmentValue.setText(
                            appointment.getDepartment()
                    );

                    reasonValue.setText(
                            appointment.getReason()
                    );

                    priorityValue.setText(
                            appointment.getPriority()
                    );


                    prescriptionInfo.setText(
                            "<html>No prescription has been added.<br>" +
                                    "Click the button below to add medicines.</html>"
                    );
                });


        // =================================================
        // ADD PRESCRIPTION
        // =================================================

        addPrescriptionButton.addActionListener(e -> {

            int selectedRow =
                    doctorTable.getSelectedRow();


            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Please select an approved appointment first."
                );

                return;
            }


            int appointmentId =
                    Integer.parseInt(
                            doctorTable
                                    .getValueAt(
                                            selectedRow,
                                            0
                                    )
                                    .toString()
                    );


            Appointment appointment =
                    AdminDAO.getAppointmentById(
                            appointmentId
                    );


            if (appointment == null) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Could not load appointment."
                );

                return;
            }


            openPrescriptionWindow(
                    appointment
            );
        });


        // =================================================
        // COMPLETE APPOINTMENT
        // =================================================

        completeAppointmentButton
                .addActionListener(e -> {

                    int selectedRow =
                            doctorTable.getSelectedRow();


                    if (selectedRow == -1) {

                        JOptionPane.showMessageDialog(
                                panel,
                                "Please select an approved appointment first."
                        );

                        return;
                    }


                    int appointmentId =
                            Integer.parseInt(
                                    doctorTable
                                            .getValueAt(
                                                    selectedRow,
                                                    0
                                            )
                                            .toString()
                            );


                    int result =
                            JOptionPane.showConfirmDialog(
                                    panel,
                                    "Mark this appointment as completed?",
                                    "Complete Appointment",
                                    JOptionPane.YES_NO_OPTION
                            );


                    if (result !=
                            JOptionPane.YES_OPTION) {

                        return;
                    }


                    boolean updated =
                            AdminDAO.updateAppointmentStatus(
                                    appointmentId,
                                    "COMPLETED"
                            );


                    if (updated) {

                        doctorTableModel
                                .removeRow(
                                        selectedRow
                                );

                        doctorTable.clearSelection();

                        patientValue.setText(
                                "Select an appointment"
                        );

                        patientIdValue.setText("-");
                        departmentValue.setText("-");
                        reasonValue.setText("-");
                        priorityValue.setText("-");


                        JOptionPane.showMessageDialog(
                                panel,
                                "Appointment completed successfully."
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                panel,
                                "Could not complete appointment."
                        );
                    }
                });


        // =================================================
        // CLEAR
        // =================================================

        clearButton.addActionListener(e -> {

            doctorTable.clearSelection();

            patientValue.setText(
                    "Select an appointment"
            );

            patientIdValue.setText("-");
            departmentValue.setText("-");
            reasonValue.setText("-");
            priorityValue.setText("-");

            prescriptionInfo.setText(
                    "<html>No prescription has been added.<br>" +
                            "Click the button below to add medicines.</html>"
            );
        });


        // =================================================
        // SEARCH
        // =================================================

        searchField.getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            private void filterTable() {

                                String search =
                                        searchField
                                                .getText()
                                                .trim()
                                                .toLowerCase();


                                for (
                                        int i =
                                        0;
                                        i <
                                                doctorTableModel
                                                        .getRowCount();
                                        i++
                                ) {

                                    String patient =
                                            doctorTableModel
                                                    .getValueAt(
                                                            i,
                                                            1
                                                    )
                                                    .toString()
                                                    .toLowerCase();


                                    String patientId = "";


                                    try {

                                        int appointmentId =
                                                Integer.parseInt(
                                                        doctorTableModel
                                                                .getValueAt(
                                                                        i,
                                                                        0
                                                                )
                                                                .toString()
                                                );


                                        Appointment a =
                                                AdminDAO
                                                        .getAppointmentById(
                                                                appointmentId
                                                        );


                                        if (a != null) {

                                            patientId =
                                                    a.getPatientId()
                                                            .toLowerCase();
                                        }

                                    } catch (Exception ignored) {
                                    }


                                    boolean matches =
                                            patient.contains(search)
                                                    ||
                                                    patientId.contains(search);


                                    doctorTable
                                            .setRowHeight(
                                                    i,
                                                    matches
                                                            ? 35
                                                            : 0
                                            );
                                }
                            }


                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filterTable();
                            }


                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filterTable();
                            }


                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filterTable();
                            }
                        }
                );


        // =================================================
        // FINAL SCROLLABLE PAGE
        // =================================================

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


    // =====================================================
    // PRESCRIPTION WINDOW
    // =====================================================

    private void openPrescriptionWindow(
            Appointment appointment
    ) {

        JDialog dialog =
                new JDialog(
                        this,
                        "Add Prescription",
                        true
                );

        dialog.setSize(
                500,
                600
        );

        dialog.setLocationRelativeTo(this);

        dialog.setLayout(
                new BorderLayout()
        );


        // =================================================
        // HEADER
        // =================================================

        JPanel topPanel =
                new JPanel();

        topPanel.setLayout(
                new BoxLayout(
                        topPanel,
                        BoxLayout.Y_AXIS
                )
        );

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );


        JLabel title =
                new JLabel(
                        "Add Prescription"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );


        JLabel patientLabel =
                new JLabel(
                        "Patient: " +
                                appointment.getPatientName()
                );

        patientLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        topPanel.add(title);

        topPanel.add(
                Box.createVerticalStrut(5)
        );

        topPanel.add(patientLabel);


        dialog.add(
                topPanel,
                BorderLayout.NORTH
        );


        // =================================================
        // MEDICINE CONTAINER
        // =================================================

        JPanel medicineContainer =
                new JPanel();

        medicineContainer.setLayout(
                new BoxLayout(
                        medicineContainer,
                        BoxLayout.Y_AXIS
                )
        );

        medicineContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );


        JScrollPane medicineScroll =
                new JScrollPane(
                        medicineContainer
                );

        medicineScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        medicineScroll.getVerticalScrollBar()
                .setUnitIncrement(16);


        dialog.add(
                medicineScroll,
                BorderLayout.CENTER
        );


        // =================================================
        // MEDICINE CARDS
        // =================================================

        java.util.List<JPanel> medicineCards =
                new java.util.ArrayList<>();


        java.util.function.Consumer<Void>
                addMedicineCard =
                ignored -> {

                    JPanel card =
                            new JPanel();

                    card.setLayout(null);

                    card.setPreferredSize(
                            new Dimension(
                                    420,
                                    190
                            )
                    );

                    card.setMaximumSize(
                            new Dimension(
                                    Integer.MAX_VALUE,
                                    190
                            )
                    );

                    card.setBackground(
                            Color.WHITE
                    );

                    card.setBorder(
                            BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(
                                            new Color(
                                                    220,
                                                    225,
                                                    235
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


                    // -----------------------------
                    // Medicine number
                    // -----------------------------

                    JLabel medicineTitle =
                            new JLabel(
                                    "Medicine " +
                                            (medicineCards.size() + 1)
                            );

                    medicineTitle.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    14
                            )
                    );

                    medicineTitle.setBounds(
                            15,
                            10,
                            200,
                            25
                    );

                    card.add(
                            medicineTitle
                    );


                    // -----------------------------
                    // Medicine Name
                    // -----------------------------

                    JLabel nameLabel =
                            new JLabel(
                                    "Medicine Name:"
                            );

                    nameLabel.setBounds(
                            15,
                            45,
                            110,
                            25
                    );

                    card.add(nameLabel);


                    JTextField nameField =
                            new JTextField();

                    nameField.setBounds(
                            125,
                            45,
                            265,
                            27
                    );

                    card.add(nameField);


                    // -----------------------------
                    // Description
                    // -----------------------------

                    JLabel descriptionLabel =
                            new JLabel(
                                    "Description:"
                            );

                    descriptionLabel.setBounds(
                            15,
                            80,
                            110,
                            25
                    );

                    card.add(
                            descriptionLabel
                    );


                    JTextField descriptionField =
                            new JTextField();

                    descriptionField.setBounds(
                            125,
                            80,
                            265,
                            27
                    );

                    card.add(
                            descriptionField
                    );


                    // -----------------------------
                    // Dosage
                    // -----------------------------

                    JLabel dosageLabel =
                            new JLabel(
                                    "Dosage:"
                            );

                    dosageLabel.setBounds(
                            15,
                            115,
                            110,
                            25
                    );

                    card.add(
                            dosageLabel
                    );


                    JTextField dosageField =
                            new JTextField();

                    dosageField.setBounds(
                            125,
                            115,
                            265,
                            27
                    );

                    card.add(
                            dosageField
                    );


                    // -----------------------------
                    // When to Take
                    // -----------------------------

                    JLabel whenLabel =
                            new JLabel(
                                    "When to Take:"
                            );

                    whenLabel.setBounds(
                            15,
                            150,
                            110,
                            25
                    );

                    card.add(
                            whenLabel
                    );


                    JTextField whenField =
                            new JTextField();

                    whenField.setToolTipText(
                            "Example: After breakfast"
                    );

                    whenField.setBounds(
                            125,
                            150,
                            265,
                            27
                    );

                    card.add(
                            whenField
                    );


                    medicineContainer.add(
                            card
                    );

                    medicineContainer.add(
                            Box.createVerticalStrut(
                                    10
                            )
                    );


                    medicineCards.add(
                            card
                    );


                    medicineContainer.revalidate();
                    medicineContainer.repaint();
                };


        // Add first medicine automatically

        addMedicineCard.accept(null);


        // =================================================
        // BOTTOM BUTTONS
        // =================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                10
                        )
                );


        JButton addMedicineButton =
                new JButton(
                        "+ Add Medicine"
                );


        JButton cancelButton =
                new JButton(
                        "Cancel"
                );


        JButton saveButton =
                new JButton(
                        "Save Prescription"
                );

        saveButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        saveButton.setForeground(
                Color.WHITE
        );

        saveButton.setBackground(
                new Color(
                        37,
                        99,
                        235
                )
        );

        saveButton.setFocusPainted(
                false
        );


        bottomPanel.add(
                addMedicineButton
        );

        bottomPanel.add(
                cancelButton
        );

        bottomPanel.add(
                saveButton
        );


        dialog.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =================================================
        // ADD MEDICINE
        // =================================================

        addMedicineButton.addActionListener(
                e -> {

                    addMedicineCard.accept(
                            null
                    );

                    medicineScroll
                            .getVerticalScrollBar()
                            .setValue(
                                    medicineScroll
                                            .getVerticalScrollBar()
                                            .getMaximum()
                            );
                }
        );


        // =================================================
        // CANCEL
        // =================================================

        cancelButton.addActionListener(
                e -> dialog.dispose()
        );


        // =================================================
        // SAVE
        // =================================================

        saveButton.addActionListener(
                e -> {

                    if (medicineCards.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please add at least one medicine."
                        );

                        return;
                    }


                    // Validate every medicine

                    for (
                            int i = 0;
                            i < medicineCards.size();
                            i++
                    ) {

                        JPanel card =
                                medicineCards.get(i);


                        JTextField nameField =
                                (JTextField)
                                        card.getComponent(2);

                        JTextField descriptionField =
                                (JTextField)
                                        card.getComponent(4);

                        JTextField dosageField =
                                (JTextField)
                                        card.getComponent(6);

                        JTextField whenField =
                                (JTextField)
                                        card.getComponent(8);


                        if (
                                nameField
                                        .getText()
                                        .trim()
                                        .isEmpty()
                                        ||
                                        descriptionField
                                                .getText()
                                                .trim()
                                                .isEmpty()
                                        ||
                                        dosageField
                                                .getText()
                                                .trim()
                                                .isEmpty()
                                        ||
                                        whenField
                                                .getText()
                                                .trim()
                                                .isEmpty()
                        ) {

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    "Please fill all fields for Medicine "
                                            + (i + 1)
                            );

                            return;
                        }
                    }


                    // Database connection will be added next

                    JOptionPane.showMessageDialog(
                            dialog,
                            "Prescription added successfully."
                    );

                    dialog.dispose();
                }
        );


        dialog.setVisible(true);
    }
}