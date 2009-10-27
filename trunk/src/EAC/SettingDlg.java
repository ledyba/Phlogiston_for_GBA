package EAC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 * @author ������
 * @version 1.0
 */

public class SettingDlg extends JFrame {
  EndDialog EndDialog;

  private static String[][] ShortCut = new String[2][2];//�z��[2][2]�ł�.
  /*
   �Y�����P������
   0:begining Controlling
   1:Stopping Controlling

   �Y�����Q������
   1:�L�[�P
   2:�L�[�Q
   */
  public static final String ShortCutKeyTitle[] = {
      "ShortCut.BeginingControlling.",//+�L�[�̐���
      "ShortCut.StoppingControlling.",//+�L�[�̐���
  };

  File Conf;
  JPanel ContentPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JTabbedPane SettingsTabbedPanel = new JTabbedPane();
  JPanel PathPanel = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel AutoOpenFilePathPanel = new JPanel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JCheckBox AutoOpenFilePath_CheckBox = new JCheckBox();
  JLabel AutoOpenFilePath_Label = new JLabel();
  JButton AutoOpenFilePath_Button = new JButton();
  JTextField AutoOpenFilePath_Field = new JTextField();
  JLabel AutoOpenFilePath_titlelabel = new JLabel();
  JPanel ButtonPanel = new JPanel();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JPanel ButtonPanel2 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JButton OK_Button = new JButton();
  JButton Cancel_Button = new JButton();
  JPanel ShortCutKeySettingPanel = new JPanel();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  JPanel BeginControllingPanel = new JPanel();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  JLabel BeginControlling = new JLabel();
  MyJButton BeginControllingKey_Button1 = new MyJButton();
  JPanel StoppingControllingPanel = new JPanel();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  JLabel StoppingControllingLabel = new JLabel();
  JPanel ShotCutKeySettingPanel = new JPanel();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  JPanel PathInsidePanel = new JPanel();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  JLabel BeginingControllingKey1Label = new JLabel();
  JLabel StoppingControllingKey1Label = new JLabel();
  MyJButton StoppingControllingKey_Button1 = new MyJButton();
  JPanel ShortCut_TitlePanel = new JPanel();
  GridBagLayout gridBagLayout9 = new GridBagLayout();
  JLabel ShortCutTitleLabel = new JLabel();
  JPanel ShortCutAttensionLabel = new JPanel();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  JLabel ShortCutAttentionLabel1 = new JLabel();
  public SettingDlg(File File) {
    Conf = File;
    try {
      jbInit();
      Conf();//�ݒ�̂�݂���
      SetShortCutKeyState();//�ǂݍ��񂾐ݒ�̔��f�i�V���[�g�J�b�g�j
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  void SetShortCutKeyState(){
    String str="";
    str=KeyEvent.getKeyModifiersText(Integer.parseInt(ShortCut[0][0]))+" "+KeyEvent.getKeyText(Integer.parseInt(ShortCut[0][1]));
    BeginControllingKey_Button1.setText(str);
    //BeginControllingKey_Button1.setDefaultText(str);
    str=KeyEvent.getKeyModifiersText(Integer.parseInt(ShortCut[1][0]))+" "+KeyEvent.getKeyText(Integer.parseInt(ShortCut[1][1]));
    StoppingControllingKey_Button1.setText(str);
    //StoppingControllingKey_Button1.setDefaultText(str);
  }
  public void Conf() throws Exception{
  if(Conf.canRead()){//�ݒ�t�@�C��������Ȃ��
    Properties prop = new Properties();
    // �v���p�e�B�t�@�C������L�[�ƒl�̃��X�g��ǂݍ��݂܂�
    prop.load(new FileInputStream(Conf));
    AutoOpenFilePath_CheckBox.setSelected("true".equals(prop.getProperty("AutoOpen.enabled",MainFrame.AutoOpenFile_Default)));
    AutoOpenFilePath_Field.setText(prop.getProperty("AutoOpen.path",MainFrame.AutoOpenFileName_Default));
    /*�V���[�g�J�b�g*/
    for(int i=0;i<=1;i++){
      for(int j=0;j<=1;j++){
        ShortCut[i][j] =
            prop.getProperty(ShortCutKeyTitle[i]+String.valueOf(j),MainFrame.ShortCutKeyDefault[i][j]);
      }
    }
      /*�V���[�g�J�b�g�����*/

  }else{
    //�����ݒ�
    Properties prop = new Properties();
    prop.setProperty("AutoOpen.enabled",MainFrame.AutoOpenFile_Default);
    prop.setProperty("AutoOpen.path",MainFrame.AutoOpenFileName_Default);//�p�X������
    /*�V���[�g�J�b�g*/
    for(int i=0;i<=1;i++){
      for(int j=0;j<=1;j++){
        prop.setProperty(ShortCutKeyTitle[i]+String.valueOf(j) ,MainFrame.ShortCutKeyDefault[i][j]);//�L�[�̓o�^
      }
    }
      /*�V���[�g�J�b�g�����*/
    prop.store(new FileOutputStream(Conf), "Settings");
    //�����čċA
    Conf();
  }
  return;
}
  private void jbInit() throws Exception {
    /*������*/
    BeginControllingKey_Button1.setButtonMode(0);
    //BeginControllingKey_Button1.setAutoRewriteText(true);
    StoppingControllingKey_Button1.setButtonMode(1);
    //StoppingControllingKey_Button1.setAutoRewriteText(true);


    StoppingControllingKey_Button1.addActionListener(new
        SettingDlg_StoppingControllingKey_Button1_actionAdapter(this));
    this.setIconImage(MainFrame.winIcon);
    this.setSize(new Dimension(353, 320));
    this.setState(Frame.NORMAL);
    this.setTitle("Settings");
    this.setVisible(false);
    ContentPanel.setLayout(borderLayout1);
    PathPanel.setLayout(gridBagLayout1);
    AutoOpenFilePathPanel.setLayout(gridBagLayout2);
    AutoOpenFilePath_CheckBox.setText("Open the file when this soft starts.");
    AutoOpenFilePath_Label.setVerifyInputWhenFocusTarget(true);
    AutoOpenFilePath_Label.setText("File:");
    AutoOpenFilePath_Button.setText("Choose...");
    AutoOpenFilePath_Button.addActionListener(new SettingDlg_AutoOpenFilePath_Button_actionAdapter(this));
    AutoOpenFilePath_Field.setEnabled(true);
    AutoOpenFilePath_Field.setEditable(false);
    AutoOpenFilePath_Field.setText("");
    AutoOpenFilePathPanel.setName("AutoOpen");
    AutoOpenFilePathPanel.setAutoscrolls(false);
    AutoOpenFilePathPanel.setBorder(BorderFactory.createEtchedBorder());
    AutoOpenFilePath_titlelabel.setText("Auto File Open");
    ButtonPanel.setLayout(gridBagLayout3);
    ButtonPanel2.setLayout(gridLayout1);
    OK_Button.setText("OK");
    OK_Button.addActionListener(new SettingDlg_OK_Button_actionAdapter(this));
    Cancel_Button.setText("Cancel");
    Cancel_Button.addActionListener(new SettingDlg_Cancel_Button_actionAdapter(this));
    ShortCutKeySettingPanel.setLayout(gridBagLayout4);
    BeginControllingPanel.setLayout(gridBagLayout5);
    BeginControlling.setText("Begining controlling");
    BeginControllingPanel.setBorder(BorderFactory.createEtchedBorder());
    BeginControllingKey_Button1.addActionListener(new
        SettingDlg_BeginControllingKey_Button1_actionAdapter(this));
    StoppingControllingPanel.setLayout(gridBagLayout6);
    StoppingControllingLabel.setText("Stopping controlling");
    ShotCutKeySettingPanel.setLayout(gridBagLayout7);
    StoppingControllingPanel.setBorder(BorderFactory.createEtchedBorder());
    PathInsidePanel.setLayout(gridBagLayout8);
    BeginingControllingKey1Label.setText("Key");
    StoppingControllingKey1Label.setText("Key");
    ShortCut_TitlePanel.setLayout(gridBagLayout9);
    ShortCutTitleLabel.setText("Short Cut Keys Settings");
    ShortCut_TitlePanel.setBorder(BorderFactory.createEtchedBorder());
    ShortCutAttensionLabel.setLayout(gridBagLayout10);
    ShortCutAttentionLabel1.setText(
        "You can use Ctrl, Alt, Shift, or Meta as modifier keys.");
    this.getContentPane().add(ContentPanel,  BorderLayout.CENTER);
    ContentPanel.add(SettingsTabbedPanel, BorderLayout.CENTER);
    SettingsTabbedPanel.add(PathPanel,  "Path");
    AutoOpenFilePathPanel.add(AutoOpenFilePath_Button,            new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    AutoOpenFilePathPanel.add(AutoOpenFilePath_Label,          new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    ContentPanel.add(ButtonPanel,  BorderLayout.SOUTH);
    ButtonPanel.add(ButtonPanel2,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    ButtonPanel2.add(OK_Button, null);
    ButtonPanel2.add(Cancel_Button, null);
    ShortCutKeySettingPanel.add(ShotCutKeySettingPanel,
                                new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    PathPanel.add(PathInsidePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    AutoOpenFilePathPanel.add(AutoOpenFilePath_titlelabel,
                              new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    PathInsidePanel.add(AutoOpenFilePathPanel,
                        new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.CENTER,
                                               GridBagConstraints.HORIZONTAL,
                                               new Insets(5, 20, 0, 20), 0, 0));
    StoppingControllingPanel.add(StoppingControllingLabel,
                                 new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    StoppingControllingPanel.add(StoppingControllingKey1Label,
                                 new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 20, 0, 5), 0, 0));
    StoppingControllingPanel.add(StoppingControllingKey_Button1,
                                 new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    AutoOpenFilePathPanel.add(AutoOpenFilePath_CheckBox,
                              new GridBagConstraints(0, 1, 3, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 5, 5, 5), 0, 0));
    AutoOpenFilePathPanel.add(AutoOpenFilePath_Field,
                              new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    SettingsTabbedPanel.add(ShortCutKeySettingPanel, "Short Cut");
    ShortCut_TitlePanel.add(ShortCutTitleLabel,
                            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    BeginControllingPanel.add(BeginControlling,
                              new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    BeginControllingPanel.add(BeginControllingKey_Button1,
                              new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    BeginControllingPanel.add(BeginingControllingKey1Label,
                              new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 20, 0, 5), 0, 0));
    ShotCutKeySettingPanel.add(StoppingControllingPanel,
                               new GridBagConstraints(0, 3, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(5, 20, 0, 20), 0, 0));
    ShotCutKeySettingPanel.add(BeginControllingPanel,
                               new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(5, 20, 0, 20), 0, 0));
    ShotCutKeySettingPanel.add(ShortCutAttensionLabel,
                               new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(5, 20, 0, 20), 0, 0));
    ShotCutKeySettingPanel.add(ShortCut_TitlePanel,
                               new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(5, 20, 0, 20), 0, 0));
    ShortCutAttensionLabel.add(ShortCutAttentionLabel1,
                               new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
  }

  void Cancel_Button_actionPerformed(ActionEvent e) {
    dispose();//�Ƃ���
  }
  public JFrame getEndDlg(){
    return EndDialog;
  }

  void OK_Button_actionPerformed(ActionEvent e) {//OK�{�^��
    try{
      Properties prop = new Properties();
      /*AutoOpen*/
      prop.setProperty("AutoOpen.enabled",Boolean.toString(AutoOpenFilePath_CheckBox.isSelected()));//�L�����H
      prop.setProperty("AutoOpen.path",AutoOpenFilePath_Field.getText());//�p�X������
      /*�I�[�g���[�h�I��*/
      /*�V���[�g�J�b�g*/
      for(int i=0;i<=1;i++){
        for(int j=0;j<=1;j++){
          prop.setProperty(ShortCutKeyTitle[i]+String.valueOf(j) , ShortCut[i][j]);//�L�[�̓o�^
        }
      }
      /*�V���[�g�J�b�g�����*/
      // �v���p�e�B�̃��X�g���t�@�C���ɕۑ����܂�
      prop.store(new FileOutputStream(MainFrame.SettingFileName), "Settings");
    }catch(Exception s){
      s.printStackTrace();
    }
    EndDialogOpen();
    dispose();
  }
  void EndDialogOpen(){
    EndDialog = new EndDialog();
    EndDialog.pack();//�T�C�Y�͊m�肳���Ă���
    Dimension dlgSize = EndDialog.getSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    EndDialog.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    EndDialog.setVisible(true);
  }
  void AutoOpenFilePath_Button_actionPerformed(ActionEvent e) {
    File Folder = new File("./pecf/");
    Folder.mkdir();
    JFileChooser chooser = new JFileChooser( "./pecf/" );
    PECF_FileFilter Filter = new PECF_FileFilter();
    chooser.setFileFilter(Filter);
    int selected = chooser.showOpenDialog(this);
    if(selected == JFileChooser.APPROVE_OPTION) {//�t�@�C�����J���ꂽ
      AutoOpenFilePath_Field.setText(chooser.getSelectedFile().getPath());//�p�X��ݒ�
    }
  }
  public static void SettingShortCutKey(int Mode,int Modifiers ,int KeyState){
    //�L�[�̐ݒ�
    ShortCut[Mode][0] = String.valueOf(Modifiers);
    ShortCut[Mode][1] = String.valueOf(KeyState);
    MainFrame.ShortCutKey[Mode][0] = Modifiers;
    MainFrame.ShortCutKey[Mode][1] = KeyState;

  }
  void addShortCutKeyListner(Object o){//�ꉞ�{�^���ɂ����g���Ȃ�
    SettingShortCutKeyListner SettingShortCutKeyListner = new SettingShortCutKeyListner();
    ((MyJButton)o).addKeyListener(SettingShortCutKeyListner);
    ((MyJButton)o).setText("Press Keys");
  }
  public void BeginControllingKey_Button1_actionPerformed(ActionEvent e) {
    addShortCutKeyListner(BeginControllingKey_Button1);
  }

  public void StoppingControllingKey_Button1_actionPerformed(java.awt.event.
      ActionEvent e) {
    addShortCutKeyListner(StoppingControllingKey_Button1);
  }
}

class SettingDlg_StoppingControllingKey_Button1_actionAdapter
    implements java.awt.event.ActionListener {
  private SettingDlg adaptee;
  SettingDlg_StoppingControllingKey_Button1_actionAdapter(SettingDlg adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(java.awt.event.ActionEvent e) {
    adaptee.StoppingControllingKey_Button1_actionPerformed(e);
  }
}

class SettingShortCutKeyListner implements java.awt.event.KeyListener {
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() == e.VK_SHIFT ||
       e.getKeyCode() == e.VK_CONTROL ||
       e.getKeyCode() == e.VK_META ||
       e.getKeyCode() == e.VK_ALT){}else{//�C���L�[�̏ꍇ�͖�����
      String key_text = e.getKeyModifiersText(e.getModifiers()) +
                                         " " + e.getKeyText(e.getKeyCode());
      ( (MyJButton) e.getSource()).setText(key_text);
      //( (MyJButton) e.getSource()).setDefaultText(key_text);//�f�t�H���g�ݒ�̕ۑ��D
      SettingDlg.SettingShortCutKey( ( (MyJButton) e.getSource()).getButtonMode(),
                                    e.getModifiers(),
                                    e.getKeyCode());
      ( (MyJButton) e.getSource()).removeKeyListener(this);//���X�i�͏����Ă���
    }
  }
  public void keyReleased(KeyEvent e){
  }
  public void keyTyped(KeyEvent e){}

}
class SettingDlg_BeginControllingKey_Button1_actionAdapter
    implements ActionListener {
  private SettingDlg adaptee;
  SettingDlg_BeginControllingKey_Button1_actionAdapter(SettingDlg adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.BeginControllingKey_Button1_actionPerformed(e);
  }
}

class SettingDlg_Cancel_Button_actionAdapter implements java.awt.event.ActionListener {
  SettingDlg adaptee;

  SettingDlg_Cancel_Button_actionAdapter(SettingDlg adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Cancel_Button_actionPerformed(e);
  }
}

class SettingDlg_OK_Button_actionAdapter implements java.awt.event.ActionListener {
  SettingDlg adaptee;

  SettingDlg_OK_Button_actionAdapter(SettingDlg adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.OK_Button_actionPerformed(e);
  }
}

class SettingDlg_AutoOpenFilePath_Button_actionAdapter implements java.awt.event.ActionListener {
  SettingDlg adaptee;

  SettingDlg_AutoOpenFilePath_Button_actionAdapter(SettingDlg adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.AutoOpenFilePath_Button_actionPerformed(e);
  }
}
