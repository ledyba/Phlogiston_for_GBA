package EAC;

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

public class Setting {
  Properties prop;//staticにしてはいけない！
  final int ButtonNumber = 8;//ボタン数
  public Setting() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  public void SaveSetting(File SaveFile){//書き込み
    try{
      prop = new Properties();
      Buttons_Selected_Save();
      LR_Save();
      UD_Save();
      A_Save();
      B_Save();
      L_Save();
      R_Save();
      Start_Save();
      Select_Save();
      prop.store(new FileOutputStream(SaveFile), "Phlogiston Emulator Controll File v1.0");
      prop = null;
      setFlags(SaveFile,true);//フラグセット
    } catch (Exception er) {
      er.printStackTrace();
    }
  }
  public void ReadSetting(File SaveFile){//読み込み
    try{
      if(SaveFile.canRead()){
        prop = new Properties();
        prop.load(new FileInputStream(SaveFile));
        Buttons_Selected_Open();
        LR_Open();
        UD_Open();
        A_Open();
        B_Open();
        L_Open();
        R_Open();
        Start_Open();
        Select_Open();
        prop = null;
        setFlags(SaveFile,true);//フラグセット
      }else{
        MainFrame.StatusBar.setText("It can't be read!");
      }
    } catch (Exception er) {
      er.printStackTrace();
    }
  }
  public void setFlags(File File,boolean openFlag){
    if(File != null){
      MainFrame.OpeningFileName = File.getPath(); //名前を設定
      MainFrame.FileNameField.setText(File.getName()); //ファイル名変更
    }else{
      MainFrame.OpeningFileName = ""; //名前を設定
      MainFrame.FileNameField.setText("none"); //ファイル名変更
    }
    MainFrame.isOpen = openFlag;//開かれているというフラグをたてる
  }
  public void DefaultSetting(){//デフォルト
    Buttons_Selected_Default();
    LR_Default();
    UD_Default();
    A_Default();
    B_Default();
    L_Default();
    R_Default();
    Start_Default();
    Select_Default();
    setFlags(null,false);//フラグセット
  }
  private void Buttons_Selected_Save(){//右側のバーが選択されているか，いないか．
    for(int i=0;i<ButtonNumber;i++){
      prop.setProperty("Selected."+i,
                       Boolean.toString(MainFrame.AllButtons.isSelectedIndex(i)));
    }
  }

  private void Buttons_Selected_Open(){//右側のバーが選択されているか，いないかの設定
    MainFrame.AllButtons.clearSelection();//すべてリセットする
    for(int i=0;i<ButtonNumber;i++){
      if("true".equals(
          prop.getProperty(("Selected."+String.valueOf(i)),
                           MainFrame.Buttons_Selected_Default))){//検知して・・・
        MainFrame.AllButtons.addSelectionInterval(i,i);//設定する
      }
    }
  }
  private void Buttons_Selected_Default(){
      if("true".equals(MainFrame.Buttons_Selected_Default)){//デフォルトが「選択」なら
        MainFrame.AllButtons.addSelectionInterval(0, ButtonNumber-1); //設定する
      }else{
        MainFrame.AllButtons.clearSelection();//すべてリセットする
      }
  }

