package com.demo.cotroller;
import com.alibaba.fastjson.JSON;
import com.common.model.User;
import com.common.util.RedisUtil;
import com.demo.service.UserService;
import com.demo.task.MyTask;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
@RestController
@Log4j
public class IndexController {
    @Autowired
    private MyTask myTask;
    @Autowired
    private UserService uService;
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(value = "user/indexPage")
    public String  indexPage() throws InterruptedException, ExecutionException, TimeoutException {
        /*for(int i = 0;i<10;i++){
            myTask.task(String.valueOf(i));
            //log.debug(myTask.sayHello(i+"").get(10, TimeUnit.SECONDS));
        }*/
        HashMap<String,Object> hashMap = new HashMap();
        PageInfo<User> user =uService.page(hashMap);
        redisUtil.set("zhanghu","6666666666666");
        log.debug("user:"+user);
        return JSON.toJSONString(user);
    }
}
