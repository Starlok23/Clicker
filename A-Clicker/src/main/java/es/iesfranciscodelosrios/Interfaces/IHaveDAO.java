package es.iesfranciscodelosrios.Interfaces;


import java.util.List;

import es.iesfranciscodelosrios.Model.structureL;
import es.iesfranciscodelosrios.Model.structures;
import javafx.collections.ObservableList;

public interface IHaveDAO<T, K> {

	List<structureL> getInfo();
}
