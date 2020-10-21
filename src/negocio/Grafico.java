/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author diego
 */
public class Grafico extends JPanel{
        
    static String mx, mn;
    static double maximo, minimo, passo, a, b, c, d, e, f, l, h, i, j, m;

    double evidencia;
    double[] t = new double[300];
    double[] w = new double[300];
    private double[] ff = new double[600];
    private double[] fg = new double[600];

    Teste_backpropagation aa = new Teste_backpropagation();
    
    Grafico(){}
    
    Grafico (double [] shapes){
        ff = shapes;
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        minimo = 0;
        maximo = 1;
        passo = (maximo - minimo)/10;
        
        a = minimo;
        b = minimo + passo;
        c = minimo + 2 * passo;
        d = minimo + 3 * passo;
        e = minimo + 4 * passo;
        f = minimo + 5 * passo;
        l = minimo + 6 * passo;
        h = minimo + 7 * passo;
        i = minimo + 8 * passo;
        j = minimo + 9 * passo;
        m = minimo + 10 * passo;

        g.setColor(Color.lightGray);

        // linhas horizontais
        int p = 70;
        do {
            for (int i = 0; i < 320; i = i + 20) {
                g.drawLine(0, p, i, p);
            }
            p = p + 22;
        } while (p <= 340);

        // linhas verticais
        int q = 0;
        do {
            for (int i = 0; i < 200; i = i + 20) {
                g.drawLine(q, 70, q, 332);
            }
            q = q + 20;
        } while (q < 320);

        g.setColor(Color.RED);
        g.drawString("*Sinal 1 ", 60, 50);

        g.setColor(Color.BLUE);
        g.drawString("*Sinal 2 ", 130, 50);

        g.setColor(Color.BLACK);
        int k = 0, escalaHorizontal = 0;

        // escala horizontal
        for (int i = 0; i <= 300; i = i + 20) {
            g.drawString("" + k, escalaHorizontal, 350);
            escalaHorizontal = escalaHorizontal + 20;
            k = k + 2;
        }

        g.drawString("(*10)", 340, 350);

        // escala vertical
        g.drawString("-1.0    ", 320, 330); //+a
        g.drawString("-0.8    ", 320, 305); //+b
        g.drawString("-0.6    ", 320, 280); //+c
        g.drawString("-0.4    ", 320, 255); //+d
        g.drawString("-0.2    ", 320, 230); //+e
        g.drawString(" 0.0    ", 320, 205); //+f
        g.drawString(" 0.2    ", 320, 180); //+l
        g.drawString(" 0.4    ", 320, 155); //+h
        g.drawString(" 0.6    ", 320, 130); //+i
        g.drawString(" 0.9    ", 320, 105); //+j
        g.drawString(" 1.0    ", 320, 80); //+m
        
        ff = aa.get_backp();

        System.arraycopy(ff, 0, fg, 0, 600);

        for (int nn = 0; nn < 300; nn++) {
            t[nn] = ff[nn];

        }

        System.arraycopy(ff, 300, w, 0, 300);

        int xx = 0;
        double y = 0, n = 0;

        // g.setColor(Color.red);
        for (int z = 0; z <= 299; z++) {
            y = +203 + (-250 * t[z]) * 0.5;
            n = +203 + (-250 * w[z]) * 0.5;
            g.setColor(Color.red);
            g.drawLine(xx - 1, (int) y - 1, xx, (int) y);
            g.setColor(Color.blue);
            g.drawLine(xx - 1, (int) n - 1, xx, (int) n);
            xx++;
        }
    }
}
