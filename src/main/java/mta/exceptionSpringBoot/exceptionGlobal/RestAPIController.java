package mta.exceptionSpringBoot.exceptionGlobal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1")
public class RestAPIController {
    private List<Todo> todoList;
    @PostConstruct
    public void init() {
        // Tạo các đối tượng Todo từ 0 đến 10
        todoList = IntStream.range(0, 10)
                .mapToObj(i -> new Todo("title-" + i, "detail-" + i))
                .collect(Collectors.toList());
    }
    /*   http://localhost:8081/api/v1/todo/11
   ==> Phát sinh ngoại lệ IndexOutOfBoundsException ví đối tượng 11 không tồn tại     */
    @GetMapping("/todo/{todoId}")
    public Todo getTodo(@PathVariable(name = "todoId") Integer todoId) throws SQLException {

        return todoList.get(todoId);
    }
    /*    http://localhost:8081/api/v1/data/0      */
    @GetMapping("/data/{todoId}")
    public String getData(@PathVariable(name = "todoId") Integer todoId) throws SQLException {
        if (todoId==0)  throw new SQLException( " " );
        return "done";
    }
}
