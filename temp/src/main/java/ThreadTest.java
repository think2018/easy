

import java.io.PrintStream;

public class ThreadTest extends Thread
{
  public ThreadTest(String name)
  {
    super(name);
  }

  public void run() {
    for (int i = 0; i < 5; i++)
      for (long k = 0L; k < 1000L; k += 1L)
        System.out.println(getName() + " : " + i + ":" + k);
  }

  public static void main(String[] args)
  {
    Thread t1 = new ThreadTest("阿三");
    Thread t2 = new ThreadTest("李四");
    t1.start();
    t2.start();
  }
}