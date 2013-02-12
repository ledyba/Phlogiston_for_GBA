package EAC.controller;

import java.awt.Robot;
import EAC.*;
/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 * @author ������
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
    }else{}//�G���[����
    return pressed;
  }
  private void Press(int keyCode){//����̃L�[������
    MyRobot.keyPress(keyCode);
  }
  private void Release(int keyCode){//����̃L�[�𗣂�
    MyRobot.keyRelease(keyCode);
  }
  public void MoniPress(int keyCode){//����̃L�[�������C���j�^�𔽉f������
    MyRobot.keyPress(keyCode);
    if(keyCode == Left){
      MainFrame.Show_L_Box.setSelected(true);//�Z�b�g
      LeftState = true;
    }else if(keyCode == Right){
      MainFrame.Show_R_Box.setSelected(true);//�Z�b�g
      RightState = true;
    }else if(keyCode == Up){
      MainFrame.Show_U_Box.setSelected(true);//�Z�b�g
      UpState = true;
    }else if(keyCode == Down){
      MainFrame.Show_D_Box.setSelected(true);//�Z�b�g
      DownState = true;
    }else if(keyCode == A){
      MainFrame.Show_A_Box.setSelected(true);//�Z�b�g
      AState = true;
    }else if(keyCode == B){
      MainFrame.Show_B_Box.setSelected(true);//�Z�b�g
      BState = true;
    }else if(keyCode == L){
      MainFrame.Show_LButton_Box.setSelected(true);//�Z�b�g
      LState = true;
    }else if(keyCode == R){
      MainFrame.Show_RButton_Box.setSelected(true);//�Z�b�g
      LState = true;
    }else if(keyCode == Start){
      MainFrame.Show_Start_Box.setSelected(true);//�Z�b�g
      StartState = true;
    }else if(keyCode == Select){
      MainFrame.Show_Select_Box.setSelected(true);//�Z�b�g
      SelectState = true;
    }else{}//�G���[����
  }
    public void MoniRelease(int keyCode){//����̃L�[�𗣂��C���j�^�ɔ��f������
      MyRobot.keyRelease(keyCode);
      if(keyCode == Left){
        MainFrame.Show_L_Box.setSelected(false);//�Z�b�g
        LeftState = false;
      }else if(keyCode == Right){
        MainFrame.Show_R_Box.setSelected(false);//�Z�b�g
        RightState = false;
      }else if(keyCode == Up){
        MainFrame.Show_U_Box.setSelected(false);//�Z�b�g
        UpState = false;
      }else if(keyCode == Down){
        MainFrame.Show_D_Box.setSelected(false);//�Z�b�g
        DownState = false;
      }else if(keyCode == A){
        MainFrame.Show_A_Box.setSelected(false);//�Z�b�g
        AState = false;
      }else if(keyCode == B){
        MainFrame.Show_B_Box.setSelected(false);//�Z�b�g
        BState = false;
      }else if(keyCode == L){
        MainFrame.Show_LButton_Box.setSelected(false);//�Z�b�g
        LState = false;
      }else if(keyCode == R){
        MainFrame.Show_RButton_Box.setSelected(false);//�Z�b�g
        LState = false;
      }else if(keyCode == Start){
        MainFrame.Show_Start_Box.setSelected(false);//�Z�b�g
        StartState = false;
      }else if(keyCode == Select){
        MainFrame.Show_Select_Box.setSelected(false);//�Z�b�g
        SelectState = false;
      }else{}//�G���[����
  }


  public void AllPress(){//���ׂĂ���
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

  public void AllRelease(){//���ׂĂ͂Ȃ�
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

/*�ʂ̃{�^����p�̃��\�b�h�Q*/

  public void Left_Press(){//��
    MainFrame.Show_L_Box.setSelected(true);//�Z�b�g
    LeftState = true;
    Press(Left);
  }
  public void Left_Release(){//��
    MainFrame.Show_L_Box.setSelected(false); //�Z�b�g
    LeftState = false;
    Release(Left);
  }

  public void Right_Press(){//�E
    MainFrame.Show_R_Box.setSelected(true);//�Z�b�g
    RightState = true;
    Press(Right);
  }
  public void Right_Release(){//�E
    MainFrame.Show_R_Box.setSelected(false); //�Z�b�g
    RightState = false;
    Release(Right);
  }

  public void Up_Press(){//��
    MainFrame.Show_U_Box.setSelected(true);//�Z�b�g
    UpState = true;
    Press(Up);
  }
  public void Up_Release(){//��
    MainFrame.Show_U_Box.setSelected(false); //�Z�b�g
    UpState = false;
    Release(Up);
  }


  public void Down_Press(){//��
    MainFrame.Show_D_Box.setSelected(true);//�Z�b�g
    DownState = true;
    Press(Down);
  }
  public void Down_Release(){//��
    MainFrame.Show_D_Box.setSelected(false); //�Z�b�g
    DownState = false;
    Release(Down);
  }

  public void A_Press(){//A
    MainFrame.Show_A_Box.setSelected(true);//�Z�b�g
    AState = true;
    Press(A);
  }
  public void A_Release(){//A
    MainFrame.Show_A_Box.setSelected(false); //�Z�b�g
    AState = false;
    Release(A);
  }

  public void B_Press(){//B
    MainFrame.Show_B_Box.setSelected(true);//�Z�b�g
    BState = false;
    Press(B);
  }
  public void B_Release(){//B
    MainFrame.Show_B_Box.setSelected(false); //�Z�b�g
    BState = false;
    Release(B);
  }

  public void L_Press(){//L Button
    MainFrame.Show_LButton_Box.setSelected(true);//�Z�b�g
    LState = true;
    Press(L);
  }
  public void L_Release(){//L Button
    MainFrame.Show_LButton_Box.setSelected(false); //�Z�b�g
    LState = false;
    Release(L);
  }


  public void R_Press(){//R Button
    MainFrame.Show_RButton_Box.setSelected(true);//�Z�b�g
    RState = true;
    Press(R);
  }
  public void R_Release(){//L Button
    MainFrame.Show_RButton_Box.setSelected(false); //�Z�b�g
    RState = false;
    Release(R);
  }

  public void Start_Press(){//Start Button
    MainFrame.Show_Start_Box.setSelected(true);//�Z�b�g
    StartState = true;
    Press(Start);
  }
  public void Start_Release(){//Start Button
    MainFrame.Show_Start_Box.setSelected(false); //�Z�b�g
    StartState = false;
    Release(Start);
  }

  public void Select_Press(){//Select Button
    MainFrame.Show_Select_Box.setSelected(true);//�Z�b�g
    SelectState = true;
    Press(Select);
  }
  public void Select_Release(){//Select Button
    MainFrame.Show_Select_Box.setSelected(false); //�Z�b�g
    SelectState = false;
    Release(Select);
  }


}