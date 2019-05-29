# FlashText4s

A idiomatic port of [flashtext.py](https://github.com/vi3k6i5/flashtext) into Scala

```scala
  val keywordProcessor = KeywordProcessor()
  keywordProcessor.addKeyWord("word", "sentence")
  keywordProcessor.addKeyWord("wrong", "correct")

  val replaceResult = keywordProcessor.replaceKeyWords("This word is wrong")
  println(replaceResult)  //This setence is correct
```