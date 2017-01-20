(ns lcmap.commons.numbers)

(defmulti numberize
  "Converts a string to a number or nil.  If the string contains a mix of
   number and character data, returns "
  (fn [n] (type n)))

(defmethod numberize :default [n]
  nil)

(defmethod numberize Number [number]
  number)

(defmethod numberize String [string]
  (let [number-format (java.text.NumberFormat/getInstance)]
    (try
      (.parse number-format string)
      (catch java.text.ParseException ex nil))))
