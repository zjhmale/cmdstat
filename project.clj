(defproject zsh-history "a tool to find you top 10 command ever your used"
  :description "FIXME: write description"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [jansi-clj "0.1.0"]
                 [environ "1.0.1"]]
  :main ^:skip-aot zsh-history.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
