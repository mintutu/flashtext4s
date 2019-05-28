package flashtext

import scala.collection.mutable

case class KeywordTrieNode(keyword: String = "", cleanName: String = "", children: mutable.Map[Char, KeywordTrieNode] = mutable.Map.empty) {

  def contains(c: Char) = children.contains(c)

  def contains(word: String) = keyword.nonEmpty && word == keyword

  def isEmpty() =  children.isEmpty

  def get(c: Char) = children.get(c)

  def get() = if (cleanName.nonEmpty) cleanName else keyword

  def add(word: String, cleanName: String, characters: List[Char]): KeywordTrieNode = {
    characters match {
      case head :: remainedChars =>
        val node = get(head).getOrElse(new KeywordTrieNode())
        children += head -> node.add(word, cleanName, remainedChars)
        this
      case Nil => KeywordTrieNode(word, cleanName)
    }
  }

  def toStringHelper(pad: String): String = {
    if (children.isEmpty) {
      "\n "
    } else {
      pad + children.keys.map {
        e => s"$e ${children.get(e).get.toStringHelper(pad + " ")}"
      }.mkString
    }
  }
}
