package com.example.notfoodpandatest;

import com.example.notfoodpandatest.component.PanelCover;
import com.example.notfoodpandatest.swing.Button;
import com.example.notfoodpandatest.swing.MyTextField;
import com.example.notfoodpandatest.swing.RoundedLabel;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.Timer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.*;

// MAIN CLASS
public class NotFoodpanda extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WelcomeVideo video = new WelcomeVideo();
        video.play(() -> {
            SwingUtilities.invokeLater(() -> {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.setVisible(true);
            });
        });
    }
}

// WELCOME VIDEO CLASS - JAVA FX
class WelcomeVideo {

    private static final int FRAME_WIDTH = 1070;
    private static final int FRAME_HEIGHT = 600;
    public void play(Runnable callback) {
        URL videoURL = WelcomeVideo.class.getResource("/NotFoodpandaLogin.mp4");
        String videoPath = videoURL.toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(callback::run);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(FRAME_WIDTH);
        mediaView.setFitHeight(FRAME_HEIGHT);

        Pane pane = new Pane();
        pane.setPrefSize(FRAME_WIDTH, FRAME_HEIGHT);
        pane.getChildren().add(mediaView);

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Not Foodpanda");
        centerStageOnScreen(stage, FRAME_WIDTH, FRAME_HEIGHT);
        stage.show();

        mediaPlayer.play();
    }

    private void centerStageOnScreen(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - width) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - height) / 2;
        stage.setX(centerX);
        stage.setY(centerY);
    }
}

// WELCOME PAGE - I'M HUNGRY AND SIGN UP AS RIDER
class WelcomePage extends javax.swing.JFrame {

    public WelcomePage() {
        initComponents();
        setSize(1070,600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        Background = new javax.swing.JLabel();
        ImHungryButton = new javax.swing.JButton();
        BecomeARiderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        URL imageURL = WelcomePage.class.getResource("/Not Foodpanda Login.png");
        Background.setIcon(new javax.swing.ImageIcon(imageURL));
        getContentPane().add(Background);
        Background.setBounds(-3, -3, 1070, 590);

        ImHungryButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 100, true));
        getContentPane().add(ImHungryButton);
        ImHungryButton.setBounds(310, 270, 430, 140);
        ImHungryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                SignUpLoginPage SignUpLoginScreen = new SignUpLoginPage();
                SignUpLoginScreen.showFrame();
            }
        });

        getContentPane().add(BecomeARiderButton);
        BecomeARiderButton.setBounds(319, 444, 420, 90);
        BecomeARiderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BecomeARiderPage BecomeARiderScreen = new BecomeARiderPage();
                BecomeARiderScreen.setVisible(true);
            }
        });

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel Background;
    private javax.swing.JButton BecomeARiderButton;
    private javax.swing.JButton ImHungryButton;
    // End of variables declaration
}

// LOGIN AND REGISTER PANEL
class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public static String[] LoggedInUser;
    // Setter for LoggedInUser
    public void setLoggedInUser(String[] loggedInUser)
    {
        LoggedInUser = loggedInUser;
    }
    // Getter for LoggedInUser
    public String[] getLoggedInUser()
    {
        return LoggedInUser;
    }

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]25[]push"));

        // Create account label
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 231, 129));
        register.add(label, "span, center, wrap");

        // Sign up - Name text field
        MyTextField txtUser = new MyTextField();
        URL UserImageURL = PanelLoginAndRegister.class.getResource("/icon/user.png");
        ImageIcon userIcon = new ImageIcon(UserImageURL);
        txtUser.setPrefixIcon(userIcon);
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%, center, wrap");

        // Sign up - Email text field
        MyTextField txtEmail = new MyTextField();
        URL MailImageURL = PanelLoginAndRegister.class.getResource("/icon/mail.png");
        ImageIcon mailIcon = new ImageIcon(MailImageURL);
        txtEmail.setPrefixIcon(mailIcon);
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%, center, wrap");

        // Sign up - Phone number text field
        MyTextField txtPhoneNumber = new MyTextField();
        URL PhoneImageURL = PanelLoginAndRegister.class.getResource("/icon/phone.png");
        ImageIcon phoneIcon = new ImageIcon(PhoneImageURL);
        txtPhoneNumber.setPrefixIcon(phoneIcon);
        txtPhoneNumber.setHint("Phone Number");
        register.add(txtPhoneNumber, "w 60%, center, wrap");


        // Sign up - Location dropdown
        JComboBox<String> locationDropdown = new JComboBox<>();
        URL LocationImageURL = PanelLoginAndRegister.class.getResource("/icon/location.png");
        ImageIcon locationIcon = new ImageIcon(LocationImageURL);

        // Set custom renderer for the dropdown list items
        locationDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setIcon(locationIcon);
                return label;
            }
        });

        // Set background, foreground, and font properties
        locationDropdown.setBackground(txtPhoneNumber.getBackground());
        locationDropdown.setForeground(txtPhoneNumber.getForeground());
        locationDropdown.setFont(txtPhoneNumber.getFont());

        // Set preferred height based on the phone number text field
        locationDropdown.setPreferredSize(txtPhoneNumber.getPreferredSize());

        // Add items to the dropdown
        locationDropdown.addItem("Islamabad");
        locationDropdown.addItem("Lahore");
        locationDropdown.addItem("Karachi");

        register.add(locationDropdown, "w 60%, center, wrap, sgx txtPhoneNumber");

        // Sign up button
        com.example.notfoodpandatest.swing.Button cmd = new com.example.notfoodpandatest.swing.Button();
        cmd.setBackground(new Color(255, 231, 129));
        cmd.setForeground(new Color(41, 38, 38));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40, center");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = txtUser.getText();
                String Email = txtEmail.getText();
                String PhoneNumber = txtPhoneNumber.getText();
                String Location = (String) locationDropdown.getSelectedItem();

                // Check if any field is empty or null
                if (Name.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty() || Location == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    registerUser(Name, Email, PhoneNumber, Location);
                }
            }
        });
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]25[]push"));

        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 231, 129));
        login.add(label, "span, center, wrap");

        //Sign in - email text field
        MyTextField txtEmail = new MyTextField();
        URL MailImageURL = PanelLoginAndRegister.class.getResource("/icon/mail.png");
        ImageIcon mailIcon = new ImageIcon(MailImageURL);
        txtEmail.setPrefixIcon(mailIcon);
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%, center, wrap");


        //Sign in - phone text field
        MyTextField txtPhone = new MyTextField();
        URL PhoneImageURL = PanelLoginAndRegister.class.getResource("/icon/phone.png");
        ImageIcon phoneIcon = new ImageIcon(PhoneImageURL);
        txtPhone.setPrefixIcon(phoneIcon);
        txtPhone.setHint("Phone Number");
        login.add(txtPhone, "w 60%, center, wrap");


        com.example.notfoodpandatest.swing.Button cmd = new Button();
        cmd.setBackground(new Color(255, 231, 129));
        cmd.setForeground(new Color(41, 38, 38));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40, center");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = txtEmail.getText();
                String PhoneNumber = txtPhone.getText();

                // Check if email or phone number fields are empty
                if (Email.isEmpty() || PhoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both email and phone number!", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (validateUser(Email, PhoneNumber)) {
                        JOptionPane.showMessageDialog(null, "Successfully logged in!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        RestaurantsPage RestaurantsScreen = new RestaurantsPage();
                        RestaurantsScreen.setVisible(true);

                        // Storing the info of the logged in user
                        try (InputStream inputStream = PanelLoginAndRegister.class.getResourceAsStream("/Not Foodpanda Users.txt");
                             Scanner scanner = new Scanner(inputStream)) {

                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                String[] fields = line.split(", ");
                                String EmailToBeChecked = fields[1];
                                String PhoneToBeChecked = fields[2];
                                if (EmailToBeChecked.equals(Email) && PhoneToBeChecked.equals(PhoneNumber)) {
                                    String User[] = {fields[0], fields[1], fields[2], fields[3]};
                                    setLoggedInUser(User);
                                    System.out.println(LoggedInUser[0]);
                                }
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (Email.equals("nimrahkamran620@gmail.com") && PhoneNumber.equals("03332907662" )) {
                        setVisible(false);
                        adminpanel adminpanel = new adminpanel();
                        adminpanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Invalid Credentials!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new Color(41, 38, 38));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new Color(41, 38, 38));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }

    public boolean validateUser(String Email, String PhoneNumber) {
        try (InputStream inputStream = PanelLoginAndRegister.class.getResourceAsStream("/Not Foodpanda Users.txt");
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userInfo = line.split(", ");
                if (userInfo.length >= 3) {
                    String storedEmail = userInfo[1];
                    String storedPhoneNumber = userInfo[2];

                    if (Email.equals(storedEmail) && PhoneNumber.equals(storedPhoneNumber)) {
                        // User is validated
                        System.out.println("User is validated.");
                        return true;
                    }
                }
            }
            // User not found or credentials invalid
            System.out.println("Invalid credentials.");
        } catch (IOException e) {
            e.printStackTrace();
            // Error accessing the file
            System.out.println("Error accessing user data.");
        }
        return false;
    }


    public void registerUser(String Name, String Email, String PhoneNumber, String Location) {
        try {
            File file = new File(PanelLoginAndRegister.class.getResource("/Not Foodpanda Users.txt").toURI());
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(", ");
                    if (userInfo.length >= 4) {
                        String existingEmail = userInfo[1].trim();
                        String existingPhoneNumber = userInfo[2].trim();
                        if (existingEmail.equals(Email) || existingPhoneNumber.equals(PhoneNumber)) {
                            JOptionPane.showMessageDialog(null, "Email or Phone Number already exists! Please sign in instead.", "Error", JOptionPane.ERROR_MESSAGE);
                            reader.close();
                            return;
                        }
                    }
                }
                reader.close();
            }

            FileWriter writer = new FileWriter(file, true); // Create a FileWriter in append mode
            writer.write(Name + ", " + Email + ", " + PhoneNumber + ", " + Location + "\n"); // Write user information to the file
            writer.close(); // Close the FileWriter
            JOptionPane.showMessageDialog(null, "Successfully Registered! Sign in to Start Ordering!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Registering! Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}

//register restaurant
class RegisterRestaurantPage extends javax.swing.JFrame {


    public RegisterRestaurantPage() {
        initComponents();
        setSize(1070,610);
        setResizable(false);
        setLocationRelativeTo(null);
    }


    //Generated Code">
    private void initComponents() {

        Description = new javax.swing.JTextField();
        RestaurantName = new javax.swing.JTextField();
        Location = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        RegisterButton = new javax.swing.JButton();

        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Your Restaurant has been registered Successfully !");
            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(Description);
        Description.setBounds(360, 430, 360, 40);
        getContentPane().add(RestaurantName);
        RestaurantName.setBounds(210, 260, 500, 40);
        getContentPane().add(Location);
        Location.setBounds(300, 350, 420, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegisterRestaurantPage.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1090, 620);
        getContentPane().add(BackButton);
        BackButton.setBounds(730, 510, 230, 80);
        getContentPane().add(RegisterButton);
        RegisterButton.setBounds(50, 530, 230, 60);

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField Description;
    private javax.swing.JTextField Location;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JTextField RestaurantName;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
}

//ADMIN PAST ORDERS - SEE COMPLETE ORDER HISTORY
class AdminPastOrders extends javax.swing.JFrame {

    public AdminPastOrders() {
        initComponents();
        setSize(1070,600);
        setResizable(false);
        setLocationRelativeTo(null);

        displayOrderInfo(); // Display the extracted order information in the table
    }

    public List<String> extractOrderInfo() {
        List<String> orderInfo = new ArrayList<>();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                String email = fields[0];
                String restaurant = fields[1];
                String bill = fields[3];
                String orderNo = fields[4];

                // Add the extracted email, restaurant, bill, and order number to the list
                orderInfo.add(email);
                orderInfo.add(restaurant);
                orderInfo.add(bill);
                orderInfo.add(orderNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderInfo;
    }

    // Method to display the extracted order information in the table
    public void displayOrderInfo() {
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        List<String> orderInfo = extractOrderInfo();

        for (int i = 0; i < orderInfo.size(); i += 4) {
            String email = orderInfo.get(i);
            String restaurant = orderInfo.get(i + 1);
            String bill = orderInfo.get(i + 2);
            String orderNo = orderInfo.get(i + 3);

            model.addRow(new Object[]{email, restaurant, bill, orderNo});

            if (i == 0) {
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Email", "Restaurant", "Total Bill", "Order Number"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setRowHeight(30);
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(1).setResizable(false);
            Table.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 130, 690, 50);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 450, 290, 150);
        jPanel1.setBackground(new Color(42,42,42));

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/pastOrdersPage.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-4, 0, 1070, 602);

        BackButton.setText("jButton1");
        getContentPane().add(BackButton);
        BackButton.setBounds(839, 30, 200, 80);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                adminpanel AdminPanel = new adminpanel();
                AdminPanel.setVisible(true);
            }
        });

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PastOrders pastOrders = new PastOrders();
                pastOrders.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//USER PAST ORDERS
class UserPastOrders extends javax.swing.JFrame {
    private javax.swing.JTable jTable1;
    private DefaultTableModel model;
    String[] LoggedInUser = PanelLoginAndRegister.LoggedInUser;
    String UserEmail = LoggedInUser[1];

    public UserPastOrders() {
        initComponents();
        setSize(1070, 610);
        setResizable(false);
        setLocationRelativeTo(null);
        displayOrderInfo(); // Display the extracted order information in the table
    }

    // Method to extract order info from the text file
    public List<String> extractOrderInfo() {
        List<String> orderInfo = new ArrayList<>();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                String email = fields[0];
                String restaurant = fields[1];
                String bill = fields[3];
                String orderNo = fields[4];

                // Add the extracted email, restaurant, bill, and order number to the list
                orderInfo.add(email);
                orderInfo.add(restaurant);
                orderInfo.add(bill);
                orderInfo.add(orderNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderInfo;
    }

    // Method to display the extracted order information in the table
    public void displayOrderInfo() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        List<String> orderInfo = extractOrderInfo();

        for (int i = 0; i < orderInfo.size(); i += 4) {
            String email = orderInfo.get(i);
            String restaurant = orderInfo.get(i + 1);
            String bill = orderInfo.get(i + 2);
            String orderNo = orderInfo.get(i + 3);

            if (email.equals(UserEmail)) {
                model.addRow(new Object[]{restaurant, bill, orderNo});

                if (i == 0) {
                    jScrollPane1.setBounds(30, 130, 690, defaultHeight);
                } else {
                    defaultHeight += 30;
                    jScrollPane1.setBounds(30, 130, 690, defaultHeight);
                }
            }
        }
    }

    // Generated Code
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        complaintsTextBox = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        submitButton1 = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        submitButton1 .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's complaint from the text area
                String complaint = complaintsTextBox.getText();

                // Append the complaint to the text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true))) {
                    writer.write(complaint);
                    writer.newLine();
                    writer.flush();
                    JOptionPane.showMessageDialog(null, "Complaint submitted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Clear the text area after submission
                    complaintsTextBox.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to submit complaint.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jTable1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.GreyInline"));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Restaurant", "Bill", "Order Number"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 450, 290, 150);
        jPanel1.setBackground(new Color(42,42,42));

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 140, 620, 280);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Past Orders.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1090, 610);
        getContentPane().add(submitButton1);
        submitButton1.setBounds(30, 520, 250, 60);
        getContentPane().add(BackButton);
        BackButton.setBounds(830, 40, 220, 80);

        getContentPane().add(complaintsTextBox);
        complaintsTextBox.setBounds(30, 470, 250, 30);
        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField complaintsTextBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton submitButton1;
    // End of variables declaration
}

