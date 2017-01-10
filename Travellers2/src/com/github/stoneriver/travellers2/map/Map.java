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
package com.github.stoneriver.travellers2.map;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.github.stoneriver.travellers2.block.Block;
import com.github.stoneriver.travellers2.image.ImageLoader;

/**
 * マップの読み込み,データの保持を行います.<br>
 * マップは,.datファイルから読み込まれます.次に,マップデータを格納するファイルのテンプレートを示します.<br>
 * <br>
 * background.png //背景の定義<br>
 * blockCnt mapX mapY //マップ情報の定義<br>
 * a com.github.stoneriver.travellers2.block.BlockGrass //ブロックの定義<br>
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
	 * マップデータで使うchar,ブロックです.<br>
	 * 例えば、マップデータに次の記述があった場合:<br>
	 * a com.github.stoneriver.travellers2.block.BlockGrass<br>
	 * コード内で,次のデータは:<br>
	 * {@code blocks.get('a');}
	 * 次に等しくなります:<br>
	 * {@code Class.forname("com.github.stoneriver.travellers2.block.BlockGrass")}
	 *
	 */
	private HashMap<Character, Class<Block>> blocks = new HashMap<>();

	/**
	 * マップデータです.
	 */
	private MapDataArray array;

	/**
	 * マップのデータを読み込みます.<br>
	 * 通常,このメソッドはインスタンス生成時に一度呼び出され,それ以降呼び出されることはありません.
	 */
	@SuppressWarnings("unchecked")
	private void loadData() {

		//背景,マップ情報の読み込み
		backgroundSource = sc.next();
		blockCnt = sc.nextInt();
		mapX = sc.nextInt();
		mapY = sc.nextInt();

		//ブロックの定義
		for (int i = 0; i < blockCnt; i++) {
			char c = sc.next().charAt(0);
			String s = sc.next();
			try {
				blocks.put(c, (Class<Block>) Class.forName(s));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("マップの読み込みでエラーが発生しました.次のクラスが見つかりません:" + s);
			}
		}

		array = new MapDataArray(mapX, mapY);
		//マップデータの読み込み
		for (int y = 0; y < mapY; y++) {
			String s = sc.next();
			char[] cs = s.toCharArray();
			for (int x = 0; x < mapX; x++)
				try {
					array.setArrayof(x, y, blocks.get(cs[x]).newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * loadDaa()で読み込まれたデータをもとに,イメージを読み込みます.
	 *
	 * @throws IOException
	 *             {@link ImageIO#read(File)}でIOExceptionがスローされた場合
	 */
	public void loadImage() throws IOException {
		imgBackground = ImageLoader.loadImage(backgroundSource);
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

	public Block getBlockAt(int x, int y) {
		return array.getArrayof(x, y);
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
	 *            マップファイル(.dat形式)の名前
	 * @throws IOException
	 *             {@link #loadImage()}でIOExceptionがスローされた場合
	 */
	public Map(String source) throws IOException {
		this(source, true);
	}
}
