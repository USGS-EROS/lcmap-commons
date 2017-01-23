(ns lcmap.commons.collections)

(defn vectorize
  "Guarantees value is a vector"
  [value]
  (if (and (coll? value)(not (map? value)))
    (vec value)
    (vector value)))
