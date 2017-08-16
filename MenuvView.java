import javax.swing.*;
import java.awt.event.*;

class MenuvView extends JMenuItem implements IView {
  private Model model;
  boolean vop = false;

  MenuvView(Model model_, String iname) {
    super(iname);
    model = model_;
    addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          model.updateViewOption(vop);
        }
      }
    );
    vop = (iname == "Full") ? true : false;
  }  

  public void updateView() {
    //repaint(); 
    System.out.println("Update view menu item");

  }
}
