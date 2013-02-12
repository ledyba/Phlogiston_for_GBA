package EAC.controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class AutoFire_Core extends TimerTask{
  int keyCode;
  RobotCore Robot;
  public AutoFire_Core(int keyCode,boolean first){
    this.keyCode = keyCode;
    try{
      Robot = new RobotCore();
      if(first != true){//押した状態からはじめるならば　こちらもあえて逆にする
        Robot.MoniPress(keyCode);
      }else{//でなければ
        Robot.MoniRelease(keyCode);
      }
    }catch(Exception e){}
  }
  public void run(){
    if(Robot.isPressed(keyCode)){
      Robot.MoniRelease(keyCode);
    }else{
      Robot.MoniPress(keyCode);
    }
    //連射なら，これでおわりだね．
  }
}