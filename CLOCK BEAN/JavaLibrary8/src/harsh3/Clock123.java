package harsh3;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.beans.*;
import java.io.Serializable;
import java.util.Calendar;
import javax.swing.*;


public class Clock123 extends Canvas implements Serializable, Runnable

{
  
  // state & properties

  private transient Image     offImage; 	 	
  private transient Graphics  offGrfx;
  private transient Thread    clockThread;
  private boolean             raised;
  private boolean             digital;
  private transient Color foreg_color=Color.MAGENTA;
  private transient Color Backg_color=Color.GREEN;
  int hour, min, sec;
  int xc = 200, yc = 150;
  String amPm,AM,PM;
  int xhours, yhours, xminutes, yminutes, xsecond, ysecond, second, minutes, hours;
  
  // Constructors
//this.getContentPane().setBackground(Color.GREEN);
 
  
  public Clock123() 
  
  {
	
    this(false, false);  			//this method calls the next constructors and assign the values of r and d
  
  
}

  public Clock123(boolean r, boolean d) 
  
  {
    
	  // Allow the superclass constructor to do its thing
   
	  super();                             //constructor of canvas class is called

    // Set properties
    
   raised = r;
   digital = d;
   
   setSize(400,300);
    
  // set Background + default size + create/start teh clock thread

    clockThread=new Thread(this,"Clock123");  
    clockThread.start(); 
          
 }

  // Accessor methods
  
  public boolean isRaised() 
  
  {
    return raised;
  }

  public void setRaised(boolean r) 
  
  {
    raised = r;
    repaint();
  
  }

  public boolean isDigital()
  
  {
  
    return digital;
  
  }

  public void setDigital(boolean d) 
  
  {
    
    digital = d;
    repaint();   		      //repaint is used to call the update method 
  
  }

  
  // Other public methods
public void setForegroundColor(Color fc)
{
    foreg_color=fc;
    repaint();
}

public Color getForegroundColor()
        {
            return foreg_color;
        }

public void setBackgroundColor(Color bc)
{
    Backg_color=bc;
    repaint();
}

public Color getBackgroundColor()
        {
            return Backg_color;
        }

  
  
  public void run()  		     //this method is for executing the thread 
  
  {
    
    Thread h1=Thread.currentThread();
    while(clockThread==h1)

  {
    repaint();
    try
    
  {
        
    Thread.sleep(980);              // Process is slept for 0.9 ms as sometime is required for executing the thread
       
    
  }
    
   catch(InterruptedException e)
    
   {}
    
  }
  
  }  

   public void update(Graphics g) 
  
  {
    
    paint(g);  //it calls the paint method
  
  }

   public synchronized void paint(Graphics g)
  
  {
    Dimension d = getSize();
    
    // Create the offscreen graphics context
      
    offImage = createImage(d.width, d.height);
    offGrfx = offImage.getGraphics();
    
    // Paint the background with 3D effects
   offGrfx.setColor(Backg_color);
   offGrfx.fillRect(0,0,400,300);
   offGrfx.draw3DRect(0,0,400,300,raised);
       // Paint the clock
    
    if (digital)                                      
      drawDigitalClock(offGrfx);
    else
      drawAnalogClock(offGrfx);

    // Paint the image onto the screen

    g.drawImage(offImage, 0, 0, null);
  
  }

   // Private support methods

  private void drawAnalogClock(Graphics g)
  
  {

	  g.setFont(new Font("CALIBRI", Font.ITALIC, 25));     //This is for setting specific font and its size 
	 
	  g.setColor(Color.WHITE);

	  g.fillOval(xc - 150, yc - 150, 300, 300);  	      //It is used to fill the oval shape figure with white color
	  
	  g.setColor(foreg_color);
	 
	  g.drawString("1", xc+65, yc-105);     				
	  g.drawString("2", xc+115, yc-60);     				
	  g.drawString("3", xc+135, yc);    					
	  g.drawString("4", xc+115, yc+70);     				
	  g.drawString("5", xc+70, yc+120);     				
	  g.drawString("6", xc-10, yc+145);   					
	  g.drawString("7", xc-85, yc+120);     				
	  g.drawString("8", xc-125, yc+70);     				
	  g.drawString("9", xc-145, yc);     					
	  g.drawString("10",xc-130, yc-60);     				
	  g.drawString("11",xc-85, yc-110);     				
	  g.drawString("12",xc-10, yc-130);   					

	  Calendar now = Calendar.getInstance();

	  hours = now.get(Calendar.HOUR_OF_DAY);  		//It is used for real time (hours) from the system
	  
	  minutes = now.get(Calendar.MINUTE);    		//It is used for real time (hours) from the system
	  
	  second = now.get(Calendar.SECOND);     		//It is used for real time (hours) from the system

	  xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 120 + xc);     			//It is used to calculate the second's hand value at x coordinate
	  
	  
	  ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 120 + yc);     			//It is used to calculate the second's hand value at y coordinate 
	  
	  
	  xminutes = (int) (Math.cos(minutes * 3.14f / 30 - 3.14f / 2) * 100 + xc);   			//It is used to calculate the minute's hand value at x coordinate
	  
	  
	  yminutes  = (int) (Math.sin(minutes * 3.14f / 30 - 3.14f / 2) * 100 + yc);  			//It is used to calculate the minute's hand value at y coordinate
	  
	  
	  xhours = (int) (Math.cos((hours * 30 + minutes / 2) * 3.14f / 180 - 3.14f / 2) * 80 + xc);    //It is used to calculate the hour's hand value at x coordinate
	  
	  
	  yhours = (int) (Math.sin((hours * 30 + minutes / 2) * 3.14f / 180 - 3.14f / 2) * 80 + yc);    //It is used to calculate the hour's hand value at y coordinate

	  
	  g.setColor(Color.red);
	  g.drawLine(xc, yc, xsecond, ysecond); 		//This is for displaying seconds  hand in red color

	  g.setColor(Color.black);
	  g.drawLine(xc, yc - 1, xminutes, yminutes); 		//This is for displaying minutes hand in black color

	  g.setColor(Color.black);
	  g.drawLine(xc, yc - 1, xhours, yhours);  		//This is for displaying hours hand in black color
          g.drawLine(xc-1, yc, xhours, yhours);  		//This is for displaying hours hand in black color
  
	
	 
  }
  
  
  private void drawDigitalClock(Graphics g)
  
{
    Dimension d = getSize();
    	
    // Get the time as a string
    
    Calendar now = Calendar.getInstance();
    hour = now.get(Calendar.HOUR_OF_DAY);
    min = now.get(Calendar.MINUTE);
    sec = now.get(Calendar.SECOND);        
    
    if(hour<12)
    {
    	amPm="AM";
    }
    else
    	amPm="PM";
	 
    g.setFont(new Font("TimesRoman", Font.ITALIC, 45));     		//This is for setting specific font and its size 

    g.setColor(foreg_color);
    g.drawString(hour + ":"+ min + ":" + sec + ":" + amPm, 80, 200);	// Draw the time and take it as STRING
    
}

}