package teamkakkokari.travellers.src.structure;

public enum Structure {
	
	AIR(new StructureAir(), null),
	WALL(new StructureWall(), null),
	BOX1(new StructureBox(), "Box1.png"),
	CHEST1(new StructureBox(), "Chest1.png"),
	CHEST2(new StructureBox(), "Chest2.png"),
	DOOR(new StructureDoor(), "Door1.png"),
	KEY(new StructureKey(), "Key.png"),
	;
	
	private final int id;
	private final String texture;
	private final StructureBase base;
	
	public final static Structure[] structures = Structure.values();
	private static int latestID = -1;
	
	private static int getID() {
		return latestID++;
	}
	
	private Structure(StructureBase base, String texture) {
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
	public StructureBase getBase() {
		return base;
	}

	public static Structure getStructure(int id) {
		for (Structure structure : structures) {
			if (structure.id == id) {
				return structure;
			}
		}
		return null;
	}
}
