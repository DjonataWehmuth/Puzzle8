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
public class Tabuleiro {
 final int MOVEU_CIMA     = 1;
    final int MOVEU_BAIXO    = 2;
    final int MOVEU_ESQUERDA = 3;
    final int MOVEU_DIREITA  = 4;

    private Tree Tree;
    private List<Node> Fechado;
    private List<Node> Aberto;

    public List<Node> getAberto() {
        return Aberto;
    }

    public void setAberto(List<Node> Aberto) {
        this.Aberto = Aberto;
    }
    private final Node NodeSolucao;

    public Tree getTree() {
        return Tree;
    }

    public void setTree(Tree Tree) {
        this.Tree = Tree;
    }

    public List<Node> getFechado() {
        return Fechado;
    }

    public void setFechado(List<Node> Fechado) {
        this.Fechado = Fechado;
    }

    public Tabuleiro(Integer[] solucao, Integer[] problema) {
        this.Tree = new Tree();
        this.Fechado = new ArrayList<>();
        this.Aberto = new ArrayList<>();
        this.Tree.setRootElement(new Node());
        this.Tree.add(new Node(problema));
        this.NodeSolucao = new Node(solucao);
    }

    public void PercorreTree(){
       boolean achou = false;
       for (Node CurrentNode : this.Tree.getRootElement().getChildren()) {
            if (CurrentNode.equals(this.NodeSolucao)){
                achou = true;
                System.out.println("Já está ordenado");
                break;
            }
            else{
                 if (CurrentNode.getAberto()) {
                     open(CurrentNode);
                     for (Node Criado: CurrentNode.getChildren()){
                       Aberto.add(Criado);
                     }
                     Fechado.add(CurrentNode);
                     CurrentNode.Fechar();
                 }
            }
        }

        while ((this.Aberto.size() > 0) && !(achou)) {
            achou = PercorreFilhos();
        }
        if ((this.Aberto.size() == 0)&&(!achou))
            System.out.println("Vetor de abertos vazio.");
    }

    protected boolean PercorreFilhos(){
        OrdenarAbertos();

        List<Node> ListaTemp = new ArrayList<>();

        for (Node CurrentNode : this.Aberto) {
           if (CurrentNode.equals(this.NodeSolucao)){
               CurrentNode.Caminho();
               return true;
           }
           else if (CurrentNode.getAberto()) {
                open(CurrentNode);
                ListaTemp.add(CurrentNode);
                CurrentNode.Fechar();
           }
           else{
               System.out.println("Registro fechado na lista aberta");
           }
       }

       for(Node CurrentNode: ListaTemp){
           this.Aberto.remove(CurrentNode);
           this.Fechado.add(CurrentNode);
           for(Node NodeFilho: CurrentNode.getChildren()){
                if (!(ListaContemNode(Fechado, NodeFilho)) && !(ListaContemNode(Aberto, NodeFilho))) {
                     Aberto.add(NodeFilho);
                }
           }
       }
       return false;
    }

    public Boolean ListaContemNode(List<Node> Lista, Node UmNode){
        for(Node CurrentNode: Lista){
            if (UmNode.equals(CurrentNode))
                return true;
        }
        return false;
    }

    protected void open(Node current) {
        for (Movimento problem : GetProblemas(current.getPosicoes())) {
            Node node = new Node(problem.vetor);
            node.setMovimento(problem.Movimento);
            if (!(ListaContemNode(Fechado,node)) && !(ListaContemNode(Aberto, node))) {
                current.addChild(node);
            }
        }
    }

    protected List<Movimento> GetProblemas(Integer[] posicoes){
        List<Movimento> list = new ArrayList<Movimento>();

        for(int i = 0; i <= 8; i++) {
            switch(i){
                case 0:{
                    if ((posicoes[i] == 0) && (i == 0)){
                        list.add(GetClone(posicoes, i, 1, MOVEU_DIREITA));
                        list.add(GetClone(posicoes, i, 3, MOVEU_BAIXO));
                    }
                }
                case 1:{
                    if ((posicoes[i] == 0) && (i == 1)){
                        list.add(GetClone(posicoes, i, 2, MOVEU_DIREITA));
                        list.add(GetClone(posicoes, i, 4, MOVEU_BAIXO));
                        list.add(GetClone(posicoes, i, 0, MOVEU_ESQUERDA));
                    }
                }
                case 2:{
                    if ((posicoes[i] == 0) && (i == 2)){
                        list.add(GetClone(posicoes, i, 1, MOVEU_ESQUERDA));
                        list.add(GetClone(posicoes, i, 5, MOVEU_BAIXO));
                    }
                }
                case 3:{
                    if ((posicoes[i] == 0) && (i == 3)){
                        list.add(GetClone(posicoes, i, 0, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 4, MOVEU_DIREITA));
                        list.add(GetClone(posicoes, i, 6, MOVEU_BAIXO));
                    }
                }
                case 4:{
                    if ((posicoes[i] == 0) && (i == 4)){
                        list.add(GetClone(posicoes, i, 1, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 3, MOVEU_ESQUERDA));
                        list.add(GetClone(posicoes, i, 5, MOVEU_DIREITA));
                        list.add(GetClone(posicoes, i, 7, MOVEU_BAIXO));
                    }
                }
                case 5:{
                    if ((posicoes[i] == 0) && (i == 5)){
                        list.add(GetClone(posicoes, i, 2, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 4, MOVEU_ESQUERDA));
                        list.add(GetClone(posicoes, i, 8, MOVEU_BAIXO));
                    }
                }
                case 6:{
                    if ((posicoes[i] == 0) && (i == 6)){
                        list.add(GetClone(posicoes, i, 3, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 7, MOVEU_DIREITA));
                    }
                }
                case 7:{
                    if ((posicoes[i] == 0) && (i == 7)){
                        list.add(GetClone(posicoes, i, 4, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 6, MOVEU_ESQUERDA));
                        list.add(GetClone(posicoes, i, 8, MOVEU_DIREITA));
                    }
                }
                case 8:{
                    if ((posicoes[i] == 0) && (i == 8)){
                        list.add(GetClone(posicoes, i, 5, MOVEU_CIMA));
                        list.add(GetClone(posicoes, i, 7, MOVEU_ESQUERDA));
                    }
                }
            }
        }
        return list;
    }

    protected Movimento GetClone(Integer[] original, Integer posicaoZero,
              Integer posicaoMovimento,
              Integer movimentacao){
       Integer[] clone = new Integer[original.length];
       clone = original.clone();

       clone[posicaoZero] = clone[posicaoMovimento];
       clone[posicaoMovimento] = 0;

       return new Movimento(clone, movimentacao);
    }

    public void OrdenarAbertos(){

        if (Aberto.size() > 1000){
            List<Node> ListaTemp = new ArrayList<>();
            int Media = 0;
            for(Node gambia: Aberto){
                Media += gambia.getValorHeuristica();
            }
            Media = Media / Aberto.size();
            for(Node gambia: Aberto){
                if (gambia.getValorHeuristica() > Media)
                    ListaTemp.add(gambia);
            }
            for(Node gambia: ListaTemp){
                Aberto.remove(gambia);
            }
        }

        for (int i = 0; i < Aberto.size(); ++i) {
            for (int j = 0; j < Aberto.size() - 1; ++j) {
                Node tmp = Aberto.get(j);
                Node tmp2 = Aberto.get(j+1);
                if (tmp.getValorHeuristica() > tmp2.getValorHeuristica()) {
                    Node temp = tmp;
                    Aberto.set(j, tmp2);
                    Aberto.set(j+1, temp);
                }
            }
        }
    }
}
