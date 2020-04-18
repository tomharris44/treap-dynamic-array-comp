import java.util.Random;
/**
 * @author Tom Harris
 *
 */
public class Treap {
	
	private TreapNode root;

	public Treap() {
		this.root = null;
	}
	
	public TreapNode getRoot() {
		return root;
	}
	
	
	
	/**
	 * Routes the input operation to the appropriate internal Treap method
	 * 
	 * @param op Operation to be processed by Treap
	 */
	public void route(Operation op) {
		if (op instanceof Insertion) {
			this.insert(((Insertion) op).getElem());
		} else if (op instanceof Deletion) {
			this.delete(((Deletion) op).getKeyDel());
		} else {
			this.search(((Search) op).getQ(), this.root);
		}
	}
	
	
	/**
	 * Performs a right rotation on a given Treap node
	 * 
	 * @param node Parent of TreapNode to be rotated right
	 */
	public void rotateRight(TreapNode node) {
		
		TreapNode lChild = node.getLeft();
		TreapNode LRSubtree = null;
		
		if (lChild.getRight() != null) {
			LRSubtree = lChild.getRight();
		}
		
		lChild.setRight(node); 

		if (node.getParent() != null) {
			lChild.setParent(node.getParent());
			if (node.getParent().getLeft() != null && node.getParent().getLeft().getId() == node.getId()) {
				node.getParent().setLeft(lChild);
			} else if (node.getParent().getRight() != null && node.getParent().getRight().getId() == node.getId()){
				node.getParent().setRight(lChild);
			}
		} else {
			lChild.setParent(null);
			this.root = lChild;
		}
		
        node.setLeft(LRSubtree); 
        node.setParent(lChild);
        
        if (LRSubtree != null) {
        	LRSubtree.setParent(node);
        }
        
        
	}
	
	/**
	 * Performs a left rotation on a given Treap node
	 * 
	 * @param node Parent of TreapNode to be rotated left
	 */
	public void rotateLeft(TreapNode node) {
		
		TreapNode rChild = node.getRight();
		TreapNode RLSubtree = null;
		
		
		if (rChild.getLeft() != null) {
			RLSubtree = rChild.getLeft();
		}
		
		
		rChild.setLeft(node); 
		rChild.setParent(node.getParent());
		
		if (node.getParent() != null) {
			if (node.getParent().getLeft() != null && node.getParent().getLeft().getId() == node.getId()) {
				node.getParent().setLeft(rChild);
			} else if (node.getParent().getRight() != null && node.getParent().getRight().getId() == node.getId()){
				node.getParent().setRight(rChild);
			}
		} else {
			this.root = rChild;
		}
		
        node.setRight(RLSubtree);
        node.setParent(rChild);
        
        if (RLSubtree != null) {
        	RLSubtree.setParent(node);
        }
        
	}
	
	/**
	 * Resolves the priority condition of a given Treap, by rotating a given TreapNode up. BST condition is maintained whilst resolving. Used after an insertion.
	 * 
	 * @param node TreapNode to be rotated up
	 */
	public void resolveHeapUp(TreapNode node) {
		
		double xPriority = node.getPriority();
		double parentPriority = node.getParent().getPriority();
		
		int xKey = node.getKey();
		int parentKey = node.getParent().getKey();
		
		if (xPriority < parentPriority){
			
			if (xKey < parentKey) {
				rotateRight(node.getParent());
			} else {
				rotateLeft(node.getParent());
			}
			
			if (node.getParent() != null) {
				resolveHeapUp(node);
			}
	
		}
		
	}
	
