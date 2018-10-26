# Spring boot  使用spring-data-jpa简化数据访问层操作

spring.jpa.properties.hibernate.hbm2ddl.auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：

* create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
* create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
* update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
* validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

## Spring Data JPA 的基本语法和使用示例

### 官方文档：

中文文档：

## 单元测试
## 新断言assertThat使用

JUnit 4.4 结合 Hamcrest 提供了一个全新的断言语法——assertThat。程序员可以只使用 assertThat 一个断言语句，结合 Hamcrest 提供的匹配符，就可以表达全部的测试思想，我们引入的版本是Junit4.12所以支持assertThat。

#### assertThat 的基本语法如下：

清单 1 assertThat 基本语法

```java
assertThat( [value], [matcher statement] );1
```

- value 是接下来想要测试的变量值；
- matcher statement 是使用 Hamcrest 匹配符来表达的对前面变量所期望的值的声明，如果 value 值与 matcher statement 所表达的期望值相符，则测试成功，否则测试失败。

#### assertThat 的优点

- 优点 1：以前 JUnit 提供了很多的 assertion 语句，如：assertEquals，assertNotSame，assertFalse，assertTrue，assertNotNull，assertNull 等，现在有了 JUnit 4.4，一条 assertThat 即可以替代所有的 assertion 语句，这样可以在所有的单元测试中只使用一个断言方法，使得编写测试用例变得简单，代码风格变得统一，测试代码也更容易维护。
- 优点 2：assertThat 使用了 Hamcrest 的 Matcher 匹配符，用户可以使用匹配符规定的匹配准则精确的指定一些想设定满足的条件，具有很强的易读性，而且使用起来更加灵活。如清单 2 所示：

清单 2 使用匹配符 Matcher 和不使用之间的比较

```java
// 想判断某个字符串 s 是否含有子字符串 "developer" 或 "Works" 中间的一个
// JUnit 4.4 以前的版本：assertTrue(s.indexOf("developer")>-1||s.indexOf("Works")>-1 );
// JUnit 4.4：
assertThat(s, anyOf(containsString("developer"), containsString("Works"))); 
// 匹配符 anyOf 表示任何一个条件满足则成立，类似于逻辑或 "||"， 匹配符 containsString 表示是否含有参数子 
// 字符串，文章接下来会对匹配符进行具体介绍123456
```

- 优点 3：assertThat 不再像 assertEquals 那样，使用比较难懂的“谓宾主”语法模式（如：assertEquals(3, x);），相反，assertThat 使用了类似于“主谓宾”的易读语法模式（如：assertThat(x,is(3));），使得代码更加直观、易读。
- 优点 4：可以将这些 Matcher 匹配符联合起来灵活使用，达到更多目的。如清单 3 所示： 
  清单 3 Matcher 匹配符联合使用

```java
字符相关匹配符
/**equalTo匹配符断言被测的testedValue等于expectedValue，
* equalTo可以断言数值之间，字符串之间和对象之间是否相等，相当于Object的equals方法
*/
assertThat(testedValue, equalTo(expectedValue));
/**equalToIgnoringCase匹配符断言被测的字符串testedString
*在忽略大小写的情况下等于expectedString
*/
assertThat(testedString, equalToIgnoringCase(expectedString));
/**equalToIgnoringWhiteSpace匹配符断言被测的字符串testedString
*在忽略头尾的任意个空格的情况下等于expectedString，
*注意：字符串中的空格不能被忽略
*/
assertThat(testedString, equalToIgnoringWhiteSpace(expectedString);
/**containsString匹配符断言被测的字符串testedString包含子字符串subString**/
assertThat(testedString, containsString(subString) );
/**endsWith匹配符断言被测的字符串testedString以子字符串suffix结尾*/
assertThat(testedString, endsWith(suffix));
/**startsWith匹配符断言被测的字符串testedString以子字符串prefix开始*/
assertThat(testedString, startsWith(prefix));
一般匹配符
/**nullValue()匹配符断言被测object的值为null*/
assertThat(object,nullValue());
/**notNullValue()匹配符断言被测object的值不为null*/
assertThat(object,notNullValue());
/**is匹配符断言被测的object等于后面给出匹配表达式*/
assertThat(testedString, is(equalTo(expectedValue)));
/**is匹配符简写应用之一，is(equalTo(x))的简写，断言testedValue等于expectedValue*/
assertThat(testedValue, is(expectedValue));
/**is匹配符简写应用之二，is(instanceOf(SomeClass.class))的简写，
*断言testedObject为Cheddar的实例
*/
assertThat(testedObject, is(Cheddar.class));
/**not匹配符和is匹配符正好相反，断言被测的object不等于后面给出的object*/
assertThat(testedString, not(expectedString));
/**allOf匹配符断言符合所有条件，相当于“与”（&&）*/
assertThat(testedNumber, allOf( greaterThan(8), lessThan(16) ) );
/**anyOf匹配符断言符合条件之一，相当于“或”（||）*/
assertThat(testedNumber, anyOf( greaterThan(16), lessThan(8) ) );
数值相关匹配符
/**closeTo匹配符断言被测的浮点型数testedDouble在20.0¡À0.5范围之内*/
assertThat(testedDouble, closeTo( 20.0, 0.5 ));
/**greaterThan匹配符断言被测的数值testedNumber大于16.0*/
assertThat(testedNumber, greaterThan(16.0));
/** lessThan匹配符断言被测的数值testedNumber小于16.0*/
assertThat(testedNumber, lessThan (16.0));
/** greaterThanOrEqualTo匹配符断言被测的数值testedNumber大于等于16.0*/
assertThat(testedNumber, greaterThanOrEqualTo (16.0));
/** lessThanOrEqualTo匹配符断言被测的testedNumber小于等于16.0*/
assertThat(testedNumber, lessThanOrEqualTo (16.0));
集合相关匹配符
/**hasEntry匹配符断言被测的Map对象mapObject含有一个键值为"key"对应元素值为"value"的Entry项*/
assertThat(mapObject, hasEntry("key", "value" ) );
/**hasItem匹配符表明被测的迭代对象iterableObject含有元素element项则测试通过*/
assertThat(iterableObject, hasItem (element));
/** hasKey匹配符断言被测的Map对象mapObject含有键值“key”*/
assertThat(mapObject, hasKey ("key"));
/** hasValue匹配符断言被测的Map对象mapObject含有元素值value*/
assertThat(mapObject, hasValue(value));
```