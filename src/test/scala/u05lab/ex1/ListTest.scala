package u05lab.ex1

import org.junit.Test
import org.junit.Assert.*

class ListTest {

  private var list = List(1, 2, 3, 4)
  private var zipList = List((1, 0), (2, 1), (3, 2), (4, 3))
  private var partList = (List(2, 4), List(1, 3))
  private var partList2 = (List(1, 2), List(3, 4))

  @Test def testZipRightWithRec(): Unit = {
    assertEquals(zipList, list.zipRightWithRec)
  }

  @Test def testPartition(): Unit = {
    assertEquals(list.partition(_ % 2 == 0), partList)
    assertEquals(list.partition(_ <= 2), partList2)
  }

}
