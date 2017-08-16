import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.*;

class FrameView extends JFrame implements IView {
  private Model model;

  FrameView(Model model_, String name) {
    super(name);
    model = model_;
    setPreferredSize(new Dimension(1600,1200));
    }

  public void updateView() {
    //setVisible(false);
    //setPreferredSize(new Dimension(getWidth(), getHeight()));
    //pack();
    //setVisible(true);
    //repaint(); 
    System.out.println("Update scroll view");

  }
}

