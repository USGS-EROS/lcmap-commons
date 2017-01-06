(ns lcmap.commons.collections-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.collections :refer :all]))

(deftest vectorize-test
  (testing "lcmap.commons.collections/vectorize"

    (def test_vals [[1 2 3] '(1 2 3) #{1 2 3} "a" 123 true nil])
    (doall (map #(is (vector? (vectorize %))) test_vals))

    (doall (for [item (take 3 test_vals)]
            (is (= (reduce + item)
                   (reduce + (vectorize item))))))))
