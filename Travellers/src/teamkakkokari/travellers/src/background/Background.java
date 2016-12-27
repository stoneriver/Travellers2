package teamkakkokari.travellers.src.background;

public enum Background {
	
	GRASS("Grass.png"),
	TATAMI1("Tatami1.png"),
	TATAMI2("Tatami2.png"),
	TATAMI3("Tatami3.png"),
	TATAMI4("Tatami4.png"),
	TATAMI5("Tatami5.png"),
	TATAMI6("Tatami6.png"),
	;
	
	private final int id;
	private final String texture;
	
	public final static Background[] backgrounds = Background.values();
	private static int latestID = -1;
	
	private static int getID() {
		return latestID++;
	}
	
	private Background(String texture) {
		id = getID();
		this.texture = texture;
	}
	
	/**
	 * _idを取得します。
	 * 
	 * @return 取得するid
	 */
	public int getid() {
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

	public static Background getBackground(int id) {
		for (Background background : backgrounds) {
			if (background.id == id) {
				return background;
			}
		}
		return null;
	}
	
}
