package com.learn.hanjx.concurrent.demo;
/*
*向调度程序提示当前线程是否愿意得到它当前的处理器的使用。调度程序可以自由地忽略
*这个提示。Yield是一个启发式的尝试，以改善线程之间的相对进展，否则将过度使用一个中央处理器。
它的使用应结合详细的分析和基准，以确保它实际上有所需的效果。
*/
/**
 * A hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore
 * this hint. Yield is a heuristic attempt to improve relative progression between threads that would otherwise over-utilize a CPU.
 * Its use should be combined with detailed profiling and benchmarking to ensure that it actually has the desired effect.
 */
public class YieldExample
{
   public static void main(String[] args)
   {
      Thread producer = new Producer();
      Thread consumer = new Consumer();

      producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
      consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

      producer.start();
      consumer.start();

      try {
		Thread.sleep(1000);
      } catch (InterruptedException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
      }
      System.out.println("---------------线程调度 one by one--------------------");
      Thread producer1 = new Producer1();
      Thread consumer1 = new Consumer1();

      producer1.setPriority(Thread.MIN_PRIORITY); //Min Priority
      consumer1.setPriority(Thread.MAX_PRIORITY); //Max Priority

      producer1.start();
      consumer1.start();
   }
}

class Producer extends Thread
{
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println("I am Producer : Produced Item " + i);
      }
   }
}

class Consumer extends Thread
{
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println("I am Consumer : Consumed Item " + i);
      }
   }
}


class Producer1 extends Thread
{
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println("I am Producer1 : Produced Item " + i);
         Thread.yield();
      }
   }
}

class Consumer1 extends Thread
{
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println("I am Consumer1 : Consumed Item " + i);
         Thread.yield();
      }
   }
}
