package com.exercise;

import com.exercise.model.User;
import com.exercise.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExerciseApplicationTests {


    @Autowired
    private UserRepository userRepository;

    Optional<User> user;

    @Value("${initializer.user.name}")
    private String name;
    @Value("${initializer.user.userId}")
    private Long userId;

    @Before
    public void setUp() {
        user = userRepository.findById(1L);
    }

    @Test
    public void contextLoads() {
        assertEquals(user.get().getName(), this.name);
        assertEquals(user.get().getId(), this.userId);
    }

}
