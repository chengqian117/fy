package com.softi.subwayMap;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    private String cai;
    private String mi;



    public static void liuda(FutureTask<String> fu) throws InterruptedException {
        Thread.sleep(2000L);
        String cai="ok";
        while (!fu.isDone()){
            Thread.sleep(1000L);
            System.out.println("还没到");
        }
        try {
            String mi=fu.get();
            if(cai.equals("ok")&&mi.equals("ok")){
                System.out.println("好了");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Callable<String> ca=new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "ok";
            }
        };
        FutureTask<String> fu=new FutureTask<String>(ca);

        new Thread(fu).start();
        try {
            FutureTest.liuda(fu);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
