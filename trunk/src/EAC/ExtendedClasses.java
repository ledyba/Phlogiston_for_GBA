package EAC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//����R���|�[�l���g�u����

/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 *
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 *
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 *
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 *
 * @author ������
 * @version 1.0
 */


  class MyJButton extends javax.swing.JButton{
    private int Mode = 0;
    private String DefaultText="";
    private boolean AutoReweiteText = false;
    public void setButtonMode(int mode){
      this.Mode = mode;
    }
    public int getButtonMode(){
      return this.Mode;
    }
    public void removeAllKeyListeners(){
      KeyListener listeners[] = getKeyListeners();
      for(int i=0;i<listeners.length;i++){
        removeKeyListener(listeners[i]);
      }
    }
  /*
    public void setAutoRewriteText(boolean flag){
      //�L�[���B�ꂽ�Ƃ��Ɏ����I�Ƀ��X�i�������C�e�L�X�g����͂��Ă���邩�D
      AutoReweiteText = flag;
    }
    public boolean getAutoRewriteText(){
      //�L�[���B�ꂽ�Ƃ��Ɏ����I�Ƀ��X�i�������C�e�L�X�g����͂��Ă���邩�D
      return AutoReweiteText;
    }
    public void setDefaultText(String str){
      DefaultText = str;
    }
    public String getDefaultText(){
        return DefaultText;
      }*/
    public void init(String text,Icon icon){
      super.init(text,icon);
      //�����ɍ��ꂽ�Ƃ��̏������������
     /* this.addComponentListener(new ComponentAdapter(){//�E�C���h�E�̃��X�i�̒ǉ�
      public void componentHidden(ComponentEvent e){
        if(getAutoRewriteText()){
          removeAllKeyListeners(); //���X�i�������Ă����܂��傤
          setText(getDefaultText());
        }
      }
       public void componentShown(ComponentEvent e){
       }
      });*/
    }
    public void processKeyEvent(KeyEvent e){//�X�y�[�X�L�[��������������āE�E�E�D
      //System.out.println(this.getKeyListeners().length);
      //    super.processKeyEvent(e);
      //�������}�E�X�ŉ����Ă����Ȃ��̂ɃX�y�[�X�L�[��������������ĉ������C�Ƃ����̂����Ȃ�C
      //�����Ŋe���X�i�ɏ��������͂����邵������܂�
      //�ςȏ����͖����D
      KeyListener listeners[] = getKeyListeners();
      for(int i=0;i<listeners.length;i++){
        switch(e.getID()){
          case KeyEvent.KEY_PRESSED:
            listeners[i].keyPressed(e);
            break;
          case KeyEvent.KEY_RELEASED:
            listeners[i].keyReleased(e);
            break;
          case KeyEvent.KEY_TYPED:
            listeners[i].keyTyped(e);
            break;
        }
      }
      e.consume();//�C�x���g�͏���Ă����܂��傤�D�łȂ��Ƒ�ςȂ��ƂɁE�E�E�D
    }
    public void processFocusEvent(FocusEvent e){
        //���ƋC�ɂȂ�̂�Tab�Ńt�H�[�J�X���ڂ��Ă��܂��������ǁE�E�E�d���Ȃ���ˁD
        super.processFocusEvent(e);
    }
  }
  class IconLoader extends ClassLoader{
    Image load(String Str){
      Image img = null;
      img = Toolkit.getDefaultToolkit().createImage(EAC.MainFrame.class.getResource(Str));
      return img;
    }
}
//�ȉ����ݎg�p���Ă��Ȃ�
/*
  class MyJRadioButton extends JRadioButton {
    public void processKeyEvent(KeyEvent e){
        //�������Ȃ��悤�ɂ��Ă��C�_���I�Ȃ������X�i�ɒʒm����Ȃ��I
      }
    }
    class MyJComboBox extends javax.swing.JComboBox {
      public MyJComboBox(final Object items[]) {
        super(items);
      }
      public void processKeyEvent(KeyEvent e){
          //�������Ȃ��E�E�E�I�H
        }
      }
  class CustomizedFocusManager extends javax.swing.FocusManager{
    public void processKeyEvent(Component c,KeyEvent e){
      //�������Ȃ�
    }
  }
*/
