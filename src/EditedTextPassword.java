
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPasswordField;

public class EditedTextPassword extends JPasswordField{
    
    public EditedTextPassword(int size)
    {
        super(size);
        setOpaque(false);
        setForeground(Color.BLACK);
        setHorizontalAlignment(CENTER);
    }
    
   @Override
   protected void paintComponent(Graphics g)
   {
       g.setColor(getBackground());
       g.fillRoundRect(0, 0,getWidth()-1,getHeight()-1,15,15);
       super.paintComponent(g);
   }
   
   @Override
   protected void paintBorder(Graphics g){
       g.setColor(Color.decode("#3e0e4c"));
       g.drawRoundRect(0, 0,getWidth()-1, getHeight()-1, 20, 20);
   }
}
