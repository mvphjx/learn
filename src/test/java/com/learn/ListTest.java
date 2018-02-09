package com.learn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest
{
    @Test
    public void growTest(){
        List<String> list = new ArrayList<>();
        while (true){
            list.add("0");
            if(list.size()>23){
                break;
            }
        }
    }
}
