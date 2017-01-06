(ns lcmap.commons.collections)

(defn vectorize
  "Guarantees value is a vector"
  [value]
  (if (coll? value)
    (vec value)
    (vector value)))
