package es.iesfranciscodelosrios.Interfaces;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import es.iesfranciscodelosrios.Model.username;
import javafx.collections.ObservableList;

public interface IUsernameDAO<T, K> {
	
	boolean insert(T ob);
	int login(String user, String pass) throws IOException;
	void getInfo(String user);
	boolean counterUp(String user);
	boolean levelUp(String user);
}
