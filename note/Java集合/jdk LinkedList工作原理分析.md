# jdk LinkedList工作原理分析

List接口的实现类之一ArrayList的内部实现是一个数组，而另外一个实现LinkedList内部实现是使用双向链表。

### LinkList类中定义了一个内部类Node代码如下：

```java
private static class Node<E> {
    E item; // 节点所表示的值
    Node<E> next; // 后节点
    Node<E> prev; // 前节点

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

因为每个Node 有三个属性，分别是  item 当前节点，next 下一节点，prev 上一节点，因为保存了，实现双向链表的关键就在这两个节点指针。

LinkList 增删改动作都会处理节点的指向关系，

### LinkedList的3个属性：

```java
transient int size = 0; // 集合链表内节点数量

transient Node<E> first; // 集合链表的首节点

transient Node<E> last; // 集合链表的尾节点
```

### add(E e)

**添加元素到链表的最后一个位置**

实现了，空的List 第一次添加的初始化动作，和向尾部追加元素，

```java
public boolean add(E e) {
    linkLast(e);
    return true;
}

void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null); // 由于是添加元素的链表尾部，所以也就是这个新的节点是最后1个节点，它的前节点肯定是目前链表的尾节点，它的后节点为null
    last = newNode; // 尾节点变成新的节点
    if (l == null) // 如果一开始尾节点还没设置，那么说明这个新的节点是第一个节点，那么首节点也就是这个第一个节点
        first = newNode;
    else // 否则，说明新节点不是第一个节点，处理节点前后关系
        l.next = newNode;
    size++; // 节点数量+1
    modCount++;
}
```

## LinkedList和ArrayList的比较

1. LinkedList和ArrayList的设计理念完全不一样，ArrayList基于数组，而LinkedList基于节点，也就是链表。所以LinkedList内部没有容量这个概念，因为是链表，链表是无界的
2. 两者的使用场景不同，ArrayList适用于读多写少的场合。LinkedList适用于写多读少的场合。 刚好相反。 那是因为LinkedList要找节点的话必须要遍历一个一个节点，直到找到为止。而ArrayList完全不需要，因为ArrayList内部维护着一个数组，直接根据索引拿到需要的元素即可。
3. 两个都是List接口的实现类，都是一种集合