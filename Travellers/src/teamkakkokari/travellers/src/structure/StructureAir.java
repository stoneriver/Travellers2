package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers.src.mob.Creature;
import teamkakkokari.travellers1.src.gui.GuiField;

public final class StructureAir extends StructureBase {
	
	@Override
	public void actionTread(GuiField gui) {
		//ONHOLD リリース時に確率変更3
		if (4 < gui.manager._random.nextInt(5)) {
			gui.invoker.setGameStateBattle(gui.player, Creature.TEST.getBase());
		}
	}

}
