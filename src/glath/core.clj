(ns glath.core)

(defn sqrt
  "I compute the square root of a number x"
  [x]
  (iterate #(apply approx %) [x 3]))

(defn approx [x guess]
  (/ (+ x (/ x guess)) 2))
