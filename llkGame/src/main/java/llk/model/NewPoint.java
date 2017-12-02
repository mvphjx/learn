package llk.model;
/*
 * Point类增强版  算法1和11用到了
 */
public class NewPoint implements  Cloneable {
	private int x;
	private int y;
	private int z=0;//方向（0-5）表示方向0表示，刚初始化的点不知道方向，1234上下左右，5表示所有方向都尝试失败
	private int num=2;//可以拐弯的次数

	public NewPoint() {

	}

	public NewPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public NewPoint(int x, int y,int z,int num) {
		this.x = x;
		this.y = y;
		this.z=z;
		this.num=num;
	}
	/*
	 * 克隆操作多个对象，避免操作一个对象  数据不安全(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public NewPoint clone() 
	   {
	      try {
			return (NewPoint)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new NewPoint();
	   }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	

}
