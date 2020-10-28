package datacloud.scala.tpfonctionnel.catalogue

import datacloud.scala.tpobject.catalogue.CatalogueWithNonMutable

class CatalogueSoldeWithNamedFunction extends CatalogueWithNonMutable with CatalogueSolde {
  
  def solde(n: Integer): Unit = {
    var diminution_n = diminution(_: Double, n);
    map = map.mapValues(diminution_n);
  }
  
  def diminution(a: Double, percent: Int): Double = a * ((100.0 - percent) /100.0);
}