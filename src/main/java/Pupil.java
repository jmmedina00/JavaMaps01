public class Pupil {
	private int code;
	private String name, dni, province;
	private double grade = 0;

	/**
	 * Unparameterized constructor
	 */
	public Pupil() {}

	/**
	 * Parameterized constructor for a Pupil object. All parameters are set except
	 * their grade, which is initialized to 0.
	 *
	 * @param code Pupil's internal code
	 * @param name Pupil's name
	 * @param dni Pupil's DNI, equivalent to Passport Card
	 * @param province Pupil's province
	 */
	public Pupil(int code, String name, String dni, String province) {
		this.code = code;
		this.name = name;
		this.dni = dni;
		this.province = province;
	}

	/**
	 * Getter of province attribute
	 *
	 * @return The pupil's province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Getter of code attribute
	 *
	 * @return The pupil's internal code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Getter of name attribute
	 *
	 * @return The pupil's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of dni attribute
	 *
	 * @return The pupil's DNI
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Getter of grade attribute
	 *
	 * @return The pupil's grade
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * Setter of grade attribute
	 *
	 * @param grade New grade to be assigned
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}
}
