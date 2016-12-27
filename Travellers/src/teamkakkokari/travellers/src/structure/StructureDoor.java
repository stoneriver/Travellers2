package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers1.src.gui.GuiField;
import teamkakkokari.travellers1.src.map.MapGrassLand;

public final class StructureDoor extends StructureBase {
	
	@Override
	public void actionTread(GuiField gui) {
		gui.initComponents(new MapGrassLand());
	}

}
