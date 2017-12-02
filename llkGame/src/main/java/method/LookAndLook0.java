package method;

import java.util.Stack;

import llk.model.Point;

public class LookAndLook0 {
	

	 
	private Stack<Point> s;
	private int[][] v;
	private Point a;
	private Point b;
	private int x, y, dx, dy, i, j;
	private static Long count=0L;

	public LookAndLook0(int[][] v) {
		this.v = v;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public void setValue(int x, int y, int value) {
		v[x][y] = value;
	}

	// 找一对解.返回在V数组中的坐标
	public boolean search() {
		s = new Stack<Point>();
		x = v.length;
		y = v[0].length;

		for (i = 0; i < x; i++) {
			for (j = 0; j < y; j++) {
				if (v[i][j] != 0) {// 0 是空白区
					dx = i;
					dy = j;

					//if (up(3) || left(3))
					if (up(3) || down(3) || left(3) || right(3)){
						//System.out.println(count);
						return true;
					}
					
						

				}
			}
		}

		return false;
	}

	// 上
	private boolean up(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(3);// 返回3 = 可深入 .返回2 = 碰障碍 0=越界  1=ok
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (left(c))
				return true;
		}
		peek();
		while (true) {
			k = $(4);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (right(c))
				return true;
		}
		pop();
		return false;
	}

	// 下
	private boolean down(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(3);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (left(c))
				return true;
		}
		peek();
		while (true) {
			k = $(4);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (right(c))
				return true;
		}
		pop();
		return false;
	}

	// 左
	private boolean left(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(1);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (up(c))
				return true;
		}
		peek();
		while (true) {
			k = $(2);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (down(c))
				return true;
		}
		pop();
		return false;
	}

	// 右
	private boolean right(int c) {
		if (--c < 0)
			return false;
		int k;
		push();
		while (true) {
			k = $(1);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (up(c))
				return true;
		}
		peek();
		while (true) {
			k = $(2);
			if (k == 0)
				break;
			if (k == 1)
				return true;
			if (k == 2)
				break;
			if (down(c))
				return true;
		}
		pop();
		return false;
	}

	// 走一步 XXX( 1,2,3,4 上下左右 )
	private int $(int xxx) {
		count++;
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
		}

		if (dx < 0 || dx >= x || dy < 0 || dy >= y) { // 越界
			return 0;
		}

		if (v[i][j] == v[dx][dy]) { // 找到一对
			a = new Point(i, j);
			b = new Point(dx, dy);
			return 1;
		}
		// 返回3 = 可深入 .返回2 = 碰障碍
		return v[dx][dy] != 0 ? 2 : 3;
	}

	// =================

	private void pop() {
		Point p = s.pop();
		dx = p.getX();
		dy = p.getY();
	}

	private void peek() {
		Point p = s.peek();
		dx = p.getX();
		dy = p.getY();
	}

	private void push() {
		s.push(new Point(dx, dy));
	}
	 public static void main(String[] args) {
		 int[][] a = {  { 6, 1, 2, 1, 0, 0}, 
				 		{ 0, 4, 5, 0, 0, 0 },
				 		{ 6, 0, 6, 0, 0, 0},
				 		{ 0, 2, 5, 6, 0, 4 } };
	 LookAndLook0 df = new LookAndLook0(a); 
	 while (df.search()) {
	 //System.out.println(df.s.size());
	 System.out.print(df.getA().getX() + "," + df.getA().getY()+" 	and	 ");
	 System.out.println(df.getB().getX() + "," + df.getB().getY());
	 df.setValue(df.getA().getX(), df.getA().getY(), 0);
	 df.setValue(df.getB().getX(), df.getB().getY(), 0);
	 System.out.println("count:"+count);
	 count=0L;
	 //System.out.println(df.s.size());
	 } }

	public Long getCount() {
		return count;
	}

	 

}
