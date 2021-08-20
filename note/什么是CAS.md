### 什么是CAS？

* 是什么？(定义和特性？)
* 怎么做到的？(原理)
* 解决了什么问题？使用场景是什么样的？
* 相关问题？
* 相关博文链接

#### 1、什么是CAS？

CAS是英文单词**Compare And Swap**的缩写，翻译过来就是比较并替换。是一种有名的无锁（lock-free）算法，是一种原子操作，用于保证在无锁情况下的数据一致性的问题。

**CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。**

**更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。** 

也是一种现代 CPU 广泛支持的CPU指令级的操作，只有一步原子操作，所以非常快。

而且CAS避免了请求操作系统来裁定锁的问题，不用麻烦操作系统，直接在CPU内部就搞定了。

```
CAS有三个操作参数：

内存位置V（它的值是我们想要去更新的）
预期原值A（上一次从内存中读取的值）
新值B（应该写入的新值）
CAS的操作过程：将内存位置V的值与A比较（compare），如果相等，则说明没有其它线程来修改过这个值，所以把内存V的的值更新成B（swap），如果不相等，说明V上的值被修改过了，不更新，而是返回当前V的值，再重新执行一次任务再继续这个过程。

所以，当多个线程尝试使用CAS同时更新同一个变量时，其中一个线程会成功更新变量的值，剩下的会失败。失败的线程可以重试或者什么也不做。
```

**简单来说，CAS 的含义是“我认为原有的值应该是什么，如果是，则将原有的值更新为新值，否则不做修改，并告诉我这个值现在是多少”。（这段描述引自《Java并发编程实践》）**

-------

##### 1.1锁（lock）的代价

锁是用来做并发最简单的方式，其代价也是最高的，Java在JDK1.5之前都是靠synchronized关键字来加锁。但是加锁机制会有如下几个问题：

- 加锁、释放锁会需要操作系统进行上下文切换和调度延时，在上下文切换的时候，cpu之前缓存的指令和数据都将失效，这个过程将增加系统开销。
- 多个线程同时竞争锁，锁竞争机制本身需要消耗系统资源。没有获取到锁的线程会被挂起直至获取锁，在线程被挂起和恢复执行的过程中也存在很大开销。
- 等待锁的线程会阻塞，影响实际的使用体验。如果被阻塞的线程优先级高，而持有锁的线程优先级低，将会导致优先级反转(Priority Inversion)。

------

##### 1.2 乐观锁与悲观锁

**悲观锁：**是认为别的线程会修改值。
独占锁是一种悲观锁，synchronized就是一种独占锁。synchronized加锁后就能够确保程序执行时不会被其它线程干扰，得到正确的结果。

**乐观锁：**本质上是乐观的，认为别的线程不会去修改值。

如果发现值被修改了，可以再次重试。CAS机制（Compare And Swap）就是一种乐观锁。

从思想上来说，Synchronized属于**悲观锁**，悲观地认为程序中的并发情况严重，所以严防死守。CAS属于**乐观锁**，乐观地认为程序中的并发情况不那么严重，所以让线程不断去尝试更新。

---

#### 2、怎么做到的？(原理)

----

以 Java中的Atomic 相关类举例子，在1.8版本之后，实现方式是封装在unsafe类中，代码如下：

```java
    //调用JNI实现CAS
	public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        return v;
    }
```

---

#### 3、解决了什么问题？JAVA中有哪些地方使用了CAS机制？

可以用CAS在无锁的情况下实现原子操作，但要明确应用场合，非常简单的操作且又不想引入锁可以考虑使用CAS操作，当想要非阻塞地完成某一操作也可以考虑CAS。不推荐在复杂操作中引入CAS，会使程序可读性变差，且难以测试，同时会出现ABA问题。

**JAVA 的 Atomic 类 和Lock系列类的底层实现都有使用到CAS机制。**

PS：从思想上来说，CAS属于是乐观锁，乐观地认为程序中的并发情况不那么严重，所以使用场景也应该是并发情况不严重的场景。

---

#### 4、相关问题

##### 4.1 CAS有什么缺点

---

* 首先就是ABA 问题
* CPU开销比较大。
  - 以JAVA Auomic 类 为列，底层实现中是do while 循环，如果并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力。
* 不能保证代码块的原子性，只能保证变量原子性

##### 4.1.1 ABA问题如何解决？

JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题。**如果当前引用 == 预期引用，并且当前标志等于预期标志，则以原子方式将该引用和该标志的值设置为给定的更新值。**源码如下：

```java
/**
 *expectedReference - 该引用的预期值
 *newReference - 该引用的新值
 *expectedStamp - 该标志的预期值
 *newStamp - 该标志的新值
 */
public boolean compareAndSet(V   expectedReference,
                                 V   newReference,
                                 int expectedStamp,
                                 int newStamp) {
        Pair<V> current = pair;
        return
            expectedReference == current.reference &&
            expectedStamp == current.stamp &&
            ((newReference == current.reference &&
              newStamp == current.stamp) ||
             casPair(current, Pair.of(newReference, newStamp)));
    }
```



---

##### 4.2 CAS 和 Synchronize的区别是什么？适合用于什么样的场景？有什么样的优缺点？





#### 5、相关博文连接

[漫画：什么是CAS](https://www.cnblogs.com/myopensource/p/8177074.html)

https://segmentfault.com/a/1190000015239603

https://www.jianshu.com/p/6a129e4687f6



**小结：**



