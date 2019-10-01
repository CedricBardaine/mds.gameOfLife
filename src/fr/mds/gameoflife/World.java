package fr.mds.gameoflife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class World {
	// Cell[][] zaWarudo ;
	Cell[][] theWorld;
	int generation = 0;

	World(int nbColumns, int nbRows) {
		this.theWorld = new Cell[nbColumns][nbRows];
		for (int i = 0; i < theWorld.length; i++) {
			for (int j = 0; j < theWorld[i].length; j++) {
				int rInt = (int) (Math.random() * 2); // 0 ou 1
				if (rInt == 1)
					this.theWorld[i][j] = new DeadCell();
				else if (rInt == 0)
					this.theWorld[i][j] = new AliveCell();
			}

		}
		this.generation = 0;
	}

	// valideé !!!!
	World(String file) {
		int margeX = 10;
		int margeY = 10;
		// créer le monde en ajoutant 20 Cell autour du nombre Cell généré par le file
		// importé
		this.theWorld = new Cell[getNbLinesInFile(file) + 10][getNbMaxLengthofLinesinFile(file) + 10];
		String[] fileContent = getTabOfStringFromFile(file);

		// on initialise les cellules à mortes
		for (int i = 0; i < this.theWorld.length; i++) {
			for (int j = 0; j < this.theWorld[i].length; j++) {
				this.theWorld[i][j] = new DeadCell();
			}
		}

		char[] charsOfTheCurrentLine = null;
		int iTabStr = 0;
		for (int i = 5; i < this.theWorld.length - 5; i++) {
			int jTabStr = 0;

			try {
				charsOfTheCurrentLine = fileContent[iTabStr].toCharArray(); // validé !!!!
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			for (int j = 5; j < this.theWorld[i].length - 5; j++) {
//				jTabStr validé !!!! 

				try {
					if (charsOfTheCurrentLine[jTabStr] == '*' || charsOfTheCurrentLine[jTabStr] == 'O') // ca va faire
																										// un IndexOob
																										// non ? !!!!
						this.theWorld[i][j] = new AliveCell(); // validé !!!!

					else
						this.theWorld[i][j] = new DeadCell();

				} catch (ArrayIndexOutOfBoundsException e) {
//					e.printStackTrace();  
				}

				jTabStr++;

			}
			iTabStr++;
		}
	}

	/**
	 * Refresh the entire World of Cell, calling the method Cell.newGeneration on
	 * each.
	 * 
	 * @author Cedri
	 * @version 1.0 the morphism of the Cells is made one by one, and the following
	 *          Cells depends on the new state of the newly others.
	 */
//	void newGeneration() {
//
//		for (int i = 0; i < theWorld.length; i++) {
//			for (int j = 0; j < theWorld[i].length; j++) {
//				theWorld[i][j] = theWorld[i][j].newGeneration(getNbNeighbours(i, j));
//			}
//		}
//		this.generation++;
//	}
	/**
	 * Refresh the entire World of Cell, calling the method Cell.newGeneration on
	 * each.
	 * 
	 * @author Cedri
	 * @version 2.0 the morphism of the Cells doesn't depends anymore on the new
	 *          states of the others
	 */
	void newGeneration() {
		int nbx = this.theWorld.length;
		int nby = this.theWorld[0].length;
		Cell[][] theNewWord = new Cell[nbx][nby];

		for (int i = 0; i < theWorld.length; i++) {
			for (int j = 0; j < theWorld[i].length; j++) {
				theNewWord[i][j] = theWorld[i][j].newGeneration(getNbNeighbours(i, j));
			}
		}
		this.theWorld = theNewWord;

		this.generation++;
	}

	void prepareRabbitsWorld() {
		this.theWorld = new Cell[3][7];
		this.theWorld[0][0] = new AliveCell();
		this.theWorld[0][1] = new DeadCell();
		this.theWorld[0][2] = new DeadCell();
		this.theWorld[0][3] = new DeadCell();
		this.theWorld[0][4] = new AliveCell();
		this.theWorld[0][5] = new AliveCell();
		this.theWorld[0][6] = new AliveCell();

		this.theWorld[1][0] = new AliveCell();
		this.theWorld[1][1] = new AliveCell();
		this.theWorld[1][2] = new AliveCell();
		this.theWorld[1][3] = new DeadCell();
		this.theWorld[1][4] = new DeadCell();
		this.theWorld[1][5] = new AliveCell();
		this.theWorld[1][6] = new DeadCell();

		this.theWorld[2][0] = new DeadCell();
		this.theWorld[2][1] = new AliveCell();
		this.theWorld[2][2] = new DeadCell();
		this.theWorld[2][3] = new DeadCell();
		this.theWorld[2][4] = new DeadCell();
		this.theWorld[2][5] = new DeadCell();
		this.theWorld[2][6] = new DeadCell();
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "Génération : " + this.generation;
		ret += "\n";

		for (int i = 0; i < theWorld.length; i++) {
			for (int j = 0; j < theWorld[i].length; j++) {
				ret += theWorld[i][j].getAsString();
			}
			ret += "\n";
		}
		return ret;
	}

//	/**
//	 * 
//	 * @param c
//	 * @param r
//	 * @return
//	 * @version 0.0
//	 */
//	public int getNbNeighbours(int c, int r) {
//		int nbN = 0;
//
//		try {
//			if (this.theWorld[c - 1][r - 1].isAlive())
//				nbN++;
//			if (this.theWorld[c][r - 1].isAlive())
//				nbN++;
//			if (this.theWorld[c + 1][r - 1].isAlive())
//				nbN++;
//			if (this.theWorld[c - 1][r].isAlive())
//				nbN++;
//			if (this.theWorld[c + 1][r].isAlive())
//				nbN++;
//			if (this.theWorld[c - 1][r + 1].isAlive())
//				nbN++;
//			if (this.theWorld[c][r + 1].isAlive())
//				nbN++;
//			if (this.theWorld[c + 1][r + 1].isAlive())
//				nbN++;
//
//		} catch (IndexOutOfBoundsException ioobe) {
//			/* on laisse passer. */ } catch (Exception e) {
//			e.getMessage();
//		}
//
//		return nbN;
//	}
	/**
	 * 
	 * @param c
	 * @param r
	 * @return
	 * @version 2.0
	 */
	public int getNbNeighbours(int c, int r) {
		int nbN = 0;

		int cMax = this.theWorld.length;
		int rMax = this.theWorld[0].length;

		if (c - 1 >= 0 && r - 1 >= 0)
			if (this.theWorld[c - 1][r - 1].isAlive())
				nbN++;

		if (r - 1 >= 0)
			if (this.theWorld[c][r - 1].isAlive())
				nbN++;

		if (c + 1 < cMax && r - 1 >= 0)
			if (this.theWorld[c + 1][r - 1].isAlive())
				nbN++;

		if (c - 1 >= 0)
			if (this.theWorld[c - 1][r].isAlive())
				nbN++;

		if (c + 1 < cMax)
			if (this.theWorld[c + 1][r].isAlive())
				nbN++;

		if (c - 1 >= 0 && r + 1 < rMax)
			if (this.theWorld[c - 1][r + 1].isAlive())
				nbN++;

		if (r + 1 < rMax)
			if (this.theWorld[c][r + 1].isAlive())
				nbN++;

		if (c + 1 < cMax && r + 1 < rMax)
			if (this.theWorld[c + 1][r + 1].isAlive())
				nbN++;

		return nbN;
	}
	/**
	 * This version uses trycatch blocks to ignore the case that leads to an
	 * Exception
	 * 
	 * @param c
	 * @param r
	 * @return
	 * @version 1.0
	 */
//	public int getNbNeighbours(int c, int r) {
//		int nbN = 0;
//		
//		try {
//			if (this.theWorld[c - 1][r - 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c][r - 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c + 1][r - 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c - 1][r].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c + 1][r].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c - 1][r + 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c][r + 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		try {
//			if (this.theWorld[c + 1][r + 1].isAlive())
//				nbN++;
//		} catch (Exception e) {
//		}
//		
//		return nbN;
//	}

	/**
	 * Return the number of line in the specified file in parameters Exemple of
	 * filename : files/myfile.txt
	 * 
	 * @param file the name of the file
	 * @return the number of line in the specified file in parameters
	 */
	public int getNbLinesInFile(String file) {
		BufferedReader reader;
		int ret = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				ret++;
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @param file The path of the file to analyse.
	 */
	public int getNbMaxLengthofLinesinFile(String file) {
		BufferedReader reader;
		int ret = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				if (ret < line.length())
					ret = line.length();
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Idée de V2 : avec une Array à la place du tableau de string
	 * 
	 * @param file
	 * @return
	 * @version 0.1
	 */
	public String[] getTabOfStringFromFile(String file) {
		String[] ret = new String[getNbLinesInFile(file)];

		int i = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				ret[i] = line;
				i++;
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
