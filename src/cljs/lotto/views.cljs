(ns lotto.views
  (:require [lotto.utils :refer [state-viewer]]
            [re-frame.core :as re-frame]
            [lotto.cards :as cards]))

;Defines card face
(defn card-face [face]
  [:div.front
   [:div.card {:style {:font-size "240px"}}
    (get cards/mahjong face)]])
    
;Defines card back
(defn card-back []
  [:div.back
   [:div.card]])

(defn oriented-card [face side]
  [:div {:class (if (= :front side)
                  "flip-container"
                  "flip-container back-of-card")}
   [:div.flipper
    [card-back]
    [card-face face]]])
  

(defn main-panel []
  (let [current-player (re-frame/subscribe [:current-player])]
    [:div
     [:h1 "Welcome to my little world!"]
     [:h2 "Current State"]
     [:strong "Current Player: "]
     [:span @current-player]
     [oriented-card :one-of-bamboos :front]
     [state-viewer]]))
   
