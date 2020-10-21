/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import negocio.*;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author diego
 */
public class Teste_backpropagation extends javax.swing.JFrame {
    
    static double [] pesos = new double [600];
    static double [] backp = new double[600];
    
    public static void main(String args[]){
        
        Grafico g = new Grafico();
        Grafico gf;
        
        Backpropagation bck = new Backpropagation();
        
        bck.iteracao_III();
        
        backp = bck.teste_backpropagation(bck.v0, bck.v, bck.w0, bck.w);
        
        JFrame aplicacao = new JFrame();
        aplicacao.getContentPane().setBackground (new Color (255,255,255));
        aplicacao.setTitle("Redes Neurais Artificiais");
        aplicacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacao.add(g);
        aplicacao.setSize(500,400);
        aplicacao.setVisible(true);
    }
    
    public double [] get_backp(){
        return backp;
    }
    
}
