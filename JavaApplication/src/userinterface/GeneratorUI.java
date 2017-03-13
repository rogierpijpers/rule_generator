package userinterface;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import domain.controller.UIControllerBuilder;
import util.RuleHolder;
import domain.controller.UIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

// SHOW BUSINESSRULE CODE & NAME NYI !!! --------------------------------------------------

public class GeneratorUI extends Application{
	private UIController controller;
	private String saveDirectory;

	private void initializeControllers(){
		controller = new UIControllerBuilder().createUIController();
		saveDirectory = getApplicationPath();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initializeControllers();
		
		BorderPane root = new BorderPane();
		Label setLabel = new Label("current set:");
		ComboBox<String> set = getSetComboBox();
		ListView<String> list = getStringListView(set);
		setSetChangeListeners(set, list);
		CheckBox execute = new CheckBox("execute scripts");
		Button generateSelected = getGenerateSelectedButton(primaryStage, list, execute);
		Button generateAll = getGenerateAllButton(primaryStage, set, execute);
		VBox rootBox = new VBox();

		HBox top = getNewHBox(10);
		VBox center = getNewVBox();
		HBox bottom = getNewHBox(20);
		bottom.setAlignment(Pos.TOP_RIGHT);

		VBox topContainer = new VBox();
		createMenu(primaryStage, set, list, topContainer);
		addObjectsToView(root, setLabel, set, list, execute, generateSelected, generateAll, rootBox, top, center, bottom, topContainer);
		createSceneAndShow(primaryStage, root);
	}

	private VBox getNewVBox() {
		VBox center = new VBox();
		center.setSpacing(10);
		return center;
	}

	private HBox getNewHBox(int value) {
		HBox top = new HBox();
		top.setSpacing(value);
		return top;
	}

	private void addObjectsToView(BorderPane root, Label setLabel, ComboBox<String> set, ListView<String> list, CheckBox execute, Button generateSelected, Button generateAll, VBox rootBox, HBox top, VBox center, HBox bottom, VBox topContainer) {
		top.getChildren().addAll(setLabel, set);
		center.getChildren().addAll(list, execute);
		bottom.getChildren().addAll(generateSelected, generateAll);

		rootBox.getChildren().addAll(top, center, bottom);
		rootBox.setPadding(new Insets(50, 50, 50, 50));
		rootBox.setSpacing(10);

		root.setTop(topContainer);
		root.setCenter(rootBox);
	}

	private void createSceneAndShow(Stage primaryStage, BorderPane root) {
		Scene scene = new Scene(root, 800, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Businessrule Generator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setSetChangeListeners(final ComboBox<String> set, final ListView<String> list) {
		set.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				list.setItems(getListViewItems(set.getValue()));
			}
	    });
	}

	private ListView<String> getStringListView(ComboBox<String> set) {
		ListView<String> list = new ListView<String>();
		if(set.getValue() != null){
			list.setItems(getListViewItems(set.getValue()));
		}
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return list;
	}

	private ComboBox<String> getSetComboBox() {
		ComboBox<String> set = new ComboBox<>();
		set.setMinWidth(80);
		try{
			set.getItems().addAll(controller.getAllSets());
		}catch(NullPointerException e){}
		;
		return set;
	}

	private void createMenu(final Stage primaryStage, final ComboBox<String> set, final ListView<String> list, VBox topContainer) {
		MenuBar mainMenu = new MenuBar();

		topContainer.getChildren().add(mainMenu);

		Menu file = new Menu("File");
		Menu settings = new Menu("Settings");

		MenuItem refresh = getRefreshMenuItem(set, list);
		MenuItem errorLog = getErrorLogMenuItem(primaryStage);
		MenuItem exitApp = getExitAppMenuItem();
		file.getItems().addAll(refresh, errorLog, exitApp);

		MenuItem targetSettings = new MenuItem("Target database");
		targetSettings.setDisable(true);
		MenuItem scriptDir = getScriptDirMenuItem(primaryStage);
		settings.getItems().addAll(targetSettings, scriptDir);

		mainMenu.getMenus().addAll(file, settings);
	}

	private MenuItem getExitAppMenuItem() {
		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		return exitApp;
	}

	private MenuItem getScriptDirMenuItem(final Stage primaryStage) {
		MenuItem scriptDir = new MenuItem("Script directory");
		scriptDir.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	DirectoryChooser chooser = new DirectoryChooser();
				chooser.setTitle("Script directory");
				File defaultDirectory = new File(getApplicationPath());
				chooser.setInitialDirectory(defaultDirectory);
				File selectedDirectory = chooser.showDialog(primaryStage);
				try {
					saveDirectory = selectedDirectory.getCanonicalPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NullPointerException e){
					// exception is thrown when no directory is selected
				}
		    }
		});
		return scriptDir;
	}

	private MenuItem getErrorLogMenuItem(final Stage primaryStage) {
		MenuItem errorLog = new MenuItem("Open errorlog");
		errorLog.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				File f = new File("errorLog.txt");
				if(f.exists() && !f.isDirectory()) {
					if (Desktop.isDesktopSupported()) {
					    	try {
								Desktop.getDesktop().edit(f);
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
				}else{
					MessageDialog emptyDialog = new MessageDialog(primaryStage, "Errorlog doesn't exist yet...");
					emptyDialog.show();
				}
			}
		});
		return errorLog;
	}

	private MenuItem getRefreshMenuItem(final ComboBox<String> set, final ListView<String> list) {
		MenuItem refresh = new MenuItem("Refresh");
		refresh.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				try{
					set.getItems().clear();
					set.getItems().addAll(controller.getAllSets());
				}catch(NullPointerException e){};
				list.setItems(getListViewItems(set.getValue()));
			}

		});
		return refresh;
	}

	private Button getGenerateSelectedButton(final Stage primaryStage, final ListView<String> list, final CheckBox execute) {
		Button generateSelected = new Button("Generate selected");
		generateSelected.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				generateRules(primaryStage, list.getSelectionModel().getSelectedItems(), saveDirectory, execute.isSelected());
			}
		});
		return generateSelected;
	}

	private Button getGenerateAllButton(final Stage primaryStage, final ComboBox<String> set, final CheckBox execute) {
		Button generateAll = new Button("Generate all");
		generateAll.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				if(set.getValue() != null){
					ObservableList<String> rules = getListViewItems(set.getValue());
					generateRules(primaryStage, rules, saveDirectory, execute.isSelected());
				}
			}
		});
		return generateAll;
	}

	private ObservableList<String> getListViewItems(String setValue){
		ObservableList <String> ruleCodes = FXCollections.observableArrayList();
		ObservableList<RuleHolder> holders = controller.getRuleCodesAndNames(setValue);
		for(RuleHolder holder : holders){
			ruleCodes.add(holder.getCode());
		}
		return ruleCodes;
	}
	
	private String getApplicationPath(){
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	private void generateRules(Stage primaryStage, ObservableList<String> rules, String saveDir, boolean execute){
		// ObservableList<String> codes = FXCollections.observableArrayList();
		if(!rules.isEmpty()){
			/*
			for(RuleHolder holder : rules){
				codes.add(holder.getCode());
			}
			*/
			controller.generateBusinessRules(rules, saveDir, execute);
			MessageDialog succesDialog = new MessageDialog(primaryStage, "Rules are generated. \n\nTo see if possible errors might have occured, \nsee the errorlog (file-> Open errorlog)");
			succesDialog.show();
		}else{
			MessageDialog emptyDialog = new MessageDialog(primaryStage, "No rules are selected");
			emptyDialog.show();
		}
	}

}
