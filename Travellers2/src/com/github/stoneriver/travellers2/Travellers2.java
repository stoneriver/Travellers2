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
package com.github.stoneriver.travellers2;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import com.github.stoneriver.travellers2.scene.Scene;
import com.github.stoneriver.travellers2.scene.SceneStage1;

/**
 * Travellers2メイン・クラスです.
 *
 * @author stoneriver
 */
public class Travellers2 extends JFrame {
	private Scene scene;

	/**
	 * シーンを変更します.
	 * @param newScene 新しいシーン
	 */
	public void setScene(Scene newScene) {
		scene = newScene;
		getContentPane().add(scene);
	}

	/**
	 * Travellers2コンストラクタです.<br>
	 * これ以降,このインスタンスを操作する必要はありません.つまり,次のコードが書けます:
	 * {@code new Travellers2();}
	 */
	public Travellers2() {

		//サイズ設定、固定-----------------------------------------------------
		getContentPane().setPreferredSize(new Dimension(640, 480));
		setResizable(false);
		pack();

		//色々設定-------------------------------------------------------------
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//初期GUIの設定--------------------------------------------------------
		try {
			setScene(new SceneStage1(this));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//表示-----------------------------------------------------------------
		setVisible(true);

	}

	/**
	 * Travellers2メイン関数です.
	 * @param args JVM引数
	 */
	public static void main(String[] args) {
		new Travellers2();
	}

}
