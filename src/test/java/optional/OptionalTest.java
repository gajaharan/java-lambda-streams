package optional;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class OptionalTest {

    @Test
    public void shouldCreateEmptyOptional(){
        Optional<String> optional = Optional.empty();
        assertThat(optional.isPresent(),equalTo(false));
    }

    @Test
    public void shouldReturnBookName(){
        Optional<Book> book = Optional8.getBook();
        String bookName = book.map(Book::getName).orElse("default");
        assertThat(bookName,equalTo("Experience Java 8"));
    }

    @Test
    public void shouldReturnBookAuthor(){
        Optional<Book> book = Optional8.getBookWithAuthor();
        String authorName = book.flatMap(Book::getAuthor).orElse("default");
        assertThat(authorName,equalTo("Stijn De Mulder"));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoElementException(){
        //Optional<Book> book = Optional8.getBook();
        Optional<Book> book = Optional.ofNullable(null);
        book.get().getAuthor();
    }

    @Test(expected = MyCustomException.class)
    public void shouldThrowOptionalEmptyException() throws MyCustomException{
        //Optional<Book> book = Optional8.getBook();
        //book.get().getAuthor(); // getAuthor.or.....
        Optional<Book> book = Optional.empty();
        book.map(Book::getName).orElseThrow(() -> new MyCustomException());

    }

}