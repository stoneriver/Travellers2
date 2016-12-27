package teamkakkokari.travellers1.src.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import teamkakkokari.travellers1.Travellers1;

/**
 * バージョン情報を表示するFrame。
 * 
 * @author TEAMKakkokari, T-K, T-I
 *
 */
public class GuiVersionInfo extends JFrame {
	
	public GuiVersionInfo() {
		setSize(320, 240);
		setLayout(new FlowLayout());
		setTitle("Version Info");
		Font font = new Font(Font.DIALOG, Font.PLAIN, 16);
		JLabel[] jLabels = {
				new JLabel(Travellers1.GAME_TITLE_JAPANESE),
				new JLabel(Travellers1.GAME_VERSION),
				new JLabel("Press any key to return..."),
		};
		for (int i = 0; i < jLabels.length; i++) {
			jLabels[i].setFont(font);
			add(jLabels[i]);
		}
		setVisible(true);
	}
	
	@Override
	protected void processKeyEvent(java.awt.event.KeyEvent e) {
		if (e.getID() != KeyEvent.KEY_PRESSED) {
			return;
		}
		setVisible(false);
	}
	
}
