package edu.eci.arep.hadamardmatrix;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        port(getPort());
        get("/hadamard/:order", (req, res) -> {

            int order=  Integer.parseInt(req.params(":order"));
            return hadamard(order);});
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e on localhost)
    }
    private static String hadamard(int k)
    {
        // compute matrix size N
        int N = new Double(Math.pow(2, k)).intValue();
        int[][] had = new int[N][N];

        // initialize Hadamard matrix of order N
        had[0][0] = 1;
        for (int n = 1; n < N; n += n)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    had[i+n][j]   =  had[i][j];
                    had[i][j+n]   =  had[i][j];
                    had[i+n][j+n] =  (had[i][j] + 1) % 2; //1 -> 0 || 0 -> 1
                }
            }
        }
        String rta="";
        for(int i=0;i<had.length;i++){
            for (int j = 0;j<had[i].length;j++){
                rta+=had[i][j];
            }
            rta+="\n";
        }
        return rta;
    }
}
