package com.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;

import java.net.URI;
import java.util.Map;

public class Task
{
    public static void main(String[] args) throws Exception
    {
        updateWebJob();
    }

    public static void updateWebJob() throws Exception
    {
        String url = "http://192.168.128.12:8082/";
        String user = "hanjianxiang";
        String passWord = "123456";
        JenkinsServer jenkins = new JenkinsServer(new URI(url), user, passWord);
        Map<String, Job> jobs = jenkins.getJobs();
        jobs.forEach((name, job) -> {
            if (name.contains("web"))
            {
                /**
                 * 格式 邮箱1，空格邮箱2
                 * 居然必须加空格
                 */
                String email = "lili@, hanjianxiang@";
                ConfigUtil.updateEmail(jenkins, name, email, false);
            }
        });

    }
}
