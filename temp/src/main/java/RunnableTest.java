

import java.io.PrintStream;

public class RunnableTest
  implements Runnable
{
  private String name;

  public RunnableTest(String name)
  {
    this.name = name;
  }

  public void run() {
    for (int i = 0; i < 5; i++)
      for (long k = 0L; k < 1000L; k += 1L) {
        try {
          Thread.sleep(3000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(this.name + ": " + i + ":" + k);
      }
  }

  public static void main(String[] args)
  {
    try {
      RunnableTest ds1 = new RunnableTest("阿三");
      RunnableTest ds2 = new RunnableTest("李四");

      Thread t1 = new Thread(ds1);
      Thread t2 = new Thread(ds2);

      t1.start();
      t2.start();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}