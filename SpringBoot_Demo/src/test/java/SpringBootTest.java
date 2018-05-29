import com.alibaba.fastjson.JSON;
import com.common.model.User;
import com.demo.service.UserService;
import com.demo.service.UserService123456;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.server.Main;

import java.util.HashMap;
import java.util.List;

//@RunWith(SpringRunner.class)
//@org.springframework.boot.test.context.SpringBootTest(classes = Main.class)
@Log4j
public class SpringBootTest {
    @Autowired
    private UserService123456 userService;
    @Autowired
    private UserService uService;

    //@Test
    public void add(){
        User user = new User();
        user.setUsername("tiger");
        user.setPasswd("123456");
        user.setRole("user");
        user.setComments("测试专用");
        userService.add(user);
    }
    //@Test
   //@Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public void delete(){
        userService.delete(5+"",5);
        throw new RuntimeException("测试异常。。。");
    }
    //@Test
    public void update(){
        User user = new User();
        user.setId(5);
        user.setUsername("tiger.z");
        user.setPasswd("123456");
        user.setRole("user");
        user.setComments("测试专用");
        //userService.modify(user);
        userService.modify(5+"",5,"tiger.z");
    }
    //@Test
    public void selectPage(){
        List<User> users = userService.selectPage(1,2);
        PageInfo<User> user = new PageInfo<>(users);
        Page<User> page= (Page<User>) users;
        log.info("PageInfo:"+ JSON.toJSONString(user)+"page:"+JSON.toJSONString(page));
    }
    //@Test
    public void testJdk8(){
        List<User> users = userService.selectPage(1,10);
        users.forEach(user->System.out.println(user.getPasswd()));
        users.forEach(user->user.getUsername());
    }
    //@Test
    public void selectById(){
        User user =userService.selectById(5+"",5);
        log.debug("user:"+user);
    }

    //@Test
    public void select(){
        HashMap<String,Object> hashMap = new HashMap();
        PageInfo<User> user =uService.page(hashMap);
        log.debug("user:"+user);
    }
}
