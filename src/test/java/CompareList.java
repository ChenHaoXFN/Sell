import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-03 17:10
 */
public class CompareList {

  private static List<String> kList = new ArrayList<>();
  private static List<String> CList = new ArrayList<>();

  public static void main(String[] args) throws IOException {

    File file = new File("/Users/chenhao/Downloads/list.txt");
    InputStreamReader i = new InputStreamReader(new FileInputStream(file),"UTF-8");
    BufferedReader br = new BufferedReader(i);
    String line;
    while ((line = br.readLine()) != null) {
      kList.add(line);
    }

    File cfile = new File("/Users/chenhao/Downloads/mylist.txt");
    InputStreamReader ci = new InputStreamReader(new FileInputStream(cfile),"UTF-8");
    BufferedReader cbr = new BufferedReader(ci);
    String cline;
    while ((cline = cbr.readLine()) != null) {
      CList.add(cline);
    }

    System.out.println(CList.size());
    System.out.println(kList.size());


  }

}
