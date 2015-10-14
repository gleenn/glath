(ns glath.core-test
  (:require [clojure.test :refer :all]
            [glath.core :refer :all]))

(deftest sqrt-test
  (testing "That the square root of 9 is 3"
    (is (= (sqrt 9) 3))))

(deftest approx-test
  (testing "That the approximate square root of 9 is "
    (is (= (approx 9 4) (/ (+ 9 (/ 9 4)) 2)))))
