### 设计模式
#### 原则
- 开闭原则：对扩展开放，对更改封闭
- 里氏代换原则：子类可以替换父类，并且出现在父类出现的任何地方
- 依赖倒转原则：针对接口编程，依赖于抽象而不依赖于具体
- 接口隔离原则：使用多个隔离的接口，比使用单个接口要好。降低类之间的耦合度
- 迪米特法则：一个对象应该对其他对象保持最少的了解
- 单一职责原则：一个类应该仅有一个引起它变化的原因
#### 常见设计模式
- 工厂模式
  - 定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，使其创建过程延迟到子类进行

  创建接口
  ```java
  public interface Shape{
      void draw();
  }
  ```
  创建实现接口的实体类
  ```java
  public class Rectangle implements Shape{
      @Override
      public void draw(){
          System.out.println("Rectangle:draw() method");
      }
  }
  ```
  创建工厂
  ```java
  public class ShapeFactory{
      public Shape getShape(String shapeType){
          if(shapeType.equalsIgnoreCase("Rectangle")){
              return new Rectangle();
          }
      }
  }
  ```
  使用该工厂
  ```java
  public class Main{
      public static void main(String[]args){
          ShapeFactory shapeFactory = new ShapeFactory();
          Shape shape = shapeFactory.getShape("Rectangle");
          shape.draw();
      }
  }
  ```
- 单例模式——只有一个实例
```
public class Test{
    // 构造函数私有
    private Test(){

    }
    // 被加载时就会实例化对象
    private static Test item = new Test();

    public static Test getItem(){
        return item;
    }
}
```
- 适配器模式
  - 将一个类的接口转换成客户希望的另一个接口
  - 需要用类A来实现接口B，但是类A并没有实现接口B中的所有方法，但是类A不能改变，这是可以创建一个类C，继承类A并实现接口B，此时类C就是一个适配器

接口B
```java
public interface B{
    void method1();
    void method2();
}
```
类A
```java
public class A{
    public void method1(){
        System.out.println("method1");
    }
}
```
类C
```java
public class C extends A implements B{
    public void method2(){
        System.out.println("method2");
    }
}
```
使用
```java
public class Main{
    public static void main(String[]args){
        C c = new C();
        c.method1();
        c.method2();
    }
}
```
- 观察者模式——也称发布订阅模式
  - 一种一对多的依赖关系，一个对象的状态发生改变，所有依赖于它的对象都得到通知并被自动更新

观察者接口
```java
public abstract class Observer{
    public abstract void update(String msg);
}
```
观察者一
```java
public class Observer1 extends Observer{
    public void update(String msg){
        System.out.println(Observer1.class.getName()+" "+msg);
    }
}
````
观察者二
```java
public class Observer2 extends Observer{
    public void update(String msg){
        System.out.println(Observer2.class.getName()+" "+msg);
    }
}
````
被观察者
```java
public class Subject{

    private List<Observer> observers = new ArrayList<>();

    public void setMsg(String msg){
        notifyAll(msg);
    }

    public void addAttach(Observer observer){
        observers.add(observer);
    }

    // 通知所有订阅者
    public void notifyAll(String msg){
        for(Observer observer:observers){
            observer.update(msg);
        }
    }
}
```
使用
```java
public class Main{
    Observer1 observer1 = new Observer1();
    Observer2 observer2 = new Observer2();

    Subject subject = new Subject();
    subject.addAttach(observer1);
    subject.addAttach(observer2);
    object.setMsg("msg change");
}
```