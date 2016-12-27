package teamkakkokari.travellers.src.structure;

import teamkakkokari.travellers1.src.gui.GuiField;

public abstract class StructureBase {
	
	/**
	 * Creatureがこのストラクチャを踏んだ時のアクションです。
	 * これをサブクラスでオーバーライドしない場合、アクションは何もありません。
	 * もし引数が必要な場合はサブクラスでオーバーロードしてください。
	 * @param gui Creatureの存在するGuiField。通常はthisを指定してください。
	 */
	public void actionTread(GuiField gui) {
	}
	
	/**
	 * Creatureがこのストラクチャを踏もうとした時のアクションです。
	 * これをサブクラスでオーバーライドしない場合、アクションは何もありません。
	 * もし引数が必要な場合はサブクラスでオーバーロードしてください。
	 * @param gui Creatureの存在するGuiField。通常はthisを指定してください。
	 */
	public void preActionTread(GuiField gui) {
	}

}
