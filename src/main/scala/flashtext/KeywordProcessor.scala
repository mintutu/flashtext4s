package flashtext

import scala.collection.mutable

case class KeywordProcessor(caseSensitive: Boolean = false) {

  var rootNode: KeywordTrieNode = KeywordTrieNode()

  def addKeyWord(word: String): Unit = {
    addKeyWord(word, word)
  }

  def addKeyWord(word: String, cleanName: String): Unit = {
    rootNode.add(word, cleanName, word.toList)
  }

  def findKeywords(input: String): mutable.Set[String] = {
    var keywords = mutable.Set[String]()
    var currentNode = rootNode
    input.foreach {
      c =>
        val node = currentNode.get(c)
        if (node.isDefined) {
          currentNode = node.get
          currentNode.get().map{keywords += _}
        } else {
          currentNode = rootNode
        }
    }
    keywords
  }

  def replaceKeyWords(input: String): String = {
    val output: mutable.StringBuilder = new mutable.StringBuilder()
    val buffer: mutable.StringBuilder = new mutable.StringBuilder()
    var currentNode = rootNode
    input.foreach {
      c =>
        val node = currentNode.get(c)
        if (node.isDefined) {
          currentNode = node.get
          currentNode.get() match {
            case Some(name) =>
              buffer.clear()
              buffer.append(name)
            case None =>
              buffer.append(c)
          }
        } else {
          output.append(buffer)
          output.append(c)
          buffer.clear()
          currentNode = rootNode
        }
    }
    if (buffer.nonEmpty) {
      output.append(buffer)
    }
    output.toString()
  }
}