  private void LR_Save() throws Exception{
    prop.setProperty("LR.reciprocating.selected",//往復運動は選択されていますか？
                     Boolean.toString(MainFrame.LR_ModeGroup.
                                      isSelected
                                      (MainFrame.LR_reciprocating_Radio.getModel())));
    prop.setProperty("LR.reciprocating.interval",
                     MainFrame.LR_reciprocating_interval_Field.getText());//インターバル設定
    prop.setProperty("LR.reciprocating.FirstKey",
                     Integer.toString(MainFrame.LR_reciprocating_first_key_ComboBox.
                                      getSelectedIndex()));//往復運動：最初のキー

    prop.setProperty("LR.keeping.selected",
                     Boolean.toString(MainFrame.LR_ModeGroup.
                                      isSelected(MainFrame.
                                                 LR_keeping_Radio.getModel())));//キープモード？
    prop.setProperty("LR.keeping.key",Integer.toString(//キーは？
        MainFrame.LR_keeping_key_ComboBox.getSelectedIndex()));
  }
  private void LR_Open() throws Exception{//Open
    MainFrame.LR_ModeGroup.setSelected(MainFrame.LR_reciprocating_Radio.getModel(),
                                       "true".equals(prop.getProperty("LR.reciprocating.selected",
        MainFrame.ReciprocatingSelected_Default)));//往復運動の設定
    MainFrame.LR_reciprocating_interval_Field.setText(
        prop.getProperty("LR.reciprocating.interval",
                         MainFrame.ReciprocatingInterval_Default));//インターバルの設定
    MainFrame.LR_reciprocating_first_key_ComboBox.setSelectedIndex(
        Integer.parseInt(prop.getProperty("LR.reciprocating.FirstKey",
                                          MainFrame.LRUD_FirstKey_Default)));//初めのキーの設定
    MainFrame.LR_ModeGroup.setSelected(MainFrame.LR_keeping_Radio.getModel(),
                                       "true".equals(prop.getProperty("LR.keeping.selected",
        MainFrame.KeepingSelected_Default)));//キープの設定
    MainFrame.LR_keeping_key_ComboBox.setSelectedIndex(
        Integer.parseInt(prop.getProperty("LR.reciprocating.key",
                                          MainFrame.LRUD_Keep_KeyDefault)));//初めのキーの設定

  }
  private void LR_Default(){
    MainFrame.LR_ModeGroup.setSelected(MainFrame.LR_reciprocating_Radio.getModel(),
                                       "true".equals(MainFrame.ReciprocatingSelected_Default));//往復運動の設定
    MainFrame.LR_reciprocating_interval_Field.setText(MainFrame.ReciprocatingInterval_Default);//インターバルの設定
    MainFrame.LR_reciprocating_first_key_ComboBox.setSelectedIndex(Integer.parseInt(MainFrame.LRUD_FirstKey_Default));//初めのキーの設定
    MainFrame.LR_ModeGroup.setSelected(MainFrame.LR_keeping_Radio.getModel(),
                                       "true".equals(MainFrame.KeepingSelected_Default));//キープの設定
    MainFrame.LR_keeping_key_ComboBox.setSelectedIndex(
        Integer.parseInt(MainFrame.LRUD_Keep_KeyDefault));//初めのキーの設定
  }
  private void UD_Save() throws Exception{
    prop.setProperty("UD.reciprocating.selected",//往復運動は選択されていますか？
                     Boolean.toString(MainFrame.UD_ModeGroup.
                                      isSelected
                                      (MainFrame.UD_reciprocating_Radio.getModel())));
    prop.setProperty("UD.reciprocating.interval",
                     MainFrame.UD_reciprocating_interval_Field.getText());//インターバル設定
    prop.setProperty("UD.reciprocating.FirstKey",
                     Integer.toString(MainFrame.UD_reciprocating_first_key_ComboBox.
                                      getSelectedIndex()));//往復運動：最初のキー

    prop.setProperty("UD.keeping.selected",
                     Boolean.toString(MainFrame.UD_ModeGroup.
                                      isSelected(MainFrame.
                                                 UD_keeping_Radio.getModel())));//キープモード？
    prop.setProperty("UD.keeping.key",Integer.toString(//キーは？
        MainFrame.UD_keeping_key_ComboBox.getSelectedIndex()));
  }
  private void UD_Open() throws Exception{//Open
    MainFrame.UD_ModeGroup.setSelected(MainFrame.UD_reciprocating_Radio.getModel(),
                                       "true".equals(prop.getProperty("UD.reciprocating.selected",
        MainFrame.ReciprocatingSelected_Default)));//往復運動の設定
    MainFrame.UD_reciprocating_interval_Field.setText(
        prop.getProperty("UD.reciprocating.interval",
                         MainFrame.ReciprocatingInterval_Default));//インターバルの設定
    MainFrame.UD_reciprocating_first_key_ComboBox.setSelectedIndex(
        Integer.parseInt(prop.getProperty("UD.reciprocating.FirstKey",
                                          MainFrame.LRUD_FirstKey_Default)));//初めのキーの設定
    MainFrame.UD_ModeGroup.setSelected(MainFrame.UD_keeping_Radio.getModel(),
                                       "true".equals(prop.getProperty("UD.keeping.selected",
        MainFrame.KeepingSelected_Default)));//キープの設定
    MainFrame.UD_keeping_key_ComboBox.setSelectedIndex(
        Integer.parseInt(prop.getProperty("UD.reciprocating.key",
                                          MainFrame.LRUD_Keep_KeyDefault)));//初めのキーの設定

  }
  private void UD_Default(){
    MainFrame.UD_ModeGroup.setSelected(MainFrame.UD_reciprocating_Radio.getModel(),
                                       "true".equals(MainFrame.ReciprocatingSelected_Default));//往復運動の設定
    MainFrame.UD_reciprocating_interval_Field.setText(MainFrame.ReciprocatingInterval_Default);//インターバルの設定
    MainFrame.UD_reciprocating_first_key_ComboBox.setSelectedIndex(Integer.parseInt(MainFrame.LRUD_FirstKey_Default));//初めのキーの設定
    MainFrame.UD_ModeGroup.setSelected(MainFrame.UD_keeping_Radio.getModel(),
                                       "true".equals(MainFrame.KeepingSelected_Default));//キープの設定
    MainFrame.UD_keeping_key_ComboBox.setSelectedIndex(
        Integer.parseInt(MainFrame.LRUD_Keep_KeyDefault));//初めのキーの設定
  }

