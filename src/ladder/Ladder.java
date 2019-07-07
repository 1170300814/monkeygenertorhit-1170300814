package ladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Ladder {
  private final List<Board> ladders = Collections.synchronizedList(new ArrayList<Board>());
  private boolean state = false;
  private String direction = null;
  private int numofmonkey = 0;
  private int ID;
  private int lastS = 0;

  public Ladder(int ID) {
    // ladders.add(new Board());
    for (int i = 0; i <= 20; i++) {
      ladders.add(new Board());
    }
    this.ID = ID;
  }


  public void setnumofmonkey(int numofmonkey) {
    synchronized (this) {
      this.numofmonkey = numofmonkey;
    }
  }

  public void setState(boolean state) {
    synchronized (this) {
      this.state = state;
    }
  }

  public void setDirection(String direction) {
    synchronized (this) {
      this.direction = direction;
    }
  }

  public void setlastS(int lastS) {
    synchronized (this) {
      this.lastS = lastS;
    }
  }

  public int getlastS() {
    synchronized (this) {
      return lastS;
    }
  }

  public int getID() {
    synchronized (this) {
      return ID;
    }
  }



  public int getnumofmonkey() {
    synchronized (this) {
      return numofmonkey;
    }
  }



  public boolean getState() {
    synchronized (this) {
      return state;
    }
  }

  public List<Board> getladders() {
    synchronized (this) {
      return ladders;
    }
  }

  public String getDiection() {
    synchronized (this) {
      return direction;
    }
  }

  public boolean startandset() {
    synchronized (this) {
      if (state == false) {
        state = true;
        return false;
      } else {
        return true;
      }
    }
  }
}
