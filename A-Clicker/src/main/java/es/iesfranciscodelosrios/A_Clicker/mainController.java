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

public class mainController implements Initializable {

	@FXML
	private Button btnSignIn;

	@FXML
	private Button btnSignUp;

	@FXML
	private StackPane containerForm;

	private VBox signInForm, signUpForm;

	/**
	 *  Metodo para intercambiar la parte derecha de la vista dependiendo del botoón pulsado
	 * @param e
	 */
	@FXML
	private void actionEvent(ActionEvent e) {
		Object evt = e.getSource();

		if (evt.equals(btnSignIn)) {
			signInForm.setVisible(true);
			signUpForm.setVisible(false);
		} else if (evt.equals(btnSignUp)) {
			signInForm.setVisible(false);
			signUpForm.setVisible(true);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			signInForm = loadForm("signIn.fxml");
			signUpForm = loadForm("signUp.fxml");
			containerForm.getChildren().addAll(signInForm, signUpForm);
			signInForm.setVisible(true);
			signUpForm.setVisible(false);
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

}
