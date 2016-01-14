package userinterface;

import java.io.File;
import java.io.IOException;

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
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GeneratorUI extends Application{
	private UIController controller;
	private String saveDirectory;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new UIController();
		saveDirectory = getApplicationPath();
		
		BorderPane root = new BorderPane();
		
		Label setLabel = new Label("current set:");
		ComboBox<String> set = new ComboBox<>();
		set.setMinWidth(80);
		try{
			set.getItems().addAll(controller.getAllSets());
		}catch(NullPointerException e){};
		
		ListView<RuleHolder> list = new ListView<RuleHolder>();
		if(set.getValue() != null){	
			list.setItems(getListViewItems(set.getValue()));
		}
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		set.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				list.setItems(getListViewItems(set.getValue()));
			}    
	    });
		
		CheckBox execute = new CheckBox("execute scripts");
		
		Button generateSelected = new Button("Generate selected");
		generateSelected.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				generateRules(primaryStage, list.getSelectionModel().getSelectedItems(), saveDirectory, execute.isSelected());
			}
		});
		Button generateAll = new Button("Generate all");
		generateAll.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				if(set.getValue() != null){
					ObservableList<RuleHolder> rules = getListViewItems(set.getValue());
					generateRules(primaryStage, rules, saveDirectory, execute.isSelected());
				}
			}
		});
		
		
		VBox rootBox = new VBox();
		
		HBox top = new HBox();
		top.setSpacing(10);
		
		VBox center = new VBox();
		center.setSpacing(10);
		
		HBox bottom = new HBox();
		bottom.setSpacing(20);
		bottom.setAlignment(Pos.TOP_RIGHT);
		
		// menu
		
		VBox topContainer = new VBox(); 
		MenuBar mainMenu = new MenuBar();  
		
		topContainer.getChildren().add(mainMenu);
		
		Menu file = new Menu("File");
		Menu settings = new Menu("Settings");
		
		MenuItem refresh = new MenuItem("Refresh");
		refresh.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				try{
					set.getItems().addAll(controller.getAllSets());
				}catch(NullPointerException e){};
				list.setItems(getListViewItems(set.getValue()));
			}
			
		});
		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		file.getItems().addAll(refresh, exitApp);
		
		MenuItem targetSettings = new MenuItem("Target database");
		targetSettings.setDisable(true);
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
		settings.getItems().addAll(targetSettings, scriptDir);
		
		mainMenu.getMenus().addAll(file, settings);
		
		// adding objects
		
		top.getChildren().addAll(setLabel, set);
		center.getChildren().addAll(list, execute);
		bottom.getChildren().addAll(generateSelected, generateAll);
		
		rootBox.getChildren().addAll(top, center, bottom);
		rootBox.setPadding(new Insets(50, 50, 50, 50));
		rootBox.setSpacing(10);
		
		root.setTop(topContainer);
		root.setCenter(rootBox);
		
		
		// creating scene
		
		Scene scene = new Scene(root, 800, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Businessrule Generator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private ObservableList<RuleHolder> getListViewItems(String setValue){
		return controller.getRuleCodesAndNames(setValue);
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
	
	private void generateRules(Stage primaryStage, ObservableList<RuleHolder> rules, String saveDir, boolean execute){
		ObservableList<String> codes = FXCollections.observableArrayList();
		if(!rules.isEmpty()){
			for(RuleHolder holder : rules){
				codes.add(holder.getCode());
			}
			controller.generateBusinessRules(codes, saveDir, execute);
			MessageDialog succesDialog = new MessageDialog(primaryStage, "Rules are generated");
			succesDialog.show();
		}else{
			MessageDialog emptyDialog = new MessageDialog(primaryStage, "No rules are selected");
			emptyDialog.show();
		}
	}

}