// SIGN UP AND LOGIN PAGE
class SignUpLoginPage extends JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin = true;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;

    public SignUpLoginPage() {
        initComponents();
        init();
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loginAndRegister = new PanelLoginAndRegister();
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%"); //  1al as 100%
        loginAndRegister.showRegister(!isLogin);
        cover.login(isLogin);
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new JLayeredPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        GroupLayout bgLayout = new GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 537, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg, GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public void showFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}

// BECOME A RIDER - REGISTER AS RIDER OR GO BACK TO WELCOME PAGE
class BecomeARiderPage extends javax.swing.JFrame {

    public BecomeARiderPage() {
        initComponents();
        setSize(933,560);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        LocationDropdown = new javax.swing.JComboBox<>();
        SignUpLabel = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        GoBackLabel = new javax.swing.JLabel();
        GoBackButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Location dropdown
        LocationDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islamabad", "Lahore", "Karachi" }));
        getContentPane().add(LocationDropdown);
        LocationDropdown.setBounds(90, 390, 420, 40);

        //Phone Text Field
        MyTextField txtPhoneNumber = new MyTextField();
        URL PhoneImageURL = NotFoodpanda.class.getResource("/icon/phone.png");
        ImageIcon phoneIcon = new ImageIcon(PhoneImageURL);
        txtPhoneNumber.setPrefixIcon(phoneIcon);
        txtPhoneNumber.setHint("Phone Number");
        getContentPane().add(txtPhoneNumber);
        txtPhoneNumber.setBounds(90, 320, 420, 40);

        //Email text field
        MyTextField txtEmail = new MyTextField();
        URL MailImageURL = NotFoodpanda.class.getResource("/icon/mail.png");
        ImageIcon mailIcon = new ImageIcon(MailImageURL);
        txtEmail.setPrefixIcon(mailIcon);
        txtEmail.setHint("Email");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(90, 250, 420, 40);

        //Username text field
        MyTextField txtUser = new MyTextField();
        URL UserImageURL = NotFoodpanda.class.getResource("/icon/user.png");
        ImageIcon userIcon = new ImageIcon(UserImageURL);
        txtUser.setPrefixIcon(userIcon);
        txtUser.setHint("Name");
        getContentPane().add(txtUser);
        txtUser.setBounds(90, 180, 420, 40);

        //Sign up label on the button
        SignUpLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        SignUpLabel.setForeground(new java.awt.Color(42, 42, 42));
        SignUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignUpLabel.setText("Sign Up");
        getContentPane().add(SignUpLabel);
        SignUpLabel.setBounds(340, 480, 110, 30);

        //Sign up button
        SignUpButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(33, 38, 38));
        SignUpButton.setText("Sign Up");
        SignUpButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 231, 129), 50, true));
        getContentPane().add(SignUpButton);
        SignUpButton.setBounds(320, 470, 150, 50);
        SignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = txtUser.getText();
                String Email = txtEmail.getText();
                String PhoneNumber = txtPhoneNumber.getText();
                String Location = (String) LocationDropdown.getSelectedItem();

                // Check if any field is empty or null
                if (Name.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty() || Location == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    registerRider(Name, Email, PhoneNumber, Location);
                }
            }
        });


        //Go back label on the go back button
        GoBackLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        GoBackLabel.setForeground(new java.awt.Color(42, 42, 42));
        GoBackLabel.setText("Go Back");
        getContentPane().add(GoBackLabel);
        GoBackLabel.setBounds(150, 480, 100, 30);

        //GO back button
        GoBackButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        GoBackButton.setForeground(new java.awt.Color(33, 38, 38));
        GoBackButton.setText("Go Back");
        GoBackButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 231, 129), 50, true));
        GoBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(GoBackButton);
        GoBackButton.setBounds(120, 470, 150, 50);

        //Background
        URL imageURL = WelcomePage.class.getResource("/Become a Rider.png");
        jLabel1.setIcon(new javax.swing.ImageIcon(imageURL));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 933, 537);

        pack();
    }

    private void GoBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        setVisible(false);
        WelcomePage welcomePageScreen = new WelcomePage();
        welcomePageScreen.setVisible(true);
    }

    //Register a Rider Method
    public void registerRider(String Name, String Email, String PhoneNumber, String Location) {
        try {
            String FilePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Not Foodpanda Riders.txt";
            File file = new File(FilePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(", ");
                    if (userInfo.length >= 4) {
                        String existingEmail = userInfo[1].trim();
                        String existingPhoneNumber = userInfo[2].trim();
                        if (existingEmail.equals(Email) || existingPhoneNumber.equals(PhoneNumber)) {
                            JOptionPane.showMessageDialog(null, "Email or Phone Number already exists! Please sign in instead.", "Error", JOptionPane.ERROR_MESSAGE);
                            reader.close();
                            return;
                        }
                    }
                }
                reader.close();
            }

            FileWriter writer = new FileWriter(file, true); // Create a FileWriter in append mode
            writer.write(Name + ", " + Email + ", " + PhoneNumber + ", " + Location + "\n"); // Write user information to the file
            writer.close(); // Close the FileWriter
            JOptionPane.showMessageDialog(null, "Successfully Registered! Sign in to Start Ordering!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Registering! Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton GoBackButton;
    private javax.swing.JLabel GoBackLabel;
    private javax.swing.JComboBox<String> LocationDropdown;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel SignUpLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}

//RESTAURANTS PAGE
class RestaurantsPage extends javax.swing.JFrame {

    SelectedRestaurant selectedRestaurant = SelectedRestaurant.getInstance();
    public static String SearchKey;

    public RestaurantsPage() {
        initComponents();
        setSize(1070,600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        DemonicPizzaButton = new javax.swing.JButton();
        OptionsDropDown = new javax.swing.JComboBox<>();
        CheezyYesButton = new javax.swing.JButton();
        LFCButton = new javax.swing.JButton();
        McDanishButton = new javax.swing.JButton();
        SignOutButton = new javax.swing.JButton();
        RegisterRestaurantButton = new javax.swing.JButton();
        SearchBar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        SearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Search bar
        SearchBar.setFont(new java.awt.Font("Dialog", 0, 14));
        getContentPane().add(SearchBar);
        SearchBar.setBounds(320, 490, 310, 50);

        RegisterRestaurantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterRestaurantPage registerRestaurantPage = new RegisterRestaurantPage();
                registerRestaurantPage.setVisible(true);
            }
        });

        OptionsDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Main Menu", "Account", "Past Orders" ,"About Us" }));
        getContentPane().add(OptionsDropDown);
        OptionsDropDown.setBounds(10, 10, 130, 23);
        OptionsDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedOption = (String) OptionsDropDown.getSelectedItem();
                //Account Option
                if (SelectedOption.equals("Account")){
                    setVisible(false);
                    AccountPage accountPage = new AccountPage();
                    accountPage.setVisible(true);
                }
                else if (SelectedOption.equals("Past Orders")){
                    setVisible(false);
                    UserPastOrders pastOrders = new UserPastOrders();
                    pastOrders.setVisible(true);
                }
            }
        });
        OptionsDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedOption = (String) OptionsDropDown.getSelectedItem();
                //Aboutuspage Option
                if (SelectedOption.equals("About Us")){
                    setVisible(false);
                    aboutuspage aboutuspage = new aboutuspage();
                    aboutuspage.setVisible(true);
                }
            }
        });


        URL imageURL = WelcomePage.class.getResource("/Restaurants.png");
        jLabel1.setIcon(new javax.swing.ImageIcon(imageURL));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1080, 600);

        // Search button
        getContentPane().add(SearchButton);
        SearchButton.setBounds(649, 484, 60, 60);
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = SearchBar.getText(); // Retrieve the search query from the text field
                setVisible(false);

                // Perform the search
                ItemSearch itemSearch = new ItemSearch();
                String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/food items.txt";
                itemSearch.readItemsFromFile(filePath);
                List<Item> searchResults = itemSearch.searchItems(searchQuery);

                // Create an instance of SearchResultsTable
                SearchResultsTable searchResultsTable = new SearchResultsTable();

                // Call the displaySearchResults method to populate the table with the search results
                searchResultsTable.displaySearchResults(searchResults);

                // Make the frame visible
                searchResultsTable.setVisible(true);
            }
        });

        DemonicPizzaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemonicPizzaButtonActionPerformed(evt);
            }
        });
        getContentPane().add(DemonicPizzaButton);
        DemonicPizzaButton.setBounds(789, 194, 250, 240);

        CheezyYesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheezyYesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CheezyYesButton);
        CheezyYesButton.setBounds(30, 190, 240, 240);

        LFCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LFCButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LFCButton);
        LFCButton.setBounds(289, 194, 230, 240);

        McDanishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                McDanishButtonActionPerformed(evt);
            }
        });
        getContentPane().add(McDanishButton);
        McDanishButton.setBounds(539, 194, 230, 240);

        SignOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SignOutButton);
        SignOutButton.setBounds(19, 474, 260, 90);

        RegisterRestaurantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterRestaurantButtonActionPerformed(evt);
            }
        });
        getContentPane().add(RegisterRestaurantButton);
        RegisterRestaurantButton.setBounds(769, 474, 270, 90);

        pack();
    }

    //Cheezy-yes button
    private void CheezyYesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        selectedRestaurant.setSelectedRestaurant("CheezyYes");
        setVisible(false);
        CheezyYes CheezyYesPage = new CheezyYes();
        CheezyYesPage.setVisible(true);
    }

    //Register restaurant button
    private void RegisterRestaurantButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println("Button Clicked");
    }

    //LFC Button
    private void LFCButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        selectedRestaurant.setSelectedRestaurant("LFC");
        setVisible(false);
        LalaMusaFriedChicken LFCScreen = new LalaMusaFriedChicken();
        LFCScreen.setVisible(true);
    }

    //McDanish Button
    private void McDanishButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        selectedRestaurant.setSelectedRestaurant("McDanish");
        setVisible(false);
        McDanish mcDanishPage = new McDanish();
        mcDanishPage.setVisible(true);
    }

    //Demonic Pizza Button
    private void DemonicPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        selectedRestaurant.setSelectedRestaurant("Demonic Pizza");
        setVisible(false);
        DemonicPizza demonicPizza = new DemonicPizza();
        demonicPizza.setVisible(true);
    }

    //Signout Button
    private void SignOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        setVisible(false);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.setVisible(true);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton CheezyYesButton;
    private javax.swing.JButton DemonicPizzaButton;
    private javax.swing.JButton LFCButton;
    private javax.swing.JButton McDanishButton;
    private javax.swing.JComboBox<String> OptionsDropDown;
    private javax.swing.JButton RegisterRestaurantButton;
    private javax.swing.JButton SignOutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JButton SearchButton;
    // End of variables declaration
}

