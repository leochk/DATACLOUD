package datacloud.scala.tpfonctionnel

object Counters {
  def nbLetters(str: List[String]): Int = {
    var cpt = 0;
    var tmp = str.flatMap(_.split(" ")).map(_.size).reduce((a, b) => a + b);
    return tmp;
  }
}