package cn.com.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 数据库配置文件
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Config extends Properties {
	private static Config config;

	private Config()
	{
		InputStream iStream;
		try {
			iStream = new FileInputStream("config.properties");
			this.load(iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 得到唯一的配置对象
	 * @return
	 */
	public static Config createConfig() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

}
