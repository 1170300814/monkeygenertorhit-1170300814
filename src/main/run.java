package main;

import log.Mylog;

public class run {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    // long start2 = System.currentTimeMillis();
    // MonkeyGenerator run2 = new MonkeyGenerator();
    // try {
    // run2.MonkeyGeneratorv1();
    // } catch (InterruptedException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // long end2 = System.currentTimeMillis();
    // Mylog.logger.info("MonkeyGeneratorv1 cost " + (end2 - start2) + "ms");
    // System.out.println("MonkeyGeneratorv1 cost " + (end2 - start2) + "ms");



    // long start = System.currentTimeMillis();
    // MonkeyGenerator run = new MonkeyGenerator();
    // try {
    // run.MonkeyGeneratorv2();
    // } catch (InterruptedException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // long end = System.currentTimeMillis();
    // Mylog.logger.info("MonkeyGeneratorv1 cost " + (end - start) + "ms");
    // System.out.println("MonkeyGeneratorv1 cost " + (end - start) + "ms");
    //
    //

    long start1 = System.currentTimeMillis();
    MonkeyGenerator run1 = new MonkeyGenerator();
    try {
      run1.MonkeyGeneratorv3();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    long end1 = System.currentTimeMillis();
    Mylog.logger.info("MonkeyGeneratorv1 cost " + (end1 - start1) + "ms");
    System.out.println("MonkeyGeneratorv1 cost " + (end1 - start1) + "ms");
  }
}
