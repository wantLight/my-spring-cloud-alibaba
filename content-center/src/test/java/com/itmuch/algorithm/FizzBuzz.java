package com.itmuch.algorithm;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {

    private int n;
    private Semaphore sNumber=new Semaphore(1);
    private Semaphore sFizz=new Semaphore(0);
    private Semaphore sBuzz=new Semaphore(0);
    private Semaphore sFizzBuzz=new Semaphore(0);
    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3==0&&i%5!=0){
                sFizz.acquire();
                sNumber.release();
                printFizz.run();
            }
        }       
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=0;i<=n;i++){
            if(i%3!=0&&i%5==0){
                sBuzz.acquire();
                printBuzz.run();
                sNumber.release();
            }
        }       
    }
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3==0&&i%5==0){
                sFizzBuzz.acquire();
                printFizzBuzz.run();
                sNumber.release();
            }
        }      
    }
  
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            sNumber.acquire();
            if(i%3==0&&i%5==0){
                sFizzBuzz.release();
            }else if(i%3==0){
                sFizz.release();
            }else if(i%5==0){
                sBuzz.release();
            }else{
                printNumber.accept(i);
                sNumber.release();
            }
        }        
    }

}
