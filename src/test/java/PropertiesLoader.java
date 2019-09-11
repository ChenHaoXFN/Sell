import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * 加载properties内数据
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-04 16:05
 */
public class PropertiesLoader {




//
//  public static void main(String[] args) throws IOException {
//    // 1、初始化一个 properties ，注意导包是 util
//    Properties properties = new Properties();
//
//    // 2、读取 对应properties 中的数据
//    properties.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(
//        "ds"));
//
//    // 3、获取对应数据
//    String ftp_url = properties.getProperty("ftp_url");
//    String ftp_name = properties.getProperty("ftp_name");
//    String ftp_password = properties.getProperty("ftp_password");
//
//    System.out.println("ftp_url:"+ftp_url);
//    System.out.println("ftp_name:"+ftp_name);
//    System.out.println("ftp_password:"+ftp_password);
//
//
//  }

  @Test
  public void getProperties() throws IOException {
    // 0、定义一个文件夹名,需要loader的文件夹名
    String path = "ds";
    List<String> fileNames = new ArrayList<>();

    // 1、 获得 classLoader
    ClassLoader classLoader = PropertiesLoader.class.getClassLoader();

    // 2、 获取 classpath 下的所有文件  ，注意导包是google的
    ImmutableSet<ResourceInfo> resources = ClassPath.from(classLoader).getResources();

    // 3、遍历 截取出我们需要的 ds 文件下的配置文件
    for (ResourceInfo info : resources) {
      if (info.getResourceName().startsWith(path)) {
        fileNames.add(info.getResourceName());
      }
    }

    Properties activeProperties = null;

    // 4、遍历 ds 文件夹下的配置文件，找到 enable 为true 的配置文件
    for (String fileName : fileNames) {
      if (fileName.endsWith(".properties")) {

        // 获得数据流
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        // 将数据流封装到 properties 对象中
        Properties properties = new Properties();
        properties.load(stream);
        String enable = properties.getProperty("enable");
        if (enable.equalsIgnoreCase("true")) {
          activeProperties = properties;
        }

      }
    }

    if (activeProperties == null) {
      throw new IllegalArgumentException("No active datasource config found");
    }

    // 5、获取对应数据
    String ftp_url = activeProperties.getProperty("ftp_url");
    String ftp_name = activeProperties.getProperty("ftp_name");
    String ftp_password = activeProperties.getProperty("ftp_password");

    System.out.println("ftp_url:" + ftp_url);
    System.out.println("ftp_name:" + ftp_name);
    System.out.println("ftp_password:" + ftp_password);

  }

  @Before
  public void setUp() throws IOException {
    // 设置 jvm 参数
    Properties p = new Properties();
    // 这里为 外部配置文件 全路径
    p.setProperty("sell.externalDataSource", "/Users/chenhao/Desktop/external.properties");
    System.setProperties(p);
  }

  @Test
  public void loadExternalDataSource() throws IOException {

    // 1、有没有 jvm 参数为读取外部参数
    String extDs = System.getProperty("sell.externalDataSource");

    Properties properties = null;

    // 2、load 外部配置文件
    if (extDs != null) {
      File f = new File(extDs);
      if (!f.exists()) {
        throw new RuntimeException("外部配置文件不存在");
      }
      // 3、写入 properties 对象
      properties = new Properties();
      properties.load(new FileInputStream(f));
    }

    // 4、获取对应数据
    String ftp_url = properties.getProperty("ftp_url");
    String ftp_name = properties.getProperty("ftp_name");
    String ftp_password = properties.getProperty("ftp_password");

    System.out.println("ftp_url:" + ftp_url);
    System.out.println("ftp_name:" + ftp_name);
    System.out.println("ftp_password:" + ftp_password);


  }
}
