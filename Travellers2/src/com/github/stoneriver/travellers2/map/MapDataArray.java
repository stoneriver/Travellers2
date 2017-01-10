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
package com.github.stoneriver.travellers2.map;

import com.github.stoneriver.travellers2.block.Block;

/**
 * マップデータを保管するクラスです.
 *
 * @author stoneriver
 */
public class MapDataArray {
	/**
	 * マップデータの2次元配列です.(x, y)のデータはblockArray[y][x]に保管されていることに留意してください.
	 */
	private Block[][] blockArray;

	/**
	 * 指定された座標のBlockを返します.
	 *
	 * @param x x座標
	 * @param y y座標
	 * @return (x, y)のBlock
	 */
	public Block getArrayof(int x, int y) {
		return blockArray[y][x];
	}

	/**
	 * 指定された座標のBlockをセットします.
	 * @param x x座標
	 * @param y t座標
	 * @param newBlock 新しいBlock
	 */
	public void setArrayof(int x, int y, Block newBlock) {
		blockArray[y][x] = newBlock;
	}

	/**
	 * マップのサイズを指定して新しいMapDataArrayインスタンスを生成します.
	 * @param x x方向のサイズ
	 * @param y y方向のサイズ
	 */
	public MapDataArray(int x, int y) {
		blockArray = new Block[y][x];
	}

}
