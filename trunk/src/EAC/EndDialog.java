package EAC;

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 *
 * <p>説明: エミュエレータを自動操縦します．</p>
 *
 * <p>著作権: Copyright (c) 2005 PSI</p>
 *
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 *
 * @author 未入力
 * @version 1.0
 */
public class EndDialog
    extends JFrame {
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel contentPane = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel TextLabel = new JLabel();
  JLabel TextLabel2 = new JLabel();
  JLabel TextLabel3 = new JLabel();
  JPanel ButtonsPanel = new JPanel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton YES = new JButton();
  JButton NO = new JButton();
  JPanel TitlePanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel TitleLabel = new JLabel();
  public EndDialog() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    //何かしているよととりあえず通知
    MainFrame.setIsEndDlgOpend(true);
   this.setIconImage(MainFrame.winIcon);
    getContentPane().setLayout(borderLayout1);
    this.setTitle("Warning!");
    contentPane.setLayout(gridBagLayout1);
    TextLabel.setText(
        "Settings will be reflected");
    TextLabel2.setText(" when this program is restarted.");
    TextLabel3.setText("Woud you like to make this program killed?");
    ButtonsPanel.setLayout(gridBagLayout2);
    YES.setPreferredSize(new Dimension(60, 23));
    YES.setText("Yes");
    YES.addActionListener(new EndDialog_YES_actionAdapter(this));
    NO.setPreferredSize(new Dimension(60, 23));
    NO.setText("No");
    NO.addActionListener(new EndDialog_NO_actionAdapter(this));
    TitlePanel.setBorder(BorderFactory.createEtchedBorder());
    TitlePanel.setLayout(borderLayout2);
    TitleLabel.setToolTipText("");
    TitleLabel.setText("Settings is not reflected now.");
    this.getContentPane().add(contentPane, java.awt.BorderLayout.CENTER);
    contentPane.add(TextLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(5, 0, 0, 0), 0, 0));
    contentPane.add(TextLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    contentPane.add(TextLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    this.getContentPane().add(ButtonsPanel, java.awt.BorderLayout.SOUTH);
    ButtonsPanel.add(YES, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(0, 0, 0, 0), 0, 0));
    ButtonsPanel.add(NO, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    this.getContentPane().add(TitlePanel, java.awt.BorderLayout.NORTH);
    TitlePanel.add(TitleLabel, java.awt.BorderLayout.CENTER);
  }

  public void YES_actionPerformed(ActionEvent e) {//終了する
    MainFrame.setIsEndDlgOpend(false);
    MainFrame.StopRunning();
    System.exit(0);
  }

  public void NO_actionPerformed(ActionEvent e) {//ダイアログを閉じるだけ
    MainFrame.setIsEndDlgOpend(false);
    this.dispose();
  }
}

class EndDialog_NO_actionAdapter
    implements ActionListener {
  private EndDialog adaptee;
  EndDialog_NO_actionAdapter(EndDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.NO_actionPerformed(e);
  }
}

class EndDialog_YES_actionAdapter
    implements ActionListener {
  private EndDialog adaptee;
  EndDialog_YES_actionAdapter(EndDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.YES_actionPerformed(e);
  }
}
