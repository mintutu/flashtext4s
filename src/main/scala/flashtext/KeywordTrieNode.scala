package flashtext

import scala.collection.mutable

case class KeywordTrieNode(keyword: Option[String] = None,
                           cleanName: Option[String] = None,
                           children: mutable.Map[Char, KeywordTrieNode] = mutable.Map.empty) {

  def contains(c: Char) = children.contains(c)

  def contains(word: String) = keyword.nonEmpty && word == keyword

  def isEmpty() =  children.isEmpty

  def get(c: Char) = children.get(c)

  def get() = cleanName orElse keyword

  def add(word: String, cleanName: String, characters: List[Char]): KeywordTrieNode = {
    characters match {
      case head :: remainedChars =>
        val node = get(head).getOrElse(new KeywordTrieNode())
        children += head -> node.add(word, cleanName, remainedChars)
        this
      case Nil => KeywordTrieNode(Some(word), Some(cleanName))
    }
  }

  override def toString: String = {
    toStringHelper("")
  }

  def toStringHelper(pad: String): String = {
    val sb: mutable.StringBuilder = new mutable.StringBuilder()
    get().map(sb.append)
    sb.append("\n")
    if (children.nonEmpty) {
      children.map {
        case (key, value) =>
          sb.append(pad).append(key).append(":").append(value.toStringHelper(pad + " "))
      }
    }
    sb.toString()
  }
}
