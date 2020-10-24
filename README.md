[TOC]

# 恋上数据结构与算法-腾讯课堂(JAVA实现)
## 复杂度
#### 均摊复杂度(结合ArrayList理解)
- ArrayList的add(E element)只在尾部添加,最好O(1),最坏O(n)(发生扩容的情况),这种情况适合用均摊复杂度分析,就是绝大部分情况都是O(1),而突然会有复杂度变高的情况,将扩容发生的情况,均摊到每一次未扩容的add操作中,这样每一次就还是相当于O(1),**均摊复杂度一般就是最好复杂度,适合连续出现了复杂度很低的情况,突然出现了复杂度较高的情况**

#### 复杂度震荡(结合ArrayList理解)

- ArrayList,如果涉及扩容,缩容操作的实现,如果设计的不合理,比如当容量满了再添加就扩容两倍,而缩容操作设计为,当删除元素时,如果元素个数小于等于(一定有等于,没有等于就并不会造成震荡)容量一半就缩容,那么就会导致,在扩容的那一刻(尾部添加)add(element)时间复杂夫也为O(n),(尾部删除)remove()也为O(n),造成原本尾部添加删除大部分时间都是O(1),突然如果在这反复添加删除,就会一直O(n),这种情况就叫做复杂度震荡,解决办法扩容缩容倍数不要相乘为1

#### 常规知识点

- **斐波那契(fibonacci)** 递归和非递归算法 还有线性代数特征方程解法
- 事后统计法的缺点
- 评估算法优劣标准 正确性 可读性 健壮性(对不合理输入的处理反应能力) **时间复杂度**(估算程序指令执行次数) **空间复杂度**
- **大O表示法(Big O)** (忽略常数,系数,低阶,对数阶一般省略底数)(n/=2>0和i*=2>n都是log2n的复杂度)
- 最好最坏复杂度,均摊复杂度,复杂度震荡,平均复杂度
- 一般编程语言中数组的缺点 无法动态修改容量

## 动态数组arrayList的实现

- 数组变量 array1的容量通过array1.length即可获取,不需要单独设置capacity成员变量
- 声明的局部变量放栈空间,new的实例对象放堆空间,由局部变量指向该堆空间的首地址
- `Object[] objects = new Object[7]`堆空间中的Object对象数组不是随着Object对象成员多少而变化大小的,而是存放的内存地址
- new一个对象数组,数组里面放的是对象的地址,而不是对象本身,而且这样的设计才是合理的,不可能例如创建了Object[]数组,然后objects[1] = new Person("name");给下标1的元素赋值,但是Person对象却占用10个子节,Object才占8个字节,那么怎么可能放的进去呢
- Java内存objects对象数组的释放顺序,先是栈空间的局部变量释放->堆空间被指向的连续空间被释放->每个空间指向的内存地址被释放
- clear()写成泛型动态数组后就不能只写成`size=0`了,因为之前最简化的程序时,我们写死了elements类型就是int,那么此时堆空间中每个连续空间中存储的就是int类型的元素,我们设置size之后,如果再次add,那么直接add即可,这个int数组空间依然可以重新利用,所以并不需要清空内存,而当我们改写成泛型之后,一定要清空这些内存
- 区分好`elements=null`和`for (int i = 0; i <this.size ; i++) {
            elements[i] = null;
        }`前一个会造成栈空间指向堆空间的指向直接断掉,堆空间包括地址空间和对象空间清空,而后一个仅仅会让每一个地址空间的值为null,而没有影响elements指向地址空间,那么之后加入新元素,就不用重新申请地址空间
- Java默认equals方法是比较两个对象是否完全一样,就是内存地址是否一致(但是Integer 等基本对象已经重写好了equals,比较的是值)
- null的处理,在Java中数组是建议可以存储null空值的,默认正常写add,那么就是支持空的,但是要考虑indexOf可能会引起空指针异常错误,改写为element在前,防止elements数组中被传入null元素,防止引起空指针,然后再单独对`if(element == null){
            for (int i = 0; i < size; i++) {
                if(elements[i] == null){
                    return i;
                }
            }
        }`
- 动态数组的*缩容操作*,典型的用**时间换空间**的操作

## 链表

#### 动态数组明显的缺点

- 扩容要进行复制操作,且新的扩容空间用不满,依然会造成内存空间的大量浪费

#### 动态数组ArrayList优化
- 优化在头部添加或者删除的操作复杂度
- 设置一个int first;代表数组开始的位置(首元素位置)

#### 链式存储的线性表
- 由于结点类型是只用于链表内部的,应该声明为内部类,而且内部类建议写为静态的
- 并且由于这是只用在LinkList内部的,所以就不必声明私有,公共等
- 设计带虚拟头结点的LinkedList,要在LinkedList基础上修改 add remove indexOf toString rangecheck,但也并不是非要加虚拟头节点(至少浪费了一点空间),好处在于不用在add,remove等方法中单独对链表无结点的情况单独判断.
- 常说的比如链表插入节点和删除节点更快,指的**仅仅是****插入和删除这两个操作的时间复杂度为O(1)**
,而数组的插入删除时间复杂度为O(n),仅指插入删除的动作发生时,而如果是实现一个链表的插入操作,那么实际上还是涉及先找到index索引位置,那么平均复杂度为O(n/2)即O(n),这个区别要明白
- **ArrayList和LinkedList的add(int index,E element) remove平均复杂度都为O(n),set和getArrayList为O(1),LinkedList为O(n),链表的另一个优点是,节省内存空间**
#### 均摊复杂度
- ArrayList的add(E element)只在尾部添加,最好O(1),最坏O(n)(发生扩容的情况),这种情况适合用均摊复杂度分析,就是绝大部分情况都是O(1),而突然会有复杂度变高的情况,将扩容发生的情况,均摊到每一次未扩容的add操作中,这样每一次就还是相当于O(1),均摊复杂度一般就是最好复杂度,适合连续出现了复杂度很低的情况,突然出现了复杂度较高的情况

