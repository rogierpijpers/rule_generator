package userinterface;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageDialog extends Stage {

	public MessageDialog(Stage owner, String message) {
		super(StageStyle.UTILITY);
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		this.setResizable(false);
		this.setTitle("Message");

		BorderPane root = new BorderPane();
		VBox center = new VBox();
		center.setPadding(new Insets(20, 20, 10, 20));
		HBox bottom = new HBox();
		bottom.setPadding(new Insets(20, 20, 10, 20));

		Label messageLab = new Label(message);

		Button b = new Button("Ok");
		b.setOnAction(e -> close());

		center.getChildren().addAll(messageLab);
		bottom.getChildren().addAll(b);

		root.setCenter(center);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 350, 150);
		this.setScene(scene);
	}

}
