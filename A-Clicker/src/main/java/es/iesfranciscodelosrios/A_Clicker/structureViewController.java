package es.iesfranciscodelosrios.A_Clicker;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Timer;

import es.iesfranciscodelosrios.Model.structureL;
import es.iesfranciscodelosrios.Model.structures;
import es.iesfranciscodelosrios.Model.username;
import es.iesfranciscodelosrios.ModelDAO.structureLDAO;
import es.iesfranciscodelosrios.ModelDAO.structuresDAO;
import es.iesfranciscodelosrios.ModelDAO.usernameDAO;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class structureViewController implements Initializable {

	@FXML
	private VBox panelFormStructuresView;

	@FXML
	private TableView<structureL> structuresView;

	@FXML
	private TableColumn<structures, String> colStructureName;

	@FXML
	private TableColumn<structures, String> colStructureDescription;

	@FXML
	private TableColumn<structures, Integer> colStructureLevel;

	@FXML
	private TableColumn<username, Integer> colStructureCost;

	@FXML
	private Button btnPurchase;

	private structuresDAO aux = new structuresDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.colStructureName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		this.colStructureDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		this.colStructureLevel.setCellValueFactory(new PropertyValueFactory<>("Level"));
		this.colStructureCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
		structureLDAO sl = new structureLDAO();
		ObservableList<structureL> list = (ObservableList<structureL>) sl.getInfo();
		this.structuresView.setItems(list);
		passive(list);
		passive2(list);
		passive3(list);
		
	}

	/**
	 *  Metodo que te condiciona un coste para comprar una mejora
	 * @param e
	 * @throws IOException
	 */
	@FXML
	public void actionEvent(ActionEvent e) throws IOException {
		Object evt = e.getSource();
		if (evt.equals(btnPurchase)) {
			if (DataService.user.getCounter() >= 250) {
				int asd = DataService.user.getCounter();
				int result = asd - 250;
				DataService.user.setCounter(result);
				aux.levelUp(DataService.str.getName(), DataService.user.getCounter(), DataService.user.getUsername(), DataService.str.getIdStructures(), DataService.user.getIdUsername());
				App.setRoot("game");
			} else if (DataService.user.getCounter() >= 500) {
				int asd = DataService.user.getCounter();
				int result = asd - 500;
				DataService.user.setCounter(result);
				aux.levelUp(DataService.str.getName(), DataService.user.getCounter(), DataService.user.getUsername(), DataService.str.getIdStructures(), DataService.user.getIdUsername());
				App.setRoot("game");
			} else if ((DataService.user.getCounter() >= 1000)) {
				int asd = DataService.user.getCounter();
				int result = asd - 1000;
				DataService.user.setCounter(result);
				aux.levelUp(DataService.str.getName(), DataService.user.getCounter(), DataService.user.getUsername(), DataService.str.getIdStructures(), DataService.user.getIdUsername());
				App.setRoot("game");
			}

		}
	}

	/**
	 *  Metodo para recoger los datos al seleccionar una estructura de la tabla
	 * @param event
	 */
	@FXML
	public void mouseEvent(MouseEvent event) {
		structureL str = this.structuresView.getSelectionModel().getSelectedItem();
		if (str != null) {
			DataService.str.setIdStructures(str.getIdStructures());
			DataService.str.setName(str.getName());
			DataService.str.setLevel(str.getLevel());
			DataService.str.setCost(str.getCost());
			DataService.str.setDescription(str.getDescription());

		}
	}

	/**
	 *  Hilo secundario para incrementar el contador del usuario
	 * @param list
	 */
	public void passive(List<structureL> list) {
		Runnable r = new passiveActive(list);
		Thread t = new Thread(r);
		t.start();
	}
	
	/**
	 *  Hilo secundario para incrementar el contador del usuario
	 * @param list
	 */
	public void passiveNull(List<structureL> list) {
		Runnable r = new passiveActive(list);
		Thread t = new Thread(r);
		t.start();
	}
	
	/**
	 *  Hilo secundario para incrementar el contador del usuario
	 * @param list
	 */
	public void passive2(List<structureL> list) {
		Runnable r = new passiveActive2(list);
		Thread t = new Thread(r);
		t.start();
	}
	

	/**
	 *  Hilo secundario para incrementar el contador del usuario
	 * @param list
	 */
	public void passive3(List<structureL> list) {
		Runnable r = new passiveActive3(list);
		Thread t = new Thread(r);
		t.start();
	}
	
}
