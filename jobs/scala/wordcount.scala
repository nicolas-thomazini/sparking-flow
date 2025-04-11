import org.apache.spark.sql.SparkSession

object WordCount {
    def main(args: Array[String]) {
        val spark = SparkSession.builder.appName("Word Count").master("spark://spark-master:7077").getOrCreate()
        val sc = spark.sparkContext

        val textData = sc.parallelize(List("Hello World", "Hello Spark", "Hello Scala", "Hello Nicolas"))

        val counts = textData.flatMap(line => line.split(" "))
                                .map(word => (word, 1))
                                .reduceByKey(_ + _)
        
        counts.collect().foreach(println) //printing thhe word counts directly

        spark.stop()
    }
}