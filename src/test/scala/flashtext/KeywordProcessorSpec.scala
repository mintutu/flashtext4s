package flashtext

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable._

class KeywordProcessorSpec extends FlatSpec with Matchers {

  "findKeywords" should "find keyword at the end of the sentence" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("apple")
    val actualResult = keywordProcessor.findKeywords("I like the apple")
    actualResult shouldBe Set("apple")
  }

  "findKeywords" should "skip incomplete at the end of the sentence" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("pineapple")
    val actualResult = keywordProcessor.findKeywords("I like the apple")
    actualResult shouldBe Set()
  }

  "findKeywords" should "find keyword at the beginning" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala")
    val actualResult = keywordProcessor.findKeywords("Scala is the best programming language")
    actualResult shouldBe Set("Scala")
  }

  "findKeywords" should "find multiple keywords" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala")
    keywordProcessor.addKeyWord("Java")
    val actualResult = keywordProcessor.findKeywords("I like Scala and Java")
    actualResult shouldBe Set("Scala", "Java")
  }

  "findKeywords" should "find 2 keywords which one in other" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala")
    keywordProcessor.addKeyWord("Scalable")
    val actualResult = keywordProcessor.findKeywords("Scala is billed as a Scalable language")
    actualResult shouldBe Set("Scalable", "Scala")
  }

  "findKeywords" should "with case sensitive" in {
    val keywordProcessor = KeywordProcessor(true)
    keywordProcessor.addKeyWord("SCALA")
    val actualResult = keywordProcessor.findKeywords("I like scala")
    actualResult shouldBe Set("scala")
  }

////
  "replaceKeyWords" should "replace keyword at the end of the sentence" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("apple", "banana")
    val actualResult = keywordProcessor.replaceKeywords("I like the apple")
    actualResult shouldBe "I like the banana"
  }

  "replaceKeyWords" should "skip incomplete at the end of the sentence" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("pineapple", "banana")
    val actualResult = keywordProcessor.replaceKeywords("I like the apple")
    actualResult shouldBe "I like the apple"
  }

  "replaceKeyWords" should "find keyword at the beginning" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala", "Java")
    val actualResult = keywordProcessor.replaceKeywords("Scala is the best programming language")
    actualResult shouldBe "Java is the best programming language"
  }

  "replaceKeyWords" should "find multiple keywords" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala", "Python")
    keywordProcessor.addKeyWord("Java", "C++")
    val actualResult = keywordProcessor.replaceKeywords("I like Scala and Java")
    actualResult shouldBe "I like Python and C++"
  }

  "replaceKeyWords" should "find 2 keywords which one in other" in {
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala", "Java")
    keywordProcessor.addKeyWord("functional", "object oriented")
    val actualResult = keywordProcessor.replaceKeywords("Scala is functional programming")
    actualResult shouldBe "Java is object oriented programming"
  }

  "replaceKeyWords" should "with case sensitive" in {
    val keywordProcessor = KeywordProcessor(true)
    keywordProcessor.addKeyWord("SCALA", "the Pineapple")
    val actualResult = keywordProcessor.replaceKeywords("I like scala")
    actualResult shouldBe "I like the pineapple"
  }
}
