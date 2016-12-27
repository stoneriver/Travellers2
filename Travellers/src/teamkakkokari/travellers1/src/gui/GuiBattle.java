package teamkakkokari.travellers1.src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import teamkakkokari.travellers.src.mob.CreatureBase;
import teamkakkokari.travellers.src.mob.PlayerBase;
import teamkakkokari.travellers.src.resource.ResourceManager;

public final class GuiBattle extends GuiDecide {
	
	enum BattleSelect {
		ROOT_ATTACK(37, 340), ROOT_ITEM(37, 380), ROOT_ESCAPE(37, 420),
		
		ATTACK_1(200, 340),
		
		ESCAPE_1(200, 340)
		
		;
		
		public final int x;
		public final int y;
		
		private BattleSelect(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public final CreatureBase enemy;
	public final PlayerBase player;
	private BattleSelect select = BattleSelect.ROOT_ATTACK;
	public GuiBattleScene scene = GuiBattleScene.DEFAULT;
	public String[] console = new String[3];
	public boolean battleContinueFlg = true;
	
	public GuiBattle(ResourceManager manager, TravellersFrame invoker, PlayerBase player, CreatureBase enemy) {
		super(manager, invoker);
		this.enemy = enemy;
		this.player = player;
		setBackground(Color.BLACK);
		console[0] = "何をする？";
		console[1] = "";
		console[2] = "";
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (!battleContinueFlg) {
			invoker.setGameStateFromBattle(TravellersGameState.FIELD);
		}
		
		g.setColor(Color.WHITE);
		g.drawRoundRect(20, 300, 145, 160, 8, 8);
		g.drawRoundRect(180, 300, 420, 160, 8, 8);
		g.drawRoundRect(150, 10, 470, 35, 8, 8);
		g.drawRoundRect(20, 255, 480, 35, 8, 8);
		
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 27));
		
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		g.drawString("▶", select.x, select.y);
		
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 27));
		g.drawString("攻撃", 55, 340);
		g.drawString("アイテム", 55, 380);
		g.drawString("逃げる", 55, 420);
		g.drawString(console[0], 218, 340);
		g.drawString(console[1], 218, 380);
		g.drawString(console[2], 218, 420);
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 23));
		//TODO 敵・味方情報の表示
		g.drawString(
				enemy.getName() +
						"：HP" + String.valueOf(enemy.getHp()) +
						"；ATK" + String.valueOf(enemy.getAttack()),
				160, 35);
		g.drawString(
				player.getName() +
						"：HP" + String.valueOf(player.getHp()) +
						"；ATK" + String.valueOf(player.getAttack()),
				30, 280);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		if (e.getID() != KeyEvent.KEY_PRESSED) {
			return;
		}
		super.processKeyEvent(e);
		//TODO selectに応じた各種処理の追加
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z:
				if (scene == GuiBattleScene.LOSE) {
					invoker.setGameState(TravellersGameState.GAMEOVER);
				} else if (scene == GuiBattleScene.WIN ||
						scene == GuiBattleScene.ESCAPE) {
					invoker.setGameState(TravellersGameState.FIELD);
				}
				switch (select) {
					case ROOT_ATTACK:
						select = BattleSelect.ATTACK_1;
						scene = GuiBattleScene.ATTACK;
						console[0] = "Zで攻撃";
						break;
					case ROOT_ITEM:
						//ONHOLD アイテム実装でコメントアウト解除
						//slect = BattleSelect.BRANCH_1;
						//scene = BattleScene.ITEM;
						break;
					case ROOT_ESCAPE:
						console[0] = "逃げた！";
						select = BattleSelect.ESCAPE_1;
						scene = GuiBattleScene.ESCAPE;
						break;
					case ATTACK_1:
						enemy.setHp(enemy.getHp() - player.getAttack());
						if (enemy.getHp() <= 0) {
							enemy.setHp(0);
							console[0] = "勝った！";
							scene = GuiBattleScene.WIN;
							break;
						}
						player.setHp(player.getHp() - enemy.getAttack());
						if (player.getHp() <= 0) {
							player.setHp(0);;
							console[0] = "負けた・・・・";
							scene = GuiBattleScene.LOSE;
						}
						break;
					case ESCAPE_1:
						battleContinueFlg = false;
						break;
				}
				break;
			case KeyEvent.VK_X:
				switch (select) {
					case ATTACK_1:
					case ESCAPE_1:
						select = BattleSelect.ROOT_ATTACK;
						break;
					default:
						break;
				}
				scene = GuiBattleScene.DEFAULT;
				break;
		}
		
		repaint();
		
	}
	
	@Override
	protected void cursorUp() {
		switch (select) {
			case ROOT_ATTACK:
				select = BattleSelect.ROOT_ESCAPE;
				break;
			case ROOT_ITEM:
				select = BattleSelect.ROOT_ATTACK;
				break;
			case ROOT_ESCAPE:
				select = BattleSelect.ROOT_ITEM;
				break;
			case ATTACK_1:
				break;
			case ESCAPE_1:
				break;
		}
	}
	
	@Override
	protected void cursorDown() {
		switch (select) {
			case ROOT_ATTACK:
				select = BattleSelect.ROOT_ITEM;
				break;
			case ROOT_ITEM:
				select = BattleSelect.ROOT_ESCAPE;
				break;
			case ROOT_ESCAPE:
				select = BattleSelect.ROOT_ATTACK;
				break;
			case ATTACK_1:
				break;
			case ESCAPE_1:
				break;
		}
	}
}
