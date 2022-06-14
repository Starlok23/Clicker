package es.iesfranciscodelosrios.Interfaces;

import java.util.Collection;
import java.util.List;

import es.iesfranciscodelosrios.Model.structures;
import javafx.collections.ObservableList;

public interface IStructuresDAO<T, K> {

	boolean insert(String name, String description, int cost);

	ObservableList<structures> getInfo();

	boolean levelUp(String name, int cost, String user, int idU, int idS);

	List<structures> getAll();
	
	boolean delete(String name);
	
	boolean update(String name, String description, int cost, int id);

}
