package EAC.controller;

import java.util.Timer;
import java.util.TimerTask;

import EAC.*;
/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 * @author ������
 * @version 1.0
 */

public class controller extends Thread{
  public static RobotCore RobotCore;
  public static Timer Timer;//�^�C�}���쐬����
  public controller() {
    Timer = new Timer(false);
  }
  public void run(){
    try{
      RobotCore = new RobotCore();
      /*�㉺���E���[�h
       0:LR 1:UD
       **_reciprocating_Radio�@�� **_keeping_Radio
       */
      LR();
      UD();
      /*�{�^�����[�h
       2:A 3:B 4:L 5:R 7:Start 8:Select
       *_Autofire_Radio�@�Ɓ@*_keeping_Radio
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
    public void exit(){//�R���g���[�����I��������
      Timer.cancel();
      Timer = null;
      RobotCore.AllRelease();//���ׂĂ͂Ȃ�
      return;
    }

  public void LR() throws Exception{
    if (MainFrame.AllButtons.isSelectedIndex(0)) { //�I���ɂȂ��Ă���Ȃ��
      if(MainFrame.LR_ModeGroup.isSelected(MainFrame.LR_reciprocating_Radio.getModel())){//�u�����^���v���[�h��
        float interval = Float.parseFloat(MainFrame.
                                         LR_reciprocating_interval_Field.
                                         getText());
        if((interval * 1000) >= 1){//1�~���b�����̏ꍇ�͍s��Ȃ�
          TimerTask Task = null;
          if (MainFrame.LR_reciprocating_first_key_ComboBox.getSelectedIndex() ==
              0) { //�Ȃɂ��Ȃ��F�����i�d�l)
          }
          else if (MainFrame.LR_reciprocating_first_key_ComboBox.
                   getSelectedIndex() == 1) { //Left
            Task = new Reciprocating_Core(RobotCore.Left, RobotCore.Right,
                                          RobotCore.Left); //������͂��߂�
          }
          else { //Right
            Task = new Reciprocating_Core(RobotCore.Right, RobotCore.Left,
                                          RobotCore.Right); //�E����͂��߂�
          }

          if (Task != null) {
            long longInterval = (long)(interval * 1000);
            Timer.scheduleAtFixedRate(Task, 0, longInterval);
          }
        }

      }else if(MainFrame.LR_ModeGroup.isSelected(MainFrame.LR_keeping_Radio.getModel())){//�u��ԃL�[�v�v���[�h��
        if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 0){//�L�[��0�Ԗځ��Œ肵�Ȃ�
        }else if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 1){//Left
          RobotCore.Right_Release();//Right�L�[�𗣂�
          RobotCore.Left_Press();//Left�L�[������
        }else if(MainFrame.LR_keeping_key_ComboBox.getSelectedIndex() == 2){//Right
          RobotCore.Left_Release();//Left�L�[�𗣂�
          RobotCore.Right_Press();//Right�L�[������
        }else{}
      }
    }
  }
  public void UD() throws Exception{
    if (MainFrame.AllButtons.isSelectedIndex(1)) { //�I���ɂȂ��Ă���Ȃ��
      if(MainFrame.UD_ModeGroup.isSelected(MainFrame.UD_reciprocating_Radio.getModel())){//�u�����^���v���[�h��
        float interval = Float.parseFloat (MainFrame.UD_reciprocating_interval_Field.getText());
        if((interval * 1000) >= 1){//1�~���b�����̏ꍇ�͍s��Ȃ�
          TimerTask Task = null;
          if (MainFrame.UD_reciprocating_first_key_ComboBox.getSelectedIndex() ==
              0) { //�Ȃɂ��Ȃ��F�����i�d�l)
          }
          else if (MainFrame.UD_reciprocating_first_key_ComboBox.
                   getSelectedIndex() == 1) { //Up
            Task = new Reciprocating_Core(RobotCore.Up, RobotCore.Down,
                                          RobotCore.Up); //�ォ��
          }
          else { //Down
            Task = new Reciprocating_Core(RobotCore.Down, RobotCore.Up,
                                          RobotCore.Down); //������
          }
          if (Task != null) {
            long longInterval = (long)(interval * 1000);
            Timer.scheduleAtFixedRate(Task, 0, longInterval);
          }
        }

      }else if(MainFrame.UD_ModeGroup.isSelected(MainFrame.UD_keeping_Radio.getModel())){//�u��ԃL�[�v�v���[�h��
        if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 0){//�L�[��0�Ԗځ��Œ肵�Ȃ�
        }else if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 1){//Up
          RobotCore.Down_Release();//Down�L�[�𗣂�
          RobotCore.Up_Press();//Up�L�[������
        }else if(MainFrame.UD_keeping_key_ComboBox.getSelectedIndex() == 2){//Down
          RobotCore.Up_Release();//Up�L�[�𗣂�
          RobotCore.Down_Press();//Down�L�[������
        }else{}
      }
    }
  }

  public void A(){
    if (MainFrame.AllButtons.isSelectedIndex(2)) { //�I���ɂȂ��Ă���Ȃ��
      if (MainFrame.A_ModeGroup.isSelected(MainFrame.A_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.A_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.A_Press();//����
        }else{//off�Ȃ�
          RobotCore.A_Release();//����
        }
      }
    }
  }
  public void B(){
    if (MainFrame.AllButtons.isSelectedIndex(3)) { //�I���ɂȂ��Ă���Ȃ��

      if (MainFrame.B_ModeGroup.isSelected(MainFrame.B_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.B_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.B_Press();//����
        }else{//off�Ȃ�
          RobotCore.B_Release();//����
        }
      }
    }
  }
  public void L(){
    if (MainFrame.AllButtons.isSelectedIndex(4)) { //�I���ɂȂ��Ă���Ȃ��

      if (MainFrame.L_ModeGroup.isSelected(MainFrame.L_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.L_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.L_Press();//����
        }else{//off�Ȃ�
          RobotCore.L_Release();//����
        }
      }
    }
  }
  public void R(){
    if (MainFrame.AllButtons.isSelectedIndex(5)) { //�I���ɂȂ��Ă���Ȃ��

      if (MainFrame.R_ModeGroup.isSelected(MainFrame.R_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.R_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.R_Press();//����
        }else{//off�Ȃ�
          RobotCore.R_Release();//����
        }
      }
    }
  }
  public void Start(){
    if (MainFrame.AllButtons.isSelectedIndex(6)) { //�I���ɂȂ��Ă���Ȃ��

      if (MainFrame.Start_ModeGroup.isSelected(MainFrame.Start_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.Start_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.Start_Press();//����
        }else{//off�Ȃ�
          RobotCore.Start_Release();//����
        }
      }
    }
  }
  public void Select(){
    if (MainFrame.AllButtons.isSelectedIndex(7)) { //�I���ɂȂ��Ă���Ȃ��

      if (MainFrame.Select_ModeGroup.isSelected(MainFrame.Select_Autofire_Radio.
                                            getModel())) { //�u�A�ˁv���[�h��
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
          getModel())) { //�u��ԃL�[�v�v���[�h��
        if(MainFrame.Select_keeping_state_ComboBox.getSelectedIndex() == 0){//on�Ȃ�
          RobotCore.Select_Press();//����
        }else{//off�Ȃ�
          RobotCore.Select_Release();//����
        }
      }
    }
  }

}