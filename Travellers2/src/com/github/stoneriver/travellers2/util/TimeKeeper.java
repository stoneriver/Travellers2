/*
 * Copyright 2016-2017 stoneriver
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stoneriver.travellers2.util;

/**
 * クラスTimeKeeperは,処理にかかった時間などを計測します.
 *
 * @author stoneriver
 */
public class TimeKeeper {

	/**
	 * 最後にgetTime()が呼ばれた時刻
	 */
	private long lastCall;

	/**
	 * 最後にこのメソッドが呼ばれてからの経過時間をミリ秒で取得します.<br>
	 * ただし,この経過時間は次の場合に0に設定されます：<br>
	 * ・インスタンス生成時<br>
	 * ・{@link #getTime()}が呼ばれたとき<br>
	 * ・{@link #resetTime()}が呼ばれたとき<br>
	 * @return 最後にこのメソッドが呼ばれてからの経過時間
	 */
	public long getTime() {
		return lastCall -= System.currentTimeMillis();
	}

	/**
	 * 経過時間を0に設定します.
	 */
	public void resetTime() {
		lastCall = System.currentTimeMillis();
	}

	/**
	 * TimeKeeperインスタンスを生成します.
	 */
	public TimeKeeper() {
		lastCall = System.currentTimeMillis();
	}

}
