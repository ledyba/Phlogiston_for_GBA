package EAC.controller;

import java.awt.Robot;
import EAC.*;
/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class RobotCore {
  Robot MyRobot;
  int Left = MainFrame.Left;
  int Right = MainFrame.Right;
  int Up = MainFrame.Up;
  int Down = MainFrame.Down;
  int A = MainFrame.A;
  int B = MainFrame.B;
  int L = MainFrame.L;
  int R= MainFrame.R;
  int Start = MainFrame.Start;
  int Select = MainFrame.Select;

  boolean LeftState = false;
  boolean RightState = false;
  boolean UpState = false;
  boolean DownState = false;
  boolean AState = false;
  boolean BState = false;
  boolean LState = false;
  boolean RState = false;
  boolean StartState = false;
  boolean SelectState = false;
  public RobotCore() throws Exception{
    MyRobot = new Robot();
  }
  public boolean isPressed(int keyCode){
    boolean pressed = false;
    if(keyCode == Left){
      pressed = LeftState;
    }else if(keyCode == Right){
      pressed = RightState;
    }else if(keyCode == Up){
      pressed = UpState;
    }else if(keyCode == Down){
      pressed = DownState;
    }else if(keyCode == A){
      pressed = AState;
    }else if(keyCode == B){
      pressed = BState;
    }else if(keyCode == L){
      pressed = LState;
    }else if(keyCode == R){
      pressed = RState;
    }else if(keyCode == Start){
      pressed = StartState;
    }else if(keyCode == Select){
      pressed = SelectState;
    }else{}//エラー処理
    return pressed;
  }
  private void Press(int keyCode){//特定のキーを押す
    MyRobot.keyPress(keyCode);
  }
  private void Release(int keyCode){//特定のキーを離す
    MyRobot.keyRelease(keyCode);
  }
  public void MoniPress(int keyCode){//特定のキーを押し，モニタを反映させる
    MyRobot.keyPress(keyCode);
    if(keyCode == Left){
      MainFrame.Show_L_Box.setSelected(true);//セット
      LeftState = true;
    }else if(keyCode == Right){
      MainFrame.Show_R_Box.setSelected(true);//セット
      RightState = true;
    }else if(keyCode == Up){
      MainFrame.Show_U_Box.setSelected(true);//セット
      UpState = true;
    }else if(keyCode == Down){
      MainFrame.Show_D_Box.setSelected(true);//セット
      DownState = true;
    }else if(keyCode == A){
      MainFrame.Show_A_Box.setSelected(true);//セット
      AState = true;
    }else if(keyCode == B){
      MainFrame.Show_B_Box.setSelected(true);//セット
      BState = true;
    }else if(keyCode == L){
      MainFrame.Show_LButton_Box.setSelected(true);//セット
      LState = true;
    }else if(keyCode == R){
      MainFrame.Show_RButton_Box.setSelected(true);//セット
      LState = true;
    }else if(keyCode == Start){
      MainFrame.Show_Start_Box.setSelected(true);//セット
      StartState = true;
    }else if(keyCode == Select){
      MainFrame.Show_Select_Box.setSelected(true);//セット
      SelectState = true;
    }else{}//エラー処理
  }
    public void MoniRelease(int keyCode){//特定のキーを離し，モニタに反映させる
      MyRobot.keyRelease(keyCode);
      if(keyCode == Left){
        MainFrame.Show_L_Box.setSelected(false);//セット
        LeftState = false;
      }else if(keyCode == Right){
        MainFrame.Show_R_Box.setSelected(false);//セット
        RightState = false;
      }else if(keyCode == Up){
        MainFrame.Show_U_Box.setSelected(false);//セット
        UpState = false;
      }else if(keyCode == Down){
        MainFrame.Show_D_Box.setSelected(false);//セット
        DownState = false;
      }else if(keyCode == A){
        MainFrame.Show_A_Box.setSelected(false);//セット
        AState = false;
      }else if(keyCode == B){
        MainFrame.Show_B_Box.setSelected(false);//セット
        BState = false;
      }else if(keyCode == L){
        MainFrame.Show_LButton_Box.setSelected(false);//セット
        LState = false;
      }else if(keyCode == R){
        MainFrame.Show_RButton_Box.setSelected(false);//セット
        LState = false;
      }else if(keyCode == Start){
        MainFrame.Show_Start_Box.setSelected(false);//セット
        StartState = false;
      }else if(keyCode == Select){
        MainFrame.Show_Select_Box.setSelected(false);//セット
        SelectState = false;
      }else{}//エラー処理
  }


  public void AllPress(){//すべておす
    Left_Press();
    Right_Press();
    Up_Press();
    Down_Press();
    A_Press();
    B_Press();
    L_Press();
    R_Press();
    Start_Press();
    Select_Press();
    MainFrame.StatusBar.setText("All Keys is pressed");
  }

  public void AllRelease(){//すべてはなす
    Left_Release();
    Right_Release();
    Up_Release();
    Down_Release();
    A_Release();
    B_Release();
    L_Release();
    R_Release();
    Start_Release();
    Select_Release();
    MainFrame.StatusBar.setText("All Keys is released");
  }

/*個別のボタン専用のメソッド群*/

  public void Left_Press(){//左
    MainFrame.Show_L_Box.setSelected(true);//セット
    LeftState = true;
    Press(Left);
  }
  public void Left_Release(){//左
    MainFrame.Show_L_Box.setSelected(false); //セット
    LeftState = false;
    Release(Left);
  }

  public void Right_Press(){//右
    MainFrame.Show_R_Box.setSelected(true);//セット
    RightState = true;
    Press(Right);
  }
  public void Right_Release(){//右
    MainFrame.Show_R_Box.setSelected(false); //セット
    RightState = false;
    Release(Right);
  }

  public void Up_Press(){//上
    MainFrame.Show_U_Box.setSelected(true);//セット
    UpState = true;
    Press(Up);
  }
  public void Up_Release(){//上
    MainFrame.Show_U_Box.setSelected(false); //セット
    UpState = false;
    Release(Up);
  }


  public void Down_Press(){//下
    MainFrame.Show_D_Box.setSelected(true);//セット
    DownState = true;
    Press(Down);
  }
  public void Down_Release(){//下
    MainFrame.Show_D_Box.setSelected(false); //セット
    DownState = false;
    Release(Down);
  }

  public void A_Press(){//A
    MainFrame.Show_A_Box.setSelected(true);//セット
    AState = true;
    Press(A);
  }
  public void A_Release(){//A
    MainFrame.Show_A_Box.setSelected(false); //セット
    AState = false;
    Release(A);
  }

  public void B_Press(){//B
    MainFrame.Show_B_Box.setSelected(true);//セット
    BState = false;
    Press(B);
  }
  public void B_Release(){//B
    MainFrame.Show_B_Box.setSelected(false); //セット
    BState = false;
    Release(B);
  }

  public void L_Press(){//L Button
    MainFrame.Show_LButton_Box.setSelected(true);//セット
    LState = true;
    Press(L);
  }
  public void L_Release(){//L Button
    MainFrame.Show_LButton_Box.setSelected(false); //セット
    LState = false;
    Release(L);
  }


  public void R_Press(){//R Button
    MainFrame.Show_RButton_Box.setSelected(true);//セット
    RState = true;
    Press(R);
  }
  public void R_Release(){//L Button
    MainFrame.Show_RButton_Box.setSelected(false); //セット
    RState = false;
    Release(R);
  }

  public void Start_Press(){//Start Button
    MainFrame.Show_Start_Box.setSelected(true);//セット
    StartState = true;
    Press(Start);
  }
  public void Start_Release(){//Start Button
    MainFrame.Show_Start_Box.setSelected(false); //セット
    StartState = false;
    Release(Start);
  }

  public void Select_Press(){//Select Button
    MainFrame.Show_Select_Box.setSelected(true);//セット
    SelectState = true;
    Press(Select);
  }
  public void Select_Release(){//Select Button
    MainFrame.Show_Select_Box.setSelected(false); //セット
    SelectState = false;
    Release(Select);
  }


}