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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * マップを表現するクラスです.<br>
 * マップは次の要素で構成されます;<br>
 * ・マップ・セル.マップの描画を決定します.<br>
 * <br>
 * マップのエンコードは以下の通りです.<br>
 * 1行目;2整数X, Yが空白区切りで与えられます.<br>
 * XはマップのX軸方向の要素数を表します. YはマップのY軸方向の要素数を表します.<br>
 * i行目（2 ≤ i ≤ Y + 1）;16進数で表現された整数がX個,空白区切りで与えられます.<br>
 * i行目,j列目の数は座標(j, i)のマップデータのIDです.
 *
 * @author stoneriver
 */
public class Map {
	/**
	 * マップ・セルです.<br>
	 * 座標(x, y)のマップ・セルIDはdata[y][x]に格納されていることに留意してください.
	 */
	private int[][] cells;

	/**
	 * X方向のセル数です.
	 */
	private int X;

	/**
	 * Y方向のセル数です.
	 */
	private int Y;

	/**
	 * 座標(x, y)のマップidを取得します。
	 *
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @return マップid
	 */
	public int getCell(int x, int y) {
		return cells[y][x];
	}

	/**
	 * X方向のセル数を取得します.
	 *
	 * @return X方向のセル数
	 */
	public int getX() {
		return X;
	}

	/**
	 * Y方向のセル数を取得します.
	 *
	 * @return Y方向のセル数
	 */
	public int getY() {
		return Y;
	}

	/**
	 * マップファイルsourceからマップデータを読み込み、Mapインスタンスを生成します.
	 *
	 * @param source
	 *            マップファイル
	 * @throws IOException
	 */
	public Map(String source) throws IOException {
		InputStream in = getClass().getResourceAsStream(source);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader rd = new BufferedReader(inr);

		String str;

		// X, Yの読み込み
		str = rd.readLine();
		String[] xny = str.split(" ");
		X = Integer.valueOf(xny[0]);
		Y = Integer.valueOf(xny[1]);

		// マップセルの読み込み
		cells = new int[Y][X];
		for (int y = 0; y < Y; y++) {
			str = rd.readLine();
			String[] row = str.split(" ");
			for (int x = 0; x < X; x++) {
				cells[y][x] = Integer.decode("0x" + row[x]);
			}
		}
	}

	/**
	 * デバッグ用.
	 *
	 * @throws IOException
	 */
	public Map() throws IOException {
		this("map1.dat");
	}
}