#### 双向链表
- add和remove肯定快一些,因为可以从最坏O(n)->O(n/2)
- 避免空指针错误,只要再写node.next或者node.prev的情况,就要考虑此时node有没有特殊情况会是null
- 双向链表比单向链表的添加删除操作节省一半时间,但还是O(n)的时间复杂度
- 动态数组开辟销毁内存空间次数相对较少,但会造成空间浪费,双向链表相反
- **如果频繁在尾部删除添加,双向链表,动态数组都可以**
- **如果频繁在头部删除添加或者在任意位置删除添加,建议双向链表**
- **频繁的查询(随机访问操作),建议选择动态数组**
- 单链表性能又一般(相比双向链表),是否没用呢? 不是的 Hash表的涉及就用到了单向链表
- Java的LinkedList实现就是双向链表
- 静态链表,早期编程语言没有指针的话,通过数组模拟,二维数组,每一个元素含2个数据,其中一个数据是下一个元素的索引
- 


## 栈
#### Last In First Out LIFO后入先出
#### 代码设计
- 实际上我们直接用arrayList或者linkList添加push pop等方法即可实现
```java
import list.arrayList;

public class Stack<E> extends arrayList<E> {
    public void push(E element){
        add(element);
    }
    public E pop(){
        return remove(size-1);
    }
    public E top(){
        return get(size - 1);
    }
}
```
- **java知识点**
- ==但是这里涉及到java继承的缺点是,我们设计的stack不光有push pop等方法,我们一样还可以stack.add()stack.indexOf(int index)等类似方法,这样实际上没有什么问题,但是对于栈来说,我们是只希望只能在栈顶进行相关操作,所以不想让非栈顶元素也有暴露的可能,所以我们最好不要使用这种继承的方法==
- ==我们最好使用这种直接让arrayList成为我们的私有成员的方法,相当于list就专门装stack的元素的,这样我们就不会暴露出indexOf等方法==
- ==即通过将arrayList(继承了抽象类abstractList的)成为自己的私有成员,而并不是去继承,这样就可以不用暴露原本arrayList的一些方法,同时在Stack内部又能通过实例化arrayList一个实例对象,来利用这个实例对象实现自己的逻辑==
```java
import list.List;
import list.arrayList;

public class Stack<E>{
    private List<E> list = new arrayList<E>();

    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void push(E element){
        list.add(element);
    }
    public E pop(){
        return list.remove(list.size()-1);
    }
    public E top(){
        return list.get(list.size() - 1);
    }
}
```
- java官方底层的实现其实就类似于继承的方法,继承自vector
- 应用举例:浏览器的前进和后退 (两个栈实现的思想)只要输入新的网址,第二个栈清空,第一个栈入栈新网址.后退就第一个栈出栈栈顶网址,放入第二个栈,访问第一个栈的新栈顶,前进就将第二个栈栈顶元素出栈入到第一个栈,然后访问第一个栈的栈顶元素
- 应用实例,括号匹配问题

## 队列

### 概念

- 特殊的一种**线性表**
- **只能在队尾(rear)添加元素,只能在队头(front)移除元素**,入队enQueue和出队deQueue
- **先进先出(后进后出)原则,First In First Out FIFO**

### Java源码实现

- java.util.Queue
- offer入队,poll出队,peek获取队头
- ==Queue是一个接口,而java.util.LinkedList就实现的Deque接口,而Deque接口又实现了Queue接口(接口实现接口的情况用extends关键字),所以LinkedList就包含上面offer poll的方法(也就是说LinkedList就可以当成Queue队列来用),并且它的offer方法实际就底层就一句代码 return add(e),就是在list链表的尾部添加元素的操作==

### 双端队列(Deque double ended queue)

- 队头队尾都可以添加删除操作
- java源码也就是LinkedList实现了双端链表的功能,因为LinkedList实现了Deque接口,Deque实现了Queue接口

### 循环队列(Circle Queue)

- 只要注意一点:就是将计算出的索引映射到循环队列中的真实索引

- 也就是`(front + size)%elements.length;`关于循环队列长度取模的这个操作

- 而上面这个`(front + size)%elements.length;`实际可以封装成一个索引映射的方法,这样就不用在出队,入队,扩容等索引计算时,都要计算真实索引,比如下面代码中的举例

- ```java
	public int index(int index){
		return (front + index)%elements.length;
	}
	
	//入队操作的优化
	public void enQueue(E element) {
	    ensureCapacity(size+1);;
	    elements[(front + size)%elements.length] = element;
	    size++;
	}
	//优化之后
	public void enQueue(E element) {
	    ensureCapacity(size+1);;
	    elements[index(size)] = element;
	    size++;
	}
	```

### 循环双端队列

- 不需要再额外申请一个成员变量rear,只需要用`(front+size-1)%elements.length`

- 循环双端队列就是再上面要加入一个enQueueFront()在队头入队方法,和deQueueRear()在队尾出队的方法

- ==同时,还要注意上面的index索引映射不能直接复制了,因为考虑一种特殊情形,比如front恰好位于数组0位置,那么如果此时按原来的index方法调用的话,相当于调用index(-1)[其他情况都是调用index(-1)],**但是因为front为0,那么就相当于对-1取模**,那么结果还是-1,就没有达到循环的目的,因为数组没有-1位置,所以需要将index方法进行一定的修改==

