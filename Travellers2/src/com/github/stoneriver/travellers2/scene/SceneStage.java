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
package com.github.stoneriver.travellers2.scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.github.stoneriver.travellers2.Travellers2;
import com.github.stoneriver.travellers2.map.Map;
import com.github.stoneriver.travellers2.mob.Player;
import com.github.stoneriver.travellers2.util.FPSStabilizer;
import com.github.stoneriver.travellers2.util.TimeKeeper;

/**
 *
 *
 * @author stoneriver
 */
public class SceneStage extends Scene {

	private Map map;

	private Player player;

	private FPSStabilizer fstab;

	private TimeKeeper tker;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocusInWindow();

		//処理にかかった時間の取得
		long t = tker.getTime();

		//背景の描画
		g.drawImage(map.getimgBackground(), 0, 0, null, null);

		//マップの描画
		for (int x = 0; x < map.getMapX(); x++)
			for (int y = 0; y < map.getMapY(); y++)
				g.drawImage(map.getBlockAt(x, y).getImage(), 32 * x, 32 * y, null);

		//プレイヤーの描画
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
	}

	public SceneStage(Travellers2 parent) throws IOException {
		super(parent);
		map = new Map("Stage1.dat");
		player = new Player();
		fstab = new FPSStabilizer(() -> repaint());
		fstab.start();
		tker = new TimeKeeper();
	}

}
