package teamkakkokari.travellers.src.item;

public enum Item {
	;
	
	public final int _id;
	public final String _texture;
	public final ItemBase _base;
	
	private static int latestID = -1;
	private static int getID() {
		return latestID++;
	}
	
	private Item(ItemBase base, String texture) {
		_id = getID();
		this._base = base;
		this._texture = texture;
	}
	

}
