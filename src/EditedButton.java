
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;


public class EditedButton extends JButton{
      public EditedButton(String text)
    { 
        super(text);
        setContentAreaFilled(false);
        setForeground(Color.YELLOW);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setForeground(Color.ORANGE);
                setSize(getWidth()+3,getHeight()+3);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                  setForeground(Color.YELLOW);
                  setSize(getWidth()-3,getHeight()-3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.YELLOW);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               setForeground(Color.ORANGE);
            }
        });
    }
    
   @Override
   protected void paintComponent(Graphics g)
   {
       g.setColor(Color.decode("#5b167e"));
       g.fillRoundRect(0, 0,getWidth()-1,getHeight()-1,25,25);
       super.paintComponent(g);
   }
   
   @Override
   protected void paintBorder(Graphics g){
       g.setColor(Color.YELLOW);
       g.drawRoundRect(0, 0,getWidth()-1, getHeight()-1, 25, 25);
   }
    
}