- ```java
	public int index(int index){
		index+=front;
		if(index < 0){
			return index+elements.length;
		}
		return index % elements.length;
	}
	```

### %运算符优化(无论何时都可以注意一下这个优化)

- 也就是模运算的优化

- 原则:尽量避免乘,除,取模等浮点数运算

- ```java
	//思路就是n%m分解操作
	//比如当n>=m并且n不超过m的两倍,时取模运算就是n-m
	//而当n<m时,取模运算的结果就是n本身
	
	//也就是满足前提条件m>0,n>=0,n<2m
	(n - (n>=m ? m : 0))
	//其他情况正常取模计算即可
	```

- ```java
	//那么根据上面的优化思路,对循环队列的索引映射方法进行优化(以循环双端队列为例)
	public int index(int index){
		index+=front;
		if(index < 0){
			return index+elements.length;
		}
		return index - (index >= elements.length ? elements.length : 0);
	}
	```


## 二叉树(Binary Tree)

#### 概念性质

- 一个父节点的两个节点才是兄弟节点,不是同一个父亲的同层节点并不是兄弟节点
- 树和节点的**深度**和**高度**,在不同的教材中定义不同(但高度和深度一般是算节点数,而不是算路径)
- 即使某节点只有一颗子树,也要区分左右子树(左右子树是有顺序的)

#### 二叉树性质

- 非空二叉树的第i层,最多有2^i-1^个节点

- 高度为h的二叉树最多有2^h^-1个节点

- 对于任何一颗非空二叉树,如果叶子结点个数为n0,度为2的节点个数为n2,则有n0=n2+1

- > n = n1 + n2 + n0
	>
	> n-1 = n1 + 2n2
	>
	> 1 = -n2 + no
	>
	> no = n2 + 1

- **真二叉树(Proper Binary Tree)**,所有节点度要么为0,要么为2

- **满二叉树(不是完全二叉树)(Full Binary Tree)**,所有节点度要么为0,要么为2,且所有叶子节点都在最后一层

- >满二叉树第i层节点数量:2^i-1^
	>
	>满二叉树叶子节点数量:2^h-1^
	>
	>节点为n个满二叉树的高度:h = log~2~(n+1)

- **完全二叉树(Complete Binary Tree)**,叶子节点只会出现在最后两层,且最后一层的叶子节点都靠左对齐

- > 1. 某些教材定义:如果按照序号从左到右编号,那么完全二叉树和同高度的满二叉树的节点编号都是一一对应的
  > 2. 完全二叉树从根节点到倒数第二层是一颗满二叉树
  > 3. 度为1的节点只有左子树
  > 4. 度为1的节点要么一个,要么没有
  > 5. 同样节点数量的二叉树,完全二叉树高度最小
  > 6. 若完全二叉树的高度为h,那么至少有2^h-1^个节点,至多有2^h^-1个节点(满二叉树)
  > 7. **总节点数量:2^h-1^<=n<2^h^ -> h-1<=log~2~n < h -> h = log~2~n向下取整 + 1 = log~2~n向上取整**(*平时Java整数的除法都是向下取整*)
  > 8. **一棵有n个节点的完全二叉树,从上到下,从左到右对节点从1开始进行编号,对任意第i个节点,如果i=1,他是根节点,如果i>1,它的父节点编号为floor(i/2),如果2i<=n,那么它的左子节点编号为2i,如果2i>n,那么它没有左子节点,如果2i+1<=n,那么它的右子节点为2i+1,如果2i+1>n,那么它无右子节点**(二叉堆时用这个结论)
  >
  > 9. **一棵有n个节点的完全二叉树,从上到下,从左到右对节点从0开始进行编号,对任意第i个节点,如果i=0,他是根节点,如果i>=1,它的父节点编号为floor((i-1)/2),如果2i+1<=n-1,那么它的左子节点编号为2i+1,如果2i+1>n-1,那么它没有左子节点,如果2i+2<=n-1,那么它的右子节点为2i+2,如果2i+2>n-1,那么它无右子节点**(二叉堆时用这个结论)
  > 10. 完全二叉树n1要么为1,要么为0,那么当n1=1时,n=2n0,n必然为偶数,此时叶子节点个数为n/2,非叶子节点个数n/2.
  > 11. 当n1=0时,n必然为奇数,n=2n0-1,n必然为技术,叶子节点个数n0 = (n+1)/2,非叶子节点个数为n1+n2 = (n-1)/2
  > 12. 上面两个结论,可以统一用floor((n+1)/2)[**Java用(n+1)/2**],然后还可以优化为floor((n+1)>>1),而且java整数除法默认就是向下取整的floor也不需要,并且还可以表示为ceiling(n/2)
  > 13. 非叶子节点个数类似有n1+n2 = floor(n/2)[**Java用n/2**] = ceiling((n-1)/2)

- ==如果一颗完全二叉树有768个节点,求叶子结点个数?==

- > 首先,n0 = n2 + 1,n = 1 + n0 + n2 -> n = 2n0 -> n0 = 384
	>
	> 或者直接用结论floor((n+1)/2)


## 二叉搜索树(Binary Search Tree)(BST)

- 也叫二叉查找树,二叉排序树

### 需求分析

- 动态数组存放元素,搜索某个元素,平均时间复杂度O(n)
- 维护一个有序的动态数组,二分搜索查找某个元素,O(logn),但是添加删除操作依然是平均时间复杂度O(n)
- 使用二叉搜索树,添加删除,搜索的最坏时间复杂度可优化到O(logn)

