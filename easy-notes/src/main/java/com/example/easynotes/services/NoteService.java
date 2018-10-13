/**
 * 
 */
package com.example.easynotes.services;

import java.util.List;
import java.util.Optional;

import com.example.easynotes.model.Note;

/**
 * @author e1077874
 *
 */
public interface NoteService {
	public List<Note> getAllNote();
	public Note createNote(Note note);
	public Optional<Note> getNoteById(Long noteId);
	public Note updateNote(Note note);
	public void deleteNote(Note note);
}
