(ns glath.core-test
  (:require [clojure.test :refer :all]
            [glath.core :refer :all]))

(deftest dot-product-test
  (is (= 6 (dot-product [1 2 3] [1 1 1])))
  (is (= 10 (dot-product [1 2 3] [3 2 1])))
  (is (= 6.0 (dot-product [1 2 3] [1.0 1.0 1.0]))))

(deftest cosine-similarity-test
  (testing "identical vectors have a similarity of 1"
    (is (Double/isNaN (cosine-similarity [0] [0])))
    (is (= 1.0 (cosine-similarity [1] [1])))
    (is (= 1.0 (cosine-similarity [2] [2])))
    (is (= 1.0 (cosine-similarity [0 1] [0 1])))
    (is (= 0.0 (cosine-similarity [1 0] [0 1])))))

(deftest transpose-test
  (is (= [[0.0 1.0 0.0 0.0]
          [0.0 0.0 1.0 0.0]
          [0.0 0.0 0.0 1.0]]
         (transpose [[0.0 0.0 0.0]
                     [1.0 0.0 0.0]
                     [0.0 1.0 0.0]
                     [0.0 0.0 1.0]]))))

(deftest chi-squared-test
  ;District:      A 	B 	C 	D 	total

  ;White collar 	90 	60 	104 95 	349
  ;Blue collar 	  30 	50 	51 	20 	151
  ;No collar 	    30 	40 	45 	35 	150
  ;Total 	        150 150 200 150 650

  (testing "the chi-squared value from https://en.wikipedia.org/wiki/Chi-squared_test"
    (is (= 24.571202835698884 (chi-squared [[90 60 104 95]
                                            [30 50 51 20]
                                            [30 40 45 35]])))))
