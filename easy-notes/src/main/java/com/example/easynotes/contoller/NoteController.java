/**
 * 
 */
package com.example.easynotes.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.services.NoteService;

/**
 * @author e1077874
 *
 */

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	// Get all the notes
	@GetMapping("/notes")
	public List<Note> getAllNotes(){		
		return noteService.getAllNote();
	}
	
	// Create a new note
	@PostMapping("/notes")
	public Note crateNote(@Valid @RequestBody Note note){
		return noteService.createNote(note);	
	}
	
	// Get a single note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value="id") Long id){
		return noteService.getNoteById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}
	
	// Update a note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value="id") Long id, @Valid @RequestBody Note noteDetail){
		Note note = noteService.getNoteById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		
		note.setTitle(noteDetail.getTitle());
		note.setContent(noteDetail.getContent());
		
		return noteService.updateNote(note);
				
	} 
	
	// Delete a note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value="id") Long id){
		Note note = noteService.getNoteById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));		
		
		noteService.deleteNote(note);
		return ResponseEntity.ok().build();
	} 	
}