//CHEEZY-YES MENU
class CheezyYes extends javax.swing.JFrame {

    CheckoutPage checkoutPage = CheckoutPage.getInstance();
    RoundedLabel CartQuantityLabel;

    public CheezyYes() {
        initComponents();
        setSize(1080,630);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        //Buttons and Labels creation
        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        Page1Panel = new javax.swing.JPanel();
        Page1Background = new javax.swing.JLabel();
        PizzaStackerButton = new javax.swing.JButton();
        CrunchyChickenPastaButton = new javax.swing.JButton();
        MexicanSandwichButton = new javax.swing.JButton();
        CheeseSticksButton = new javax.swing.JButton();
        CartButton = new javax.swing.JButton();
        Page2Panel = new javax.swing.JPanel();
        Page2Background = new javax.swing.JLabel();
        SpecialRoastedPlatterButton = new javax.swing.JButton();
        OvenBakedWingsButton = new javax.swing.JButton();
        CheckoutButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        CalzoneChunksButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Scroll Pane
        jScrollPane1.setBackground(new java.awt.Color(41, 38, 38));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //Main Panel
        MainPanel.setBackground(new java.awt.Color(41, 38, 38));

        //Page 1 Panel
        Page1Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Cart Quantity Label
        CartQuantityLabel = new RoundedLabel("0", new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setBackground(new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        CartQuantityLabel.setFont(CartQuantityLabel.getFont().deriveFont(Font.BOLD));
        CartQuantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CartQuantityLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        Page1Panel.add(CartQuantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 20, 20));

        //Page 1 Background
        URL Page1ImageURL = CheezyYes.class.getResource("/CheezyYes Page 1.png");
        Page1Background.setIcon(new javax.swing.ImageIcon(Page1ImageURL));
        Page1Panel.add(Page1Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 600));

        //Pizza Stacker Button
        Page1Panel.add(PizzaStackerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 460, 100, 40));
        PizzaStackerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Pizza Stacker", 790.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Crunchy Chicken Pasta Button
        Page1Panel.add(CrunchyChickenPastaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 100, 30));
        CrunchyChickenPastaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Crunchy Chicken Pasta",790.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Mexican Sandwich Button
        Page1Panel.add(MexicanSandwichButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 100, 30));
        MexicanSandwichButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Mexican Sandwich",790.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Cheese Stick Button
        Page1Panel.add(CheeseSticksButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 100, 30));
        CheeseSticksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Cheese Sticks",560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Cart Button
        Page1Panel.add(CartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 70));
        CartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });

        //Page 2 Panel
        Page2Panel.setBackground(new java.awt.Color(255, 153, 153));
        Page2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Page 2 Background
        URL Page2ImageURL = CheezyYes.class.getResource("/CheezyYes Page 2.png");
        Page2Background.setIcon(new javax.swing.ImageIcon(Page2ImageURL));
        Page2Panel.add(Page2Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        //Spacial Roasted Platter Button
        Page2Panel.add(SpecialRoastedPlatterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 110, 30));
        SpecialRoastedPlatterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Special Roasted Platter",990.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Oven Baked Wings Button
        Page2Panel.add(OvenBakedWingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 110, 30));
        OvenBakedWingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Oven Baked Wings",490.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Checkout Button
        CheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });
        Page2Panel.add(CheckoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 370, 50));

        //Back button
        Page2Panel.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 530, 110, 60));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Confirmation for going back
                int option = JOptionPane.showOptionDialog(null, "You have already selected items from CheezyYes. Going back will clear your cart, are you sure you want to proceed?", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{"Yes", "No"}, "No");
                //If user selects 'yes', Open restaurants page and clear cart to allow orders from one restaurant only
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false); //Hide the CheezyYes page
                    RestaurantsPage restaurantsPage = new RestaurantsPage();
                    restaurantsPage.setVisible(true); //Display the restaurants page
                    checkoutPage.clearCart(); //Clear cart
                }
            }
        });

        //Calzone Chunks Button
        Page2Panel.add(CalzoneChunksButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 110, 30));
        CalzoneChunksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Calzone Chunks",960.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Page1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(Page1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(MainPanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 1080, 600);


        pack();

    }// </editor-fold>

    public RoundedLabel getCartQuantityLabel() {
        return CartQuantityLabel;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CalzoneChunksButton;
    private javax.swing.JButton CartButton;
    private javax.swing.JButton CheckoutButton;
    private javax.swing.JButton CheeseSticksButton;
    private javax.swing.JButton CrunchyChickenPastaButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton MexicanSandwichButton;
    private javax.swing.JButton OvenBakedWingsButton;
    private javax.swing.JLabel Page1Background;
    private javax.swing.JPanel Page1Panel;
    private javax.swing.JLabel Page2Background;
    private javax.swing.JPanel Page2Panel;
    private javax.swing.JButton PizzaStackerButton;
    private javax.swing.JButton SpecialRoastedPlatterButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//MCDANISH MENU
class McDanish extends javax.swing.JFrame {

    CheckoutPage checkoutPage = CheckoutPage.getInstance();
    RoundedLabel CartQuantityLabel;

    public McDanish() {
        initComponents();
        setSize(1080,650);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        Page1Panel = new javax.swing.JPanel();
        Page1Background = new javax.swing.JLabel();
        McNuggetsButton = new javax.swing.JButton();
        HappyMealButton = new javax.swing.JButton();
        QuarterPounderButton = new javax.swing.JButton();
        EggMcMuffinButton = new javax.swing.JButton();
        CartButton = new javax.swing.JButton();
        Page2Panel = new javax.swing.JPanel();
        Page2Background = new javax.swing.JLabel();
        McCafeButton = new javax.swing.JButton();
        CheckoutButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        CheeseburgerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Scroll Panel
        jScrollPane1.setBackground(new java.awt.Color(41, 38, 38));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        MainPanel.setBackground(new java.awt.Color(41, 38, 38));

        //Page 1 Panel
        Page1Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Cart Quantity Label
        CartQuantityLabel = new RoundedLabel("0", new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setBackground(new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        CartQuantityLabel.setFont(CartQuantityLabel.getFont().deriveFont(Font.BOLD));
        CartQuantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CartQuantityLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        Page1Panel.add(CartQuantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 20, 20));

        //Page 1 Background
        URL Page1ImageURL = McDanish.class.getResource("/McDanish Page 1.png");
        Page1Background.setIcon(new javax.swing.ImageIcon(Page1ImageURL));
        Page1Panel.add(Page1Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));
        //MCNUGGETS BUTTON
        Page1Panel.add(McNuggetsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 450, 200, 60));
        McNuggetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("10 Piece Chicken McNuggets",560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        Page1Panel.add(HappyMealButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 200, 60));

        //HAPPY MEAL BUTTON
        HappyMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                checkoutPage.addItemToCart("Hamburger Happy Meal", 1360.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        Page1Panel.add(QuarterPounderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 210, 60));

        //QUARTER POUNDER BUTTON
        QuarterPounderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Quarter Pounder with Cheese Deluxe", 990.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        Page1Panel.add(EggMcMuffinButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 210, 60));

        //EGG MCMUFFIN BUTTON
        EggMcMuffinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Egg McMuffin Meal", 560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Cart Button
        Page1Panel.add(CartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 70));
        CartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });

        Page2Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Page 2 Background
        URL Page2ImageURL = McDanish.class.getResource("/McDanish Page 2.png");
        Page2Background.setIcon((new javax.swing.ImageIcon(Page2ImageURL)));
        Page2Panel.add(Page2Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        Page2Panel.add(McCafeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 170, 60));

        //MCCAFE BUTTON
        McCafeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Caramel Frappe with Blueberry Muffin", 590.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHECKOUT BUTTON
        CheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });
        Page2Panel.add(CheckoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, 370, 60));
        Page2Panel.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 530, 110, 60));

        //BACK BUTTON
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Confirmation for going back
                int option = JOptionPane.showOptionDialog(null, "You have already selected items from McDanish. Going back will clear your cart, are you sure you want to proceed?", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{"Yes", "No"}, "No");
                //If user selects 'yes', Open restaurants page and clear cart to allow orders from one restaurant only
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false); //Hide the CheezyYes page
                    RestaurantsPage restaurantsPage = new RestaurantsPage();
                    restaurantsPage.setVisible(true); //Display the restaurants page
                    checkoutPage.clearCart(); //Clear cart
                }
            }
        });

        Page2Panel.add(CheeseburgerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, 180, 60));
        //CHEESEBURGER BUTTON
        CheeseburgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Double Cheeseburger with Diet Coke", 785.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Page1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(Page1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(MainPanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 1080, 630);

        pack();
    }// </editor-fold>

    public RoundedLabel getCartQuantityLabel() {
        return CartQuantityLabel;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CartButton;
    private javax.swing.JButton CheckoutButton;
    private javax.swing.JButton CheeseburgerButton;
    private javax.swing.JButton EggMcMuffinButton;
    private javax.swing.JButton HappyMealButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton McCafeButton;
    private javax.swing.JButton McNuggetsButton;
    private javax.swing.JLabel Page1Background;
    private javax.swing.JPanel Page1Panel;
    private javax.swing.JLabel Page2Background;
    private javax.swing.JPanel Page2Panel;
    private javax.swing.JButton QuarterPounderButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//DEMONIC PIZZA MENU
class DemonicPizza extends javax.swing.JFrame {

    RoundedLabel CartQuantityLabel;
    CheckoutPage checkoutPage = CheckoutPage.getInstance();

    public DemonicPizza() {
        initComponents();
        setSize(1080,650);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        Page1Panel = new javax.swing.JPanel();
        Page1Background = new javax.swing.JLabel();
        HotNSpicyPizzaButton = new javax.swing.JButton();
        ChickenFajitaPizzaButton = new javax.swing.JButton();
        ChickenPieceButton = new javax.swing.JButton();
        CheesyTikkaPizzaButton = new javax.swing.JButton();
        BehariKababPizzaButton = new javax.swing.JButton();
        FriesButton = new javax.swing.JButton();
        LargePizzaDealButton = new javax.swing.JButton();
        CartButton = new javax.swing.JButton();
        Page2Panel = new javax.swing.JPanel();
        Page2Background = new javax.swing.JLabel();
        CheeseLoverPizzaButton = new javax.swing.JButton();
        ChickenPepporoniPizzaButton = new javax.swing.JButton();
        CheckoutButton = new javax.swing.JButton();
        VegetablePizzaButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(41, 38, 38));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        MainPanel.setBackground(new java.awt.Color(41, 38, 38));

        Page1Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Cart Quantity Label
        CartQuantityLabel = new RoundedLabel("0", new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setBackground(new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        CartQuantityLabel.setFont(CartQuantityLabel.getFont().deriveFont(Font.BOLD));
        CartQuantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CartQuantityLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        Page1Panel.add(CartQuantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 30, 20, 20));

        //Page 1 Background
        URL Page1ImageIcon = DemonicPizza.class.getResource("/Demonic Pizza Page 1.png");
        Page1Background.setIcon(new javax.swing.ImageIcon(Page1ImageIcon));
        Page1Panel.add(Page1Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        //HOT N SPICY PIZZA BUTTON
        Page1Panel.add(HotNSpicyPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 170, 70));
        HotNSpicyPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Hot N Spicy Pizza", 380.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHICKEN FAJITA BUTTON
        Page1Panel.add(ChickenFajitaPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 170, 70));
        ChickenFajitaPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Chicken Fajita Pizza", 490.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHICKEN PIECE BUTTON
        Page1Panel.add(ChickenPieceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 170, 70));
        ChickenPieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Chicken Piece", 200.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHEESY TIKKA PIZZA
        Page1Panel.add(CheesyTikkaPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 170, 70));
        CheesyTikkaPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Chicken Tikka Pizza", 470.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //BEHARI KABAB PIZZA
        Page1Panel.add(BehariKababPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 170, 70));
        BehariKababPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Behari Kabab Pizza", 680.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //FRIES BUTTON
        Page1Panel.add(FriesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, 170, 70));
        FriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Fries ", 90.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Large Pizza Deal Button
        Page1Panel.add(LargePizzaDealButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 350, 180, 70));
        LargePizzaDealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Large Pizza Deal", 1200, 1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //Cart Button
        Page1Panel.add(CartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 80, 70));
        CartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });

        Page2Panel.setBackground(new java.awt.Color(0, 51, 255));
        Page2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Page 2 Background
        URL Page2ImageIcon = DemonicPizza.class.getResource("/Demonic Pizza Page 2.png");
        Page2Background.setIcon(new javax.swing.ImageIcon(Page2ImageIcon));
        Page2Panel.add(Page2Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        //CHEESE LOVER PIZZA
        Page2Panel.add(CheeseLoverPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 450, 170, 60));
        CheeseLoverPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Cheese Lover Pizza", 1360.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        Page2Panel.add(ChickenPepporoniPizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 170, 60));

        //CHICKEN PEPPERONI PIZZA
        ChickenPepporoniPizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Chicken Pepperoni Pizza", 560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHECKOUT BUTTON
        Page2Panel.add(CheckoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 390, 60));
        CheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });
        Page2Panel.add(VegetablePizzaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 170, 60));
        //VEGETABLE PIZZA
        VegetablePizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Vegetable Pizza ", 560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        //BACK BUTTON
        Page2Panel.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 120, 50));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Confirmation for going back
                int option = JOptionPane.showOptionDialog(null, "You have already selected items from Demonic Pizza. Going back will clear your cart, are you sure you want to proceed?", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{"Yes", "No"}, "No");
                //If user selects 'yes', Open restaurants page and clear cart to allow orders from one restaurant only
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false); //Hide the CheezyYes page
                    RestaurantsPage restaurantsPage = new RestaurantsPage();
                    restaurantsPage.setVisible(true); //Display the restaurants page
                    checkoutPage.clearCart(); //Clear cart
                }
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Page1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(Page1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(MainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>

    public RoundedLabel getCartQuantityLabel() {
        return CartQuantityLabel;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JButton BehariKababPizzaButton;
    private javax.swing.JButton CartButton;
    private javax.swing.JButton CheckoutButton;
    private javax.swing.JButton CheeseLoverPizzaButton;
    private javax.swing.JButton CheesyTikkaPizzaButton;
    private javax.swing.JButton ChickenFajitaPizzaButton;
    private javax.swing.JButton ChickenPepporoniPizzaButton;
    private javax.swing.JButton ChickenPieceButton;
    private javax.swing.JButton FriesButton;
    private javax.swing.JButton HotNSpicyPizzaButton;
    private javax.swing.JButton LargePizzaDealButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Page1Background;
    private javax.swing.JPanel Page1Panel;
    private javax.swing.JLabel Page2Background;
    private javax.swing.JPanel Page2Panel;
    private javax.swing.JButton VegetablePizzaButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//LFC MENU
class LalaMusaFriedChicken extends javax.swing.JFrame {

    CheckoutPage checkoutPage = CheckoutPage.getInstance();
    RoundedLabel CartQuantityLabel;

    public LalaMusaFriedChicken() {
        initComponents();
        setSize(1090,630);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        Page1Panel = new javax.swing.JPanel();
        Page1Background = new javax.swing.JLabel();
        KrunchComboButton = new javax.swing.JButton();
        MightyZingerButton = new javax.swing.JButton();
        HotWingsButton = new javax.swing.JButton();
        MidnightDeal1Button = new javax.swing.JButton();
        CartButton = new javax.swing.JButton();
        FamilyFestival1Button = new javax.swing.JButton();
        Page2Panel = new javax.swing.JPanel();
        Page2Background = new javax.swing.JLabel();
        ValueBucketButton = new javax.swing.JButton();
        CheckoutButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        XtremeDuoBoxButton = new javax.swing.JButton();
        CrispyDuoBoxButton = new javax.swing.JButton();
        BonelessMealBoxButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(41, 38, 38));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        MainPanel.setBackground(new java.awt.Color(41, 38, 38));

        Page1Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CartQuantityLabel = new RoundedLabel("0", new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setBackground(new java.awt.Color(255, 3, 3));
        CartQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        CartQuantityLabel.setFont(CartQuantityLabel.getFont().deriveFont(Font.BOLD));
        CartQuantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CartQuantityLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        Page1Panel.add(CartQuantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 50, 20));

        URL Page1ImageURL = LalaMusaFriedChicken.class.getResource("/LFC Page 1.png");
        Page1Background.setIcon(new javax.swing.ImageIcon(Page1ImageURL));
        Page1Panel.add(Page1Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));
        Page1Panel.add(KrunchComboButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 490, 170, 50));

        //KRUNCH COMBO
        KrunchComboButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Krunch Combo", 560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());

            }
        });

        Page1Panel.add(MightyZingerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 170, 50));
        //MIGHTY ZINGER
        MightyZingerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Mighty Zinger", 680.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        Page1Panel.add(HotWingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 170, 50));
        //HOT WINGS
        HotWingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Hot Wings", 560.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        Page1Panel.add(MidnightDeal1Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 170, 50));
        //MIDNIGHT DEAL
        MidnightDeal1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Midnight Deal", 450.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CART BUTTON
        Page1Panel.add(CartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 70));
        CartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });

        //FAMILY FESTIVAL
        Page1Panel.add(FamilyFestival1Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 490, 170, 50));
        FamilyFestival1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Family Festival",1890.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        Page2Panel.setBackground(new java.awt.Color(41, 38, 38));
        Page2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        URL Page2ImageURL = LalaMusaFriedChicken.class.getResource("/LFC Page 2.png");
        Page2Background.setIcon(new javax.swing.ImageIcon(Page2ImageURL));
        Page2Panel.add(Page2Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        //VALUE BUCKET
        Page2Panel.add(ValueBucketButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 150, 40));
        ValueBucketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Value Bucket",1750.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        //CHECKOUT BUTTON
        Page2Panel.add(CheckoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 530, 300, 60));
        CheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                checkoutPage.setVisible(true);
                checkoutPage.displayCartItems();
            }
        });
        //BACK BUTTON
        Page2Panel.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 60));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Confirmation for going back
                int option = JOptionPane.showOptionDialog(null, "You have already selected items from Lala Musa Fried Chicken. Going back will clear your cart, are you sure you want to proceed?", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{"Yes", "No"}, "No");
                //If user selects 'yes', Open restaurants page and clear cart to allow orders from one restaurant only
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false); //Hide the CheezyYes page
                    RestaurantsPage restaurantsPage = new RestaurantsPage();
                    restaurantsPage.setVisible(true); //Display the restaurants page
                    checkoutPage.clearCart(); //Clear cart
                }
            }
        });
        //XTREME DUO BOX
        Page2Panel.add(XtremeDuoBoxButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, 150, 40));
        XtremeDuoBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Xtreme Duo Box",1390.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        //CRISPY DUO BOX
        Page2Panel.add(CrispyDuoBoxButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 150, 40));
        CrispyDuoBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Crispy Duo Box",1150.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });
        //BONELESS MEAL BOX
        Page2Panel.add(BonelessMealBoxButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 150, 40));
        BonelessMealBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutPage.addItemToCart("Boneless Meal Box",630.0,1);
                CartQuantityLabel.setText(checkoutPage.getTotalQuantity());
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Page1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Page2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(Page1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Page2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(MainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    public RoundedLabel getCartQuantityLabel() {
        return CartQuantityLabel;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JButton BonelessMealBoxButton;
    private javax.swing.JButton CartButton;
    private javax.swing.JButton CheckoutButton;
    private javax.swing.JButton CrispyDuoBoxButton;
    private javax.swing.JButton FamilyFestival1Button;
    private javax.swing.JButton HotWingsButton;
    private javax.swing.JButton KrunchComboButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton MidnightDeal1Button;
    private javax.swing.JButton MightyZingerButton;
    private javax.swing.JLabel Page1Background;
    private javax.swing.JPanel Page1Panel;
    private javax.swing.JLabel Page2Background;
    private javax.swing.JPanel Page2Panel;
    private javax.swing.JButton ValueBucketButton;
    private javax.swing.JButton XtremeDuoBoxButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//ACCOUNTS PAGE - EDIT PERSONAL INFO + SEE VOUCHERS
class AccountPage extends javax.swing.JFrame {

    String[] LoggedInUser = PanelLoginAndRegister.LoggedInUser;
    private MyTextField UserTxt;
    private MyTextField EmailTxt;
    private MyTextField PhoneTxt;
    private JComboBox LocationBox;

    public AccountPage() {
        initComponents();
        setSize(1070,600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        GreetingLabel = new javax.swing.JLabel();
        ImHungryButton = new javax.swing.JButton();
        LocationDropdown = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        VoucherTextArea = new javax.swing.JTextArea();
        Background = new javax.swing.JLabel();
        SaveInfoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Greeting label
        GreetingLabel.setFont(new java.awt.Font("PingFang TC", 1, 24)); // NOI18N
        GreetingLabel.setForeground(new java.awt.Color(0, 0, 0));
        GreetingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FileReaderAndDisplayer Extract = new FileReaderAndDisplayer();
        String fullName = PanelLoginAndRegister.LoggedInUser[0];
        String[] nameParts = fullName.split(" ");
        String firstName = nameParts[0];
        GreetingLabel.setText("Hello, " + firstName + "!");
        getContentPane().add(GreetingLabel);
        GreetingLabel.setBounds(790, 270, 280, 60);

        //Email Text Field
        MyTextField EmailTextField = new MyTextField();
        URL EmailImageURL = AccountPage.class.getResource("/icon/mail.png");
        ImageIcon MailIcon = new ImageIcon(EmailImageURL);
        EmailTextField.setPrefixIcon(MailIcon);
        EmailTextField.setHint("Email");
        getContentPane().add(EmailTextField);
        EmailTextField.setBounds(60, 270, 670, 30);
        EmailTxt = EmailTextField;

        //Phone Text Field
        MyTextField PhoneTextField = new MyTextField();
        URL PhoneImageURL = AccountPage.class.getResource("/icon/phone.png");
        ImageIcon PhoneIcon = new ImageIcon(PhoneImageURL);
        PhoneTextField.setPrefixIcon(PhoneIcon);
        PhoneTextField.setHint("Phone Number");
        getContentPane().add(PhoneTextField);
        PhoneTextField.setBounds(60, 310, 670, 30);
        PhoneTxt = PhoneTextField;

        //Username Text Field
        MyTextField UsernameTextField = new MyTextField();
        URL UserImageURL = AccountPage.class.getResource("/icon/user.png");
        ImageIcon UserIcon = new ImageIcon(UserImageURL);
        UsernameTextField.setPrefixIcon(UserIcon);
        UsernameTextField.setHint("Name");
        getContentPane().add(UsernameTextField);
        UsernameTextField.setBounds(60, 230, 670, 30);
        UserTxt = UsernameTextField;

        //Location Dropdown
        LocationDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islamabad", "Lahore", "Karachi" }));
        getContentPane().add(LocationDropdown);
        LocationDropdown.setBounds(60, 360, 580, 23);
        LocationBox = LocationDropdown;

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");

        VoucherTextArea.setColumns(20);
        VoucherTextArea.setRows(5);
        VoucherTextArea.setEditable(false);
        jScrollPane1.setViewportView(VoucherTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 490, 720, 60);



        SaveInfoButton.setBackground(new java.awt.Color(255, 231, 129));
        SaveInfoButton.setFont(new java.awt.Font("Dialog", 1, 14));
        SaveInfoButton.setForeground(new java.awt.Color(41, 38, 38));
        SaveInfoButton.setOpaque(true);
        SaveInfoButton.setText("Save");
        SaveInfoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(SaveInfoButton);
        SaveInfoButton.setBounds(660, 360, 70, 20);
        SaveInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newEmail = EmailTxt.getText();
                String newPhone = PhoneTxt.getText();

                if (isEmailOrPhoneAlreadyExists(newEmail, newPhone)) {
                    JOptionPane.showMessageDialog(null, "Email or phone number already exists. Changes not saved.");
                } else {
                    saveChanges();
                }
            }
        });


        URL BackgroundImage = AccountPage.class.getResource("/Account Page.png");
        Background.setIcon(new javax.swing.ImageIcon(BackgroundImage)); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1070, 600);

        getContentPane().add(ImHungryButton);
        ImHungryButton.setBounds(840, 494, 210, 50);
        ImHungryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });

        pack();
    }// </editor-fold>


    private static final String USER_DATA_FILE = "Not Foodpanda Users.txt";
    private static final int NAME_INDEX = 0;
    private static final int EMAIL_INDEX = 1;
    private static final int PHONE_INDEX = 2;
    private static final int LOCATION_INDEX = 3;

    private void updateField(int fieldIndex, String newValue) {
        try {
            // Get the resource file path
            String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Not Foodpanda Users.txt";

            // Read the lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            boolean emailOrPhoneExists = false;

            for (String line : lines) {
                String[] fields = line.split(", ");

                if (fields.length > fieldIndex && (fieldIndex == EMAIL_INDEX || fieldIndex == PHONE_INDEX)
                        && fields[fieldIndex].equals(newValue)) {
                    emailOrPhoneExists = true;
                    break;
                }
            }

            if (emailOrPhoneExists) {
                JOptionPane.showMessageDialog(null, "Email or phone number already exists. Changes not saved.");
            } else {
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] fields = line.split(", ");

                    if (fields.length > fieldIndex && fields[fieldIndex].equals(LoggedInUser[fieldIndex])) {
                        fields[fieldIndex] = newValue;
                        lines.set(i, String.join(", ", fields));
                        break;
                    }
                }

                // Write the updated lines back to the file
                Files.write(Paths.get(filePath), lines);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error updating information!");
            throw new RuntimeException(ex);
        }
    }

    private void saveChanges() {
        // Update name if not empty
        String newName = UserTxt.getText();
        if (!newName.isEmpty()) {
            updateField(NAME_INDEX, newName);
        }

        // Update email if not empty
        String newEmail = EmailTxt.getText();
        if (!newEmail.isEmpty()) {
            updateField(EMAIL_INDEX, newEmail);
        }

        // Update phone if not empty
        String newPhone = PhoneTxt.getText();
        if (!newPhone.isEmpty()) {
            updateField(PHONE_INDEX, newPhone);
        }

        // Update location
        String newLocation = (String) LocationBox.getSelectedItem();
        if (newLocation != null) {
            updateField(LOCATION_INDEX, newLocation);
        }

        // Display success message
        JOptionPane.showMessageDialog(null, "Changes saved successfully!");
    }

    private boolean isEmailOrPhoneAlreadyExists(String newEmail, String newPhone) {
        try {
            // Get the resource file path
            String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Not Foodpanda Users.txt";

            // Read the lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                String[] fields = line.split(", ");

                if ((fields.length > EMAIL_INDEX && fields[EMAIL_INDEX].equals(newEmail))
                        || (fields.length > PHONE_INDEX && fields[PHONE_INDEX].equals(newPhone))) {
                    return true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error checking information!");
            throw new RuntimeException(ex);
        }
        return false;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton ImHungryButton;
    private javax.swing.JComboBox<String> LocationDropdown;
    private javax.swing.JButton SaveInfoButton;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel GreetingLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea VoucherTextArea;
    // End of variables declaration
}

//aboutus page
class aboutuspage extends javax.swing.JFrame {

    public aboutuspage() {
        initComponents();
        setSize(1070,630);
        setLocationRelativeTo(null);
        setResizable(false);
    }

   //Generated Code
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/About Us.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1100, 600);
        getContentPane().add(BackButton);
        BackButton.setBounds(60, 490, 210, 80);

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}

