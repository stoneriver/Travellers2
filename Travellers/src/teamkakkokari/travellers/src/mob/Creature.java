package teamkakkokari.travellers.src.mob;

public enum Creature {
	TEST(new CreatureBase(0, 0, 0, "敵"), "Ameba.png"),;
	
	private final int id;
	private final String texture;
	private final CreatureBase base;
	
	public final static Creature[] CREATURES = Creature.values();
	private static int latestID = -1;
	
	private static int getID() {
		return latestID++;
	}
	
	private Creature(CreatureBase base, String texture) {
		id = getID();
		this.base = base;
		this.texture = texture;
	}
	
	/**
	 * idを取得します。
	 * 
	 * @return 取得するid
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * textureを取得します。
	 * 
	 * @return 取得するtexture
	 */
	public String getTexture() {
		return texture;
	}
	
	/**
	 * baseを取得します。
	 * 
	 * @return 取得するbase
	 */
	public CreatureBase getBase() {
		return base;
	}
	
}
