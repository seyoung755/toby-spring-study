package com.toby.suntory.transaction;

import com.toby.suntory.user.dao.UserDao;
import com.toby.suntory.user.domain.Level;
import com.toby.suntory.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class TransactionTest {

    @Autowired
    UserDao userDao;

    @Test
    void TransactionTest() {
        userDao.deleteAll();

        try {
            createUserListWithTrans();
        } catch (Exception e) {

        } finally {
            assertThat(userDao.getCount()).isEqualTo(0);
        }
    }

    @Transactional
    public void createUserListWithTrans() {
        for (int i = 0; i < 10; i++) {
            createUser(i);
        }
        throw new RuntimeException("에러 발생"); // 롤백이 발생하지만 createUser는 독립적인 트랜잭션이므로 괜찮을 것이라 생각
    }


    @Transactional
    public void createUser(int index) {
        User user = new User("test" + index, "박범진", "p1", Level.BASIC, 49, 0, "test@naver.com");
        userDao.add(user);
    }

}
