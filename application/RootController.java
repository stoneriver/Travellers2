/*Copyright 2016 stoneriver
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
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RootController implements Initializable {
	
	//変数宣言
	//コントローラ使用分変数
	private int currentEventNum;
	private Plan plan;
	
	//GUI使用分変数
	//イベントリスト
	private ObservableList<Event> obsevableEventList;
	@FXML
	private TableView<Event> tableEventList;
	@FXML
	private TableColumn<Event, Integer> columnEventNum;
	@FXML
	private TableColumn<Event, String> columnEventName;
	//直近イベントリスト
	private ObservableList<Event> obsevableNearbyEventList;
	@FXML
	private TableView<Event> tableNearbyEventList;
	@FXML
	private TableColumn<Event, Integer> columnNearbyEventNum;
	@FXML
	private TableColumn<Event, String> columnNearbyEventStatus;
	@FXML
	private TableColumn<Event, String> columnNearbyEventName;
	@FXML
	private TableColumn<Event, Integer> columnNearbyEventStart;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	//ボリュームコントロール
	@FXML
	private Button buttonClimax;
	@FXML
	private Button buttonNormal;
	@FXML
	private Button buttonSilent;
	//イベント進行
	@FXML
	private Button buttonBack;
	@FXML
	private Button buttonForward;
	//メニュー
	@FXML
	private Menu menuOpen;
	private MenuItem menuUpdate;
	
	//関数定義
	//クラス定義
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDisableAll(true);
		initializeNearbyEventList();
		initializeEventList();
		menuUpdate = new MenuItem("Update");
		menuUpdate.setOnAction((ActionEvent e) -> {
			updateAvailablePlans();
		});
		updateAvailablePlans();
	}
	
	private String[] findAllPlans() {
		File dir = new File(".");
		String[] files = dir.list();
		List<String> result = new ArrayList<>();
		System.out.println("p");
		int i;
		for (String s : files) {
			if (s.endsWith(".properties")) {
				result.add(s);
			}
		}
		
		return (String[]) result.toArray(new String[0]);
	}
	
	private void addPlansToMenu(String[] plans) {
		for (String s : plans) {
			MenuItem item = new MenuItem(s);
			item.setOnAction((ActionEvent e) -> {
				loadPlan(s);
			});
			menuOpen.getItems().add(item);
		}
	}
	
	private void loadPlan(String property) {
		try {
			plan = new Plan(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDisableAll(false);
		updateEventList();
		setCurrentEvent(0);
	}
	
	private void updateAvailablePlans() {
		String[] plans = findAllPlans();
		menuOpen.getItems().clear();
		menuOpen.getItems().add(menuUpdate);
		addPlansToMenu(plans);
	}
	
	//コントローラ関数
	private void setCurrentEvent(int num) {
		
		plan.getEvent(currentEventNum).stop();
		
		currentEventNum = num;
		//ボタンの有効無効
		if (currentEventNum <= 0) {
			buttonBack.setDisable(true);
		} else {
			buttonBack.setDisable(false);
		}
		if (currentEventNum >= plan.getEventCount() - 1) {
			buttonForward.setDisable(true);
		} else {
			buttonForward.setDisable(false);
		}
		
		plan.getEvent(currentEventNum).play();
		updateNearbyEventList();
	}
	
	//GUI関数
	private void setDisableAll(boolean b) {
		for (Control c : getAllControl()) {
			c.setDisable(b);
		}
	}
	
	private Control[] getAllControl() {
		Control[] result = {
				buttonClimax,
				buttonNormal,
				buttonSilent,
				buttonBack,
				buttonForward,
				tableNearbyEventList,
				tableEventList
		};
		return result;
	}
	
	private void initializeNearbyEventList() {
		obsevableNearbyEventList = FXCollections.observableArrayList();
		columnNearbyEventNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		columnNearbyEventStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnNearbyEventName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnNearbyEventStart.setCellValueFactory(new PropertyValueFactory<>("start"));
		tableNearbyEventList.setItems(obsevableNearbyEventList);
	}
	
	private void initializeEventList() {
		obsevableEventList = FXCollections.observableArrayList();
		columnEventNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		columnEventName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableEventList.setItems(obsevableEventList);
	}
	
	private void updateNearbyEventList() {
		obsevableNearbyEventList.clear();
		int fix;
		if (currentEventNum == 0) {
			fix = 0;
		} else if (currentEventNum == plan.getEventCount() - 2) {
			fix = -2;
		} else if (currentEventNum == plan.getEventCount() - 1) {
			fix = -3;
		} else {
			fix = -1;
		}
		for (int i = fix + currentEventNum; i < fix + currentEventNum + 4; i++) {
			obsevableNearbyEventList.add(plan.getEvent(i));
		}
		
	}
	
	private void updateEventList() {
		for (Event e : plan.getEvents()) {
			obsevableEventList.add(e);
		}
	}
	
	//イベント関数
	@FXML
	private void onButtonClimaxAction() {
		plan.getEvent(currentEventNum).setPreferredVolume(1.0);
	}
	
	@FXML
	private void onButtonNormalAction() {
		plan.getEvent(currentEventNum).setPreferredVolume(0.7);
	}
	
	@FXML
	private void onButtonSilentAction() {
		plan.getEvent(currentEventNum).setPreferredVolume(0.0);
	}
	
	@FXML
	private void onButtonBackAction() {
		setCurrentEvent(currentEventNum - 1);
	}
	
	@FXML
	private void onButtonForwardAction() {
		setCurrentEvent(currentEventNum + 1);
	}
	
	@FXML
	private void onMenuUpdateAction() {
		updateAvailablePlans();
	}
	
}
