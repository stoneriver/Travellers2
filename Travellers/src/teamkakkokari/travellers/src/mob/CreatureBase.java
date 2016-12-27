package teamkakkokari.travellers.src.mob;

/**
 * 生物に関するクラス。 HP、攻撃力、防御力、名前を含みます。
 *
 * @author TEAMKakkokari, T-O
 */
public class CreatureBase {
	
	/**
	 * Creatureのインスタンスを生成します。
	 *
	 * @param hp CreatureのHP
	 * @param attack Creatureの攻撃力
	 * @param defend Creatureの防御力
	 * @param name Creatureの名前
	 */
	public CreatureBase(int hp, int attack, int defend, String name) {
		this.hp = hp;
		this.attack = attack;
		this.defend = defend;
		this.name = name;
	}
	
	private int hp;
	private int attack;
	private int defend;
	private final String name;
	private int facing = FACING_UP;
	private int x;
	private int y;
	
	/**
	 * hpを取得します。
	 * 
	 * @return 取得するhp
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * hpを設定します。
	 * 
	 * @param hp 設定後のhp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	/**
	 * attackを取得します。
	 * 
	 * @return 取得するattack
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * attackを設定します。
	 * 
	 * @param attack 設定後のattack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	/**
	 * defendを取得します。
	 * 
	 * @return 取得するdefend
	 */
	public int getDefend() {
		return defend;
	}
	
	/**
	 * defendを設定します。
	 * 
	 * @param defend 設定後のdefend
	 */
	public void setDefend(int defend) {
		this.defend = defend;
	}
	
	/**
	 * facingを取得します。
	 * 
	 * @return 取得するfacing
	 */
	public int getFacing() {
		return facing;
	}
	
	/**
	 * facingを設定します。
	 * 
	 * @param facing 設定後のfacing
	 */
	public void setFacing(int facing) {
		this.facing = facing;
	}
	
	/**
	 * xを取得します。
	 * 
	 * @return 取得するx
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * xを設定します。
	 * 
	 * @param x 設定後のx
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * yを取得します。
	 * 
	 * @return 取得するy
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * yを設定します。
	 * 
	 * @param y 設定後のy
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * nameを取得します。
	 * 
	 * @return 取得するname
	 */
	public String getName() {
		return name;
	}
	
	public static final int FACING_UP = 0;
	public static final int FACING_DOWN = 1;
	public static final int FACING_LEFT = 2;
	public static final int FACING_RIGHT = 3;
	
	/**
	 * Creaureを向いている方向に応じて前に進ませます。
	 */
	public void walkForward() {
		switch (facing) {
			case FACING_UP:
				y--;
				break;
			case FACING_DOWN:
				y++;
				break;
			case FACING_LEFT:
				x--;
				break;
			case FACING_RIGHT:
				x++;
				break;
		}
	}
}
