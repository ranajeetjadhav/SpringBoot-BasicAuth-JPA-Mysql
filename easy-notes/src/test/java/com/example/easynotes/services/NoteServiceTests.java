/**
 * 
 */
package com.example.easynotes.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author e1077874
 *
 */

@RunWith(SpringRunner.class)
public class NoteServiceTests {
		
	@Mock
	private NoteRepository noteRepository;
	
	@InjectMocks
	private NoteServiceImpl noteService;
	
	@Before
	public void init() {
		noteService = new NoteServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllNotesTest(){
		List<Note> noteList = new ArrayList<>();
		noteList.add(new Note(1, "note 1", "Spring boot is awesome", new Date(), new Date()));
		noteList.add(new Note(2, "note 2", "Spring boot is awesome", new Date(), new Date()));
		
		when(noteRepository.findAll()).thenReturn(noteList);
		
		List<Note> resultList = noteService.getAllNote();

		// test if it same list (equal references) 
		assertThat(noteList, is(resultList));			
	}
	
	@Test
	public void createNoteTest(){
		Note note = new Note(1, "note 1", "Spring boot is awesome", new Date(), new Date());
		
		when(noteRepository.save(any())).thenReturn(note);
		
		Note resultNote = noteService.createNote(note);
		
		// test if it same object (equal references) 
		assertThat(note, is(resultNote));		
	}
	
	@Test
	public void getNoteByIdTest(){
		Optional<Note> note = Optional.of(new Note(1, "note 1","Spring boot is awesome", new Date(), new Date()));
		
		when(noteRepository.findById(anyLong())).thenReturn(note);
		
		Optional<Note> resultNote = noteService.getNoteById(123L);
		
		assertThat(note, is(resultNote));
	}
	
	@Test
	public void updateNoteTest(){
		Note note = new Note(1, "note 1", "Spring boot is awesome", new Date(), new Date());
	
		when(noteRepository.save(any())).thenReturn(note);
				
		Note resultNote = noteService.updateNote(note);
		
		assertThat(note, is(resultNote));
	}
	
	@Test
	public void deleteNoteTest(){
		Note note = new Note(1, "note 1", "Spring boot is awesome", new Date(), new Date());
			
		doNothing().when(noteRepository).delete(any());
		
		noteService.deleteNote(note);
		
		verify(noteRepository, times(1)).delete(any());
	}
}
