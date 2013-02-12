package EAC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class SaveDlg extends JFrame {
  File SaveFile;
  JPanel ContentPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel ButtonsPanel2 = new JPanel();
  JButton Yes = new JButton();
  GridLayout gridLayout1 = new GridLayout();
  JButton No = new JButton();
  JPanel ButtonsPanel = new JPanel();
  JLabel Label2 = new JLabel();
  JLabel Label1 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel MessagePanel = new JPanel();
  JLabel FilaNameLabel = new JLabel();

  public SaveDlg(File File) {
    SaveFile = File;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setIconImage(MainFrame.winIcon);
    this.setSize(new Dimension(197, 97));
    this.setTitle("Warning!");
    ContentPanel.setLayout(borderLayout1);
    Yes.addActionListener(new SaveDlg_Yes_actionAdapter(this));
    Yes.setText("Yes");
    Yes.addActionListener(new SaveDlg_Yes_actionAdapter(this));
    Yes.addActionListener(new SaveDlg_Yes_actionAdapter(this));
    No.addActionListener(new SaveDlg_No_actionAdapter(this));
    No.setText("No");
    No.addActionListener(new SaveDlg_No_actionAdapter(this));
    No.addActionListener(new SaveDlg_No_actionAdapter(this));
    ButtonsPanel.setLayout(gridLayout1);
    Label2.setText("Do you want to continue?");
    Label1.setText("  has exised.");
    MessagePanel.setLayout(gridBagLayout1);
    FilaNameLabel.setText(SaveFile.getName());
    this.getContentPane().add(ContentPanel,  BorderLayout.CENTER);
    ContentPanel.add(ButtonsPanel2,  BorderLayout.SOUTH);
    ButtonsPanel2.add(ButtonsPanel, null);
    ButtonsPanel.add(Yes, null);
    ButtonsPanel.add(No, null);
    ContentPanel.add(MessagePanel,  BorderLayout.CENTER);
    MessagePanel.add(Label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    MessagePanel.add(FilaNameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    MessagePanel.add(Label2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  void Yes_actionPerformed(ActionEvent e) {
    SaveFile Save = new SaveFile();
    Save.saveFile(SaveFile);//セーブ
    dispose();
  }

  void No_actionPerformed(ActionEvent e) {
    MainFrame.StatusBar.setText("Saving file was canceled.");
    dispose();
  }
}

class SaveDlg_Yes_actionAdapter implements java.awt.event.ActionListener {
  SaveDlg adaptee;

  SaveDlg_Yes_actionAdapter(SaveDlg adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Yes_actionPerformed(e);
  }
}

class SaveDlg_No_actionAdapter implements java.awt.event.ActionListener {
  SaveDlg adaptee;

  SaveDlg_No_actionAdapter(SaveDlg adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.No_actionPerformed(e);
  }
}