	/**
	 * Resolves the priority condition of a given Treap, by rotating a given TreapNode down. BST condition is maintained whilst resolving. Used after a deletion.
	 * 
	 * @param node TreapNode to be rotated down
	 */
	public void resolveHeapDown(TreapNode node) {
		
		double xPriority = node.getPriority();
		
		 if (node.getLeft() != null && node.getRight() != null){
			 
	 		if (node.getLeft().getPriority() < node.getRight().getPriority()){
				double childPriority = node.getLeft().getPriority();
				
				if (xPriority > childPriority) {
					rotateRight(node);
					resolveHeapDown(node);
				}
			} else {
				double childPriority = node.getRight().getPriority();
				
				if (xPriority > childPriority) {
					rotateLeft(node);
					resolveHeapDown(node);
				}
			}
		 		
		 } else if (node.getLeft() != null) {
			 
			double childPriority = node.getLeft().getPriority();
			
			if (xPriority > childPriority) {
				rotateRight(node);
				resolveHeapDown(node);
			}
			
			
		} else if (node.getRight() != null) {
			
			double childPriority = node.getRight().getPriority();
			
			if (xPriority > childPriority) {
				rotateLeft(node);
				resolveHeapDown(node);
			}
		}
		
	}
	
	
	/**
	 * Inserts a given TreapNode into the Treap
	 * 
	 * @param node TreapNode to be inserted
	 */
	public void insert(Element node) {
		
		Random rand = new Random();
		
		double new_priority = rand.nextDouble();
		
		if (this.root == null) {
			TreapNode new_node = new TreapNode(new_priority,node.getKey(),node.getId(),null);
			this.root = new_node;
			return;
		}
	
		TreapNode comp = root;
		
		TreapNode new_node = null;
		
		Boolean check = true;
		
		while (check) {
			if (comp.getKey() > node.getKey()) {
				if (comp.getLeft() != null) {
					comp = comp.getLeft();
				} else {
					new_node = new TreapNode(new_priority,node.getKey(),node.getId(),comp);
					comp.setLeft(new_node);
					check = false;
				}
			} else {
				if (comp.getRight() != null) {
					comp = comp.getRight();
				} else {
					new_node = new TreapNode(new_priority,node.getKey(),node.getId(),comp);
					comp.setRight(new_node);
					check = false;
				}
			}			
		}
	
		resolveHeapUp(new_node);
		
		
	}
	
	/**
	 * Searches for a given key value at a TreapNode. Uses recursion to search rest of Treap
	 * 
	 * @param q Search key
	 * @param node TreapNode to compare at
	 * @return Result of search() on left/right child depending on comparison of search key to key value at node
	 */
	public TreapNode search(int q, TreapNode node) {
		
	    if (node==null) {
	    	return null;
	    } else if (node.getKey()==q) { 
	    	return node;
	    }
	    
	    if (node.getKey() > q) 
	        return search(q, node.getLeft()); 
	  
	    return search(q, node.getRight());
	}
	
	/**
	 * Deletes given key from Treap.
	 * 
	 * @param key Key to be deleted
	 * @return True if key was successfully found and deleted
	 */
	public boolean delete(int key) {
		
		TreapNode keyDel = search(key, this.root);
		
		if (keyDel == null) {
			return false;
		}
		
		keyDel.setPriority(Integer.MAX_VALUE);
		
		resolveHeapDown(keyDel);
		
		deleteNode(keyDel);
		
		return true;
		
	}
	
	/**
	 * Deletes given node by removing parental reference to node
	 * 
	 * @param node TreapNode to be deleted
	 */
	public void deleteNode(TreapNode node) {
		if (node.getParent() != null) {
			int nodeId = node.getId();
			if (node.getParent().getLeft() != null && nodeId == node.getParent().getLeft().getId()) {
				node.getParent().setLeft(null);
			} else if (node.getParent().getRight() != null && nodeId == node.getParent().getRight().getId()) {
				node.getParent().setRight(null);
			}
		} else {
			this.root = null;
		}
		
	}
	
	/**
	 * Specific print function for a Treap rooted at given node.
	 * 
	 * @param node TreapNode that is root of Trea p to be printed
	 */
	public void print(TreapNode node) {
		
		
		if (node != null) {
			if (node.getParent() != null) {
				System.out.print("Parent: " + node.getParent().getKey() + " " + node.getParent().getId() + " ; ");
			}
		
			System.out.println(node.getKey() + " " + node.getPriority() + " " + node.getId());
	
			print(node.getLeft());
			print(node.getRight());
		} else {
			System.out.println("NULL");
		}
		
	}
	
	/**
	 * Checks whether priority and BST requirements of Treap are met. 
	 * 
	 * @param node root of Treap to be checked
	 * @return True if Treap is valid
	 */
	public boolean isTreap(TreapNode node) {
		
		if (node == null) {
			return true;
		}
		
		if (node.getLeft() != null) {
			if (node.getLeft().getKey() > node.getKey() || node.getLeft().getPriority() < node.getPriority() 
					|| node.getId() != node.getLeft().getParent().getId() || (node.getLeft().getKey() == node.getKey() && node.getLeft().getId() > node.getId())) {
				print(node);
				print(node.getLeft().getParent());
				return false;
			}
		}
		
		if (node.getRight() != null) {
			if (node.getRight().getKey() < node.getKey() || node.getRight().getPriority() < node.getPriority() 
					|| node.getId() != node.getRight().getParent().getId() || (node.getRight().getKey() == node.getKey() && node.getRight().getId() < node.getId())) {
				print(node);
				print(node.getRight().getParent());
				return false;
			}
		}
		
		return isTreap(node.getLeft()) && isTreap(node.getRight());
	}

}
