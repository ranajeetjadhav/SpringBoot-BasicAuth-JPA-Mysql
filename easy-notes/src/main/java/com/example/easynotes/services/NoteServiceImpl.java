/**
 * 
 */
package com.example.easynotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;

/**
 * @author e1077874
 *
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository; 
	
	/* (non-Javadoc)
	 * @see com.example.easynotes.services.NoteService#getAllNote()
	 */
	@Override
	public List<Note> getAllNote() {
		return noteRepository.findAll();		
	}

	/* (non-Javadoc)
	 * @see com.example.easynotes.services.NoteService#createNote(com.example.easynotes.model.Note)
	 */
	@Override
	public Note createNote(Note note) {
		return noteRepository.save(note);
	}

	/* (non-Javadoc)
	 * @see com.example.easynotes.services.NoteService#getNoteById(java.lang.Long)
	 */
	@Override
	public Optional<Note> getNoteById(Long noteId) {
		return noteRepository.findById(noteId);
	}

	/* (non-Javadoc)
	 * @see com.example.easynotes.services.NoteService#updateNote(com.example.easynotes.model.Note)
	 */
	@Override
	public Note updateNote(Note note) {		
		return noteRepository.save(note);
	}

	/* (non-Javadoc)
	 * @see com.example.easynotes.services.NoteService#deleteNote(java.lang.Long)
	 */
	@Override
	public void deleteNote(Note note) {
		noteRepository.delete(note);
	}
}
