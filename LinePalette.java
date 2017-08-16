import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;

class LinePalette extends JPanel implements IView {
  private Model model;
  private int tnum = -1;
  

  LinePalette(Model model_, int i) {
    //setBackground(Color.BLUE);
    //setLayout(new BorderLayout(BorderLayout.EAST));
    //setPreferredSize(new Dimension(200, 400));
    model = model_;
    tnum = i;
    addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("testLine");
          model.changeLine(tnum);
        }
      }
    );
  }

  static Color[] colorlist = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW};

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int w = getWidth();
    int h = getHeight();

    g2.setColor(colorlist[tnum]);
    g2.setStroke(new BasicStroke((tnum + 1) * h / 15, BasicStroke.CAP_BUTT, 0));
    g2.drawLine( w / 10, h / 2, 9 * w / 10, h / 2); 
    //g2.fillRect(0, 0, w, h);
    //g2.setColor(Color.BLACK);
    //int st = (model.color_no == cnum) ? 16 : 5;
    //g2.setStroke(new BasicStroke(130));
    if (model.thickness_no == tnum) {
      //System.out.println("special test");
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(h / 10));
      g2.drawRect(0, 0, w, h);
    }
    //System.out.println("testline: " + tnum);
    //g2.drawRect(0, 0, w, h);
  }


  public void updateView() {
    repaint();
    System.out.println("Update LinePalette");

  }


}

