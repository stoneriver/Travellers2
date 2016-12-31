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
import java.util.ArrayList;

/**
 *
 * @author Ayumu
 */
public class Map {
	/**
	 * マップ・データです. 座標(x, y)のマップidはdata[y][x]に格納されていることに留意してください.
	 */
	private int[][] data;

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
		return data[y][x];
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

		ArrayList<int[]> tmp = new ArrayList<int[]>(0);
		String str;
		while ((str = rd.readLine()) != null) {
			String[] cellstr = str.split(" ");
			int[] cellint = new int[cellstr.length];
			for (int j = 0; j < cellstr.length; j++) {
				cellint[j] = Integer.decode("0x" + cellstr[j]);
			}
			tmp.add(cellint);
		}
		data = tmp.toArray(new int[tmp.size()][]);
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
