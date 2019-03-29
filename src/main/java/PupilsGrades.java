import gendata.NameGenerator;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * The project's main class, in a way, responsible for handling the pupils' data.
 */
public class PupilsGrades implements Grades {
	private NameGenerator ng;
	private LinkedHashMap<String, ArrayList<Pupil>> lhm = new LinkedHashMap<>(); //Provinces are used as keys
	private ArrayList<String> provinces;

	/**
	 * The class' constructor. No parameters were firstly required, but
	 * the name generator needs to be initialized somewhere.
	 * The ArrayList with provinces is loaded here as well.
	 *
	 * @param forNames A List with files to read names from.
	 * @param forSurnames A List with files to read names from.
	 */
	public PupilsGrades(List<File> forNames, List<File> forSurnames) {
		//Read all provinces from the given file below.
		provinces = new ArrayList<>();
		Scanner scnr = null;
		try {
			//Scanner used to read the given file
			scnr = new Scanner(
					new File(System.getProperty("user.dir") + "/data/provinces.txt")
			);

			while (scnr.hasNext()) {
				String prov = scnr.nextLine();
				provinces.add(prov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scnr != null) {
				scnr.close();
			}
		}

		//Once that's done, the name generator is initialized
		ng = new NameGenerator(forNames, forSurnames);
	}

	/**
	 * Assign a random grade from 0 to 10 (exclusive) to a pupil whose
	 * DNI matches the given one. No change if that pupil is not found
	 *
	 * @param dni The pupil's DNI a grade is wanted to assign.
	 */
	public void gimmeGrade(String dni) {
		Pupil pupil = null;
		Iterator<String> itKey = lhm.keySet().iterator();

		//In order to leave the loop early properly, I found it better to use while loops.
		while (itKey.hasNext() && (pupil == null)) {
			String prov = itKey.next();
			Iterator<Pupil> itVal = lhm.get(prov).iterator();

			while (itVal.hasNext() && (pupil == null)) {
				Pupil nextOne = itVal.next();
				if (nextOne.getDni().equals(dni)) {
					pupil = nextOne;
				}
			}
		}

		//Selected pupil might be null if given DNI is erroneous
		if (pupil != null) {
			pupil.setGrade(Math.random() * 10);
		}
	}

	/**
	 * Create a new pupil with the given code with random data and add it to the map by
	 * its chosen province.
	 *
	 * This method will send DNI to stdout if toStdout is set to true.
	 *
	 * @param code Code provided to new pupil
	 * @param toStdout Whether printing pupil's generated DNI or not
	 */
	public void addPupil(int code, boolean toStdout) {
		//Random pupil generation
		String dni = ""; //DNI format: DDDDDDDDL (D: digit, L: letter)
		for (int x = 0; x < 8; x++) {
			dni += String.valueOf((int) (Math.random() * 10)); //-> 0-9
		}

		dni += "X"; //DNI letter is often calculated, but not required for the exercise's purpose.
		if (toStdout) {
			System.out.println(dni); //Left it in purpose, in order to be able to test gimmeGrade method.
		}
		Pupil alu = new Pupil(code,
				ng.randomFullName(),
				dni,
				provinces.get((int) (Math.random() * provinces.size()))
		);

		//Adding pupil to the map
		String oneProvince = alu.getProvince();
		ArrayList<Pupil> arr = null;
		/*
		* The ArrayList the pupil will be added to will be defined by
		* whether the province already exists in the map or not.
		*/

		if (lhm.containsKey(oneProvince)) {
			arr = lhm.get(oneProvince);
		} else {
			arr = new ArrayList<>();
			lhm.put(oneProvince, arr); //The new ArrayList can be added to the map safely already
		}
		arr.add(alu);
	}

	/**
	 * Write the contents of the object's map to given file, organizing pupils by province
	 *
	 * @param file File the pupil's info is going to be written to
	 */
	public void writeToFile(File file) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
			for (String oneProvince : lhm.keySet()) {
				ps.println(oneProvince + ":");
				Iterator<Pupil> it = lhm.get(oneProvince).iterator();

				while (it.hasNext()) {
					Pupil alu = it.next();
					ps.println(alu.getCode() + " - " + alu.getName() + " - " + alu.getDni()
							+ " - " + alu.getGrade());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
}
