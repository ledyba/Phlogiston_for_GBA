package EAC.controller;

import java.util.Timer;
import java.util.TimerTask;

import EAC.*;
/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class controller extends Thread{
  public static RobotCore RobotCore;
  public static Timer Timer;//タイマを作成する
  public controller() {
    Timer = new Timer(false);
  }
  public void run(){
    try{
      RobotCore = new RobotCore();
      /*上下左右モード
       0:LR 1:UD
       **_reciprocating_Radio　と **_keeping_Radio
       */
      LR();
      UD();
      /*ボタンモード
       2:A 3:B 4:L 5:R 7:Start 8:Select
       *_Autofire_Radio　と　*_keeping_Radio
       */
      A();
      B();
      L();
      R();
      Start();
      Select();

    }catch(Exception e){
      System.out.println(e);
    }
    }
    public void exit(){//コントローラを終了させる
      Timer.cancel();
      Timer = null;
      RobotCore.AllRelease();//すべてはなす
      return;
    }

  public void LR() throws Exception{
    if (MainFrame.AllButtons.isSelectedIndex(0)) { //オンになっているならば
      if(MainFrame.LR_ModeGroup.isSelected(MainFrame.LR_reciprocating_Radio.getModel())){//「往復運動」モード時
        float interval = Float.parseFloat(MainFrame.
                                         LR_reciprocating_interval_Field.
                                         getText());
        if((interval * 1000) >= 1){//1ミリ秒未満の場合は行わない
          TimerTask Task = null;
          if (MainFrame.LR_reciprocating_first_key_ComboBox.getSelectedIndex() ==
              0) { //なにもなし：無視（仕様)
          }
          else if (MainFrame.LR_reciprocating_first_key_ComboBox.
                   getSelectedIndex() == 1) { //Left
            Task = new Reciprocating_Core(RobotCore.Left, RobotCore.Right,
                                          RobotCore.Left); //左からはじめる
          }
          else { //Right
            Task = new Reciprocating_Core(RobotCore.Right, RobotCore.Left,
                                          RobotCore.Right); //右からはじめる
          }

          if (Task != null) {
            long longInterval = (long)(interval * 1000);
            Timer.scheduleAtFixedRate(Task, 0, longInterval);
          }
        }

      }else if(MainFrame.LR_ModeGroup.isSelected(MainFrame.LR_keeping_Radio.getModel())){//「状態キープ」モード時
        if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 0){//キーが0番目→固定しない
        }else if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 1){//Left
          RobotCore.Right_Release();//Rightキーを離す
          RobotCore.Left_Press();//Leftキーを押す
        }else if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 2){//Right
          RobotCore.Left_Release();//Leftキーを離す
          RobotCore.Right_Press();//Rightキーを押す
        }else{}
      }
    }
  }
  public void UD() throws Exception{
    if (MainFrame.AllButtons.isSelectedIndex(1)) { //オンになっているならば
      if(MainFrame.UD_ModeGroup.isSelected(MainFrame.UD_reciprocating_Radio.getModel())){//「往復運動」モード時
        float interval = Float.parseFloat (MainFrame.UD_reciprocating_interval_Field.getText());
        if((interval * 1000) >= 1){//1ミリ秒未満の場合は行わない
          TimerTask Task = null;
          if (MainFrame.UD_reciprocating_first_key_ComboBox.getSelectedIndex() ==
              0) { //なにもなし：無視（仕様)
          }
          else if (MainFrame.UD_reciprocating_first_key_ComboBox.
                   getSelectedIndex() == 1) { //Up
            Task = new Reciprocating_Core(RobotCore.Up, RobotCore.Down,
                                          RobotCore.Up); //上から
          }
          else { //Down
            Task = new Reciprocating_Core(RobotCore.Down, RobotCore.Up,
                                          RobotCore.Down); //下から
          }
          if (Task != null) {
            long longInterval = (long)(interval * 1000);
            Timer.scheduleAtFixedRate(Task, 0, longInterval);
          }
        }

      }else if(MainFrame.UD_ModeGroup.isSelected(MainFrame.UD_keeping_Radio.getModel())){//「状態キープ」モード時
        if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 0){//キーが0番目→固定しない
        }else if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 1){//Up
          RobotCore.Down_Release();//Downキーを離す
          RobotCore.Up_Press();//Upキーを押す
        }else if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 2){//Down
          RobotCore.Up_Release();//Upキーを離す
          RobotCore.Down_Press();//Downキーを押す
        }else{}
      }
    }
  }

  public void A(){
    if (MainFrame.AllButtons.isSelectedIndex(2)) { //オンになっているならば
      if (MainFrame.A_ModeGroup.isSelected(MainFrame.A_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.A_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.A_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.A, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.A, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.A_ModeGroup.isSelected(MainFrame.A_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.A_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.A_Press();//押す
        }else{//offなら
          RobotCore.A_Release();//離す
        }
      }
    }
  }
  public void B(){
    if (MainFrame.AllButtons.isSelectedIndex(3)) { //オンになっているならば

      if (MainFrame.B_ModeGroup.isSelected(MainFrame.B_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.B_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.B_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.B, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.B, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.B_ModeGroup.isSelected(MainFrame.B_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.B_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.B_Press();//押す
        }else{//offなら
          RobotCore.B_Release();//離す
        }
      }
    }
  }
  public void L(){
    if (MainFrame.AllButtons.isSelectedIndex(4)) { //オンになっているならば

      if (MainFrame.L_ModeGroup.isSelected(MainFrame.L_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.L_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.L_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.L, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.L, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.L_ModeGroup.isSelected(MainFrame.L_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.L_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.L_Press();//押す
        }else{//offなら
          RobotCore.L_Release();//離す
        }
      }
    }
  }
  public void R(){
    if (MainFrame.AllButtons.isSelectedIndex(5)) { //オンになっているならば

      if (MainFrame.R_ModeGroup.isSelected(MainFrame.R_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.R_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.R_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.R, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.R, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.R_ModeGroup.isSelected(MainFrame.R_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.R_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.R_Press();//押す
        }else{//offなら
          RobotCore.R_Release();//離す
        }
      }
    }
  }
  public void Start(){
    if (MainFrame.AllButtons.isSelectedIndex(6)) { //オンになっているならば

      if (MainFrame.Start_ModeGroup.isSelected(MainFrame.Start_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.Start_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.Start_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.Start, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.Start, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.Start_ModeGroup.isSelected(MainFrame.Start_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.Start_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.Start_Press();//押す
        }else{//offなら
          RobotCore.Start_Release();//離す
        }
      }
    }
  }
  public void Select(){
    if (MainFrame.AllButtons.isSelectedIndex(7)) { //オンになっているならば

      if (MainFrame.Select_ModeGroup.isSelected(MainFrame.Select_Autofire_Radio.
                                            getModel())) { //「連射」モード時
         int time = Integer.parseInt(MainFrame.Select_Autofire_speed_Field.getText());
         if(time > 0){
           TimerTask Task;
           if (MainFrame.Select_Autofire_firstState_ComboBox.getSelectedIndex() == 0) { //0 == ON
              Task = new AutoFire_Core(RobotCore.Select, true);
           }
           else { //OFF
             Task = new AutoFire_Core(RobotCore.Select, false);
           }
           Timer.scheduleAtFixedRate(Task, 0, 1000/time/2);
         }else{}
      }
      else if (MainFrame.Select_ModeGroup.isSelected(MainFrame.Select_keeping_Radio.
          getModel())) { //「状態キープ」モード時
        if(MainFrame.Select_keeping_state_ComboBox.getSelectedIndex() == 0){//onなら
          RobotCore.Select_Press();//押す
        }else{//offなら
          RobotCore.Select_Release();//離す
        }
      }
    }
  }

}