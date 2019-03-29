package gendata;

import java.io.File;
import java.util.*;

/**
 * Basic random name generator used to create names using CSV files as its source.
 */
public class NameGenerator {
	private ArrayList<String> names, surnames;

	/**
	 * Add names from a CSV file to an ArrayList, all located in the same column.
	 *
	 * @param file File to read names from
	 * @param arr ArrayList names will be added to
	 */
	private static void addTo(File file, ArrayList<String> arr) {
		Scanner scnr = null;
		try {
			scnr = new Scanner(file);
			while (scnr.hasNext()) {
				//Column depends on CSV files, originally expected to be 0
				String str = scnr.next().split(",")[1];
				//Table headers are completely lowercase, so they're omitted
				if (!(str.toLowerCase().equals(str))) {
					//Delete double quotes from names before adding them to the ArrayLists
					arr.add(str.replace("\"", "").toUpperCase());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scnr.close();
		}
	}

	/**
	 * Load a new name generator with the following lists of files.
	 *
	 * @param forNames List with files to load names from
	 * @param forSurnames List with files to load names from
	 */
	public NameGenerator(List<File> forNames, List<File> forSurnames) {
		names = new ArrayList<>();
		surnames = new ArrayList<>();

		Iterator<File> itFile = forNames.iterator();
		while (itFile.hasNext()) {
			File file = itFile.next();
			addTo(file, names);
		}

		itFile = forSurnames.iterator();
		while (itFile.hasNext()) {
			File file = itFile.next();
			addTo(file, surnames);
		}

		//Having the names sorted is better, in order to make the generator more "random"
		Collections.sort(names);
		Collections.sort(surnames);
	}

	/**
	 * Dispense a full name randomly generated.
	 *
	 * @return A random but full name
	 */
	public String randomFullName() {
		String oneName = names.get(
				(int) (Math.random() * names.size())
		);
		String oneSurname = surnames.get(
				(int) (Math.random() * surnames.size())
		);

		return oneName + " " + oneSurname;
	}
}
