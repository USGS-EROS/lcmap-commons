(ns lcmap.commons.time
  "Time related utility functions and format standardization for lcmap."
  (:require [clj-time.format :as f]
            [clj-time.core :as t]))

(def lcmap-format
  "ISO 8601 compliant time format "
  (f/formatters :date-time))

(defn str->timestamp
  "String to timestamp as org.joda.time.DateTime"
  [ts-str]
  (f/parse lcmap-format ts-str))

(defn timestamp->str
  "Timestamp as string"
  [ts]
  (f/unparse lcmap-format ts))

(defn timestamp
  "Return current timestamp"
  ([] (timestamp->str (t/now))))
