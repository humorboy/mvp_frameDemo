package com.humorboy.practice.ui.activity;

import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.humorboy.practice.R;
import com.humorboy.practice.ui.base.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTestActivity extends BaseActivity {
    private Handler mHandler;
    private TextView log_view,index_view;
    private static String classIndex = "五种线程池:1,newFixedThreadPool(固定线程数量) 2,newCachedThreadPool(根据实际情况调整线程池中线程的数量)" +
            "3,newSingleThreadExecutor(只有一个线程的线程池)4,(定时或周期性执行某任务的线程池)5,newSingleThreadScheduledExecutor()";
    private ExecutorService fixedThreadPool,singleThreadPool,cachedThreadPool;
    private ScheduledExecutorService scheduledThreadPool ,singleThreadScheduledPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_thread_pool_test);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initData() {
        initThreadPool();
    }

    @Override
    public void initViews(Bundle bundle) {
        mHandler = new Handler(getMainLooper());
        log_view  = (TextView) findViewById(R.id.log_view);
        index_view  = (TextView) findViewById(R.id.index_view);
        index_view.setText(classIndex);
        testFixedThreadPool();
//        testCachedThreadPool();
//        testScheduledThreadPool();
    }

    @Override
    public void setHeader() {
        super.setHeader();
        title.setText(""+getClass().getSimpleName());
    }

    private void initThreadPool() {
        //固定线程数量的线程池,该线程池可以控制线程的最大并发数
        fixedThreadPool = Executors.newFixedThreadPool(5);
        //单任务的线程池
        singleThreadPool = Executors.newSingleThreadExecutor();
        cachedThreadPool = Executors.newCachedThreadPool();
        scheduledThreadPool = Executors.newScheduledThreadPool(5);
        singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
    }

    private void testFixedThreadPool() {
        updateLogView("**固定任务数量线程池**"+"\n",log_view);
        for (int i = 1; i <= 50; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String message = "线程："+threadName+",正在执行第" + index + "个任务"+"\n";
                    updateLogView(message,log_view);
                    Log.d(TAG, message);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }



    private void testCachedThreadPool() {
        updateLogView("**无固定任务数量线程池**"+"\n",log_view);
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String message = "线程：" + threadName + ",正在执行第" + index + "个任务"+"\n";
//                    updateLogView(message,log_view);
                    Log.v(TAG, message);
                    try {
//                        long time = index * 500;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void testScheduledThreadPool() {
        updateLogView("**延迟或周期性线程池**"+"\n",log_view);
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 2, TimeUnit.SECONDS);
        //延迟5秒后，每隔3秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String message = "线程：" + threadName + ",正在执行周期性任务,3s执行一次"+"\n";
                updateLogView(message,log_view);
                Log.v(TAG, message);
            }
        }, 5, 3, TimeUnit.SECONDS);
    }

    private void updateLogView(final String text, final TextView tv){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(tv.getText()+""+text);
            }
        });
    }

    @Override
    public void initListeners() {

    }

}
