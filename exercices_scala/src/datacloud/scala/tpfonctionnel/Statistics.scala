package datacloud.scala.tpfonctionnel

object Statistics {
  def average(l : List[(Double, Int)]): Double = {  
    var l2 = l.flatMap((t: (Double, Int)) => List.fill(t._2)(t._1));
    val nbNote = l2.size;
    var sum = l2.reduce((a, b) => a + b);
    return sum/nbNote;
  }
}