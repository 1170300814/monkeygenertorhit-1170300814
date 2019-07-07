package strategy;

import java.util.Set;
import ladder.Ladder;
import log.Mylog;
import monkey.Monkey;

public class Strategy3 implements Context {

  @Override
  public Ladder goaction(Monkey monkey, Set<Ladder> set) {
    while (true) {
      for (Ladder ladder : set) {
        synchronized (ladder) {
          if (ladder.startandset() == false) {
            ladder.setDirection(monkey.getDirection());
            ladder.setnumofmonkey(1);
            return ladder;
          }
        }
      }
      if ((System.currentTimeMillis() - monkey.getbegintime()) % 1000 == 0)
        Mylog.logger.info("monkey" + monkey.getID() + "wait"
            + (System.currentTimeMillis() - monkey.getbegintime()) + "ms");
    }
  }
}
