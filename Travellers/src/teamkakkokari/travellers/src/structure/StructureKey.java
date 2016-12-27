package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers1.src.gui.GuiField;
import teamkakkokari.travellers1.src.gui.GuiFieldScene;
import teamkakkokari.travellers1.src.gui.GuiFieldVisible;

public final class StructureKey extends StructureBase {
	
	@Override
	public void actionTread(GuiField gui) {
		gui.map._structure[gui.player.getY() - gui.zeroY][gui.player.getY() - gui.zeroX] = Structure.AIR;
		gui.console[0] = "鍵だ！";
		gui.console[1] = "ゲームクリア！";
		gui.visible = GuiFieldVisible.CONSOLE;
		gui.scene = GuiFieldScene.CLEAR;
	}

}
