package application;

public class Event {
	
	private final int number;
	private final String eventName;
	private final int start;
	private final int end;
	private final int aPoint;
	private final int bPoint;
	private final String timing;
	private final int volume;
	
	/**
	 * numberを取得します。
	 * 
	 * @return number
	 */
	public int getNumber() {
		return number;
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
	 * endを取得します。
	 * 無音イベントの場合は、0を返します。
	 * 
	 * @return end
	 */
	public int getEnd() {
		return end;
	}
	
	/**
	 * aPointを取得します。
	 * 無音イベントの場合は、0を返します。
	 * 
	 * @return aPoint
	 */
	public int getAPoint() {
		return aPoint;
	}
	
	/**
	 * bPointを取得します。
	 * 無音イベントの場合は、0を返します。
	 * 
	 * @return bPoint
	 */
	public int getBPoint() {
		return bPoint;
	}
	
	/**
	 * timingを取得します。
	 * 
	 * @return timing
	 */
	public String getTiming() {
		return timing;
	}
	
	/**
	 * volumeを取得します。
	 * 
	 * @return volume
	 */
	public int getVolume() {
		return volume;
	}
	
	public Event(int number, String eventName, int start, int end, int aPoint, int bPoint, String timing, int volume) {
		this.number = number;
		if (!eventName.startsWith("null")) {
			this.eventName = eventName;
		} else {
			this.eventName = null;
		}
		this.start = start;
		this.end = end;
		this.aPoint = aPoint;
		this.bPoint = bPoint;
		this.timing = timing;
		this.volume = volume;
	}
	
}
