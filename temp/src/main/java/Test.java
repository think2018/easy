

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.PrintStream;

public class Test
{
  public static void keyPressWithCtrl(Robot r, int key)
  {
    r.keyPress(17);
    r.keyPress(key);
    r.keyRelease(key);
    r.keyRelease(17);
    r.delay(100);
  }

  public static void keyPressWithShift(Robot r, int key)
  {
    r.keyPress(16);
    r.keyPress(key);
    r.keyRelease(key);
    r.keyRelease(16);
    r.delay(100);
  }

  public static void keyPressString(Robot r, String str)
  {
    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable tText = new StringSelection(str);
    clip.setContents(tText, null);
    keyPressWithCtrl(r, 86);
    r.delay(100);
  }

  public static void keyPressWithAlt(Robot r, int key)
  {
    r.keyPress(18);
    r.keyPress(key);
    r.keyRelease(key);
    r.keyRelease(18);
    r.delay(100);
  }

  public static void autoLogin(Robot robot)
  {
    try
    {
      ProcessBuilder proc = new ProcessBuilder(new String[] { 
        "/Applications/QQ.app/Contents/MacOS/QQ" });

      proc.start();

      for (int i = 0; i < 40; i++) {
        robot.delay(2000);

        robot.keyPress(9);
        System.out.println("@@@@@@@@@@ i : " + i);
      }

      String username = "233171118";

      keyPressString(robot, username);

      robot.keyPress(9);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
    throws AWTException
  {
    Robot robot = new Robot();

    autoLogin(robot);
  }
}