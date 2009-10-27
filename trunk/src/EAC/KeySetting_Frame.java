package EAC;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class KeySetting_Frame extends JFrame {
  static int Left;
  static int Right;
  static int Up;
  static int Down;
  static int A;
  static int B;
  static int L;
  static int R;
  static int Start;
  static int Select;
  JPanel ContentPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel Settings_Label = new JLabel();
  JPanel MainSettingPanel = new JPanel();
  JPanel FinishButtonPanel = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel Buttons = new JPanel();
  JButton CancelButton = new JButton();
  JButton OK_Button = new JButton();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  MyJButton Select_Setting_Button = new MyJButton();
  JPanel Side_Panel_1 = new JPanel();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  MyJButton Left_Setting_Button = new MyJButton();
  JLabel Up_Setting_Label = new JLabel();
  JLabel Right_Setting_Label = new JLabel();
  MyJButton Down_Setting_Button = new MyJButton();
  MyJButton Right_Setting_Button = new MyJButton();
  MyJButton Up_Setting_Button = new MyJButton();
  JLabel Down_Setting_Label = new JLabel();
  JLabel Left_Setting_Label = new JLabel();
  JPanel Side_Panel_2 = new JPanel();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  MyJButton A_Setting_Button = new MyJButton();
  MyJButton B_Setting_Button = new MyJButton();
  JLabel R_Setting_Label = new JLabel();
  JLabel L_Setting_Label = new JLabel();
  JLabel B_Setting_Label = new JLabel();
  MyJButton L_Setting_Button = new MyJButton();
  JLabel A_Setting_Label = new JLabel();
  MyJButton R_Setting_Button = new MyJButton();
  JPanel SIde_Panel_3 = new JPanel();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  JLabel Start_Setting_Label = new JLabel();
  MyJButton Start_Setting_Button = new MyJButton();
  JLabel Select_Setting_Label = new JLabel();
  JPanel AttentionPanel = new JPanel();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  JLabel AttentionLabel1 = new JLabel();
  JLabel AttentionLabel2 = new JLabel();
  JLabel AttentionLabel3 = new JLabel();
  JLabel AttensionLabel4 = new JLabel();
  JLabel AttentionLabel5 = new JLabel();
  JLabel AttentionLabel6 = new JLabel();
  public KeySetting_Frame() {
    Left = MainFrame.Left;
    Right = MainFrame.Right;
    Up = MainFrame.Up;
    Down = MainFrame.Down;
    A = MainFrame.A;
    B = MainFrame.B;
    L = MainFrame.L;
    R= MainFrame.R;
    Start = MainFrame.Start;
    Select = MainFrame.Select;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    /*自分で書いた*/
    /*this.addComponentListener(new ComponentAdapter(){//ウインドウのリスナの追加
    public void componentHidden(ComponentEvent e){
      KeyListenersRemove();//リスナをけしておきましょう
      KeyConfSet();//ウィンドウが開いたら設定を上書き（出来ればMyJButtonクラスに組み込みたいが・・・．めんどう．）
    }
     public void componentShown(ComponentEvent e){}
    });*///ちゃんと毎回オブジェクトを破棄するようにしたので問題は無い．
    KeyConfSet();
    Down_Setting_Button.addActionListener(new KeySetting_Frame_Down_Setting_Button_actionAdapter(this));
    Right_Setting_Button.addActionListener(new KeySetting_Frame_Right_Setting_Button_actionAdapter(this));
    Up_Setting_Button.addActionListener(new KeySetting_Frame_Up_Setting_Button_actionAdapter(this));
    L_Setting_Button.addActionListener(new KeySetting_Frame_L_Setting_Button_actionAdapter(this));
    R_Setting_Button.addActionListener(new KeySetting_Frame_R_Setting_Button_actionAdapter(this));
    A_Setting_Button.addActionListener(new KeySetting_Frame_A_Setting_Button_actionAdapter(this));
    B_Setting_Button.addActionListener(new KeySetting_Frame_B_Setting_Button_actionAdapter(this));
    Start_Setting_Button.addActionListener(new KeySetting_Frame_Start_Setting_Button_actionAdapter(this));
    Select_Setting_Button.addActionListener(new KeySetting_Frame_Select_Setting_Button_actionAdapter(this));
    this.setIconImage(MainFrame.winIcon);
    /*おわり*/
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    ContentPanel.setLayout(borderLayout1);
    Settings_Label.setBorder(titledBorder2);
    Settings_Label.setText("Key Settings");
    FinishButtonPanel.setLayout(gridBagLayout1);
    CancelButton.setText("Cancel");
    CancelButton.addActionListener(new KeySetting_Frame_CancelButton_actionAdapter(this));
    OK_Button.setText("OK");
    OK_Button.addActionListener(new KeySetting_Frame_OK_Button_actionAdapter(this));
    Buttons.setLayout(gridBagLayout2);
    this.setLocale(java.util.Locale.getDefault());
    this.setSize(new Dimension(442, 361));
    this.setState(Frame.NORMAL);
    this.setTitle("Key Settings");
    MainSettingPanel.setMaximumSize(new Dimension(32767, 32767));
    MainSettingPanel.setLayout(gridBagLayout3);

    Side_Panel_1.setLayout(gridBagLayout4);
    Left_Setting_Button.setText(getKeyText(Left));
    Left_Setting_Button.addActionListener(new KeySetting_Frame_Left_Setting_Button_actionAdapter(this));
    Up_Setting_Label.setText("Up:");
    Right_Setting_Label.setText("Right:");
    Right_Setting_Button.setOpaque(true);
    Down_Setting_Label.setText("Down:");
    Left_Setting_Label.setText("Left:");
    Side_Panel_2.setLayout(gridBagLayout5);
    R_Setting_Label.setIconTextGap(4);
    R_Setting_Label.setText("R:");
    L_Setting_Label.setAlignmentY((float) 0.5);
    L_Setting_Label.setMinimumSize(new Dimension(6, 15));
    L_Setting_Label.setText("L:");
    B_Setting_Label.setText("B:");
    A_Setting_Label.setText("A:");

    SIde_Panel_3.setLayout(gridBagLayout6);
    Start_Setting_Label.setText("Start:");
    Select_Setting_Label.setOpaque(false);
    Select_Setting_Label.setText("Select:");
    Side_Panel_2.setBorder(BorderFactory.createEtchedBorder());
    Side_Panel_2.setDebugGraphicsOptions(0);
    Side_Panel_1.setBorder(BorderFactory.createEtchedBorder());
    Side_Panel_1.setDebugGraphicsOptions(0);
    SIde_Panel_3.setBorder(BorderFactory.createEtchedBorder());
    AttentionPanel.setBorder(BorderFactory.createEtchedBorder());
    AttentionPanel.setLayout(gridBagLayout7);
    AttentionLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
    AttentionLabel1.setText("Attention !");
    AttentionLabel2.setText(
        "When you use direction keys on the keyboard to controll direction,");
    AttentionLabel3.setText("this program can\'t controll some of emulators.");
    AttensionLabel4.setText(
        "So, when this program can\'t controll your emulator,");
    AttentionLabel5.setText("please change the direction keys settings.");
    AttentionLabel6.setText("I recommend numeric keypad to direction keys");
    this.getContentPane().add(ContentPanel, BorderLayout.CENTER);
    ContentPanel.add(Settings_Label, BorderLayout.NORTH);
    ContentPanel.add(MainSettingPanel, BorderLayout.CENTER);
    ContentPanel.add(FinishButtonPanel,  BorderLayout.SOUTH);
    FinishButtonPanel.add(Buttons, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_1.add(Left_Setting_Button,    new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_1.add(Up_Setting_Label,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_1.add(Right_Setting_Label,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_1.add(Down_Setting_Button,  new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_1.add(Right_Setting_Button,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_1.add(Up_Setting_Button,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_1.add(Down_Setting_Label,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_1.add(Left_Setting_Label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_2.add(A_Setting_Button,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_2.add(B_Setting_Button,  new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_2.add(B_Setting_Label,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_2.add(R_Setting_Button,   new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_2.add(L_Setting_Label,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_2.add(L_Setting_Button,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    Side_Panel_2.add(A_Setting_Label,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Side_Panel_2.add(R_Setting_Label,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    SIde_Panel_3.add(Start_Setting_Label,        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    SIde_Panel_3.add(Start_Setting_Button,         new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    SIde_Panel_3.add(Select_Setting_Label,        new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    SIde_Panel_3.add(Select_Setting_Button, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    MainSettingPanel.add(Side_Panel_2,
                         new GridBagConstraints(1, 0, 1, 2, 1.0, 0.0
                                                , GridBagConstraints.NORTH,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 5), 5, 5));
    MainSettingPanel.add(Side_Panel_1,
                         new GridBagConstraints(0, 0, 1, 2, 1.0, 0.0
                                                , GridBagConstraints.NORTH,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 20, 5, 5), 5, 5));
    MainSettingPanel.add(SIde_Panel_3,
                         new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.NORTH,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(5, 5, 5, 20), 5, 5));
    MainSettingPanel.add(AttentionPanel, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(5, 20, 0, 20), 0, 0));
    Buttons.add(OK_Button, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                  , GridBagConstraints.CENTER,
                                                  GridBagConstraints.NONE,
                                                  new Insets(0, 0, 0, 0), 18, 0));
    Buttons.add(CancelButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    AttentionPanel.add(AttentionLabel2,
                       new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(0, 0, 0, 0), 0, 0));
    AttentionPanel.add(AttentionLabel1,
                       new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.NONE,
                                              new Insets(0, 0, 5, 0), 0, 0));
    AttentionPanel.add(AttentionLabel3,
                       new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.WEST,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(0, 0, 0, 0), 0, 0));
    AttentionPanel.add(AttensionLabel4,
                       new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.WEST,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(0, 0, 0, 0), 0, 0));
    AttentionPanel.add(AttentionLabel5,
                       new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.WEST,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(0, 0, 0, 0), 0, 0));
    AttentionPanel.add(AttentionLabel6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
  }
  public void KeyConfSet(){//キー設定をセットします
    Up_Setting_Button.setText(getKeyText(Up));
    Left_Setting_Button.setText(getKeyText(Left));
    Down_Setting_Button.setText(getKeyText(Down));
    Right_Setting_Button.setText(getKeyText(Right));
    L_Setting_Button.setText(getKeyText(L));
    R_Setting_Button.setText(getKeyText(R));
    A_Setting_Button.setText(getKeyText(A));
    B_Setting_Button.setText(getKeyText(B));
    Start_Setting_Button.setText(getKeyText(Start));
    Select_Setting_Button.setText(getKeyText(Select));
  }
  public void KeyListenersRemove(){//リスナを消します
    Down_Setting_Button.removeAllKeyListeners();
    Right_Setting_Button.removeAllKeyListeners();
    Up_Setting_Button.removeAllKeyListeners();
    L_Setting_Button.removeAllKeyListeners();
    R_Setting_Button.removeAllKeyListeners();
    A_Setting_Button.removeAllKeyListeners();
    B_Setting_Button.removeAllKeyListeners();
    Start_Setting_Button.removeAllKeyListeners();
    Select_Setting_Button.removeAllKeyListeners();
  }
  public void changeKeyStateToMainFrame(){
    //メインフレームクラスの変数を書き換えます．
    MainFrame.Left = Left;
    MainFrame.Right = Right;
    MainFrame.Down = Down;
    MainFrame.Up = Up;
    MainFrame.A = A;
    MainFrame.B = B;
    MainFrame.L = L;
    MainFrame.R = R;
    MainFrame.Start = Start;
    MainFrame.Select = Select;
  }
  public static String getKeyText(int key){//キーの値を返す
    switch(key){
      case KeyEvent.VK_UP:
        return "Up";
      case KeyEvent.VK_DOWN:
        return "Down";
      case KeyEvent.VK_LEFT:
        return "Left";
      case KeyEvent.VK_RIGHT:
        return "Right";
      default:
        return KeyEvent.getKeyText(key);
    }
  }

  void CancelButton_actionPerformed(ActionEvent e) {
    this.cancel();
  }

  //ダイアログを閉じる
  void cancel() {
    dispose();
  }

  void Left_Setting_Button_actionPerformed(ActionEvent e) {//Leftの設定
      LeftSetting LeftSetting = new LeftSetting();
      Left_Setting_Button.addKeyListener(LeftSetting);
      Left_Setting_Button.setText("Press a Key");
  }
  class LeftSetting implements KeyListener{//左を設定します
    public void keyPressed(KeyEvent e){
      Left = e.getKeyCode();
      Left_Setting_Button.setText(KeySetting_Frame.getKeyText(Left));
      Left_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class RightSetting implements KeyListener{//右を設定します
    public void keyPressed(KeyEvent e){
      Right = e.getKeyCode();
      Right_Setting_Button.setText(KeySetting_Frame.getKeyText(Right));
      Right_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class DownSetting implements KeyListener{//下を設定します
  public void keyPressed(KeyEvent e){
    Down = e.getKeyCode();
    Down_Setting_Button.setText(KeySetting_Frame.getKeyText(Down));
    Down_Setting_Button.removeKeyListener(this);//リスナは消しておく
  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
}

  class UpSetting implements KeyListener{//上を設定します
    public void keyPressed(KeyEvent e){
      Up = e.getKeyCode();
      Up_Setting_Button.setText(KeySetting_Frame.getKeyText(Up));
      Up_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }

  class ASetting implements KeyListener{//Aを設定します
    public void keyPressed(KeyEvent e){
      A = e.getKeyCode();
      A_Setting_Button.setText(KeySetting_Frame.getKeyText(A));
      A_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class BSetting implements KeyListener{//Bを設定します
    public void keyPressed(KeyEvent e){
      B = e.getKeyCode();
      B_Setting_Button.setText(KeySetting_Frame.getKeyText(B));
      B_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class LSetting implements KeyListener{//Lを設定します
    public void keyPressed(KeyEvent e){
      L = e.getKeyCode();
      L_Setting_Button.setText(KeySetting_Frame.getKeyText(L));
      L_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }

  class RSetting implements KeyListener{//Rを設定します
    public void keyPressed(KeyEvent e){
      R = e.getKeyCode();
      R_Setting_Button.setText(KeySetting_Frame.getKeyText(R));
      R_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class StartSetting implements KeyListener{//スタートを設定します
    public void keyPressed(KeyEvent e){
      Start = e.getKeyCode();
      Start_Setting_Button.setText(KeySetting_Frame.getKeyText(Start));
      Start_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }
  class SelectSetting implements KeyListener{//セレクトを設定します
    public void keyPressed(KeyEvent e){
      Select = e.getKeyCode();
      Select_Setting_Button.setText(KeySetting_Frame.getKeyText(Select));
      Select_Setting_Button.removeKeyListener(this);//リスナは消しておく
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  }


  void OK_Button_actionPerformed(ActionEvent e) {//OK
    changeKeyStateToMainFrame();//メインフレームクラスの変数を書き換える
    try {
      //キー設定を保存する
        Properties prop = new Properties();
        prop.setProperty("Left",String.valueOf(Left));
        prop.setProperty("Right",String.valueOf(Right));
        prop.setProperty("Up",String.valueOf(Up));
        prop.setProperty("Down",String.valueOf(Down));

        prop.setProperty("L",String.valueOf(L));
        prop.setProperty("R",String.valueOf(R));
        prop.setProperty("A",String.valueOf(A));
        prop.setProperty("B",String.valueOf(B));

        prop.setProperty("Start",String.valueOf(Start));
        prop.setProperty("Select",String.valueOf(Select));
        // プロパティのリストをファイルに保存します
        prop.store(new FileOutputStream(MainFrame.KeySettingFileName), "Key Settings");
    } catch (Exception er) {
      er.printStackTrace();
    }
    cancel();
  }

  void Right_Setting_Button_actionPerformed(ActionEvent e) {
    RightSetting Setting = new RightSetting();
    Right_Setting_Button.addKeyListener(Setting);
    Right_Setting_Button.setText("Press a Key");
  }

  void Up_Setting_Button_actionPerformed(ActionEvent e) {
    UpSetting Setting = new UpSetting();
    Up_Setting_Button.addKeyListener(Setting);
    Up_Setting_Button.setText("Press a Key");
  }
  void Down_Setting_Button_actionPerformed(ActionEvent e) {
    DownSetting Setting = new DownSetting();
    Down_Setting_Button.addKeyListener(Setting);
    Down_Setting_Button.setText("Press a Key");
  }
  void L_Setting_Button_actionPerformed(ActionEvent e) {
    LSetting Setting = new LSetting();
    L_Setting_Button.addKeyListener(Setting);
    L_Setting_Button.setText("Press a Key");
  }
  void R_Setting_Button_actionPerformed(ActionEvent e) {
    RSetting Setting = new RSetting();
    R_Setting_Button.addKeyListener(Setting);
    R_Setting_Button.setText("Press a Key");
  }
  void A_Setting_Button_actionPerformed(ActionEvent e) {
    ASetting Setting = new ASetting();
    A_Setting_Button.addKeyListener(Setting);
    A_Setting_Button.setText("Press a Key");
  }
  void B_Setting_Button_actionPerformed(ActionEvent e) {
    BSetting Setting = new BSetting();
    B_Setting_Button.addKeyListener(Setting);
    B_Setting_Button.setText("Press a Key");
  }

  void Start_Setting_Button_actionPerformed(ActionEvent e) {
    StartSetting Setting = new StartSetting();
    Start_Setting_Button.addKeyListener(Setting);
    Start_Setting_Button.setText("Press a Key");
  }
  void Select_Setting_Button_actionPerformed(ActionEvent e) {
    SelectSetting Setting = new SelectSetting();
    Select_Setting_Button.addKeyListener(Setting);
    Select_Setting_Button.setText("Press a Key");
  }


}

class KeySetting_Frame_CancelButton_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_CancelButton_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.CancelButton_actionPerformed(e);
  }
}

class KeySetting_Frame_Left_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Left_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Left_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_OK_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_OK_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.OK_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_Right_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Right_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Right_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_Up_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Up_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Up_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_Down_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Down_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Down_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_L_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_L_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.L_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_R_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_R_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.R_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_A_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_A_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.A_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_B_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_B_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.B_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_Start_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Start_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Start_Setting_Button_actionPerformed(e);
  }
}

class KeySetting_Frame_Select_Setting_Button_actionAdapter implements java.awt.event.ActionListener {
  KeySetting_Frame adaptee;

  KeySetting_Frame_Select_Setting_Button_actionAdapter(KeySetting_Frame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Select_Setting_Button_actionPerformed(e);
  }
}
