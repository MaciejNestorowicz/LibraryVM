package pl.vm.library.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import pl.vm.library.exception.ParameterValidationException;
import pl.vm.library.service.BookService;

import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("pl.vm.library.*")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;

	@Test
	public void trueIsTrue() {
		assertThat(true).isTrue();
	}

	@Test
	public void shouldDeleteBookFromDatabase() {
		int before = bookService.findAll().size();
		int expected = before-1;
		bookService.delete(3L);
		int actual = bookService.findAll().size();
		assertEquals(expected, actual);
	}

	@Test(expected = ParameterValidationException.class)
	public void shouldThrowParameterValidationException() {
			bookService.delete(1L);
	}

	@Test(expected = EntityNotFoundException.class)
	public void shouldThrowEntityNotFoundException() {
			bookService.delete(99L);
	}
}
