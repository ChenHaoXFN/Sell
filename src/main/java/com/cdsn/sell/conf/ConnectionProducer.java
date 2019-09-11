package com.cdsn.sell.conf;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 加载ftp配置项
 *
 * @author ch
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-07 09:07
 */
@Component
public class ConnectionProducer {

  @Bean
  public void connect() throws IOException {

    Properties properties = getProperties();

    // 获取对应数据
    String ftp_url = properties.getProperty("ftp_url");
    String ftp_name = properties.getProperty("ftp_name");
    String ftp_password = properties.getProperty("ftp_password");

    System.out.println("ftp_url:" + ftp_url);
    System.out.println("ftp_name:" + ftp_name);
    System.out.println("ftp_password:" + ftp_password);

  }


  public Properties getProperties() throws IOException {

    Properties activeProperties = null;

    // 1、首先获取 优先级 最高的外部配置文件
    String extDs = System.getProperty("sell.externalDataSource");

    if (extDs != null) {
      // 如果 jvm 参数中存在指定配置文件为外部，则读取外部配置文件
      File dataSourceFile = new File(extDs);

      if (!dataSourceFile.exists()) {
        throw new RuntimeException("外部配置文件不存在，请重新检查路径！");
      }
      activeProperties = new Properties();
      activeProperties.load(new FileInputStream(dataSourceFile));

    } else {
      // 2、其次获取优先级为2 的 jvm 参数
      String active = System.getProperty("sell.activeDataSource");

      // 3、获取所有对应目录下的配置文件
      List<String> files = getResourceFiles("ds");

      // 4、遍历 ds 文件夹下的配置文件

      for (String fileName : files) {
        // 5、如果有jvm参数指定了配置文件名，就使用指定的
        //    如果没有jvm参数，则找到 enable 为true 的配置文件
        if (fileName.endsWith(active == null ? ".properties" : active + ".properties")) {

          // 获得数据流
          InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
          // 将数据流封装到 properties 对象中
          Properties properties = new Properties();
          properties.load(stream);

          // 如果 active 不存在，那么 找到 enable 为true的配置文件
          if (active == null || active.equalsIgnoreCase("")) {
            String enable = properties.getProperty("enable");
            if (enable.equalsIgnoreCase("true")) {
              activeProperties = properties;
            }
          } else {
            // 存在 jvm 参数指定的文件
            activeProperties = properties;
          }
        }
      }
    }
    if (activeProperties == null) {
      throw new IllegalArgumentException("No active datasource config found");
    }
    return activeProperties;

  }

  public List<String> getResourceFiles(String path) throws IOException {

    List<String> fileNames = new ArrayList<>();

    // 1、 获得 classLoader
    ClassLoader classLoader = ConnectionProducer.class.getClassLoader();

    // 2、 获取 classpath 下的所有文件  ，注意导包是google的
    ImmutableSet<ResourceInfo> resources = ClassPath.from(classLoader).getResources();

    // 3、判断当前是否在jar中运行，如果是的话，springboot 会自己在外面有 BOOT-INF 目录
    URL url = ConnectionProducer.class.getResource("");

    // 获取协议

    String protocol = url.getProtocol();
    System.out.println(protocol);

    if ("jar".equals(protocol)) {
      // jar
      path = "BOOT-INF/classes/" + path;
    }

    // 4、遍历 截取出我们需要的 ds 文件下的配置文件
    for (ResourceInfo info : resources) {
      if (info.getResourceName().startsWith(path)) {
        fileNames.add(info.getResourceName());
      }
    }
    return fileNames;
  }
}
