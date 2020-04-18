/**
 * @author Tom Harris
 *
 */
public class TreapNode {
	
	private double priority;
	private int key;
	private int id;
	private TreapNode parent;
	private TreapNode right;
	private TreapNode left;
	
	public TreapNode(double priority, int key, int id, TreapNode parent) {
		this.setPriority(priority);
		this.setKey(key);
		this.setId(id);
		this.setParent(parent);
		this.setRight(null);
		this.setLeft(null);
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "" + this.getKey();
		
	}

	public TreapNode getParent() {
		return parent;
	}

	public void setParent(TreapNode parent) {
		this.parent = parent;
	}

	public TreapNode getRight() {
		return right;
	}

	public void setRight(TreapNode right) {
		this.right = right;
	}

	public TreapNode getLeft() {
		return left;
	}

	public void setLeft(TreapNode left) {
		this.left = left;
	}
	
	
	

}