//CHECKOUT PAGE - EDIT CART, BILLING, PAYMENT
class CheckoutPage extends javax.swing.JFrame {

    String[] LoggedInUser = PanelLoginAndRegister.LoggedInUser;
    private static CheckoutPage instance; // Singleton instance
    private DefaultTableModel tableModel;
    SelectedRestaurant selectedRestaurant = SelectedRestaurant.getInstance();
    double subtotal;
    double tax;
    double platformFee = 10.0;
    double deliveryCharges;
    double GrandTotal;
    double voucherValue = 0.0;
    String OrderNumber;

    public CheckoutPage() {
        initComponents();
        setSize(1070, 630);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create a new DefaultTableModel instance
        tableModel = new DefaultTableModel();

        // Create the table model with column names
        String[] columnNames = {"Sr. No", "Item", "Price", "Quantity", "Action"};
        tableModel.setColumnIdentifiers(columnNames);

        // Set the model for the CartTable
        CartTable.setModel(tableModel);

        //update delivery charges
        updateDeliveryCharges();

        //Update grand total
        updateGrandTotal();
    }

    int DefaultHeight = 50;
    private List<CartItems> cartItems = new ArrayList<>();

    //ADD ITEM TO CART + STORE IN ARRAY LIST + UPDATE SUBTOTAL
    public void addItemToCart(String itemName, double price, int quantity) {
        DefaultTableModel model = (DefaultTableModel) CartTable.getModel();

        // Check if the item already exists in the cart
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String existingItemName = (String) model.getValueAt(i, 1);
            if (existingItemName.equals(itemName)) {
                // Update the quantity of the existing item
                int existingQuantity = (int) model.getValueAt(i, 3);
                int updatedQuantity = existingQuantity + quantity;
                model.setValueAt(updatedQuantity, i, 3);

                // Update the quantity in the cartItems list
                CartItems existingCartItem = cartItems.get(i);
                existingCartItem.setQuantity(updatedQuantity);

                // Update the subtotal
                updateSubtotal();
                updateGrandTotal();
                updateGrandTotal();

                return; // Exit the method after updating the quantity
            }
        }
        // If the item doesn't exist in the cart, add a new row
        int srNo = model.getRowCount() + 1; // Calculate the serial number
        if (srNo == 1) {
            jScrollPane1.setBounds(30, 110, 690, DefaultHeight);
        } else {
            DefaultHeight = DefaultHeight + 30;
            jScrollPane1.setBounds(30, 110, 690, DefaultHeight);
        }
        model.addRow(new Object[]{srNo, itemName, price, quantity, null});

