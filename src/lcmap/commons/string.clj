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

(defn remove-all-trailing [token value]
  "Removes all occurances of trailing tokens from a value"
  (if (str/ends-with? value token)
    (let [regex (re-pattern (str "(?<=.)" token "$"))]
      (remove-all-trailing token (str/replace value regex "")))
    (str value)))
