/**
 * 
 */
package com.example.easynotes.integration.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.easynotes.model.Note;

/**
 * @author e1077874
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EasyNotesintegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Ignore
	@Test
	public void createNoteIntegrationTest(){
		Note note = new Note("note 1", "Spring boot is awesome");
		
		Note responseNote = restTemplate.withBasicAuth("ir", "ir").postForObject("/api/notes", note , Note.class);
		
		assertThat(note.getTitle(), is(responseNote.getTitle()));
		assertThat(note.getContent(), is(responseNote.getContent()));
	}
	
	@Test
	public void getNoteByIdIntegrationTest(){		
		ResponseEntity<Note> responseNote = restTemplate.withBasicAuth("ir", "ir").getForEntity("/api/notes/2", Note.class);
		assertThat("my updated notes", is(responseNote.getBody().getTitle()));
	}
	
	/*@Test
	public void getAllNotesIntegrationTest(){		
		List<Note> responseNote = (List<Note>) restTemplate.withBasicAuth("ir", "ir").getForObject("/api/notes", Note.class);
		
		System.out.println(responseNote);
				//getForEntity("/api/notes", Note.class);
		//assertThat("my updated notes", is(responseNote.getBody().getTitle()));
	}*/
}
