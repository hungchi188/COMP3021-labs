package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>{

	public ArrayList<Note> notes;
	public String name;
	
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

	@Override
	public int compareTo(Folder o) {
		if (name.compareTo(o.name)>0) {
			return 1;				//arg name smaller than this name
		} else if (name.compareTo(o.name)<0) {
			return -1;				//this name smaller than arg
		} else return 0;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		keywords = keywords.toUpperCase();
		String[] words = keywords.split("\\s+");
		
	
		List<Note> tarNote = new ArrayList<Note>();
	
		for(Note note:notes) {
			boolean match = true;

			if(note instanceof ImageNote) {
				
				for(int i=0; i<words.length;i++) {
					
					if(words[i].contains("OR")) continue;
					if (!note.getTitle().toUpperCase().contains(words[i])){  //not contain the keyword
						if (i==words.length-1) {			//last keyword
							match = false;
							break;
						}
						
						if(!words[i+1].contains("OR")) {		//next keyword is not or
							match = false;
							break;
						}
					}else {
						if (i==words.length-1) break;
						if(words[i+1].contains("OR")) {		//if match and next word is or
							i=i+2;
						}
					}
				}
				if(match) tarNote.add(note);
			} else if (note instanceof TextNote) {
				
				TextNote temp = (TextNote)note;
				for(int i=0; i<words.length;i++) {
					
					if(words[i].contains("OR")) continue;
					if (!(temp.getTitle().toUpperCase().contains(words[i]))&&
						!(temp.getContent().toUpperCase().contains(words[i]))){  //not contain the keyword
						if (i==words.length-1) {			//last keyword
							match = false;
							break;
						}
						
						if(!words[i+1].contains("OR")) {		//next keyword is not or
							match = false;
							break;
						}
					}else {
						if (i==words.length-1) break;
						if(words[i+1].contains("OR")) {		//if match and next word is or then skip
							i=i+2;
						}
					}
				}
				if(match) tarNote.add(temp);
			}
		}
		return tarNote;
	}

}
