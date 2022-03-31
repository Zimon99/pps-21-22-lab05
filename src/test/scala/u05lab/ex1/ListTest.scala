package u05lab.ex1

import org.junit.Test
import org.junit.Assert.*

class ListTest {

  private var list = List(1, 2, 3, 4)
  private var zipList = List((1, 0), (2, 1), (3, 2), (4, 3))

  @Test def testZipRightWithRec(): Unit = {
    assertEquals(zipList, list.zipRightWithRec)
  }

}
