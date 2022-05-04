package com.toby.suntory.user.dao;

import com.toby.suntory.user.domain.Level;
import com.toby.suntory.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setLevel(Level.valueOf(rs.getInt("level")));
        user.setLogin(rs.getInt("login"));
        user.setRecommend(rs.getInt("recommend"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    public void add(final User user) {
        this.jdbcTemplate.update(
                "insert into user(id, name, password, level, login, recommend, email) " +
                    "values (?,?,?,?,?,?,?)",
                    user.getId(), user.getName(), user.getPassword(),
                user.getLevel().getValue(), user.getLogin(), user.getRecommend(), user.getEmail());
    }

    public User get(String id) {

        return jdbcTemplate.queryForObject("select * from user where id = ?",
                new Object[]{id}, this.userMapper);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from user");
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from user order by id",
                this.userMapper);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "update user set name = ?, password = ?, level = ?, login = ?, " +
                        "recommend = ? where id = ? ",
                user.getName(), user.getPassword(), user.getLevel().getValue(),
                user.getLogin(), user.getRecommend(), user.getId());
    }
}
