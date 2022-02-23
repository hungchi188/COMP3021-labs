package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
			return name;
		}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}

	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for(Note note : notes) {
			if(note instanceof TextNote) {
				nText +=1;
			}else if (note instanceof ImageNote){
				nImage +=1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	


}
