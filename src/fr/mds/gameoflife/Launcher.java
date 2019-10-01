package fr.mds.gameoflife;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) throws IOException {
		World theWorld = new World(50, 100);

		int selectedMode = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		        System.out.print("Enter String");
//		        String s = br.readLine();
		System.out.println("Enter the mode :");
		System.out.println("1 : Random");
		System.out.println("2 : Rabit world");
		System.out.println("3 : Read a pattern.txt");
		System.out.print(": ");
		try {
			selectedMode = Integer.parseInt(br.readLine());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Format!");
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////
		if (selectedMode == 1) {

			System.out.println(theWorld.toString());
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.getMessage();
				}

				theWorld.newGeneration();

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println(theWorld.toString());
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////
		if (selectedMode == 2) {
			theWorld.prepareRabbitsWorld();

			System.out.println(theWorld.toString());
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.getMessage();
				}

				theWorld.newGeneration();

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println(theWorld.toString());
			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////
		if (selectedMode == 3) {
			System.out.println("Here is a list of the files you can play : ");
			System.out.println("----------------");
			File dir = new File("files/");
			File[] content = dir.listFiles();
			for (File object : content) {
				if (object.isFile())
					System.out.println(object.getName());
			}
			System.out.println("----------------");
			System.out.println();

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the exact name of the choosen file");
			String nameFile = sc.nextLine();

			String filePath = "files/" + nameFile;
			// tet du path
			File leFile = new File(filePath);
			if (leFile.isFile()) {

				theWorld = new World(filePath);

				System.out.println(theWorld.toString());
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.getMessage();
					}

					theWorld.newGeneration();

					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println(theWorld.toString());

					

				}
			}
			else System.err.println("This file doesn't exists.");

		}

		else
			System.err.println("Le mode sélectionner est invalid ! ");
	}
}
