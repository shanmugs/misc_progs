package lt.redis.redistester.main.util

import scala.util.Random

object Generator {

  val random = Random

  val WORDS = Array(
    "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
    "Vestibulum", "vel", "nibh", "ac", "enim", "tristique", "accumsan", "Vivamus", "varius",
    "sapien", "libero", "Nullam", "placerat", "lectus", "nec", "lorem", "interdum", "quis",
    "viverra", "purus", "elementum", "Maecenas", "aliquet", "elit", "sodales", "ante", "porta",
    "eleifend", "Suspendisse", "est", "orci", "suscipit", "a", "turpis", "vitae", "lobortis",
    "iaculis", "mauris", "Praesent", "cursus", "eros", "et", "sem", "accumsan", "faucibus",
    "Morbi", "tincidunt", "nunc", "a", "neque", "maximus", "convallis", "Integer", "sed",
    "molestie", "nisl", "Vestibulum", "quis", "magna", "porttitor", "fermentum", "ex",
    "at", "lobortis", "dolor", "Nam", "eu", "rhoncus", "nisi", "et", "bibendum", "elit",
    "Aenean", "pulvinar", "blandit", "massa", "eu", "condimentum", "Duis", "congue", "felis",
    "ac", "placerat", "feugiat", "Maecenas", "lacinia", "hendrerit", "nunc", "a", "tristique",
    "est", "sodales", "sed", "Maecenas", "varius", "pharetra", "felis", "ultrices", "accumsan",
    "ex", "Morbi", "et", "porttitor", "ipsum", "Maecenas", "in", "vulputate", "nisl", "in",
    "ullamcorper", "ante")

  def key() :String = key(word(), word(), word())

  def key(domain: String, tableName: String, query: String): String =
    s"${domain}#${tableName}#${query}"

  def word() :String = WORDS(random.nextInt(WORDS.length))

  def phrase(numWords: Int): String =
    (0 until numWords).map(_ => word()).foldLeft("")(_ + _ + ' ')
}
