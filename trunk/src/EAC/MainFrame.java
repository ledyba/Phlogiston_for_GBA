package EAC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import java.io.File;
import javax.swing.filechooser.*;


import EAC.controller.*;
import java.awt.event.*;
/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class MainFrame extends JFrame {
  /*自分で定義したもの　はじめ*/
  public static final String SettingFileName = "Phlogiston.ini";
  public static final String KeySettingFileName = "Key.ini";
  public static final String DefaultTitle = "Phlogiston for GBA";
  public static final String FileType = ".pecf";
  //ファイル入出力関係
  public static String AutoOpenFile;//オートマチックにファイルを開くか？
  public static String AutoOpenFileName;//オートマチックにファイルを開くときのパス

  public static final String AutoOpenFile_Default = "false";//オートマチックにファイルを開くか？
  public static final String AutoOpenFileName_Default = "";//オートマチックにファイルを開くときのパス

  public static boolean isOpen = false;//今ファイルを開いているか否か
  public static String OpeningFileName;//今開いているファイル名
  public static String Title;
  private static boolean isEndDlgOpened = false;//EndDlgは開いてますか？
  //アイコン
  public static IconLoader iconLoader = new IconLoader();
  public static Image winIcon;
  //デフォルト設定
  public static final String Buttons_Selected_Default = "true";//右側のバー
  public static final String ReciprocatingSelected_Default = "false";//往復運動は標準？
  public static final String AutofireSelected_Default = "false";//連射は標準？
  public static final String KeepingSelected_Default = "true";//状態維持は標準？
  public static final String ReciprocatingInterval_Default = "0";//往復運動の時間は？？
  public static final String AutoFireTime_Default = "0";//連射の時間は？

  public static final String LRUD_FirstKey_Default = "0";//往復運動の標準は？
  public static final String ABLE_FirstKey_Default = "0";//往復運動の標準は？

  public static final String LRUD_Keep_KeyDefault = "0";//押し続けるキーの標準は？(上下上下)
  public static final String ABLR_Keep_KeyDefault = "1";//押し続けるキーの標準は？(ボタン)

  //エミュレータのキー（VBA仕様）
  public static int Left;
  public static int Right;
  public static int Up;
  public static int Down;

  public static int A;
  public static int B;
  public static int L;
  public static int R;

  public static int Start;
  public static int Select;
  //キーおわり
  //キーデフォルト
  public static final int Left_default = KeyEvent.VK_LEFT;
  public static final int Right_default = KeyEvent.VK_RIGHT;
  public static final int Up_default = KeyEvent.VK_UP;
  public static final int Down_default = KeyEvent.VK_DOWN;

  public static final int A_default = KeyEvent.VK_Z;
  public static final int B_default = KeyEvent.VK_X;
  public static final int L_default = KeyEvent.VK_A;
  public static final int R_default = KeyEvent.VK_S;

  public static final int Start_default = KeyEvent.VK_ENTER;
  public static final int Select_default = KeyEvent.VK_BACK_SPACE;

  //ショートカットキー用
  public static int[][] ShortCutKey = new int[2][2];
  public static boolean[][] ShortCutKeyState = new boolean[2][2];
  //押されているかどうかの判断につかう
  //フォーカスをウインドウが失ったら自動的にクリアすること
  //(そうしないと多分正常にうごかない)
  public static final String[][] ShortCutKeyDefault = {
      {String.valueOf(0),String.valueOf(KeyEvent.VK_SPACE)},
      {String.valueOf(0),String.valueOf(KeyEvent.VK_SPACE)},
  };

  public static final String ShortCutKeyTitle[] = {
      "ShortCut.BeginingControlling.",//+キーの数字
      "ShortCut.StoppingControlling.",//+キーの数字
  };

  private static controller controller = null;//コントローラ
  private static KeySetting_Frame KeySetting_Frame;//キー設定用
  private static SettingDlg SettingDlg;//セッティングフレーム
  Setting Setting = new Setting();//セッティングの読みこみ・かきこみ
  public static String LR_KeyNameList[] = {
      "None",
      "Left",
      "Right"
  };

  public static String UD_KeyNameList[] = {
      "None",
      "Up",
      "Down"
  };
  public static String ON_OFF_KeyNameList[] = {
        "On",
        "Off"
    };
  /*自分で定義したもの　おわり*/
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel Main_Panel = new JPanel();
  JPanel R_Conf_Panel = new JPanel();
  JPanel B_Conf_Panel = new JPanel();
  JPanel A_Conf_Panel = new JPanel();
  JPanel UD_Conf_Panel = new JPanel();
  JPanel L_Conf_Panel = new JPanel();
  JTabbedPane ButtonSelect_TabPanel = new JTabbedPane();
  JPanel LR_Conf_Panel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  private static JButton StopButton = new JButton();
  JPanel Controll_Buttons_Panel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel Switch_Panel = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private static JButton BeginButton = new JButton();
  JPanel AllSwitchListPanel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel Start_Conf_Panel = new JPanel();
  JPanel Select_Conf_Panel = new JPanel();
  JPanel AllSwitchButtonPanel = new JPanel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton AllSelectButton = new JButton();
  JButton AllClearButton = new JButton();
  JPanel UnderPanel = new JPanel();
  public static JLabel StatusBar = new JLabel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel UDLR_ShowPanel = new JPanel();
  JPanel ShowNowSelected = new JPanel();
  public static JCheckBox Show_U_Box = new JCheckBox();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  public static JCheckBox Show_R_Box = new JCheckBox();
  public static JCheckBox Show_L_Box = new JCheckBox();
  public static JCheckBox Show_D_Box = new JCheckBox();
  JPanel StartSelect_ShowPanel = new JPanel();
  public static JCheckBox Show_Start_Box = new JCheckBox();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  public static JCheckBox Show_Select_Box = new JCheckBox();
  JPanel AB_ShowPanel = new JPanel();
  public static JCheckBox Show_B_Box = new JCheckBox();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  public static JCheckBox Show_A_Box = new JCheckBox();
  JPanel LR_Show_Panel = new JPanel();
  public static JCheckBox Show_LButton_Box = new JCheckBox();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  public static JCheckBox Show_RButton_Box = new JCheckBox();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JScrollPane AllButtons_ScrollPane = new JScrollPane();
  public static JList AllButtons = new JList();
  TitledBorder titledBorder1;
  JPanel LR_reciprocating_Panel = new JPanel();
  GridBagLayout gridBagLayout9 = new GridBagLayout();
  public static JRadioButton LR_reciprocating_Radio = new JRadioButton();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  JLabel LR_reciprocating_interval_Label = new JLabel();
  public static JTextField LR_reciprocating_interval_Field = new JTextField();
  JLabel LR_reciprocating_first_key_Label = new JLabel();
  public static JComboBox LR_reciprocating_first_key_ComboBox = new JComboBox(LR_KeyNameList);
  JPanel LR_keeping_Panel = new JPanel();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  public static JRadioButton LR_keeping_Radio = new JRadioButton();
  JLabel LR_keeping_state_label = new JLabel();
  public static JComboBox LR_keeping_key_ComboBox = new JComboBox(LR_KeyNameList);
  GridBagLayout gridBagLayout11 = new GridBagLayout();
  public static JComboBox UD_reciprocating_first_key_ComboBox = new JComboBox(UD_KeyNameList);
  public static JRadioButton UD_reciprocating_Radio = new JRadioButton();
  GridBagLayout gridBagLayout12 = new GridBagLayout();
  public static JComboBox UD_keeping_key_ComboBox = new JComboBox(UD_KeyNameList);
  public static JRadioButton UD_keeping_Radio = new JRadioButton();
  JPanel UD_keeping_Panel = new JPanel();
  JLabel UD_reciprocating_interval_Label = new JLabel();
  JPanel UD_reciprocating_Panel = new JPanel();
  JLabel UD_reciprocating_first_key_Label = new JLabel();
  JLabel UD_keeping_state_label = new JLabel();
  public static JTextField UD_reciprocating_interval_Field = new JTextField();
  GridBagLayout gridBagLayout13 = new GridBagLayout();
  GridBagLayout gridBagLayout14 = new GridBagLayout();
  JPanel A_Autofire_Panel = new JPanel();
  GridBagLayout gridBagLayout15 = new GridBagLayout();
  public static JRadioButton A_Autofire_Radio = new JRadioButton();
  JLabel A_Autofire_speed_Label = new JLabel();
  public static JTextField A_Autofire_speed_Field = new JTextField();
  JLabel A_Autofire_firstState_label = new JLabel();
  public static JComboBox A_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel A_keeping_panel = new JPanel();
  GridBagLayout gridBagLayout16 = new GridBagLayout();
  public static JRadioButton A_keeping_Radio = new JRadioButton();
  JLabel A_keeping_state = new JLabel();
  public static JComboBox A_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  GridBagLayout gridBagLayout17 = new GridBagLayout();
  GridBagLayout gridBagLayout18 = new GridBagLayout();
  GridBagLayout gridBagLayout19 = new GridBagLayout();
  GridBagLayout gridBagLayout20 = new GridBagLayout();
  GridBagLayout gridBagLayout21 = new GridBagLayout();
  JLabel B_Autofire_firstState_label = new JLabel();
  JLabel B_Autofire_speed_Label = new JLabel();
  public static JRadioButton B_Autofire_Radio = new JRadioButton();
  JLabel B_keeping_state = new JLabel();
  public static JComboBox B_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  public static JTextField B_Autofire_speed_Field = new JTextField();
  GridBagLayout gridBagLayout110 = new GridBagLayout();
  public static JComboBox B_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel B_keeping_panel = new JPanel();
  JPanel B_Autofire_Panel = new JPanel();
  public static JRadioButton B_keeping_Radio = new JRadioButton();
  GridBagLayout gridBagLayout111 = new GridBagLayout();
  JLabel L_Autofire_firstState_label = new JLabel();
  JLabel L_Autofire_speed_Label = new JLabel();
  public static JRadioButton L_Autofire_Radio = new JRadioButton();
  public static JComboBox L_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JLabel L_keeping_state = new JLabel();
  public static JTextField L_Autofire_speed_Field = new JTextField();
  GridBagLayout gridBagLayout112 = new GridBagLayout();
  public static JComboBox L_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel L_Autofire_Panel = new JPanel();
  JPanel L_keeping_panel = new JPanel();
  public static JRadioButton L_keeping_Radio = new JRadioButton();
  GridBagLayout gridBagLayout113 = new GridBagLayout();
  JLabel R_Autofire_firstState_label = new JLabel();
  JLabel R_Autofire_speed_Label = new JLabel();
  public static JRadioButton R_Autofire_Radio = new JRadioButton();
  public static JComboBox R_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JLabel R_keeping_state = new JLabel();
  public static JTextField R_Autofire_speed_Field = new JTextField();
  GridBagLayout gridBagLayout114 = new GridBagLayout();
  public static JComboBox R_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel R_Autofire_Panel = new JPanel();
  JPanel R_keeping_panel = new JPanel();
  public static JRadioButton R_keeping_Radio = new JRadioButton();
  GridBagLayout gridBagLayout115 = new GridBagLayout();
  JLabel Start_Autofire_firstState_label = new JLabel();
  JLabel Start_Autofire_speed_Label = new JLabel();
  public static JRadioButton Start_Autofire_Radio = new JRadioButton();
  public static JComboBox Start_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JLabel Start_keeping_state = new JLabel();
  public static JTextField Start_Autofire_speed_Field = new JTextField();
  public static JComboBox Start_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel Start_Autofire_Panel = new JPanel();
  JPanel Start_keeping_panel = new JPanel();
  public static JRadioButton Start_keeping_Radio = new JRadioButton();
  GridBagLayout gridBagLayout116 = new GridBagLayout();
  GridBagLayout gridBagLayout117 = new GridBagLayout();
  JLabel Select_Autofire_firstState_label = new JLabel();
  JLabel Select_Autofire_speed_Label = new JLabel();
  public static JRadioButton Select_Autofire_Radio = new JRadioButton();
  public static JComboBox Select_Autofire_firstState_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JLabel Select_keeping_state = new JLabel();
  public static JTextField Select_Autofire_speed_Field = new JTextField();
  public static JComboBox Select_keeping_state_ComboBox = new JComboBox(ON_OFF_KeyNameList);
  JPanel Select_Autofire_Panel = new JPanel();
  JPanel A_keeping_panel1 = new JPanel();
  public static JRadioButton Select_keeping_Radio = new JRadioButton();
  GridBagLayout gridBagLayout118 = new GridBagLayout();
  GridBagLayout gridBagLayout119 = new GridBagLayout();
  ////ラジオボックスのグループ
  //LR
  public static ButtonGroup LR_ModeGroup = new ButtonGroup();
  //UD
  public static ButtonGroup UD_ModeGroup = new ButtonGroup();
  //A
  public static ButtonGroup A_ModeGroup = new ButtonGroup();
  //B
  public static ButtonGroup B_ModeGroup = new ButtonGroup();
  //L
  public static ButtonGroup L_ModeGroup = new ButtonGroup();
  //R
  public static ButtonGroup R_ModeGroup = new ButtonGroup();
  //Start
  public static ButtonGroup Start_ModeGroup = new ButtonGroup();
  //Select
  public static ButtonGroup Select_ModeGroup = new ButtonGroup();
  JMenu Settings_Menu = new JMenu();
  JMenuItem KeySettings_Menu = new JMenuItem();
  JMenuItem Save_As_Menu = new JMenuItem();
  JMenuItem Save_Menu = new JMenuItem();
  JMenuItem Open_Menu = new JMenuItem();
  JMenuItem New_Menu = new JMenuItem();
  public static JTextField FileNameField = new JTextField();
  JMenuItem SettingMenu = new JMenuItem();
  //フレームのビルド
  public MainFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    enableEvents(AWTEvent.KEY_EVENT_MASK);//ショートカット専用
    try {
      //コンフィグファイルのよみこみ
      Conf();
      //キーコンフィグのよみこみ
      KeyConf();
      //初期設定
      jbInit();
      AutoOpen();
      AddShortCut();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public void AddShortCut(){
      //ショートカットの登録
      if(ShortCutKey[1][0] == ShortCutKey[0][0]
          && ShortCutKey[1][1] == ShortCutKey[0][1]){//両方とも同じキーならば
        ShortCutKeyAction sca = new ShortCutKeyAction();
        KeyStroke sck = KeyStroke.getKeyStroke(ShortCutKey[0][1],
                                               ShortCutKey[0][0], false);
        sca.setKey(3);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sck,
            String.valueOf(3));
          getRootPane().getActionMap().put(String.valueOf(3), sca);
          //キーを３として登録
      }else{
        for (int i = 0; i <= 1; i++) {
          ShortCutKeyAction sca = new ShortCutKeyAction();
          KeyStroke sck = KeyStroke.getKeyStroke(ShortCutKey[i][1],
                                                 ShortCutKey[i][0], false);
          sca.setKey(i);
          getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sck,
              String.valueOf(i));
          getRootPane().getActionMap().put(String.valueOf(i), sca);
        }
        //それぞれのアクションマップを登録
      }

    /*this.getLayeredPane().addKeyListener(new KeyListener(){
    public void keyPressed(KeyEvent e){
      System.out.println("Hello");
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  });*/

  }
  //コンポーネントの初期化
  private void jbInit(){
      /*
      JComboBoxが選択されてしまうのに，Focusは関係ない！
       MyFocusManager MyFocusManager = new MyFocusManager();
      MyFocusManager.setCurrentManager(MyFocusManager);
    */
    try{
    winIcon = iconLoader.load("icon.png");
    this.setIconImage(winIcon);
    SettingMenu.setText("Settings");
    SettingMenu.addActionListener(new MainFrame_SettingMenu_actionAdapter(this));
    Settings_Menu.add(SettingMenu);
    /*自分で書いた　はじめ*/
    //複数選択可能に
    AllButtons.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    //自作リストモデル
    MyListModel MyListModel = new MyListModel();
    AllButtons.setModel(MyListModel);
    //自作セレクションモデル
    MyListSelectionModel MyListSelectionModel = new MyListSelectionModel();
    AllButtons.setSelectionModel(MyListSelectionModel);
    //マウスリスナ
    listMouseListener listMouseListener = new listMouseListener();
    AllButtons.addMouseListener(listMouseListener);

    //ラジオボタングループ
    //LR
    Save_As_Menu.setText("Save As");
    Save_As_Menu.addActionListener(new MainFrame_Save_As_Menu_actionAdapter(this));
    Save_Menu.setText("Save");
    Save_Menu.addActionListener(new MainFrame_Save_Menu_actionAdapter(this));
    Open_Menu.setText("Open");
    Open_Menu.addActionListener(new MainFrame_Open_Menu_actionAdapter(this));
    New_Menu.setText("New File");
    New_Menu.addActionListener(new MainFrame_New_Menu_actionAdapter(this));
    FileNameField.setEnabled(true);
    FileNameField.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    FileNameField.setLocale(java.util.Locale.getDefault());
    FileNameField.setName("");
    FileNameField.setVisible(true);
    FileNameField.setAlignmentY((float) 0.5);
    FileNameField.setAutoscrolls(false);
    FileNameField.setDoubleBuffered(false);
    FileNameField.setMaximumSize(new Dimension(30, 21));
    FileNameField.setMinimumSize(new Dimension(6, 21));
    FileNameField.setOpaque(true);
    FileNameField.setPreferredSize(new Dimension(30, 21));
    FileNameField.setCaretPosition(0);
    FileNameField.setDisabledTextColor(Color.lightGray);
    FileNameField.setEditable(false);
    FileNameField.setText("none");
    FileNameField.setColumns(0);
    FileNameField.setHorizontalAlignment(SwingConstants.LEADING);
    LR_ModeGroup.add(LR_reciprocating_Radio);
    LR_ModeGroup.add(LR_keeping_Radio);
    //UD
    UD_ModeGroup.add(UD_reciprocating_Radio);
    UD_ModeGroup.add(UD_keeping_Radio);
    //A
    A_ModeGroup.add(A_Autofire_Radio);
    A_ModeGroup.add(A_keeping_Radio);
    //B
    B_ModeGroup.add(B_Autofire_Radio);
    B_ModeGroup.add(B_keeping_Radio);
    //L
    L_ModeGroup.add(L_Autofire_Radio);
    L_ModeGroup.add(L_keeping_Radio);
    //R
    R_ModeGroup.add(R_Autofire_Radio);
    R_ModeGroup.add(R_keeping_Radio);
    //Start
    Start_ModeGroup.add(Start_Autofire_Radio);
    Start_ModeGroup.add(Start_keeping_Radio);
    //Select
    Select_ModeGroup.add(Select_Autofire_Radio);
    Select_ModeGroup.add(Select_keeping_Radio);

    /*自分で書いた　おわり*/
    StopButton.addActionListener(new MainFrame_StopButton_actionAdapter(this));
    Show_LButton_Box.setEnabled(false);
    Show_LButton_Box.setOpaque(true);
    Show_LButton_Box.setBorderPainted(false);
    Show_LButton_Box.setContentAreaFilled(true);
    Show_LButton_Box.setSelected(false);
    Show_LButton_Box.setBorderPaintedFlat(false);
    Show_RButton_Box.setEnabled(false);
    Show_U_Box.setEnabled(false);
    Show_U_Box.setDoubleBuffered(false);
    Show_R_Box.setEnabled(false);
    Show_L_Box.setEnabled(false);
    Show_D_Box.setEnabled(false);
    Show_B_Box.setEnabled(false);
    Show_A_Box.setEnabled(false);
    Show_Start_Box.setEnabled(false);
    Show_Select_Box.setEnabled(false);
    Settings_Menu.setText("Settings");
    KeySettings_Menu.setText("Key Setting");
    KeySettings_Menu.addActionListener(new MainFrame_KeySettings_Menu_actionAdapter(this));

    BeginButton.addActionListener(new MainFrame_BeginButton_actionAdapter(this));
    A_keeping_panel.setLayout(gridBagLayout16);
    A_keeping_Radio.setText("Keeping the state  Mode");
    A_keeping_state.setText("Which state?");
    jMenuFile.setActionCommand("File");
    jMenuFileExit.setActionCommand("Exit");
    jMenuHelp.setActionCommand("Help");
    jMenuHelpAbout.setActionCommand("About");
    A_Conf_Panel.setLayout(gridBagLayout14);
    A_Autofire_Panel.setLayout(gridBagLayout15);
    A_Autofire_Radio.setToolTipText("");
    A_Autofire_Radio.setText("Autofire Mode");
    A_Autofire_speed_Label.setText("Speed(Shot per second)");
    A_Autofire_speed_Field.setText("0");
    A_Autofire_firstState_label.setText("First State");
    UD_Conf_Panel.setDebugGraphicsOptions(0);
    UD_Conf_Panel.setLayout(gridBagLayout11);
    UD_reciprocating_Radio.setText("Reciprocating Mode");
    UD_keeping_Radio.setText("Keeping the state Mode");
    UD_keeping_Radio.setToolTipText("");
    UD_keeping_Panel.setLayout(gridBagLayout12);
    UD_keeping_Panel.setMinimumSize(new Dimension(10, 10));
    UD_reciprocating_interval_Label.setToolTipText("");
    UD_reciprocating_interval_Label.setText("Interval(sec):");
    UD_reciprocating_Panel.setLayout(gridBagLayout13);
    UD_reciprocating_first_key_Label.setText("First Key:");
    UD_keeping_state_label.setText("Which state?");
    UD_reciprocating_interval_Field.setText("0");
    titledBorder1 = new TitledBorder("");
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(537, 418));
    setTitle(DefaultTitle);
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new MainFrame_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new MainFrame_jMenuHelpAbout_ActionAdapter(this));
    Main_Panel.setDebugGraphicsOptions(0);
    Main_Panel.setLayout(borderLayout3);
    StopButton.setRolloverEnabled(false);
    StopButton.setText("Stop");
    Switch_Panel.setLayout(borderLayout2);
    Controll_Buttons_Panel.setLayout(gridBagLayout1);
    BeginButton.setText("Fire");
    AllSwitchListPanel.setLayout(borderLayout4);
    AllSwitchButtonPanel.setAlignmentX((float) 0.5);
    AllSwitchButtonPanel.setLayout(gridBagLayout2);
    AllSelectButton.setText("All Select");
    AllSelectButton.addActionListener(new MainFrame_AllSelectButton_actionAdapter(this));
    AllClearButton.setText("All Clear");
    AllClearButton.addActionListener(new MainFrame_AllClearButton_actionAdapter(this));
    StatusBar.setText("Status");
    UnderPanel.setLayout(borderLayout5);
    UDLR_ShowPanel.setLayout(gridBagLayout4);
    ShowNowSelected.setLayout(gridBagLayout3);
    Show_U_Box.setText("U");
    Show_R_Box.setText("R");
    Show_L_Box.setText("L");
    Show_D_Box.setText("D");
    Show_Start_Box.setText("Start");
    StartSelect_ShowPanel.setLayout(gridBagLayout5);
    Show_Select_Box.setText("Select");
    Show_B_Box.setText("B");
    AB_ShowPanel.setDebugGraphicsOptions(0);
    AB_ShowPanel.setLayout(gridBagLayout6);
    Show_A_Box.setText("A");
    LR_Show_Panel.setLayout(gridBagLayout7);
    Show_LButton_Box.setToolTipText("");
    Show_LButton_Box.setText("L Button");
     Show_RButton_Box.setText("R Button");
    AllButtons.setEnabled(true);
    AllButtons.setLocale(java.util.Locale.getDefault());
    AllButtons.setDebugGraphicsOptions(0);
    AllButtons.setOpaque(true);
    AllButtons.setVerifyInputWhenFocusTarget(false);
    AllButtons.setSelectedIndex(-1);
    ShowNowSelected.setBorder(titledBorder1);
    LR_Conf_Panel.setLayout(gridBagLayout8);
    LR_reciprocating_Panel.setLayout(gridBagLayout9);
    LR_reciprocating_Radio.setText("Reciprocating Mode");
    LR_reciprocating_interval_Label.setToolTipText("");
    LR_reciprocating_interval_Label.setText("Interval(sec):");
    LR_reciprocating_interval_Field.setText("0");
    LR_reciprocating_first_key_Label.setText("First Key:");
    LR_keeping_Panel.setMinimumSize(new Dimension(10, 10));
    LR_keeping_Panel.setLayout(gridBagLayout10);
    LR_keeping_Radio.setToolTipText("");
    LR_keeping_Radio.setText("Keeping the state Mode");
    LR_keeping_state_label.setText("Which state?");
    jMenuFile.add(New_Menu);
    jMenuFile.addSeparator();
    jMenuFile.add(Open_Menu);
    jMenuFile.addSeparator();
    jMenuFile.add(Save_Menu);
    jMenuFile.add(Save_As_Menu);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(Settings_Menu);
    jMenuBar1.add(jMenuHelp);
    B_Conf_Panel.setLayout(gridBagLayout17);
    L_Conf_Panel.setLayout(gridBagLayout18);
    R_Conf_Panel.setLayout(gridBagLayout19);
    Start_Conf_Panel.setLayout(gridBagLayout20);
    Select_Conf_Panel.setLayout(gridBagLayout21);
    B_Autofire_firstState_label.setText("First State");
    B_Autofire_speed_Label.setText("Speed(Shot per second)");
    B_Autofire_Radio.setText("Autofire Mode");
    B_Autofire_Radio.setToolTipText("");
    B_keeping_state.setText("Which state?");
    B_Autofire_speed_Field.setText("0");
    B_keeping_panel.setLayout(gridBagLayout111);
    B_Autofire_Panel.setLayout(gridBagLayout110);
    B_keeping_Radio.setText("Keeping the state  Mode");
    L_Autofire_firstState_label.setText("First State");
    L_Autofire_speed_Label.setText("Speed(Shot per second)");
    L_Autofire_Radio.setText("Autofire Mode");
    L_Autofire_Radio.setToolTipText("");
    L_keeping_state.setText("Which state?");
    L_Autofire_speed_Field.setText("0");
    L_Autofire_Panel.setLayout(gridBagLayout112);
    L_keeping_panel.setLayout(gridBagLayout113);
    L_keeping_Radio.setText("Keeping the state  Mode");
    R_Autofire_firstState_label.setText("First State");
    R_Autofire_speed_Label.setText("Speed(Shot per second)");
    R_Autofire_Radio.setText("Autofire Mode");
    R_Autofire_Radio.setToolTipText("");
    R_keeping_state.setText("Which state?");
    R_Autofire_speed_Field.setText("0");
    R_Autofire_Panel.setLayout(gridBagLayout114);
    R_keeping_panel.setLayout(gridBagLayout115);
    R_keeping_Radio.setText("Keeping the state  Mode");
    R_keeping_state_ComboBox.setDebugGraphicsOptions(0);
    Start_Autofire_firstState_label.setText("First State");
    Start_Autofire_speed_Label.setText("Speed(Shot per second)");
    Start_Autofire_Radio.setText("Autofire Mode");
    Start_Autofire_Radio.setToolTipText("");
    Start_keeping_state.setText("Which state?");
    Start_Autofire_speed_Field.setText("0");
    Start_Autofire_Panel.setLayout(gridBagLayout116);
    Start_keeping_panel.setLayout(gridBagLayout117);
    Start_keeping_Radio.setText("Keeping the state  Mode");
    Select_Autofire_firstState_label.setText("First State");
    Select_Autofire_speed_Label.setText("Speed(Shot per second)");
    Select_Autofire_Radio.setText("Autofire Mode");
    Select_Autofire_Radio.setToolTipText("");
    Select_keeping_state.setText("Which state?");
    Select_Autofire_speed_Field.setText("0");
    Select_Autofire_Panel.setLayout(gridBagLayout118);
    A_keeping_panel1.setLayout(gridBagLayout119);
    Select_keeping_Radio.setText("Keeping the state  Mode");
    LR_keeping_Radio.setSelected(true);
    UD_keeping_Radio.setSelected(true);
    A_keeping_state_ComboBox.setVerifyInputWhenFocusTarget(true);
    A_keeping_state_ComboBox.setSelectedIndex(1);
    A_keeping_Radio.setSelected(true);
    B_keeping_Radio.setSelected(true);
    B_keeping_state_ComboBox.setSelectedIndex(1);
    L_keeping_Radio.setSelected(true);
    L_keeping_state_ComboBox.setSelectedIndex(1);
    R_keeping_Radio.setSelected(true);
    R_keeping_state_ComboBox.setSelectedIndex(1);
    Start_keeping_Radio.setSelected(true);
    Start_keeping_state_ComboBox.setOpaque(true);
    Start_keeping_state_ComboBox.setSelectedIndex(1);
    Select_keeping_state_ComboBox.setSelectedIndex(1);
    Select_keeping_Radio.setSelected(true);
    StopButton.setEnabled(false);

    this.setJMenuBar(jMenuBar1);
    contentPane.add(Main_Panel,  BorderLayout.CENTER);
    ButtonSelect_TabPanel.add(LR_Conf_Panel, "LR");
    LR_Conf_Panel.add(LR_reciprocating_Panel,              new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ButtonSelect_TabPanel.add(UD_Conf_Panel, "UD");
    UD_keeping_Panel.add(UD_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_keeping_Panel.add(UD_keeping_state_label, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_keeping_Panel.add(UD_keeping_key_ComboBox,    new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    UD_Conf_Panel.add(UD_reciprocating_Panel,       new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_reciprocating_Panel.add(UD_reciprocating_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_reciprocating_Panel.add(UD_reciprocating_first_key_Label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_reciprocating_Panel.add(UD_reciprocating_first_key_ComboBox,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    UD_reciprocating_Panel.add(UD_reciprocating_interval_Label, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UD_reciprocating_Panel.add(UD_reciprocating_interval_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    UD_Conf_Panel.add(UD_keeping_Panel,     new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ButtonSelect_TabPanel.add(A_Conf_Panel,  "A Button");
    A_Conf_Panel.add(A_Autofire_Panel,              new GridBagConstraints(0, 0, 2, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_Autofire_Panel.add(A_Autofire_Radio,               new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_Autofire_Panel.add(A_Autofire_speed_Label,              new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ButtonSelect_TabPanel.add(B_Conf_Panel,  "B Button");
    B_keeping_panel.add(B_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_keeping_panel.add(B_keeping_state, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_keeping_panel.add(B_keeping_state_ComboBox,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    B_Conf_Panel.add(B_Autofire_Panel,    new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_Autofire_Panel.add(B_Autofire_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_Autofire_Panel.add(B_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    B_Autofire_Panel.add(B_Autofire_speed_Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_Autofire_Panel.add(B_Autofire_firstState_label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    B_Autofire_Panel.add(B_Autofire_firstState_ComboBox,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    B_Conf_Panel.add(B_keeping_panel,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ButtonSelect_TabPanel.add(L_Conf_Panel,  "L Button");
    ButtonSelect_TabPanel.add(R_Conf_Panel,  "R Button");
    ButtonSelect_TabPanel.add(Start_Conf_Panel,  "Start");
    Main_Panel.add(Switch_Panel,  BorderLayout.SOUTH);
    Switch_Panel.add(Controll_Buttons_Panel, BorderLayout.EAST);
    contentPane.add(UnderPanel, BorderLayout.SOUTH);
    UnderPanel.add(ShowNowSelected,  BorderLayout.SOUTH);
    Main_Panel.add(ButtonSelect_TabPanel, BorderLayout.CENTER);
    Controll_Buttons_Panel.add(StopButton,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Controll_Buttons_Panel.add(BeginButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Switch_Panel.add(StatusBar,  BorderLayout.CENTER);
    Main_Panel.add(AllSwitchListPanel, BorderLayout.EAST);
    ButtonSelect_TabPanel.add(Select_Conf_Panel,  "Select");
    AllSwitchListPanel.add(AllSwitchButtonPanel,  BorderLayout.SOUTH);
    AllSwitchButtonPanel.add(AllSelectButton,     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    AllSwitchButtonPanel.add(AllClearButton,    new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    AllSwitchListPanel.add(AllButtons_ScrollPane, BorderLayout.CENTER);
    AllSwitchListPanel.add(FileNameField, BorderLayout.NORTH);
    AllButtons_ScrollPane.getViewport().add(AllButtons, null);
    UDLR_ShowPanel.add(Show_L_Box,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UDLR_ShowPanel.add(Show_R_Box,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UDLR_ShowPanel.add(Show_D_Box,  new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    UDLR_ShowPanel.add(Show_U_Box,  new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ShowNowSelected.add(StartSelect_ShowPanel,        new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    StartSelect_ShowPanel.add(Show_Start_Box, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    StartSelect_ShowPanel.add(Show_Select_Box, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ShowNowSelected.add(AB_ShowPanel,        new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
    AB_ShowPanel.add(Show_B_Box,        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    AB_ShowPanel.add(Show_A_Box, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ShowNowSelected.add(LR_Show_Panel,             new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_Show_Panel.add(Show_RButton_Box,         new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 40, 0, 0), 0, 0));
    LR_Show_Panel.add(Show_LButton_Box,       new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
    ShowNowSelected.add(UDLR_ShowPanel,             new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 50), 0, 0));
    LR_reciprocating_Panel.add(LR_reciprocating_Radio,                  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_reciprocating_Panel.add(LR_reciprocating_first_key_Label,           new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_reciprocating_Panel.add(LR_reciprocating_first_key_ComboBox,          new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    LR_reciprocating_Panel.add(LR_reciprocating_interval_Label,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_reciprocating_Panel.add(LR_reciprocating_interval_Field,    new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    LR_Conf_Panel.add(LR_keeping_Panel,          new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_keeping_Panel.add(LR_keeping_Radio,           new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_keeping_Panel.add(LR_keeping_state_label,             new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    LR_keeping_Panel.add(LR_keeping_key_ComboBox,          new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    A_Autofire_Panel.add(A_Autofire_firstState_label,    new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_Autofire_Panel.add(A_Autofire_firstState_ComboBox,        new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    A_Autofire_Panel.add(A_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    A_Conf_Panel.add(A_keeping_panel,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel.add(A_keeping_Radio,        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel.add(A_keeping_state,        new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel.add(A_keeping_state_ComboBox,           new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    L_Autofire_Panel.add(L_Autofire_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_Autofire_Panel.add(L_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    L_Autofire_Panel.add(L_Autofire_speed_Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_Autofire_Panel.add(L_Autofire_firstState_label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_Autofire_Panel.add(L_Autofire_firstState_ComboBox,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    L_Conf_Panel.add(L_keeping_panel,      new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_keeping_panel.add(L_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_keeping_panel.add(L_keeping_state, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    L_keeping_panel.add(L_keeping_state_ComboBox,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    L_Conf_Panel.add(L_Autofire_Panel,   new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_Autofire_Panel.add(R_Autofire_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_Autofire_Panel.add(R_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    R_Autofire_Panel.add(R_Autofire_speed_Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_Autofire_Panel.add(R_Autofire_firstState_label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_Autofire_Panel.add(R_Autofire_firstState_ComboBox,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    R_Conf_Panel.add(R_keeping_panel,     new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_keeping_panel.add(R_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_keeping_panel.add(R_keeping_state, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    R_keeping_panel.add(R_keeping_state_ComboBox,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    R_Conf_Panel.add(R_Autofire_Panel,    new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_Autofire_Panel.add(Start_Autofire_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_Autofire_Panel.add(Start_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    Start_Autofire_Panel.add(Start_Autofire_speed_Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_Autofire_Panel.add(Start_Autofire_firstState_label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_Autofire_Panel.add(Start_Autofire_firstState_ComboBox,   new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 0));
    Start_Conf_Panel.add(Start_keeping_panel,     new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_keeping_panel.add(Start_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_keeping_panel.add(Start_keeping_state, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Start_keeping_panel.add(Start_keeping_state_ComboBox,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    Start_Conf_Panel.add(Start_Autofire_Panel,   new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Select_Autofire_Panel.add(Select_Autofire_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Select_Autofire_Panel.add(Select_Autofire_speed_Field,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    Select_Autofire_Panel.add(Select_Autofire_speed_Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Select_Autofire_Panel.add(Select_Autofire_firstState_label, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Select_Autofire_Panel.add(Select_Autofire_firstState_ComboBox,     new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    Select_Conf_Panel.add(A_keeping_panel1,     new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel1.add(Select_keeping_Radio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel1.add(Select_keeping_state, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    A_keeping_panel1.add(Select_keeping_state_ComboBox,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
        ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
    Select_Conf_Panel.add(Select_Autofire_Panel,   new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
        ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    Settings_Menu.add(KeySettings_Menu);
  }catch(Exception e){
    e.printStackTrace();
  }
}
public void AutoOpen(){
  if(AutoOpenFile.equals("true") && isOpen == false){
    File SaveFile = new File(AutoOpenFileName);
      if(SaveFile.canRead()){
        Setting Setting = new Setting();
        Setting.ReadSetting(SaveFile);
        this.OpeningFileName = SaveFile.getPath();//名前を設定
        FileNameField.setText(SaveFile.getName());//ファイル名変更
        isOpen = true;//開かれているというフラグをたてる
        StatusBar.setText("Reading File ("+SaveFile.getName()+") has finished.");//通知
      }
    }else{
      Setting.DefaultSetting();//オートオープンが無効ならば，デフォルト設定に
    }
  }
  public void setTitle(String e){
    Title = e;
    this.repaint();
  }
  public String getTitle(){//タイトル変更がしたい・・・でも出来ない・・・．
    return Title;
  }
  public static void Conf() throws Exception{
    File Conf = new File(SettingFileName);
    if(Conf.canRead()){//設定ファイルがあるならば
      Properties prop = new Properties();
      // プロパティファイルからキーと値のリストを読み込みます
      prop.load(new FileInputStream(Conf));
      AutoOpenFile = prop.getProperty("AutoOpen.enabled",AutoOpenFile_Default);
      AutoOpenFileName = prop.getProperty("AutoOpen.path",AutoOpenFileName_Default);

      /*ショートカット*/
      for(int i=0;i<=1;i++){
        for(int j=0;j<=1;j++){
          ShortCutKey[i][j] =
              Integer.parseInt(prop.getProperty(ShortCutKeyTitle[i]+String.valueOf(j),ShortCutKeyDefault[i][j]));
        }
      }
      /*ショートカットおわり*/

    }else{
      //初期設定
      Properties prop = new Properties();
      prop.setProperty("AutoOpen.enabled",AutoOpenFile_Default);
      prop.setProperty("AutoOpen.path",AutoOpenFileName_Default);//パスを見る
      prop.store(new FileOutputStream(Conf), "Settings");
      //そして再帰
      Conf();
    }
    return;
  }
  public static void KeyConf(){
    try {
      File Conf = new File(KeySettingFileName);
      if(Conf.canRead()){
        //設定をよみこむ
        Properties prop = new Properties();
        // プロパティファイルからキーと値のリストを読み込みます
        prop.load(new FileInputStream(KeySettingFileName));

        Left = Integer.parseInt(prop.getProperty("Left" , Integer.toString(Left_default)));
        Right = Integer.parseInt(prop.getProperty("Right", Integer.toString(Right_default)));
        Up = Integer.parseInt(prop.getProperty("Up", Integer.toString(Up_default)));
        Down = Integer.parseInt(prop.getProperty("Down", Integer.toString(Down_default)));

        L = Integer.parseInt(prop.getProperty("L", Integer.toString(L_default)));
        R = Integer.parseInt(prop.getProperty("R", Integer.toString(R_default)));
        A = Integer.parseInt(prop.getProperty("A", Integer.toString(A_default)));
        B = Integer.parseInt(prop.getProperty("B", Integer.toString(B_default)));

        Start = Integer.parseInt(prop.getProperty("Start", Integer.toString(Start_default)));
        Select = Integer.parseInt(prop.getProperty("Select", Integer.toString(Select_default)));
      }else{
        //デフォルトのキー設定を保存する
          Properties prop = new Properties();
          prop.setProperty("Left",String.valueOf(Left_default));
          prop.setProperty("Right",String.valueOf(Right_default));
          prop.setProperty("Up",String.valueOf(Up_default));
          prop.setProperty("Down",String.valueOf(Down_default));

          prop.setProperty("L",String.valueOf(L_default));
          prop.setProperty("R",String.valueOf(R_default));
          prop.setProperty("A",String.valueOf(A_default));
          prop.setProperty("B",String.valueOf(B_default));

          prop.setProperty("Start",String.valueOf(Start_default));
          prop.setProperty("Select",String.valueOf(Select_default));
          // プロパティのリストをファイルに保存します
          prop.store(new FileOutputStream(KeySettingFileName), "Key Settings");
          //そしてまたここを呼びます
          KeyConf();
      }

    } catch (Exception er) {
      er.printStackTrace();
    }

  }
  //[ファイル|終了]
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  //[ヘルプ|バージョン情報]
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }
  //ウィンドウが閉じられたときに終了するようにオーバー
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      StopRunning();//とりあえずボタン選択解除
      jMenuFileExit_actionPerformed(null);
    }else if(e.getID() == WindowEvent.WINDOW_DEACTIVATED){//非アクティブになった
      for(int i=0;i<=1;i++){
        for(int j=0;j<=1;j++){
          ShortCutKeyState[i][j] = false;//キーが押されているかの判定はクリアする
        }
      }
    }
  }
  /*
  protected void processFocusEvent(FocusEvent e){
    return;
  }*/
  /*
  protected void processKeyEvent(KeyEvent e){//キー処理はここで受け取れ！
    super.processKeyEvent(e);
    if(e.getID() == KeyEvent.KEY_PRESSED){
      for(int i=0;i<=1;i++){
        for(int j=0;j<=1;j++){
          if(e.getKeyCode() == MainFrame.ShortCutKey[i][j]){
            MainFrame.ShortCutKeyState[i][j] = true; //キーが押されているかの判定を入れる
          }
        }
      }
      ShortCutControlling();
    }else if(e.getID() == KeyEvent.KEY_RELEASED){
      for(int i=0;i<=1;i++){
        for(int j=0;j<=1;j++){
          if(e.getKeyCode() == ShortCutKey[i][j]){
            ShortCutKeyState[i][j] = false; //キーが押されているかの判定を入れる
          }
        }
      }
    }
  }*/
  void ShortCutControlling(){
      if(isRunning()){//動作中なら
        if(ShortCutKeyState[1][0] && ShortCutKeyState[1][1]){
          StopRunning();
        }
      }else{
        if(ShortCutKeyState[0][0] && ShortCutKeyState[0][1]){
          BeginRunnning();
        }
      }
  }
  static boolean isDoing(){
    boolean check = false;
    if(controller != null){//動作中
      check = true;
    }
    if(KeySetting_Frame != null && KeySetting_Frame.isShowing()){//インスタンスがつくられていて，なおかつアクティブ
      check = true;
    }
    if(SettingDlg != null && SettingDlg.isShowing()){
      check = true;
    }
    if(getIsEndDlgOpened()){
      check = true;
    }
    return check;
  }
  public static void setIsEndDlgOpend(boolean Flag){
    isEndDlgOpened = Flag;
  }
  public static boolean getIsEndDlgOpened(){
    return isEndDlgOpened;
  }
  static boolean isRunning(){//動作中？
    return controller != null;
  }
  void AllSelectButton_actionPerformed(ActionEvent e) { //AllSelect
    AllButtons.addSelectionInterval(0,AllButtons.getModel().getSize()-1);
  }

  void AllClearButton_actionPerformed(ActionEvent e) { //AllClear
    AllButtons.clearSelection();
  }
  class MyListModel extends AbstractListModel{//リストモデル
    public String ButtonNameList[] = {
        "L and R",
        "U and D",
        "A Button",
        "B Button",
        "L Button",
        "R Button",
        "Start Button",
        "Select Button",
    };
    public int getSize(){//サイズを返す
      return ButtonNameList.length;
    }

    public Object getElementAt(int index){//値を返す
      return ButtonNameList[index];
    }

  }

  class MyListSelectionModel extends DefaultListSelectionModel{//選択関係の実装
    public boolean SelectedButton[] = new boolean[AllButtons.getModel().getSize()];//選択されたかされてないかの判定配列
    public boolean MouseSelected = false;//マウスの状態によって処理を分けるために使います．

    public void clearSelection(){
      //すべて選択クリア
      for(int i=0;i<AllButtons.getModel().getSize();i++){
        SelectedButton[i]=false;
      }
      changed();
    }
    public void addSelectionInterval(int index0, int index1){
      if(index0 ==-1 || index1 == -1){
        return;
      }
      //選択範囲を、現在の選択範囲と index0 以上 index1 以下のインデックスの示す範囲との和集合に変更します。
      for(int i=Math.min(index0,index1);i<=Math.max(index0,index1);i++){
        SelectedButton[i]=true;
      }
      changed();
    }

    public void setSelectionInterval(int index0, int index1){
      //選択範囲を、現在の選択範囲と index0 以上 index1 以下のインデックスの示す範囲に変更します。
      //今回は，CTRLキーを押さなくてもよいように，反転するという処理に
      //CTRLキーなしにマウスを押すとこのメソッドが呼ばれるようです
      if(index0 ==-1 || index1 == -1){
        return;
      }
      for(int i=Math.min(index0,index1);i<=Math.max(index0,index1);i++){
        if(listMouseListener.MouseState == true){
          SelectedButton[i] = true;
        }else{
          SelectedButton[i] = !SelectedButton[i];
        }
      }
      changed();
    }
    public void removeSelectionInterval(int index0, int index1){//選択場所を差集合に変更します．
      for(int i=Math.min(index0,index1);i<=Math.max(index0,index1);i++){
        SelectedButton[i]=false;
      }
      changed();
    }
    public boolean isSelectedIndex(int index){//セルが選択されているかを返す
      return SelectedButton[index];
    }
    public void changed(){//変更を反映させる
      AllButtons.revalidate();
      AllButtons.repaint();
      return;
    }

  }
  static class listMouseListener implements MouseListener{
    //押していないとfalse
    //押しているとTrue
    static boolean MouseState = false;
    public void mousePressed ( MouseEvent e ){
      MouseState = true;
    }
    public void mouseReleased ( MouseEvent e ){
      MouseState = false;
    }
    public void mouseClicked ( MouseEvent e ){
    }
    public void mouseEntered ( MouseEvent e ){//コンポ－ネント中に入った！

    }
    public void mouseExited ( MouseEvent e ){//出てしまった
      //MouseState = false;
    }
  }

  void BeginButton_actionPerformed(ActionEvent e) {//「Fire」：オートマチック操作を実装
    BeginRunnning();
  }
  public static void BeginRunnning(){
    if(!isDoing()){//設定中で無ければ
      StopButton.setEnabled(true); //ストップボタンを有効にする
      BeginButton.setEnabled(false); //このボタンを無効にする
      controller = new controller();
      controller.run();
      StatusBar.setText("Automatic Controlling has started.");
    }
  }
  void StopButton_actionPerformed(ActionEvent e) {//「Stop」：自動操縦を停止させる
    StopRunning();
  }
  static void StopRunning(){
    BeginButton.setEnabled(true);//開始ボタンを有効にする
    StopButton.setEnabled(false);//このボタンを無効にする
    if(controller != null){//スレッドが動いていたら
      controller.exit();
      controller = null;
    }
    StatusBar.setText("Automatic Controlling has stopped.");
  }
  void KeySettings_Menu_actionPerformed(ActionEvent e) {//キーの設定
    if(isDoing() == false){//操縦・設定中でなければ
      KeySetting_Frame = new KeySetting_Frame();
      Dimension dlgSize = KeySetting_Frame.getSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      KeySetting_Frame.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      //dlg.pack();
      //dlg.setSize(dlg.ContentPanel.preferredSize());
      KeySetting_Frame.setVisible(true);
    }else{//操縦中！
      StatusBar.setText("You can't set the keys when the soft is doing something.");
    }
  }

  void Open_Menu_actionPerformed(ActionEvent e) {//開く
    File Folder = new File("./pecf/");
    Folder.mkdir();
    JFileChooser chooser = new JFileChooser( "./pecf/" );
    PECF_FileFilter Filter = new PECF_FileFilter();
    chooser.setFileFilter(Filter);
    int selected = chooser.showOpenDialog(this);
    if(selected == JFileChooser.APPROVE_OPTION) {//ファイルが開かれた
      Setting Setting = new Setting();
      Setting.ReadSetting(chooser.getSelectedFile());//読む！
      StatusBar.setText("Reading File ("+chooser.getSelectedFile().getName()+") has finished.");//通知
    }
  }
  void New_Menu_actionPerformed(ActionEvent e) {
    //開かれていないのだからフラグを下げる
    isOpen = false;
    Setting Setting = new Setting();
    Setting.DefaultSetting();//デフォルト
    StatusBar.setText("New File Opened");
  }

  void Save_Menu_actionPerformed(ActionEvent e) {
    new_file_make();
  }

  void Save_As_Menu_actionPerformed(ActionEvent e) {
    if(isOpen){
      File SaveFile = new File(OpeningFileName);
      Setting Setting = new Setting();
      Setting.SaveSetting(SaveFile);
      StatusBar.setText("Saving file as "+SaveFile.getName()+"has finished.");//通知
    }else{//開いていないんだから・・・
      new_file_make();
    }
  }
  void new_file_make(){//新規保存
    File Folder = new File("./pecf/");
    Folder.mkdir();
    JFileChooser chooser = new JFileChooser( "./pecf/" );
    PECF_FileFilter Filter = new PECF_FileFilter();
    chooser.setFileFilter(Filter);
    int selected = chooser.showSaveDialog(this);
    if(selected == JFileChooser.APPROVE_OPTION) {//ファイルが保存
      File SaveFile;
      if (chooser.getSelectedFile().getName().toLowerCase().endsWith(FileType)) { //特別な拡張子で終わる
        SaveFile = new File(chooser.getSelectedFile().getPath());
      }
      else {
        SaveFile = new File(chooser.getSelectedFile().getPath() + FileType);
      }
      if(SaveFile.canRead()){//上書きになってしまう
        showSaveDlg(SaveFile);
      }else{
        SaveFile Save = new SaveFile();
        Save.saveFile(SaveFile);//セーブ
      }
    }
  }
  void showSaveDlg(File SaveFile){
    SaveDlg SaveDlg = new SaveDlg(SaveFile);
    Dimension dlgSize = SaveDlg.getSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    SaveDlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    SaveDlg.setVisible(true);
  }

  void SettingMenu_actionPerformed(ActionEvent e) {//セッティングメニュー
    if(isDoing() == false){//操縦・設定中でなければ
      showSettingDlg();
    }else{//操縦中！
      StatusBar.setText("You can't set the keys when the soft is doing something.");
    }
  }
  void showSettingDlg(){
    File Conf = new File(SettingFileName);
    SettingDlg = new SettingDlg(Conf);
    Dimension dlgSize = SettingDlg.getSize();
    Dimension frmSize = getSize();
    Point loc = this.getLocation();
    SettingDlg.setLocation( ((frmSize.width - dlgSize.width) / 2) + loc.x,
                   ((frmSize.height - dlgSize.height) / 2) + loc.y);

    SettingDlg.setVisible(true);
  }

}

class PECF_FileFilter extends javax.swing.filechooser.FileFilter {
  public boolean accept(File f){
    String name = f.getName().toLowerCase(); ;
    if(f != null){
      if(f.isDirectory()) {//ディレクトリなら・・・
        return true;
      }else{
        if (name.endsWith(MainFrame.FileType)) { //独自の拡張子^^;
          return true;
        }
        else {
          return false;
        }
      }
      }
      return false;
  }
  public String getDescription(){
    return "Phlogiston Emulator Conrotoll File (*.pecf)";
  }
}

class MainFrame_jMenuFileExit_ActionAdapter implements ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuFileExit_ActionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class MainFrame_AllSelectButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_AllSelectButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.AllSelectButton_actionPerformed(e);
  }
}

class MainFrame_AllClearButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_AllClearButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.AllClearButton_actionPerformed(e);
  }
}

class MainFrame_BeginButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_BeginButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BeginButton_actionPerformed(e);
  }
}

class MainFrame_StopButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_StopButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.StopButton_actionPerformed(e);
  }
}

class MainFrame_KeySettings_Menu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_KeySettings_Menu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.KeySettings_Menu_actionPerformed(e);
  }
}

class MainFrame_Open_Menu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_Open_Menu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Open_Menu_actionPerformed(e);
  }
}

class MainFrame_New_Menu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_New_Menu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.New_Menu_actionPerformed(e);
  }
}

class MainFrame_Save_Menu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_Save_Menu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Save_Menu_actionPerformed(e);
  }
}

class MainFrame_Save_As_Menu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_Save_As_Menu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Save_As_Menu_actionPerformed(e);
  }
}

class MainFrame_SettingMenu_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_SettingMenu_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.SettingMenu_actionPerformed(e);
  }
}
class ShortCutKeyAction extends AbstractAction{//ショートカットキー動作
  private int KEY=0;
  public void actionPerformed(ActionEvent e) {
    //System.out.println(KEY+":"+MainFrame.isRunning());//デバッグ用メッセージ
    if(MainFrame.isRunning()){
      if(KEY == 1 || KEY == 3){
        MainFrame.StopRunning();
      }
    }else{
      if(KEY == 0 || KEY == 3){
        MainFrame.BeginRunnning();
      }
    }
  }
  public void setKey(int key){
    KEY = key;
  }
}
