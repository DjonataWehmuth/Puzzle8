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
public class Tree {
 private Node rootElement;

    public Node getRootElement() {
        return this.rootElement;
    }

    public void setRootElement(Node rootElement) {
        this.rootElement = rootElement;
    }

    public List<Node> toList() {
        List<Node> list = new ArrayList<Node>();
        walk(rootElement, list);
        return list;
    }

    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public String toString() {
        return toList().toString();
    }

    /**
     * percorre os nodes de forma recursiva e da um add nos elementos filhos...
     * é usado no toList()
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(Node element, List<Node> list) {
        list.add(element);
        for (Node data : element.getChildren()) {
            walk(data, list);
        }
    }

    public boolean Contem(Node element){
        boolean result = false;
        for (Node data : getRootElement().getChildren()) {
            if (data.equals(element)){
                    result = true;
                    break;
            }else if (data.getNumberOfChildren() > 0)
                for (Node filho : element.getChildren()) {
                    result = Contem(filho);
                    if (result)
                        break;
                }
            if (result)
                break;
        }
        return result;
    }

    public void add(Node element){
        this.rootElement.addChild(element);
    }
}
