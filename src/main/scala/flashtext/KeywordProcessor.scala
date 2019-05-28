package flashtext

import scala.collection.mutable

case class KeywordProcessor(caseSensitive: Boolean = false) {

  var rootNode: KeywordTrieNode = KeywordTrieNode()

  var _terms_in_trie = 0

  def addKeyWord(word: String): Unit = {
    addKeyWord(word, word)
  }

  def addKeyWord(word: String, cleanName: String): Unit = {
    rootNode.add(word, cleanName, word.toList)
    _terms_in_trie += 1
  }

  def extractKeywords(chars: List[Char]): mutable.Set[String] = {
    var keywords = mutable.Set[String]()
    var currentNode = rootNode
    chars.foreach {
      c =>
        val node = currentNode.get(c)
        if (node.isDefined) {
          currentNode = node.get
          if (currentNode.get().nonEmpty) {
            keywords += currentNode.get()
          }
        } else {
          currentNode = rootNode
        }
    }
    keywords
  }
}