        // Store the item in the cartItems list
        cartItems.add(new CartItems(itemName, price, quantity));

        // Update the subtotal
        updateSubtotal();
        updateGrandTotal();
        updateGrandTotal();
    }

    //CLEAR CART METHOD
    public void clearCart() {
        cartItems.clear();
        DefaultTableModel model = (DefaultTableModel) CartTable.getModel();
        model.setRowCount(0);
    }

    //DISPLAY CART ITEMS
    public void displayCartItems() {
        DefaultTableModel model = (DefaultTableModel) CartTable.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        for (int i = 0; i < cartItems.size(); i++) {
            CartItems item = cartItems.get(i);
            int srNo = i + 1;
            String itemName = item.getItemName();
            double price = item.getPrice();
            int quantity = item.getQuantity();
            model.addRow(new Object[]{srNo, itemName, price, quantity, null});
            if (i == 0) {
                jScrollPane1.setBounds(30, 110, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 110, 690, defaultHeight);
            }
        }
    }
    //GET TOTAL QUANTITY TO DISPLAY ON THE CART QUANTITY LABEL
    public String getTotalQuantity() {
        int totalQuantity = 0;
        for (CartItems cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
        return String.valueOf(totalQuantity);
    }

    //UPDATE SUBTOTAL + TAX
    public double updateSubtotal() {
         subtotal = 0.0;
        // Calculate subtotal by iterating over the cartItems list
        for (CartItems cartItem : cartItems) {
            double itemPrice = cartItem.getPrice();
            int itemQuantity = cartItem.getQuantity();
            subtotal += itemPrice * itemQuantity;
        }
        // Update the SubtotalTextField with the new subtotal value
        SubtotalTextField.setText(String.valueOf(subtotal));
        double tax = 0.15 * subtotal;
        TaxTextField.setText(String.valueOf(tax));
        return subtotal;
    }

    //UPDATE DELIVERY CHARGES ACCORDING TO RESTAURANT
    public double updateDeliveryCharges() {
        SelectedRestaurant selectedRestaurant = SelectedRestaurant.getInstance();
        selectedRestaurant.getSelectedRestaurant();
        if (selectedRestaurant.equals("CheezyYes"))
        {
            deliveryCharges = 70.0;
        }
        else if (selectedRestaurant.equals("McDanish"))
        {
            deliveryCharges = 30.0;
        }
        else if (selectedRestaurant.equals("LFC"))
        {
            deliveryCharges = 50.0;
        }
        else if (selectedRestaurant.equals("Demonic Pizza"))
        {
            deliveryCharges = 100.0;
        }
        DeliveryChargesTextField.setText(String.valueOf(deliveryCharges));
        return deliveryCharges;
    }

    //UPDATE GRAND TOTAL
    public void updateGrandTotal(){
        double Subtotal = updateSubtotal();
        double tax = 0.15 * Subtotal;
        double delivery = updateDeliveryCharges();
        double platformFee = 10.0;
        GrandTotal = Subtotal + tax + delivery + platformFee;
        GrandTotalTextField.setText(String.valueOf(GrandTotal));
    }

    // GET CART ITEMS
    public List<CartItems> getCartItems() {
        return cartItems;
    }

    //Generate Order Number
    private Random random = new Random();
    public String generateOrderNumber() {
        int intOrderNumber = random.nextInt(90000) + 10000; // Generate a random number between 10000 and 99999
        String orderNumber = String.valueOf(intOrderNumber);
        return orderNumber;
    }

    public void saveOrderToFile() {
        String[] loggedInUserEmail = PanelLoginAndRegister.LoggedInUser;
        String userEmail = loggedInUserEmail[1];
        SelectedRestaurant selectedRestaurant = SelectedRestaurant.getInstance();
        String restaurant = selectedRestaurant.getSelectedRestaurant();
        List<CartItems> cartItems = getCartItems();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt";

        // Logic for saving the order to the file
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the order details to the file
            bufferedWriter.write(userEmail + ", " + restaurant + ", " + formatItems(cartItems) + ", " + GrandTotal + ", " + OrderNumber);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatItems(List<CartItems> cartItems) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cartItems.size(); i++) {
            CartItems item = cartItems.get(i);
            builder.append(item.getItemName()).append("%").append(item.getPrice()).append("%").append(item.getQuantity());
            if (i < cartItems.size() - 1) {
                builder.append("|");
            }
        }
        return builder.toString();
    }

    //SINGLETON
    public static CheckoutPage getInstance() {
        if (instance == null) {
            instance = new CheckoutPage();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        CartTable = new javax.swing.JTable();
        ReceiptPanel = new javax.swing.JPanel();
        RecipetHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PlatformFeeLabel = new javax.swing.JLabel();
        SubtotalLabel = new javax.swing.JLabel();
        GrandTotalLabel = new javax.swing.JLabel();
        TaxLabel = new javax.swing.JLabel();
        VouchersLabel = new javax.swing.JLabel();
        GrandTotalTextField = new javax.swing.JTextField();
        SubtotalTextField = new javax.swing.JTextField();
        TaxTextField = new javax.swing.JTextField();
        PlatformFeeTextField = new javax.swing.JTextField();
        DeliveryChargesTextField = new javax.swing.JTextField();
        DeliveryChargesLabel1 = new javax.swing.JLabel();
        VouchersDropDown = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        DebitCardButton = new javax.swing.JButton();
        CashOnDeliveryButton = new javax.swing.JButton();
        GoBackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        String[] columnNames = { "Sr. No", "Item", "Price", "Quantity", "Action" };
        Object[][] data = { { null, null, null, null, null } };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }
        };
        CartTable.setDefaultEditor(Object.class, null);
        CartTable.setModel(model);
        CartTable.setRowHeight(30);
        JTableHeader header = CartTable.getTableHeader();
        header.setBackground(new Color(255, 255, 153));
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) CartTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jScrollPane1.setViewportView(CartTable);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 690, 50);

        ReceiptPanel.setLayout(null);

        RecipetHeader.setBackground(new java.awt.Color(255, 255, 153));

        //Receipt Label
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Receipt");
        RecipetHeader.add(jLabel2);

        ReceiptPanel.add(RecipetHeader);
        RecipetHeader.setBounds(0, 0, 310, 40);

        //Platform Fee Label
        PlatformFeeLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        PlatformFeeLabel.setText("Platform Fee");
        ReceiptPanel.add(PlatformFeeLabel);
        PlatformFeeLabel.setBounds(10, 140, 160, 22);

        //Subtotal Label
        SubtotalLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        SubtotalLabel.setText("Subtotal");
        ReceiptPanel.add(SubtotalLabel);
        SubtotalLabel.setBounds(10, 60, 160, 22);

        //Grand Total Label
        GrandTotalLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        GrandTotalLabel.setText("Grand Total");
        ReceiptPanel.add(GrandTotalLabel);
        GrandTotalLabel.setBounds(10, 260, 170, 30);

        //Tax Label
        TaxLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TaxLabel.setText("Tax");
        ReceiptPanel.add(TaxLabel);
        TaxLabel.setBounds(10, 100, 160, 22);

        //Vouchers Label
        VouchersLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        VouchersLabel.setText("Vouchers");
        ReceiptPanel.add(VouchersLabel);
        VouchersLabel.setBounds(10, 220, 90, 22);

        //GRAND TOTAL TEXT FIELD
        GrandTotalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        GrandTotalTextField.setEditable(false);
        ReceiptPanel.add(GrandTotalTextField);
        GrandTotalTextField.setBounds(180, 260, 120, 30);

        //SUBTOTAL TEXT FIELD
        SubtotalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SubtotalTextField.setText(String.valueOf(subtotal));
        SubtotalTextField.setEditable(false);
        ReceiptPanel.add(SubtotalTextField);
        SubtotalTextField.setBounds(180, 60, 120, 20);

        //TAX TEXT FIELD - 15% OF SUBTOTAL
        TaxTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ReceiptPanel.add(TaxTextField);
        TaxTextField.setEditable(false);
        TaxTextField.setBounds(180, 100, 120, 20);

        //PLATFORM FEE - FIXED AT 10RS
        PlatformFeeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ReceiptPanel.add(PlatformFeeTextField);
        PlatformFeeTextField.setEditable(false);
        PlatformFeeTextField.setText(String.valueOf(platformFee));
        PlatformFeeTextField.setBounds(180, 140, 120, 20);

        //Delivery Charges - Vary Restaurant to Restaurant
        DeliveryChargesTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DeliveryChargesTextField.setToolTipText("");
        ReceiptPanel.add(DeliveryChargesTextField);
        DeliveryChargesTextField.setEditable(false);
        DeliveryChargesTextField.setBounds(180, 180, 120, 20);
        DeliveryChargesLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        DeliveryChargesLabel1.setText("Delivery Charges");
        ReceiptPanel.add(DeliveryChargesLabel1);
        DeliveryChargesLabel1.setBounds(10, 180, 170, 22);

        //Vouchers Combobox
        VouchersDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE"}));
        ReceiptPanel.add(VouchersDropDown);
        VouchersDropDown.setBounds(111, 220, 190, 23);

        getContentPane().add(ReceiptPanel);
        ReceiptPanel.setBounds(750, 110, 310, 310);

        //Go Back Button - Go Back to Selected Restaurant
        GoBackButton.setBackground(new java.awt.Color(255, 51, 51));
        GoBackButton.setFont(new java.awt.Font("Dialog", 1, 18));
        GoBackButton.setForeground(new java.awt.Color(0, 0, 0));
        GoBackButton.setOpaque(true);
        GoBackButton.setText("Go Back");
        GoBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        getContentPane().add(GoBackButton);
        GoBackButton.setBounds(939, 20, 110, 50);
        GoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.out.println("Go Back Button Pressed");
                if (selectedRestaurant.getSelectedRestaurant().equals("CheezyYes")){
                    CheezyYes cheezyYesScreen = new CheezyYes();
                    cheezyYesScreen.setVisible(true);
                    cheezyYesScreen.getCartQuantityLabel().setText(getTotalQuantity());

                }
                else if (selectedRestaurant.getSelectedRestaurant().equals("McDanish")){
                    McDanish McDanishSceen = new McDanish();
                    McDanishSceen.setVisible(true);
                    McDanishSceen.getCartQuantityLabel().setText(getTotalQuantity());
                }
                else if (selectedRestaurant.getSelectedRestaurant().equals("LFC")){
                    LalaMusaFriedChicken LFCScreen = new LalaMusaFriedChicken();
                    LFCScreen.setVisible(true);
                    LFCScreen.getCartQuantityLabel().setText(getTotalQuantity());
                }
                else if (selectedRestaurant.getSelectedRestaurant().equals("Demonic Pizza")){
                    DemonicPizza Demonicpizzascreen = new DemonicPizza();
                    Demonicpizzascreen.setVisible(true);
                    Demonicpizzascreen.getCartQuantityLabel().setText(getTotalQuantity());
                }
            }
        });

        //Background od Checkout Page
        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/Checkout Page.png"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(3, 2, 1070, 600);

        //DEBIT CARD BUTTON
        getContentPane().add(DebitCardButton);
        DebitCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderNumber = generateOrderNumber();
                saveOrderToFile();
                setVisible(false);
                DebitCardPaymentPage debitCardPaymentPage = new DebitCardPaymentPage();
                debitCardPaymentPage.setVisible(true);
            }
        });
        DebitCardButton.setBounds(410, 510, 320, 60);

        //CASH ON DELIVERY BUTTON
        getContentPane().add(CashOnDeliveryButton);
        CashOnDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderNumber = generateOrderNumber();
                saveOrderToFile();
                setVisible(false);
                ThanksForOrderingPage thanksForOrderingPage = new ThanksForOrderingPage();
                thanksForOrderingPage.setVisible(true);
            }
        });
        CashOnDeliveryButton.setBounds(9, 514, 320, 60);
        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JTable CartTable;
    private javax.swing.JButton CashOnDeliveryButton;
    private javax.swing.JButton DebitCardButton;
    private javax.swing.JLabel DeliveryChargesLabel1;
    private javax.swing.JButton GoBackButton;
    private javax.swing.JTextField DeliveryChargesTextField;
    private javax.swing.JLabel GrandTotalLabel;
    private javax.swing.JTextField GrandTotalTextField;
    private javax.swing.JLabel PlatformFeeLabel;
    private javax.swing.JTextField PlatformFeeTextField;
    private javax.swing.JPanel ReceiptPanel;
    private javax.swing.JPanel RecipetHeader;
    private javax.swing.JLabel SubtotalLabel;
    private javax.swing.JTextField SubtotalTextField;
    private javax.swing.JLabel TaxLabel;
    private javax.swing.JTextField TaxTextField;
    private javax.swing.JComboBox<String> VouchersDropDown;
    private javax.swing.JLabel VouchersLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}

