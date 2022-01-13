import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;

public class Tools {

    public static int id = 0;
    public static int m = 0;
    public static int custAdm = 1;
    public static int addUpdate = 1;
    public static int flag = 0;
    public static JFrame creatFrame(String title, int width, int hight) {
        JFrame frm = null;
        try {
            frm = new JFrame(title);
            frm.setSize(width, hight);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setLocationRelativeTo(null);
            frm.setLayout(null);
            frm.setResizable(false);
            Image img = ImageIO.read(Tools.class.getResource("icon.png"));
            frm.setIconImage(img);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return frm;
    }

    //********************************
    public static ImageIcon setScalImage(int width, int hight, String ImageName) {
        Image m, i = null;
        try {
            m = ImageIO.read(Tools.class.getResource(ImageName));
            i = m.getScaledInstance(width, hight, Image.SCALE_AREA_AVERAGING);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        ImageIcon ic = new ImageIcon(i);
        return ic;
    }

    //*******************************
    public static Font setFont(int size) {
        return new Font("Arial Bold", Font.PLAIN, size);
    }

    public static Font setFont(String fontName, int size) {
        return new Font(fontName, Font.PLAIN, size);
    }
    //********************************
    public static void playSound(String name){
        try{
            new JFXPanel();
            new MediaPlayer(new Media(new File(name).toURI().toString())).play();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public static Connection getConnected() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root","123456789");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return c;
    }
    
    public static void setWindowClose(JFrame frm)
    {
        frm.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
            }

            @Override
            public void windowClosing(WindowEvent we) {
                
            }
            
            @Override
            public void windowClosed(WindowEvent we) {
                Tools.flag=0;
            }

            @Override
            public void windowIconified(WindowEvent we) {
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
            
            }
        });
    }
    
}
