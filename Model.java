import java.util.ArrayList;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Rectangle2D;
import java.awt.Point;

interface IView {
  public void updateView();

}

public class Model {
  private int couter;

  private ArrayList<IView> views = new ArrayList<IView>();
  private IView viewframe;
 
  ArrayList<GeoShape> geos = new ArrayList<GeoShape>(); 

  
  int cur_shape = -1;
  

  int tool_no = -1;
  int color_no = -1;
  int thickness_no = -1;

  File curFile = null;

  boolean fsize = true; 
 
  public void addView(IView view) {
    views.add(view);
    view.updateView();

  }

  public void addViewFrame(IView view) {
    viewframe = view;
  }

  public void changeTool(int i) {
    if (tool_no != i) {
      int tmp = tool_no;
      tool_no = i;
      notifyObservers(tmp);
      notifyObservers(i);
    }
  }

  public void changeColor(int i) {
    if (color_no != i) { 
      int tmp = color_no + 6;
      color_no = i;
      notifyObservers(tmp);
      notifyObservers(i + 6);
    }
  }

  public void changeLine(int i) {
    if (thickness_no != i) {
      int tmp = thickness_no + 12;
      thickness_no = i;
      notifyObservers(tmp);
      notifyObservers(i + 12);
    }

  }

  public void createShape(Point p) {
    GeoShape shape = new GeoShape(p.x, p.y, p.x, p.y, tool_no - 2);
    geos.add(shape);
    shape.setCol(color_no);
    shape.setLine(thickness_no); 
    cur_shape = geos.size() - 1;
  }

  public void updateShape(Point p) {
    GeoShape tmpgeo = geos.get(cur_shape);
    tmpgeo.x1 = p.x;
    tmpgeo.y1 = p.y;
    notifyObservers(16); 
  }

  public void selectShape(Point p) {
    cur_shape = -1;
    int N = geos.size();
    for (int i = N - 1; i >= 0; i--) {
      if (geos.get(i).contains(p)) {
        cur_shape = i;
        geos.get(i).select(p);
        changeColor(geos.get(i).shape_col);
        changeLine(geos.get(i).shape_line);
        break;
      }
    }

  }

  public void deleteShape(Point p) {
    cur_shape = -1;
    int N = geos.size();
    for (int i = N - 1; i >= 0; i--) {
      if (geos.get(i).contains(p)) {
        cur_shape = i;
        geos.remove(i);
        break;
      }
    }
    notifyObservers(16);
  }

  public void fillShape(Point p) {
    cur_shape = -1;
    int N = geos.size();
    for (int i = N - 1; i >= 0; i--) {
      if (geos.get(i).contains(p)) {
        cur_shape = i;
        geos.get(i).fill = true;
        break;
      }
    }
    notifyObservers(16);
  }

  public void updatePosition(Point p) {
    if (cur_shape >= 0) {
      geos.get(cur_shape).updatePosition(p);
      notifyObservers(16);
    }
  }

  public void updateViewOption(boolean vop) {
    if (fsize != vop) {
      fsize = vop;
      notifyObservers(16);
      notifyFrame();
    }
  }

  public void newFile() {
    curFile = null; 
    geos.clear();
    notifyObservers(16);
  }

  public void loadFile(File file) {
    curFile = file; 
    geos.clear();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(curFile.getPath()));
      String line = reader.readLine();

      while (line != null) {
        String[] parts = line.split(" ");

        GeoShape shape = new GeoShape(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        geos.add(shape);
        shape.setCol(Integer.parseInt(parts[5]));
        shape.setLine(Integer.parseInt(parts[6]));
        if (Integer.parseInt(parts[7]) == 1) {
          shape.fill = true;
        }

        line = reader.readLine();
      }

    } catch (IOException ioe) { } finally {
      try { 
        if (reader != null) { 
          reader.close(); 
        }
      } catch (Exception e) {}        
    }
    notifyObservers(16); 
    
  }

  public void saveFile(File file) {
    FileWriter out = null;
    try {
      out = new FileWriter(file.getPath());
      for (GeoShape geo : geos) {
        int geofill = geo.fill ? 1 : 0;
        String towrite = Integer.toString(geo.x0) + " " 
                         + Integer.toString(geo.y0) + " " 
                         + Integer.toString(geo.x1) + " " 
                         + Integer.toString(geo.y1) + " "
                         + Integer.toString(geo.shape_type) + " "
                         + Integer.toString(geo.shape_col) + " "
                         + Integer.toString(geo.shape_line) + " " 
                         + Integer.toString(geofill) + "\n";
        out.write(towrite);
      } 
    } catch (IOException e) {} finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {}
    }
   
  }

  public void saveFile() {
    saveFile(curFile);
  }

  private void notifyObservers() {
    for (IView view : this.views) {
      view.updateView();
    }
  }

  private void notifyObservers(int i) {
    if (i >= 0) {
      views.get(i).updateView();
    }
  }

  private void notifyFrame() {
    viewframe.updateView();

  }
}
