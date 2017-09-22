package lab.zlren.demo

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
	def main(args: Array[String]): Unit = {
		//Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

		val conf = new SparkConf().setAppName("SparkPi").setMaster("local[2]")
		val sc = new SparkContext(conf)

		val slices = 2
		val n = 10000000 * slices

		val count = sc.parallelize(1 to n, slices).map(_ =>
			if (math.pow(math.random * 2 - 1, 2) + math.pow(math.random * 2 - 1, 2) < 1) 1 else 0
		).reduce(_ + _)

		println("pi is roughly " + 4.0 * count / n)
	}
}
