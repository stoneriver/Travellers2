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
package com.github.stoneriver.travellers2.scene;

import javax.swing.JPanel;

import com.github.stoneriver.travellers2.Travellers2;

/**
 * 各Sceneの親となるクラスです.
 *
 * @author stoneriver
 */
public abstract class Scene extends JPanel {
	protected Travellers2 parent;

	public Scene(Travellers2 parent) {
		this.parent = parent;
	}

}
