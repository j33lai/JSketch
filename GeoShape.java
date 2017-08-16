import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class GeoShape {
  int x0;
  int y0;
  int x1;
  int y1;

  int xs;
  int ys;

  int shape_type = -1;
  int shape_col = -1;
  int shape_line = -1;

  boolean fill = false;

  GeoShape(int x0, int y0, int x1, int y1, int t) {
    this.x0 = x0;
    this.y0 = y0;
    this.x1 = x1;
    this.y1 = y1;
    shape_type = t;
  }

  public void setCol(int col) {
    shape_col = col;
  }

  public void setLine(int l) {
    shape_line = l;
  }

  public void draw(Graphics2D g2) {
    switch (shape_type) {
      case 0 :
        g2.drawLine(x0, y0, x1, y1);
        //g2.drawLine(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, x0 < x1 ? x1 : x0, y0 < y1 ? y1 : y0);
        break;
      case 1 :
        if (fill) {
          int t = (shape_line + 1) * 5 / 2;
          g2.fillArc((x0 < x1 ? x0 : x1) - t, (y0 < y1 ? y0 : y1) - t, Math.abs(x1-x0) + 2 * t, Math.abs(y1-y0) + 2 * t, 0, 360);
        } else {
          g2.drawArc(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0), 0, 360);
        }
        break;
      case 2 :   
        if (fill) {
          int t = (shape_line + 1) * 5 / 2;
          g2.fillRect((x0 < x1 ? x0 : x1) - t, (y0 < y1 ? y0 : y1) - t, Math.abs(x1-x0) + 2 * t, Math.abs(y1-y0) + 2 * t);
        } else {  
          g2.drawRect(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0));
        }
        break;
      default :
        break;
    }
  }

  public boolean contains(Point p) {
    switch (shape_type) {
      case 0 :
        Line2D tmpline = new Line2D.Double(x0, y0, x1, y1);
        return tmpline.ptLineDist(p.x, p.y) <= (shape_line + 1) * 5;
      case 1 :
        Arc2D tmparc = new Arc2D.Double(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0), 0, 360, Arc2D.OPEN);
        return tmparc.contains(p.x, p.y);
      case 2 :
        Rectangle2D tmprec = new Rectangle2D.Double(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, Math.abs(x1-x0), Math.abs(y1-y0));
        return tmprec.contains(p.x, p.y);  
      default :
        return false;
    }  
  }

  public void select(Point p) {
    xs = p.x;
    ys = p.y;
  }

  public void updatePosition(Point p) {
        System.out.println("updateposition"); 
        int xt = p.x - xs; 
        int yt = p.y - ys;
        x0 += xt;
        y0 += yt;
        x1 += xt;
        y1 += yt;
        xs = p.x;
        ys = p.y;
  }
}
