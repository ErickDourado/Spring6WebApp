package guru.springframework.spring7webapp.bootstrap;

import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEjb = new Book();
        noEjb.setTitle("J2EE Development without EJB");
        noEjb.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEjbSaved = bookRepository.save(noEjb);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEjbSaved);

//      Não é necessário salvar novamente, o JPA/Hibernate faz isso automaticamente
//      Mas talvez seja bom manter redundância para uma outra pessoa que leia o código entender melhor

//      authorRepository.save(ericSaved);
//      authorRepository.save(rodSaved);

        Publisher publisherOne = new Publisher();
        publisherOne.setPublisherName("Publisher name");
        publisherOne.setAddress("123 Main St");
        publisherOne.setCity("Springfield");
        publisherOne.setState("Sao Paulo");
        publisherOne.setZipCode("01587682");

        Publisher publisherTwo = new Publisher();
        publisherOne.setPublisherName("Publisher two name");
        publisherOne.setAddress("456 Main St");
        publisherOne.setCity("Springfield");
        publisherOne.setState("Rio de Janeiro");
        publisherOne.setZipCode("06879054");

        publisherRepository.save(publisherOne);
        publisherRepository.save(publisherTwo);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }

}
