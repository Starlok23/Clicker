package es.iesfranciscodelosrios.Model;

import javafx.beans.property.SimpleStringProperty;

public class username {

	public int idUsername;
	public String username;
	public String name;
	public String password;
	public int counter;
	public int level;

	public int getIdUsername() {
		return idUsername;
	}

	public void setIdUsername(int idUsername) {
		this.idUsername = idUsername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public username(String name, String username, String password) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.level = 1;
		this.counter = 0;
	}

	public username(int idUsername, String username, String name, String password, int level, int counter) {
		this.idUsername = idUsername;
		this.username = username;
		this.name = name;
		this.password = password;
		this.counter = counter;
		this.level = level;
	}

	public username(String username, int level, int counter) {
	this.username = username;
	this.level = level;
	this.counter = counter;
	}
	
	

	public username() {
		super();
	}

	public username(String username2, String name2, String password2, int level2, int counter2) {
		this.username= username2;
		this.name = name2;
		this.password = password2;
		this.level = level2;
		this.counter = counter2;
	}

	@Override
	public String toString() {
		return "username [idUsername=" + idUsername + ", username=" + username + ", name=" + name + ", password="
				+ password + ", counter=" + counter + ", level=" + level + "]";
	}
	
	

}
