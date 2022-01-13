
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Deposit {
    
    JLabel image, cash;
    EditedText txt;
    EditedButton btn;
    
    Deposit() {
        
        JFrame deposit = Tools.creatFrame("Deposit Cash", 500, 220);
        
        image = new JLabel();
        image.setBounds(0, 0, 500, 100);
        image.setIcon(Tools.setScalImage(image.getWidth(), image.getHeight(), "p1.jpg"));
        deposit.add(image);
        
        cash = new JLabel("Cash Amount : ");
        cash.setSize(20, 20);
        cash.setBounds(80, 120, 100, 20);
        deposit.add(cash);
        
        txt = new EditedText(25);
        txt.setBounds(175, 120, 200, 20);
        deposit.add(txt);
        
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        
        btn = new EditedButton("Deposit");
        btn.setSelected(true);
        btn.setBounds(200, 150, 100, 30);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txt.getText().equals("")) {
                    Tools.playSound("s1.mp3");
                    JOptionPane.showMessageDialog(btn, "Please Enter Cash Amount", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    double amount = Double.valueOf(txt.getText());
                    try {
                        PreparedStatement s = Tools.getConnected().prepareStatement("select amount from account where id=? ");
                        s.setInt(1, Tools.id);
                        ResultSet r = s.executeQuery();
                        r.next();
                        double current = r.getDouble(1);
                        
                        PreparedStatement inst = Tools.getConnected().prepareStatement("insert into ops (cid,op,DW_amount,dat,balence,tim) values(?,?,?,?,?,?)");
                        inst.setInt(1, Tools.id);
                        inst.setString(2, "deposite");
                        inst.setDouble(3, amount);
                        inst.setDate(4, d);
                        inst.setDouble(5, current);
                        inst.setString(6, sdf.format(d));
                        inst.executeUpdate();
                        
                        PreparedStatement update = Tools.getConnected().prepareStatement("update account set amount=? where id=?");
                        update.setDouble(1, current + amount);
                        update.setInt(2, Tools.id);
                        update.executeUpdate();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Tools.playSound("s3.mp3");
                    deposit.dispose();
                    JOptionPane.showMessageDialog(btn, "   Successful Operation ", "Deposit Cash", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ////////////When press on enter key :
        txt.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt.getText().equals("")) {
                        Tools.playSound("s1.mp3");
                        JOptionPane.showMessageDialog(btn, "Please Enter Cash Amount", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        
                        double amount = Double.valueOf(txt.getText());
                        try {
                            PreparedStatement s = Tools.getConnected().prepareStatement("select amount from account where id=? ");
                            s.setInt(1, Tools.id);
                            ResultSet r = s.executeQuery();
                            r.next();
                            double current = r.getDouble(1);
                            
                            PreparedStatement inst = Tools.getConnected().prepareStatement("insert into ops (cid,op,DW_amount,dat,balence,tim) values(?,?,?,?,?,?)");
                            inst.setInt(1, Tools.id);
                            inst.setString(2, "deposite");
                            inst.setDouble(3, amount);
                            inst.setDate(4, d);
                            inst.setDouble(5, current);
                            inst.setString(6, sdf.format(d));
                            inst.executeUpdate();
                            
                            PreparedStatement update = Tools.getConnected().prepareStatement("update account set amount=? where id=?");
                            update.setDouble(1, current + amount);
                            update.setInt(2, Tools.id);
                            update.executeUpdate();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Tools.playSound("s3.mp3");
                        deposit.dispose();
                        JOptionPane.showMessageDialog(btn, "   Successful Operation ", "Deposit Cash", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        deposit.add(btn);
        
        deposit.setVisible(true);
        deposit.setDefaultCloseOperation(2);
        Tools.setWindowClose(deposit);
        
    }
    
}
