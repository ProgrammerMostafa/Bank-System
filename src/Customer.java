
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Customer {

    JFrame customer;
    EditedButton btn1 ,btn2,btn3;
    public Customer()
    {
         ////////////////---------------------////////////////*Customer_Mode_Form*////////////////---------------------////////////////
        customer = Tools.creatFrame("Customer Mode", 580, 400);
      
                 /*Making Button*/
                 
      btn1= new EditedButton("View Account Details");
      btn1.setFont(Tools.setFont(13));
      btn1.setBounds(390, 20, 170, 60);
      customer.getContentPane().add(btn1);
      btn1.addActionListener(new btnsAction());
      
      btn2= new EditedButton("Deposit Cash");
      btn2.setFont(Tools.setFont(13));
      btn2.setBounds(10, 20, 170, 60);
      customer.getContentPane().add(btn2);
      btn2.addActionListener(new btnsAction());
      
      btn3= new EditedButton("Withdraw Cash");
      btn3.setFont(Tools.setFont(13));
      btn3.setBounds(200, 20, 170, 60);
      customer.getContentPane().add(btn3);
      btn3.addActionListener(new btnsAction());

      JButton btnBack = new EditedButton("Logout");
      btnBack.setFont(Tools.setFont(15));
      btnBack.setBounds(205, 300, 170, 60);
      customer.getContentPane().add(btnBack);
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new First();
                customer.dispose();
            }
        });
        
                /*Making Backgroung Image*/
          
     JLabel label = new JLabel();
     label.setLocation(0, 50);
     label.setSize(600, 250);
        try {
            label.setIcon(Tools.setScalImage(600, 250, "4.png"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
     customer.getContentPane().add(label);
     customer.setVisible(true);
             
    }
    
    public class btnsAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object obj = ae.getSource();
            if (obj.equals(btn1)) {
                if (Tools.flag == 0) {
                    ++Tools.flag;
                    new Account_Details();
                }  
            }
                            
            else if(obj.equals(btn2)){
                if (Tools.flag == 0) {
                    ++Tools.flag;
                    new Deposit();  
                }  
            }
            else if(obj.equals(btn3)){
                if (Tools.flag == 0) {
                    ++Tools.flag;
                    new Withdraw(); 
                }    
            }
        }
}
}
