package lt.redis.redistester.main.util

import scala.collection.mutable

object Timer {

  var saveTimes = mutable.Map[String, Array[Long]]()

  def time[R](block: => R): R = time("time", block)

  def time[R](tag: String, block: => R): R = {

    val initTime = System.nanoTime()
    val result = block
    val endTime = System.nanoTime()
    val time = endTime - initTime

    println(formatTime(tag, time))
    result
  }

  def save[R](tag: String, block: => R): R = {
    val initTime = System.nanoTime()
    val result = block
    val endTime = System.nanoTime()

    if (!saveTimes.contains(tag)) {
      saveTimes.put(tag, Array())
    }
    saveTimes.put(tag, saveTimes(tag) :+ (endTime - initTime))
    result
  }

  def printAllStats(): Unit = {
    saveTimes.foreach(x => {
      val time = x._2.foldLeft(0L)(_ + _) / x._2.size.toLong
      println(formatTime(x._1, time))
    })
  }

  def formatTime(tag: String, time: Long): String =
    s"[$tag] Elapsed time: ${time * 1e-9} s, ${time * 0.001} ms, ${time} ns"

  def getTime(tag: String) = saveTimes.get(tag)

  def getMedia(tag: String) = saveTimes.get(tag)
    .map(x => x.foldLeft(0L)(_ + _) / x.size.toLong)

  def getFormatMedia(tag: String) = saveTimes.get(tag)
      .map(x => formatTime(tag,
        x.foldLeft(0L)(_ + _) / x.size.toLong))
}
