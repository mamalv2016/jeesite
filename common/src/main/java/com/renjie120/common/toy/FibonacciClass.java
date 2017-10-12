package com.renjie120.common.toy;

/**
 * 实现斐波那契数列的一个递归算法。
 */
public class FibonacciClass
{
    public static int calcFibonacci(int c){
        if(c<2){
            return c;
        }else{
            return calcFibonacci(c-1)+calcFibonacci(c-2);
        }
    }

    /**
     * 使用尾递归方式进行计算的方法.
     * @param n
     * @param acc1
     * @param acc2
     * @return
     */
    public static int calcFibonacci2(int n, int acc1, int acc2)
    {
        if (n == 0) return acc1;
        return calcFibonacci2(n - 1, acc2, acc1 + acc2);
    }

    //1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368
    public static void main(String[] args){
        int calTime = 50;
        long start =System.currentTimeMillis();
        int ans = FibonacciClass.calcFibonacci(calTime);
        long end =System.currentTimeMillis();
        System.out.println((end-start)+":"+ans);

        start =System.currentTimeMillis();
        int ans2 = FibonacciClass.calcFibonacci2(calTime,0,1);
        end =System.currentTimeMillis();
        System.out.println((end-start)+":"+ans2);
    }
}

