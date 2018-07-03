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
