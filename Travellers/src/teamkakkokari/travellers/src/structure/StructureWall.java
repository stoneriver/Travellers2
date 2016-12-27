package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers1.src.gui.GuiField;

public final class StructureWall extends StructureBase {
	
	@Override
	public void preActionTread(GuiField gui) {
		gui.scrollingFlg = -1;
	}

}
