package datacloud.scala.tpfonctionnel.catalogue

import datacloud.scala.tpobject.catalogue.CatalogueWithNonMutable

class CatalogueSoldeWithFor extends CatalogueWithNonMutable with CatalogueSolde {
    def solde(n: Integer): Unit = {
      for ((k, v) <- map) {
        removeProduct(k);
        storeProduct(k, v * ((100.0 - n) /100.0));
      }
    }
}