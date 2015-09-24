(ns cmdstat.core
  (:require [environ.core :refer [env]]
            [clojure.string :as cs]
            [jansi-clj.core :refer :all])
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
                      (let [command (magenta command)
                            weight (cyan (str weight " times"))
                            arrow (yellow "=>")]
                        (format "%s %s %s" command arrow weight)))]
    (cs/join "\n" (map format-one history-map))))

(defn format-history-lines
  [history-lines]
  (->> (map history-line->command history-lines)
       (frequencies)
       (sort-by-weight)
       (take 10)
       (format-history-map)))

(defn -main
  [& args]
  (let [file-path (if args
                    (first args)
                    (-> (env :home)
                        (str "/.zsh_history")))]
    (-> file-path
        slurp
        (cs/split #"\n")
        format-history-lines
        println)))
