(ns image-api.core
    (:require [org.httpkit.server :as server]
      [compojure.core :refer :all]
      [compojure.route :as route]
      [ring.middleware.defaults :refer :all]
      [clojure.pprint :as pp]
      [clojure.string :as str]
      [clojure.data.json :as json]
      [clj-http.client :as client])
  (:gen-class))
  

(def image "https://github.com/robchamberspfc/little-one-images/blob/master/content/00.%20November-December%202017/IMG-20171128-WA0000.jpg?raw=true")


; Simple Body Page
(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (str "<img src='" image "'>"))})

; request-example
(defn request-example [req]
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (->>
                (pp/pprint req)
                (str "Request Object: " req))})

(defn test [req]
  ; println (str (client/get "https://defra-floods-testing.publishmydata.com/flood-monitoring/id/floods")))
  println (str (System/getenv "TESTROB123")))
  ; println "Hello")

(defroutes app-routes
  (GET "/" [] simple-body-page)
  (GET "/request" [] request-example)
  (GET "/test" [] test)
     (route/not-found "Error, page not found!"))

(defn -main
  "This is our main entry point"
  [& args]    
    (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})      
      ; Run the server without ring defaults
      ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))