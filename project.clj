(defproject cmdstat "0.1.0"
  :description "a tool to find you top 10 command ever your used"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jansi-clj "0.1.0"]
                 [environ "1.0.0"]]
  :plugins [[lein-bin "0.3.5"]]
  :bin {:name "cmdstat"}
  :main ^:skip-aot cmdstat.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

