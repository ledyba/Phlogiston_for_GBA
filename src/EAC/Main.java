package EAC;

import javax.swing.UIManager;
import java.awt.*;

/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class Main {
  boolean packFrame = false;

  //アプリケーションのビルド
  public Main() {
    Splash Splash = new Splash();
    Splash.wshow();
    MainFrame frame = new MainFrame();
    //validate() はサイズを調整する
    //pack() は有効なサイズ情報をレイアウトなどから取得する
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //ウィンドウを中央に配置
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

    //メインウインドウを開いたらSplashも消すこと
    Splash.dispose();

    //メインウインドウを開く
    frame.setVisible(true);
  }
  //Main メソッド
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new Main();
  }
}
