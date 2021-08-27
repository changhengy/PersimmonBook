# jdk ArrayList工作原理分析

list是一个动态的数组，当list的长度满了，再次插入数据到list当中的时候，list会自动地扩展它的长度。

ArrayList类的属性如下：

```java
private static final int DEFAULT_CAPACITY = 10; // 集合的默认容量
private static final Object[] EMPTY_ELEMENTDATA = {}; // 一个空集合数组，容量为0
private transient Object[] elementData; // 存储集合数据的数组，默认值为null
private int size; // ArrayList集合中数组的当前有效长度，比如数组的容量是5，size是1 表示容量为5的数组目前已经有1条记录了，其余4条记录还是为空
```

常用方法：

### add(E e) 方法

#### List初始化和扩容机制

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // 调用ensureCapacityInternal，参数是集合当前的长度。确保集合容量够大，不够的话需要扩容
    elementData[size++] = e; // 数组容量够的话，直接添加元素到数组最后一个位置即可，同时修改集合当前有效长度
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    if (elementData == EMPTY_ELEMENTDATA) { // 如果数组是个空数组，说明调用的是无参的构造函数
        // 如果调用的是无参构造函数，说明数组容量为0，那就需要使用默认容量
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }

    ensureExplicitCapacity(minCapacity);
}    

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // 如果集合需要的最小长度比数组容量要大，那么就需要扩容，已经放不下了
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) { // 扩容的实现
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 长度扩大1.5倍
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // 将数组拷贝到新长度的数组中
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

### add(int index, E element) 方法

### remove(int index)

### remove(Object o)

### clear()

### set(int index, E element)

### get(int index)

### addAll

### toArray

## ArrayList的注意点

1. 当数据量很大的时候，ArrayList内部操作元素的时候会移动位置，很耗性能
2. ArrayList虽然可以自动扩展长度，但是数据量一大，扩展的也多，会造成很多空间的浪费
3. ArrayList有一个内部私有类，SubList。ArrayList提供一个subList方法用于构造这个SubList。这里需要注意的是SubList和ArrayList使用的数据引用是同一个对象，在SubList中操作数据和在ArrayList中操作数据都会影响双方。
4. ArrayList允许加入null元素