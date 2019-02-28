import org.apache.spark.{SparkConf, SparkContext}

object test1 {
  def main(args: Array[String]) {

      val logFile ="D:\\data\\a.txt";

      val conf = new SparkConf().setAppName("WordCount").setMaster("local");

      val sc = new SparkContext(conf)
      val logData = sc.textFile(logFile, 2).cache()
      val numAs = logData.filter(line => line.contains("p")).count()
      val numBs = logData.filter(line => line.contains("h")).count()
      println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
      print("---------------");
      val lines =  sc.textFile(logFile)
      val pairs = lines.map(s=>(s,1))
      pairs.reduceByKey((x, y)=>x+y).foreach(println)

    }


}
