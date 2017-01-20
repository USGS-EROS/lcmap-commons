(ns lcmap.commons.numbers-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.numbers :refer :all]))

(deftest numberize-test
  (testing "lcmap.commons.numbers/numberize"
    (let [integers (vector (Integer/MIN_VALUE) -10 0 10 (Integer/MAX_VALUE))]
      (doall (map #(is (= % (numberize %))) integers))
      (doall (map #(is (= % (numberize (str %)))) integers))
      (doall (map #(is (= nil (numberize (str "will-be-nil" %)))) integers))
      (doall (map #(is (= % (numberize (str  % "wont-be-nil")))) integers))
      (doall (map #(is (= (long %) (numberize (long %)))) integers)))))
