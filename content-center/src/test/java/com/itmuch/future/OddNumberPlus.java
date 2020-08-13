package com.itmuch.future;

import java.util.function.Supplier;

public class OddNumberPlus implements Supplier<Integer> {

    @Override
    public Integer get() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1+3+5+7+9;
    }
}
