package com.learn.Try.T2019.T09;

import com.hisign.pu.pki.service.PkiLoginService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Test01
{

    private static String getSfIdFromDN(String tStrDN) throws UnsupportedEncodingException
    {
        String sfId = "";
        if (tStrDN == null)
        {
            sfId = "";
        } else
        {
            tStrDN = new String(tStrDN.getBytes("ISO8859-1"), "GBK");
            String[] dnsplit = tStrDN.trim().split(",");
            for (int i = 0; i < dnsplit.length; i++)
            {
                if (dnsplit[i].toLowerCase().indexOf("cn=") >= 0)
                {
                    String[] cnsplit = dnsplit[i].split("=");
                    String dnName = cnsplit[1];
                    if (dnName.trim().indexOf(" ") >= 0)
                    {
                        String[] dn = dnName.split(" ");

                        sfId = dn[1];
                    } else
                    {

                        sfId = "";
                    }
                }
            }
        }
        return sfId;
    }

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        PkiLoginService pkiLoginService = new PkiLoginService();
        //131102199201032033
        String url = "CN=韦珈莹 123, OU=00, OU=10, O=40, L=00, L=00, ST=11, C=CN";
        Map<String,String> map = new HashMap();
        map.put("DNNAME",url);
        System.out.println(pkiLoginService.loginPki(map));
        String url2 = "CN=Τ��Ө 131102199201032033, OU=00, OU=10, O=40, L=00, L=00, ST=11, C=CN";
        map.put("DNNAME",url2);
        System.out.println(pkiLoginService.loginPki(map));

    }
}
