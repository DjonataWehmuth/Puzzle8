/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8;

import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author Djonata Wehmuth
 */
public class Node implements Cloneable {
    final int MOVEU_CIMA     = 1;
    final int MOVEU_BAIXO    = 2;
    final int MOVEU_ESQUERDA = 3;
    final int MOVEU_DIREITA  = 4;

    public Integer[] posicoes;
    public List<Node> children;
    private int level = 0;
    private Node parent;
    private String Movimento;

    public String getMovimento() {
        return Movimento;
    }

    public void setMovimento(String Movimento) {
        this.Movimento = Movimento;
    }

    public void setMovimento(int Movimento) {
        if(Movimento == MOVEU_BAIXO){
            this.Movimento = "Mover para baixo";
        }
        else if(Movimento == MOVEU_CIMA){
            this.Movimento = "Mover para cima";
        }
        else if(Movimento == MOVEU_DIREITA){
            this.Movimento = "Mover para direita";
        }
        else if(Movimento == MOVEU_ESQUERDA){
            this.Movimento = "Mover para esquerda";
        }
        else{
            this.Movimento = "Movimento inválido";
        }
    }

    public int getLevel() {
        return level;
    }

    public int getValorHeuristica() {
        return ValorHeuristica;
    }
    private Boolean Aberto = true;
    private int ValorHeuristica;

    public Node() {
        super();
        parent = null;
        children = null;
    }

    public Boolean getAberto() {
        return Aberto;
    }

    public void Fechar(){
        this.Aberto = false;
    }

    public Node(Integer[] posicoes) {
        this.posicoes = posicoes;
        parent = null;
        children = null;
        this.CalcularHeuristica();
    }

    public Integer[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Integer[] posicoes) {
        this.posicoes = posicoes;
        this.CalcularHeuristica();
    }

    public List<Node> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node>();
        }
        return this.children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }

    public void addChild(Node child) {
        if (children == null) {
            children = new ArrayList<Node>();
        }
        children.add(child);
        child.level = this.level + 1;
        child.parent = this;
    }

    public void insertChildAt(int index, Node child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            addChild(child);
            return;
        } else {
            children.get(index);
            children.add(index, child);
        }
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public void setParent( Node parent ) {
        this.parent = parent;
        this.level = parent.level + 1;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return posicoes.toString();
    }

    public boolean equals( Object obj ) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Node other = (Node) obj;
      if (posicoes == null) {
        if (other.posicoes != null) return false;
      }
      //utilizado if porque não consegui utilizar o equals
      else if((posicoes[0] == other.posicoes[0])&&
              (posicoes[1] == other.posicoes[1])&&
              (posicoes[2] == other.posicoes[2])&&
              (posicoes[3] == other.posicoes[3])&&
              (posicoes[4] == other.posicoes[4])&&
              (posicoes[5] == other.posicoes[5])&&
              (posicoes[6] == other.posicoes[6])&&
              (posicoes[7] == other.posicoes[7])&&
              (posicoes[8] == other.posicoes[8])){
        return true;
      }
    return false;
    }


    //Calcula o valor pra ordenar depois..
    //PosicaoFinal como as peças deveriam estar

    public void CalcularHeuristica(){
        this.ValorHeuristica = 0;
        for(int i = 0; i <= 8; i++) {
            switch(i){
                case 0:{
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}
                    break;
                }
                case 1:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 2;}
                    break;
                }
                case 2:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}
                    break;
                }
                case 3:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}
                    break;
                }
                case 4:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}
                    break;
                }
                case 5:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 2;}
                    break;
                }
                case 6:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}
                    break;
                }
                case 7:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 1;}
                    break;
                }
                case 8:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}
                    break;
                }
            }
        }
    }

    public void Caminho(){
        System.out.println(Caminho(this));
    }

    private String Caminho(Node node){
        if((node.parent != null)&&(node.Movimento != null)){
            return Caminho(node.parent) + " \n " + node.getMovimento();
        }
        return "";
    }
}
