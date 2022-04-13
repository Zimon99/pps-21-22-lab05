package u05lab.ex2

import u05lab.ex2.ConferenceReviewing.Question

trait ConferenceReviewing:
  def loadReview(article: Int, scores: Map[Question, Int]): Unit
  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit
  def orderedScores(article: Int, question: Question): List[Int]
  def averageFinalScore(article: Int): Double
  def acceptedArticles: Set[Int]
  def sortedAcceptedArticles: List[(Int, Double)]
  def averageWeightedFinalScoreMap: Map[Int, Double]
  def getReviews: List[(Int, Map[Question, Int])]
  

object ConferenceReviewing:
  enum Question:
    case RELEVANCE, SIGNIFICANCE, CONFIDENCE, FINAL

  def apply(): ConferenceReviewing = new ConferenceReviewingImpl

  private class ConferenceReviewingImpl extends ConferenceReviewing:
    private var reviews: List[(Int, Map[Question, Int])] = Nil

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      reviews = reviews.++(List((article, scores)))

    override def getReviews: List[(Int, Map[Question, Int])] = reviews

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit = ???
    override def orderedScores(article: Int, question: Question): List[Int] = ???
    override def averageFinalScore(article: Int): Double = ???
    override def acceptedArticles: Set[Int] = ???
    override def sortedAcceptedArticles: List[(Int, Double)] = ???
    override def averageWeightedFinalScoreMap: Map[Int, Double] = ???


