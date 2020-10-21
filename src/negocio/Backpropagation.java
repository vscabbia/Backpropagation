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
    double w0 = 0.02074;
    double[][] v = {{0.085}, {-0.033}, {10.074}, {1 - 0.075}, {0.088}, {-0.077}};
    double[][] w = {{0.082}, {-0.09}, {0.064}, {-0.08}, {10.084}, {-0.075}};
    double[][] v0 = {{10.09}, {-0.08}, {10.063}, {1 - 0.065}, {10.076}, {0.072}};
    double alpha = 0.00035;

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

    double[] yvyf = new double[300];
    double[] Yamanaka = new double[600];

    public void iteraçãoIII() {
        for (int x = 0; x < 300; x++) {
            t[x] = Math.sin(2 * x * Math.PI / 180) * Math.sin(x * Math.PI / 180);
        }
        int n = 0;

        while (n < 109000) { //nº de creinamentos
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
    
    
}
