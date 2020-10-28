package datacloud.scala.tpfonctionnel.catalogue

import datacloud.scala.tpobject.catalogue.CatalogueWithNonMutable

class CatalogueSoldeWithAnoFunction extends CatalogueWithNonMutable with CatalogueSolde {
  
  def solde(n: Integer): Unit = {
    var diminution = (a: Double) => a * ((100.0 - n) /100.0);

    map = map.mapValues(diminution);
  }
  
}