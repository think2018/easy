

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupTest
{
  public static void main(String[] args)
    throws IOException
  {
    String url = "http://www.baidu.com";
    List urls = new ArrayList();
    findUrl(url, urls);
  }

  public static void reqUrl(String url)
  {
    try
    {
      Connection.Response response = Jsoup.connect(url).execute();
      String bodyHtml = response.body();

      System.out.println(bodyHtml);
      System.out.println(response.charset());
    } catch (IOException e) {
      System.out.println("############## ");
    }
  }

  public static void findUrl(String url, List<String> urls)
  {
    try {
      Connection.Response response = Jsoup.connect(url).execute();

      for (Element element : response.parse().getElementsByTag("A"))
      {
        String urlString = element.attr("href");
        if ((urlString.contains("http")) && (urlString.startsWith("http")) && (!urls.contains(urlString))) {
          System.out.println(urlString);
          System.out.println(urlString.substring(0, urlString.lastIndexOf("com") + 4));
          urls.add(urlString);
          findUrl(urlString, urls);
        }
      }
    }
    catch (Exception e) {
      System.out.println("@@@@@@@@@@@@@@@@@@@@ exception ");
    }
  }
}