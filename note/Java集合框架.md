

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

## 五、映射表 Map

> Map是一个双列集合，其中保存的是键值对，键要求保持唯一性，值可以重复
>
> Map接口的主要实现类有：HashMap、LinkedHashMap、TreeMap、IdentityHashMap、WeakHashMap、HashTable、Properties

### 5.1 HashMap

继承自抽象类AbstractMap，key 不可重复，因为使用的是哈希表存储元素，所以输入的数据的顺序和输出数据的顺序基本不一致，

另外，HashMap最多只允许一个key 为null

### 5.2 LinkedHashMap

HashMap 的子类，内部使用链表数据结构来记录插入的顺序，使得输入的记录顺序和输出的记录顺序是相同的。

**LinkedHashMap与HashMap最大的不同处在于，LinkedHashMap输入的记录和输出的记录顺序是相同的**

### 5.3 TreeMap

能够把它保存的记录根据键排序，默认是按键值的升序排序，也可以指定排序的比较器，当用 Iterator 遍历时，得到的记录是排过序的；如需使用排序的映射，建议使用 TreeMap。TreeMap实际使用的比较少！

### 5.4 IdentityHashMap

继承自AbstractMap，与HashMap有些不同，在获取元素的时候，通过`==`代替`equals ()`来进行判断，**比较的是内存地址**。

### 5.5 WeakHashMap

### 5.6 HashTable

HashTable，一个元老级的类，**Since:**JDK1.0，键值不能为空，方法都加了`synchronized`同步锁，是线程安全的，之前状态机中存状态就用的这个类，

HashTable 和 HashMap 的区别，类似于ArryList 和 Vector。

同时，HashMap 是 HashTable 的轻量级实现，他们都完成了Map 接口，区别在于 HashMap 允许K和V为空，而HashTable不允许K和V为空，由于非线程安全，效率上可能高于 Hashtable。

如果在多线程环境中需要使用HashMap，有两个方案：

> 使用 Collections 工具包下的 算法进行实现，

```java
Map<String, Object> map =Collections.synchronizedMap(new HashMap<>());
```

> 使用并发工具包中的`ConcurrentHashMap`类

### 5.7 Properties

Properties 继承自Hashtable，并新增了load() 和 store() ，可以直接导入或者将映射写入文件，另外，Properties  的 键值都是String 类型。

盲猜Spring Boot 应该就是使用Properties 类进行的文件加载，[廖雪峰的使用方法](https://www.liaoxuefeng.com/wiki/1252599548343744/1265119084411136)

如果有多个`.properties`文件，可以反复调用`load()`读取，后读取的key-value会覆盖已读取的key-value

这应该就是，Spring Boot 不同路径下 properties  文件优先级的实现原理。

## 六、比较器 Comparator

> `Comparator`比较器、`Comparable `排序接口
>
> `Comparator`和 `Comparable `接口都是用来比较大小的，一般在TreeMap 和 TreeSet 中使用比较多，主要用于解决排序问题。
>
> **[Java中Comparable与Comparator的区别](https://www.jianshu.com/p/fa1a1089d44d)**

### 6.1 Comparable 

Comparable ：对每个实现 Comparable 的类实例进行整体排序。

```java
package java.lang;
import java.util.*;
public interface Comparable<T> {
    public int compareTo(T o);
}
```

>  **若一个类实现了Comparable 接口，实现 Comparable 接口的类的对象的 List 列表 ( 或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。**

> **此外，实现 Comparable 接口的类的对象 可以用作 “有序映射 ( 如 TreeMap)” 中的键或 “有序集合 (TreeSet)” 中的元素，而不需要指定比较器。**

**测试代码：**

```java
public class CollectionNotSafeDemo {
    public static void main(String[] args) {
        Person person_1 = new Person(15, "张三");
        Person person_2 = new Person(18, "李四");
        Person person_3 = new Person(20, "王五");

        List<Person> list = new ArrayList<>();
        list.add(person_1);
        list.add(person_3);
        list.add(person_2);
        list.add(person_1);
        list.add(person_3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}

class Person implements Comparable<Person>{
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +"age=" + age +", name='" + name + '\'' +'}';
    }
}
```

**输出结果：**

[Person{age=15, name='张三'}, Person{age=20, name='王五'}, Person{age=18, name='李四'}, Person{age=15, name='张三'}, Person{age=20, name='王五'}]
[Person{age=15, name='张三'}, Person{age=15, name='张三'}, Person{age=18, name='李四'}, Person{age=20, name='王五'}, Person{age=20, name='王五'}]

### 6.2、Comparator

```java
Collections.sort( StudentList , new StudentComparator()) 
不仅要传入待排序的列表，还要传入实现了Comparator的类的对象
```

## 七、常用工具类

`Collections`常用算法类、`Arrays`静态数组的排序、查找算法

### 7.1、Collections类

#### 7.1.1、addAll

#### 7.1.2、binarySearch

#### 7.1.3、sort

#### 7.1.4、shuffle

#### 7.1.5、reverse

#### 7.1.6、synchronized系列



### 7.2、Arrays类

#### 7.2.1、asList

#### 7.2.2、sort

#### 7.2.3、binarySearch

#### 7.2.4、copyOf

#### 7.2.5、copyOfRange

#### 7.2.6、equals和deepEquals

#### 7.2.7、toString和deepToString

## 八、迭代器

`Iterator`通用迭代器、`ListIterator`针对`Iterator`特化的迭代器































































