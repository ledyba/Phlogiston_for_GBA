package EAC;
import java.io.*;
/**
 * <p>�^�C�g��: �G�~�����[�^�E�I�[�g�}�`�b�N�E�R���g���[��</p>
 * <p>����: �G�~���G���[�^���������c���܂��D</p>
 * <p>���쌠: Copyright (c) 2005 PSI</p>
 * <p>��Ж�: �Ձi�v�T�C�j�̋����֐S���</p>
 * @author ������
 * @version 1.0
 */

public class SaveFile {
  public SaveFile() {
  }
  void saveFile(File SaveFile){//�t�@�C�����Z�[�u
    Setting Setting = new Setting();
    Setting.SaveSetting(SaveFile); //�����I
    MainFrame.StatusBar.setText("Saving File (" + SaveFile.getName() +
                      ") has finished."); //�ʒm

    return;
  }

}