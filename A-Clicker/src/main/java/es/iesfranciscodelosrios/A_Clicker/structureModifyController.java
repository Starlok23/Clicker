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

public class structureModifyController implements Initializable {

	@FXML
	private TextField modifyName;

	@FXML
	private TextField modifyDescription;

	@FXML
	private TextField modifyCost;

	@FXML
	private Button btnModify;

	@FXML
	private Button btnReturn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		modifyName.setText(DataService.str.getName());
		modifyCost.setText(String.valueOf(DataService.str.getCost()));
		modifyDescription.setText(DataService.str.getDescription());

	}

	/**
	 * Modificar mejora seleccionada previamente
	 * 
	 * @throws IOException
	 */
	@FXML
	public void update(ActionEvent e) throws IOException {
		DataService.str.setName(modifyName.getText());
		DataService.str.setDescription(modifyDescription.getText());
		DataService.str.cost = Integer.parseInt(modifyCost.getText());
		Object evt = e.getSource();
		if (btnModify.equals(evt)) {

			if (!modifyName.getText().isEmpty() && !modifyDescription.getText().isEmpty()
					&& !modifyCost.getText().isEmpty()) {
				structuresDAO st = new structuresDAO();
				st.update(DataService.str.getName(), DataService.str.getDescription(), DataService.str.getCost(),
						DataService.str.getIdStructures());
				App.setRoot("admin");
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

}