  private void A_Save(){
    prop.setProperty("A.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      A_ModeGroup.isSelected(MainFrame.
        A_Autofire_Radio.getModel())));
    prop.setProperty("A.Autofire.Time",
                     MainFrame.A_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("A_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      A_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("A.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      A_ModeGroup.isSelected(MainFrame.
        A_keeping_Radio.
        getModel())));
    prop.setProperty("A.keeping.state", //キープする状態
                     Integer.toString(MainFrame.A_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void A_Open(){
    MainFrame.A_ModeGroup.setSelected(MainFrame.A_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("A.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.A_Autofire_speed_Field.setText(
        prop.getProperty("A.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.A_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("A_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.A_ModeGroup.setSelected(MainFrame.A_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("A.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.A_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("A.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void A_Default(){
    MainFrame.A_ModeGroup.setSelected(MainFrame.A_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.A_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.A_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.A_ModeGroup.setSelected(MainFrame.A_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.A_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }

  private void B_Save(){
    prop.setProperty("B.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      B_ModeGroup.isSelected(MainFrame.
        B_Autofire_Radio.getModel())));
    prop.setProperty("B.Autofire.Time",
                     MainFrame.B_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("B_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      B_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("B.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      B_ModeGroup.isSelected(MainFrame.
        B_keeping_Radio.
        getModel())));
    prop.setProperty("B.keeping.state", //キープする状態
                     Integer.toString(MainFrame.B_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void B_Open(){
    MainFrame.B_ModeGroup.setSelected(MainFrame.B_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("B.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.B_Autofire_speed_Field.setText(
        prop.getProperty("B.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.B_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("B_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.B_ModeGroup.setSelected(MainFrame.B_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("B.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.B_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("B.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void B_Default(){
    MainFrame.B_ModeGroup.setSelected(MainFrame.B_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.B_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.B_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.B_ModeGroup.setSelected(MainFrame.B_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.B_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }

  private void L_Save(){
    prop.setProperty("L.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      L_ModeGroup.isSelected(MainFrame.
        L_Autofire_Radio.getModel())));
    prop.setProperty("L.Autofire.Time",
                     MainFrame.L_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("L_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      L_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("L.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      L_ModeGroup.isSelected(MainFrame.
        L_keeping_Radio.
        getModel())));
    prop.setProperty("L.keeping.state", //キープする状態
                     Integer.toString(MainFrame.L_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void L_Open(){
    MainFrame.L_ModeGroup.setSelected(MainFrame.L_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("L.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.L_Autofire_speed_Field.setText(
        prop.getProperty("L.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.L_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("L_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.L_ModeGroup.setSelected(MainFrame.L_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("L.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.L_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("L.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void L_Default(){
    MainFrame.L_ModeGroup.setSelected(MainFrame.L_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.L_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.L_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.L_ModeGroup.setSelected(MainFrame.L_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.L_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }

  private void R_Save(){
    prop.setProperty("R.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      R_ModeGroup.isSelected(MainFrame.
        R_Autofire_Radio.getModel())));
    prop.setProperty("R.Autofire.Time",
                     MainFrame.R_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("R_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      R_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("R.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      R_ModeGroup.isSelected(MainFrame.
        R_keeping_Radio.
        getModel())));
    prop.setProperty("R.keeping.state", //キープする状態
                     Integer.toString(MainFrame.R_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void R_Open(){
    MainFrame.R_ModeGroup.setSelected(MainFrame.R_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("R.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.R_Autofire_speed_Field.setText(
        prop.getProperty("R.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.R_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("R_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.R_ModeGroup.setSelected(MainFrame.R_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("R.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.R_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("R.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void R_Default(){
    MainFrame.R_ModeGroup.setSelected(MainFrame.R_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.R_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.R_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.R_ModeGroup.setSelected(MainFrame.R_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.R_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }


  private void Start_Save(){
    prop.setProperty("Start.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      Start_ModeGroup.isSelected(MainFrame.
        Start_Autofire_Radio.getModel())));
    prop.setProperty("Start.Autofire.Time",
                     MainFrame.Start_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("Start_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      Start_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("Start.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      Start_ModeGroup.isSelected(MainFrame.
        Start_keeping_Radio.
        getModel())));
    prop.setProperty("Start.keeping.state", //キープする状態
                     Integer.toString(MainFrame.Start_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void Start_Open(){
    MainFrame.Start_ModeGroup.setSelected(MainFrame.Start_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("Start.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.Start_Autofire_speed_Field.setText(
        prop.getProperty("Start.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.Start_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("Start_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.Start_ModeGroup.setSelected(MainFrame.Start_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("Start.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.Start_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("Start.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void Start_Default(){
    MainFrame.Start_ModeGroup.setSelected(MainFrame.Start_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.Start_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.Start_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.Start_ModeGroup.setSelected(MainFrame.Start_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.Start_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }
  private void Select_Save(){
    prop.setProperty("Select.Autofire.selected",
                     Boolean.toString(MainFrame.
                                      Select_ModeGroup.isSelected(MainFrame.
        Select_Autofire_Radio.getModel())));
    prop.setProperty("Select.Autofire.Time",
                     MainFrame.Select_Autofire_speed_Field.getText()); //連射時間
    prop.setProperty("Select_Autofire.FirstState", //最初の状態
                     Integer.toString(MainFrame.
                                      Select_Autofire_firstState_ComboBox.
                                      getSelectedIndex()));
    prop.setProperty("Select.keeping.selected",//状態維持モード
                     Boolean.toString(MainFrame.
                                      Select_ModeGroup.isSelected(MainFrame.
        Select_keeping_Radio.
        getModel())));
    prop.setProperty("Select.keeping.state", //キープする状態
                     Integer.toString(MainFrame.Select_keeping_state_ComboBox.
                                      getSelectedIndex()));
  }
  private void Select_Open(){
    MainFrame.Select_ModeGroup.setSelected(MainFrame.Select_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(
        prop.getProperty("Select.Autofire.selected",MainFrame.AutofireSelected_Default)));

    MainFrame.Select_Autofire_speed_Field.setText(
        prop.getProperty("Select.Autofire.Time",MainFrame.AutoFireTime_Default));//連射時間

    MainFrame.Select_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(
        prop.getProperty("Select_Autofire.FirstState",MainFrame.ABLE_FirstKey_Default)));

    MainFrame.Select_ModeGroup.setSelected(MainFrame.Select_keeping_Radio.getModel(),//状態維持
                                      "true".equals(
        prop.getProperty("Select.keeping.selected",MainFrame.ABLR_Keep_KeyDefault)));

    MainFrame.Select_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(prop.getProperty("Select.keeping.state",MainFrame.ABLR_Keep_KeyDefault)));

  }

  private void Select_Default(){
    MainFrame.Select_ModeGroup.setSelected(MainFrame.Select_Autofire_Radio.getModel(),//連射モード
                                      "true".equals(MainFrame.AutofireSelected_Default));

    MainFrame.Select_Autofire_speed_Field.setText(MainFrame.AutoFireTime_Default);//連射時間

    MainFrame.Select_Autofire_firstState_ComboBox.setSelectedIndex(//最初のキー
        Integer.parseInt(MainFrame.ABLE_FirstKey_Default));

    MainFrame.Select_ModeGroup.setSelected(MainFrame.Select_keeping_Radio.getModel(),//状態維持
                                      "true".equals(MainFrame.ABLR_Keep_KeyDefault));

    MainFrame.Select_keeping_state_ComboBox.setSelectedIndex(//状態維持をするキー
        Integer.parseInt(MainFrame.ABLR_Keep_KeyDefault));

  }

  private void jbInit() throws Exception {
  }

}
