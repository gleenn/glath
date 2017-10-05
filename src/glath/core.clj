(ns glath.core)

(defrecord Embed [x y z])

(defn square [x] (* x x))

(defn cosine-similarity [vec-a vec-b]
  (/
    (reduce + (map * vec-a vec-b))
    (*
      (Math/sqrt (reduce + (map square vec-a)))
      (Math/sqrt (reduce + (map square vec-b))))))

(defn median-point [points]
  (let [N (count (first points))
        mins (for [i (range N)]
          (apply min (for [point points] (nth point i))))
        maxs (for [i (range N)]
          (apply max (for [point points] (nth point i))))]
    (map (fn [min max] (+ min (/ (- max min) 2))) mins maxs)))

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
