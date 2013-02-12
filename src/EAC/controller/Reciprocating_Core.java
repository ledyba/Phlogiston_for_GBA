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

public class Reciprocating_Core extends TimerTask{
  int Key1;
  int Key2;
  int FirstKey;
  RobotCore Robot;
  public Reciprocating_Core(int Key1,int Key2,int FirstKey) {
    this.Key1=Key1;
    this.Key2=Key2;
    this.FirstKey=FirstKey;
    try{
      Robot = new RobotCore();
      if(Key1 == FirstKey || Key2 == FirstKey){
        //ここを実行してから，run()の起動までがほぼ無いので，ここであえて逆をいれる．
        if (FirstKey == Key1) {
          Robot.MoniPress(Key2);
        }
        else {
          Robot.MoniPress(Key1);
        }
      }
    }catch(Exception e){}
  }

  public void run(){
    if(Robot.isPressed(Key1) && !Robot.isPressed(Key2)){//Key1が押されていたら
      Robot.MoniRelease(Key1);
      Robot.MoniPress(Key2);
    }else if(!Robot.isPressed(Key1) && Robot.isPressed(Key2)){//Key2が押されていたら
      Robot.MoniRelease(Key2);
      Robot.MoniPress(Key1);
    }else{
      //とりあえずFirstKeyでもおしておく．．．
      Robot.MoniPress(FirstKey);
    }
  }
}