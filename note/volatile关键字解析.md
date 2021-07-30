##### volatile 关键字解析

volatile  是JAVA虚拟机提供的 **轻量级** 同步机制

volatile 的特性：

volatile 保证**可见性、有序性、** **不保证原子性**

---

以上三个特性的解释：

JMM [JAVA Memory Model ](https://zhuanlan.zhihu.com/p/29881777)



[volatile 作用和原理](https://www.cnblogs.com/monkeysayhi/p/7654460.html)

volatile 是如何保证内存可见性的：

volatile的特殊规则就是：

- read、load、use动作必须**连续出现**。
- assign、store、write动作必须**连续出现**。

所以，使用volatile变量能够保证:

- 每次`读取前`必须先从主内存刷新最新的值。
- 每次`写入后`必须立即同步回主内存当中。

也就是说，**volatile关键字修饰的变量看到的随时是自己的最新值**。线程1中对变量v的最新修改，对线程2是可见的。



是什么？

怎么做到的？

这么做出于什么目的？





[**volatile能实现多线程同步吗？？？NO**](https://blog.csdn.net/p10010/article/details/50418974)
