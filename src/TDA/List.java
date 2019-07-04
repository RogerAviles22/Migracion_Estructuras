/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

/**
 *
 * @author Rogencio
 */
public interface List <E>{    
    boolean isEmpty();
    int size();
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean removeFirst();
    boolean removeLast();
    E getFirst();
    E getLast();    
}
