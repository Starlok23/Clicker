package es.iesfranciscodelosrios.A_Clicker;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import es.iesfranciscodelosrios.Model.username;
import es.iesfranciscodelosrios.ModelDAO.usernameDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class signUpController implements Initializable{
	
	@FXML
	private TextField txtNameSignUp, txtUsernameSignUp;
	
	@FXML
	private PasswordField txtPassword, txtRepeatPassword;
	
	@FXML
	private Button btnSignUp, btnCleanSignUp;
	
	private usernameDAO modelUser = new usernameDAO();
	
	/**
	 *  Limpia los TextFields en la pestaña de inicio de sesión
	 */
	public void cleanFields() {
		txtNameSignUp.setText("");
		txtUsernameSignUp.setText("");
		txtPassword.setText("");
		txtRepeatPassword.setText("");
	}
	
	/**
	 *  Metodo para comprobar que los campos de registro están rellenos para registrar un usuario
	 * @param e
	 */
	@FXML
	public void actionEvent(ActionEvent e) {
		
		Object evt = e.getSource();
		
		if(btnSignUp.equals(evt)) {
			if(!txtNameSignUp.getText().isEmpty() && !txtUsernameSignUp.getText().isEmpty()
					&& !txtPassword.getText().isEmpty() && !txtRepeatPassword.getText().isEmpty()) {
				if(txtPassword.getText().equals(txtRepeatPassword.getText())){
					username user = new username(txtNameSignUp.getText(), txtUsernameSignUp.getText(), txtPassword.getText());
					if(modelUser.insert(user)) {
						JOptionPane.showMessageDialog(null, "Usuario registrado con exito","OPERACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
						cleanFields();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}else if(btnCleanSignUp.equals(evt)){
			cleanFields();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
