/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.util.Iterator;

/**
 *
 * @author Rogencio
 */
public class CircularLinkedList<E> implements List<E>, Iterable<E>{
    
    private Node<E> last;
    private int efectivo;
    
    public CircularLinkedList(){        
        last = null;
        efectivo = 0;
    }    
    
    @Override
    public boolean isEmpty() {
        return last==null;
    }
    
    
    @Override
    public int size() {
        return efectivo;
    }

    
    @Override
    public boolean addFirst(E element) {
        Node<E> node = new Node<>(element);
        
        if(element == null) return false;
        else if(isEmpty()){
            last = node;
            last.setNext(node);   
        }
        else{
            Node<E> first = last.getNext(); 
            node.setNext(first);
            last.setNext(node);   
        }
        efectivo++;
        return true;
    }

    
    @Override
    public boolean addLast(E element) {
        Node<E> node = new Node<>(element);
        
        if(element == null) return false;
        else if(isEmpty()){
            last = node;
            last.setNext(node);   
        }
        else{
            node.setNext(last.getNext());
            last.setNext(node);
            last = node;
        }
        efectivo++;
        return true;
    }

    
    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return last.getNext().getData();
    }

    
    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return last.getData();
    }
    
    
    @Override
    public boolean removeFirst() {
        Node<E> first = last.getNext();
        if(isEmpty()) return false;
        
        else if (first == last){
            last.setData(null); 
            last = null;
        }
        else{
            last.setNext(first.getNext());
            first.setData(null); 
            first.setNext(null);
        }
        efectivo--;
        return true;
    }

    
    @Override
    public boolean removeLast() {
        Node<E> first = last.getNext();
        if(isEmpty()) return false;
        
        else if (first == last){
            last.setData(null); 
            last = null;
        }
        else{
            last.setNext(first.getNext());
            first.setData(null); 
            first.setNext(null);
        }
        efectivo--;
        return true;
    }
    
    @Override
    public Iterator<E> iterator(){
        
        return new Iterator<E>() {
            
            private Node<E> p = last.getNext();
            
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E element = p.getData();
                p = p.getNext();
                return element;
            }
        };
    }
        
    @Override
    public String toString(){
        
        if(isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node<E> temp = last.getNext();
        do{
            sb.append(temp.getData()+",");
            temp = temp.getNext();
            
        }while(temp!=last.getNext());
        
        return sb.substring(0,sb.length()-1)+"]";
        
    }    
    
    @Override
    public boolean equals(Object o){
        
        if(o == null || !(o instanceof CircularLinkedList)) return false;
        else if(o == this) return true;
        
        CircularLinkedList<E> other = (CircularLinkedList<E>)o;
        if(this.efectivo!= other.efectivo) return false;
        
        Node<E> temp = this.last.getNext();
        Node<E> temp2 = other.last.getNext();
        do{
            if(!(temp.getData().equals(temp2.getData()))){
                return false;
            }        
            temp = temp.getNext();
            temp2 = temp2.getNext();
            
        }while(temp!=last.getNext());
        
        return true;
    }
    
    
    private E get(int index) {
        
        if(isEmpty()) return null;
        else if(index>=0 && index<efectivo){
            int cont = 0;
            for(Node<E> temp= last.getNext();temp!=null;temp=temp.getNext()){
                if(cont==index){
                    return temp.getData();
                }
                cont++;
            }
        }
        return null;
    }
    
}    
    class Node<E>{
        
        private E data;
        private Node<E> next;
    
        public Node (E data){

            this.data = data;
            this.next = this;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

