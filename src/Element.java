/**
 * @author Tom Harris
 *
 */
public class Element {
	
	private int id;
	private int key;
	
	public Element(int id, int key) {
		this.setId(id);
		this.setKey(key);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	
}
