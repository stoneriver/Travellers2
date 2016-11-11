package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Event {
	
	private final int num;
	private final String eventName;
	private final int start;
	private final MediaPlayer player;
	
	/**
	 * numを取得します。
	 * 
	 * @return num
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * eventNameを取得します。
	 * 無音イベントの場合は、文字列"null"を返します。
	 * 
	 * @return file
	 */
	public String getEventName() {
		return eventName;
	}
	
	/**
	 * startを取得します。
	 * 無音イベントの場合は、0を返します。
	 * 
	 * @return start
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * playerを取得します。
	 * 
	 * @return player
	 */
	public MediaPlayer getPlayer() {
		return player;
	}

	public Event(int number, String eventName, int start, String mediaSource) throws MalformedURLException {
		this.num = number;
		if (!eventName.startsWith("無音")) {
			this.eventName = eventName;
			this.start = start;
			File file = new File(mediaSource);
			Media media = new Media(file.toURL().toString());
			this.player = new MediaPlayer(media);
		} else {
			this.eventName = null;
			this.start = 0;
			this.player = null;
		}
	}
	
}
