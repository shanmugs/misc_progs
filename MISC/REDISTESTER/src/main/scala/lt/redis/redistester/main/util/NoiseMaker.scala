package lt.redis.redistester.main.util

import com.redis.RedisClient

import scala.util.Random

class NoiseMaker(client: RedisClient) {

  def keys(items: Int = 1): Unit = {
    if (items > 0) {
      (0 until items).foreach(x =>
        client.set(s"fake_${x}_${Random.nextInt()}", Generator.word())
      )
    }
  }

  def zkeys(items: Int = 1, itemsContent: Int = 1): Unit = {
    (0 until items).foreach(x => {
      val key = s"fake_${x}_${Random.nextInt()}"
      (0 until itemsContent).foreach(y => {
        client.zadd(key, y, Generator.word())
      })
    })
  }

}