//CART ITEMS CLASS - STORE ORDER INFO
class CartItems {
    private String itemName;
    private double price;
    private int quantity;

    public CartItems(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}

//SELECTED RESTAURANT - SAVES INFO ABOUT THE RESTAURANT USER ENTERS
class SelectedRestaurant {
    private String selectedRestaurant;
    private static SelectedRestaurant instance;

    private SelectedRestaurant() {
        // private constructor to enforce singleton pattern
    }

    public static SelectedRestaurant getInstance() {
        if (instance == null) {
            instance = new SelectedRestaurant();
        }
        return instance;
    }

    public void setSelectedRestaurant(String selectedRestaurant) {
        this.selectedRestaurant = selectedRestaurant;
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }
}

//SEARCH FOR FOOD ITEMS
class ItemSearch {

    private List<Item> items;

    public ItemSearch() {
        items = new ArrayList<>();
    }

    public void readItemsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(", ");
                if (fields.length == 4) {
                    String restaurant = fields[0];
                    String itemName = fields[1];
                    double price = Double.parseDouble(fields[2]);
                    String keywords = fields[3];
                    items.add(new Item(restaurant, itemName, price, keywords));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> searchItems(String searchQuery) {
        List<Item> searchResults = new ArrayList<>();
        for (Item item : items) {
            if (item.matchesSearchQuery(searchQuery)) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }

    public static void main(String[] args) {
        ItemSearch itemSearch = new ItemSearch();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/food items.txt";
        itemSearch.readItemsFromFile(filePath);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the search query: ");
        String searchQuery = scanner.nextLine();

        List<Item> searchResults = itemSearch.searchItems(searchQuery);

        if (searchResults.isEmpty()) {
            System.out.println("No items found matching the search query.");
        } else {
            System.out.println("Search Results:");
            for (Item item : searchResults) {
                System.out.println(item);
            }
        }
    }
}
class Item {
    private String restaurant;
    private String itemName;
    private double price;
    private String keywords;

    public Item(String restaurant, String itemName, double price, String keywords) {
        this.restaurant = restaurant;
        this.itemName = itemName;
        this.price = price;
        this.keywords = keywords;
    }

    public boolean matchesSearchQuery(String searchQuery) {
        return restaurant.toLowerCase().contains(searchQuery.toLowerCase())
                || itemName.toLowerCase().contains(searchQuery.toLowerCase())
                || keywords.toLowerCase().contains(searchQuery.toLowerCase());
    }

    @Override
    public String toString() {
        return "Restaurant: " + restaurant + "\n"
                + "Item Name: " + itemName + "\n"
                + "Price: " + price + "\n"
                + "Keywords: " + keywords + "\n";
    }

    public String getRestaurant() {
        return restaurant;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }
}
class SearchResultsTable extends JFrame {
    private DefaultTableModel tableModel;

    public SearchResultsTable() {
        initComponents();
        setSize(1070, 600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel2 = new JLabel();
        BrowseRestaurantsButton = new JButton();
        GoButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{{null, null, null}},
                new String[]{"Restaurant", "Item Name", "Price"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 100, 730, 50);

        jLabel2.setIcon(new ImageIcon("/Users/amal/Downloads/SearchResults.png"));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-2, -6, 1070, 610);

        //Browse Restaurants Button
        getContentPane().add(BrowseRestaurantsButton);
        BrowseRestaurantsButton.setBounds(779, 184, 270, 60);
        BrowseRestaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });

        //Go Button
        getContentPane().add(GoButton);
        GoButton.setBounds(780, 404, 280, 60);
        // Go button
        getContentPane().add(GoButton);
        GoButton.setBounds(780, 404, 280, 60);
        GoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    String restaurant = (String) jTable1.getValueAt(selectedRow, 0);
                    if (restaurant.equals("LFC")) {
                        setVisible(false);
                       LalaMusaFriedChicken LFC = new LalaMusaFriedChicken();
                       LFC.setVisible(true);
                    } else if (restaurant.equals("McDanish")) {
                        setVisible(false);
                        McDanish mcDanish = new McDanish();
                        mcDanish.setVisible(true);
                    } else if (restaurant.equals("CheezyYes")) {
                        setVisible(false);
                        CheezyYes cheezyYes = new CheezyYes();
                        cheezyYes.setVisible(true);
                    }
                    else if (restaurant.equals("Demonic Pizza")){
                        setVisible(false);
                        DemonicPizza demonicPizza = new DemonicPizza();
                        demonicPizza.setVisible(true);
                    }
                }
            }
        });
        pack();
    }

    public void displaySearchResults(List<Item> searchResults) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        for (int i = 0; i < searchResults.size(); i++) {
            Item item = searchResults.get(i);
            String restaurant = item.getRestaurant();
            String itemName = item.getItemName();
            double price = item.getPrice();
            model.addRow(new Object[]{restaurant, itemName, price});
            if (i == 0) {
                jScrollPane1.setBounds(30, 110, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 110, 690, defaultHeight);
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BrowseRestaurantsButton;
    private javax.swing.JButton GoButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}

//Admin Panel page
class adminpanel extends javax.swing.JFrame {

    public adminpanel() {
        initComponents();
        setSize(1070,610);
        setResizable(false);
        setLocationRelativeTo(null);
    }

   //"Generated Code"
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        seeRidersButton = new javax.swing.JButton();
        orderHistoryButton = new javax.swing.JButton();
        manageComplaintsButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                WelcomePage WelcomePage = new WelcomePage();
                WelcomePage.setVisible(true);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Admin Panel.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-3, 6, 1080, 590);
        //See rider button
        getContentPane().add(seeRidersButton);
        seeRidersButton.setBounds(40, 230, 260, 60);
        seeRidersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RiderInfo Adm = new RiderInfo();
                Adm.setVisible(true);
            }
        });
        //Order History Button
        getContentPane().add(orderHistoryButton);
        orderHistoryButton.setBounds(390, 230, 290, 50);
        orderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdminPastOrders pastOrders = new AdminPastOrders();
                pastOrders.setVisible(true);
            }
        });
        getContentPane().add(manageComplaintsButton);
        manageComplaintsButton.setBounds(190, 340, 350, 50);
        getContentPane().add(BackButton);
        BackButton.setBounds(270, 440, 200, 70);

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton manageComplaintsButton;
    private javax.swing.JButton orderHistoryButton;
    private javax.swing.JButton seeRidersButton;
    // End of variables declaration
}

