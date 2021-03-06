(ns glath.square-root-test
  (:require [clojure.test :refer [deftest testing is]]
            [glath.square-root :refer :all]))

(deftest approx-test
  (testing "That the approximate square root of 9 is "
    (is (= (approx 9 4) (/ (+ 4 (/ 9 4)) 2)))))

(defn test-sqrt [num expected-sqrt]
  (testing (str "That the square root of " num " is " expected-sqrt)
    (is (< -0.000000001 (- (sqrt num) expected-sqrt) 0.000000001))))

(deftest sqrt-test
  (test-sqrt 9 3)
  (test-sqrt 100 10)
  (test-sqrt 10 3.1622776985168457)
  (test-sqrt 10000000000 100000)
  (test-sqrt 100000000000000000000 10000000000))
