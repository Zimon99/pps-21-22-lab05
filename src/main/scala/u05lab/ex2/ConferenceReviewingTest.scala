package u05lab.ex2

import org.junit.Test
import org.junit.Assert.*
import u05lab.ex2.ConferenceReviewing.Question

class ConferenceReviewingTest:
  private val cr: ConferenceReviewing = ConferenceReviewing()
  val map: Map[Question, Int] = Map(Question.SIGNIFICANCE -> 1)

  @Test def testLoadReview(): Unit =
    cr.loadReview(0, map)
    assertEquals(cr.getReviews, List((0, map)))