//THANKS FOR ORDERING CLASS - LEADS BACK TO RESTAURANTS AND ORDER TRACKING
class ThanksForOrderingPage extends javax.swing.JFrame {

    public ThanksForOrderingPage() {
        initComponents();
        setSize(1070,602);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TrackOrderButton = new javax.swing.JButton();
        BrowseRestaurantsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/Thanks.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -230, 1920, 1080);
        getContentPane().add(TrackOrderButton);
        TrackOrderButton.setBounds(589, 124, 370, 60);
        TrackOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TRACKING1 tracking = new TRACKING1();
                tracking.setVisible(true);
            }
        });

        //BROWSE RESTAURANTS BUTTON
        getContentPane().add(BrowseRestaurantsButton);
        BrowseRestaurantsButton.setBounds(590,44,370,60);
        BrowseRestaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
                //clear cart
                CheckoutPage checkoutPage = CheckoutPage.getInstance();
                checkoutPage.clearCart();
            }
        });
        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BrowseRestaurantsButton;
    private javax.swing.JButton TrackOrderButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}

//DEBIT/CREDIT CARD PAYMENT PAGE
class DebitCardPaymentPage extends javax.swing.JFrame {

    public DebitCardPaymentPage() {
        initComponents();
        setSize(1070,620);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        ExpiryDateLabel = new javax.swing.JLabel();
        CardNumberLabel = new javax.swing.JLabel();
        CVVLabel = new javax.swing.JLabel();
        CardholderNameLabel = new javax.swing.JLabel();
        CardholderNameTextField = new javax.swing.JTextField();
        CardNumberTextField = new javax.swing.JTextField();
        YearDropdown = new javax.swing.JComboBox<>();
        MonthDropdown = new javax.swing.JComboBox<>();
        CVVPasswordField = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        //Expiry Label
        ExpiryDateLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        ExpiryDateLabel.setText("Expiry Date");
        ExpiryDateLabel.setForeground(new Color(255,255,255));
        getContentPane().add(ExpiryDateLabel);
        ExpiryDateLabel.setBounds(40, 320, 150, 40);

        //Card Number label
        CardNumberLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        CardNumberLabel.setText("Card Number");
        CardNumberLabel.setForeground(new Color(255,255,255));
        getContentPane().add(CardNumberLabel);
        CardNumberLabel.setBounds(40, 150, 170, 40);

        //CVV Label
        CVVLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        CVVLabel.setText("CVV");
        CVVLabel.setForeground(new Color(255,255,255));
        getContentPane().add(CVVLabel);
        CVVLabel.setBounds(420, 320, 60, 40);

        //Cardholder Name Label
        CardholderNameLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        CardholderNameLabel.setText("Cardholder Name");
        CardholderNameLabel.setForeground(new Color(255,255,255));
        getContentPane().add(CardholderNameLabel);
        CardholderNameLabel.setBounds(40, 230, 330, 40);

        //Cardholder Text Field
        getContentPane().add(CardholderNameTextField);
        CardholderNameTextField.setBounds(270, 230, 390, 40);

        //Card Number Text Field
        getContentPane().add(CardNumberTextField);
        CardNumberTextField.setBounds(270, 150, 390, 40);

        //Year Dropdown
        YearDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028" }));
        getContentPane().add(YearDropdown);
        YearDropdown.setBounds(300, 330, 90, 30);

        //Month Dropdown
        MonthDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        getContentPane().add(MonthDropdown);
        MonthDropdown.setBounds(201, 330, 100, 30);

        //CVV Text Field
        getContentPane().add(CVVPasswordField);
        CVVPasswordField.setBounds(480, 320, 180, 40);

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        //Proceed With Payment Button
        jButton2.setText("Proceed with Payment");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        getContentPane().add(jButton2);
        jButton2.setBounds(420, 430, 230, 50);
        jButton2.setOpaque(true);
        final boolean[] allLettersASCII = {false}; // Array to hold the boolean value
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardholderName = CardholderNameTextField.getText().trim();
               // allLettersASCII[0] = cardholderName.matches("\\p{Alpha}*");
//                if (!allLettersASCII[0]) {
//                    JOptionPane.showMessageDialog(null, "Cardholder name should contain only letters.");
//                    return;
//                }
                if (YearDropdown.getSelectedItem().equals("2023") && MonthDropdown.getSelectedItem().equals("January") || YearDropdown.getSelectedItem().equals("2023") && MonthDropdown.getSelectedItem().equals("February") || YearDropdown.getSelectedItem().equals("2023") && MonthDropdown.getSelectedItem().equals("March") || YearDropdown.getSelectedItem().equals("2023") && MonthDropdown.getSelectedItem().equals("May")) {
                    JOptionPane.showMessageDialog(null,"Card is expired! Please try again!");
                    return;
                }
                String cardNumber = CardNumberTextField.getText().trim();
                if (!cardNumber.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Card Number should contain numbers only.");
                    return;
                }
                if (cardNumber.length() != 16) {
                    JOptionPane.showMessageDialog(null, "Card Number should be 16 digits long.");
                    return;
                }
                char[] cvv = CVVPasswordField.getPassword();
                String strCvv = new String(cvv);
                if (!strCvv.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null,"CVV should be numeric");
                    return;
                }
                if (strCvv.length() != 3)
                {
                    JOptionPane.showMessageDialog(null,"CVV should be 3 digits long.");
                    return;
                }

