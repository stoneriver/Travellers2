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

/**
 *
 * @author Ayumu
 */
public class Map {
	/**
	 * マップ・データです. 座標(x, y)のマップidはdata[y][x]に格納されていることに留意してください.
	 */
	private char[][] data = { { 'A', 'A', 'A' }, { 'D', 'C', 'B' }, };

	/**
	 * 座標(x, y)のマップidを取得します。
	 *
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @return マップid
	 */
	public char getCell(int x, int y) {
		return data[y][x];
	}

	/**
	 * マップファイルsourceからマップデータを読み込み、Mapインスタンスを生成します.
	 *
	 * @param source
	 *            マップファイル
	 */
	public Map(String source) {

	}

	/**
	 * デバッグ用.
	 */
	public Map() {
	}
}
