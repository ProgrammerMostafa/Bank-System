import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class First {
    
    private JLabel admin,customer,log;
     public First() {
        try {
            JFrame login = Tools.creatFrame("Login As", 550, 310);
            
            customer = new JLabel();
            customer.setBounds(50, 60, 200, 185);
            customer.setIcon(Tools.setScalImage(customer.getWidth(), customer.getHeight(), "customer1.png"));
      
            admin=new JLabel();
            admin.setBounds(300,60 , 210, 185);
            admin.setIcon(Tools.setScalImage(admin.getWidth(), admin.getHeight(), "admin1.png"));
            
            customer.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                   Tools.custAdm = 1;
                   new Login();
                   login.dispose();
                }
                public void mouseEntered(MouseEvent evt) {
                    customer.setIcon(Tools.setScalImage(customer.getWidth(), customer.getHeight(), "customer.png"));
                }

                public void mouseExited(MouseEvent evt) {
                    customer.setIcon(Tools.setScalImage(customer.getWidth(), customer.getHeight(), "customer1.png"));
                }
                
            });
            admin.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    Tools.custAdm = 2;
                    new Login();
                    login.dispose();
                }
                public void mouseEntered(MouseEvent evt) {
                    admin.setIcon(Tools.setScalImage(admin.getWidth(), admin.getHeight(), "admin.png"));
                }
                public void mouseExited(MouseEvent evt) {
                    admin.setIcon(Tools.setScalImage(admin.getWidth(), admin.getHeight(), "admin1.png"));
                }
            });
            
            log = new JLabel();
            log.setBounds(185, 5, 200, 40);
            log.setFont(Tools.setFont("Snap ITC",35));
            log.setForeground(Color.YELLOW);
            log.setText("Login As");
                    
            login.getContentPane().setBackground(Color.decode("#3e0e4c"));
            login.getContentPane().add(customer);
            login.getContentPane().add(admin);
            login.getContentPane().add(log);
            
            login.setVisible(true);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
     }
    public static void main(String[] args) {
          //************** Decliring Variables
        JFrame start = Tools.creatFrame("",550, 280);
        JProgressBar pb = new JProgressBar(0, 100);
        JLabel load = new JLabel();
        JLabel logo = new JLabel();
        JLabel BackGround = new JLabel();
        JLabel num = new JLabel();
        //************** Handling Desgin
        start.setLayout(null);
        start.setUndecorated(true);
        //*******
        pb.setValue(0);
        pb.setSize(440,15);
        pb.setLocation(50,230);
        pb.setBackground(Color.decode("#5b167e"));
        //*******
        logo.setSize(150,80);
        logo.setLocation(10,0);
        logo.setIcon(Tools.setScalImage(logo.getWidth(),logo.getHeight(),"logo.png"));
        //*******
        num.setText("0%");
        num.setBounds(465, 210, 40,20);
        num.setForeground(Color.WHITE);
        //*******
        load.setText("Loading");
        load.setBounds(50,210,70,20);
        load.setForeground(Color.WHITE);
        //*******
        BackGround.setSize(550,280);
        BackGround.setIcon(Tools.setScalImage(BackGround.getWidth(), BackGround.getHeight(),"bank.jpg"));
        //************** Adding to Frame
        start.add(pb);
        start.add(logo);
        start.add(num);
        start.add(load);
        start.add(BackGround);
        //************** Oporations
        start.setVisible(true);
        try{
            int x = 1;
            for(int i = 0 ; i<102 ; i++)
            {
                pb.setValue(i);
                num.setText(i+"%");
                Thread.sleep(40);
                if(i%10 == 0)
                {
                    if(x==1)
                    {
                        load.setText("Loading.");
                        x++;
                    }
                    else if (x==2)
                    {
                        load.setText("Loading..");
                        x++;
                    }
                    else if (x==3)
                    {
                        load.setText("Loading...");
                        x++;
                    }
                    else if (x==4)
                    {
                        load.setText("Loading....");
                        x=1;
                    }
                }
                if(i==15||i==55)
                  i+=6;         
                if(i==100)
                {
                    new First();
                    start.dispose();
                    
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
