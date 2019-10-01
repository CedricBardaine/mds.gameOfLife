package fr.mds.gameoflife;

/**
 * 
 * @author cedri
 * @version 0.1 
 */
public interface Cell {
	
	
	/**
	 * @param nbNeighbours
	 * @return the next generation cell depending on the number of neighbours.
	 */
	Cell newGeneration(int nbNeighbours) ; 
	
	/**
	 * @return a String representation of the cell state. 
	 */
	String getAsString() ;
	
	/**
	 * @return if the cell is currently alive or not 
	 */
	boolean isAlive() ; 
}
