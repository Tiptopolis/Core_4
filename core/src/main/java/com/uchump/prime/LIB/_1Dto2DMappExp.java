package com.uchump.prime.LIB;

public class _1Dto2DMappExp {

	public void mn()
	{
	    int[] oneD = {1,2,3,4,5,6};
	    int w = 3;
	    int h = 2;
	    int[][] twoD = new int[h][w];
	    int[] oneDReversed = new int[oneD.length];

	    for (int i = 0; i < h; i++) {
	        for (int j = 0; j < w; j++) {
	            twoD[i][j] = oneD[i*w+j];
	        }
	    }

	    for (int i = 0; i < w*h; i++) {
	        oneDReversed[i] = twoD[(i / w)][(i%w)];
	    }
	}
	
}
