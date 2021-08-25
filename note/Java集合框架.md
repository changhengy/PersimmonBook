

----

[JAVA 集合框架图](http://www.justdojava.com/2019/09/16/java-collection-1/)

↑ 这个人的博客好屌

[极客学院 集合类的教程](https://wiki.jikexueyuan.com/project/java-collection/hashmap.html)

---

# 初探 java 集合框架图

实际开发中，经常用到 java 的集合框架，比如 ArrayList 、 LinkedList 、 HashMap 、 LinkedHashMap，几乎经常接触到，虽然用的多，但是对集合的整体框架，基础知识还是不够系统，今天想和大家一起来梳理一下！

## 一、集合类简介

> Java集合就像一种容器，可以把多个对象(实际上是对象的引用，但习惯上都称对象)保存在容器中，从 Java 5 增加了泛型以后，Java 集合可以记住容器中对象的数据类型，使得编码更加简洁、健壮。

Java 集合大概可以分为两类，一个是Collection，另外一个是Map

- Collection：主要由List、Set、Queue子接口类，
  - List 代表有序、可重复的集合
  - Set 代表无序、不可重复的集合
  - Queue 队列的集合实现
- Map ：则代表具有映射关系的键值对集合。
  - HashMap实现原理：**JDK 1.7** ： Table数组+ Entry链表；**JDK1.8** : Table数组+ Entry链表/红黑树；(为什么要使用红黑树？)

```java
java.util.Collection 下的接口和继承类关系简易结构图：
```

![JavaCollectionPF](.\images\JavaCollectionPF.png)

```java
java.util.Map 下的接口和继承类关系简易结构图：
```

![JavaCollectionPF](.\images\JavaMapPF.png)

----------

**Java 集合框架中主要封装的是经典的数据结构和算法，比如：动态数组、双向链表、队列、栈、Set、Map、等**

------------------

* 将集合框架挖掘处理，可以分为以下几个部分

1)、**数据结构**

`List`列表、`Queue`队列、`Dueue`双向队列、`Set`集合、`Map`映射

2)、**比较器**

`Comparator`比较器、`Comparable `排序接口

3)、**封装算法**

`Collections`常用算法类、`Arrays`静态数组的排序、查找算法

4)、**迭代器**

`Iterator`通用迭代器、`ListIterator`针对`Iterator`特化的迭代器

## 二、有序列表List

> List 的集合的特点就是存取有序、可以存储重复的元素、可以使用下标对元素进行操作
>
> List 的主要实现类有：ArryList、LinkedList、Vector、Stack

### 2.1、ArryList

ArryList是一个动态数组结构，支持随机存取，尾部插入方便，内部插入删除效率低（因为要变更部位之后的所有元素都要移位复制），

如果内部数组容量不足则进行自动扩容，因此当数组元素较多时，效率很低。

### 2.2、LinkedList

LinkedList是双向链表结构，所以是任意位置插入方便，但是不能随机查询，查询时只能从一段开始遍历，知道找到查询对象，然后返回；

不过在增删改时不需要像ArryList那样进行copy 动作，所以增删改的效率相对较高，但是每个节点元素需要保存前驱和后继节点指针，所以占用内存稍多

> ArryList 和 LinkedList 的区别就和C++中数组和链表的数据结构相仿，ArryList数组增删改效率低，查效率高，LinkedList增删改效率高，查效率低，
>
> 另外猜测还有内存连续性的差别

### 2.3 Vector

Vector 也是一个动态数组，引入时间非常早，在jdk1.1中引入，后来在jdk1.2中引入的ArryList，两者方法基本相同，

主要区别在于 Vector 是线程安全的，原理是 `get`、 `set`、`add`、`remove`、`insert`、等设计元素操作的方法都用了**`synchronized`**修饰，这样也就导致执行效率较低，关于`Vector`，现在用的很少了，

如果想使用线程安全的ArryList可以使用以下两种方法：

* 使用 Collections  工具类中的  synchronizedList 方法	

  * ```java
    List<Object> list =Collections.synchronizedList(new ArrayList<Object>());
    ```

* 使用 CopyOnWriteArrayList 类，写时复制 ArryList 

  * ```java
    final CopyOnWriteArrayList<Object> cowList = new CopyOnWriteArrayList<String>(Object);
    ```

### 2.4 Stack

从上面的类关系图可以看出 Stack 是 Vector 的子类，所以本质也是动态数组，但是不同的是，他的数据结构是先进后出(类似于压栈弹栈的方式)，所以叫栈，

关于 `Stack ` 现在用的也比较少，因为有双端队列`ArryDeque`，可以实现`Stack` 所有功能，且效率更高。

## 三、集合Set

> Set 集合的特点：元素不可重复，存取无序，无下标
>
> Set 接口的主要实现类，如图中所示，HashSet、LinkedSet、TreeSet

### 3.1HashSet

HashSet实现是基于HashMap的`key`实现的，所以元素不可重复，特性同HashMap。 

> 我记得HashMap合意有一个key是null，那么 HashSet 是不是可以有一个元素是null

### 3.2 LinkedSet

LinkedHashSet底层也是基于 LinkedHashMap 的`k`实现的，一样元素不可重复，特性同 LinkedHashMap。

### 3.3 TreeSet

同样的，TreeSet 也是基于 TreeMap 的`k`实现的，同样元素不可重复，特性同 TreeMap；

Set集合的实现，基本都是围绕着Map中的Key 做文章，使用的Map 中 key 的无序、不可重复的特性；

所以我们只需要重点关注Map 的实现即可

