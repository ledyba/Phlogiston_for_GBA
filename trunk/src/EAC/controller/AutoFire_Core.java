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

public class AutoFire_Core extends TimerTask{
  int keyCode;
  RobotCore Robot;
  public AutoFire_Core(int keyCode,boolean first){
    this.keyCode = keyCode;
    try{
      Robot = new RobotCore();
      if(first != true){//��������Ԃ���͂��߂�Ȃ�΁@������������ċt�ɂ���
        Robot.MoniPress(keyCode);
      }else{//�łȂ����
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
    //�A�˂Ȃ�C����ł���肾�ˁD
  }
}