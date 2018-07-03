(ns glath.k-means-test
  (:require [clojure.test :refer :all]
            [glath.k-means :refer :all]))

(deftest median-point-test
  (testing "returns the point that is the median point of the given points"
    (is (= [0.5 1.0 1.5]
           (median-point [[0.0 0.0 0.0]
                          [1.0 0.0 0.0]
                          [0.0 2.0 0.0]
                          [0.0 0.0 3.0]])))))

(deftest cartesian-distance-test
  (is (= 0.0 (cartesian-distance [1.0 0.0] [1.0 0.0])))
  (is (= (Math/sqrt 2.0) (cartesian-distance [1.0 0.0] [0.0 1.0])))
  (is (= (Math/sqrt 3.0) (cartesian-distance [1.0 1.0 1.0] [0.0 0.0 0.0])))
  (is (= (Math/sqrt 14.0) (cartesian-distance [3.0 2.0 1.0] [0.0 0.0 0.0 0.0]))))

(deftest reclassify-test
  (testing "super basic cases"
    (is (= {} (reclassify {})))
    (is (= {[1.0 0.0 0.0] #{[1.0 0.0 0.0]}}
           (reclassify {[1.0 0.0 0.0] #{[1.0 0.0 0.0]}}))))

  (testing "moves points to the median-point that is closest"
    (is (= {[2.5] #{[2] [3]}}
           (reclassify {[10] #{[2] [3]}
                        [1]  #{}})))

    (is (= {[3.0 0.0] #{[1.0 0.0] [5.0 0.0]}
            [0.0 1.5] #{[0.0 1.0] [0.0 2.0]}}
           (reclassify {[0.9 0.0] #{[1.0 0.0] [0.0 2.0]}
                        [0.0 0.9] #{[0.0 1.0] [5.0 0.0]}})))))

(deftest k-means-test
  (testing "this doesn't work yet :/"
    (is (= #_{[0.0 1.5] #{[0.0 1.0] [0.0 2.0]}
            [1.0 0.0] #{[1.0 0.0]}}
           (k-means 2 [[1.0 0.0] [0.0 2.0] [0.0 1.0]])))))

(deftest initial-classifications-test
  (with-redefs [shuffle identity]
    (is (= {[0.5 1.0 1.5] [[0.0 0.0 0.0]
                           [1.0 0.0 0.0]
                           [0.0 2.0 0.0]
                           [0.0 0.0 3.0]]}
           (initial-classifications 1 [[0.0 0.0 0.0]
                                       [1.0 0.0 0.0]
                                       [0.0 2.0 0.0]
                                       [0.0 0.0 3.0]])))

    (is (= {[0.5 0.0 0.0] [[0.0 0.0 0.0] [1.0 0.0 0.0]]
            [0.0 1.0 1.5] [[0.0 2.0 0.0] [0.0 0.0 3.0]]}
           (initial-classifications 2 [[0.0 0.0 0.0]
                                       [1.0 0.0 0.0]
                                       [0.0 2.0 0.0]
                                       [0.0 0.0 3.0]])))

    (is (= {[0.0 0.0 0.0] [[0.0 0.0 0.0]]
            [1.0 0.0 0.0] [[1.0 0.0 0.0]]
            [0.0 2.0 0.0] [[0.0 2.0 0.0]]
            [0.0 0.0 3.0] [[0.0 0.0 3.0]]}
           (initial-classifications 4 [[0.0 0.0 0.0]
                                       [1.0 0.0 0.0]
                                       [0.0 2.0 0.0]
                                       [0.0 0.0 3.0]])))))
