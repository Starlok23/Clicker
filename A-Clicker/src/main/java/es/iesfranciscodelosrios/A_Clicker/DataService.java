package es.iesfranciscodelosrios.A_Clicker;

import java.util.ArrayList;
import java.util.List;

import es.iesfranciscodelosrios.Model.structureL;
import es.iesfranciscodelosrios.Model.username;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DataService {
	
	public static IntegerProperty counter =  new SimpleIntegerProperty(0); ;
	public static username user = new username();
	public static structureL str = new structureL();
	
}
