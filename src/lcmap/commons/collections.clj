(ns lcmap.commons.collections)

(defn vectorize
  "Guarantees value is a vector."
  [value]
  (cond (vector? value) value
        (or
         (sequential? value)
         (set? value))(into [] value)
        :else (conj [] value)))
