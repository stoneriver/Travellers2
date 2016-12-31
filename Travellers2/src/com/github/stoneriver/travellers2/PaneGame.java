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
package com.github.stoneriver.travellers2;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * ゲーム画面のクラスです.
 * インスタンスをSceneの子ノードにして使ってください.
 * @author Ayumu
 */
public class PaneGame extends Group {
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimationTimer timer;

	private Map map;

	/**
	 * 描画関数です。
	 */
	private void paint() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 2; y++) {
				Image img = ResourceManager.mng.getResouece(map.getCell(x, y));
				gc.drawImage(img, x * 32, y * 32);
			}
		}
	}

	public PaneGame()  {
		// 描画用カンバスの設定
		canvas = new javafx.scene.canvas.Canvas(640, 480);
		getChildren().add(canvas);

		// グラフィックス・コンテクストの取得
		gc = canvas.getGraphicsContext2D();

		// アニメーションタイマーの設定
		// 毎秒60回handle()を呼び出す
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				paint();
			}
		};
		timer.start();

		try {
			map = new Map();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
