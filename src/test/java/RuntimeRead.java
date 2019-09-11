import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import	java.io.BufferedReader;
/**
 * TODO
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-04 14:29
 */
public class RuntimeRead {


  public static void main(String[] args) throws IOException {

    Process process = Runtime.getRuntime().exec("ll");

    InputStream inputStream = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(inputStream);
    BufferedReader br = new BufferedReader(isr);
    while (br.readLine() != null) {

      System.out.println(br.readLine());
    }

  }



}
