(ns lcmap.commons.id-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.id :refer :all]))

(def uuid-regex #"[a-z0-9{8}]-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}")

(deftest uuid-test
  (testing "lcmap.commons.id/uuid"
    (is (= (re-matches uuid-regex (uuid))))))
