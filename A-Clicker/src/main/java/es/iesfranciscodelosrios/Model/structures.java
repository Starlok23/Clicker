package es.iesfranciscodelosrios.Model;

public class structures {

	public int idStructures;
	public String name;
	public String description;
	public int cost;


	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getIdStructures() {
		return idStructures;
	}

	public void setIdStructures(int idStructures) {
		this.idStructures = idStructures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public structures(int idStructures, String name, String description,int cost) {
		this.idStructures = idStructures;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public structures(String name, String description, int cost) {
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public structures() {
	}

	@Override
	public String toString() {
		return "structures [idStructures=" + idStructures + ", name=" + name + ", description="
				+ description + ", cost=" + cost + "]";
	}

}
