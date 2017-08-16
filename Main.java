import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.tree.*;	

public class Main{

  public static void main(String[] args){	
    Model model = new Model();
    FrameView frame = new FrameView(model, "JSketch");
    
    

    //frame.setLayout(new BorderLayout());
    frame.getContentPane().setLayout(new GridBagLayout());
    //frame.getContentPane().setPreferredSize(new Dimension(1600,1200)); 
    //int w = frame.getWidth();
    //int h = frame.getHeight();

    GridBagConstraints c = new GridBagConstraints();


    for (int i = 0; i < 6; i++) {
      ToolPalette tool_i = new ToolPalette(model, i);
      c.fill = GridBagConstraints.BOTH;
    //System.out.println(h);
    //c.weightx = 1200 / 3;
      c.weightx = 0.05;
      c.weighty = 0.1;
      c.gridx = i % 2;
      c.gridy = i / 2;
      //c.ipadx = 50;
      frame.getContentPane().add(tool_i, c);      
      model.addView(tool_i);
    }

    //Color[] colorlist = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLACK};     
 
    for (int i = 0; i < 6; i++) {
      ColorPalette colorpalette = new ColorPalette(model, i);
      c.fill = GridBagConstraints.BOTH;
      c.weightx = 0.05;
      c.weighty = 0.1;
      //c.gridwidth = 2;
    //c.ipadx = 200;
      c.gridx = i % 2;
      c.gridy = i / 2 + 3;
      frame.getContentPane().add(colorpalette, c);
      model.addView(colorpalette);
    }
 
    for (int i = 0; i < 4; i++) {
      LinePalette linepalette = new LinePalette(model, i);
      c.fill = GridBagConstraints.BOTH;
      c.weightx = 0.1;
      c.weighty = 0.1;
      c.gridwidth = 2;
    //c.ipadx = 200;
      c.gridx = 0;
      c.gridy = 6 + i;
      frame.getContentPane().add(linepalette, c);
      model.addView(linepalette);
    }

    MainView mainview = new MainView(model);
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.9;
    c.weighty = 1;
    c.gridheight = 11;
    //c.ipadx = 1400;
    c.gridx = 2;
    c.gridy = 0;

    //frame.getContentPane().setPreferredSize(new Dimension(1600, 1200));    
    //mainview.setPreferredSize(new Dimension(1500, 1100));
 
    JScrollPane scrollPane = new JScrollPane(mainview);
    frame.getContentPane().add(scrollPane, c);      

    //frame.getContentPane().add(mainview, c);
    model.addView(mainview);
    model.addViewFrame(frame);
    //jpanel mainpanel = new jpanel();	
		// create model and initialize it
		//model model = new model();
		
		// create view, tell it about model (and controller)
		//view view = new view(model);
		// tell model about view. 
		//model.addview(view);
		
		// create second view ...
		//view2 view2 = new view2(model);
		//model.addview(view2);
		
		// create the window
		//jpanel p = new jpanel(new gridlayout(2,1));
		//frame.getcontentpane().add(p);
		//p.add(view);
		//p.add(view2);

    //mainpanel.setbackground(color.red);	
    //mainPanel.setLayout(new BorderLayout());	
    //frame.add(mainPanel, BorderLayout.EAST);
    //frame.setPreferredSize(new Dimension(1600,1200));
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    System.out.println("size = " + mainview.getWidth() + " * " + mainview.getHeight());

    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");

    FilevView file_new = new FilevView(model, "New");
    file.add(file_new);
    model.addView(file_new);

    FilevView file_load = new FilevView(model, "Load");
    file.add(file_load);
    model.addView(file_load); 

    FilevView file_save = new FilevView(model, "Save");
    file.add(file_save);
    model.addView(file_save);

    JMenu view = new JMenu("View");

    MenuvView view_full = new MenuvView(model, "Full");
    view.add(view_full);
    model.addView(view_full);

    MenuvView view_fit = new MenuvView(model, "Fit");    
    view.add(view_fit);
    model.addView(view_fit);

    menuBar.add(file);
    menuBar.add(view);
    frame.setJMenuBar(menuBar);
                 


                //JButton jb1 = new JButton("Components");
                //frame.add(jb1);

  } 
}
