(defproject glath "0.1.0-SNAPSHOT"
  :description "Clojure library that implements some basic math functions"
  :url "https://github.com/gleenn/glath"

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/test.check "0.9.0" :scope "test"]]

  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.19.0"]]}})
