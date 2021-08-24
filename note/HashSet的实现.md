[HashSet实现原理](https://www.jianshu.com/p/1ed5fa8e357b)

[HashMap原理详解，看不懂算我输（附面试题）](https://zhuanlan.zhihu.com/p/127147909)

## HashSet简述

> HashSet中不允许有重复元素，这是因为**HashSet是基于HashMap实现的**，HashSet中的元素都存放在HashMap的key上面，而value中的值都是统一的一个**private static final Object PRESENT = new Object();**。HashSet跟HashMap一样，都是一个存放链表的数组。

## HashSet的构造

> 对于HashSet而言，它是基于HashMap实现的，HashSet底层使用HashMap来保存所有元素，更确切的说，HashSet中的元素，只是存放在了底层HashMap的key上， 而value使用一个static final的Object对象标识。因此HashSet 的实现比较简单，相关HashSet的操作，基本上都是直接调用底层HashMap的相关方法来完成

```java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
{
    static final long serialVersionUID = -5024744406713321676L;

    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }
	// 什么都不传的话，默认构造是，创建一个负载因子 0.75 ，初始容量是16 的map
    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }
    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }
    public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }
    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }
    ... ...
}
```



