package teamkakkokari.travellers.src.mob;

import teamkakkokari.travellers.src.item.Inventory;

/**
 * キャラクターに関するクラス。Inventoryを含みます。
 *
 * @author TEAMKakkokari, T-I
 */
public class CharacterBase extends CreatureBase {
	
	private final Inventory inventory;
	
	/**
	 * Characterのインスタンスを生成します。
	 *
	 * @param hp CharacterのHP
	 * @param attack Characterの攻撃力
	 * @param defend Characterの防御力
	 * @param name Characterの名前
	 * @param inventory Characterのインベントリ
	 */
	public CharacterBase(int hp, int attack, int defend, String name, Inventory inventory) {
		super(hp, attack, defend, name);
		this.inventory = inventory;
	}

	/**
	 * inventoryを取得します。
	 * 
	 * @return 取得するinventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
}
