(ns lcmap.commons.string-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.string :refer :all]))

(def uuid-regex #"[a-z0-9{8}]-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}")

(deftest lead-with-test
  (testing "lcmap.commons.string/lead-with"
    (is (= "/nothing-leading"   (lead-with "/"     "nothing-leading")))
    (is (= "/something-leading" (lead-with "/"     "/something-leading")))
    (is (= "///already-leading" (lead-with "/"     "///already-leading")))
    (is (= "/////lead-multiple" (lead-with "/////" "lead-multiple")))))

(deftest end-with-test
  (testing "lcmap.commons.string/end-with"
    (is (= "nothing-ending/"    (end-with "/"     "nothing-ending")))
    (is (= "something-ending/"  (end-with "/"     "something-ending/")))
    (is (= "already-ending///"  (end-with "/"     "already-ending///")))
    (is (= "end-multiple/////"  (end-with "/////" "end-multiple")))))

(deftest singular-test
  (testing "lcmap.commons.string/singular"
    (is (= "/only-one-please"     (singular "/" "/////only-one-please")))
    (is (= "/they/are/everywhere" (singular "/" "////they//are///everywhere")))
    (is (= "nothing-happening"    (singular "/" "nothing-happening")))))
