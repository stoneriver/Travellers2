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
package com.github.stoneriver.travellers2.mob;

import java.awt.Point;

import com.github.stoneriver.travellers2.util.HasImage;

/**
 * 生物を表現するクラスです.
 *
 *
 * @author stoneriver
 */
public abstract class Mob implements HasImage{

	/**
	 * Mobの座標です.
	 */
	private Point cor = new Point();

	/**
	 * Mobの座標を(x, y)に移動させます.
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		cor.setLocation(x, y);
	}

	/**
	 * Mobを移動させます.
	 * @param dx x方向の移動距離
	 * @param dy y方向の移動距離
	 */
	public void translate(int dx, int dy) {
		cor.translate(dx, dy);
	}

	/**
	 * MobのX座標を取得します.
	 * @return MobのX座標
	 */
	public int getX() {
		return cor.x;
	}

	/**
	 * MobのY座標を取得します.
	 * @return MobのY座標
	 */
	public int getY() {
		return cor.y;
	}

}
