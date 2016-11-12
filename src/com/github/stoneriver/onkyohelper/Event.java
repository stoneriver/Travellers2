package com.github.stoneriver.onkyohelper;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Event {
	
	private final int num;
	private final String name;
	private final int start;
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
	 * 無音イベントの場合は、文字列"null"を返します。
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
			return "➤";
		} else {
			return "";
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
					System.out.println("++");
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
					System.out.println("--");
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
	
	@SuppressWarnings("deprecation")
	public Event(int number, String eventName, int start) throws MalformedURLException {
		this.num = number;
		this.name = eventName;
		if (!eventName.startsWith("無音")) {
			this.start = start;
			File file = new File(eventName);
			Media media = new Media(file.toURL().toString());
			this.player = new MediaPlayer(media);
			player.setStartTime(Duration.millis(start));
			player.setVolume(0);
		} else {
			this.start = 0;
			this.player = null;
		}
	}
	
}
