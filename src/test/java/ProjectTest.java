import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ProjectTest {
	@Test
	//This test is used to make a simple test of all classes
	public void test() {
		String dir = System.getProperty("user.dir") + "/data/";
		//The original project had two files for names and two for surnames
		List<File> nombres = Arrays.asList(
				new File(dir + "new-top-firstNames.csv")
		);
		List<File> apellidos = Arrays.asList(
				new File(dir + "new-top-surnames.csv")
		);

		PupilsGrades pg = new PupilsGrades(nombres, apellidos);
		/* Initialize a new ByteArrayOutputStream and set it to a new PrintStream
		   that replaces stdout in order to be able to read generated DNIs. */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));

		for (int x = 0; x < 30; x++) {
			//Codes pupils will have are set to be random, and expected to be all unique
			pg.addPupil((int) (Math.random() * Integer.MAX_VALUE), true);
		}

		String dni[] = new String(baos.toByteArray()).split("\n");
		for (String pupilDni : dni) {
			pg.gimmeGrade(pupilDni);
		}

		File guardarEn = new File(dir + "example.txt");
		pg.writeToFile(guardarEn);
	}
}