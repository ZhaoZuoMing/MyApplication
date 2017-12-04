package f.sky.flight.model;

public class RegionInfo {
	
	private int id;
	private int parent;//省编号
	private String name;//中文名称
	private int type;//1为省名  2为城市名
	public RegionInfo() {
		super();
	}
	
	public RegionInfo(int id, int parent, String name) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
