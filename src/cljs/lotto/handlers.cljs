;; Handlers are event handlers to change the database

(ns lotto.handlers
    (:require [re-frame.core :as re-frame]
              [lotto.db :as db]
              [lotto.cards :as cards]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

;; width x height should be even
;; card-names is a set of unique card names
(defn deal-cards [deck width height]
  (let [num-cards (* width height)
        num-pairs (/ num-cards 2)
        shuffled-deck (shuffle deck)
        chosen-cards (take num-pairs shuffled-deck)
        chosen-cards-doubled (concat chosen-cards chosen-cards)
        game-deck (shuffle chosen-cards-doubled)
        game-deck-cards (for [card-name game-deck]
                          (cards/card card-name :back))
        rows (partition width game-deck-cards)
        cards-grid (vec (for [row rows]
                          (vec row)))]
    cards-grid))
        
(re-frame/reg-event-db
  :deal-cards
  (fn [db [_ card-names width height]]
    (assoc db 
      :cards (deal-cards card-names width height)
      :current-player :A)))

(re-frame/reg-event-db
  :flip-up
  (fn [db [_ x y]]
    (update-in db [:cards y x] cards/flip-up))) 
    
(re-frame/reg-sub
  :card-at
   (fn [db, [_ x y]]
     ;;(get (get (get db :cards) y) x)))
     (get-in db [:cards y x])))
