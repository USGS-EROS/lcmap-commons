(ns lcmap.commons.string
  (:require [clojure.string :as str]))

(defn end-with [token value]
  "Appends a token if one does not exist."
  (if-not (str/ends-with? value token)
    (str value token)
    value))

(defn lead-with [token value]
  "Prepends a token if one does not exist."
  (if-not (str/starts-with? value token)
    (str token value)
    value))

(defn singular [token value]
  "Replaces multiple tokens with a single."
  (let [regex (re-pattern (str token "+"))]
    (str/replace value regex token)))

(defn rstrip [token value]
  "Removes all occurances of trailing tokens from a value"
  (if (= token (str (last value)))
   (rstrip token (butlast value))
   (apply str value)))

(defn lstrip [token value]
  "Removes all occurances of leading tokens from a value"
  (if (= token (str (first value)))
    (lstrip token (rest value))
    (apply str value)))

(defn strip [token value]
  "Removes all leading and trailing tokens"
  (lstrip token (rstrip token value)))