### Java设计思想

#### 如何为泛型设计一种可比较性,让二叉搜索树BST可以不仅仅对整数进行比较

##### Comparable接口实现方法

- ```java
	//设计一个Comparable接口
	//一个接口,用于让每一个需要处理的E元素都要实现compareTo这个方法
	public interface Comparable<E> {
		int comparaTo(E e);
	}

	//设计Person类实现这个接口(另一个文件)
	public class Person implements Comparable<Person> {
		private int age;

		public Person(int age) {
			this.age = age;
		}

		//实现compareTo方法,告诉我如果传入的是另一个Person对象,那么如何进行比较
		@Override
		public int comparaTo(Person e) {
			// TODO Auto-generated method stub
			// 一种不聪明的判断方法
			// if(age > e.age){
			//     return 1;
			// }else if(age < e.age){
			//     return -1;
			// }
			// return 0;
			// 一句话解决
			return age - e.age;
		}
	}

	//然后修改BST类的compare方法
	private int compare(E e1,E e2){
        return e1.comparaTo(e2);
    }

	//让二叉搜索树的E泛型限制为都是实现了Comparable接口的
	public class BinarySearchTree<E extends Comparable>
	```

- **局限性**:一旦Person的compareTo方法实现之后,比如按年龄从大小大比较,那我们就无法之后建立从小到大比较的二叉搜索树,除非重写Person Comparable里面compareTo的逻辑

##### Comparator比较器实现方法

- ```java
	//首先定义比较器Comparator接口
	public interface Comparator<E> {
		int compare(E e1,E e2);
	}
	//去掉使用Comparable实现方法写死的E
	public class BinarySearchTree<E>
	//然后在BinarySearchTree中定义构造方法,让其创建时需要传入比较器作为参数
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator comparator){
        this.comparator = comparator;
	}
	//然后修改BST类的compare方法的实现
	private int compare(E e1,E e2){
        return comparator.compare(e1, e2);
    }

	//然后在需要创建BST对象的代码里,声明创建实现了Comparator接口的比较器
	private static class PersonComparator implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }
    
    private static class PersonComparator2 implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
	}

	//最后这样new对象即可
	BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
	```
- **局限性**:但是这样comparator的实现方法每次都要创建对应的比较器才可以,而有时候我不想要非要传入比较器,所以最好的方法应该是将这两个方法结合起来

##### 完美结合

- ```java
	//结合的话那就不能直接将BST的E按照Comparable的实现方法写死了
	//因为我们不想每一个传入的对象,我们都必须让这个对象实现了Comparable接口才可以用来创建二叉搜索树
	//所以不能在外部BinarySearchTree<E extends Comparable>强制了,而在compare方法内实现
	//也就是说不能按下面这种写法了
	public class BinarySearchTree<E extends Comparable>
	//在compare方法强制
	private int compare(E e1, E e2) {
        if(comparator != null){
            return comparator.compare(e1, e2);
        }
		return ((Comparable<E>)e1).comparaTo(e2); //也就是确定没有比较器时,那么我确定这个e1一定要实现Comparable接口才可以
	}
	```

- java官方本身就有java.lang.Comparable和java.util.Comparator

- 像java本身带的内置类型基本都实现了java.lang.Comparable接口

- 同时java本身还支持匿名类,可以使用匿名类的方式直接创建一个BST,使用官方的java.lang.Comparable,匿名类类似于js的闭包,这样就不用自定义一个实现比较器Comparator接口的类了

- ```java
	//通过匿名类的方式创建一个BST二叉搜索树
	BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>(){
		@Override
		public int compare(Person p1,Person P2){
			return 0;
		}
	});
	```

#### 打印器Printer

- 使用讲师自己写的一个printer打印器来输出检验

- 导入printer package,然后BST实现BinaryTreeInfo接口,然后实现下面四个方法即可
- ```java
    @Override
    public Object root() {
        return rNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).rightNode;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
	}
	```


#### Math.random()问题

- `random方法一定要*100整个加括号然后转换为int,否则相当于random先转int,random本身是0-1,取不到1的小数,那么自然转为int后就一直是0,*100依然还是0`

### 二叉树遍历

#### Preorder Traversal

##### 二叉搜索树的非递归前序遍历思路

- 利用栈

- 首先根节点入栈,然后出栈访问,如果有右子节点,右子节点入栈,左子节点入栈,然后栈不空就再次循环流程

- ```java
  private void preorderTraversalNonRecursive(Node<E> node){
          Stack<Node> stack = new Stack<>();
          // Node<E> tempNode = rNode;
          // stack.push(tempNode);
          // while(!stack.empty()){
          //     Node<E> temp = stack.pop();
          //     if (temp != null) {
          //         System.out.println(temp.element);
          //     } else {
          //         break;
          //     }
          //     if(temp.rightNode != null){
          //         stack.push(temp.rightNode);
          //     }
          //     if(temp.leftNode != null){
          //         stack.push(temp.leftNode);
          //     }
          // }
          
          while(!stack.isEmpty()||node!=null){
              if(node != null){
                  // 进栈之前就已经访问,上面的方法是出栈的时候访问
                  System.out.println(node.element);
                  stack.push(node);
                  node = node.leftNode;
              } else{
                  // 代表左路径已经走到最后的
                  node = stack.pop();
                  node = node.rightNode;
              }
          }
      }
  ```

  

#### Inorder Traversal

- 二叉搜索树的中序遍历结果是升序或降序的(升序就是按照左子树小,右子树大)

