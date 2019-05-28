package flashtext

object Main extends App {

  val keywordProcessor = KeywordProcessor()
  keywordProcessor.addKeyWord("word")
  keywordProcessor.addKeyWord("wrong")
  keywordProcessor.addKeyWord("win")
  keywordProcessor.addKeyWord("wing")
  val result = keywordProcessor.extractKeywords("This word is wrong".toList)
  println(result)
}
