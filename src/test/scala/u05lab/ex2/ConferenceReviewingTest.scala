package u05lab.ex2

import org.junit.Assert.*
import org.junit.Test
import u05lab.ex2.ConferenceReviewing
import u05lab.ex2.ConferenceReviewing.Question

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
    var l: List[(Int, Double)] = List( (2, 7), (0, 9), (1, 10) )
    assertEquals(  l , cr.sortedAcceptedArticles)


