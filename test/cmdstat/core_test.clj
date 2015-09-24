(ns cmdstat.core-test
  (:require [clojure.test :refer :all]
            [environ.core :refer [env]]
            [cmdstat.core :refer :all]))

(deftest env-test
  (testing "should get the same path"
    (is (= (env :home) (System/getProperty "user.home")))))
