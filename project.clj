(defproject clojure-study "0.1.0-SNAPSHOT"
  :description "clojure 스터디"
  :url "http://github.com/dsdstudio/clojure-study"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] 
  			[org.clojure/java.jdbc "0.4.2"]
  			[mysql/mysql-connector-java "5.1.38"]
  ]
  :main clojure-study.mysql)
