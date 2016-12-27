package teamkakkokari.travellers1.src.gui;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;

import teamkakkokari.travellers.src.mob.CreatureBase;
import teamkakkokari.travellers.src.mob.PlayerBase;
import teamkakkokari.travellers.src.resource.ResourceManager;
import teamkakkokari.travellers1.Travellers1;

public final class TravellersFrame extends JFrame {
	
	private TravellersGameState gameState = TravellersGameState.TITLE;
	private ResourceManager manager = new ResourceManager();
	public HashMap<TravellersGameState, Gui> gui = new HashMap<>();
	
	public TravellersFrame() {
		
		//各種処理--------------------------------------------------------------------------
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Travellers1.GAME_TITLE_JAPANESE);
		setResizable(false);
		getContentPane().setPreferredSize(new Dimension(640, 480));
		pack();
		
		//GUIを生成-------------------------------------------------------------------------
		gui.put(TravellersGameState.TITLE, new GuiTitle(manager, this));
		gui.put(TravellersGameState.FROMFIRST, new GuiFromFirst(manager, this));
		gui.put(TravellersGameState.FIELD, new GuiField(manager, this));
		gui.put(TravellersGameState.BATTLE, null);
		
		//各種処理--------------------------------------------------------------------------
		paint();
		setVisible(true);
		
	}
	
	private void paint() {
		
		//すべてのGUIを除去-----------------------------------------------------------------
		getContentPane().removeAll();
		
		//GUIを表示-------------------------------------------------------------------------
		switch (gameState) {
			case TITLE:
			case FROMFIRST:
			case FIELD:
			case BATTLE:
				add(gui.get(gameState));
				gui.get(gameState).setVisible(true);
				break;
			case GAMECLEAR:
				System.out.println("ゲームクリア！");
				System.exit(0);
				break;
			case GAMEOVER:
				System.out.println("ゲームオーバー・・・");
				System.exit(0);
				break;
		}
		
		//各種処理--------------------------------------------------------------------------
		repaint();
		revalidate();
		
	}
	
	public void setGameState(TravellersGameState state) {
		this.gameState = state;
		paint();
	}
	
	public void setGameStateBattle(PlayerBase player, CreatureBase creature) {
		this.gameState = TravellersGameState.BATTLE;
		gui.replace(TravellersGameState.BATTLE, new GuiBattle(manager, this, player, creature));
		paint();
	}
	
	public void setGameStateFromBattle(TravellersGameState state) {
		this.gameState = state;
		gui.replace(TravellersGameState.BATTLE, null);
		paint();
	}
	
}
