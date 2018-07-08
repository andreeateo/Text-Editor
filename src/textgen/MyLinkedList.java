package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author andreea teodor   
 */

public class MyLinkedList<E> extends AbstractList<E>
{
  LLNode<E> head = null;
  LLNode<E> tail = null;
  int size = 0;

  public MyLinkedList()
  {
  }

  public boolean add(E element)
  {
    if(element == null) 
      throw new NullPointerException();
    
    LLNode newNode = new LLNode(element);

    if (head == null)
    {
      head = newNode;
      tail = newNode;
    }
    else
    {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
    return true;
  }

  public E get(int index)
  {
    if (index < 0 || index >= this.size)
      throw new IndexOutOfBoundsException();

    LLNode<E> p = new LLNode<E>(null);
    p = this.head;
    for (int i = 0; i < index; i++)
    {
      p = p.next;
    }
    return p.data;
  }

  public void add(int index, E element)
  {
    if(element == null) 
      throw new NullPointerException();
    if (index == size) // empty list and add at the end of the list
    {
      this.add(element);
      return;
    }
    else if (index < 0 || index > this.size)
      throw new IndexOutOfBoundsException();
    LLNode<E> newNode = new LLNode<E>(element);
    LLNode<E> currNode = null;
    currNode = this.head;

    for (int i = 0; i < index; i++)
    {
      currNode = currNode.next;
    }

    newNode.next = currNode;

    if (currNode.prev == null) // add at the beginning of the list
    {
      newNode.prev = head;
      head = newNode;
    }
    else // add in the middle of the list
    {
      newNode.prev = currNode.prev;
      currNode.prev.next = newNode;
    }

    currNode.prev = newNode;
    size++;
  }

  public int size()
  {
    return size;
  }

  
  public E remove(int index)
  {
    if (index < 0 || index >= this.size)
      throw new IndexOutOfBoundsException();
    LLNode<E> currNode = null;
    currNode = this.head;
    for (int i = 0; i < index; i++)
    {
      currNode = currNode.next;
    }
    if (currNode.prev == null)            // remove Front list
    {
      head = currNode.next;
      currNode.next = head;
    }
    else if (currNode.next == null)       // remove end list
    {
      currNode.prev = tail;
      tail = currNode.prev;
    }
    else
    {
      currNode.prev.next = currNode.next; // remove Mid list
      currNode.next.prev = currNode.prev;
    }
    size--;
    return currNode.data;
  }

  public E set(int index, E element)
  {
    if (index < 0 || index >= this.size)
      throw new IndexOutOfBoundsException();

    if(element == null) 
      throw new NullPointerException();
    
    LLNode<E> currNode = null;
    currNode = this.head;
    
    for (int i = 0; i < index; i++)
    {
      currNode = currNode.next;
    }    
   
    E original_data = currNode.data;
    currNode.data = element;
    
    return original_data;
  }
}

class LLNode<E>
{
  LLNode<E> prev;
  LLNode<E> next;
  E data;

  public LLNode()
  {
    this.prev = null;
    this.next = null;
  }

  public LLNode(E e)
  {
    this.data = e;
    this.prev = null;
    this.next = null;
  }

  public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode)
  {
    this(e);
    this.next = prevNode.next;
    prevNode.next = this;
    this.prev = nextNode.prev;
    nextNode.prev = this;
  }

}
