(ns lcmap.commons.collections-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.collections :refer :all]))

(deftest vectorize-test
  (testing "lcmap.commons.collections/vectorize"

    (def test-vals [[1 2 3] '(1 2 3) #{1 2 3} "a" 123 true nil
                    {:a 1 :b 2 :c 3}])

    (doall (map #(is (vector? (vectorize %))) test-vals))

    (comment "For non-map collections, vectorize should create a vector
             with values that match")
    (doall (for [item (take 3 test-vals)]
            (is (= (reduce + item)
                   (reduce + (vectorize item))))))

    (comment "For maps, vectorize should include the entire map as a
             vector element")
    (let [test-map (last test-vals)
          map-vect (vectorize test-map)
          rebuilt  (first map-vect)]
      (is (map? rebuilt))
      (is (= test-map rebuilt)))))
