/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author USER
 */
public class Backpropagation {

    double[] t = new double[300];

//    public double w0 = 0.02074;
//    public double[][] v = {{0.085}, {-0.033}, {0.074}, {-0.075}, {0.088}, {-0.077}};
//    public double[][] w = {{0.082}, {-0.09}, {0.064}, {-0.08}, {0.084}, {-0.075}};
//    public double[][] v0 = {{0.09}, {-0.08}, {0.063}, {-0.065}, {0.076}, {-0.072}};
//    double alpha = 0.00035;

    public double w0 = -0.0002;
    public double [][] v = {{0.5}, {-0.3}, {0.64}, {-0.5}, {0.8}, {-0.7}};
    public double [][] w = {{0.2}, {-0.1}, {0.34}, {-0.2}, {0.44}, {-0.35}};
    public double [][] v0 = {{0.1}, {-0.1}, {0.23}, {-0.15}, {0.36}, {-0.22}};
    double alpha = 0.0001;
    
    double[][] z = new double[6][1];
    double[][] zf = new double[6][1];
    double[][] zff = new double[6][1];
    double[] y = new double[300];
    double[] yf = new double[300];
    double[] yff = new double[300];
    double[] erro = new double[300];
    double[] errow = new double[300];
    double[][] deltaw = new double[6][1];
    double deltaw0 = 0.0;
    double[][] deltav = new double[6][1];
    double[][] deltav0 = new double[6][1];

    double[][] zz = new double[6][1];
    double[][] zzf = new double[6][1];
    double[][] zzff = new double[6][1];
    double[] yy = new double[300];
    double[] yyf = new double[300];
    double[] Yamanaka = new double[600];

    public void iteracao_III() {
        for (int x = 0; x < 300; x++) {
            t[x] = Math.sin(2 * x * Math.PI / 180) * Math.sin(x * Math.PI / 180);
        }
        int n = 0;

        while (n < 4000) { //nº de creinamentos
            for (int x = 0; x < t.length; x++) {
                z[0][0] = v0[0][0] + t[x] * v[0][0];
                z[1][0] = v0[1][0] + t[x] * v[1][0];
                z[2][0] = v0[2][0] + t[x] * v[2][0];
                z[3][0] = v0[3][0] + t[x] * v[3][0];
                z[4][0] = v0[4][0] + t[x] * v[4][0];
                z[5][0] = v0[5][0] + t[x] * v[5][0];

                zf[0][0] = 2 / (1 + (Math.exp(-z[0][0]))) - 1;
                zf[1][0] = 2 / (1 + (Math.exp(-z[1][0]))) - 1;
                zf[2][0] = 2 / (1 + (Math.exp(-z[2][0]))) - 1;
                zf[3][0] = 2 / (1 + (Math.exp(-z[3][0]))) - 1;
                zf[4][0] = 2 / (1 + (Math.exp(-z[4][0]))) - 1;
                zf[5][0] = 2 / (1 + (Math.exp(-z[5][0]))) - 1;

                zff[0][0] = 0.5 * (1 + zf[0][0]) * (1 - zf[0][0]);
                zff[1][0] = 0.5 * (1 + zf[1][0]) * (1 - zf[1][0]);
                zff[2][0] = 0.5 * (1 + zf[2][0]) * (1 - zf[2][0]);
                zff[3][0] = 0.5 * (1 + zf[3][0]) * (1 - zf[3][0]);
                zff[4][0] = 0.5 * (1 + zf[4][0]) * (1 - zf[4][0]);
                zff[5][0] = 0.5 * (1 + zf[5][0]) * (1 - zf[5][0]);

                y[x] = w0 + zf[0][0] * w[0][0] + zf[1][0] * w[1][0] + zf[2][0] * w[2][0] + zf[3][0] * w[3][0] + zf[4][0] * w[4][0] + zf[5][0] * w[5][0];
                yf[x] = 2 / (1 + (Math.exp(-y[x]))) - 1;
                erro[x] = (t[x] - yf[x]);
                yff[x] = 0.5 * (1 + yf[x]) * (1 - yf[x]);
                errow[x] = alpha * erro[x] * yff[x];
            }
            for (int x = 0; x < t.length; x++) {
                deltaw[0][0] = errow[x] * zf[0][0];
                deltaw[1][0] = errow[x] * zf[1][0];
                deltaw[2][0] = errow[x] * zf[2][0];
                deltaw[3][0] = errow[x] * zf[3][0];
                deltaw[4][0] = errow[x] * zf[4][0];
                deltaw[5][0] = errow[x] * zf[5][0];
                deltaw0 += alpha * erro[x];
            }

            w[0][0] = w[0][0] + deltaw[0][0];
            w[1][0] = w[1][0] + deltaw[1][0];
            w[2][0] = w[2][0] + deltaw[2][0];
            w[3][0] = w[3][0] + deltaw[3][0];
            w[4][0] = w[4][0] + deltaw[4][0];
            w[5][0] = w[5][0] + deltaw[5][0];
            w0 = w0 + deltaw0;

            for (int x = 0; x < t.length; x++) {
                deltav[0][0] = deltaw[0][0] * w[0][0] * zff[0][0] * t[x];
                deltav[1][0] = deltaw[1][0] * w[1][0] * zff[1][0] * t[x];
                deltav[2][0] = deltaw[2][0] * w[2][0] * zff[2][0] * t[x];
                deltav[3][0] = deltaw[3][0] * w[3][0] * zff[3][0] * t[x];
                deltav[4][0] = deltaw[4][0] * w[4][0] * zff[4][0] * t[x];
                deltav[5][0] = deltaw[5][0] * w[5][0] * zff[5][0] * t[x];
            }
            v[0][0] = v[0][0] + deltav[0][0];
            v[1][0] = v[1][0] + deltav[1][0];
            v[2][0] = v[2][0] + deltav[2][0];
            v[3][0] = v[3][0] + deltav[3][0];
            v[4][0] = v[4][0] + deltav[4][0];
            v[5][0] = v[5][0] + deltav[5][0];

            for (int x = 0; x < t.length; x++) {
                deltav0[0][0] = alpha * erro[x] * zf[0][0];
                deltav0[1][0] = alpha * erro[x] * zf[1][0];
                deltav0[2][0] = alpha * erro[x] * zf[2][0];
                deltav0[3][0] = alpha * erro[x] * zf[3][0];
                deltav0[4][0] = alpha * erro[x] * zf[4][0];
                deltav0[5][0] = alpha * erro[x] * zf[5][0];
            }
            v0[0][0] = v0[0][0] + deltav0[0][0];
            v0[1][0] = v0[1][0] + deltav0[1][0];
            v0[2][0] = v0[2][0] + deltav0[2][0];
            v0[3][0] = v0[3][0] + deltav0[3][0];
            v0[4][0] = v0[4][0] + deltav0[4][0];
            v0[5][0] = v0[5][0] + deltav0[5][0];
            
            n = n + 1;
            System.out.println("número de treinamentos " + n + "\n");
        }
    }

