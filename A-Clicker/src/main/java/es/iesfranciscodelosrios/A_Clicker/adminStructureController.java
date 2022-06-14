package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.Model.structures;
import es.iesfranciscodelosrios.ModelDAO.structuresDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminStructureController implements Initializable{

	@FXML 
	private TableView<structures> structureView;
	
	@FXML
	private TableColumn<structures, String> colIdStructures;
	
	@FXML
	private TableColumn<structures, String> colName;
	
	@FXML
	private TableColumn<structures, String> colDescription;
	
	@FXML
	private TableColumn<structures, Integer> colCost;
	
	@FXML
	private Button btnAdd;
	
	@FXML
	private Button btnRemove;
	
	@FXML
	private Button btnModify;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.colIdStructures.setCellValueFactory(new PropertyValueFactory<>("IdStructures"));
		this.colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		this.colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		this.colCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
		structuresDAO st = new structuresDAO();
		ObservableList<structures> list = (ObservableList<structures>) st.getAll();
		this.structureView.setItems(list);
	}
	
	/**
	 *  Metodo para añadir una mejora
	 * @param e
	 * @throws IOException 
	 */
	public void actionAdd(ActionEvent e) throws IOException {
		App.setRoot("structureAdd");
	}
	
	/**
	 *  Metodo para eliminar una mejora
	 * @param e
	 * @throws IOException 
	 */
	public void actionRemove(ActionEvent e) throws IOException {
		structuresDAO st = new structuresDAO();
		structures str = this.structureView.getSelectionModel().getSelectedItem();
		if(str != null) {
			st.delete(str.getName());
		}
		App.setRoot("admin");
	}
	
	/**
	 * Metodo para modificar una mejora
	 * @param e
	 * @throws IOException 
	 */
	public void actionModify(ActionEvent e) throws IOException {
		structures st = this.structureView.getSelectionModel().getSelectedItem();
		if(st != null) {
			DataService.str.setIdStructures(st.getIdStructures());
			DataService.str.setName(st.getName());
			DataService.str.setDescription(st.getDescription());
			DataService.str.setCost(st.getCost());
			App.setRoot("structureModify");
		}
		
	}

}
