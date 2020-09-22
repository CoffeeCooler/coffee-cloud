package threadtest;

import java.util.concurrent.*;

public class ThreadOrderTest2 {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(5);
        RejectedExecutionHandler hander = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.DAYS, queue,hander);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("：线程id"+Thread.currentThread().getId()+",线程名称"+Thread.currentThread().getName());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            threadPoolExecutor.execute(thread);
            thread.join();
        }


    }
}
