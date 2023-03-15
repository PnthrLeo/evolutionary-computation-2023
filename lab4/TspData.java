package lab3;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files


class TspData {
    
    String name;
	String comment;
	String type;
	Integer dimension;
	String edgeWeightType;
	ArrayList<Integer[]> nodeCoords;
	
	TspData() {
		name = "";
		comment = "";
		type = "";
		dimension = 0;
		edgeWeightType = "";
		nodeCoords = new ArrayList<Integer[]>();
	}

	TspData(String path) {
		this();
		this.read_tsp(path);
	}

	public void read_tsp(String path) {
		try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

				if (line.contains("NAME")) {
					String[] words = line.split(" : ");
					this.name = words[1];
				}
				else if (line.contains("COMMENT")) {
					String[] words = line.split(" : ");
					this.comment += words[1] + "\n";
				}
				else if (line.contains("TYPE")) {
					String[] words = line.split(" : ");
					this.type = words[1];
				}
				else if (line.contains("DIMENSION")) {
					String[] words = line.split(" : ");
					this.dimension = Integer.parseInt(words[1]);
				}
				else if (line.contains("EDGE_WEIGHT_TYPE")) {
					String[] words = line.split(" : ");
					this.edgeWeightType = words[1];
				}
				else if (line.contains("NODE_COORD_SECTION")) {
					line = myReader.nextLine();
					while (!line.contains("EOF")) {
						String[] words = line.split(" ");
						Integer[] pos = new Integer[2];
						pos[0] = Integer.parseInt(words[1]);
						pos[1] = Integer.parseInt(words[2]);
						nodeCoords.add(pos);
						line = myReader.nextLine();
					}
				}
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
}
