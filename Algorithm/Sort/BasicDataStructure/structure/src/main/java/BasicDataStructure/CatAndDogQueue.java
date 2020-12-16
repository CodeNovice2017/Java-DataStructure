package BasicDataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class CatAndDogQueue {

    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    // 关于为什么要设计这个类,是为了进一步封装上面的Pet,并在这个类中维护index属性
    // 因为我们不可能将题目提供的Cat Dog Pet加上一个int index属性
    // 所以设计这么一个PetEnterQueueElement类,扩展维护了每一个要进入接下来猫狗队列的元素
    // 关于为什么要设置为static的,是因为我在后面的静态内部类CatDogQueue中会new PetEnterQueueElement()
    // 如果不加static,会报错The member type CatDogQueue cannot be declared static; static
    // types can only be declared in static or top level typesJava
    public static class PetEnterQueueElement{
        private Pet pet;
        private long index;

        public PetEnterQueueElement(Pet pet,long index){
            this.pet = pet;
            this.index = index;
        }
        private Pet getPet(){
            return this.pet;
        }
        private long getCount(){
            return this.index;
        }   
    }
    public static class CatDogQueue {

        private Queue<PetEnterQueueElement> dogQueue = new LinkedList<>();
        private Queue<PetEnterQueueElement> catQueue = new LinkedList<>();
        private long index;

        public CatDogQueue() {
            dogQueue = new LinkedList<>();
            catQueue = new LinkedList<>();
            index = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                dogQueue.offer(new PetEnterQueueElement(pet, index++)); // 因为我这里会new
                                                                        // PetEnterQueueElement,所以PetEnterQueueElement一定是static的
            } else if (pet.getPetType().equals("cat")) {
                catQueue.offer(new PetEnterQueueElement(pet, index++));
            } else {
                throw new RuntimeException("err,element is not a dog or cat!");
            }
        }

        public Pet pollAll() {
            if (!this.isDogEmpty() && !this.isCatEmpty()) {
                if (this.dogQueue.peek().getCount() < this.catQueue.peek().getCount()) {
                    return dogQueue.poll().pet;
                } else {
                    return catQueue.poll().pet;
                }
            } else if (!this.isDogEmpty()) {
                return dogQueue.poll().pet;
            } else if (!this.isCatEmpty()) {
                return catQueue.poll().pet;
            } else {
                throw new RuntimeException("Queue is empty!");
            }
        }

        public Pet pollDog() {

            if (!this.isDogEmpty()) {
                return (Dog) this.dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Pet pollCat() {
            if (!this.isCatEmpty()) {
                return (Dog) this.dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isDogEmpty() {
            if (this.dogQueue.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isCatEmpty() {
            if (this.catQueue.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isEmpty() {
            if (this.isCatEmpty() && this.isDogEmpty()) {
                return true;
            } else {
                return false;
            }
        }

    }
}

