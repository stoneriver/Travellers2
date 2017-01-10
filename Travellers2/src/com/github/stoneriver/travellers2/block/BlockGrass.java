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
package com.github.stoneriver.travellers2.block;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 草ブロックを表すクラスです.
 *
 * @author stoneriver
 */
public class BlockGrass extends Block {

	private static Image texture;

	/* (非 Javadoc)
	 * @see com.github.stoneriver.travellers2.block.Block#getTexture()
	 */
	@Override
	public Image getTexture() {
		return texture;
	}

	static {
		try {
			texture = ImageIO.read(BlockGrass.class.getResourceAsStream(BlockGrass.class.getSimpleName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
