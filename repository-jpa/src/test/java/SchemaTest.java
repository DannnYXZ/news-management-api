import com.epam.lab.configuration.RepositoryConfig;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = RepositoryConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class SchemaTest {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void testAuthor() {
        Author author = new Author();
        author.setName("kek");
        author.setSurname("kekovich");
        entityManager.persist(author);
        entityManager.flush();
    }

    @Test
    public void testSaveNews() {
        News news = new News()
            .setTitle("dssd").setShortText("short")
            .setFullText("full").setCreationDate(new Date())
            .setModificationDate(new Date())
            .setTags(Arrays.asList(new Tag().setName("tag 1"), new Tag().setName("tag 2")))
            .setAuthor(new Author().setName("Salami").setSurname("sure"));
        entityManager.persist(news);
    }

    @Test
    public void findNews() {
        News n = entityManager.find(News.class, 10L);
        int x = 1;
    }

    @Test
    public void query() {
    }
}
