package es.iesfranciscodelosrios.Model;

public class structureL extends structures{

	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "structureL [level=" + level + ", name=" + name + ", description=" + description + ", cost=" + cost
				+ "]";
	}

	public structureL(int level) {
		this.level = level;
	}
	
	public structureL(int idStructures, String name, String description, int cost, int level) {
		super(idStructures, name, description, cost);
		this.level = level;
	}

	public structureL(String name, String description, int level, int cost) {
		this.name = name;
		this.description = description;
		this.level = level;
		this.cost = cost;
	}
	
	public structureL() {
		this.level = 0;
	}

	public structureL(String name, String description, int cost) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.level = 0;
	}
	
}
