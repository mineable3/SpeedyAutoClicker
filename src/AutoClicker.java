
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutoClicker {

  public static boolean clicking = true;
  public static int amountClicked = 0;
  public static int rate = 0;
  public static int numOfClicks = 0;

  public static void main(String[] args) throws InterruptedException {
    while (numOfClicks == 0) {

        try {

            System.out.println("How many clicks do you want to do?: ");
            //similar to the Scanner class in getting data, but does it safer
            BufferedReader xyz = new BufferedReader(new InputStreamReader(System.in));


            try {
            numOfClicks = Integer.parseInt(xyz.readLine());
            if (numOfClicks == 0) {
                numOfClicks = 0;
                System.out.println("Must be at least 1 click.");
            }
            } catch (NumberFormatException ex) {
            System.out.println("Error 1 - please try again");
            }
        } catch (IOException e) {}
      
    
    }


    while (rate == 0) {
        try {
        System.out.println("Max speed of the autoclicker?: (in milliseconds)");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            rate = Integer.parseInt(in.readLine());

            /* Sets a maximum speed that the auto clicker can go
            if (rate < 10) {
                rate = 0;
                System.out.println("Must be at least 1 milliseconds");
            } */

        } catch (NumberFormatException ex) {
            System.out.println("Error 2 - please try again");
        }
        } catch (IOException e) {}

    }


    try {
        //telling the user to setup the mouse where they want it
        System.out.println("PLEASE MOVE YOUR MOUSE INTO POSITION!");
        System.out.println("MOVE MOUSE TO END AUTOCLICK");
        System.out.println("Sleeping for 10 seconds");

        Thread.sleep(10000);

        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Current Mouse Location" + p);

        Robot robot = new Robot();
        while (clicking == true) {
            try {
                Point z = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Current Mouse Location " + z);

                Thread.sleep(rate);

                //If you want rate in nano seconds you can also use the method sleep(long millis, int nanos)
                //Thread.sleep(0, rate);
                

                //Checks if the mouse was moved and ends the loop if so
                if (Math.round(z.getX() + z.getY()) != Math.round(p.getX() + p.getY())) {
                    System.out.println("MOUSE MOVED!: " + z);
                    clicking = false;
                    break;
                }

                //the actual clicking
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                amountClicked++;
                System.out.println("Amount clicked so far: " + amountClicked);

                

                if (amountClicked == numOfClicks) {
                    clicking = false;
                }
            } catch (InterruptedException ex) {}
        }
    } catch (AWTException e) {}
  }
}