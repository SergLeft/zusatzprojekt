import scala.collection.mutable

/** Ein dünn besetztes Array.
 *
 * @constructor Erzeugt ein neues Array mit der übergebenen Größe.
 * @param size die feste Größe dieses Arrays
 * @param default der Standardwert D für dieses Array
 */
class SparseArray[A](val size: Int, val default: A) {

  if (size < 0) {
    throw IllegalArgumentException("size must not be negative")
  }

  // bildet die Arrayposition auf den jeweiligen Wert ab
  private val entries = mutable.HashMap[Int, A]()

  /** Gibt zurück, welches Element an der übergebenen Position gespeichert ist.
   *
   * @throws IndexOutOfBoundsException wenn die Position im Array nicht existiert
   */
  def load(position: Int): A = ???

  /** Schreibt den übergebenen Wert an die übergebene Position im Array.
   *
   * @throws IndexOutOfBoundsException wenn die Position im Array nicht existiert
   */
  def update(position: Int, value: A): Unit = ???

  /** Gibt eine Sequenz mit allen Einträgen dieses Arrays zurück, inklusive der nicht explizit gespeicherten Standardwerte. */
  def asDense: Seq[A] = ???
}
