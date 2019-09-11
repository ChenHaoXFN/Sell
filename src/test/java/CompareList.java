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

  private static List<String> resultList = new ArrayList<>();

  public static void main(String[] args) throws IOException {

    File file = new File("/Users/chenhao/Desktop/extends.txt");
    InputStreamReader i = new InputStreamReader(new FileInputStream(file),"UTF-8");
    BufferedReader br = new BufferedReader(i);
    String line;
    while ((line = br.readLine()) != null) {
      resultList.add(line);
    }

    System.out.println(resultList.size());

  }

}
