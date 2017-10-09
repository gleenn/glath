(ns glath.core)

(defrecord Embed [x y z])

(defn square [x] (* x x))

(defn cosine-similarity [vec-a vec-b]
  (/
    (reduce + (map * vec-a vec-b))
    (*
      (Math/sqrt (reduce + (map square vec-a)))
      (Math/sqrt (reduce + (map square vec-b))))))

(defn transpose [m]
  (apply mapv vector m))

(defn dist [point-a point-b]
  )

(defn median-point [points n-groups]
  (map #(apply min %)
       (partition n-groups (transpose points))))

(defn initial-median-points [points n-groups]
  (let [median-points (take n-groups (shuffle points))]
    (reduce (fn [median-point results]
              (assoc results median-point))
            {}
            median-points)))

#_(defn k-means [points n-groups]
  ; take points and assign them to initial groups
  ; [[0 0 1] [0 1 0] [1 0 0] [0 0 2] [0 2 0] [2 0 0]] =>
  ; 1. map of group-index G_i => random non-overlapping set of points
  ; 2. calculate median point for each group based on points contained in G_i for each i
  ; 3. a) calculate distance from G_i to each point
  ;    b) select G_i that has min distance as
  ;    c) reassign every point to new group-index based on which G_i is closest
  ; 4. goto step 2

  (let [groups-medians (group-medians)]
    (loop []))
  )

(defn median-point [points]
  (->> points
       transpose
       (map (juxt #(apply min %) #(apply max %)))
       (map (fn [[min max]] (+ min (/ (- max min) 2))))))
