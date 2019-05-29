package flashtext

import java.util.concurrent.TimeUnit

import flashtext.KeywordPerfTest.KeywordState
import org.openjdk.jmh.annotations._

import scala.collection.mutable
import scala.util.Random

object KeywordPerfTest {

  @State(Scope.Benchmark)
  class KeywordState() {
    @Param(Array("1000", "10000", "100000"))
    var keywordsSize: Int = _

    var keywords: mutable.ListBuffer[String] = mutable.ListBuffer[String]()
    var content: String = _
    val keywordProcessor = KeywordProcessor()
    val maxWordLength = 10
    val contentSize = 50000

    @Setup()
    def setup() = {
      (1 to keywordsSize).map { _ =>
        val word = randomAlphaNumericString(Random.nextInt(maxWordLength))
        keywords += word
        keywordProcessor.addKeyWord(word)
      }

      content = (1 to contentSize).map { _ =>
        randomAlphaNumericString(Random.nextInt(maxWordLength))
      }.mkString(" ")
    }

    def randomAlphaNumericString(length: Int): String = {
      val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      randomStringFromCharList(length, chars)
    }

    def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
      val sb = new StringBuilder
      for (i <- 1 to length) {
        val randomNum = util.Random.nextInt(chars.length)
        sb.append(chars(randomNum))
      }
      sb.toString
    }

  }
}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class KeywordPerfTest {
  @Benchmark
  def extractWithKeywordProcessor(state: KeywordState) = {
    state.keywordProcessor.findKeywords(state.content)
  }

  @Benchmark
  def extractWithContains(state: KeywordState) = {
    state.keywords.filter(state.content.contains(_))
  }
}
