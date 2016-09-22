(ns lcmap.commons.time-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.time :refer :all]
            [clj-time.core :as t]))

(deftest timestamp-test
  "Tests lcmap.commons.time/timestamp, timestamp->str and str->timestamp"
  (testing "lcmap.commons.time timestamps"
    (let [ts (timestamp)]
      (= ts (timestamp->str (str->timestamp ts))))))
