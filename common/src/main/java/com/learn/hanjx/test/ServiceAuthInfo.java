package com.learn.hanjx.test;

public class ServiceAuthInfo {
	public String	serverName	= "localhost";
	public int		serverPort	= 4447;
	public String	userName	= "ysabisadm";
	public String	password	= "ysabis#2013";

	public static ServiceAuthInfo build(String serviceString)
	{
		return build(null, serviceString);
	}

	public static ServiceAuthInfo build(ServiceAuthInfo si, String serviceString)
	{
		if(si == null)	si = new ServiceAuthInfo();
		if (ABISHelper.isEmpty(serviceString))
			return si;

		String[] items = serviceString.split("@");

		String	stemp;
		if (items.length > 0 )
		{
			stemp = items[0].trim();
			if ( !stemp.isEmpty() )
			{
				// 用户信息
				String[] subitems = stemp.split(":");
				if (subitems.length > 0 && subitems[0].length()>0)
					si.userName = subitems[0];
				if (subitems.length > 1 && subitems[1].length()>0 )
					si.password = subitems[1];
			}
		}

		if (items.length > 1)
		{
			// 服务器信息
			stemp = items[1].trim();
			String[] subitems = stemp.split(":");
			if (subitems.length > 0 && !ABISHelper.isEmpty(subitems[0]))
				si.serverName = subitems[0];
			if (subitems.length > 1 && !ABISHelper.isEmpty(subitems[1]))
				si.serverPort = (int) ABISHelper.toNumber(subitems[1], si.serverPort);
		}

		return si;
	}
	
	/**
	 * 构造服务器连接串
	 */
	@Override
	public String toString()
	{
		return userName + "@" + serverName + ":" + serverPort;
	}
}
