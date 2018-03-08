package com.learn.hanjx.algorithm;

import java.util.Arrays;

/**
 * 国际象棋 皇后攻击所有直线 斜线
 * <p>
 * 0 0 1 0 0 1 0 0
 * 1 0 1 0 1 0 0 0
 * 0 1 1 1 0 0 0 0
 * 1 1 X 1 1 1 1 1
 * 0 1 1 1 0 0 0 0
 * 1 0 1 0 1 0 0 0
 * 0 0 1 0 0 1 0 0
 * 0 0 1 0 0 0 1 0
 * 如果皇后坐标为 (x1,y1),可攻击点为
 * （*,y1）
 * （x1,*）
 * x+y=x1+y1
 * x-y = x1-y1
 * <p>
 * <p>
 * 8*8棋盘放置八个皇后。
 */
public class 八皇后
{
    public int queenNum = 8;
    public int[][] chessboard = new int[8][8];

    public static void main(String[] args)
    {
        八皇后 model = new 八皇后();
        for (int i = 0; i < model.queenNum; i++)
        {
            model.palced();
        }
    }

    /**
     * 放置一个皇后  并且同步不能放置的坐标
     *
     * @return
     */
    private boolean palced()
    {
        for (int x = 0; x < chessboard.length; x++)
        {
            for (int y = 0; y < chessboard[y].length; y++)
            {
                if (chessboard[x][y] == 0)
                {
                    if (palced(x, y))
                    {
                        return true;
                    } else
                    {

                    }
                }
            }
        }
        return false;
    }

    /**
     * 放置一个皇后  并且同步不能放置的坐标
     *
     * @return
     */
    private boolean palced(int x1, int y1)
    {
        if (chessboard[x1][y1] == 1)
        {
            return false;
        }
        for (int x = 0; x < chessboard.length; x++)
        {
            for (int y = 0; y < chessboard[y].length; y++)
            {
                if (x == x1 || y == y1 || x + y == x1 + y1 || x - y == x1 - y1)
                {
                    chessboard[x][y] = 1;
                }
            }
        }
        return true;
    }

    public static void main2(String[] args)
    {
        int[][] test = new int[2][10];
        System.out.println(Arrays.toString(test));
        for (int i = 0; i < test.length; i++)
        {
            System.out.println(Arrays.toString(test[i]));
        }
    }

}
