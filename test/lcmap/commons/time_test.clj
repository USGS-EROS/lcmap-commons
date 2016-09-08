(ns lcmap.commons.time-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.time :refer :all]
            [clj-time.core :as t]))

(deftest timestamp-test
   (testing "lcmap.commons.time timestamps"
       (let [ts (t/now)]
           (is (= ts
                  (str->timestamp
                      (timestamp->str ts)))))))