- ```java
  private void inorderTraversalNonRecursive(Node<E> node){
          
          Stack<Node> stack = new Stack<>();
          Node<E> temp = null;
          
          while(!stack.isEmpty()||node!=null){
              
              // 访问时间不同于前序遍历的第二种写法
              if(node!=null){
                  stack.push(node);
                  node = node.leftNode;
              }else{
                  // 出栈就代表当前树的根节点的左子树已经遍历结束,该访问我自己了
                  node = stack.pop();
                  System.out.println(node.element);
                  node = node.rightNode;
              }
          }
      }
  ```

- **思路**:前序和中序的非递归遍历都是比较相似的,只要把握好这种方式就是递归的拆分,只是访问节点的时间不同而已,前序是在入栈之前访问,中序是在出栈之后访问,要这么想就是说递归转非递归那我肯定需要栈来辅助,因为程序的运行原理就是这样,**栈可以保存状态**,无论是何时访问节点,我们都需要将此时的状态保存,只要保存了状态就不会出现以后找不到节点的情况

#### Postorder Traversal

- 后序不同于前序和中序的地方在于需要设置一个每个节点的访问状态,只有被左右子树都遍历结束才访问根

- ```java
  private void postorderTraversalNonRecursive(Node<E> node){
  
          Stack<Node> stack = new Stack<>();
          Map<E,Integer> map = new HashMap<>();
          Node<E> temp = null;
  
          while (!stack.isEmpty() || node != null) {
  
              // 访问时间不同于前序遍历的第二种写法
              if (node != null) {
                  stack.push(node);
                  map.put(node.element, 1);
                  node = node.leftNode;
              } else {
                  node = stack.peek();
                  if(map.get(node.element) == 2){
                      stack.pop();
                      System.out.println(node.element);
                      node = null;
                  }else{
                      map.put(node.element,2);
                      node = node.rightNode;
                  }              
              }
          }
      }
  ```



#### Level order Traversal

#### 二叉搜索树.20 介绍了如何设计一个遍历接口,降低代码耦合(重要)

##### 一种Java编程中经常使用一种设计模式,(工厂模式?)
- 通过声明一个Interface接口`Visitor`,然后这个接口有一个visit方法待实现,因为`private static class Node<E>`是内部静态类,在外界访问不到,通过在BinarySearchTree下声明一个公共接口,然后比如在将这个接口作为一个方法的参数,然后在这个方法内通过`visitor.visit(node.element)`调用,在外界通过`new Visitor<Integer>(){public void visit(Integer element){业务逻辑}}`就是实现visit方法的接口

#### 二叉搜索树.21 增强遍历接口(重要)

- 重构了上面的遍历接口,写成了抽象类,为了每一个visitor对象都有一个成员stop,(接口无法声明变量,只能声明方法),然后**实现了一个能控制遍历到几的时候就结束遍历的功能**

#### 阶段总结
- **20和21比较重要:里面详细的实现了一个Visitor遍历接口,并且包含了很多小的知识点,包括抽象类和接口的区别(接口不能声明成员变量,只能有成员方法,抽象类有点像接口,但又有它自己的限制,比如一个类只能继承一个父类),如何使用抽象类Visitor的成员stop实现了中断递归调用的逻辑,还有递归调用中停止递归调用的流程分析,虽然和数据结构关系不大,但是用于理解Java语言特性还是比较不错的**

#### 二叉搜索树.30 后继节点(successor)

#### 二叉搜索树.29 前驱节点(predecessor)

#### 二叉搜索树.30 后继节点(successor)

#### 二叉搜索树.31 删除度为1的节点

#### 二叉搜索树.32 删除度为2的节点

- **如果一个节点的度为2,那么它的前驱,后继节点的度只可能是1或0**
- 就相当于找到被删除度为2的节点的前驱或者后继节点取代他,然后删除原本的这个前驱或者后继节点
- 在代码中就是把被删除节点的element值改为后继节点的值即可,然后按照删除度为1或0的节点的方式删除这个后继节点

#### 二叉搜索树 33 删除实现

#### 二叉搜索树 34 测试删除功能,完善接口

##### 深入理解Java的面向对象的封装概念,还有隐藏具体实现的概念

- 以恋上数据结构与算法的二叉搜索树的代码为例
- 我们实现的Node节点的代码如下
```java
private static class Node<E> {
    E element;
    Node<E> left;
    Node<E> right;
    Node<E> parent;
    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
}
```
- 可以看出这是一个private修饰的节点,在外是无法访问的,然后从一个具体的功能实现来分析**我们是如何将Node节点的结构不暴露给外面,同时能让外面的人调用删除节点的功能**
    1. 首先看一下删除功能代码的实现
    ```java
	public void remove(E element) {
		remove(node(element));
	}
    ```
    2. 可以看到实际上我们只在BST内部调用了remove()方法真正的逻辑实现,只暴露了一个公共的方法供外界调用,外界使用时只需要传入外界创建BST二叉树时,节点的元素类型E即可,实际E的存储在内部是Node<E>,存放于Node中的,但是外界只知道有E element这个概念,不知道我们内部具体的实现
    ###### 我们为什么要这么封装设计呢?
    3. 因为BST内部的每一个节点都需要一个Node节点的数据结构,而不是基本类型就可以做节点的,我需要节点之间存在关系,需要节点相互连接起来而形成一个二叉搜索树,这些都需要节点本身内部就有指针去指向,**而我们想封装这个节点结构的原因在于**,我们不想用户能够直接操作构造好的一颗BST,(用户可以通过add节点的方式不断添加节点构造一颗符合二叉搜索树规则的二叉搜索树),用户如果可以直接操作内部的Node节点,那么他就可以通过node.left,node.right等重新设置左右节点,或者重新设置每个节点的element值,一旦这样的操作暴露给外界,那么这颗二叉搜索树就不一定满足二叉搜索树的条件了,而不是二叉搜索树之后,大部分的删除,add等方法功能的逻辑可能都失效了,这是我们不希望看到的
    4. 所以我们通过设置Node类型为private私有,让外界无法访问,只能在BST类中访问,同时只给外界提供接口,隐藏具体的实现,依然从删除继续看,我们只暴露了一个remove(E element)接口,内部调用的是另一个重载的private的方法`remove(Node<E> node)`,也是真正的remove节点的逻辑,代码如下
    ```java
    private void remove(Node<E> node) {
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
		}
		
		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node是度为1的节点
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}
		}
	}
    ```
    5. 同时暴露的remove(E element)方法时调用了另一个private方法定位一个Node,实现了由元素值获取相应Node节点的方法,node(E element),**因为在BST内部我们是可以随意获取root节点,调用各种private方法来实现我们的功能的**
    ```java
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp > 0) {
				node = node.right;
			} else { // cmp < 0
				node = node.left;
			}
		}
		return null;
	}
    ```
    6. 这样的设计下,用户只需要使用`bst.remove(element)`的方式即可删除节点,而且用户根本无法通过bst.node(element)等直接获取节点去操作,保证了代码的可用性,保证了二叉搜索树的规则不被破坏

