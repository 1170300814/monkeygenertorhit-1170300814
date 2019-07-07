package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ladder.Ladder;
import log.Mylog;
import monkey.Monkey;

public class MonkeyGenerator {
  private int n = 0;
  private int h = 0;
  private int t = 0;
  private static int N = 0;
  private int k = 0;
  private int MV = 0;
  private static int num = 0;
  private Set<Ladder> ladders = Collections.synchronizedSet(new HashSet<>());

  public void checkRep() {
    assert n != 0 && h != 0 && t != 0 && N != 0 && k != 0 && MV != 0;
  }

  public void MonkeyGeneratorv1() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("输入参数：");
    System.out.println("输入 n:");
    n = scanner.nextInt();
    System.out.println("输入 h:");
    h = scanner.nextInt();
    System.out.println("输入 t:");
    t = scanner.nextInt();
    System.out.println("输入 N:");
    N = scanner.nextInt();
    System.out.println("输入 k:");
    k = scanner.nextInt();
    System.out.println("输入 MV:");
    MV = scanner.nextInt();
    // n = 5;
    // h = 20;
    // t = 3;
    // N = 20;
    // k = 2;
    // MV = 5;
    checkRep();
    for (int i = 0; i < n; i++) {
      ladders.add(new Ladder(i + 1));
    }
    long time1 = System.currentTimeMillis();
    List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    ExecutorService runtime = Executors.newCachedThreadPool();
    int times = N / k;
    int rest = N % k;
    for (int i = 0; i < times; i++) {
      for (int j = 0; j < k; j++) {
        int choice = (int) ((Math.random() * 3) + 1);
        num++;
        runtime.execute(new Thread(new Monkey(num, Math.random() > 0.5 ? "R->L" : "L->R",
            (int) ((Math.random() * 5) + 1), ladders, list, choice)));
      }
      Thread.sleep(t * 1000);
    }
    for (int m = 0; m < rest; m++) {
      int choice = (int) ((Math.random() * 3) + 1);
      num++;
      runtime.execute(new Thread(new Monkey(num, Math.random() > 0.5 ? "R->L" : "L->R",
          (int) ((Math.random() * 5) + 1), ladders, list, choice)));
    }
    long time2 = 0;
    runtime.shutdown();
    while (true) {
      if (runtime.isTerminated()) {
        time2 = System.currentTimeMillis();
        break;
      }
    }

