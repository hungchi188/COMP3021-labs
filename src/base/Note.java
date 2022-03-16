package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, java.io.Serializable{
	public Date date;
	public String title;
	
	private static final long serialVersionUID = 1L;

	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());		
	}
	
	public String getTitle() {
			return title;
	}
	
	@Override
	public int compareTo(Note o) {
		if (date.after(o.date)) {
			return 1;   		//argument note is smaller
		} else if(date.before(o.date)) {
			return -1;			//this note is more smaller
		} else return 0;		//equal date
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//if (obj == null)
		//	return false;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
	
	
	

}
