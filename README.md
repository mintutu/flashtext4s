# FlashText4s
[![Build Status](https://travis-ci.com/mintutu/flashtext4s.svg?branch=master)](https://travis-ci.com/mintutu/flashtext4s)
[![codecov](https://codecov.io/gh/mintutu/flashtext4s/branch/master/graph/badge.svg)](https://codecov.io/gh/mintutu/flashtext4s)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/49a8205a80dc4ecd8820b64f48ea600c)](https://www.codacy.com/app/specterbn/flashtext4s?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mintutu/flashtext4s&amp;utm_campaign=Badge_Grade)

A idiomatic port of [flashtext.py](https://github.com/vi3k6i5/flashtext) into Scala

###Usage
***Extract keywords***
```scala
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala")
    keywordProcessor.addKeyWord("Java")
    val actualResult = keywordProcessor.findKeywords("I like Scala and Java")
    actualResult shouldBe Set("Scala", "Java")
```

***Replace keywords***
```scala
    val keywordProcessor = KeywordProcessor()
    keywordProcessor.addKeyWord("Scala", "Python")
    keywordProcessor.addKeyWord("Java", "C++")
    val actualResult = keywordProcessor.replaceKeywords("I like Scala and Java")
    actualResult shouldBe "I like Python and C++"
```
