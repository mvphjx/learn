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
 *
 */
public class LookAndLook11 implements LookBasicIn{
	private  int[][] v;
	private  Stack<NewPoint> s= new Stack<>(); ;
	private  int Y=6;
	private  int X=4;
	private  NewPoint pp;
	private  int value=6;
	private  Long count=0L;
	public LookAndLook11(int[][] v){
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
		 LookAndLook11 look1=new LookAndLook11(a);
		 while(true){
			 List<Point> list=look1.search();
			 if(list==null)
				 break;
			 System.out.println(list);
		 }
	}
	/**
	 * 主方法递归
	 */
	private boolean getPoint(NewPoint p){
		boolean bool=false;
		int i=1;
		while(true){
			if(pp!=null){
				return true;//匹配成功
			}
			NewPoint np=p.clone();
			np.setZ(p.getZ()+i);
			if(np.getZ()>=5){
				return false;
			}
			NewPoint getp=goNext(np);
			if(getp!=null){//继续新的点
				getPoint(getp);
				if(pp==null)
					s.pop();
			}
			i++;//继续这个点
		}
	}
	/*
	 * 是否还能继续走
	 */
	private NewPoint goNext(NewPoint p) {
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
		if(s.size()!=0){//判断，不让往回走
			int oldx=s.peek().getX();
	        int oldy=s.peek().getY();
	        isBack=(oldx==dx&&oldy==dy);
		}
        
		if (dx < 0 || dx >= X || dy < 0 || dy >= Y||isBack) { // 越界
			return null;
		}
		if (v[dx][dy] ==value ) { // 值相同，并且拐弯次数（折线次数）不超过2次   找到一对
			if(push(np))
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
	/*
	 * 根据拐弯次数，判断是否让入栈
	 */
	private boolean push(NewPoint np){
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
						LookApi.isRight(v, new Point(i,j), new Point(pp.getX(),pp.getY()));
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
