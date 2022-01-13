
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add_Update {

    EditedText id, name, phone, pass, date, amount, ssn, address;
    JLabel AccInfo, AccId, CustName, Address, Phone, Pass, Gender, Date, Amount, SSN, icon1, icon2, txtLp1, txtLp2, txtLp3;
    JRadioButton male = new JRadioButton("Male  ");
    JRadioButton female = new JRadioButton("Female");
    EditedButton btnAdd = new EditedButton(" Done ");

    public Add_Update() {
        JFrame ADD_UPDATE = Tools.creatFrame("Add Account Information", 700, 640);
        //_____________________________:: UPDATE_MODE::____________________________
        if (Tools.addUpdate == 2) {
            ADD_UPDATE.setTitle("Update Account Information");
        }
        //**********
        //___________________________________:: JTextField ::________________________________
        id = new EditedText(15);
        name = new EditedText(15);
        address = new EditedText(20);
        phone = new EditedText(15);
        pass = new EditedText(15);
        date = new EditedText(15);
        amount = new EditedText(15);
        ssn = new EditedText(15);
        //______________________________:: JTextField Specification ::_______________________
        id.setBackground(Color.LIGHT_GRAY);
        name.setBackground(Color.LIGHT_GRAY);
        address.setBackground(Color.LIGHT_GRAY);
        phone.setBackground(Color.LIGHT_GRAY);
        pass.setBackground(Color.LIGHT_GRAY);
        date.setBackground(Color.LIGHT_GRAY);
        amount.setBackground(Color.LIGHT_GRAY);
        ssn.setBackground(Color.LIGHT_GRAY);
        ////////////////////To set date in textfield ///////////
        Date d = new Date(System.currentTimeMillis());
        date.setEditable(false);
        date.setText(d.toString());

        /////////////////To Contains The Last Id In DataBase
        String idd = "0";
        try {
            PreparedStatement pp = Tools.getConnected().prepareStatement("select id from account");
            ResultSet r = pp.executeQuery();

            while (r.next()) {
                idd = r.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setEditable(false);
        id.setText(String.valueOf(Integer.valueOf(idd) + 1));

        //___________________________________:: JRadioButton Specification ::_______________________________
        //***********
        JPanel groupRadio = new JPanel();
        groupRadio.setLayout(null);
        groupRadio.setBackground(Color.LIGHT_GRAY);

        male = new JRadioButton("Male  ");
        male.setBackground(Color.LIGHT_GRAY);
        male.setFont(Tools.setFont("Britannic Bold", 16));
        male.setForeground(Color.BLACK);
        male.setBounds(80, 0, 100, 25);
        male.setSelected(true);

        female = new JRadioButton("Female");
        female.setBackground(Color.LIGHT_GRAY);
        female.setFont(Tools.setFont("Britannic Bold", 16));
        female.setForeground(Color.BLACK);
        female.setBounds(250, 0, 100, 25);

        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        groupRadio.add(male);
        groupRadio.add(female);
        //**********
        //_______________________________:: JButton Specification ::_________________________________
        //***********
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.decode("#585858"));
        btnPanel.setBounds(0, 550, 400, 80);
        btnPanel.setLayout(null);

        EditedButton btnBack = new EditedButton("Back");
        btnBack.setFont(Tools.setFont("Britannic Bold", 18));
        btnBack.setBounds(30, 5, 130, 40);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ADD_UPDATE.dispose();
                if (Tools.m == 0) {
                    new Login();
                } else {
                    new Admin_Mode();
                }
            }
        });

        btnAdd = new EditedButton(" Done ");
        btnAdd.setFont(Tools.setFont("Britannic Bold", 18));
        btnAdd.setBounds(230, 5, 130, 40);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Tools.addUpdate == 2) {
                    //////////////******************************update data on database*************////////////////////////  
                    if (pass.getText().equals("") || name.getText().equals("") || address.getText().equals("") || ssn.getText().equals("") || phone.getText().equals("") || amount.getText().equals("")) {
                        Tools.playSound("s1.mp3");
                        JOptionPane.showMessageDialog(null, "Please Fill The Form", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            if (ssn.getText().length() != 14) {
                                Tools.playSound("s1.mp3");
                                JOptionPane.showMessageDialog(null, "SSN must be 14 digit", "Error!", JOptionPane.ERROR_MESSAGE);
                            } else if (Double.valueOf(amount.getText()) < 500) {
                                Tools.playSound("s1.mp3");
                                JOptionPane.showMessageDialog(null, "Minimum amount is 500 $", "Error!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                PreparedStatement d = Tools.getConnected().prepareStatement("update account set cname=? ,pasword=? ,address=? ,gender=? ,ssn=?"
                                        + " ,phone=?, amount=? where id =? ");
                                d.setString(1, name.getText());
                                d.setString(2, pass.getText());
                                d.setString(3, address.getText());
                                if (male.isSelected()) {
                                    d.setString(4, "male");
                                } else {
                                    d.setString(4, "female");
                                }
                                d.setString(5, ssn.getText());
                                d.setString(6, phone.getText());
                                d.setString(7, amount.getText());
                                d.setInt(8, Tools.id);
                                d.executeUpdate();

                                Tools.playSound("s3.mp3");
                                JOptionPane.showMessageDialog(null, "   Successful Operation ");
                                ADD_UPDATE.dispose();
                                new Admin_Mode();
                            }
                        } catch (Exception ex) {
                            Tools.playSound("s1.mp3");
                            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    try {
                        if (pass.getText().equals("") || name.getText().equals("") || address.getText().equals("") || ssn.getText().equals("") || phone.getText().equals("") || amount.getText().equals("")) {
                            Tools.playSound("s1.mp3");
                            JOptionPane.showMessageDialog(null, "Please Fill The Form", "Error!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (ssn.getText().length() != 14) {
                                Tools.playSound("s1.mp3");
                                JOptionPane.showMessageDialog(null, "SSN must be 14 digit", "Error!", JOptionPane.ERROR_MESSAGE);
                            } else if (Double.valueOf(amount.getText()) < 500) {
                                Tools.playSound("s1.mp3");
                                JOptionPane.showMessageDialog(null, "Minimum amount is 500!!", "Error!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                PreparedStatement p = Tools.getConnected().prepareStatement("insert into account (cname,pasword,address,gender,ssn,phone,dat,amount) values(?,?,?,?,?,?,?,?)");
                                p.setString(1, name.getText());
                                p.setString(2, pass.getText());
                                p.setString(3, address.getText());
                                if (male.isSelected()) {
                                    p.setString(4, "male");
                                } else {
                                    p.setString(4, "female");
                                }
                                p.setString(5, ssn.getText());
                                p.setString(6, phone.getText());
                                p.setDate(7, d);
                                p.setString(8, amount.getText());
                                p.executeUpdate();

                                Tools.playSound("s3.mp3");
                                JOptionPane.showMessageDialog(null, "   Successful Operation ");
                                ADD_UPDATE.dispose();
                                if (Tools.m == 0) {
                                    new Login();
                                } else {
                                    new Admin_Mode();
                                }
                            }
                        }

                    } catch (Exception ex) {
                        Tools.playSound("s1.mp3");
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnPanel.add(btnAdd);
        btnPanel.add(btnBack);
        ADD_UPDATE.getContentPane().add(btnPanel);
        //**********
        // __________________________________:: JPanel Specification ::_______________________________
        //**********
        JPanel p1 = new JPanel(new GridLayout(20, 2, 0, 2));
        p1.setBounds(0, 0, 400, 570);
        p1.setBackground(Color.decode("#585858"));
        ADD_UPDATE.getContentPane().add(p1);

        JPanel p2 = new JPanel();
        p2.setBounds(400, 0, 300, 640);
        p2.setLayout(null);
        p2.setBackground(Color.decode("#3e0e4c"));
        ADD_UPDATE.getContentPane().add(p2);

        //___________________________________:: JLabel Specification ::_______________________________
        //************
        AccInfo = new JLabel("                  Account Information ");
        AccInfo.setFont(Tools.setFont("Britannic Bold", 20));
        AccInfo.setForeground(Color.WHITE);

        AccId = new JLabel("                                Account ID");
        AccId.setFont(Tools.setFont("Britannic Bold", 16));
        AccId.setForeground(Color.WHITE);

        CustName = new JLabel("                             Customer Name");
        CustName.setFont(Tools.setFont("Britannic Bold", 16));
        CustName.setForeground(Color.WHITE);

        Address = new JLabel("                                 Address");
        Address.setFont(Tools.setFont("Britannic Bold", 16));
        Address.setForeground(Color.WHITE);

        Phone = new JLabel("                                  Phone");
        Phone.setFont(Tools.setFont("Britannic Bold", 16));
        Phone.setForeground(Color.WHITE);

        Pass = new JLabel("                                  Password");
        Pass.setFont(Tools.setFont("Britannic Bold", 16));
        Pass.setForeground(Color.WHITE);

        Gender = new JLabel("                                  Gender");
        Gender.setFont(Tools.setFont("Britannic Bold", 16));
        Gender.setForeground(Color.WHITE);

        Date = new JLabel("                           Verification Date");
        Date.setFont(Tools.setFont("Britannic Bold", 16));
        Date.setForeground(Color.WHITE);

        Amount = new JLabel("                                 Amount ");
        Amount.setFont(Tools.setFont("Britannic Bold", 16));
        Amount.setForeground(Color.WHITE);

        SSN = new JLabel("                        Social Security Number ");
        SSN.setFont(Tools.setFont("Britannic Bold", 16));
        SSN.setForeground(Color.WHITE);

        icon1 = new JLabel();
        icon1.setBounds(50, 20, 200, 200);
        icon1.setIcon(Tools.setScalImage(icon1.getWidth(), icon1.getHeight(), "z.png"));

        icon2 = new JLabel();
        icon2.setBounds(60, 320, 179, 280);
        icon2.setIcon(Tools.setScalImage(icon2.getWidth(), icon2.getHeight(), "11.png"));

        txtLp1 = new JLabel("        Computer Science Bank");
        txtLp1.setBounds(0, 220, 400, 30);
        txtLp1.setFont(Tools.setFont("Britannic Bold", 20));
        txtLp1.setForeground(Color.WHITE);

        txtLp2 = new JLabel("                     C S B");
        txtLp2.setBounds(20, 250, 400, 30);
        txtLp2.setFont(Tools.setFont("Britannic Bold", 18));
        txtLp2.setForeground(Color.WHITE);

        txtLp3 = new JLabel("           Honesty && Security");
        txtLp3.setBounds(20, 275, 400, 30);
        txtLp3.setFont(Tools.setFont("Britannic Bold", 16));
        txtLp3.setForeground(Color.WHITE);

        //**********
        //_____________________________:: Add to JFrame ::____________________________
        p1.add(AccInfo);
        p1.add(AccId);
        p1.add(id);
        p1.add(CustName);
        p1.add(name);
        p1.add(Pass);
        p1.add(pass);
        p1.add(Address);
        p1.add(address);
        p1.add(Gender);
        p1.add(groupRadio);
        p1.add(SSN);
        p1.add(ssn);
        p1.add(Phone);
        p1.add(phone);
        p1.add(Amount);
        p1.add(amount);
        p1.add(Date);
        p1.add(date);

        p2.add(icon1);
        p2.add(icon2);
        p2.add(txtLp1);
        p2.add(txtLp2);
        p2.add(txtLp3);

        //_____________________________:: UPDATE_MODE::____________________________
        if (Tools.addUpdate == 2) {
            //////////////******************************Display selected data to update*************////////////////////////  
            try {
                PreparedStatement s = Tools.getConnected().prepareStatement("select *  from account where id=?");
                s.setInt(1, Tools.id);
                ResultSet r = s.executeQuery();
                while (r.next()) {
                    id.setText("" + r.getInt(1));
                    name.setText(r.getString(2));
                    pass.setText(r.getString(3));
                    address.setText(r.getString(4));
                    if (r.getString(5).equals("male")) {
                        male.setSelected(true);
                    } else {
                        female.setSelected(true);
                    }
                    ssn.setText("" + r.getString(6));
                    phone.setText(r.getString(7));
                    date.setText("" + r.getString(8));
                    amount.setText("" + r.getString(9));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Add_Update.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ADD_UPDATE.setVisible(true);

    }
}