                // Rest of your code
                setVisible(false);
                ThanksForOrderingPage thanksForOrderingPage = new ThanksForOrderingPage();
                thanksForOrderingPage.setVisible(true);
                CheckoutPage checkoutPage = CheckoutPage.getInstance();
                checkoutPage.clearCart();
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        //Go Back Button
        jButton3.setText("Go Back");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        getContentPane().add(jButton3);
        jButton3.setBounds(40, 430, 230, 50);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CheckoutPage checkoutPage = CheckoutPage.getInstance();
                checkoutPage.setVisible(true);
            }
        });

        Background.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/DebitCardPage.png")); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(-2, -6, 1070, 610);

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JLabel Background;
    private javax.swing.JLabel CVVLabel;
    private javax.swing.JPasswordField CVVPasswordField;
    private javax.swing.JLabel CardNumberLabel;
    private javax.swing.JTextField CardNumberTextField;
    private javax.swing.JLabel CardholderNameLabel;
    private javax.swing.JTextField CardholderNameTextField;
    private javax.swing.JLabel ExpiryDateLabel;
    private javax.swing.JComboBox<String> MonthDropdown;
    private javax.swing.JComboBox<String> YearDropdown;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration
}

//ORDER TRACKING CLASS
class TRACKING1 extends javax.swing.JFrame {

    String[] LoggedInUser = PanelLoginAndRegister.LoggedInUser;
    String Location = LoggedInUser[3];
    private JButton[] buttons; // Array to store the buttons
    private Timer timer;
    private int counter = 0;
    String email;
    String restaurant;
    String bill;
    String orderNo;

    public TRACKING1() {
        initComponents();
        initializeButtons();
        extractOrderInfo(); // Call the method to populate orderInfo
        startTimer();
        setLocationRelativeTo(null);
    }

    private void initializeButtons() {
        buttons = new JButton[]{btn1, btn2, btn3, btn4};
    }
    // Start the timer to change button colors
    private void startTimer() {
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonColor();
            }
        });
        timer.start();
    }
    // Change the color of buttons alternately to yellow
    private void changeButtonColor() {
        buttons[counter].setBackground(Color.YELLOW);
        buttons[counter].setOpaque(true);
        for (int i = 0; i < buttons.length; i++) {
            if (i != counter) {
                buttons[i].setBackground(new Color(102, 102, 102));
            }
        }
        counter = (counter + 1) % buttons.length;
        if (counter == 0) {
            timer.stop();
        }
    }

    // Extracting order info
    private void extractOrderInfo() {
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                email = fields[0];
                restaurant = fields[1];
                bill = fields[3];
                orderNo = fields[4];

                // Check if the extracted email matches the logged-in user's email
                String UserEmail = LoggedInUser[1];
                if (UserEmail.equals(email)) {
                    // Store the extracted order information in simple variables
                    String extractedEmail = email;
                    String extractedRestaurant = restaurant;
                    String extractedBill = bill;
                    String extractedOrderNo = orderNo;

                    orderfrom.setText(extractedRestaurant);
                    ordernumber.setText(extractedOrderNo);

                    // Break the loop if you only need the first match
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        orderfrom = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        deliveryaddress = new javax.swing.JTextField();
        ordernumber = new javax.swing.JTextField();
        Timesetbox = new javax.swing.JTextField();
        backbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Map.gif"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Track Your Order");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setFont(new java.awt.Font("Cambria", 2, 18)); // NOI18N
        jLabel3.setText("Thankyou !!!");

        jLabel4.setText("The restaurant's driver will be at your place soon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 9, Short.MAX_VALUE))
        );

        btn1.setBackground(new java.awt.Color(102, 102, 102));
        btn1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn1.setText("The restaurant is preparing your food.....");

        btn2.setBackground(new java.awt.Color(102, 102, 102));
        btn2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn2.setText("Your order has been picked up for delivery......");

        btn3.setBackground(new java.awt.Color(102, 102, 102));
        btn3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn3.setText("Order arriving soon......");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(102, 102, 102));
        btn4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn4.setText("Order has been received.......");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Map.gif")); // NOI18N
        jLabel6.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 25)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Order Info:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Your order from:\n");

        orderfrom.setBackground(new java.awt.Color(102, 102, 102));


        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Your order no. :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Delivery address:\n");

        deliveryaddress.setBackground(new java.awt.Color(102, 102, 102));
        deliveryaddress.setText(Location);

        ordernumber.setBackground(new java.awt.Color(102, 102, 102));


        Timesetbox.setBackground(new java.awt.Color(0, 0, 0));
        Timesetbox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SelectedRestaurant selectedRestaurant = SelectedRestaurant.getInstance();
        if (selectedRestaurant.equals("CheezyYes")){
            Timesetbox.setText("25");
        }
        else if (selectedRestaurant.equals("McDanish")){
            Timesetbox.setText("15");
        }
        else if (selectedRestaurant.equals("LFC")){
            Timesetbox.setText("30");
        }
        else{
            Timesetbox.setText("45");
        }


        backbtn.setBackground(new java.awt.Color(255, 255, 0));
        backbtn.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        backbtn.setText("Back");
        backbtn.setOpaque(true);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(207, 207, 207))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(43, 43, 43)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(orderfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(deliveryaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ordernumber, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(Timesetbox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Timesetbox)))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(155, 155, 155)
                                                .addComponent(jLabel7)
                                                .addGap(36, 36, 36)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(orderfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ordernumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(deliveryaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(backbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField Timesetbox;
    private javax.swing.JButton backbtn;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JTextField deliveryaddress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField orderfrom;
    private javax.swing.JTextField ordernumber;
    // End of variables declaration
}

//Getting information regarding the Order from the stored information
 class ReadTextFile {
     public static void main(String[] args) {
         String fileName = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Specify the path and name of your text file

         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
             String line;

             while ((line = br.readLine()) != null) {
                 // Process each line of the file
                 System.out.println(line);
             }
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
 }

 //FOR THE USER INFORMATION
 class UserInfoGet {
     public static void main(String[] args) {
         String fileName = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Specify the path and name of your text file

         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
             String line;

             while ((line = br.readLine()) != null) {
                 // Process each line of the file
                 System.out.println(line);
             }
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
}

//RIDER INFO PAGE
class RiderInfo extends javax.swing.JFrame {

    public RiderInfo() {
        initComponents();
        setSize(1070,610);
        setResizable(false);
        setLocationRelativeTo(null);
        displayOrderInfo(); // Display the extracted order information in the table
    }

    public List<String> ExtractRiderInfo() {
        List<String> riderInfo = new ArrayList<>();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Not Foodpanda Riders.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                String name = fields[0];
                String email = fields[1];
                String phone = fields[2];
                String location = fields[3];

                // Add the extracted email, restaurant, bill, and order number to the list
                riderInfo.add(name);
                riderInfo.add(email);
                riderInfo.add(phone);
                riderInfo.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return riderInfo;
    }

    // Method to display the extracted order information in the table
    public void displayOrderInfo() {
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        List<String> riderInfo = ExtractRiderInfo();

        for (int i = 0; i < riderInfo.size(); i += 4) {
            String name = riderInfo.get(i);
            String email = riderInfo.get(i + 1);
            String phone = riderInfo.get(i + 2);
            String location = riderInfo.get(i + 3);

            model.addRow(new Object[]{name, email, phone, location});

            if (i == 0) {
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name", "Email", "Phone Number", "Location"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setRowHeight(30);
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(1).setResizable(false);
            Table.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 130, 690, 50);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 450, 290, 150);
        jPanel1.setBackground(new Color(42,42,42));

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/RiderInfo.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-4, 0, 1070, 602);

        getContentPane().add(BackButton);
        BackButton.setBounds(790, 354, 270, 70);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                adminpanel AdminPanel = new adminpanel();
                AdminPanel.setVisible(true);
            }
        });

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RiderInfo riderInfo = new RiderInfo();
                riderInfo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}















