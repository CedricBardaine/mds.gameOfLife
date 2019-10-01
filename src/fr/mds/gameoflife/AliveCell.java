package fr.mds.gameoflife;

public class AliveCell implements Cell {

	@Override
	public Cell newGeneration(int nbNeighbours) {
		Cell nextCell = this ;
		
		if ( nbNeighbours < 2) 
			nextCell = new DeadCell() ;
		else if (nbNeighbours == 2 || nbNeighbours == 3) 
			nextCell = new AliveCell() ; 
		else if (nbNeighbours > 3)
			nextCell = new DeadCell();
		
		return nextCell ; 
	}

	@Override
	public String getAsString() { return "O" ; } 
	
	@Override
	public boolean isAlive() {
		return true;
	}

}
