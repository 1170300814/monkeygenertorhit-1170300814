package ladder;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class test {
  public static JTextArea ta_log = new JTextArea(10, 10);

  public static void textarea() {
    // TODO Auto-generated method stub

    Frame f = new Frame("My First Test");
    f.setLocation(300, 300);
    f.setSize(500, 500);
    f.setBackground(Color.blue);
    f.setResizable(false);
    f.setVisible(true);
    ta_log.setLineWrap(true);
    ta_log.setWrapStyleWord(true);
    JScrollPane p_log = new JScrollPane(ta_log);
    ta_log.append("\r\n");
    f.add(ta_log);
  }

}
