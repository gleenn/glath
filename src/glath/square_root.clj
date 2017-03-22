(ns glath.square-root)

(defn approx [x guess]
  (/ (+ guess (/ x guess)) 2))

(defn sqrt
  "I compute the square root of a number x"
  [x]
  (let [epsilon 0.00000001]
    (loop [guess (/ x 2) previous-guess 0]
      (if (< (- epsilon) (- guess previous-guess) epsilon)
        guess
        (recur (float (approx x guess)) guess)))))
