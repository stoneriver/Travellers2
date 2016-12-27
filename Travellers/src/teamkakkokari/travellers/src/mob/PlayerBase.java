package teamkakkokari.travellers.src.mob;

import teamkakkokari.travellers.src.item.Inventory;
import teamkakkokari.travellers.src.resource.ResourceManager;

/**
 * プレーヤーに関するクラス。 ゴールド、経験値、レベルを含みます。
 *
 * @author TEAMKakkokari、T-I
 */
public class PlayerBase extends CharacterBase {

	/**
	 * Playerのインスタンスを生成します。
	 *
	 * @param hp PlayerのHP
	 * @param attack Playerの攻撃力
	 * @param defend Playerの防御力
	 * @param name Playerの名前
	 * @param inventory Playerのインベントリ
	 * @param gold Playerのゴールド
	 * @param exp Playerの経験値
	 * @param level Playerのレベル
	 * @param manager ResourceManagerオブジェクト
	 */
	public PlayerBase(int hp, int attack, int defend, String name, Inventory inventory, int gold, int exp, int level, ResourceManager manager) {
		super(hp, attack, defend, name, inventory);
		this.gold = gold;
		this.exp = exp;
		this.level = level;
	}

	private int gold;
	private int exp;
	private int level;
	
	/**
	 * goldを取得します。
	 * 
	 * @return 取得するgold
	 */
	public int getGold() {
		return gold;
	}
	/**
	 * goldを設定します。
	 * 
	 * @param gold 設定後のgold
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	/**
	 * expを取得します。
	 * 
	 * @return 取得するexp
	 */
	public int getExp() {
		return exp;
	}
	/**
	 * expを設定します。
	 * 
	 * @param exp 設定後のexp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	/**
	 * levelを取得します。
	 * 
	 * @return 取得するlevel
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * levelを設定します。
	 * 
	 * @param level 設定後のlevel
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
