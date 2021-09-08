public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 传入数组容量创建数组
     *
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 创建默认容量的数组
     */
    public Array() {
        this(10);
    }

    /**
     * 获得数组内元素数量
     *
     * @return 数组内元素数量
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 获得数组容量
     *
     * @return 数组容量
     */
    public int getCapacity() {
        return this.data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return 数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获得指的索引位置的元素
     *
     * @return 索引位置的元素
     */
    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed! Illegal Index!");
        }
        return data[index];
    }

    /**
     * 更新指定索引位置的元素为e
     */
    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed! Illegal Index!");
        }
        data[index] = e;
    }

    //判断数组中是否存在元素e
    public boolean contains(int e) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                flag = true;
            }
        }
        return flag;
    }

    //获得存在的元素e的索引,不存在则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    //向所有元素后添加一个元素
    public void addLast(E e) {
        add(size, e);
    }

    //向所有元素前添加一个元素
    public void addFirst(E e) {
        add(0, e);
    }

    //在第index位置插入一个元素
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("Add Failed! Require index <= size and index >= 0");
        }
        if (size == data.length) {
            resize(2* data.length);

        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    //删除指定位置的元素,返回删除的元素
    public E remove(int index){
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Remove Failed! Require index <= size and index >= 0");
        }
        if (size < data.length/4 && size>=10) {
            resize(data.length/2);
        }
        E ret=data[index];
        for(int i=index;i<size-1;i++){
            data[i]=data[i+1];
        }
        size--;
        data[size]=null;
        return ret;
    }

    //删除首个元素并返回
    public E removeFirst(){
        return remove(0);
    }

    //删除最后一个元素并返回
    public E removeLast(){
        return remove(size-1);
    }

    //从数组中删除元素e
    public boolean removeElement(E e){
        int index=find(e);
        if(index!=-1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size=%d, Capacity=%d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    //数组扩容
    private void resize(int newCapacity){
        E[] newData=(E[])new Object[newCapacity];
        for (int i=0;i<size;i++){
            newData[i]=data[i];
        }
        data=newData;
    }

}
