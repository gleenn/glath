(ns glath.core)

(defn square [x] (* x x))

(defn NaN->0 [x] (if (.isNaN x) 0 x))

(defn cosine-similarity [vec-a vec-b]
  (/
    (reduce + (map * vec-a vec-b))
    (*
      (Math/sqrt (reduce + (map square vec-a)))
      (Math/sqrt (reduce + (map square vec-b))))))
