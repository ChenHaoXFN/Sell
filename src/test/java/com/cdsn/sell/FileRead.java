package com.cdsn.sell;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * java 查询文件夹下所有继承
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-03 09:19
 */
public class FileRead {

  // 指定目录
//  private static final String searchPath = "/Users/chenhao/Downloads/fpm_main";
  private static final String searchPath = "/Users/chenhao/workspace/myself/sell";
  // 指定继承的类
//  private static final String extName = "CMISOperation";
  private static final String extName = "BaseEntity";


  // 是否是第一次遍历，查询的是一级继承关系
  private static boolean isOne = true;
  // 记录符合条件文件名，文件地址
  private static Map<String, String> map = new HashMap<>();
  private static List<String> result = new ArrayList<>();
  // 记录有可能存在多次继承关系的类
  private static List<String> extList = new ArrayList<>();
  private static List<String> secondList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    // 1、先找到直接继承的类
    recursiveFiles(searchPath);

    // 2、 再去找间接继承
    isOne = false;

    while (!extList.isEmpty()) {
      recursiveFiles(searchPath);
      extList.clear();
      extList.addAll(secondList);
      secondList.clear();
    }

    // 输出最终结果
    printResult();
  }


  private static void recursiveFiles(String path) throws IOException {
    File file = new File(path);

    File[] files = file.listFiles();
    // 目录下文件
    assert files != null;
//    if (files.length == 0) {
//      System.out.println(path + "该文件夹下没有文件");
//    }

    // 存在文件 遍历 判断
    for (File f : files) {
      // 判断是否为 文件夹
      if (f.isDirectory()) {

//        System.out.print("文件夹: ");
//        System.out.println(f.getAbsolutePath());

        // 为 文件夹继续遍历
        recursiveFiles(f.getAbsolutePath());

        // 判断是否为 文件
      } else if (f.isFile()) {

//        System.out.print("文件: ");
//        System.out.println(f.getAbsolutePath());
        // 读取文件内容

        fileRead(f);

      } else {
        System.out.print("未知错误文件");
      }

    }
  }

  /**
   * 判断该类是否符合标准
   */
  private static void fileRead(File f) throws IOException {

    InputStreamReader is = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(is);
    String line;
    while ((line = br.readLine()) != null) {
      // 该类存在继承关系
//      line.trim();
//      line.replaceAll("\\s*", "");
      if (line.contains("class") && line.contains("extends")) {
        getResult(f, line);

      }

    }
  }

  private static void getResult(File f, String line) {
    // 单继承
    // 这个类有继承关系,那么判断。要么符合，要么继续递归检查最底层继承的谁加班
    // 取出继承的类名
    String extendsName;
    String[] split = line.split("\\s");
    int x = getIndex(split);
    extendsName = split[x + 1];
    if (isOne) {

      if (extendsName.contains(FileRead.extName)) {
        // ==满足条件
        map.put(f.getName(), f.getPath());
        extList.add(f.getName());
        result.add(f.getPath());
      }
    } else {
      for (String s : extList) {
        String targetName = s.replaceAll(".java", "");
        // 如果这个类继承了  已经继承基类的类，那么也符合标准
        if (extendsName.contains(targetName)) {
          map.put(f.getName(), f.getPath());
          secondList.add(f.getName());
          result.add(f.getPath());
        }
      }

    }
  }

  /*
   * 需求：查找指定数据在数组中第一次出现的索引
   */
  private static int getIndex(String[] arr) {
    //遍历数组，依次获取数组中的每一个元素，和已知的数据进行比较
    for (int x = 0; x < arr.length; x++) {
      if (arr[x].equals("extends")) {

        //如果相等，就返回当前的索引值。
        return x;
      }
    }
    return -1;
  }

  private static void printResult() {
    System.out.println("================");
    // 遍历结果map
//    Set<String> strings = map.keySet();
//    for (String string : strings) {
//      System.out.println(string + "::::" + map.get(string));
//
//    }

    System.out.println("一共有 " + map.size() + " 条满足！");
    result.stream().distinct().forEach(s -> System.out.println(s));
    System.out.println(result.size());
  }
}
