package strategy;

import java.util.Set;
import ladder.Ladder;
import log.Mylog;
import monkey.Monkey;

public class Strategy2 implements Context {

  @Override
  public Ladder goaction(Monkey monkey, Set<Ladder> set) {
    while (true) {
      int fast = -1;
      Ladder templadder = null;
      for (Ladder ladder : set) {
        synchronized (ladder) {
          if (ladder.startandset() == false) {
            ladder.setDirection(monkey.getDirection());
            ladder.setnumofmonkey(1);
            return ladder;
          } else if (ladder.getDiection().equals(monkey.getDirection())
              && ladder.getladders().get(1).getdoyouhavamonkey() == false) {


            // 获得最快的猴子的速度
            if (ladder.getlastS() > fast) {
              fast = ladder.getlastS();
              templadder = ladder;
            }
          }
        }
      }

      if (templadder != null) {
        synchronized (templadder) {
          templadder.setnumofmonkey(templadder.getnumofmonkey() + 1);
          return templadder;
        }
      }
      if ((System.currentTimeMillis() - monkey.getbegintime()) % 1000 == 0)
        Mylog.logger.info("monkey" + monkey.getID() + "wait"
            + (System.currentTimeMillis() - monkey.getbegintime()) + "ms");
    }
  }
}