    public double[] teste_backpropagation(double[][] vv0, double[][] vv, double ww0, double[][] ww) {
        System.out.println("peso w0" + ww0);

        for (int j = 0; j <= 5; j++) {
            System.out.println("peso v0[" + j + "]" + vv0[j][0]);
            System.out.println("peso v[" + j + "]" + vv[j][0]);
            System.out.println("peso w[" + j + "]" + ww[j][0]);
        }

        for (int x = 0; x < t.length; x++) {
            zz[0][0] = vv0[0][0] + t[x] * vv[0][0];
            zz[1][0] = vv0[1][0] + t[x] * vv[1][0];
            zz[2][0] = vv0[2][0] + t[x] * vv[2][0];
            zz[3][0] = vv0[3][0] + t[x] * vv[3][0];
            zz[4][0] = vv0[4][0] + t[x] * vv[4][0];
            zz[5][0] = vv0[5][0] + t[x] * vv[5][0];

            zzf[0][0] = 2 / (1 + (Math.exp(-zz[0][0]))) - 1;
            zzf[1][0] = 2 / (1 + (Math.exp(-zz[1][0]))) - 1;
            zzf[2][0] = 2 / (1 + (Math.exp(-zz[2][0]))) - 1;
            zzf[3][0] = 2 / (1 + (Math.exp(-zz[3][0]))) - 1;
            zzf[4][0] = 2 / (1 + (Math.exp(-zz[4][0]))) - 1;
            zzf[5][0] = 2 / (1 + (Math.exp(-zz[5][0]))) - 1;

            zzff[0][0] = 0.5 * (1 + zzf[0][0]) * (1 - zzf[0][0]);
            zzff[1][0] = 0.5 * (1 + zzf[1][0]) * (1 - zzf[1][0]);
            zzff[2][0] = 0.5 * (1 + zzf[2][0]) * (1 - zzf[2][0]);
            zzff[3][0] = 0.5 * (1 + zzf[3][0]) * (1 - zzf[3][0]);
            zzff[4][0] = 0.5 * (1 + zzf[4][0]) * (1 - zzf[4][0]);
            zzff[5][0] = 0.5 * (1 + zzf[5][0]) * (1 - zzf[5][0]);

            yy[x] = ww0 + zzf[0][0] * ww[0][0] + zzf[1][0] * ww[1][0] + zzf[2][0] * ww[2][0] + zzf[3][0] * ww[3][0] + zzf[4][0] * ww[4][0] + zzf[5][0] * ww[5][0];

            yyf[x] = 2 / (1 + (Math.exp(-yy[x]))) - 1;
        }
        
        int k = 0;

        for (int i = 0; i < 300; i++) {
            Yamanaka[i] = t[i];
        }
        for (int j = 300; j < 600; j++) {
            Yamanaka[j] = yyf[k];
            k++;
        }
        return Yamanaka;
    }

}
