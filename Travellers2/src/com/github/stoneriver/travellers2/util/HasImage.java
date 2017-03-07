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
package com.github.stoneriver.travellers2.util;

import java.awt.Image;

/**
 * 画像を持つクラスです.
 * 画像は,GUIに表示するときに使われます.
 *
 * @author stoneriver
 */
public interface HasImage {

	/**
	 * 画像を取得します.
	 * サブクラスでこのメソッドを実装するときには,メモリー節約のため,静的フィールドを返すようにしてください.
	 *
	 * @return Image
	 */
	Image getImage();

}
