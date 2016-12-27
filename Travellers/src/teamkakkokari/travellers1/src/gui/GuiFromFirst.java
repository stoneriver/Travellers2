package teamkakkokari.travellers1.src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import teamkakkokari.travellers.src.resource.ResourceManager;

public class GuiFromFirst extends Gui {

	public GuiFromFirst(ResourceManager manager, TravellersFrame invoker) {
		super(manager, invoker);
		setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		//各種処理--------------------------------------------------------------------------
		super.paintComponent(g);
		requestFocusInWindow();
		
		//表示処理--------------------------------------------------------------------------
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("冒険が始まる・・", 100, 100);
	}
	
	@Override
	protected void processComponentKeyEvent(KeyEvent e) {
		if (e.getID() != KeyEvent.KEY_PRESSED) {
			return;
		}
		//TODO キー分岐処理
		invoker.setGameState(TravellersGameState.FIELD);
	}

}
