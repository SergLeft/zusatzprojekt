import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable
import java.lang.reflect.Field


class SparseArraySuite extends AnyFunSuite with Matchers {

  private def getEntriesMap[A](sparse: SparseArray[A]): mutable.HashMap[Int, A] = {
    val clazz: Class[_] = sparse.getClass
    val field: Field = clazz.getDeclaredField("entries")
    field.setAccessible(true)
    // Cast is safe because we know the exact type that the class declares
    field.get(sparse).asInstanceOf[mutable.HashMap[Int, A]]
  }

  test("load returns the default value for an unset, valid position") {
    val size = 10
    val default = "empty"
    val arr = new SparseArray[String](size, default)

    // every position in range that hasn't been written to should yield the default
    for (i <- 0 until size) {
      arr.load(i) shouldBe default
    }
  }

  test("load returns the stored value after an update") {
    val size = 5
    val arr = new SparseArray[Int](size, 0)

    arr.update(2, 42)
    arr.load(2) shouldBe 42
  }

  test("asDense returns a dense Seq with default values for untouched positions") {
    val size = 4
    val default = -1
    val arr = new SparseArray[Int](size, default)

    arr.update(1, 10)
    arr.update(3, 30)

    // Expected dense view: [default, 10, default, 30]
    val expected = Seq(default, 10, default, 30)
    arr.asDense should contain theSameElementsInOrderAs expected
  }

  test("asDense returns a Seq consisting solely of the default value when no updates are performed") {
    val size = 3
    val default = "x"
    val arr = new SparseArray[String](size, default)

    arr.asDense should contain theSameElementsInOrderAs Seq.fill(size)(default)
  }

  test("load throws IndexOutOfBoundsException for a negative index") {
    val arr = new SparseArray[Int](5, 0)
    assertThrows[IndexOutOfBoundsException] {
      arr.load(-1)
    }
  }

  test("load throws IndexOutOfBoundsException for an index equal to size") {
    val arr = new SparseArray[Int](5, 0)
    assertThrows[IndexOutOfBoundsException] {
      arr.load(5)
    }
  }

  test("update throws IndexOutOfBoundsException for a negative index") {
    val arr = new SparseArray[String](3, "#")
    assertThrows[IndexOutOfBoundsException] {
      arr.update(-2, "oops")
    }
  }

  test("update throws IndexOutOfBoundsException for an index greater than or equal to size") {
    val arr = new SparseArray[String](3, "#")
    assertThrows[IndexOutOfBoundsException] {
      arr.update(3, "out")
    }
  }

  test("the internal map does not contain the default value after construction") {
    val size = 7
    val default = 99
    val arr = new SparseArray[Int](size, default)

    val entries = getEntriesMap(arr)

    entries.isEmpty shouldBe true
    entries.values.exists(_ == default) shouldBe false
  }

  test("adding the default value does not create a map entry") {
    val size = 4
    val default = 0
    val arr = new SparseArray[Int](size, default)

    arr.update(2, default)

    val entries = getEntriesMap(arr)

    entries.get(2) shouldBe None
    entries.values.exists(_ == default) shouldBe false
  }

  test("updating a position with the default value does not create a map entry") {
    val size = 4
    val default = 0
    val arr = new SparseArray[Int](size, default)

    arr.update(2, 100)

    arr.load(2) shouldBe 100

    arr.update(2, default)

    arr.load(2) shouldBe default

    val entries = getEntriesMap(arr)

    entries.get(2) shouldBe None
    entries.values.exists(_ == default) shouldBe false
  }
}