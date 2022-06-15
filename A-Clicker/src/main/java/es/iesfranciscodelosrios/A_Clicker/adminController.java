package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class adminController implements Initializable{

	@FXML
	private Button btnUser;
	
	@FXML
	private Button btnStructures;
	
	@FXML
	private Button btnReturn;
	
	@FXML
	private StackPane containerForm;
	
	private VBox adminUserForm, adminStructuresForm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			adminUserForm = loadForm("adminUser.fxml");
			adminStructuresForm = loadForm("adminStructures.fxml");
			containerForm.getChildren().addAll(adminUserForm, adminStructuresForm);
			adminUserForm.setVisible(true);
			adminStructuresForm.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Metodo para cargar un fxml en la mitad derecha de la vista principal
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private VBox loadForm(String url) throws IOException {
		return (VBox) FXMLLoader.load(getClass().getResource(url));
	}

	/**
	 *  Metodo para intercambiar la parte derecha de la vista dependiendo del botoón pulsado
	 * @param e
	 */
	@FXML
	private void actionEvent(ActionEvent e) {
		Object evt = e.getSource();

		if (evt.equals(btnUser)) {
			adminUserForm.setVisible(true);
			adminStructuresForm.setVisible(false);
		} else if (evt.equals(btnStructures)) {
			adminUserForm.setVisible(false);
			adminStructuresForm.setVisible(true);
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
