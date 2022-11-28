import com.ppx.JapanApplication;
import com.ppx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: hape
 * @Date: 2022/11/24 14:07
 */
@SpringBootTest(classes = JapanApplication.class)
public class MyTest {

    @Autowired
    private UserService userService;

    @Test
    public void t1() {
        for (int i = 0; i < 2; i++) {
            userService.doo(i,0);
        }
    }
}
