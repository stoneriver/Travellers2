package teamkakkokari.travellers1.src.map;

import teamkakkokari.travellers.src.background.Background;
import teamkakkokari.travellers.src.structure.Structure;

public abstract class Map {
	
	public Map(int defaultX, int defaultY, int defaultFacing) {
		_defaultX = defaultX;
		_defaultY = defaultY;
		_defaultFacing = defaultFacing;
	}
	
	public final int _defaultX;
	public final int _defaultY;
	public final int _defaultFacing;
	public Background[][] _background;
	public Structure[][] _structure;
	
	public void toBackgrounds(int[][] map) {
		_background = new Background[map.length][];
		for (int i = 0; i < _background.length; i++) {
			_background[i] = new Background[map[i].length];
		}
		
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				_background[y][x] = Background.getBackground(map[y][x]);
			}
		}
	}
	
	public void toStructure(int[][] map) {
		_structure = new Structure[map.length][];
		for (int i = 0; i < _structure.length; i++) {
			_structure[i] = new Structure[map[i].length];
		}
		
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				_structure[y][x] = Structure.getStructure(map[y][x]);
			}
		}
	}

}