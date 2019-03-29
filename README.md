# Java Maps Exercise 1
Java project made as an exercise for programming classes from my vocational training. Its main objective is to practice map (associative array in other programming languages) usage, interface implementing and file writing. Maven has been used in order to import JUnit dependencies (necessary to run the test class) more easily.

These are the classes that were initially required:

## Grades (interface)
Only for practising interface implementation, with a single method.

* void gimmeGrade(String dni) {/\* Search within the class' map a pupil with the given DNI and assign them a random grade between 0 and 10 \*/}

## Pupil
Representing a pupil, it stores the following attributes:

* int code //Their internal code
* String name
* String dni //The Spanish equivalent to a Passport Card in the US
* String province
* double grade //Always initialized to 0, even if the parameterized constructor is used

There are two constructors for this class: one unparameterized and one parameterized, in which all attributes are set except the grade, which is, again, always initialized to 0. Getters and setters have been generated as needed.

## PupilsGrades (implements Grades)
The project's main class, in a way, responsible for handling the pupils' data. It was required to implement (apart from gimmeGrade):

* A LinkedHashMap with Strings as keys and ArrayLists of Pupils as values. The keys are provinces the pupil's are in.
* void addPupil(int code) {/\* Create a new pupil with the given code \*/}
* void writeToFile(File file) {/\* Write map's contents to a text file, organizing pupils by province \*/}

----

Obviously, the addPupil method requiring a code was a mistake from the teacher, using a Pupil object as parameter instead. However, I decided to keep it in order to try to read the generated DNIs from System.out and give grades to all randomly created pupils (that's why addPupil has a second parameter, so that the printing can be disabled).

That's achieved in the testing class, by creating a ByteArrayOutputStream that is used by a PrintStream that replaces the original System.out, allowing to access all DNIs created and, as the \n character is sort of a separator, assign random grades to all randomly generated pupil.

The randomness of the pupil generating is the reason there's an ArrayList of Strings with provinces, which are read in the constructor and used later when creating a pupil. Plus, in order to get random names to test, I decided to create the following class inside "gendata" package, and add an attribute to the main class of that type:

## NameGenerator
This class initializes taking two Lists of Files, one containing files with names, and the other containing files with surnames, all of which are always expected to be CSV files and have commas as separators. In fact, those lists are the ones PupilsGrades needs to initialize, as the NameGenerator in the class isn't created before hand.

This class has to methods:

* private static void addTo(File file, ArrayList (of String) arr) {/\* Made for internal usage inside the constructor, adds a column of all rows of names from the file to the ArrayList. The index variable str always take is subject to change, always depending on how the given CSV files are made \*/}
* void randomFullName() {/\* Creates a random full name, taking data from its two ArrayLists \*/}

CSV files uploaded with this project (in the data folder, where all reading and writing files are) have been obtained from [most-common-name](https://github.com/fivethirtyeight/data/tree/master/most-common-name), published by [FiveThrirtyEight](https://github.com/fivethirtyeight) under the [CC BY 4.0 License](https://creativecommons.org/licenses/by/4.0/).