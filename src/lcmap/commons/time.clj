(ns lcmap.commons.time
  (:require [clj-time.format :as f]))

(def lcmap-format (f/formatters :date-time))

(defn str->timestamp
  [ts-str]
  (f/parse lcmap-format ts-str))

(defn timestamp->str
  [ts]
  (f/unparse lcmap-format ts))
