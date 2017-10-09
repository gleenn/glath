(ns glath.core-test
  (:require [clojure.test :refer :all]
            [glath.core :refer :all]))

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

(deftest median-point-test
  (testing "returns the point that is the median point of the given points"
    (is (= [0.5 1.0 1.5]
           (median-point [[0.0 0.0 0.0]
                          [1.0 0.0 0.0]
                          [0.0 2.0 0.0]
                          [0.0 0.0 3.0]])))))

(deftest initial-classifications-test
  (with-redefs [shuffle identity]
    (is (= {[0.5 1.0 1.5] [[0.0 0.0 0.0]
                           [1.0 0.0 0.0]
                           [0.0 2.0 0.0]
                           [0.0 0.0 3.0]]}
           (initial-classifications [[0.0 0.0 0.0]
                                     [1.0 0.0 0.0]
                                     [0.0 2.0 0.0]
                                     [0.0 0.0 3.0]] 1)))

    (is (= {[0.5 0.0 0.0] [[0.0 0.0 0.0] [1.0 0.0 0.0]]
            [0.0 1.0 1.5] [[0.0 2.0 0.0] [0.0 0.0 3.0]]}
           (initial-classifications [[0.0 0.0 0.0]
                                     [1.0 0.0 0.0]
                                     [0.0 2.0 0.0]
                                     [0.0 0.0 3.0]] 2)))

    (is (= {[0.0 0.0 0.0] [[0.0 0.0 0.0]]
            [1.0 0.0 0.0] [[1.0 0.0 0.0]]
            [0.0 2.0 0.0] [[0.0 2.0 0.0]]
            [0.0 0.0 3.0] [[0.0 0.0 3.0]]}
           (initial-classifications [[0.0 0.0 0.0]
                                     [1.0 0.0 0.0]
                                     [0.0 2.0 0.0]
                                     [0.0 0.0 3.0]] 4)))))
