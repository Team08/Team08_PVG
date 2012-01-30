package main;

import java.io.IOException;

public class ReadNameFile extends FileIO {

	/**
	 * The constructor which takes the file name of the name file as argument
	 * 
	 * @param file
	 *            The file with the start numbers and names
	 */
	public ReadNameFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}

	protected void add() {
		sorter.addName(riderID, name);	
	}
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String[] strArr = line.split("; ");
					Integer startNumber = Integer.parseInt(strArr[0]);
					String name = strArr[1];
					
					if (tm.containsKey(startNumber)) {
						tm.get(startNumber).setName(name);
					} else {
						tm.put(startNumber, new Driver(name));
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			throw new IOException();
		}


}

