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

    override def getReviews: List[(Int, Map[Question, Int])] = reviews

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      //reviews = reviews.++(List((article, scores)))
      reviews = reviews.appended((article, scores))

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      reviews = reviews.++( List((article, Map(Question.RELEVANCE -> relevance, Question.SIGNIFICANCE -> significance, Question.CONFIDENCE -> confidence, Question.FINAL -> fin))) )

    override def orderedScores(article: Int, question: Question): List[Int] =
      reviews.filter( e => e._1 == article).map( e => e._2(question) ).sorted

    override def averageFinalScore(article: Int): Double =
      reviews.filter( e => e._1 == article).map( e => e._2(Question.FINAL) ).sum / reviews.count( e => e._1 == article ).asInstanceOf[Double]

    override def acceptedArticles: Set[Int] =
      reviews.filter( e => averageFinalScore(e._1) > 5 && e._2(Question.RELEVANCE) >= 8 ).map(k => k._1).toSet

    override def sortedAcceptedArticles: List[(Int, Double)] =
      //acceptedArticles.map(e => (e, averageFinalScore(e))).toList.sortWith( (arg1, arg2) => arg1._2 < arg2._2  )
      acceptedArticles.map(e => (e, averageFinalScore(e))).toList.sortWith(_._2 < _._2)

    // group by --> (Int, List[(Int, Map[Question, Int])])
    // Formula: CONFIDENCE*FINAL/10
    override def averageWeightedFinalScoreMap: Map[Int, Double] =
      reviews.groupBy(e => e._1).map( e => (e._1, calcAverageWeighted(e._2)) ).toMap

    private def calcAverageWeighted(l: List[(Int, Map[Question, Int])]): Double =
      l.map(  e => e._2(Question.CONFIDENCE) * e._2(Question.FINAL) / 10.0 ).sum / l.size.asInstanceOf[Double]

