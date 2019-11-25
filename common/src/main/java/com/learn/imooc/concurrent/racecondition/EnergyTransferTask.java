package com.learn.imooc.concurrent.racecondition;
/**
 * 能量转化任务
 * @author han
 *
 */

public class EnergyTransferTask implements Runnable{

	//能量系统
	private EnergySystem energySystem;
	//发送者
	private int fromBox;
	//能量总数
	private double maxAmount;
	//延迟
	private int DELAY = 10;
	
	public EnergyTransferTask(EnergySystem energySystem, int from, double max){
		this.energySystem = energySystem;
		this.fromBox = from;
		this.maxAmount = max;
	}
	
	public void run() {
		try{
			while (true){
				int toBox = (int) (energySystem.getBoxAmount()* Math.random());
				double amount = maxAmount * Math.random();
				energySystem.transfer(fromBox, toBox, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
