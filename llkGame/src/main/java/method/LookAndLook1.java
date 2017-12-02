package method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import llk.model.NewPoint;
import llk.model.Point;



/**
 * 尝试用递归写  连连看查找点的算法
 * @author Administrator
 *
 *思路比较简单  参数是   newpoint对象   xy表示数组中的位置   z表示方向 	 num表示可以拐弯的次数
 *一个点按照方向1-4递归，如果发现了新的点，就把点入栈，递归新的点；如果新的点匹配失败，就出栈一个点
 *缺陷   感觉 num，z两个参数  和  栈  有些地方功能重复，影响效率和可读性
 */
public class LookAndLook1 implements LookBasicIn{
	private static int[][] v;
	private static NewPoint px;
	private static Stack<NewPoint> s=new Stack<NewPoint>(); ;
	private static int Y=6;
	private static int X=4;
	private static NewPoint pp;
	private static int value=6;
	private static Long count=0L;
	public LookAndLook1(){
		
	}
	public LookAndLook1(int[][] v){
		this.v=v;
		 X=v.length;
		 Y=v[0].length;
	}
	public  static void main(String [] args){
		 int[][] a = {  { 6, 1, 2, 1, 0, 6}, 
			 			{ 0, 4, 5, 0, 6, 0 },
			 			{ 0, 0, 0, 0, 0, 6 },
			 			{ 3, 2, 5, 3, 0, 4 } };
		 int[][] b= { { 0, 0, 0, 0, 0, 0}, 
		 			  { 0, 4, 5, 0, 0, 0 },
		 			  { 0, 0, 0, 0, 0, 0 },
		 			  { 0, 0, 5, 0, 0, 4 } };
		 LookAndLook1 look1=new LookAndLook1(a);
		 while(true){
			 List<Point> list=look1.search();
			 System.out.println(list);
			 if(list==null)
				 break;
		 }
	}
	/**
	 * 主方法递归
	 */
	private static boolean getPoint(NewPoint p){
		if(pp!=null){
			return true;//匹配成功
		}
		boolean bool=false;
		NewPoint np=p.clone();
		np.setZ(p.getZ()+1);
		if(np.getZ()>=5){
			return false;
		}
		NewPoint getp=goNext(np);
		if(getp!=null){//继续新的点
			getPoint(getp);
			if(pp==null)
				s.pop();
		}
		getPoint(np);//继续这个点
		bool=(pp!=null);
		return bool;
	}
	/*
	 * 是否还能继续走
	 */
	private static NewPoint goNext(NewPoint p) {
		count++;
		boolean bool = false;
		NewPoint np=p.clone();
		int xxx=p.getZ();
		int dx=p.getX();
		int dy=p.getY();
		switch (xxx) {
		case 1:
			dx--;
			break;
		case 2:
			dx++;
			break;
		case 3:
			dy--;
			break;
		case 4:
			dy++;
			break;
		default: return null;
		}
		boolean isBack=false;
		if(s.size()!=0){
			int oldx=s.peek().getX();
	        int oldy=s.peek().getY();
	        isBack=(oldx==dx&&oldy==dy);
		}
        
		if (dx < 0 || dx >= X || dy < 0 || dy >= Y||isBack) { // 越界
			return null;
		}
		if (v[dx][dy] ==value ) { // 找到一对
			if(!push(np))
				return null;
			pp= new NewPoint(dx,dy);
			return null;
		}
		if(v[dx][dy] !=0){//碰壁
			return null;
		}
		if(!push(np))
			return null;
		return new NewPoint(dx,dy);	
	}
	private static boolean push(NewPoint np){
		if(s.size()!=0){
			int numZ=s.peek().getZ();
	        if(numZ!=np.getZ())
	        	np.setNum(s.peek().getNum()-1);
	        else
	            np.setNum(s.peek().getNum());
		}
		//继续
		if(np.getNum()<0)
			return false;
		s.push(np);
		return true;
	}
	public List<Point> search(){
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (v[i][j] != 0) {
					s = new Stack<NewPoint>();
					NewPoint p = new NewPoint(i, j);
					value = v[i][j];
					//count = 0;
					if (getPoint(p)) {
						//System.out.print(i + "," + j + "     " + pp.getX()+ "," + pp.getY());
						//System.out.println("   " + count);
						v[i][j]=0;
						v[ pp.getX()][pp.getY()]=0;
						List<Point> list=new ArrayList<Point>();
						list.add(new Point(i,j));
						list.add(new Point(pp.getX(),pp.getY()));
						pp = null;//清空全局变量，以便下次找到放入
						return list;
					} else {
						//System.out.println("lost:" + i + "," + j + "   "+ count);
					}

				}

			}
		}
		return null;
	}
	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
