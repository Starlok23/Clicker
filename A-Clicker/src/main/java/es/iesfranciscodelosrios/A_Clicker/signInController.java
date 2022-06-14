package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import es.iesfranciscodelosrios.Model.username;
import es.iesfranciscodelosrios.ModelDAO.structuresDAO;
import es.iesfranciscodelosrios.ModelDAO.usernameDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class signInController implements Initializable {

	@FXML
	private TextField txtUserSignIn;

	@FXML
	private PasswordField txtPasswordSignIn;

	@FXML
	private TextField txtPasswordSignInMask;

	@FXML
	private CheckBox checkViewPassSignIn;

	@FXML
	private Button btnSignIn;

	@FXML
	private Button btnClean;

	private usernameDAO modelUser = new usernameDAO();
	private gameController aux = new gameController();
	private structuresDAO structure = new structuresDAO();

	/**
	 *  Limpia los TextFields en la pestaña de inicio de sesión
	 */
	public void cleanFields() {
		txtUserSignIn.setText("");
		txtPasswordSignIn.setText("");
		txtPasswordSignInMask.setText("");
	}

	/**
	 *  Metodo asignado a botón para iniciar sesión
	 * @param e
	 * @throws IOException
	 */
	@FXML
	public void actionEvent(ActionEvent e) throws IOException {
		Object evt = e.getSource();

		if (btnSignIn.equals(evt)) {

			if (!txtUserSignIn.getText().isEmpty() && !txtPasswordSignIn.getText().isEmpty()) {
				if (txtUserSignIn.getText().equals("admin") && txtPasswordSignIn.getText().equals("admin")
						|| txtUserSignIn.getText().equals("Admin") && txtPasswordSignIn.getText().equals("Admin")) {
					App.setRoot("admin");
				} else {
					int state = modelUser.login(txtUserSignIn.getText(), txtPasswordSignIn.getText());
					if (state != -1) {
						if (state == 1) {
							modelUser.getInfo(txtUserSignIn.getText());
							//u.user()
							App.setRoot("game");
						} else if (state == 0) {
							JOptionPane.showMessageDialog(null, "Usuario no Registrado", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (btnClean.equals(evt)) {
			cleanFields();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		maskPassword(txtPasswordSignIn, txtPasswordSignInMask, checkViewPassSignIn);

	}

	/**
	 *  Metodo para mostrar contraseña
	 * @param pass
	 * @param text
	 * @param check
	 */
	public void maskPassword(PasswordField pass, TextField text, CheckBox check) {

		text.setVisible(false);
		text.setManaged(false);

		text.managedProperty().bind(check.selectedProperty());
		text.visibleProperty().bind(check.selectedProperty());

		text.textProperty().bindBidirectional(pass.textProperty());

	}

}
