#### volatile 关键字解析

* 是什么？(定义和特性？)

* 怎么做到的？(原理)

* 解决了什么问题？(应用场景)

* 使用场景是什么样的？

  

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





[**volatile能实现多线程同步吗？？？NO**](https://blog.csdn.net/p10010/article/details/50418974)



----

#### volatile 可见性验证和不保证原子性验证

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Mydata {
    volatile int num = 0 ;

    AtomicInteger number = new AtomicInteger();

    void setNum60() {
        num = 60;
    }

    public void addPlusPlus(){
        num++;
        number.addAndGet(1);
    }
}
/**
 * 1.验证Volatile的可见性
 *   1.1假如 int number = 0;number变量之前根本没有添加volatile关键字修饰，没有可见性
 *   1.2 添加了volatile 可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 *  2.1原子性指的是什么意思？
 *    不可分隔，完整性 也即某个线程正在做某个具体业务时 中间不可以被加赛或者被分隔 需要整体完整 要不同时成功  要么同时失败
 *
 *  2.2 volatile 不保证原子性的案例演示
 *     1.加synchronized
 *     2.AtomicInteger
 *
**/

public class Test {
    public static void main(String[] args) {
        extracted();

        Mydata myData = new Mydata();
        for(int i=0;i<20;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        //需要等待20个线程全都计算完成后 在用main现在取地最终的结果是多少
        while(Thread.activeCount()>2){//mian线程 GC线程
            Thread.yield();
        }
        //验证后 最终结果不等于20000
        System.out.println("finaly i=="+ myData.num);
        System.out.println("finaly i=="+ myData.number.get());
    }

    private static void extracted() {
        Mydata mydata = new Mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in ...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mydata.setNum60();
            System.out.println(Thread.currentThread().getName() + " update number value to:" + mydata.num);
        }, "AA").start();

        while (mydata.num == 0) {

        }
        System.out.println(Thread.currentThread().getName() + " get value " + mydata.num);
    }
}
```

#### volatile 底层实现需要阅读汇编代码，生成汇编代码的方式如下：

https://juejin.cn/post/6844903656806940686



#### volatile  的使用场景？

单例模式修饰类的实例的时候？DCL 双重检查锁，需要配合volatile  使用，才能解决多线程同步问题



**海子的文章**https://www.cnblogs.com/dolphin0520/p/3920373.html

