package monkey;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ladder.Ladder;
import log.Mylog;
import strategy.Context;
import strategy.Strategy1;
import strategy.Strategy2;
import strategy.Strategy3;

public class Monkey implements Runnable {
  private final int ID;
  private final String direction;
  private final int speed;
  private int actualspeed = 0;
  private Set<Ladder> set = Collections.synchronizedSet(new HashSet<>());
  private long starttime = 0;
  private List<Integer> list = null;
  private int choice = 0;

  public Monkey(int ID, String direction, int speed, Set<Ladder> set, List<Integer> list,
      int choice) {
    this.ID = ID;
    this.direction = direction;
    this.speed = speed;
    this.set = set;
    starttime = System.currentTimeMillis();
    this.list = Collections.synchronizedList(list);
    this.choice = choice;
    Mylog.logger.info("monkey" + ID + "speed" + speed + " direction " + direction);
    System.out.println("monkey" + ID + "speed" + speed + " direction " + direction);
  }

  public Monkey(int ID, String direction, int speed, Set<Ladder> set, List<Integer> list,
      int choice, long time) {
    this.ID = ID;
    this.direction = direction;
    this.speed = speed;
    this.set = set;
    starttime = time;
    this.list = Collections.synchronizedList(list);
    this.choice = choice;
    Mylog.logger.info("monkey" + ID + "speed" + speed + " direction " + direction);
    System.out.println("monkey" + ID + "speed" + speed + " direction " + direction);
  }

  public synchronized void setactualspeed(int speed) {
    this.actualspeed = speed;
  }

  public synchronized int getID() {
    return ID;
  }

  public synchronized int getactualspeed() {
    return actualspeed;
  }



  public synchronized String getDirection() {
    return direction;
  }

  public synchronized int getspeed() {
    return speed;
  }

  public synchronized long getbegintime() {
    return starttime;
  }

  @Override
  public void run() {
    Context strategy = null;
    System.out.println("in run");
    int position = 1;
    Ladder ladder = null;
    int key = 0;

    if (choice == 1) {
      System.out.println("in choice 1");
      strategy = new Strategy1();
      ladder = strategy.goaction(this, set);
    }
    if (choice == 2) {
      System.out.println("in choice 2");
      strategy = new Strategy3();
      ladder = strategy.goaction(this, set);
    }
    if (choice == 3) {
      System.out.println("in choice 3");
      strategy = new Strategy2();
      ladder = strategy.goaction(this, set);
    }

    ladder.getladders().get(position).setmonkey(this);

    while (position <= 20) {

      System.out.println("in loop");
      try {
        Thread.sleep(1000);// 一秒前进一格
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (ladder) {
        if (position == 20) {
          ladder.getladders().get(position).setmonkey(null);
          ladder.setnumofmonkey(ladder.getnumofmonkey() - 1);
          if (ladder.getnumofmonkey() == 0) {
            ladder.setState(false);
          }
          list.add(this.getID());
          break;
        }

        for (key = position + 1; key <= position + this.getspeed(); key++) {
          if (key > 20)
            break;
          if (ladder.getladders().get(key).getdoyouhavamonkey() == false) {
            continue;
          } else {
            break;
          }
        }
        ladder.getladders().get(key - 1).setmonkey(this);
        this.setactualspeed(key - 1 - position);// 设置真实的速度
        if ((key - 1) != position) {
          ladder.getladders().get(position).setmonkey(null);
        }
        position = key - 1;
        Mylog.logger.info("monkey" + this.getID() + "in" + ladder.getID() + "birdge" + (key - 1)
            + " speed" + this.getspeed() + " actialspeed" + this.getactualspeed() + " direction "
            + this.getDirection() + " time" + (System.currentTimeMillis() - this.getbegintime())
            + "ms");
      }
    }
    Mylog.logger.info("monkey" + this.getID() + "in" + ladder.getID() + "birdge" + (key - 1)
        + " speed" + this.getspeed() + " actialspeed" + this.getactualspeed() + " direction "
        + this.getDirection() + " time" + (System.currentTimeMillis() - this.getbegintime())
        + "ms");
  }
}
