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

import java.io.InputStream;

import javafx.scene.image.Image;;

/**
 * リソースの管理を行うクラスです. リソースは,getResource()メソッドが初めて呼び出された時点で生成されます.
 *
 * @author Ayumu
 */
public class ResourceManager {
	/**
	 * リソースです.
	 */
	private static Image[] resource;

	/**
	 * リソース・インスタンスです.
	 */
	public static ResourceManager mng = new ResourceManager();

	/**
	 * 1回だけ呼び出されます.
	 */
	private ResourceManager() {
		resource = new Image[0xFF];
	}

	/**
	 * IDがidであるリソースを返します. リソースが読み込まれていない場合は,読み込みます.
	 *
	 * @param id
	 *            リソースのID.
	 * @return リソース.
	 */
	public Image getResouece(int id) {
		if (resource[id] == null) {
			InputStream is = getClass().getResourceAsStream("resource/" + id + ".png");
			resource[id] = new Image(is);
		}
		return resource[id];
	}
}
