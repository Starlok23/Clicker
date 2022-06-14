package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.Model.structures;
import es.iesfranciscodelosrios.Model.username;
import es.iesfranciscodelosrios.ModelDAO.usernameDAO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableSetValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class gameController implements Initializable {

	@FXML
	private Label lbUsername;

	@FXML
	private Label lbLevel;

	@FXML
	public Label lbCounter;

	@FXML
	private StackPane containerForm;

	@FXML
	private Button btnImage;
	
	@FXML
	private Button btnReturn;
	
	@FXML
	private Button btnPurchase;

	private VBox structureViewForm;

	private usernameDAO aux = new usernameDAO();

	/**
	 *  Metodo para cargar un fxml en la mitad derecha de la vista principal
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private VBox loadForm(String url) throws IOException {
		return (VBox) FXMLLoader.load(getClass().getResource(url));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lbUsername.setText(DataService.user.getUsername());
		lbLevel.setText(String.valueOf(DataService.user.getLevel()));
		lbCounter.setText(String.valueOf(DataService.user.getCounter()));
		DataService.counter.set(DataService.user.getCounter());
		lbCounter.textProperty().bind(DataService.counter.asString());

		try {
			structureViewForm = loadForm("structureView.fxml");
			containerForm.getChildren().addAll(structureViewForm);
			structureViewForm.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Metodo para pulsar el botón central y que sume puntución
	 * @param e
	 * @throws IOException
	 */
	@FXML
	private void actionEvent(ActionEvent e) throws IOException {
		Object evt = e.getSource();
		if (evt.equals(btnImage)) {
			aux.counterUp(DataService.user.getUsername());
			DataService.counter.set(DataService.user.getCounter());
		}
	}
	
	/**
	 *  Metodo para comprar un nivel de una mejora
	 * @param e
	 */
	@FXML
	private void purchaseEvent(ActionEvent e) {
		Object evt = e.getSource();
		if(evt.equals(evt)) {
			aux.levelUp(DataService.user.getName());
			lbLevel.setText(String.valueOf(DataService.user.getLevel()));
		}
	}
	
	@FXML
	private void actionReturn(ActionEvent e) throws IOException {
		Object evt = e.getSource();
		if(evt.equals(btnReturn)) {
			App.setRoot("main");
		}
	}

}
