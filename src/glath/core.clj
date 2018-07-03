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

(defn cartesian-distance [point-a point-b]
  (->> (map - point-a point-b)
       (map square)
       (reduce +)
       Math/sqrt))

(defn median-point [points]
  (->> points
       transpose
       (map (juxt #(apply min %) #(apply max %)))
       (map (fn [[min max]] (+ min (double (/ (- max min) 2)))))))

(defn initial-classifications [k-groups points]
  (->> points
       shuffle
       (partition-all (/ (count points) k-groups))
       (mapcat (fn [grouped-points] [(median-point grouped-points) grouped-points]))
       (apply hash-map)))

(defn reclassify [classification]
  "Classification is a map where the key is the center (median-point) in N-dimensional space
   and the values are the set of points that are in that center's group. This function re-arranges
   points so they are in the group that has the closest median-point, then recalculates the
   median-points for each group."
  (let [median-points (keys classification)
        all-points (apply concat (vals classification))
        re-grouped-classification
        (reduce (fn [new-classification point]
                  (let [closest-median-point (apply min-key #(cartesian-distance point %) median-points)]
                    (update new-classification
                            closest-median-point
                            (fnil #(conj % point) (hash-set)))))
                {} all-points)]
    (reduce (fn [result [_ new-group]]
              (assoc result (median-point new-group) new-group)) {}
            re-grouped-classification)))

(defn k-means [k-groups points]
  " - take points and assign them to initial K groups randomly
    - calculate median point for each group based on points contained in G_i for each i
    - a) calculate distance from G_i to each point
      b) select G_i that has min distance as
      c) reassign every point to new group-index based on which G_i is closest
    - goto step 2 if things have moved"
  (loop [classification (initial-classifications k-groups points) i 0]
    (let [new-classification (reclassify classification)]
      (println "looping" i)
      (if (= classification new-classification)
        classification
        (recur (reclassify new-classification) (inc i))))))
