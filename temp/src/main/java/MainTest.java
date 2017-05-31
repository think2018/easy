

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MainTest
{
  public static void main(String[] args)
  {
    cmd();
  }

  public static void cmd()
  {
    try {
      String[] cmds = { "/bin/sh", "-c", "ls -al" };
      Process pro = Runtime.getRuntime().exec(cmds);
      pro.waitFor();
      InputStream in = pro.getInputStream();
      BufferedReader read = new BufferedReader(new InputStreamReader(in));
      String line = null;
      while ((line = read.readLine()) != null)
        System.out.println(line);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}