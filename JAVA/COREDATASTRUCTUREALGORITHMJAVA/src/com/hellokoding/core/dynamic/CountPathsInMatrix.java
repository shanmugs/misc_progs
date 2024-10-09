package com.hellokoding.core.dynamic;

public class CountPathsInMatrix {

    int countPaths(int[][] a)
    {
        int R = a.length;
        int C = a[0].length;

        int[][] count = new int[R][C];

        if (a[0][0]==0)
            return 0;

        for (int i=0; i<R; i++)
        {
            if (a[i][0] == 1)
                count[i][0] = 1;
            else
                break;
        }

        for (int i=1; i<C; i++)
        {
            if (a[0][i] == 1)
                count[0][i] = 1;
            else
                break;
        }

        for (int i=1; i<R; i++)
        {
            for (int j=1; j<C; j++)
            {
                if (a[i][j] == 0)
                    continue;

                if (a[i-1][j] > 0)
                    count[i][j] = (count[i][j] + count[i-1][j]);

                if (a[i][j-1] > 0)
                    count[i][j] = (count[i][j] + count[i][j-1]);
            }
        }

        return (a[R-1][C-1] > 0)? count[R-1][C-1] : 0;
    }
}
