package com.learn.hanjx.concurrent.future;

import com.learn.hanjx.util.date.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Java8 异步编程—CompletableFuture
 *
 *
 */
public class CompletableFutureDemo
{
    //异步任务执行线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args)
    {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            //对一个或多个 Future 合并操作
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                //模拟业务处理...
                try
                {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return System.nanoTime()+"@"+ DateUtils.getTimeJDK8(System.currentTimeMillis());
            }, executorService).exceptionally(throwable -> {
                // 异常 处理
                String msg = throwable.toString();
                System.out.println(throwable.toString());
                return msg;
            });
            futures.add(future);
        }
        List<String> result = futures.stream().map(CompletableFuture::join) //join操作，阻塞等待所有异步操作的结果
                .collect(Collectors.toList());
        result.forEach(System.out::println);
        executorService.shutdown();

    }
}