##### 如何理解Java程序员不用进行内存管理,相对于C来说要方便许多?

###### 场景分析
- 从二叉搜索树的clear()删除一颗树的功能
- 如果是Java写,假如树的节点的实现如下
```java
private static class Node<E> {
    E element;
    Node<E> left;
    Node<E> right;
    Node<E> parent;
    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
}
```
- 同时,在这棵树的类中,声明了以下成员属性
```java
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
    private Node<E> root;
    
    ...
}
```
- 然后来看清空这颗二叉搜索树的功能clear()
```java
public void clear() {
    root = null;
    size = 0;
}
```
- 可以看到整个清空过程,程序员只需要关注与逻辑上把这颗树清空即可,root根节点等于空其实就相当于这棵树空了,因为我这颗树的各种add,node,remove等方法都需要从root节点获取到这个树,一旦root空了,逻辑上这颗树就消失了,只能之后通过add节点重新建立一颗新的树,size设置为0,实际就是树空了自然容量为0,所以整个清空树的过程,我们只需要在逻辑上清空即可,而Java底层的虚拟机自动帮我们进行了垃圾回收,没有GC root对象指向的对象当没有引用关系后就会被自动垃圾回收
- 而在C上如果要实现这样的逻辑,我们就需要管理内存,创建和释放任何一个节点都需要我们手动的去malloc和free内存空间

## 平衡二叉搜索树(AVL树)

- 二叉搜索树因为比较简单,所以没有详细记录每一节,只记录了关键知识点

#### 二叉搜索树复杂度分析
- 搜索,添加,删除的时间复杂度,跟树的高度有关,与元素的个数无关(这个无关不是说完全无关,而是说复杂度只是和树的高度有关的意思),如果是一颗满二叉树,那么就是log(n)
- **但是树的形状是取决于元素添加顺序的**,如果add()的顺序比如是,7,4,9,2,5,8,11,这样添加元素就会生成一颗满二叉树,但是如果按照2,4,5,7,8,9,11就退化为了线性结构
  - 比如n=1048576,那么满二叉树的高度是20层,而退化为线性结构的二叉搜索树高度是1048576,差别相当大
- add,和remove都会导致二叉搜索树退化,为了维持添加,搜索,删除的复杂度为log(n),就需要平衡

#### 平衡
- 节点数量固定时,左右子树高度越接近,这颗二叉树就越平衡(树的高度越小)
##### 理想平衡
- 像完全二叉树,满二叉树那样,高度差是最小的
#### 如何改进二叉搜索树
1. **限制添加/删除顺序**:节点的添加删除顺序不可能限制,因为写出来程序给其他人用,无法保证他们添加删除都是按照固定顺序去添加删除的
2. **在添加/删除之后,想办法让二叉搜索树恢复平衡(减小树的高度)**
3. 如果一直调整节点位置,直到达到理想平衡,是可以实现的,但是这样付出的代价会比较大,总结来说,比较合理的方案是**用尽量少的调整次数达到适度平衡即可**
4. **一颗达到适度平衡的二叉搜索树,可以称之为平衡二叉搜索树**

#### 平衡二叉搜索树(Balanced Binary Search Tree BBST)

- **经典的平衡二叉搜索树**有
    1. **AVL树**
        - 在Windows NT内核广泛使用
    2. **红黑树**
        - C++ STL库(map,set底层)
        - Java的TreeMap TreeSet HashSet HashMap都用到红黑树
        - Linux的进程调度
        - Nginx的Timer管理
- 这两种经典的平衡二叉搜索树也称为**自平衡的二叉搜索树**(Self-balancing Binary Search Tree)

#### AVL树
- 最早的**自平衡的二叉搜索树**(Self-balancing Binary Search Tree)
- 名字是以两位苏联科学家的首字母命名

##### 平衡因子(Balance Factor)
- **某节点左右子树的高度差**(左子树高度减右子树高度)
  - 叶子节点的平衡因子是0
  - **AVL树的每个节点的平衡因子只可能是1,0,-1(绝对值<=1,如果超过1,称为失衡)**
  - 每个节点的左右子树高度差不超过1
  - 搜索,添加,删除的时复是O(log(n))

##### 添加导致失衡

