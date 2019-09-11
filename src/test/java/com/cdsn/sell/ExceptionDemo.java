package com.cdsn.sell;

/**
 * TODO
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-10 18:24
 */
public class ExceptionDemo {

  public static void main(String[] args) {
    IllegalArgumentException root = new IllegalArgumentException("zzk");
    IllegalStateException ils = new IllegalStateException(root);
    RuntimeException runtimeException = new RuntimeException(ils);
    Throwable rootException = getRootException(runtimeException);

    System.out.println(rootException.getMessage());


  }

  private static Throwable getRootException(Throwable sourceException) {

    boolean flag = false;

    while (!flag) {
      if (sourceException.getCause() != null) {
        sourceException = sourceException.getCause();

      } else {
        flag = true;
        return sourceException;
      }
    }

    return null;
  }

}
