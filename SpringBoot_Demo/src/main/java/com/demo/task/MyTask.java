package com.demo.task;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
@Component
@Log4j
public class MyTask {

    @Async("taskExecutor")
    public void task(String str){
        log.debug("str；"+str);
    }

    @Async("taskExecutor")
    public ListenableFuture<String> sayHello(String name) {
        String res = name + ":Hello World!";
        return new AsyncResult<>(res);
    }
}
