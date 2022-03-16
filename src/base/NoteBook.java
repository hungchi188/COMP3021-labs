package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
		fis = new FileInputStream(file);
		in = new ObjectInputStream(fis);
		NoteBook nb= (NoteBook) in.readObject();
		folders = new ArrayList<Folder>();
		folders = nb.getFolders();
		in.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for(Folder folder : folders) {
			if(folder.getName() == folderName) {
				f = folder;
				break;
			}
		}
		if(f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		
		for(Note n : f.getNotes()) {
			if(n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
		
	}
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public void sortFolders() {
		for(Folder folder : folders) {
			folder.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName, note);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> tarNote = new ArrayList<Note>();
		
		for (Folder folder:folders) {
			tarNote.addAll(folder.searchNotes(keywords));
		}
		return tarNote;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
