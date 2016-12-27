package teamkakkokari.travellers1.src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import teamkakkokari.travellers.src.mob.CreatureBase;
import teamkakkokari.travellers.src.mob.PlayerBase;
import teamkakkokari.travellers.src.resource.ResourceManager;
import teamkakkokari.travellers1.src.map.Map;
import teamkakkokari.travellers1.src.map.MapLaboratory;

public final class GuiField extends Gui {
	
	private final int chipWidth = 32;
	public Map map;
	public PlayerBase player;
	public String[] console = new String[2];
	public GuiFieldVisible visible = GuiFieldVisible.NONE;
	public GuiFieldScene scene = GuiFieldScene.NORMAL;
	public int scrollingFlg = -1;
	public int zeroX = 0;
	public int zeroY = 0;
	
	public GuiField(ResourceManager manager, TravellersFrame invoker) {
		super(manager, invoker);
		player = new PlayerBase(
				manager._random.nextInt(10) + 30,
				manager._random.nextInt(10) + 10,
				0, "勇者", null, 0, 0, 0, manager);
		initComponents(new MapLaboratory());
	}
	
	public void initComponents(Map map) {
		//初期化処理----------------------------------------------------------------------
		this.map = map;
		player.setX(map._defaultX);
		player.setY(map._defaultY);
		player.setFacing(map._defaultFacing);
		zeroX = 0;
		zeroY = 0;
		console[0] = "";
		console[1] = "";
		mapScroll();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//バックグラウンド（背景）の描画---------------------------------------------------
		for (int y = 0; y < map._background.length; y++) {
			for (int x = 0; x < map._background[y].length; x++) {
				g.drawImage(
						manager._textureBackground[map._background[y][x].getid()],
						chipWidth * (x + zeroX), chipWidth * (y + zeroY), null);
			}
		}
		
		//ストラクチャ（設置物）の描画-----------------------------------------------------
		for (int y = 0; y < map._structure.length; y++) {
			for (int x = 0; x < map._structure[y].length; x++) {
				g.drawImage(
						manager._textureStructure[map._structure[y][x].getId()],
						chipWidth * (x + zeroX), chipWidth * (y + zeroY), null);
			}
		}
		
		switch (scrollingFlg) {
			case -1:
				paintNormal(g);
				break;
			default:
				paintScroll(g);
				break;
		}
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		
		//処理拒否------------------------------------------------------------------------
		if (e.getID() != KeyEvent.KEY_PRESSED ||
				(e.getKeyCode() != KeyEvent.VK_Z && visible != GuiFieldVisible.NONE) ||
				scrollingFlg != -1) {
			return;
		}
		
		//キープレスイベント時の処理------------------------------------------------------
		switch (scene) {
			case NORMAL:
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						preMovementProcess(CreatureBase.FACING_UP);
						break;
					case KeyEvent.VK_DOWN:
						preMovementProcess(CreatureBase.FACING_DOWN);
						break;
					case KeyEvent.VK_LEFT:
						preMovementProcess(CreatureBase.FACING_LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						preMovementProcess(CreatureBase.FACING_RIGHT);
						break;
					case KeyEvent.VK_X:
						visible = GuiFieldVisible.MENU;
						break;
					case KeyEvent.VK_Z:
						visible = GuiFieldVisible.NONE;
						break;
				}
				repaint();
				break;
			case CLEAR:
				invoker.setGameState(TravellersGameState.GAMECLEAR);
				break;
		}
	}
	
	private void paintNormal(Graphics g) {
		
		requestFocusInWindow();
		
		g.drawImage(manager._texturePlayer[player.getFacing()],
				chipWidth * player.getX(), chipWidth * player.getY(), null);
				
		switch (visible) {
			case NONE:
				break;
			case CONSOLE:
				g.setColor(new Color(0x66000000, true));
				g.fillRect(20, 340, 600, 120);
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
				g.drawString(console[0], 40, 380);
				g.drawString(console[1], 40, 430);
				break;
			case MENU:
				g.setColor(new Color(0x88000000, true));
				g.fillRect(160, 60, 320, 360);
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
				g.drawString("設定", 295, 100);
				break;
		}
	}
	
	private void paintScroll(Graphics g) {
		
		if (scrollingFlg >= 32) {
			movementProcess();
			scrollingFlg = -1;
			repaint();
			return;
		}
		
		int x = 0;
		int y = 0;
		switch (player.getFacing()) {
			case CreatureBase.FACING_UP:
				y = -scrollingFlg;
				break;
			case CreatureBase.FACING_DOWN:
				y = scrollingFlg;
				break;
			case CreatureBase.FACING_LEFT:
				x = -scrollingFlg;
				break;
			case CreatureBase.FACING_RIGHT:
				x = scrollingFlg;
				break;
		}
		g.drawImage(manager._texturePlayer[player.getFacing()],
				chipWidth * player.getX() + x, chipWidth * player.getY() + y, null);
				
		scrollingFlg += 4;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		repaint();
	}
	
	private void preMovementProcess(int facing) {
		player.setFacing(facing);
		scrollingFlg = 0;
		
		//プレ・イベント処理--------------------------------------------------------------
		int x = 0;
		int y = 0;
		switch (player.getFacing()) {
			case CreatureBase.FACING_UP:
				y = -1;
				break;
			case CreatureBase.FACING_DOWN:
				y = 1;
				break;
			case CreatureBase.FACING_LEFT:
				x = -1;
				break;
			case CreatureBase.FACING_RIGHT:
				x = 1;
				break;
		}
		map._structure[player.getY() - zeroY + y][player.getX() - zeroX + x].getBase().preActionTread(this);
		
		repaint();
	}
	
	private void movementProcess() {
		
		//プレイヤー移動処理--------------------------------------------------------------
		switch (player.getFacing()) {
			case CreatureBase.FACING_UP:
				player.setY(player.getY() - 1);
				break;
			case CreatureBase.FACING_DOWN:
				player.setY(player.getY() + 1);
				break;
			case CreatureBase.FACING_LEFT:
				player.setX(player.getX() - 1);
				break;
			case CreatureBase.FACING_RIGHT:
				player.setX(player.getX() + 1);
				break;
		}
		
		//マップスクロール処理------------------------------------------------------------
		mapScroll();
		
		//各ストラクチャ処理--------------------------------------------------------------
		map._structure[player.getY() - zeroY][player.getX() - zeroX].getBase().actionTread(this);
	}
	
	private void mapScroll() {
		boolean fixed = false;
		while (!fixed) {
			if (player.getX() < 4) {
				zeroX++;
				player.setX(player.getX() + 1);
			} else if (player.getX() > 17) {
				zeroX--;
				player.setX(player.getX() - 1);
			} else if (player.getY() < 4) {
				zeroY++;
				player.setY(player.getY() + 1);
			} else if (player.getY() > 12) {
				zeroY--;
				player.setY(player.getY() - 1);
			} else {
				fixed = true;
			}
		}
	}
	
}
