package com.learn.imooc.concurrent.base;

public class Stage extends Thread {
	
	
	public void run(){
		ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
		ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
		// Runnable >> Thread
		Thread armyOfSui = new Thread(armyTaskOfSuiDynasty, "隋朝");
		Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "农民");
		//start
		armyOfSui.start();
		armyOfRevolt.start();
		
		//stage sleep
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("半路杀出个程咬金");
		Thread mrCheng = new KeyPersonThread();
		mrCheng.setName("程咬金");
		System.out.println("程咬金闪亮登场");
		
		//army stop
		armyTaskOfSuiDynasty.keeprunning=false;
		armyTaskOfRevolt.keeprunning=false;
		
		//stage sleep
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//key person
		mrCheng.start();
		try{
			//TODO
			mrCheng.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("战争结束，剧终");
	}

	public static void main(String[] args) {
		new Stage().start();

	}

}
