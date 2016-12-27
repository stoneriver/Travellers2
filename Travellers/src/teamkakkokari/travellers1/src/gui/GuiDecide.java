package teamkakkokari.travellers1.src.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import teamkakkokari.travellers.src.resource.ResourceManager;

/**
 * 上下キーを使って選択をするGuiです。
 * 
 * @author TEAMKakkokari, T-I
 *
 */
public abstract class GuiDecide extends Gui {
	
	public GuiDecide(ResourceManager manager, TravellersFrame invoker) {
		super(manager, invoker);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocusInWindow();
	}
	
	/**
	 * 上下キーのキーイベントが発生した時、それぞれcursorUp()、cursorDown()を実行します。<br>
	 * サブクラスでこのメソッドをオーバーライドするときは、必ず最初にsuper.processKeyEvent(KeyEvent)を呼んでください。
	 */
	@Override
	protected void processKeyEvent(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				cursorUp();
				break;
			case KeyEvent.VK_DOWN:
				cursorDown();
				break;
		}
	}
	
	protected abstract void cursorUp();
	
	protected abstract void cursorDown();
	
}
