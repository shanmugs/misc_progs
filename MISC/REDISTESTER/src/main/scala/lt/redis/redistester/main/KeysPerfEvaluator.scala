package lt.redis.redistester.main

import com.redis.{RedisClient, Seconds}
import com.typesafe.config.ConfigFactory
import lt.redis.redistester.main.util.Timer._
import lt.redis.redistester.main.util.{Generator, NoiseMaker, Timer}

import scala.util.Random

object KeysPerfEvaluator extends App {

  val NUM_ELEMENTS_PER_TEST = 1000
  val NUM_NOISE_ELEMENTS = 1000
  val NUM_NOISE_SUBELEMENTS = 100
  val ITERATIONS_PER_TEST = 10


  val conf = ConfigFactory.load

  val client = time("Connect to Redis",{
    new RedisClient(conf.getString("redis.host"), conf.getInt("redis.port"))})
  val noise = new NoiseMaker(client)

  println("Test ZADD")
  (0 to ITERATIONS_PER_TEST).foreach(_ => {
    client.flushall
    testZadd()
  })

  Timer.getFormatMedia("zadd_add").map(println)
  Timer.getFormatMedia("zadd_get_keys").map(println)

  println("Test simple add")
  (0 to ITERATIONS_PER_TEST).foreach(_ => {
    client.flushall
    testSimpleAdd()
  })

  Timer.getFormatMedia("simple_add").map(println)
  Timer.getFormatMedia("simple_get_keys").map(println)

  //printAllStats()

  def testZadd() = {
    noise.zkeys(NUM_NOISE_ELEMENTS, NUM_NOISE_SUBELEMENTS)

    val key = s"zkey_${Random.nextInt()}"

    save("zadd_add", {
      for (i <- 1 to NUM_ELEMENTS_PER_TEST) {
        client.zadd(key, i, s"value ${Generator.phrase(7)} +")
        //client.zremrangebyscore(key, 999, 100000)
      }})


    save("zadd_get_keys", {
      val r = client.zrangebyscore(key, 0, true, 999, false, Option.empty)
      //r.map(x => println("zrangebyscore items " + x.size))

    })

  }

  def testSimpleAdd() = {
    noise.keys(NUM_NOISE_ELEMENTS * NUM_NOISE_SUBELEMENTS)

    save("simple_add", {
      for (i <- 1 to NUM_ELEMENTS_PER_TEST) {
        client.set(s"skey_$i", s"value ${Generator.phrase(7)}", false, Seconds(60 * 60))
      }
    })

    save("simple_get_keys", {
      val r = client.keys("skey_*")
      //r.map(x => println("keys items " + x.size))
    })
  }
}
