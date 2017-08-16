import java.io.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;


class ToolPalette extends JPanel implements IView {
  private Model model;

  int tnum = 0;
  BufferedImage bi;

  ToolPalette(Model model_, int i) {
    //super(icon);
    //setBackground(Color.GREEN);
    //setLayout(new BorderLayout());
    //setPreferredSize(new Dimension(200, 400));
    //ImageIcon icon = new ImageIcon("0.GIF");

    //ToolLabel button = new ToolLabel(icon);

    //this.add(button); 

    //ToolLabel button1 = new ToolLabel(icon);

    //this.add(button1); 
    model = model_;
    tnum = i;
    try {
      bi = ImageIO.read(new File(i + ".GIF"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("test");
          model.changeTool(tnum);
        }
      }
    );
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int w = getWidth();
    int h = getHeight();
    g2.drawImage(bi, 0, 0, w, h, null);
    g2.setColor(Color.BLACK);
    int st = (model.tool_no == tnum) ? 16 : 5;
    g2.setStroke(new BasicStroke(st));
    //System.out.println(this.getWidth());
    g2.drawRect(0, 0, w, h);
  }


  public void updateView() {
    repaint(); 
    System.out.println("Update ToolPalette");

  }


}
