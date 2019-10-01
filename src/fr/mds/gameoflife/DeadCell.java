package fr.mds.gameoflife;

/**
 * This class represents a dead Cell in the World.
 * 
 * @author cedri
 * @version 0.1
 */
public class DeadCell implements Cell {

	@Override
	public Cell newGeneration(int nbNeighbours) {
		Cell nextCell = this;

		if (nbNeighbours == 3 )
			nextCell = new AliveCell();

		return nextCell;
	}

	@Override
	public String getAsString() {
		return "-";
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
