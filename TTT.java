/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lenovoa
 */
public class TTT {
  static String[][] m = new String[3][3];
    
    public static void main(String[] args) {
        Menu();
    }
    
    static String[][] inicializarTabuleiro(String[][] m){
         m = new String [][]{{"n","n","n"},
                             {"n","n","n"},
                             {"n","n","n"}};
        
        return m;
    }
    static void ImprimirTabuleiro(String[][] m){
        for(int i = 0; i < m.length;i++){
            for(int j = 0; j < m.length;j++){
                System.out.printf("   " + m[i][j]);
            }
            System.out.println("\n");
        }
    }
    static int leiaCoordenadaLinha(){
        Scanner scan = new Scanner(System.in);
        int linha;
        System.out.print("Digite a linha:");
        linha = scan.nextInt();
        while(linha < 0 || linha > 2){
            System.out.println("Valor Invalido!Tente Novamente.");
            System.out.print("Digite a linha:");
            linha = scan.nextInt();
        }
        return linha;
    }
    static int leiaCoordenadaColuna(){
        Scanner scan = new Scanner(System.in);
        int coluna;
        System.out.print("Digite a Coluna:");
        coluna = scan.nextInt();
        while(coluna < 0 || coluna > 2){
            System.out.println("Valor Invalido!Tente Novamente.");
            System.out.print("Digite a Coluna:");
            coluna = scan.nextInt();
        }
        return coluna;
    }
    
