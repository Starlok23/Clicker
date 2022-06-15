package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import es.iesfranciscodelosrios.ModelDAO.structuresDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class structureAddController implements Initializable {

	@FXML
	private TextField addName;

	@FXML
	private TextField addDescription;

	@FXML
	private TextField addCost;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnReturn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/**
	 * Añade nueva mejora
	 * 
	 * @throws IOException
	 */
	@FXML
	public void add(ActionEvent e) throws IOException {
		Object evt = e.getSource();
		if (btnAdd.equals(evt)) {
			if (!addName.getText().isEmpty() && !addDescription.getText().isEmpty() && !addCost.getText().isEmpty()) {
				if (IsInteger(addCost.getText())) {
					structuresDAO st = new structuresDAO();
					st.insert(addName.getText(), addDescription.getText(), Integer.parseInt(addCost.getText()));
					App.setRoot("admin");
				}else {
					JOptionPane.showMessageDialog(null, "Debe introducir una cantidad valida", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los parametros", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	@FXML
	private void actionReturn(ActionEvent e) throws IOException {
		Object evt = e.getSource();
		if (evt.equals(btnReturn)) {
			App.setRoot("admin");
		}
	}

	public static boolean IsInteger(String text) {
		int v;
		try {
			v = Integer.parseInt(text);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
