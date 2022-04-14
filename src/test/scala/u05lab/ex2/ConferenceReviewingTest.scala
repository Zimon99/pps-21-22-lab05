package u05lab.ex2

import org.junit.Assert.*
import org.junit.Test
import u05lab.ex2.ConferenceReviewing
import u05lab.ex2.ConferenceReviewing.Question

import scala.collection.immutable.HashMap

class ConferenceReviewingTest:
  private val cr: ConferenceReviewing = ConferenceReviewing()
  val map: Map[Question, Int] = Map(Question.SIGNIFICANCE -> 1)
  val map1: Map[Question, Int] = Map(Question.RELEVANCE -> 1, Question.SIGNIFICANCE -> 1, Question.CONFIDENCE -> 1, Question.FINAL -> 1)

  @Test def testLoadReview(): Unit =
    cr.loadReview(0, map)
    assertEquals(List((0, map)), cr.getReviews)

  @Test def testLoadReview2(): Unit =
    cr.loadReview(0, 1, 1, 1, 1)
    assertEquals(List((0, map1)), cr.getReviews)

  @Test def testOrderedScores(): Unit =
    cr.loadReview(0, 3, 1, 1, 1)
    cr.loadReview(0, 2, 1, 1, 1)
    cr.loadReview(0, 1, 1, 1, 1)
    assertEquals(List(1, 2, 3), cr.orderedScores(0, Question.RELEVANCE))

  @Test def testAverageFinalScore(): Unit =
    cr.loadReview(0, 3, 1, 1, 4)
    cr.loadReview(0, 2, 1, 1, 9)
    assertEquals(6.5, cr.averageFinalScore(0), 0.1)

  @Test def testAcceptedArticles(): Unit =
    cr.loadReview(0, 8, 1, 1, 6)
    cr.loadReview(1, 8, 1, 1, 6)
    assertEquals(Set(0, 1), cr.acceptedArticles)

  @Test def testSortedAcceptedArticles(): Unit =
    cr.loadReview(0, 8, 1, 1, 9)
    cr.loadReview(1, 8, 1, 1, 10)
    cr.loadReview(2, 8, 1, 1, 7)
    assertEquals(  List( (2, 7), (0, 9), (1, 10) ), cr.sortedAcceptedArticles)

  @Test def testAverageWeightedFinalScoreMap(): Unit =
    cr.loadReview(0, 8, 1, 5, 10) // 5 * 10 / 10 -> 5
    cr.loadReview(0, 8, 1, 4, 5)  // 4 * 5 / 10 -> 2     average -> 5
    cr.loadReview(0, 8, 1, 8, 10) // 8 * 10 / 10 -> 8
    cr.loadReview(1, 8, 1, 5, 3)  // 5 * 3 / 10 -> 1.5
    cr.loadReview(1, 8, 1, 3, 10) // 3 * 10 / 10 -> 3    average -> 3
    cr.loadReview(1, 8, 1, 9, 5)  // 4 * 5 / 10 -> 4.5
    cr.loadReview(2, 8, 1, 7, 5)  // 7 * 5 / 10 -> 3.5   average -> 3.5
    assertEquals(Map(0 -> 5.0, 1 -> 3.0, 2 -> 3.5), cr.averageWeightedFinalScoreMap)