    static boolean posicaoValida(int linha, int coluna, String[][] m){
        boolean verificacao = false;
        for(int i = 0; i < m.length;i++){
            for(int j = 0; j < m.length;j++){
                if(m[i][j] == m[linha][coluna]){
                   verificacao = true;
                }else if(m[linha][coluna] == "X" || m[linha][coluna] == "O"){
                   verificacao = false;
                }
                
            }
        }
        return verificacao;
    }
    static String verificaVencedor(int jogadas, String[][] m){
        String[] v = new String[8];
        String[][] empate = new String[3][3];
        String vencedor = null;
        if(jogadas % 9 == 0){
          System.out.println("EMPATE\n");
          vencedor = "EMPATE";
        }
        v[0] = m[0][0] + m[0][1] + m[0][2];
        v[1] = m[1][0] + m[1][1] + m[1][2];
        v[2] = m[2][0] + m[2][1] + m[2][2];
        v[3] = m[0][0] + m[1][0] + m[2][0];
        v[4] = m[0][1] + m[1][1] + m[2][1];
        v[5] = m[0][2] + m[1][2] + m[2][2];
        v[6] = m[0][0] + m[1][1] + m[2][2];
        v[7] = m[0][2] + m[1][1] + m[2][0];
        
        for(int i = 0; i < v.length;i++){
            if(v[i].equals ("XXX")){
              vencedor = "Jogador 1";
            }else if(v[i].equals("OOO")){
              vencedor = "Jogador 2"; 
            }
        }
        return vencedor;
    }
    static void jogar(int linha, int coluna, String[][] m, String jogador){
        if(linha == 0 && coluna == 0){
           m[0][0] = jogador;
        }else if(linha == 0 && coluna == 1){
           m[0][1] = jogador;
        }else if(linha == 0 && coluna == 2){
           m[0][2] = jogador;
        }else if(linha == 1 && coluna == 0){
           m[1][0] = jogador;
        }else if(linha == 1 && coluna == 1){
           m[1][1] = jogador;
        }else if(linha == 1 && coluna == 2){
           m[1][2] = jogador;
        }else if(linha == 2 && coluna == 0){
           m[2][0] = jogador;
        }else if(linha == 2 && coluna == 1){
           m[2][1] = jogador;
        }else if(linha == 2 && coluna == 2){
           m[2][2] = jogador;
        } 
    }
    static void modoJogador(){
        m = inicializarTabuleiro(m);
        boolean venceu = false;
        boolean verificacao = false;
        int linha, coluna;
        String vencedor = null;
        int jogada = 0, rodadas = 0;
        
            System.out.println("--- Jogo da Velha ---\n");
            ImprimirTabuleiro(m);
            while(true){
                
                do{
                
                System.out.println("Jogador 1");
                linha = leiaCoordenadaLinha();
                coluna = leiaCoordenadaColuna();
                verificacao = posicaoValida(linha, coluna, m);
                while(verificacao == false){
                    System.out.println("Jogada Invalida! Tente Novamente.");
                    ImprimirTabuleiro(m);
                    System.out.println("Jogador 1");
                    linha = leiaCoordenadaLinha();
                    coluna = leiaCoordenadaColuna();
                    verificacao = posicaoValida(linha, coluna, m);
                }
                jogar(linha, coluna, m, "X");
                jogada = 1;
                
            }while(jogada == 0);
            rodadas++;
            jogada = 0;
            ImprimirTabuleiro(m);
            vencedor = verificaVencedor(rodadas, m);
            
            if(vencedor == "Jogador 1"){
               break;
            }
            if(vencedor == "EMPATE"){
                m = inicializarTabuleiro(m);
                ImprimirTabuleiro(m);
                vencedor = verificaVencedor(rodadas, m);
              
            }
            
            do{
                System.out.println("Jogador 2");
                linha = leiaCoordenadaLinha();
                coluna = leiaCoordenadaColuna();
                verificacao = posicaoValida(linha, coluna, m);
                while(verificacao == false){
                    System.out.println("Jogada Invalida! Tente Novamente.");
                    ImprimirTabuleiro(m);
                    System.out.println("Jogador 2");
                    linha = leiaCoordenadaLinha();
                    coluna = leiaCoordenadaColuna();
                    verificacao = posicaoValida(linha, coluna, m);
                }
                jogar(linha, coluna, m, "O");
                jogada = 1;
                
            }while(jogada == 0);
            
            rodadas++;
            jogada = 0;
            ImprimirTabuleiro(m);
            vencedor = verificaVencedor(rodadas, m);
            
            if(vencedor == "Jogador 2"){
               break;
            }
            if(vencedor == "EMPATE"){
                m = inicializarTabuleiro(m);
                ImprimirTabuleiro(m);
                vencedor = verificaVencedor(rodadas, m);
                
            }
        }
        
        if(vencedor == "Jogador 1" || vencedor == "Jogador 2"){
           System.out.println("O Ganhador é " + vencedor);
        }
    }
    static void modoFacil(){
        String vencedor = null;
        int jogada = 0, rodadas = 0;
        m = inicializarTabuleiro(m);
        System.out.println("--- Jogo da Velha ---\n");
        ImprimirTabuleiro(m);
        
        while(true){
            
            do{
               
                jogadaUsuario();
                jogada = 1;
                
            }while(jogada == 0);
            
            rodadas++;
            jogada = 0;
            vencedor = verificaVencedor(rodadas, m);
            
            if(vencedor == "Jogador 1"){
               ImprimirTabuleiro(m);
               break;
            }
            if(vencedor == "EMPATE"){
                m = inicializarTabuleiro(m);
                ImprimirTabuleiro(m);
                vencedor = verificaVencedor(rodadas, m);
            }
            
            do{
                jogadaMaquinaFacil();
                jogada = 1;
                
            }while(jogada == 0);
           
            rodadas++;
            jogada = 0;
            ImprimirTabuleiro(m);
            vencedor = verificaVencedor(rodadas, m);
            
            if(vencedor == "Jogador 2"){
               vencedor = "Maquina"; 
               break;
            }
            if(vencedor == "EMPATE"){
                m = inicializarTabuleiro(m);
                ImprimirTabuleiro(m);
                vencedor = verificaVencedor(rodadas, m);
            }
        }
        if(vencedor == "Jogador 1" || vencedor == "Maquina"){ 
           System.out.println("O Ganhador é " + vencedor);
        }
    }
    static void jogadaUsuario(){
        int linha = leiaCoordenadaLinha();
        int coluna = leiaCoordenadaColuna();
        boolean verificacao = posicaoValida(linha, coluna, m);
        while(verificacao == false){
            System.out.println("Jogada Invalida! Tente Novamente.");
            ImprimirTabuleiro(m);
            System.out.println("Jogador");
            linha = leiaCoordenadaLinha();
            coluna = leiaCoordenadaColuna();
            verificacao = posicaoValida(linha, coluna, m);
        }
        jogar(linha, coluna, m, "X");
        
    }
    static void jogadaMaquinaFacil(){
        Random aleatorio = new Random();
        int linha = aleatorio.nextInt(2 + 1);
        int coluna = aleatorio.nextInt(2 + 1);
        boolean verificacao = posicaoValida(linha, coluna, m);
        while(verificacao == false){
            linha = aleatorio.nextInt(2 + 1);
            coluna = aleatorio.nextInt(2 + 1);
            verificacao = posicaoValida(linha, coluna, m);
        }
        jogar(linha, coluna, m, "O");
    }
    static void Instrucoes(){
        System.out.println("                ------- Instruções -------  ");
        System.out.println("O joga da velha tem os 2 modos que são, jogador contra jogador e");
        System.out.println("jogador contra maquina. para joga de forma certo o usuario tera ");
        System.out.println("que escolher entre 0, 1, 2 para escolher a linha e coluna desejada.\n");
    }
    static void Menu(){
        Scanner scan = new Scanner(System.in);
        int op;
        
        do{
            System.out.println("      --- MENU ---         ");
        System.out.println("1 - Jogador VS Jogador");
        System.out.println("2 - Jogador VS Maquina(Facil)");
        System.out.println("3 - Instruções");
        System.out.println("4 - Sair");
        op = scan.nextInt();
        
        while(op < 1 || op > 4){
            System.out.println("Valor Invalido! Tente Novamente.\n");
            System.out.println("      --- MENU ---         ");
            System.out.println("1 - Jogador VS Jogador");
            System.out.println("2 - Jogador VS Maquina(Facil)");
            System.out.println("3 - Instruções");
            System.out.println("4 - Sair");
            op = scan.nextInt();
        }
        
        switch(op){
            case 1: 
                modoJogador();
            case 2:
                modoFacil();
            case 3:
                Instrucoes();
            case 4:
                System.out.println("Jogo Encerrado.");
        }
        
        }while(!(op == 4));
        
    }
}