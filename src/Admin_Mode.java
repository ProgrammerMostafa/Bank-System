
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Admin_Mode {

    public Admin_Mode() {
        //************** Decliring Variables
        JFrame Accounts = Tools.creatFrame("Admin Mode", 800, 450);
        EditedButton btnBack = new EditedButton("Logout");
        EditedButton btnAdd = new EditedButton("Add");
        EditedButton btnUpdate = new EditedButton("Update");
        EditedButton btnDelete = new EditedButton("Delete");
        EditedButton btnView = new EditedButton("View Balances");
        JTable T_Accounts = new JTable();
        JScrollPane scrol = new JScrollPane(T_Accounts);
        //*************** Getting Data From DataBase
        try {
            PreparedStatement d = Tools.getConnected().prepareStatement("select * from account ");
            ResultSet r = d.executeQuery();
            DefaultTableModel dm = new DefaultTableModel();
            dm.addColumn("ID");
            dm.addColumn("Name");
            dm.addColumn("Password");
            dm.addColumn("Address");
            dm.addColumn("Gender");
            dm.addColumn("SSN");
            dm.addColumn("Phone");
            dm.addColumn("Date");
            dm.addColumn("Balance");
            while (r.next()) {
                String arr[] = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                    r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9)};
                dm.addRow(arr);
            }
            T_Accounts.setModel(dm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //*************** Handling Back Ground
        Accounts.getContentPane().setBackground(Color.decode("#5b167e"));
        //*************** Handling Buttons
        btnBack.setSize(120, 30);
        btnBack.setFont(Tools.setFont(14));
        btnBack.setLocation(335, 380);
        //***** 
        btnAdd.setSize(170, 30);
        btnAdd.setFont(Tools.setFont(13));
        btnAdd.setLocation(45, 330);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Tools.addUpdate = 1;
                Accounts.dispose();
                new Add_Update();
            }
        });
        //*****
        btnUpdate.setSize(170, 30);
        btnUpdate.setFont(Tools.setFont(13));
        btnUpdate.setLocation(225, 330);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Tools.addUpdate = 2;
                 if (T_Accounts.getSelectedRow() == -1) {
                    Tools.playSound("s1.mp3");
                    JOptionPane.showMessageDialog(null, "No Account Is Selected To Update", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                Tools.id= Integer.parseInt(T_Accounts.getValueAt( T_Accounts.getSelectedRow(), 0 ).toString());
                Accounts.dispose();
                new Add_Update();
                }
            }
        });
        //*****
        btnDelete.setSize(170, 30);
        btnDelete.setFont(Tools.setFont(13));
        btnDelete.setLocation(405, 330);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (T_Accounts.getSelectedRow() == -1) {
                    Tools.playSound("s1.mp3");
                    JOptionPane.showMessageDialog(null, "No Account Is Selected To Delete", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        /////////Delete transaction  from DataBase
                        PreparedStatement dt = Tools.getConnected().prepareStatement("delete from ops where cid = ?");
                        dt.setString(1, T_Accounts.getValueAt(T_Accounts.getSelectedRow(), 0).toString());
                        dt.executeUpdate();

                        /////////Delete From DataBase
                        PreparedStatement d = Tools.getConnected().prepareStatement("delete from account where id = ?");
                        d.setString(1, T_Accounts.getValueAt(T_Accounts.getSelectedRow(), 0).toString());
                        d.executeUpdate();

                        /////////Delete Row from JTabel
                        PreparedStatement dd = Tools.getConnected().prepareStatement("select * from account ");
                        ResultSet r = dd.executeQuery();
                        DefaultTableModel dm = new DefaultTableModel();
                        dm.addColumn("ID");
                        dm.addColumn("Name");
                        dm.addColumn("Password");
                        dm.addColumn("Address");
                        dm.addColumn("Gender");
                        dm.addColumn("SSN");
                        dm.addColumn("Phone");
                        dm.addColumn("Date");
                        dm.addColumn("Balance");
                        while (r.next()) {
                            String arr[] = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                                r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9)};
                            dm.addRow(arr);
                        }
                        T_Accounts.setModel(dm);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }

                }
            }
        });
        //*****
        btnView.setSize(170, 30);
        btnView.setFont(Tools.setFont(13));
        btnView.setLocation(585, 330);
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PreparedStatement s;
                try {
                     if (T_Accounts.getSelectedRow() == -1) {
                    Tools.playSound("s1.mp3");
                    JOptionPane.showMessageDialog(null, "No Account Is Selected To View Balalnce", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else
                     {
                    Tools.id=Integer.parseInt(T_Accounts.getValueAt( T_Accounts.getSelectedRow(), 0 ).toString());
                    s = Tools.getConnected().prepareStatement("select pasword from account where id=? ");
                    s.setInt(1, Tools.id);
                    ResultSet r = s.executeQuery();
                    r.next();
                    r.getString(1);
                    new Account_Details();
                     }
                    
                } catch (Exception ex) {
                    Tools.playSound("s1.mp3");
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Account ID", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //*****
        Accounts.add(btnDelete);
        Accounts.add(btnUpdate);
        Accounts.add(btnAdd);
        Accounts.add(btnBack);
        Accounts.add(btnView);
        //*************** Handling Table
        T_Accounts.setForeground(Color.WHITE);
        T_Accounts.setBackground(Color.decode("#5b167e"));
        scrol.setSize(775, 300);
        scrol.setLocation(10, 10);
        Accounts.add(scrol);
        //****************
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new First();
                Accounts.setVisible(false);
            }
        });
        //****************
        Accounts.setVisible(true);
    }
}
