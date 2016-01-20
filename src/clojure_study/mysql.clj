(ns clojure-study.mysql
	(require [clojure.java.jdbc :as j]))
(def mysql-db {:subprotocol "mysql"
 	:subname "//127.0.0.1:17100/mysql"
	:user "root"
	:password ""})
(defn -main [& args] 
 (doall (map println (j/query mysql-db "show tables"))))
