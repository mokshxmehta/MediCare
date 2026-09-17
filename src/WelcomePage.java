import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WelcomePage extends JFrame {

    public WelcomePage() {

        setTitle("MediCare+");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));

        //---------------- LEFT PANEL ----------------//

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(20,32,87));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("MediCare+");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Inter",Font.BOLD,42));
        title.setBounds(80,100,400,60);

        JLabel subtitle = new JLabel("<html>Your trusted healthcare companion.<br>"
                +"Book appointments, medicine reminders,<br>"
                +"appointment records and much more.</html>");

        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI",Font.PLAIN,20));
        subtitle.setBounds(80,160,420,100);

        ImageIcon icon = new ImageIcon("assets/hospital.png");

        Image img = icon.getImage().getScaledInstance(
                400, 380,
                Image.SCALE_SMOOTH
        );

        ImageIcon scaledIcon = new ImageIcon(img);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(80, 280, 350, 250);

        JLabel bottom = new JLabel("Book • Reminder • History • Secure");
        bottom.setForeground(new Color(180,220,255));
        bottom.setFont(new Font("Segoe UI",Font.PLAIN,18));
        bottom.setBounds(110,560,350,30);

        leftPanel.add(title);
        leftPanel.add(subtitle);
        leftPanel.add(imageLabel);
        leftPanel.add(bottom);

        //---------------- RIGHT PANEL ----------------//

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);


        JPanel loginCard = new JPanel();
        loginCard.setLayout(null);
        loginCard.setBackground(new Color(243, 247, 255));
        loginCard.setBounds(55, 45, 430, 520);
        loginCard.setBorder(BorderFactory.createLineBorder(new Color(220,220,220),2));

        rightPanel.add(loginCard);


        JLabel login = new JLabel("LOGIN AS");
        login.setFont(new Font("Times New Roman", Font.BOLD, 40));
        login.setForeground(new Color(35,58,130));
        login.setBounds(110,35,250,50);


        JLabel line = new JLabel("Choose your login type");
        line.setForeground(Color.GRAY);
        line.setFont(new Font("Inter", Font.PLAIN,18));
        line.setBounds(110,85,250,30);


        JButton patientBtn = new JButton("  PATIENT");

        ImageIcon ficon = new ImageIcon("assets/patient.png");
        Image fimg = ficon.getImage().getScaledInstance(
                35,
                35,
                Image.SCALE_SMOOTH
        );

        patientBtn.setIcon(new ImageIcon(fimg));
        patientBtn.setBounds(50,170,330,70);
        patientBtn.setBackground(new Color(37,99,235));
        patientBtn.setForeground(Color.WHITE);
        patientBtn.setFocusPainted(false);
        patientBtn.setFont(new Font("Times New Roman", Font.BOLD,22));
        patientBtn.setBorder(new EmptyBorder(10,20,10,20));


        JButton adminBtn = new JButton("  ADMIN");

        ImageIcon aicon = new ImageIcon("Assets/admin.png");
        Image aimg = aicon.getImage().getScaledInstance(
                35,
                35,
                Image.SCALE_SMOOTH
        );

        adminBtn.setIcon(new ImageIcon(aimg));
        adminBtn.setBounds(50,270,330,70);
        adminBtn.setBackground(new Color(232, 113, 53));
        adminBtn.setForeground(Color.WHITE);
        adminBtn.setFocusPainted(false);
        adminBtn.setFont(new Font("Times New Roman", Font.BOLD,22));
        adminBtn.setBorder(new EmptyBorder(10,20,10,20));


        JLabel footer = new JLabel("Your Health. Our Priority.");
        footer.setFont(new Font("Segoe UI", Font.PLAIN,18));
        footer.setForeground(Color.GRAY);
        footer.setBounds(110,430,250,30);


        loginCard.add(login);
        loginCard.add(line);
        loginCard.add(patientBtn);
        loginCard.add(adminBtn);
        loginCard.add(footer);


        add(leftPanel);
        add(rightPanel);

        //---------------- BUTTONS ----------------//

        patientBtn.addActionListener(e -> {

                new PatientLogin();
                dispose();

            });



        adminBtn.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin Module Coming Soon!"
            );
            

        });

        setResizable(false);
        setVisible(true);
    }
}