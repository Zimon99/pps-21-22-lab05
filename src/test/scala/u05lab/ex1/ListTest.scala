package u05lab.ex1

import org.junit.Test
import org.junit.Assert.*

class ListTest:

  private val list = List(1, 2, 3, 4)
  private val list1 = List(Nil)
  private val zipList = List((1, 0), (2, 1), (3, 2), (4, 3))
  private val partList1 = (List(2, 4), List(1, 3))
  private val partList2 = (List(1, 2), List(3, 4))
  private val spanList1 = (List(1), List(2, 3, 4))
  private val spanList2 = (List(1, 2), List(3, 4))

  @Test def testZipRightWithRec(): Unit = {
    assertEquals(zipList, list.zipRightWithRec)
  }

  @Test def testPartitionWithRec(): Unit = {
    assertEquals(list.partitionWithRec(_ % 2 == 0), partList1)
    assertEquals(list.partitionWithRec(_ <= 2), partList2)
  }

  @Test def testPartitionWithFilter(): Unit = {
    assertEquals(list.partitionWithFilter(_ % 2 == 0), partList1)
    assertEquals(list.partitionWithFilter(_ <= 2), partList2)
  }

  @Test def testSpanWithRec(): Unit = {
    assertEquals(spanList1, list.spanWithRec(_ % 2 != 0))
    assertEquals(spanList2, list.spanWithRec(_ < 3))
  }

  @Test def testReduceWithRec(): Unit = {
    assertEquals(10, list.reduce(_ + _))
    assertEquals(10, List(10).reduce(_ + _))
  }

  @Test def testTakeRight(): Unit = {
    assertEquals(List(4), list.takeRight(1))
    assertEquals(List(3, 4), list.takeRight(2))
    assertEquals(List(2, 3, 4), list.takeRight(3))
    assertEquals(List(1, 2, 3, 4), list.takeRight(4))
  }