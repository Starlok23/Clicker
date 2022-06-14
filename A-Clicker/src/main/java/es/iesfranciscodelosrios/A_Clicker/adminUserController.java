package es.iesfranciscodelosrios.A_Clicker;

import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.Model.structures;
import es.iesfranciscodelosrios.Model.username;
import es.iesfranciscodelosrios.ModelDAO.usernameDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminUserController implements Initializable{

	@FXML
	private TableView<username> userView;

	@FXML
	private TableColumn<username, Integer> colIdUsername;

	@FXML
	private TableColumn<username, String> colName;

	@FXML
	private TableColumn<username, String> colUsername;

	@FXML
	private TableColumn<username, String> colPassword;

	@FXML
	private TableColumn<username, Integer> colLevel;
	
	@FXML
	private TableColumn<username, Integer> colCounter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		this.colIdUsername.setCellValueFactory(new PropertyValueFactory<>("IdUsername"));
		this.colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		this.colUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
		this.colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
		this.colLevel.setCellValueFactory(new PropertyValueFactory<>("Level"));
		this.colCounter.setCellValueFactory(new PropertyValueFactory<>("Counter"));
		usernameDAO st = new usernameDAO();
		ObservableList<username> list = st.getAll();
		this.userView.setItems(list);
	}
	
}
