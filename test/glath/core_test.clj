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

(deftest median-point-test
  (is (= [1/2 1/2 1/2]
         (median-point [[0 0 0]
                        [1 0 0]
                        [0 1 0]
                        [0 0 1]]))))
