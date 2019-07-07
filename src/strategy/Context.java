package strategy;

import java.util.Set;
import ladder.Ladder;
import monkey.Monkey;

public interface Context {
  public Ladder goaction(Monkey monkey, Set<Ladder> set);
}
