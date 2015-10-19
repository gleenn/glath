(ns glath.core)

(defn approx [x guess]
  (/ (+ guess (/ x guess)) 2))

(defn sqrt
  "I compute the square root of a number x"
  [x]
  (loop [i 10 guess (/ x 2)]
    (if (= i 0)
      (float guess)
      (recur (dec i) (approx x guess)))))