## 四、队列 Queue

> Queue是一个队列集合，队列通常指的是FIFO(先进先出)容器，
>
> 新元素插入(offer)到队列尾部，访问元素(poll)会返回队列头部的元素
>
> 通常来讲，队列不允许随机访问队列里的元素
>
> Queue 接口的主要实现类有：ArryDeque、LinkList、PriorityQueue
>
> Deque接口(双向队列)的两个主要实现类是ArrayDeque和LinkedList。
>
> 其中ArrayDeque底层使用循环数组实现双向队列，而LinkedList是使用链表实现，

### 4.1 ArryDeque     [ArrayDeque工作原理分析](https://fangjian0423.github.io/2016/04/03/jdk_arraydeque/)

ArrayDeque底层使用循环数组实现双向队列，循环数组，内部有3个属性，分别是：

```java
Object[] elements; // 数组
int head; // 头索引
int tail; // 尾索引
```

因此它具有“FIFO队列”及“栈”的方法特性。既可以先进先出，也可以先进后出，分别实现在不同的接口方法中，以下是测试代码：

**先进先出**

```java
ArrayDeque<String> queue = new ArrayDeque<String>();
// 入队操作
queue.add("AAA");
queue.add("BBB");
queue.add("CCC");
System.out.println(queue);
// 获取但是不出队，peek 方法
System.out.println(queue.peek());
System.out.println(queue);
// 出队
System.out.println(queue.pop());
System.out.println(queue);
```

输出结果：

```java
[AAA, BBB, CCC]
AAA
[AAA, BBB, CCC]
AAA
[BBB, CCC]
```

**先进后出**

```java
ArrayDeque<String> stack = new ArrayDeque<String>();
// 压栈,此时AAA在最下,CCC在最外
stack.push("AAA");
stack.push("BBB");
stack.push("CCC");
System.out.println(stack);
// 获取最后添加的元素,但不删除
System.out.println(stack.peek());
System.out.println(stack);
// 弹栈，弹出最后添加的元素
System.out.println(stack.pop());
System.out.println(stack);
```

输出结果：

```java
[CCC, BBB, AAA]
CCC
[CCC, BBB, AAA]
CCC
[BBB, AAA]
```

### 4.2 LinkList    [LinkedList工作原理分析](http://fangjian0423.github.io/2016/03/27/jdk_linkedlist/)

LinkList  是List 的接口实现类，同时也是Deque接口的实现类，底层实现是一种 **双向链表** 的数据结构，

LinkList 可以根据索引来获取元素，同时增删改效率也较高，如果查找的话需要遍历整合集合，效率较低，

LinkedList同时实现了stack、Queue、PriorityQueue的所有功能。

**测试代码**

```java
LinkedList<String> linkedList = new LinkedList<>();
linkedList.add("AAA");
//入队
linkedList.offer("BBB");
//压栈
linkedList.push("CCC");
//双端的另一端入队
linkedList.addFirst("DDD");
System.out.println(linkedList);
linkedList.forEach(ele -> System.out.println("遍历中 ：" + ele));
//获取队头
System.out.println("获取队头 " + linkedList.peek());
System.out.println("获取队头 " + linkedList.peekFirst());
//获取队尾
System.out.println("获取队尾 "  + linkedList.peekLast());
// 弹栈
System.out.println(linkedList.pop());
System.out.println(linkedList);
// 从队列的前端弹栈
System.out.println(linkedList.pollFirst());
System.out.println(linkedList);
// 对队列的尾部弹栈
System.out.println(linkedList.pollLast());
System.out.println(linkedList);
```

输出结果：

```
[DDD, CCC, AAA, BBB]
遍历中 ：DDD
遍历中 ：CCC
遍历中 ：AAA
遍历中 ：BBB
获取队头 DDD
获取队头 DDD
获取队尾 BBB
DDD
[CCC, AAA, BBB]
CCC
[AAA, BBB]
BBB
[AAA]
```

### 4.3 PriorityQueue

PriorityQueue是 Queue 的一个实现类，但是此类的中存储元素的顺序并不会按照元素的添加顺序进行排列，而是内部会按照元素的大小进行排列，

是一个能进行自动排序的队列。

**测试代码**

```java
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
Random rand = new Random();
for (int i = 0; i < 10; i++) {
    priorityQueue.add(rand.nextInt(90) + 10);
}
System.out.println("处理前的数据" + priorityQueue);

System.out.println("\n处理后的数据");
for (int i = 0; i < 10; i++) {
    System.out.print(priorityQueue.poll() +", ");
}
```

输出结果：

```
处理前的数据[27, 40, 44, 55, 43, 77, 68, 99, 86, 95]

处理后的数据
27, 40, 43, 44, 55, 68, 77, 86, 95, 99, 
```

五、映射表 Map

> Map是一个双列集合，其中保存的是键值对，键要求保持唯一性，值可以重复
>
> Map接口的主要实现类有：HashMap、LinkedHashMap、TreeMap、IdentityHashMap、WeakHashMap、HashTable、Properties

5.1 HashMap、

继承自抽象类AbstractMap，

5.2 LinkedHashMap、

5.3 TreeMap、

5.4 IdentityHashMap、

5.5 WeakHashMap、

5.6 HashTable、

5.7 Properties



### 六、比较器 Comparator

`Comparator`比较器、`Comparable `排序接口

七、常用工具类

3)、**封装算法**

`Collections`常用算法类、`Arrays`静态数组的排序、查找算法

八、迭代器

`Iterator`通用迭代器、`ListIterator`针对`Iterator`特化的迭代器































































