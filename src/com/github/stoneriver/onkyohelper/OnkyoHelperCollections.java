/*Copyright [yyyy] [name of copyright owner]
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package com.github.stoneriver.onkyohelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnkyoHelperCollections {
	
	public static String[] findAllPlans() {
		File dir = new File("."); //$NON-NLS-1$
		String[] files = dir.list();
		List<String> result = new ArrayList<>();
		int i;
		for (String s : files) {
			if (s.endsWith(".plan")) { //$NON-NLS-1$
				result.add(s);
			}
		}
		
		return (String[]) result.toArray(new String[0]);
	}
}
