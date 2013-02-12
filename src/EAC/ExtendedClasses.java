package EAC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//自作コンポーネント置き場

/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 *
 * <p>説明: エミュエレータを自動操縦します．</p>
 *
 * <p>著作権: Copyright (c) 2005 PSI</p>
 *
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 *
 * @author 未入力
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
      //キーが隠れたときに自動的にリスナを消し，テキストを入力してくれるか．
      AutoReweiteText = flag;
    }
    public boolean getAutoRewriteText(){
      //キーが隠れたときに自動的にリスナを消し，テキストを入力してくれるか．
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
      //ここに作られたときの処理をかけるよ
     /* this.addComponentListener(new ComponentAdapter(){//ウインドウのリスナの追加
      public void componentHidden(ComponentEvent e){
        if(getAutoRewriteText()){
          removeAllKeyListeners(); //リスナをけしておきましょう
          setText(getDefaultText());
        }
      }
       public void componentShown(ComponentEvent e){
       }
      });*/
    }
    public void processKeyEvent(KeyEvent e){//スペースキーを押したからって・・・．
      //System.out.println(this.getKeyListeners().length);
      //    super.processKeyEvent(e);
      //もしもマウスで押してもいないのにスペースキーを押したからって押される，というのが嫌なら，
      //自分で各リスナに処理をお届けするしかあるまい
      //変な処理は無視．
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
      e.consume();//イベントは消費しておきましょう．でないと大変なことに・・・．
    }
    public void processFocusEvent(FocusEvent e){
        //あと気になるのはTabでフォーカスが移ってしまう件だけど・・・仕方ないよね．
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
//以下現在使用していない
/*
  class MyJRadioButton extends JRadioButton {
    public void processKeyEvent(KeyEvent e){
        //何もしないようにしても，ダメ！なぜかリスナに通知されない！
      }
    }
    class MyJComboBox extends javax.swing.JComboBox {
      public MyJComboBox(final Object items[]) {
        super(items);
      }
      public void processKeyEvent(KeyEvent e){
          //何もしない・・・！？
        }
      }
  class CustomizedFocusManager extends javax.swing.FocusManager{
    public void processKeyEvent(Component c,KeyEvent e){
      //何もしない
    }
  }
*/