- 添加某个节点,导致失衡,有可能导致所有的祖先节点都失衡,也可能导致部分祖先节点失衡
- **父节点和其他非祖先节点,都不可能失衡**,只有父节点的父节点或者再往上(祖先节点)才可能失衡

###### 失衡调整
- **按旋转原理的调整方式**
1. **LL-右旋转(单旋)**
   - 假设三级节点分别是n(node),p(parent),g(grand)
   - ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201022184243.png)
   1. Left-Left失衡,失衡节点是因为它左子树的左子树导致了它的失衡,这种情况称为LL
   2. 首先让失衡节点.left = 失衡节点的左子节点.right(相当于断开了失衡节点的左子树),即`g.left = p.right`
   3. 然后让失衡节点的左子节点的右子节点指向失衡节点(相当于断开了左子节点的右子树),即p.right = g
   4. 让p成为这颗子树(原本失衡节点是这颗子树的根节点)的根节点
   - ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201022184809.png)
   5. 因为添加之前t0,t1,t2都是平衡的,那么无论怎么旋转t0,t1,t2子树依然是平衡的
   6. 添加可能会导致失衡的都是祖先节点,也就是图中的n,p,g三个节点
   7. **这次右旋转是否会导致g当初的祖父节点,也就是现在p的祖父节点失衡呢?**,答案是不会,因为添加一个节点可以看到高度加了1,但是旋转之后,高度又回到之前平衡时的高度,所以旋转之后整颗子树的高度是没有变化的
   8. 交换之后,记得维护节点之间的关系,也就是`g.left = p.right`之后还需要让p.right.parent = g,即`t2.parent = g`, 然后还需要,`p.parent = g.parent`,`g.parent = p`
   9. 还需要更新一下g,p节点的高度,以后avl树应该新增一个高度属性,方便计算平衡因子
2. **RR-左旋转(单旋)**
   1. g.right = p.left
   2. p.left = g
   3. p成为这颗子树的根节点
   4. 维护内容:T1,p,g的parent属性,后更新g,p的高度
3. **LR-先RR左旋转,然后LL右旋转**
    - ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201022195953.png)
    1. 先对失衡节点的左子树做RR左旋转,然后对原失衡节点的左子树的右子树的节点进行LL右旋转,**即先对p进行RR左旋转,然后对n进行LL右旋转**
    2. 因为先做RR左旋转之后就转换为了LL失衡的情况
    - ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201022200009.png)
    - ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201022200203.png)
4. **RL-先LL右旋转,然后RR左旋转**

- **调整代码实现**
```java
  private void rebalance(Node<E> grand) {
    Node<E> parent = ((AVLNode<E>) grand).tallerChild();
    Node<E> node = ((AVLNode<E>) parent).tallerChild();
    if (parent.isLeftChild()) { // L
      if (node.isLeftChild()) { // LL
        rotateRight(grand);
      } else { // LR
        rotateLeft(parent);
        rotateRight(grand);
      }
    } else { // R
      if (node.isLeftChild()) { // RL
        rotateRight(parent);
        rotateLeft(grand);
      } else { // RR
        rotateLeft(grand);
      }
    }
  }
```
```java
    // RR-左旋调整
  private void rotateLeft(Node<E> grand) {
    Node<E> parent = grand.right;
    Node<E> child = parent.left;
    grand.right = child;
    parent.left = grand;
    afterRotate(grand, parent, child);
  }

  private void rotateRight(Node<E> grand) {
    Node<E> parent = grand.left;
    Node<E> child = parent.right;
    grand.left = child;
    parent.right = grand;
    afterRotate(grand, parent, child);
  }
    // LL-右旋调整
  private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
    // 让parent称为子树的根节点
    parent.parent = grand.parent;
    if (grand.isLeftChild()) {
      grand.parent.left = parent;
    } else if (grand.isRightChild()) {
      grand.parent.right = parent;
    } else { // grand是root节点
      root = parent;
    }

    // 更新child的parent
    if (child != null) {
      child.parent = grand;
    }

    // 更新grand的parent
    grand.parent = parent;

    // 更新高度
    updateHeight(grand);
    updateHeight(parent);
  }
```

###### 失衡调整的规律
![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201023200848.png)

- **找规律调整方法**
- 可以从上图看出,实际上失衡调整是有规律的,就是无论是LL,RR,LR,RL可以看出一系列规律
  - 未调整前,从左到右,abcdefg由大到小,调整之后依然是从左到右abcdefg由大到小
  - 最终恢复平衡之后的结构都是一模一样的
    - d永远都会成为失衡子树调整之后的根节点
    - abc独立为一颗子树,bdf独立为一颗子树,efg独立为一颗子树
