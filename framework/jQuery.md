### javascript 
- document.write();方法会把write的内容覆盖整个html页面.

***

### jQuery获得内容和属性
##### 获得内容
- html() 包括 HTML 标记
- text() 文本内容
- val() 设置或返回表单字段的值,元素必修具备value属性

#### 获得属性
- attr() 括号中需要指出属性的名称

### 设置内容和属性
- 设置内容，在括号中指出需要设置的内容即可
- 设置属性，.attr("href":"www.baidu.com","title":"百度");

### 添加元素
- append() 在被选元素的结尾插入内容
- prepend() 在被选元素的开头插入内容
- after() 在被选元素之后插入内容
- before() 在被选元素之前插入内容

### 删除元素
- remove() 删除被选元素及其子元素
- empty() 从被选元素中删除子元素

### 获取并设置css类
- addClass() 向被选中的元素添加一个或多个类
- removeClass() 从被选中元素删除一个或多个类
- toggleClass() 添加/删除 类
- css()方法 设置或返回被选元素的一个或多个属性
 1. 返回css属性 .css("background-color");
 2. 设置css属性 .css({"background-color":"yellow","font-size":"200%"});

### jQuery实现ajax
- 一定要搭建服务器才能运行，请求静态页面没有反应  ( 蠢啊,真是的 * _ * )

***

### jQuery
- $.ajax()

 ```
$.ajax({url:"",success:function(result){
}});
 ```

- $.get()

```
$.get(url,data,function(data,status,xhr){

},dataType)
``` 
url规定为需要请求的url,data可选 规定连同请求发送到服务器的数据,function可选，规定为请求成功时运行的函数,dataType 规定预期的服务器响应的数据类型，默认会智能判断.

- $.getJSON()
- $.getScript()

- $.post()  使用ajax的HTTP POST请求从服务器加载数据
- ajaxComplete()  ajax请求完成
- ajaxError()    ajax请求失败
- ajaxSend() ajax发送之前
- ajaxStart() 第一个ajax请求开始时的函数
- ajaxStop() 所有的ajax请求完成时运行的函数
- ajaxSuccess() ajax请求完成时运行的函数
- load() 从服务器加载数据
- serialize()  通过序列化表单值创建 URL 编码文本字符串
- serializeArray() 通过序列化表单值来创建对象（name 和 value）的数组

***
### noConflict()
- $.noConflict();  会释放会 $ 标识符的控制,仍可以使用全名jQuery来使用jQuery.

### jQuery 杂项
- data() 向元素添加数据或从被选元素获取数据.
 1. 添加数据 $("#xx").data("nn","value");
 2. 取回数据 $("#xx").data("nn");
 3. 移除数据 removeData()
- each() 为每个匹配元素规定要运行的函数
 * $(selector).each(function(index,element)) 选择器的index位置  element 当前元素
- get() 获取由选择器指定的DOM元素
- index() 返回指定元素相对于其他元素的index位置
- $.param(Object) 创建数组或对象的序列化表示形式.
- toArray() 以数组的形式返回 jQuery 选择器匹配的元素

### jQuery属性
- jquery 返回jQuery的版本号
- length 包含jQuery对象中元素的数目

***
### jQuery插件
##### jQuery Validate
- 需要导入jQuery库和jquery.validate库文件
- 默认校验规则
 - 常用 required minlength equalTo remote(使用ajax方法)
- 默认提示 messages
- 使用方式
 - 将校验规则写到控件中
 ```
 $.validator.setDefaults({    
   submitHandler:function(){
       //成功提交运行的函数
   }
  });
 ```
 ```
 $().ready(function(){
     //开始验证代码
     $("#xx").validate();
 });
 ```
 - 将校验规则写到js代码中
 ```
 $().ready(function(){
     $("#xx").validate({
         rules:{
         
         },
         messages:{
         
         }
     });
 });
 ```
- 常用方法以及注意问题
 - 使用其他方式替代默认的submit
 ajax
 ```
 $("#xx").validate({
     submitHandler:function(form){
         $(form).ajaxSubmit();
     }
 });
 ```
 - 只验证，不提交，debug
 ```
 $().ready(function(){
     $("#xx").validate({
         debug:true
     });
 });
 ```
 - 更改错误信息的显示位置
 
  默认情况 error.appendTo(element.parent); 把错误信息放在验证的元素后面
  ```
  errorPlacement:function(error,element){
      //修改错误显示的位置等
  }
  ```
  errorElement:"em" 标记错误的标签改为em
 - 每个字段验证通过执行函数
 ```
 success:function(){
     //执行代码
 }
 ```
 - 异步验证,远程验证只能返回true或者false
 ```
 remote:{
     url:"  ",
     type:"post"
     dataType:"json"    //接收数据的格式
     data:{
     
     }
 }
 ```
 - radio 的required表示必须选中一个
 - checkbox 的required表示必须选中，minlength表示必须选中的最小个数
 - select 的required表示选中的value不能为空
 - ignore 对某项元素不进行验证
 ```
 $("#").validate({
     ignore:".ignore"
 });
 ```
