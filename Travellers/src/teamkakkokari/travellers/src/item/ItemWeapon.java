package teamkakkokari.travellers.src.item;

import teamkakkokari.travellers1.src.gui.GuiBattle;
import teamkakkokari.travellers1.src.gui.GuiBattleScene;

public class ItemWeapon extends ItemBase {
	
	@Override
	public void useEventIn(GuiBattle gui) {
		gui.enemy.setHp(gui.enemy.getHp() - gui.player.getAttack());
		if (gui.enemy.getHp() <= 0) {
			gui.enemy.setHp(0);
			gui.console[0] = "勝った！";
			gui.scene = GuiBattleScene.WIN;
			return;
		}
		gui.player.setHp(gui.player.getHp() - gui.enemy.getAttack());
		if (gui.player.getHp() <= 0) {
			gui.player.setHp(0);
			gui.console[0] = "負けた・・・・";
			gui.scene = GuiBattleScene.LOSE;
			return;
		}
	}
	
}
