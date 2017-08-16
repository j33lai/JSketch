import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;



//static Color[] colorlist = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLACK};

class ColorPalette extends JPanel implements IView {
  private Model model;
  int cnum = -1;
  static Color[] colorlist = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLACK};


  ColorPalette(Model model_, int i) {
    model = model_;
    cnum = i;

    addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("testcolor");
          model.changeColor(cnum);
        }
      } 
    );
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int w = getWidth();
    int h = getHeight();
    
    g2.setColor(colorlist[cnum]);
    g2.fillRect(0, 0, w, h);
    g2.setColor(Color.BLACK); 
    int st = (model.color_no == cnum) ? 16 : 5;
    g2.setStroke(new BasicStroke(st)); 
    //System.out.println(this.getHeight());
    g2.drawRect(0, 0, w, h);
  }
 

  public void updateView() {
    repaint();
    System.out.println("Update ColorPallette");

  }


}
//static Color[] colorlist = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLACK};


