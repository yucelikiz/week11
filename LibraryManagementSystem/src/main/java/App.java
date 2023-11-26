import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Author author = new Author();
        author.setName("Serkan Karaismailoğlu");
        author.setBirthDate(LocalDate.of(1981, 10, 21));
        author.setCountry("Türkiye");
        entityManager.persist(author);

        Publisher publisher = new Publisher();
        publisher.setName("Ortapia Yayınları");
        publisher.setEstablishmentYear(2021);
        publisher.setAddress("https://ortapia.com");
        entityManager.persist(publisher);

        Book book = new Book();
        book.setName("Dünyanın En Yalnız Beyni");
        book.setPublicationYear(2023);
        book.setStock(23);
        book.setAuthor(author);
        book.setPublisher(publisher);
        entityManager.persist(book);

        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBorrowerName("Yücel");
        bookBorrowing.setBorrowingDate(LocalDate.now());
        bookBorrowing.setReturnDate(LocalDate.now());
        bookBorrowing.setBook(book);
        entityManager.persist(bookBorrowing);

        Category category = new Category();
        category.setName("Popüler Bilim");
        category.setDescription("Güncel bilimsel konular ile ilgili kitaplar.");
        entityManager.persist(category);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        book.setCategoryList(categoryList);
        entityManager.persist(book);

        transaction.commit();
    }
}
