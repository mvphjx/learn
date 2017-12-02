package llk;
/*
 * 解析bmp
 */

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 
 * 
 */
public class BMP {

	private int width;
	private int height;
	private byte[] data;

	public BMP() {

	}

	public BMP(String src) {
		this.read(src);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static int b2i(byte[] b, int s) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int temp = b[s + i] & 0xff;
			ret += temp << (8 * i);
		}
		return ret;
	}

	/** * 读取图片文件 * @param src 文件路径 */
	public void read(String src) {
		width = 0;
		height = 0;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src));
			byte[] b = new byte[1024 * 1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				bs.write(b, 0, len);
				bs.flush();
			}
			data = bs.toByteArray();
			width = b2i(data, 18);
			height = b2i(data, 22);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bs.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 这方法不能识别相似的  只能比较相同
	public int getColor(int x, int y) {
		// BMP图要求每行字节数为4的倍数,不够则填充1-3个无用字节
		int lineW = 0;
		switch ((width * 3) % 4) {
		case 0:
			lineW = width * 3;
			break;
		case 1:
			lineW = width * 3 + 3;
			break;
		case 2:
			lineW = width * 3 + 2;
			break;
		case 3:
			lineW = width * 3 + 1;
		}
		int i = 54 + (height - y - 1) * lineW + 3 * x;
		int r = data[i + 2] & 0xff;
		int g = data[i + 1] & 0xff;
		int b = data[i] & 0xff;
		return r + (g << 8) + (b << 16);
	}

	public void setColor(int x, int y, int v) {
		int lineW = 0;
		switch ((width * 3) % 4) {
		case 0:
			lineW = width * 3;
			break;
		case 1:
			lineW = width * 3 + 3;
			break;
		case 2:
			lineW = width * 3 + 2;
			break;
		case 3:
			lineW = width * 3 + 1;
		}
		int i = 54 + (height - y - 1) * lineW + 3 * x;
		data[i + 2] = (byte) ((v >> 16) & 0xff);
		data[i + 1] = (byte) ((v >> 8) & 0xff);
		data[i] = (byte) (v & 0xff);
	}
	

	// 取矩形颜色数据
	public byte[] getData(int x, int y, int w, int h) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(w * h);
		try {
			for (int i = x; i < x + w; i++) {
				for (int j = y; j < y + h; j++) {
					bos.write(getColor(i, j));
					bos.flush();
				}
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}
