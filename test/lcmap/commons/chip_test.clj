(ns lcmap.commons.chip-test
  (:require [clojure.test :refer :all]
            [lcmap.commons.chip :refer :all]))

(deftest basic-offset-test
  (testing "positive interval"
    (= 0 (difference   0  10))
    (= 3 (difference   3  10))
    (= 7 (difference  -3  10))
    (= 1 (difference 101  10)))
  (testing "negative interval"
    (=  0 (difference   0 -10))
    (= -9 (difference   1 -10))
    (= -7 (difference   3 -10))
    (= -1 (difference   9 -10))))

(deftest basic-near-test
  (testing "unshifted interval"
    (is (==   0 (near  3 10 0)))
    (is (==  10 (near 13 10 0)))
    (is (== -10 (near -5 10 0))))
  (testing "shifted interval"
    ;; zero shifts left to -5 because the
    ;; shifted interval is between -5..5
    (is (==  -5 (near    0 10 -5)))
    ;; one-hundred shifts to 95 because the
    ;; interval is between 95..105
    (is (==  95 (near  100 10 -5)))))

(deftest realistic-near-calculation
  ;; This test uses values derived from an ESPA output.
  ;;
  (testing "x-axis, with positive pixel geometry"
    (is (== -2040585 (near -2040584 3000 2415)))
    (is (== -2040585 (near -2040585 3000 2415)))
    (is (not (== -2040585 (near -2040586 3000 2415)))))
  (testing "y-axis, with negative pixel geometry"
    (is (== 3164805 (near 3164804 -3000 -195)))
    (is (== 3164805 (near 3164805 -3000 -195)))
    (is (not (== 3164805 (near 3164806 -3000 -195))))))

(deftest snap-calculation
  (testing "real-world values"
    (let [chip-spec {:chip_x   3000
                     :chip_y  -3000
                     :shift_x  2415
                     :shift_y  -195}]
      (testing "a point on the grid"
        (let [[tx ty] (snap -2040585 3164805 chip-spec)]
          (is (= -2040585 tx))
          (is (=  3164805 ty))))
      (testing "a point in the grid"
        (let [[tx ty] (snap (+ -2040585 100)
                            (-  3164805 100)
                            chip-spec)]
          (is (= -2040585 tx))
          (is (=  3164805 ty)))))))
