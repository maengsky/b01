package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Todo todo = Todo.builder()
                    .due_date(LocalDate.now())
                    .title("title" + i)
                    .writer("user" + (i % 10))
                    .build();
        Todo result = todoRepository.save(todo);
        log.info("TNO: " + result.getTno());
        });

    }

    @Test
    public void testSelect() {
        Optional<Todo> result= todoRepository.findById(5);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    @Test
    public void testSelectAll() {
        List<Todo> result= todoRepository.findAll();

        result.forEach(item -> log.info(item));
    }

    @Test
    public void testUpdate() {
        Optional<Todo> result= todoRepository.findById(5);
        Todo todo = result.orElseThrow();
    }

    @Test
    public void queryMethodTest1() {

    }
}
