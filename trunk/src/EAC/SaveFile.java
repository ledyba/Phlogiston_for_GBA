package EAC;
import java.io.*;
/**
 * <p>タイトル: エミュレータ・オートマチック・コントローラ</p>
 * <p>説明: エミュエレータを自動操縦します．</p>
 * <p>著作権: Copyright (c) 2005 PSI</p>
 * <p>会社名: ψ（プサイ）の興味関心空間</p>
 * @author 未入力
 * @version 1.0
 */

public class SaveFile {
  public SaveFile() {
  }
  void saveFile(File SaveFile){//ファイルをセーブ
    Setting Setting = new Setting();
    Setting.SaveSetting(SaveFile); //書く！
    MainFrame.StatusBar.setText("Saving File (" + SaveFile.getName() +
                      ") has finished."); //通知

    return;
  }

}