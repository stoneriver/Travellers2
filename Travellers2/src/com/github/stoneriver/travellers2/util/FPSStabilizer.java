/*
 * Copyright [2016] [stoneriver]
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
 * クラスFPSStabilizerは,一定時間ごとに指定された処理を実行します.
 *
 * @author stoneriver
 */
public class FPSStabilizer {
	/**
	 * コンストラクタで指定されたFPS.
	 */
	private int fps;

	/**
	 * 処理を実装された関数型インターフェース.
	 */
	private Process target;

	/**
	 * 処理を実行するコード.
	 */
	private Runnable runnable = () -> {
		long error = 0;
		long idealSleep = (1000 << 16) / fps;
		long oldTime;
		long newTime = System.currentTimeMillis() << 16;
		while (true) {
			oldTime = newTime;
			target.process();
			newTime = System.currentTimeMillis() << 16;
			long sleepTime = idealSleep - (newTime - oldTime) - error;
			if (sleepTime < 0x20000)
				sleepTime = 0x20000;
			oldTime = newTime;
			try {
				Thread.sleep(sleepTime >> 16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			newTime = System.currentTimeMillis() << 16;
			error = newTime - oldTime - sleepTime;
		}
	};

	/**
	 * 処理を実行するスレッド.
	 */
	private Thread thread;

	/**
	 * コンストラクタで指定されたFPSで,指定された処理を開始します.
	 */
	public void start() {
		thread = new Thread(runnable);
		thread.start();
	}

	/**
	 * FPSStabilizerインスタンスを生成します.
	 *
	 * @param fps
	 *            FPS
	 * @param target
	 *            実行する処理
	 * @param startNow
	 *            trueの時,このコンストラクタが呼び出された時点で処理を開始します.<br>
	 *            つまり,次のようなコードの代わりに:<br>
	 *            {@code
	 *            FPSStabilizer fstab = new FPSStabilizer(60, () -> process(), false);
	 *            fstab.start();
	 *            }<br>
	 *            次のようなコードが書けます:<br>
	 *            {@code
	 *            new FPSStabilizer(60, () -> process(), true);
	 *            }
	 *
	 */
	public FPSStabilizer(int fps, Process target, boolean startNow) {
		this.fps = fps;
		this.target = target;

		if (startNow)
			start();
	}

	/**
	 * FPSStabilizerインスタンスを生成します.<br>
	 * FPSは60に設定されます.<br>
	 * また,コンストラクタは処理を開始しません.処理を開始するときは,{@link #start()}を手動で実行してください.
	 *
	 * @param target 実行する処理
	 */
	public FPSStabilizer(Process target) {
		this(60, target, false);
	}
}
