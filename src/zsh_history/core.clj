(ns zsh-history.core
  (:require [environ.core :refer [env]]
            [clojure.string :as cs])
  (:gen-class))

(defn history-line->command
  [line]
  (-> line
      (cs/split #";")
      second))

(defn weight [_ count] count)

(defn sort-by-weight
  [m]
  (->> (vec m)
       (sort-by (partial apply weight))
       reverse))

(defn format-history-map
  [history-map]
  (letfn [(format-one [[command weight]]
                      (format "%s => %d times\n" command weight))]
    (cs/join (map format-one history-map))))

(defn format-history-lines
  [history-lines]
  (->> (map history-line->command history-lines)
       (frequencies)
       (sort-by-weight)
       (take 10)
       (format-history-map)))

(defn -main
  [& args]
  (-> (env :home)
      (str "/.zsh_history")
      slurp
      (cs/split #"\n")
      format-history-lines
      print))
