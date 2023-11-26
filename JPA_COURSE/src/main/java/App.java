import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("market");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        //Supplier Ekleme
        Supplier supplier = new Supplier();
        supplier.setAddress("Adres");
        supplier.setCompany("Patika");
        supplier.setContact("500122122");
        supplier.setMail("info@patika.dev");
        supplier.setPerson("Yücel İKİZ");
        entityManager.persist(supplier);

        //Kategori Ekleme
        Category category = new Category();
        category.setName("Telefonlar");
        entityManager.persist(category);

        //Code Ekleme
        Code code  = new Code();
        code.setGroup("11123");
        code.setSerial("44456");
        entityManager.persist(code);

        //Ürün Ekleme
        Product product = new Product();
        product.setName("Iphone 15 Pro");
        product.setPrice(1234);
        product.setStock(100);
        product.setCode(code);
        product.setSupplier(supplier);
        product.setCategory(category);
        entityManager.persist(product);

        //Renk Ekleme
        Color blue = new Color("Blue");
        Color red = new Color("Red");
        Color yellow = new Color("Yellow");

        entityManager.persist(blue);
        entityManager.persist(red);
        entityManager.persist(yellow);

        Product product1 = entityManager.find(Product.class, 1);
        List<Color> colorList = new ArrayList<>();
        colorList.add(blue);
        colorList.add(red);
        product1.setColorList(colorList);

        entityManager.persist(product1);

        transaction.commit();

    }
}
