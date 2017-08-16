import java.io.File;

import javax.swing.*;
import java.awt.event.*;

class FilevView extends JMenuItem implements IView {
  private Model model;
  int op = -1;
  //private JFileChooser fc = new JFileChooser();

  FilevView(Model model_, String iname) {
    super(iname);
    model = model_;
    addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          if (op == 0 || (model.curFile != null && op == 2)) {
            model.newFile();
          } else { 

            JFileChooser fc = new JFileChooser(); 
            int returnVal = fc.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
              File file = fc.getSelectedFile();
            //This is where a real application would open the file.
              switch (op) {
                case 1 :
                  model.loadFile(file);
                  break;
                case 2 :
                  model.saveFile(file); 
                  break;
                default :
                  break; 
              }  
             


              System.out.println("Opening: " + file.getPath() + ".");
            } 
          }
          
        }
      }
    );

    if (iname == "New") {
      op = 0;
    } else if (iname == "Load") {
      op = 1;
    } else {
      op = 2;
    }
  }

  public void updateView() {
    //repaint(); 
    System.out.println("Update view menu item");

  }
}
