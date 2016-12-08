package com.learn.imooc.concurrent.racecondition;

/**
 * ���������ϵͳ
 * ��ѭ�����غ㶨�ɣ�
 * ��������ƾ�մ������ʧ��ֻ���һ��ת�Ƶ���һ��
 */
public class EnergySystem {
	
	//�������ӣ���������ĵط�
	 private final double[] energyBoxes;
	 private final Object lockObj = new Object();
	 
	 /**
	  * 
	  * @param n    �������ӵ�����
	  * @param initialEnergy ÿ���������ӳ�ʼ���е�����ֵ
	  */
	 public EnergySystem(int n, double initialEnergy){
		 energyBoxes = new double[n];
		 for (int i = 0; i < energyBoxes.length; i++)
			 energyBoxes[i] = initialEnergy;
	 }
	 
	 /**
	  * ������ת�ƣ���һ�����ӵ���һ������
	  * @param from ����Դ
	  * @param to     �����յ� 
	  * @param amount ����ֵ
	  */
	 public void transfer(int from, int to, double amount){
		 
		 synchronized(lockObj){
			 
//			 if (energyBoxes[from] < amount)
//				 return;
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
			 System.out.printf("��%dת��%10.2f��λ������%d", from, amount, to);
			 energyBoxes[to] += amount;
			 System.out.printf(" �����ܺͣ�%10.2f%n", getTotalEnergies());
			//����������lockObj�����ϵȴ���߳�
			 lockObj.notifyAll();
		 }
		 
	 }
	 
	 /**
	  * ��ȡ��������������ܺ� 
	  */
	 public double getTotalEnergies(){
		 double sum = 0;
		 for (double amount : energyBoxes)
			 sum += amount;
		 return sum;
	 }
	 
	 /**
	  * �����������ӵĳ���
	  */
	 public  int getBoxAmount(){
		 return energyBoxes.length;
	 }
	 
}
