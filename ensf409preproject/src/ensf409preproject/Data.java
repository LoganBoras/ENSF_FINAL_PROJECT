package ensf409preproject;

/**
 * Represents data within a node
 */
public class Data {
	
	/**
	 * id is the student's id eajnbaen;b;lejtnabhoeoiqthno[
	 * faculty is the student's faculty 
	 */
	String  id,faculty, major, year;
	
	public Data( String i, String f, String m, String y)
	{
		id = i;
		faculty = f;
		major = m; //hi
		year = y;
	}
	
	public String toString()
	{
		return ("id : " + id + " faculty: " + faculty + " major: " + major + 
						" year: " + year);
	}	
}
