package llk;

import cn.com.beans.MethodCountInfo;
import llk.model.Point;
import method.*;
import win32.Mouse;
import win32.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 代码需要重构  //TODO  2017年12月31日13:57:20
 *  首先规定单一职责，不同算法，使用开闭原则。面向接口编程
 *
 *  1 win32函数获取图像
 *  2 图像抽象为java对象
 *  3 使用算法，计算出路径
 *  4 win32函数  进行点击
 *  5 额外功能，记录算法相关参数  例如运行时间，时间复杂度等
 *
 */
public class LookMain {
	private static Config config;
	private static Long count=0L;
	int click=0;
	private static String filePath="";

	// 秒杀
	public static void ms() throws FileNotFoundException, IOException {
		//读取配置文件
		if(config==null){
			config = Config.createConfig();
		}
		int hwnd = Window.getHwnd("QQ游戏 - 连连看角色版");// 取游戏窗口句柄
		if (hwnd <= 0) {
			System.out.println("获取游戏窗口句柄 失败！");
			return;
		}
		String save=config.getProperty("save");
		NewBMP bmp =null;
		if("true".equalsIgnoreCase(save)){
			File  f =getFile(hwnd);
			if(f==null)
				return;
			/*
			 * 解析位图  遍历比较像素密度  
			 * 之前的BMP   有很小的几率不能鉴定相似的图片块，换了新的方法BufferedImage
			 */
			//BMP bmp = new BMP(f.getAbsolutePath());
			bmp = new NewBMP(f.getAbsolutePath());
		}else{
			/*不保存截图，把图直接放在缓存中处理
			 * 解析位图  遍历比较像素密度  
			 */
			bmp = new NewBMP(Window.getImage(hwnd));
		}
		try {
			// 游戏截图( 游戏窗口不能被其它窗口遮挡 .否则截图会截到其它窗口 )
			int blank;// 这个值是空白区的颜色值
			// QQ连连看是 11 * 19 矩阵
			int[][] n = new int[11][19];
			Set<Fangkuai> set = new HashSet<Fangkuai>();
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 19; j++) {
					// 截取一小块 15 *15 的数据
					int x = 17 + j * 31;
					int y = 187 + i * 35;
					Fangkuai fk = new Fangkuai(bmp.getData(x, y, 15, 15));
					blank=bmp.getColor(62, 581);
					if (bmp.getColor(x, y) != blank&&bmp.getColor(x+1, y+1) != blank) {
						set.add(fk);
						int type=fk.hashCode();
						if (type != 0) {
							n[i][j] = type;
						}
					}
				}
			}			
			System.out.println(set.size());
			// 鼠标自动点击
			Long time=0L;//延迟时间
			int methodnum=0;//调用哪个方法  1  11   2   3    。。。。 缺省方法0
			try{
				time=Long.valueOf(config.getProperty("sleeptime"));
				methodnum=Integer.valueOf(config.getProperty("method"));
			}catch(Exception e){
				e.printStackTrace();
				time=0L;
			}
			switch (methodnum) {
			case 1:
				LookAndLook1 look=new LookAndLook1(n);
				 while(true){
					Thread.sleep(time);
					List<Point> list=look.search();
					if(list==null){
						 break;}
					Point a =list.get(0);
					Point b =list.get(1);
					Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
					Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				 }
				 count=look.getCount();
				break;
			case 11:
				LookAndLook11 look11=new LookAndLook11(n);
				 while(true){
					Thread.sleep(time);
					List<Point> list=look11.search();
					if(list==null){
						 break;}
					Point a =list.get(0);
					Point b =list.get(1);
					Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
					Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				 }
				 count=look11.getCount();
				break;
			case 2:
				 LookAndLook2 look2=new LookAndLook2(n);
				 
				    while(true){
				    	Thread.sleep(time);
						 List<Point> list=look2.search();
						 if(list==null)
							 break;
					Point a = list.get(0);
					Point b = list.get(1);
					Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
					Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				}
				 count=look2.getCount();
				break;
				
			   
			case 3:
				LookAndLook3 look3=new LookAndLook3(n);
				 while(true){
					Thread.sleep(time);
					List<Point> list=look3.search();
					if(list==null){
						 break;}
					Point a =list.get(0);
					Point b =list.get(1);
					Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
					Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				 }
				 count=look3.getCount();
				break;

			default:
				
				LookAndLook0 df = new LookAndLook0(n);
				while (df.search()) {
					Thread.sleep(time);
					Point a = df.getA();
					Point b = df.getB();
					Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
					Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
					df.setValue(a.getX(), a.getY(), 0);
					df.setValue(b.getX(), b.getY(), 0);
				}
				count=df.getCount();
				
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(config!=null){
				String delete = config.getProperty("delete");
				if("delete".equalsIgnoreCase(delete)){
					try{
						File file =new File(filePath);
						file.delete();
					}catch(Exception ee){
						ee.printStackTrace();
					}
					
				}
			}
		}
	}
	private static File getFile(int hwnd) throws FileNotFoundException, IOException{
		// 游戏截图( 游戏窗口不能被其它窗口遮挡 .否则截图会截到其它窗口 )
		File f = null;//File.createTempFile("qqllk", ".bmp");
		/*
		 * 建议直接读取用户上传的数据流，而不是存为文件，再解析；一定要存文件的话，
		 * 也是用File.createTempFile的方式，即使当时删除失败，也会在系统重启之类的时机被删除
		 */
		try {
			if (!(new File("D:/lookandlook").isDirectory())) {
				new File("D:/lookandlook").mkdir();
			} 
			f=new File("D:/lookandlook/"+new Date().getTime()+".bmp");
		} catch (SecurityException e) {
			System.out.println("can not make directory");
		}
		System.out.println(f.getAbsolutePath());
		filePath=f.getAbsolutePath();
		// 写出位图
		BufferedImage buffImage = Window.getImage(hwnd);
		if (buffImage == null) {
			return null;
		}
		ImageIO.write(buffImage, "bmp", new FileOutputStream(f));
		return f;
	}

	public static void main(String[] args) throws Exception {
		Date a1=new Date();
		LookMain.ms();
		Date a2=new Date();
		if(config!=null){
			System.out.println("时间1："+(a1.getTime()));
			System.out.println("时间2："+(a2.getTime()));
			System.out.println("运行时间："+(a2.getTime()-a1.getTime()));
			MethodCountInfo model = new MethodCountInfo();
			model.setRemark("连连看算法");
			model.setType("LookAndLook"+config.getProperty("method"));
			model.setTime(a2.getTime()-a1.getTime());
			model.setCount(count);
			CountAction.SaveAndView(model);
		}
		
		

	}
}
