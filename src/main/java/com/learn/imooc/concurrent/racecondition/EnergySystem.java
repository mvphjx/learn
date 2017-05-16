package com.learn.imooc.concurrent.racecondition;

/**
 * 能量系统
 * @author han
 *
 */
public class EnergySystem {
	
	 private final double[] energyBoxes;
	 //作为标示 用来同步？？
	 private final Object lockObj = new Object();
	 
	 /**
	  * 
	  * @param n    能量盒子数
	  * @param initialEnergy 能量总数
	  */
	 public EnergySystem(int n, double initialEnergy){
		 energyBoxes = new double[n];
		 for (int i = 0; i < energyBoxes.length; i++)
			 energyBoxes[i] = initialEnergy;
	 }
	 /**
	  * 能量转移
	  * @param from 发送者
	  * @param to  接受者
	  * @param amount 数量
	  */
	 public void transfer(int from, int to, double amount){
		 
		 synchronized(lockObj){
			 
			 if (energyBoxes[from] < amount)
				 return;
			//whileѭ������֤����������ʱ���񶼻ᱻ�����赲
			 //���Ǽ�����CPU��Դ
			 while (energyBoxes[from] < amount){
				 try {
					//����������, ����ǰ�̷߳���Wait Set
					lockObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
			 
			 
			 System.out.print(Thread.currentThread().getName());
			 energyBoxes[from] -= amount;
			// System.out.printf("��%dת��%10.2f��λ������%d", from, amount, to);
			 System.out.printf("from %1$s to %3$s  转移数量：%2$s；", from, amount, to);
			 energyBoxes[to] += amount;
			// System.out.printf(" �����ܺͣ�%10.2f%n", getTotalEnergies());
			 System.out.printf("能量总数%1$s \n", getTotalEnergies());
			//唤醒其他线程
			 lockObj.notifyAll();
		 }
		 
	 }
	 
/**
 * @return  能量总数
 */
	 public double getTotalEnergies(){
		 double sum = 0;
		 for (double amount : energyBoxes)
			 sum += amount;
		 return sum;
	 }
	 
/**
 * 
 * @return  能量盒子（线程）总数
 */
	 public  int getBoxAmount(){
		 return energyBoxes.length;
	 }
	 
}
