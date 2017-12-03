package com.webMagic.service;
/**
 * 爬取网页的数据
 */

import com.webMagic.model.VideoModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class GithubRepoPageProcessor implements PageProcessor{
	 public static String siteurl = "http://91.91p21.space/";
	 private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	 public static void main(String[] args) {
	    	int all = 2750;
	    	int page = all;
	    	int end = 2748;
	    	for(;page>end;page--){
	    		String url = siteurl+"/v.php?next=watch&page="+page;
		    	Spider s =Spider.create(new GithubRepoPageProcessor());
		        s.addUrl(url);   
		        s.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"));
		        s.thread(5);
		        s.run();
	    	}	        
	    }

		@Override
		public void process(Page page) {
			Html h = page.getHtml();
			//System.out.println(page.getUrl());
			Selectable s =h.$("#videobox").$(".listchannel");
			//System.out.println(s.all().size());
			for(Selectable table :s.nodes()){
				String all = table.toString();
				Selectable a = table.$("a:eq(1)");
				String title = a.$("a", "title").toString();
				if("".equals(title)||title==null){
					title=a.$("a", "title").nodes().get(1).toString();
				}
			String url = a.$("a","href").toString();
				int  start= url.indexOf("viewkey=")+"viewkey=".length();
				int  end = url.indexOf("&page=");
				String viewkey = url.substring(start,end);
				int  start1= url.indexOf("&page=")+"&page=".length();
				int  end1 = url.indexOf("&viewtype");			
				String  pagenum = url.substring(start1,end1);
				Selectable a_au = table.$("a").nodes().get(2);
				String url_au = a_au.$("a","href").toString();
				int  start2= url_au.indexOf("UID=")+"UID=".length();
				String  autorid = url_au.substring(start2);
				String  autor = a_au.$("a","innerHtml").toString();
				if(autor.indexOf("data-cfemail")>0){
					autor=a_au.$("span","data-cfemail").toString();
				}
				//
				int viewnum =0;
				int messagesnum =0;
				int collectnum =0;
				String str1 = "<span class=\"info\">查看:</span>";
				String str2 = "<span class=\"info\">收藏:</span>";
				String str3 = "<span class=\"info\">留言:</span>";
				String str4 = "<span class=\"info\">积分:</span>";
				String result = "";
				if(all.indexOf(str1)>0){
					start =  all.indexOf(str1)+str1.length();
					end =  all.indexOf(str2);
					result=all.substring(start,end);
					result=result.replace("&nbsp;", "").replace("<br/>", "").replace("<br>", "").trim();
					viewnum= Integer.valueOf(result);
				}
				if(all.indexOf(str2)>0){
					start =  all.indexOf(str2)+str2.length();
					end =  all.indexOf(str3);
					result=all.substring(start,end);
					result=result.replace("&nbsp;", "").replace("<br/>", "").replace("<br>", "").trim();
					collectnum= Integer.valueOf(result);
				}				
				if(all.indexOf(str3)>0){
					start =  all.indexOf(str3)+str3.length();
					end =  all.indexOf(str4);
					result=all.substring(start,end);
					result=result.replace("&nbsp;", "").replace("<br/>", "").replace("<br>", "").trim();
					messagesnum= Integer.valueOf(result);
				}
				VideoModel model = new VideoModel();
				model.setTitle(title);
				model.setUrl(url);
				model.setViewkey(viewkey);
				model.setPage(Integer.valueOf(pagenum));
				model.setAutor(autor);
				model.setAutorurl(siteurl+"/uvideos.php?type=public&UID="+autorid);
				model.setViewnum(viewnum);
				model.setCollectnum(collectnum);
				model.setMessagesnum(messagesnum);
				try{
					//save TODO
					System.out.println(model);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		@Override
		public Site getSite() {
			//返回 中文 html网页
			site.addHeader("Accept-Language","zh-CN");
			return this.site;
		}
		
}
