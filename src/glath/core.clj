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

(defn chi-squared [table]
  (let [observations (->> table
                          (map (partial reduce +))
                          (reduce +))
        observations-inv (float (/ 1 observations))
        col-sums (map (partial reduce +) (transpose table))
        ;_ (prn :col-sums col-sums)
        row-sums (map (partial reduce +) table)
        ;_ (prn :row-sums row-sums)
        ]

    (->> (for [i (range (count (first table)))
               j (range (count table))]
           (let [col-sum (nth col-sums i)
                 ;_ (prn :col-sum col-sum)
                 row-sum (nth row-sums j)
                 ;_ (prn :row-sum row-sum)
                 expected (* col-sum row-sum observations-inv)
                 ;_ (prn :expected expected)
                 observed (nth (nth table j) i)
                 ;_ (prn :observed observed)
                 diff (- observed expected)
                 statistic (/ (* diff diff) expected)
                 ;_ (prn :statistic statistic)
                 ]
             statistic))
         (reduce +))))
