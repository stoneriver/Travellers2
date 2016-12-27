package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers1.src.gui.GuiField;
import teamkakkokari.travellers1.src.gui.GuiFieldVisible;

public final class StructureBox extends StructureBase {
	
	@Override
	public void actionTread(GuiField gui) {
		gui.map._structure[gui.player.getY() - gui.zeroY][gui.player.getX() - gui.zeroX] = Structure.AIR;
		gui.console[0] = "箱だ！";
		gui.visible = GuiFieldVisible.CONSOLE;
	}

}
