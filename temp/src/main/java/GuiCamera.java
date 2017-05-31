

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class GuiCamera
{
  private String fileName;
  private String defaultName = "GuiCamera";
  static int serialNum = 0;
  private String imageFormat;
  private String defaultImageFormat = "png";
  Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
  private static GuiCamera guiCamera = null;

  private GuiCamera(String filePath, String format) {
    if (filePath == null)
      this.fileName = this.defaultName;
    else {
      this.fileName = filePath;
    }

    if (format == null)
      this.imageFormat = this.defaultImageFormat;
    else
      this.imageFormat = format;
  }

  public static GuiCamera getInstance(String filePath, String format)
  {
    if (guiCamera == null) {
      return new GuiCamera(filePath, format);
    }
    return guiCamera;
  }

  public void snapShot()
  {
    try {
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = (int)screensize.getWidth();
      int height = (int)screensize.getHeight();

      BufferedImage screenshot = new Robot().createScreenCapture(
        new Rectangle(0, 0, width, height));
      serialNum += 1;

      String name = this.fileName + String.valueOf(serialNum) + "." + this.imageFormat;
      File f = new File(name);
      System.out.print("Save File " + name);

      ImageIO.write(screenshot, this.imageFormat, f);
      System.out.print("..Finished!\n");
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public static void open()
  {
    Long period = Long.valueOf(86400000L);
    Long delaySec = Long.valueOf(25200000L);

    Calendar calendar = Calendar.getInstance();
    calendar.set(11, 23);
    calendar.set(12, 59);
    calendar.set(13, 59);
    Long delay = Long.valueOf(calendar.getTimeInMillis() - System.currentTimeMillis() - delaySec.longValue());

    System.out.println(period);
    System.out.println(delay);

    new ScheduledThreadPoolExecutor(3).scheduleAtFixedRate(new Runnable()
    {
      public void run()
      {
        try
        {
          System.out.println(">>>>>>>>> Start >>>>>>>>>>");

          GuiCamera cam = GuiCamera.getInstance("/Users/Think/Desktop/", "png");
          cam.snapShot();

          System.out.println(">>>>>>>>>> End  >>>>>>>>>>");
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    , 0L, 10000L, TimeUnit.MILLISECONDS);
  }

  public static void main(String[] args)
  {
    open();
  }
}