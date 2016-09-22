(ns lcmap.commons.time-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.time :refer :all]
            [clj-time.core :as t]))

(deftest timestamp-test
  "Tests lcmap.commons.time/timestamp, timestamp->str, str->timestamp
   and lcmap-format-regex"
  (testing "lcmap.commons.time timestamps"
    (let [ts (timestamp)]
      (and (not (nil? (re-matches lcmap-format-regex ts)))
           (= ts (timestamp->str (str->timestamp ts)))))))
