package flashtext

import scala.collection.mutable

case class KeywordProcessor(caseSensitive: Boolean = false) {

  var rootNode: KeywordTrieNode = KeywordTrieNode()

  def addKeyWord(word: String): Unit = {
    val inputWord = if (caseSensitive) word.toLowerCase else word
    addKeyWord(inputWord, inputWord)
  }

  def addKeyWord(word: String, cleanName: String): Unit = {
    val (inputWord, inputCleanName) = if (caseSensitive) (word.toLowerCase, cleanName.toLowerCase) else (word, cleanName)
    rootNode.add(inputWord, inputCleanName, inputWord.toList)
  }

  def findKeywords(input: String): mutable.Set[String] = {
    var keywords = mutable.Set[String]()
    var currentNode = rootNode
    input.foreach { c =>
        val character = if (caseSensitive) c.toLower else c
        currentNode.get(character) match {
          case Some(node) =>
            currentNode = node
            currentNode.get().map{keywords += _}
          case None => currentNode = rootNode
        }
    }
    keywords
  }

  def replaceKeywords(input: String): String = {
    val output: mutable.StringBuilder = new mutable.StringBuilder()
    val buffer: mutable.StringBuilder = new mutable.StringBuilder()
    var currentNode = rootNode
    input.foreach { c =>
        val character = if (caseSensitive) c.toLower else c
        val node = currentNode.get(character)
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
