package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Plan {
	
	private final Properties config = new Properties();
	private final int eventCount;
	private final Event[] events;
	
	public Plan(String property) throws IOException {
		
		//propertiesファイルをロード
		InputStream inputStream = new FileInputStream(new File(property));
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "Shift-JIS");
		config.load(inputStreamReader);
		
		//イベントの個数を読み込み
		eventCount = Integer.parseInt(config.getProperty("eventCount"));
		events = new Event[eventCount];
		
		//イベントを読み込み
		for (int i = 0; i < eventCount; i++) {
			String num = String.valueOf(i);
			String eventName;
			int start;
			int end;
			int aPoint;
			int bPoint;
			String timing;
			int volume;
			if (config.getProperty("event" + num + "Name").equals("null")) {
				eventName = "無音";
				start = 0;
				end = 0;
				aPoint = 0;
				bPoint = 0;
				volume = 0;
			} else {
				eventName = config.getProperty("event" + num + "Name");
				start = Integer.parseInt(config.getProperty("event" + num + "Start"));
				end = Integer.parseInt(config.getProperty("event" + num + "End"));
				aPoint = Integer.parseInt(config.getProperty("event" + num + "APoint"));
				bPoint = Integer.parseInt(config.getProperty("event" + num + "BPoint"));
				volume = Integer.parseInt(config.getProperty("event" + num + "Volume"));
			}
			timing = config.getProperty("event" + num + "Timing");
			events[i] = new Event(i, eventName, start, end, aPoint, bPoint, timing, volume);
		}
	}
	
	/**
	 * eventを取得します。
	 * 
	 * @return event
	 */
	public Event getEvent(int eventNum) {
		return events[eventNum];
	}
	
	/**
	 * eventsを取得します。
	 * 
	 * @return events
	 */
	public Event[] getEvents() {
		return events;
	}
	
	/**
	 * eventの個数を返します。
	 * @return eventの個数
	 */
	public int getEventCount() {
		return eventCount;
	}
	
}
