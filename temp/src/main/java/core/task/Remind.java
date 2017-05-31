package core.task;
 
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import core.ui.RemindUI;

public class Remind
{
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

          new RemindUI();

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
}