- 所以我可以将所有的调整逻辑统一,只要找出对应的abcdefg节点,然后统一的按照最后的结构进行连接即可,而无需管到底是哪一种情况的旋转,还有旋转之后的维护
- **所以上面的失衡调整的代码可以有统一的实现方式rotate()方法**
```java
	/**
	 * 恢复平衡
	 * @param grand 高度最低的那个不平衡节点
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				rotate(grand, node, node.right, parent, parent.right, grand);
			} else { // LR
				rotate(grand, parent, node.left, node, node.right, grand);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotate(grand, grand, node.left, node, node.right, parent);
			} else { // RR
				rotate(grand, grand, parent.left, parent, node.left, node);
			}
		}
	}
```
- 未简化重构之前的rotate(),共传入7个参数,abcdefg各自情况时所代表的节点要从上面笔记来看,r传入是因为我们需要确定传入的哪一个节点是未调整之前这颗子树的根节点
```java
private void rotate(
			Node<E> r, // 子树的根节点
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		// 让d成为这棵子树的根节点
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
		
		// a-b-c
		b.left = a;
		if (a != null) {
			a.parent = b;
		}
		b.right = c;
		if (c != null) {
			c.parent = b;
		}
		updateHeight(b);
		
		// e-f-g
		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		f.right = g;
		if (g != null) {
			g.parent = f;
		}
		updateHeight(f);
		
		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
	}
	
```
- 但是还可以继续优化一下,因为a,g两个节点其实可以不处理,因为a,g两个节点在调整之前和调整之后所有连接都没有发生变化,也就是说调整可能影响的节点只有bcdef五个节点
```java
	private void rotate(
			Node<E> r, // 子树的根节点
			Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f) {
		// 让d成为这棵子树的根节点
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
		
		//b-c
		b.right = c;
		if (c != null) {
			c.parent = b;
		}
		updateHeight(b);
		
		// e-f
		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		updateHeight(f);
		
		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
	}
```
##### 删除导致的失衡
- (**结论**)删除只可能导致**父节点(未改变父节点高度)或祖先节点(特殊情况刚好删除节点导致父节点的高度变化,比如父节点只有一个叶子节点的情况,虽然删除该节点未导致父节点失衡,但是父节点高度由1变0可能会导致祖先节点的失衡)**的失衡,其他所有节点都不可能失衡,(**因为未改变父节点高度的情况下其他因子的平衡因子的计算和该节点无关,比如该节点的父节点的父节点(祖先节点),这个祖先节点的平衡因子依然是左子树高度减右子树高度,该节点被删除导致失衡肯定是因为其父节点的高度差变为2了,比如左子树高2,那么删除前左子树比右子树高1,这颗子树的高度依然是其左子树的高度,其祖先节点的平衡因子并不会改变**)
- (**结论**)只要删除的节点会导致父节点的失衡,那么证明被删除的节点一定是比较短的那个子树,所以其父节点的高度并不会改变,(**注意这个删除节点,父节点高度不会改变的结论只是对父节点失衡的条件成立的,如果本身删除节点并没有导致父节点失衡,那么父节点高度可能会改变,但是改变之后父节点依然是平衡的,并没有因为删除节点而失衡,但是此时祖先节点可能失衡**)

###### 删除后的平衡方案
- LL-右旋转情况
- ![](https://raw.githubusercontent.com/CodeNovice2017/ImageRepository/master/img/20201024101217.png)
  - **LL情况是通过反向理解成添加来理解的,从上图来看删除的节点是T3的尾部,导致的结果是g的失衡,g的失衡既可以从右节点的删除来理解,又可以从T0添加了一个节点导致失衡来理解,而T0添加的情况就是LL**
  - 而祖先节点是否会失衡,并不是说删除节点之后判断祖先节点是否失衡,上面结论说了删除节点只会导致父节点的失衡,但是删除调整失衡之后,这颗子树的高度就有可能变化了,那么其祖先节点也有可能失衡,极端情况下,可能要进行O(log(n))的调整
- **开发流程**
  - 先在BST中类似afterAdd,声明afterRemove方法,并放入remove方法中
  - **关键在于afterRemove()放的位置是一个需要理解的难点**
  - ```java
    protected void afterRemove(Node<E> node){};

  	private void remove(Node<E> node) {
		if (node == null)
			return;

		size--;
		// afterRemove(node)放在这里肯定是不对的,因为我们删除后调整的条件是,有一个节点真的被删除掉了
		// 但是现在这个位置afterRemove传入的node并不一定是真正被删除的节点,就是比如是度为2的节点并没有真正被删除
		// 真正被删除的是度为2的节点的前驱或者后继节点,度为2的节点只是element换为了前驱或者后继节点的element,具体可以看remove的实现笔记
		// afterRemove(node)

		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
		}

		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;

		if (replacement != null) { // node是度为1的节点
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}

			// 等到节点真的被删除后,并且其left,right,parent等都处理好了之后,就是完全删除维护逻辑完成后再进行失衡调整
			// 删除节点之后的处理
			afterRemove(node);
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;

			// 删除节点之后的处理
			afterRemove(node);
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}

			// AVL树其实上面的两行afterRemove其实可以不用写,都统一在最后这里处理即可,但是如果想让这个BST二叉搜索树兼容后面的红黑树的话
			// 最好是这三个位置都写上,因为将来可能会传更多的参数
			// 删除节点之后的处理
			afterRemove(node);
		}
	}
	
    ```
  - **需要理解为什么afterRemove和afterAdd逻辑一样,但是少一个break**
  - ```java
        // 失衡调整其实和afterAdd是一样的,只不过afterAdd添加节点的失衡调整只需要更改一次即可
        // 因为就是只需要向上层找到第一个失衡的节点调整平衡即可,但是remove引起的失衡要一直向上调整,判断所有的祖父节点
        // 所以只比afterAdd少一个break
        @Override
        protected void afterRemove(Node<E> node) {
            while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
            }
        }
    ```
##### 总结
- **添加**
  - 可能会导致所有祖先节点失衡
  - 只要让高度最低的失衡节点恢复平衡,整棵树就恢复平衡(O(1)次调整)
- **删除**
  - 只可能导致父节点失衡或祖先节点失衡,其他节点都不可能失衡
  - 让父节点恢复平衡之后,可能会导致更高层的祖先节点的失衡,最多需要O(log(n))
- 平均时间复杂度
  - 搜索O(log(n))
  - 添加O(log(n)),O(1)次调整
  - 删除O(log(n)),调整最多需要O(log(n))

#### B树