    int num = 0;
    int num1 = 0;
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        num1++;
        if (list.get(j) < list.get(i)) {
          num++;
        }
      }
    }
    Mylog.logger.info("吞吐率为：" + N * 1.0 / ((time2 - time1) / 1000));
    Mylog.logger.info("公平性为：" + ((num1 - 2 * num) * 1.0 / num1));;
  }

  public void MonkeyGeneratorv2() throws InterruptedException {
    int choice = 3;
    Scanner scanner = new Scanner(System.in);
    System.out.println("输入参数：");
    System.out.println("输入 n:");
    n = scanner.nextInt();
    System.out.println("输入 h:");
    h = scanner.nextInt();
    System.out.println("输入 t:");
    t = scanner.nextInt();
    System.out.println("输入 N:");
    N = scanner.nextInt();
    System.out.println("输入 k:");
    k = scanner.nextInt();
    System.out.println("输入 MV:");
    MV = scanner.nextInt();
    // n = 5;
    // h = 20;
    // t = 1;
    // N = 20;
    // k = 10;
    // MV = 5;
    checkRep();
    for (int i = 0; i < n; i++) {
      ladders.add(new Ladder(i + 1));
    }
    long time1 = System.currentTimeMillis();
    List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    ExecutorService runtime = Executors.newCachedThreadPool();
    int times = N / k;
    int rest = N % k;
    for (int i = 0; i < times; i++) {
      for (int j = 0; j < k; j++) {
        runtime.execute(new Thread(new Monkey(++num, Math.random() > 0.5 ? "R->L" : "L->R",
            Math.random() > 0.5 ? 1 : 5, ladders, list, choice)));
      }
      Thread.sleep(t * 1000);
    }
    for (int m = 0; m < rest; m++) {
      runtime.execute(new Thread(new Monkey(++num, Math.random() > 0.5 ? "R->L" : "L->R",
          Math.random() > 0.5 ? 1 : 5, ladders, list, choice)));
    }
    // (int) ((Math.random() * 5) + 1)
    long time2 = 0;
    runtime.shutdown();
    while (true) {
      if (runtime.isTerminated()) {
        time2 = System.currentTimeMillis();
        break;
      }
    }

    int num = 0;
    int num1 = 0;
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        num1++;
        if (list.get(j) < list.get(i)) {
          num++;
        }
      }
    }
    Mylog.logger.info("吞吐率为：" + N * 1.0 / ((time2 - time1) / 1000));
    Mylog.logger.info("公平性为：" + ((num1 - 2 * num) * 1.0 / num1));
  }

  public void MonkeyGeneratorv3() throws InterruptedException {
    int cout1 = 0;
    k = 2;
    MV = 5;
    t = 3;
    int times = N / k;
    int rest = N % k;
    long time1 = System.currentTimeMillis();
    long time2;
    List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    ExecutorService runtime = Executors.newCachedThreadPool();
    File file = new File("src/Competition_1.txt");
    if (file.isFile() && file.exists()) {
      InputStreamReader reader = null;
      try {
        reader = new InputStreamReader(new FileInputStream(file));
      } catch (FileNotFoundException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      BufferedReader bufferedReader = new BufferedReader(reader);
      String lineString = null;
      int cout = 0;
      try {
        while ((lineString = bufferedReader.readLine()) != null) {
          // System.out.println(lineString);
          String paString = "(h|n)(=)(\\d*)";
          Pattern pattern = Pattern.compile(paString);
          Matcher ma = pattern.matcher(lineString);
          String paString2 = "(<)(\\d*)(,)(\\d*)(,)(\\D*)(,)(\\d)(>)";
          Pattern pattern2 = Pattern.compile(paString2);
          Matcher matcher = pattern2.matcher(lineString);
          if (ma.find()) {
            cout++;
            // System.out.println("match success1\n" + ma.group(3));
            if (cout == 1) {
              n = Integer.parseInt(ma.group(3));
            }
            if (cout == 2) {
              h = Integer.parseInt(ma.group(3));
              break;
            }
          }

          for (int i = 0; i < n; i++) {
            ladders.add(new Ladder(i + 1));
          }

          cout++;
          if (cout > 2) {
            if (matcher.find()) {
              cout1++;
              int choice = 2;
              // System.out.println("match success2\n" + matcher.group(6));
              String dieString = matcher.group(6);
              int speed = Integer.parseInt(matcher.group(8));
              int num = Integer.parseInt(matcher.group(4));
              int time = Integer.parseInt(matcher.group(2));
              runtime.execute(new Monkey(num, dieString, speed, ladders, list, choice, time));
              // Thread.sleep(1000);
            }

          }
        }
        bufferedReader.close();
        reader.close();
        time2 = System.currentTimeMillis();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println("file is wrong");
        e.printStackTrace();
      }
    } else {
      System.out.println("file is not exist");
    }
    runtime.shutdown();
    while (true) {
      if (runtime.isTerminated()) {
        time2 = System.currentTimeMillis();
        break;
      }
    }
    N = cout1;
    checkRep();
    int num = 0;
    int num1 = 0;
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        num1++;
        if (list.get(j) < list.get(i)) {
          num++;
        }
      }
    }
    double fair = (num1 - 2 * num) * 1.0 / num1;
    Mylog.logger.info("吞吐率为：" + N * 1.0 / ((time2 - time1) / 1000));
    Mylog.logger.info("公平性为：" + fair);
    System.out.printf("%d %d %d", num1, num, cout1);
  }

}
