package EAC.controller;

import java.util.Timer;
import java.util.TimerTask;
/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 * @author ������
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
        //���������s���Ă���Crun()�̋N���܂ł��قږ����̂ŁC�����ł����ċt�������D
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
    if(Robot.isPressed(Key1) && !Robot.isPressed(Key2)){//Key1��������Ă�����
      Robot.MoniRelease(Key1);
      Robot.MoniPress(Key2);
    }else if(!Robot.isPressed(Key1) && Robot.isPressed(Key2)){//Key2��������Ă�����
      Robot.MoniRelease(Key2);
      Robot.MoniPress(Key1);
    }else{
      //�Ƃ肠����FirstKey�ł������Ă����D�D�D
      Robot.MoniPress(FirstKey);
    }
  }
}