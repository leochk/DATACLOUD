package datacloud.scala.tpobject.catalogue

import scala.collection.mutable.HashMap

class CatalogueWithMutable extends Catalogue {
   protected var map: HashMap[String, Double] = HashMap();
   
   override def getPrice(produit: String): Double = {
     if (!map.contains(produit)) return -1.0
     return map(produit);
   }
   
   override def removeProduct(produit: String): Unit = {
     map.remove(produit); 
   }
   
   override def selectProducts(min: Double, max: Double): List[String] = {
     var res = List[String]();
     for ((k,v) <- map)
       if (v <= max && v >= min) res = res.+:(k);
     return res.toList;
   }   
   
   override def storeProduct(produit: String, prix: Double): Unit = {
     map(produit) = prix;
   }
}