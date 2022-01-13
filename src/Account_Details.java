
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Account_Details {

    private JLabel j1, id, name, total, idd, named, totald;
    private JPanel data;

    public Account_Details() {

        JFrame Acc_Det = Tools.creatFrame("Account Details", 650, 420);
        
        Acc_Det.getContentPane().setBackground(Color.decode("#663366"));
        
        PreparedStatement s;
        String n = "";
        double t = 0;

        try {
            s = Tools.getConnected().prepareStatement("select cname,amount from account where id=? ");
            s.setInt(1, Tools.id);
            ResultSet res = s.executeQuery();
            res.next();
            n = res.getString(1);
            t = res.getDouble(2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        Acc_Det.setLayout(null);

        data = new JPanel(null);
        data.setBounds(0, 0, 650, 120);
        data.setBackground(Color.decode("#663366"));

        j1 = new JLabel("Account Details");
        j1.setFont(Tools.setFont(20));
        j1.setBounds(225, 0, 150, 50);
        j1.setForeground(Color.WHITE);

        id = new JLabel("ID : ");
        id.setFont(Tools.setFont(15));
        id.setBounds(5, 30, 50, 30);
        id.setForeground(Color.WHITE);

        name = new JLabel("Name : ");
        name.setFont(Tools.setFont(15));
        name.setBounds(5, 55, 70, 30);
        name.setForeground(Color.WHITE);

        total = new JLabel("Total Balance : ");
        total.setFont(Tools.setFont(15));
        total.setBounds(5, 80, 130, 30);
        total.setForeground(Color.WHITE);

        idd = new JLabel();
        idd.setFont(Tools.setFont(15));
        idd.setBounds(45, 30, 100, 30);
        idd.setText(String.valueOf(Tools.id));
        idd.setForeground(Color.WHITE);

        named = new JLabel();
        named.setFont(Tools.setFont(15));
        named.setBounds(65, 55, 300, 30);
        named.setText(n);
        named.setForeground(Color.WHITE);

        totald = new JLabel();
        totald.setText(String.valueOf(t)+"  $");
        totald.setFont(Tools.setFont(15));
        totald.setBounds(125, 80, 200, 30);
        totald.setForeground(Color.WHITE);

        data.add(j1);
        data.add(id);
        data.add(idd);
        data.add(name);
        data.add(named);
        data.add(total);
        data.add(totald);
        Acc_Det.getContentPane().add(data);

        JTable T_Summary = new JTable();
        JScrollPane scrol = new JScrollPane(T_Summary);
        scrol.setBounds(7, 120, 630, 260);
        //*************** Getting Data From DataBase
        try {
            PreparedStatement d = Tools.getConnected().prepareStatement("select * from ops where cid=?");
            d.setInt(1, Tools.id);
            ResultSet r = d.executeQuery();
            DefaultTableModel dm = new DefaultTableModel();
            dm.addColumn("Operation");
            dm.addColumn("amount");
            dm.addColumn("Date");
            dm.addColumn("Time");
            dm.addColumn("Balance");

            while (r.next()) {
                String arr[] = {r.getString(2), r.getString(3), r.getString(4),
                    r.getString(6), r.getString(5)};

                dm.addRow(arr);
            }
            T_Summary.setModel(dm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Acc_Det.add(scrol);
        Acc_Det.setDefaultCloseOperation(2);
        Tools.setWindowClose(Acc_Det);

        T_Summary.setForeground(Color.WHITE);
        T_Summary.setBackground(Color.decode("#5b167e"));

        
        Acc_Det.setVisible(true);
    }

}
