package u05lab.ex2

import org.junit.Assert.*
import org.junit.Test
import u05lab.ex2.ConferenceReviewing.*

class ConferenceReviewingTest:
  private val cr: ConferenceReviewing = ConferenceReviewing()
  val map: Map[Question, Int] = Map(Question.SIGNIFICANCE -> 1)
  val map1: Map[Question, Int] = Map(Question.RELEVANCE -> 1, Question.SIGNIFICANCE -> 1, Question.CONFIDENCE -> 1, Question.FINAL -> 1)

  @Test def testLoadReview(): Unit =
    cr.loadReview(0, map)
    assertEquals(cr.getReviews, List((0, map)))

  @Test def testLoadReview2(): Unit =
    cr.loadReview(0, 1, 1, 1, 1)
    assertEquals(cr.getReviews, List((0, map1)))

  @Test def testOrderedScores(): Unit =
    cr.loadReview(0, 3, 1, 1, 1)
    cr.loadReview(0, 2, 1, 1, 1)
    cr.loadReview(0, 1, 1, 1, 1)
    assertEquals(cr.orderedScores(0, Question.RELEVANCE), List(1, 2, 3))

  @Test def testAverageFinalScore(): Unit =
    cr.loadReview(0, 3, 1, 1, 1)
    cr.loadReview(0, 2, 1, 1, 3)
    cr.loadReview(0, 1, 1, 1, 5)
    assertEquals(cr.averageFinalScore(0).asInstanceOf[Int], 3)

  
