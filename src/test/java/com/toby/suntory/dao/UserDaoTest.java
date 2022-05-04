package com.toby.suntory.dao;

import com.toby.suntory.user.dao.DaoFactory;
import com.toby.suntory.user.dao.UserDao;
import com.toby.suntory.user.dao.UserDaoJdbc;
import com.toby.suntory.user.domain.Level;
import com.toby.suntory.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
@DirtiesContext // 테스트 코드에서 애플리케이션의 컨텍스트를 수정하겠다는 표시
class UserDaoTest {
    // 스프링이 context를 만들어 주입한다. 각 test 오브젝트가 동일한 context를 사용한다.
    @Autowired
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        user1 = new User("user1", "유저1", "pw1", Level.BASIC, 1, 0, "test@naver.com");
        user2 = new User("user2", "유저2", "pw2", Level.SILVER, 55, 10, "test@gmail.com");
        user3 = new User("user3", "유저3", "pw3", Level.GOLD, 100, 40, "seyoung7555@naver.com");
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/toby", "suntory", "tksxhfl", true);
        dao = new UserDaoJdbc(dataSource);
    }

    @Test
    void addAndGet() throws SQLException {
        assertThat(dao.getCount()).isZero();

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);
    }

    @Test
    void count() throws SQLException {
        assertThat(dao.getCount()).isZero();

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test
    void getUserFailure() throws SQLException {
        assertThat(dao.getCount()).isZero();
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }

    @Test
    void getAll() {
        List<User> users0 = dao.getAll();
        assertThat(users0).isEmpty();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1).hasSize(1);
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2).hasSize(2);
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3).hasSize(3);
        checkSameUser(user1, users3.get(0));
        checkSameUser(user2, users3.get(1));
        checkSameUser(user3, users3.get(2));
    }

    @Test
    void update() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2); // 수정하지 않을 사용자

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);

        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
        assertThat(user1.getEmail()).isEqualTo(user2.getEmail());
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }
}
