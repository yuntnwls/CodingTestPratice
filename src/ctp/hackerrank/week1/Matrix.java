package ctp.hackerrank.week1;

import java.util.List;

public class Matrix {
    public static void main(String[] args) {

    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int n = matrix.size()/2;
        //Now for each cell in the upper quadrant, get the max value that could be flipped into that cell

        int sum = 0;
        //Iterate all rows in quadrant
        for(int r = 0; r < n; r++)
        {
            //Iterate all columns in quadrant
            for(int c = 0; c < n; c++)
            {
                //Grab the 4 possible values that could wind up in this location of the upper quadrant
                int p1 = matrix.get(r).get((2*n) - c - 1);
                int p2 = matrix.get(r).get(c);
                int p3 = matrix.get((2*n) - r - 1).get(c);
                int p4 = matrix.get((2*n) - r - 1).get((2*n) - c - 1);

                //Get the highest value possible in this cell
                sum += Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            }
        }

        return sum;
    }
}
