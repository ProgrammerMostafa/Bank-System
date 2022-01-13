import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login {

    public Login() {
        JFrame login = Tools.creatFrame("Login", 900, 455);

        JPanel right = new JPanel();
        right.setBounds(450, 0, 450, 500);
        right.setBackground(Color.WHITE);
        right.setLayout(null);

        JPanel left = new JPanel();
        left.setBounds(0, 0, 450, 500);
        left.setBackground(Color.decode("#5b167e"));
        left.setLayout(null);

        EditedText usert;
        EditedTextPassword passt;
        usert = new EditedText(25);
        passt = new EditedTextPassword(25);

        JLabel user, pass, image;
        user = new JLabel("Account ID");
        user.setFont(Tools.setFont("Britannic Bold", 20));
        user.setForeground(Color.BLACK);

        pass = new JLabel("Password");
        pass.setFont(Tools.setFont("Britannic Bold", 20));
        pass.setForeground(Color.BLACK);

        image = new JLabel();
        image.setBounds(100, 0, 270, 260);

        /////To set image and set action to open addAccount frame :
        if (Tools.custAdm == 1) {
            image.setIcon(Tools.setScalImage(image.getWidth(), image.getHeight(), "createEmail.png"));
            image.setCursor(new Cursor(Cursor.HAND_CURSOR));
            image.setToolTipText("Create New Account");
            image.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    Tools.addUpdate = 1;
                    Tools.m = 0;
                    new Add_Update();
                    login.dispose();
                }

                public void mouseEntered(MouseEvent evt) {
                    image.setToolTipText("Create New Account");
                    image.setSize(image.getWidth() + 5, image.getHeight() + 5);
                }

                public void mouseExited(MouseEvent evt) {
                    image.setSize(image.getWidth() - 5, image.getHeight() - 5);
                }
            });
        } else {
            image.setIcon(Tools.setScalImage(image.getWidth(), image.getHeight(), "log20.png"));
        }
        
        /////login buttton :
        EditedButton btnlogin = new EditedButton("Login");
        btnlogin.setFont(Tools.setFont("Britannic Bold", 18));
        btnlogin.setBounds(5, 0, 50, 50);

        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = usert.getText();
                String password = passt.getText();
                if ("admin".equals(usert.getText()) && "admin".equals(passt.getText())) {     //Admin mode 
                    Tools.m = 1;
                    login.dispose();
                    new Admin_Mode();
                } else {
                    PreparedStatement s;
                    try {
                        s = Tools.getConnected().prepareStatement("select * from account where id=? and pasword = ?");
                        s.setString(1, username);
                        s.setString(2, password);
                        ResultSet r = s.executeQuery();
                        if (!r.next()) //user name or password Error 
                        {
                            Tools.playSound("s1.mp3");
                                if(Tools.custAdm == 1)
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid Account ID And Password", "Error!", JOptionPane.ERROR_MESSAGE);
                                else 
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid UserName And Password", "Error!", JOptionPane.ERROR_MESSAGE);                            usert.setText("");
                            passt.setText("");
                            usert.requestFocus();
                        } else //username and password verified
                        {
                            login.dispose();
                            new Customer();
                            Tools.id = Integer.parseInt(username);
                        }

                    } catch (SQLException ex) {
                        System.out.println("error");
                    }
                }
            }
        });

        ///////////// when press on enter key :
        usert.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                /////////--------------------------------------------------------
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if ("admin".equals(usert.getText()) && "admin".equals(passt.getText())){ //Admin mode 
                        Tools.m = 1;
                        login.dispose();
                        new Admin_Mode();
                    } else {
                        String username = usert.getText();
                        String password = passt.getText();
                        PreparedStatement s;
                        try {
                            s = Tools.getConnected().prepareStatement("select * from account where id=? and pasword = ?");
                            s.setString(1, username);
                            s.setString(2, password);
                            ResultSet r = s.executeQuery();
                            if (!r.next()) //user name or password Error 
                            {
                                Tools.playSound("s1.mp3");
                                if(Tools.custAdm == 1)
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid Account ID And Password", "Error!", JOptionPane.ERROR_MESSAGE);
                                else 
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid UserName And Password", "Error!", JOptionPane.ERROR_MESSAGE);                                usert.setText("");
                                passt.setText("");
                                usert.requestFocus();
                            } else //username and password verified
                            {
                                login.dispose();
                                new Customer();
                                Tools.id = Integer.parseInt(username);
                            }

                        } catch (SQLException ex) {
                            System.out.println("error");
                        }
                    }
                }
            }
        });
        ///////////// when press on enter key :
        passt.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if ("admin".equals(usert.getText()) && "admin".equals(passt.getText())){ //Admin mode 
                        Tools.m = 1;
                        login.dispose();
                        new Admin_Mode();
                    } else {
                        String username = usert.getText();
                        String password = passt.getText();
                        PreparedStatement s;
                        try {
                            s = Tools.getConnected().prepareStatement("select * from account where id=? and pasword = ?");
                            s.setString(1, username);
                            s.setString(2, password);
                            ResultSet r = s.executeQuery();
                            if (!r.next()) //user name or password Error 
                            {
                                Tools.playSound("s1.mp3");
                                if(Tools.custAdm == 1)
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid Account ID And Password", "Error!", JOptionPane.ERROR_MESSAGE);
                                else 
                                    JOptionPane.showMessageDialog(btnlogin, "Please Enter a Valid UserName And Password", "Error!", JOptionPane.ERROR_MESSAGE);

                                usert.setText("");
                                passt.setText("");
                                usert.requestFocus();
                            } else //username and password verified
                            {
                                login.dispose();
                                new Customer();
                                Tools.id = Integer.parseInt(username);
                            }

                        } catch (SQLException ex) {
                            System.out.println("error");
                        }
                    }
                }
            }
        });
        
        /////back button :
        EditedButton btnBack = new EditedButton("Back");
        btnBack.setFont(Tools.setFont("Britannic Bold", 18));
        btnBack.setBounds(175, 370, 100, 40);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new First();
                login.dispose();
            }
        });

        JLabel info1 = new JLabel();
        info1.setBounds(20, 30, 300, 50);
        info1.setFont(Tools.setFont(30));
        info1.setForeground(Color.yellow);
        info1.setFont(Tools.setFont("Snap ITC", 50));

        JLabel info2 = new JLabel();

        info2.setFont(Tools.setFont(30));
        info2.setForeground(Color.yellow);
        info2.setFont(Tools.setFont("Snap ITC", 50));

        JLabel Bank_logo = new JLabel();
        Bank_logo.setBounds(15, 150, 420, 220);
        Bank_logo.setIcon(Tools.setScalImage(Bank_logo.getWidth(), Bank_logo.getHeight(), "logo.png"));
        left.add(Bank_logo);
        
        //////to set welcome customer OR welcome admin :
        if (Tools.custAdm == 1) {
            info2.setBounds(150, 100, 300, 50);
            info1.setText("Welcome");
            info2.setText("Customer");
        } else {
            info2.setBounds(220, 100, 230, 50);
            info1.setText("Welcome");
            info2.setText("Admin");
            user.setText("UserName");
        }
        
        JPanel p3 = new JPanel(new FlowLayout());
        p3.setBounds(75, 265, 300, 145);
        p3.setBackground(Color.LIGHT_GRAY);
        p3.add(user);
        p3.add(usert);
        p3.add(pass);
        p3.add(passt);
        p3.add(btnlogin);

        right.add(p3);
        right.add(image);

        login.add(right);
        login.add(left);

        left.add(btnBack);
        left.add(info1);
        left.add(info2);

        login.setVisible(true);
        usert.requestFocus();
    }
}
