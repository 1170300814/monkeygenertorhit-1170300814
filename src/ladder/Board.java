package ladder;

import monkey.Monkey;

public class Board {
  private Monkey monkey = null;
  private boolean doyouhavamonkey = false;

  public void setmonkey(Monkey monkey) {
    synchronized (this) {
      this.monkey = monkey;
      if (monkey != null)
        doyouhavamonkey = true;
      else
        doyouhavamonkey = false;
    }
  }

  public boolean getdoyouhavamonkey() {
    synchronized (this) {
      return doyouhavamonkey;
    }
  }
}
