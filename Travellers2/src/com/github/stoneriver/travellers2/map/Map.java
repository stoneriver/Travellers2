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

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.github.stoneriver.travellers2.block.Block;

/**
 * マップの読み込み,データの保持を行います.<br>
 * マップは,.datファイルから読み込まれます.次に,マップデータを格納するファイルのテンプレートを示します.<br>
 * <br>
 * background.png //背景の定義<br>
 * blockCnt mapX mapY //マップ情報の定義<br>
 * a BlockGrass //パーツの定義<br>
 * :<br>
 * :<br>
 * aaa //マップデータ<br>
 * aaa ....<br>
 * aaa<br>
 * :<br>
 * :<br>
 * <br>
 *
 * @author stoneriver
 */
public class Map {
	private Scanner sc;

	/**
	 * 背景のソースです.
	 */
	private String backgroundSource;

	/**
	 * 使用するブロックの種類の数です.
	 */
	private int blockCnt;

	/**
	 * マップのX方向の大きさです.
	 */
	private int mapX;

	/**
	 * マップのY方向の大きさです.
	 */
	private int mapY;

	/**
	 * 背景のイメージです.
	 */
	private Image imgBackground;

	/**
	 *
	 */
	// TODO loadXX()と合わせて,blockのテクスチャの使用法模索
	private HashMap<Character, Block> blocks = new HashMap<>();

	/**
	 * マップのデータを読み込みます.<br>
	 * 通常,このメソッドはインスタンス生成時に一度呼び出され,それ以降呼び出されることはありません.
	 */
	private void loadData() {
		backgroundSource = sc.next();
		blockCnt = sc.nextInt();
		mapX = sc.nextInt();
		mapY = sc.nextInt();
		for (int i = 0; i < blockCnt; i++) {
			// TODO
		}
	}

	/**
	 * loadDaa()で読み込まれたデータをもとに,イメージを読み込みます.
	 *
	 * @throws IOException
	 *             {@link ImageIO#read(String)}でIOExceptionがスローされた場合
	 */
	public void loadImage() throws IOException {
		imgBackground = ImageIO.read(new File(backgroundSource));
		// TODO ImageIO.read()参照のこと.
	}

	/**
	 * @return mapX
	 */
	public int getMapX() {
		return mapX;
	}

	/**
	 * @return mapY
	 */
	public int getMapY() {
		return mapY;
	}

	/**
	 * @return imgBackground
	 */
	public Image getimgBackground() {
		return imgBackground;
	}

	/**
	 * マップインスタンスを生成します.<br>
	 * デフォルトではマップファイルだけを読み込みます.引数で指定された場合,イメージの読み込みも行います.
	 *
	 * @param source
	 *            マップファイル(.dat形式)の名前
	 * @param loadImage
	 *            イメージの読み込みを行うか.trueならば行い,falseならば行いません.<br>
	 *            falseを指定した場合,ユーザーは,あとで{@link #loadImage()}を呼ぶ必要があります.
	 * @throws IOException
	 *             {@link #loadImage()}でIOExceptionがスローされた場合
	 */
	public Map(String source, boolean loadImage) throws IOException {
		InputStream in = getClass().getResourceAsStream(source);
		sc = new Scanner(in);
		loadData();
		if (loadImage)
			loadImage();
	}

	/**
	 * マップインスタンスを生成します.<br>
	 * このコンストラクタでは,インスタンス生成と同時にイメージの読み込みが行われます.
	 *
	 * @param source
	 * @throws IOException
	 *             {@link #loadImage()}でIOExceptionがスローされた場合
	 */
	public Map(String source) throws IOException {
		this(source, true);
	}
}
