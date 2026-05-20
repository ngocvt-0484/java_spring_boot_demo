package ngocvt.local.ngocvt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
public class TestController {
    private final JdbcTemplate jdbcTemplate;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("test")
    public String test(){
        String sql = "CREATE TABLE IF NOT EXISTS test_table(id INT auto_increment primary key, name VARCHAR(255) NOT NULL)";

        jdbcTemplate.execute(sql);
        return "Da tao thanh cong";
    }

    public static class BaseController {
    }
}
