package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class RootController implements Initializable {
	
	private int currentEventNum;
	private Event currentEvent;
	private Plan plan;
	private MediaPlayer player = null;
	private Runnable playerFinalization = () -> {
	};
	
	@SuppressWarnings("deprecation")
	private void setCurrentEvent(int num) {
		
		currentEventNum = num;
		
		if (num < 0) {
			return;
		}
		
		currentEvent = plan.getEvent(currentEventNum);
		updateNearbyEventList();
		
		//ファイル読み込み
		String fileName = currentEvent.getEventName();
		File file = new File(fileName);
		Media media;
		playerFinalization.run();
		
		if (currentEvent.getEventName().equals("無音")) {
			player = null;
			playerFinalization = () -> {
			};
		} else if (currentEvent.getAPoint() == 0 && currentEvent.getBPoint() == 0) {
			//読み込み
			media = null;
			try {
				media = new Media(file.toURL().toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			player = new MediaPlayer(media);
			playerFinalization = () -> {
				player.stop();
			};
			
			//再生
			player.setStartTime(new Duration(currentEvent.getStart()));
			player.setStopTime(new Duration(currentEvent.getEnd()));
			player.play();
			player.setVolume(0);
			setPrefferedVolume(0.7);
		} else {
			
			//読み込み
			media = null;
			try {
				media = new Media(file.toURL().toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			player = new MediaPlayer(media);
			playerFinalization = () -> {
				player.stop();
			};
			
			//再生、各処理
			player.setStartTime(new Duration(currentEvent.getStart()));
			player.setStopTime(new Duration(currentEvent.getBPoint()));
			player.setCycleCount(MediaPlayer.INDEFINITE);
			player.play();
			player.setVolume(0);
			setPrefferedVolume(0.7);
			Runnable runnable = () -> {
				while (true) {
					if ((player.getCurrentTime().toMillis() - currentEvent.getBPoint()) < 2000) {
						if (checkContinueFlag.isSelected()) {
							player.setStopTime(new Duration(currentEvent.getBPoint()));
						} else {
							player.setStopTime(new Duration(currentEvent.getEnd()));
							player.setCycleCount(1);
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(runnable).start();
		}
		
	}
	
	/* 直近イベントリスト */
	private ObservableList<Event> observableNearbyEventList;
	@FXML
	private TableView<Event> tableNearbyEventList;
	@FXML
	private TableColumn<Event, Integer> columnNearbyEventNumber;
	@FXML
	private TableColumn<Event, String> columnNearbyEventName;
	@FXML
	private TableColumn<Event, Integer> columnStart;
	@FXML
	private TableColumn<Event, Integer> columnEnd;
	@FXML
	private TableColumn<Event, Integer> columnAPoint;
	@FXML
	private TableColumn<Event, Integer> columnBPoint;
	@FXML
	private TableColumn<Event, String> columnTiming;
	@FXML
	private TableColumn<Event, Integer> columnVolume;
	
	private void initNearbyEventList() {
		observableNearbyEventList = FXCollections.observableArrayList();
		columnNearbyEventNumber.setCellValueFactory(new PropertyValueFactory<Event, Integer>("number"));
		columnNearbyEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
		columnStart.setCellValueFactory(new PropertyValueFactory<Event, Integer>("start"));
		columnEnd.setCellValueFactory(new PropertyValueFactory<Event, Integer>("end"));
		columnAPoint.setCellValueFactory(new PropertyValueFactory<Event, Integer>("aPoint"));
		columnBPoint.setCellValueFactory(new PropertyValueFactory<Event, Integer>("bPoint"));
		columnTiming.setCellValueFactory(new PropertyValueFactory<Event, String>("timing"));
		columnVolume.setCellValueFactory(new PropertyValueFactory<Event, Integer>("volume"));
		tableNearbyEventList.setItems(observableNearbyEventList);
	}
	
	private void updateNearbyEventList() {
		observableNearbyEventList.clear();
		
		//追加処理
		int min = 0;
		int max = plan.getEventCount() - 1;
		for (int i = currentEventNum - 1; i <= currentEventNum + 2; i++) {
			if (i < min) {
				observableNearbyEventList.add(new Event(-1, "", 0, 0, 0, 0, "", 0));
				continue;
			} else if (i > max) {
				
			} else {
				observableNearbyEventList.add(plan.getEvent(i));
				
			}
		}
	}
	
	/* 音量調整 */
	@FXML
	private Button buttonClimax;
	@FXML
	private Button buttonNormal;
	@FXML
	private Button buttonSilent;
	@FXML
	private CheckBox checkContinueFlag;
	private boolean volumeChangingFlag;
	
	private void setPrefferedVolume(double value) {
		
		volumeChangingFlag = true;
		
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Runnable runnable;
		if (player.getVolume() < value) {
			runnable = () -> {
				volumeChangingFlag = false;
				while (player.getVolume() < value) {
					System.out.println("incre.");
					System.out.println(player.getVolume());
					double newVolume = player.getVolume() + 0.01;
					player.setVolume(newVolume);
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (volumeChangingFlag) {
						break;
					}
					
				}
			};
		} else if (player.getVolume() > value) {
			runnable = () -> {
				while (player.getVolume() > value) {
					volumeChangingFlag = false;
					System.out.println("decre.");
					System.out.println(player.getVolume());
					double newVolume = player.getVolume() - 0.01;
					player.setVolume(newVolume);
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (volumeChangingFlag) {
						break;
					}
					
				}
			};
		} else {
			runnable = () -> {
			};
		}
		new Thread(runnable).start();
	}
	
	@FXML
	private void onButtonNormalAction() {
		setPrefferedVolume(0.7);
	}
	
	@FXML
	private void onButtonSilentAction() {
		setPrefferedVolume(0);
	}
	
	@FXML
	private void onClimaxAction() {
		setPrefferedVolume(1.0);
	}
	
	/* 戻る、進む */
	@FXML
	private Button buttonBack;
	@FXML
	private Button buttonForward;
	
	@FXML
	private void back() {
		if (currentEventNum > 0) {
			setCurrentEvent(currentEventNum - 1);
		}
	}
	
	@FXML
	private void forward() {
		if (currentEventNum < plan.getEventCount() - 1) {
			setCurrentEvent(currentEventNum + 1);
		}
	}
	
	/* プランローダー */
	@FXML
	private TextField textFieldLoadPlan;
	@FXML
	private Button buttonLoadPlan;
	
	@FXML
	private void loadPlan() {
		try {
			plan = new Plan(textFieldLoadPlan.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateEventList();
		updateNearbyEventList();
		setCurrentEvent(-1);
	}
	
	/* イベントリスト */
	private ObservableList<Event> observableEventList;
	@FXML
	private TableView<Event> tableEventList;
	@FXML
	private TableColumn<Event, Integer> columnEventListNumber;
	@FXML
	private TableColumn<Event, Integer> columnEventListName;
	
	private void initEventList() {
		observableEventList = FXCollections.observableArrayList();
		tableEventList.setItems(observableEventList);
		columnEventListNumber.setCellValueFactory(new PropertyValueFactory<Event, Integer>("number"));
		columnEventListName.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventName"));
	}
	
	private void updateEventList() {
		observableEventList.clear();
		for (Event e : plan.getEvents()) {
			observableEventList.add(e);
		}
	}
	
	/* イニシャライザ */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initNearbyEventList();
		initEventList();
	}
	
}
