

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

## 二、有序列表

> List 的集合的特点就是存取有序、可以存储重复的元素、可以使用下标对元素进行操作
>
> List 的主要实现类有：ArryList、LinkedList、Vector、Stack

### 2.1、ArryList

















