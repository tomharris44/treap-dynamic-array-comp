import java.util.Arrays;

/**
 * @author Tom Harris
 *
 */public class DynamicArray {

	private int[] array;
	private int size;
	private int current;
	
	public DynamicArray() {
		this.array = new int[1];
		
	//	Fill array with -1 to indicate empty spaces as it is possible to have key=0
		
		Arrays.fill(this.array, -1);
		
		this.size = 1;
		this.current = 0;
	}
	
	/**
	 * Route given Operation to appropriate DynamicArray method.
	 * 
	 * @param op Operation to be routed
	 */
	public void route(Operation op) {
		if (op instanceof Insertion) {
			this.insert(((Insertion) op).getElem());
		} else if (op instanceof Deletion) {
			this.delete(((Deletion) op).getKeyDel());
		} else {
			this.search(((Search) op).getQ());
		}
	}
	
	/**
	 * Resize DynamicArray once it has been filled by doubling size.
	 * 
	 * @param A Array to be resized
	 * @return Resized A
	 */
	public int[] resize(int[] A) {
		
		int[] A_new = new int[this.size * 2];
		Arrays.fill(A_new, -1);
		
		for (int i=0;i<this.size;i++) {
			A_new[i] = A[i];
		}
		
		this.size = this.size * 2;
		
		return A_new;
	}
	
	/**
	 * Insert new element into the DynamicArray. May call resize() if array is full.
	 * 
	 * @param elem Element to be inserted
	 */
	public void insert(Element elem) {
		
		if (this.current == this.size) {
			this.array = resize(this.array);
		}
		
		this.array[current] = elem.getKey();
		
		current++;
		
	}
	
	/**
	 * Search for given key in DynamicArray.
	 * 
	 * @param key Search key
	 * @return Index of key if found. Otherwise, -1.
	 */
	public int search(int key) {
		
		for (int i=0;i<this.size;i++) {
			if (this.array[i] == key) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Delete given key from DynamicArray.
	 * 
	 * @param keyDel Key to be deleted.
	 * @return True if successfully found and deleted.
	 */
	public boolean delete(int keyDel) {
		
		int ind = search(keyDel);
		
		if (ind != -1) {
			if (ind != current - 1) {
				int temp = this.array[ind];
				this.array[ind] = this.array[current - 1];
				this.array[current - 1] = temp;
			}
			
			deleteElement(current - 1);
			
			if (size > 1 && current < (0.25 * size)) {
				this.array = downsize(this.array);
			}
			
			return true;
		} else {
			return false;
		}
		
		
		
		
	}
	
	/**
	 * Downsize given array to half of its current size.
	 * 
	 * @param A Array to be downsized.
	 * @return Array with new size.
	 */
	public int[] downsize(int[] A) {
		
		int[] A_new = new int[size / 2];
			
		for (int i=0;i<size/2;i++) {
			A_new[i] = this.array[i];
		}
		
		this.size = this.size / 2;
		return A_new;
		
	}

	/**
	 * Delete key at given index from DynamicArray.
	 * 
	 * @param index Index of key to be deleted.
	 */
	public void deleteElement(int index) {
		this.array[index] = -1; 
		this.current --;
	}
	
	public int[] getArray() {
		return array;
	}
	
}
