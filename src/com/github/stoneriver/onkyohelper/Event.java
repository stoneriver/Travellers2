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
 */
package com.github.stoneriver.onkyohelper;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Event {
	
	private int num;
	private String name;
	private int start;
	private boolean isSelected;
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
	 * nameを取得します。
	 * 無音イベントの場合は、文字列"無音"を返します。
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
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
	
	public boolean isPlaying() {
		return isSelected;
	}
	
	/**
	 * ステータスを返します。
	 * 選択中の場合"➤"を、そうでない場合は""を返します。
	 * 
	 * @return 文字列でのステータス
	 */
	public String getStatus() {
		if (isSelected) {
			return "➤"; //$NON-NLS-1$
		} else {
			return ""; //$NON-NLS-1$
		}
	}
	
	public void play() {
		isSelected = true;
		if (player == null) {
			return;
		}
		player.play();
		setPreferredVolume(0.7);
	}
	
	public void pause() {
		isSelected = false;
		if (player == null) {
			return;
		}
		player.pause();
		player.setVolume(0);
	}
	
	public void stop() {
		pause();
		if (player == null) {
			return;
		}
		player.seek(Duration.ZERO);
	}
	
	private Thread volumeChanger;
	private boolean volumeChangingFlg;
	
	public void setPreferredVolume(double newVolume) {
		Runnable code;
		
		if (newVolume > player.getVolume()) {
			code = () -> {
				while (newVolume > player.getVolume()) {
					player.setVolume(player.getVolume() + 0.01);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!volumeChangingFlg) {
						break;
					}
					System.out.println("++"); //$NON-NLS-1$
				}
				volumeChangingFlg = false;
			};
		} else if (newVolume < player.getVolume()) {
			code = () -> {
				while (newVolume < player.getVolume()) {
					player.setVolume(player.getVolume() - 0.01);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!volumeChangingFlg) {
						break;
					}
					System.out.println("--"); //$NON-NLS-1$
				}
				volumeChangingFlg = false;
			};
		} else {
			code = () -> {
				volumeChangingFlg = false;
			};
		}
		
		if (volumeChangingFlg) {
			volumeChangingFlg = false;
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		volumeChanger = new Thread(code);
		volumeChangingFlg = true;
		volumeChanger.start();
		
	}
	
	public Event(int number, String eventName, int start) {
		this(number, eventName, start, true);
	}
	
	@SuppressWarnings("deprecation")
	public Event(int number, String eventName, int start, boolean generateMediaPlayer) {
		this.num = number;
		this.name = eventName;
		if (!eventName.startsWith("無音")) { //$NON-NLS-1$
			this.start = start;
			File file = new File(eventName);
			if (!generateMediaPlayer) {
				this.player = null;
				return;
			}
			Media media = null;
			try {
				media = new Media(file.toURL().toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			this.player = new MediaPlayer(media);
			player.setStartTime(Duration.millis(start));
			player.setVolume(0);
		} else {
			this.start = 0;
			this.player = null;
		}
	}
	
}
