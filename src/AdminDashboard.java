import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    private JButton dashboardButton;
    private JButton appointmentsButton;
    private JButton patientsButton;
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

        patientsButton =
                createSidebarButton(
                        "Patients",
                        "assets/patient-admin.png",
                        250
                );

        doctorsButton =
                createSidebarButton(
                        "Doctors",
                        "assets/doctor.png",
                        310
                );


        sidebar.add(dashboardButton);
        sidebar.add(appointmentsButton);
        sidebar.add(patientsButton);
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
                createPlaceholderPage(
                        "Appointments"
                ),
                "APPOINTMENTS"
        );

        contentPanel.add(
                createPlaceholderPage(
                        "Patients"
                ),
                "PATIENTS"
        );

        contentPanel.add(
                createPlaceholderPage(
                        "Doctors"
                ),
                "DOCTORS"
        );


        // ================= ADD TO FRAME =================

        add(sidebar, BorderLayout.WEST);

        add(
                contentPanel,
                BorderLayout.CENTER
        );


        // ================= BUTTON ACTIONS =================

        dashboardButton.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "DASHBOARD"
                )
        );


        appointmentsButton.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "APPOINTMENTS"
                )
        );


        patientsButton.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "PATIENTS"
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
                        "assets/admin_dashboard.png"
                );

        Image adminImage =
                adminIcon.getImage().getScaledInstance(
                        900,
                        120,
                        Image.SCALE_SMOOTH
                );

        JLabel adminImageLabel =
                new JLabel(
                        new ImageIcon(adminImage)
                );

        adminImageLabel.setBounds(
                15,
                10,
                900,
                120
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


        JLabel recentInfo =
                new JLabel(
                        "Recent appointments will appear here."
                );

        recentInfo.setForeground(Color.GRAY);

        recentInfo.setBounds(
                20,
                85,
                400,
                30
        );

        recentPanel.add(recentInfo);


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


        JLabel nextInfo =
                new JLabel(
                        "<html>" +
                                "No upcoming appointment information." +
                                "</html>"
                );

        nextInfo.setForeground(Color.GRAY);

        nextInfo.setBounds(
                20,
                70,
                450,
                80
        );

        nextPanel.add(nextInfo);


        // ================= STAT CARDS =================

        JPanel totalPatients =
                createStatCard(
                        "TOTAL PATIENTS",
                        "0",
                        "Registered Patients",
                        30,
                        490
                );

        JPanel todayAppointments =
                createStatCard(
                        "TODAY'S APPOINTMENTS",
                        "0",
                        "Today's appointments",
                        345,
                        490
                );

        JPanel pendingAppointments =
                createStatCard(
                        "PENDING APPOINTMENTS",
                        "0",
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
    // PLACEHOLDER PAGES
    // =====================================================

    private JPanel createPlaceholderPage(
            String pageName
    ) {

        JPanel panel = new JPanel(
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
}