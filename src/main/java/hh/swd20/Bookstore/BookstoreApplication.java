package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Genre;
import hh.swd20.Bookstore.domain.GenreRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository brepository, GenreRepository grepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			grepository.save(new Genre("Nonfiction"));
			grepository.save(new Genre("Fantasy"));
			grepository.save(new Genre("Crime"));
			
			brepository.save(new Book("Norwegian Wood", "Haruki Murakami", 1987, "9780307744661", 7.60, grepository.findByName("Nonfiction").get(0)));
			brepository.save(new Book("Homo Deus", "Yuval Noah Harari", 2017, "9781784703936", 10.50, grepository.findByName("Fantasy").get(0)));	
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

