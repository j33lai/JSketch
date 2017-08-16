import java.lang.Math;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

class MainView extends JViewport implements IView {
  private Model model;
  private Rectangle2D rec = new Rectangle2D.Double();

  private int curw = -1;
  private int curh = -1;
  private int iter = 0;

  MainView(Model model_) {
    setBackground(Color.WHITE);

    model = model_;
    //setLayout(new BorderLayout(BorderLayout.EAST));
    //setPreferredSize(new Dimension(1400, 50)); 

    MouseAdapter md = new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        Point p = getMousePosition();
        if (model.fsize) {
          p.x /= (double)getWidth() / (double)curw;
          p.y /= (double)getHeight() / (double)curh;
        }    

        switch (model.tool_no) {
          case 0 :
            model.selectShape(p); 
            break;
          case 1 :
            model.deleteShape(p);
            break;
          case 2 :
          case 3 :
          case 4 :
            model.createShape(p); 
            break;
          case 5 :
            model.fillShape(p);
            break;    
          default :
            break;
        }

     }

      public void mouseDragged(MouseEvent e) {
        Point p = getMousePosition();
        if (model.fsize) {
          p.x /= (double)getWidth() / (double)curw;
          p.y /= (double)getHeight() / (double)curh;
        }

        switch (model.tool_no) {
          case 0 :
            model.updatePosition(p);
            break;
          case 1 :
            break;
          case 2 :
          case 3 :
          case 4 :
            model.updateShape(p);
            break;
          case 5 :
            break;
          default :
            break;
        }
      }
    };
   
    addMouseListener(md);
    addMouseMotionListener(md);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (iter < 3) {
      curw = getWidth();
      curh = getHeight();
      iter++;
    }
    if (model.fsize) {
      double sx = (double)getWidth() / (double)curw;
      double sy = (double)getHeight() / (double)curh;
      g2.scale(sx, sy);
      //System.out.println("size = " + sx + " * " + sy);
      //curw = getWidth();
      //curh = getHeight();
    }

    int N = model.geos.size();
    for (int i = 0; i < N; i++) {
      if (model.geos.get(i).shape_col >=0) {
        g2.setColor(ColorPalette.colorlist[model.geos.get(i).shape_col]);
      }
      if (model.geos.get(i).shape_line >= 0) {
        g2.setStroke(new BasicStroke(model.geos.get(i).shape_line * 5 + 5));
      }
      model.geos.get(i).draw(g2);
    }

    //System.out.println("repaint");
    //int w = getWidth();
    //int h = getHeight();

    //System.out.println("size = " + w + " * " + h);
    //if (model.color_no >= 0) {
    //  g2.setColor(ColorPalette.colorlist[model.color_no]);
    //}

    //if (model.thickness_no >= 0) {
    //  g2.setStroke(new BasicStroke(model.thickness_no * 5 + 5));
    //}
 
    //g2.fillRect(0, 0, w, h);
    //g2.setColor(Color.BLACK);
    //int st = (model.color_no == cnum) ? 16 : 5;
    //g2.setStroke(new BasicStroke(st));
    //rec.setRect(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0));
    //g2.draw(rec);
    //g2.drawRect(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0));
  }


  public void updateView() {
    setVisible(false);
    if (model.fsize) {
      setPreferredSize(new Dimension(0, 0));
    } else {
      setPreferredSize(new Dimension(1500, 1100));
    }
    setVisible(true);
    repaint();
    //System.out.println(model.fsize);

  }


}
