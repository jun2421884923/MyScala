import org.apache.spark.{SparkConf, SparkContext}
//:::三个冒号运算符表示List的连接操作。(类似于Java中的 list1.addAll(list2))
//::两个冒号运算符表示普通元素与list的连接操作。(类似于Java中的list1.add(A)操作)
//-> 构造元组和_N访问元组第N个元素
//_可以起到类似于*作用的通配符：
//import org.apache.spark.SparkContext._
//_指集合中的每一个元素
//指代集合中的每一个元素
/**
  * partition  特殊的filter返回两个列表，第一个满足，第二个不满足条件
  * scala> List.range(1, 11).partition(_ % 3 == 0)
res3: (List[Int], List[Int]) = (List(3, 6, 9),List(1, 2, 4, 5, 7, 8, 10))
  * find   特殊的filter返回满足的第一个元素
  * scala> List.range(1, 11).find(_ % 3 == 0)
res4: Option[Int] = Some(3)
  * takeWhile 返回的结果就是集合中前N个连续满足条件的元素
  * scala> List(1, 2, 3, -4, 5, 6, 7, 8, 9, 10) takeWhile (_ > 0)
res5: List[Int] = List(1, 2, 3)
  flatMap对于嵌套的集合（即集合中的元素还是集合），如果我们希望把每一个嵌套的子集“转换”成一个新的子集
  scala> List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)) flatMap (_.toList)
res2: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  flatten将所有嵌套的集合的元素一一取出逐一放置到一个集合中≈flatMap
  scala> List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)).flatten
res12: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
fold() 和reduce()类似，但是他可以设置初始值
  sum = rdd.reduce(lambda x, y: x + y)
  num.fold(0,lambda x,y:x+y)

  union():生成一个包含两个RDD中的所有元素的RDD
intersection（）：求两个RDD共同元素的RDD
subtract():移除一个RDD中的内容，例如移除 训练数据，rdd.subtract(other)
cartesian():与另一个 RDD的笛卡尔积；rdd.cartesian(other)

  */
object test {
  /**
    * println( factorial(2) )阶乘
    * @param i
    * @return
    */
  val config = new SparkConf().setAppName("test").setMaster("local")
  val sc = new SparkContext(config)
  def factorial(i: Int): Int = {
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }
    fact(i, 1)
  }
  def factorial(n: BigInt): BigInt = {
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }
  def printStrings( args:String* ) = {
    var i : Int = 0;
    for( arg <- args ){
      println("Arg value[" + i + "] = " + arg );
      i = i + 1;
    }
  }
  def describe(x: Any) =
    x match {
      case 5 => "five"
      case true => "truth"
      case "hello" => "hi!"
      case Nil => "the empty list"
      case _ => "something else"
    }

  /**
    * 类型匹配
    * @param x
    * @return
    *
  // match only positive integers
case n: Int if 0 < n => ...
// match only strings starting with the letter ‘a’
case s: String if s(0) == 'a' => ..
    */
  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => 1
  }
  def makeRdd(): Unit ={

    var rdd = sc.parallelize(1 to 10,3)//2个参数，第一个为集合，第二个为分区数
    var res=rdd.map(x =>{ if (x%2==0) x+1 else x})
    var r=res.filter(x => x+1<=7)
    r.foreach(x => print(x))
    r.union(rdd)
    r.take(3)
    r.saveAsTextFile("d:\\data\\ttt")
    rdd.countByValue()
  }
  def joinRdd(): Unit ={
    val a1 = List((2,(200,300)), (3,(400,500)), (4,(500,600)))
    val a2 = List((2,(200,300)), (3,(400,500)), (1,(500,600)))
    val rdd1 = sc.parallelize(a1)
    val rdd2 = sc.parallelize(a2)
    rdd1.leftOuterJoin(rdd2).foreach(println)
//
    // 关于Option的处理:
    val rdd3 = rdd1.leftOuterJoin(rdd2)
    rdd3.map{ case (id,(x,y))=>
      val (y3,y2) = y.getOrElse((null, null))
      (id, x._1, x._2, y3, y2)
    }.foreach(println)
  }
  def groupby(): Unit ={
    // 目的: 生成可以进行groupByKey的rdd
    var arr1 = new Array[(Int, Int)](10)
    arr1(0) = (1,1)
    arr1(1) = (1,2)
    arr1(2) = (2,1)
    arr1(3) = (3,1)
    arr1(4) = (10,1)
    val rddA = sc.parallelize(arr1.filter(_ != null).map{case (n1,n2)=>(n1,n2)})
    rddA.groupByKey().foreach(println)
  }
  def main(args: Array[String]):Unit= {
    joinRdd()
//    println(List.range(1, 11).partition(_ % 3 == 0)._1)
//    var t= List(1, 2, 3, 4, 5).map(_ + 1)
//    println(t)



//    var touple=(123,"fsad",51)
//    println(touple._1)
//    println(describe("sd"))


//    printStrings("python","java","scala")
//    var arr=Array(1,3,5,7,9)
//    for(a <- 1 to (arr.length-1)){
//        println(arr(a))
//    }
//    var a:String="qew"
//    var b:Int=123
//    print(a.toUpperCase+b.toString)


//    var f=(x:Int) => x +1
//    println(f(5))
//    var list = Array(1,2,3,4)
//    for (aa <- list) {
//      printf(aa+"   ")
//    }
//    var s:String = "a"
//    s+="b"
//    println(s)
//    s++="c"
//    println(s)
//    val first = (1,2,3) // 定义三元元组

//    val one = 1
//    val two = 2
//    val three = one -> two
//
//    println(three) // 构造二元元组

//    println(three._2) // 访问二元元组中第二个值
//    val lst = List(1,2,3,4,5)
//    val lstFilter = lst.filter(_ > 3)
//    println(lstFilter)
//    val m = Map(1 -> 2,2 -> 4)
//    for ((k,_) <- m) println(k) //如果不需要所有部件， 则在不需要的部件使用_； 本例只取key,因此在value处用_


    //var z:Array[String] = new Array[String](3)
    // var myList = Array(1.9, 2.9, 3.4, 3.5)
    //var total = 0.0;
    //for ( i <- 0 to (myList.length - 1)) {
    //  total += myList(i);
    //}
    //println("总和为 " + total);

  }
}
