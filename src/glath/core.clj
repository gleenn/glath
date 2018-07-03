(ns glath.core)

(defrecord Embed [x y z])

(defn square [x] (* x x))

(defn dot-product [vec-a vec-b]
  (reduce + (map * vec-a vec-b)))

(defn transpose [m]
  (apply mapv vector m))

(defn cosine-similarity [vec-a vec-b]
  (/
    (dot-product vec-a vec-b)
    (*
      (Math/sqrt (reduce + (map square vec-a)))
      (Math/sqrt (reduce + (map square vec-b))))))
