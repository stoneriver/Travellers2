package teamkakkokari.travellers1.src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import teamkakkokari.travellers.src.resource.ResourceManager;
import teamkakkokari.travellers1.Travellers1;

/**
 * タイトル画面のクラス。
 * 
 * @author TEAMKakkokari, T-I
 *
 */
public class GuiTitle extends GuiDecide {
	
	enum Select {
		FROMFIRST, FROMSAVE, SETTING
	}
	
	private Select select = Select.FROMFIRST;
	
	public GuiTitle(ResourceManager manager, TravellersFrame invoker) {
		super(manager, invoker);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		//各種処理-------------------------------------------------------------------------
		super.paintComponent(g);
		
		
		//表示処理-------------------------------------------------------------------------
		g.drawImage(manager._logo, 0, 0, null);
		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		g.drawString("Copyright © 2015-2016 TEAMKakkokari All Rights Reserved. ", 135, 475);
		
		g.setColor(new Color(0xffffff));
		g.drawString("Version:" + Travellers1.GAME_VERSION, 10, 20);
		
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
		g.setColor(new Color(0x000000));
		g.drawString("最初から", 260, 320);
		g.drawString("続きから", 260, 360);
		g.drawString("設定", 260, 400);
		
		//▶の表示--------------------------------------------------------------------------
		int i = 0;
		switch (select) {
			case FROMFIRST:
				i = 320;
				break;
			case FROMSAVE:
				i = 360;
				break;
			case SETTING:
				i = 400;
				break;
		}
		g.drawString("▶", 230, i);
	}
	
	protected void processKeyEvent(KeyEvent e) {
		if (e.getID() != KeyEvent.KEY_PRESSED) {
			return;
		}
		super.processKeyEvent(e);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z:
				switch (select) {
					case FROMFIRST:
						invoker.setGameState(TravellersGameState.FROMFIRST);
						break;
					case FROMSAVE:
						invoker.setGameState(TravellersGameState.FIELD);
						break;
					case SETTING:
						break;
				}
				break;
			case KeyEvent.VK_V:
				new GuiVersionInfo();
				break;
		}
		repaint();
	}
	
	@Override
	protected void cursorUp() {
		switch (select) {
			case FROMFIRST:
				select = Select.SETTING;
				break;
			case FROMSAVE:
				select = Select.FROMFIRST;
				break;
			case SETTING:
				select = Select.FROMSAVE;
				break;
		}
	}
	
	@Override
	protected void cursorDown() {
		switch (select) {
			case FROMFIRST: {
				select = Select.FROMSAVE;
				break;
			}
			case FROMSAVE: {
				select = Select.SETTING;
				break;
			}
			case SETTING: {
				select = Select.FROMFIRST;
				break;
			}
		}
	}
	
}