/*Copyright 2016 stoneriver
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */package application;

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
			if (config.getProperty("event" + num + "Name").equals("null")) {
				eventName = "無音";
				start = 0;
			} else {
				eventName = config.getProperty("event" + num + "Name");
				start = Integer.parseInt(config.getProperty("event" + num + "Start"));
			}
			events[i] = new Event(i, eventName, start);
		}
	}
	
	/**
	 * eventsを取得します。
	 * 
	 * @return events
	 */
	public Event[] getEvents() {
		return events;
	}
	
	public Event getEvent(int num) {
		return events[num];
	}
	
	/**
	 * eventの個数を返します。
	 * @return eventの個数
	 */
	public int getEventCount() {
		return eventCount;
	}
	
}
